package com.dia.app.common.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: Dia
 * @Description: 阿里云分块上传线程
 * @version: v1.0.0
 * @date: 2018年5月24日 下午1:36:04
 */
public class AliYunOSSRunnable implements Runnable {

	private MultipartFile file;
	private long startPos;
	private long partSize;
	private int partNumber;
	private String uploadId;
	private static String key;
	private static String bucketName;
	private static Logger logger = LoggerFactory.getLogger(AliYunOSSRunnable.class);

	// 新建一个List保存每个分块上传后的ETag和PartNumber
	public static List<PartETag> partETags = Collections.synchronizedList(new ArrayList<PartETag>());

	private OSSClient client = null;
	
    public AliYunOSSRunnable(OSSClient client,MultipartFile file, long startPos, long partSize, int partNumber, String uploadId, String key , String bucketName) {  
         this.client = client;
    	 this.file = file;
         this.startPos = startPos;
         this.partSize = partSize;
         this.partNumber = partNumber;
         this.uploadId = uploadId;
         AliYunOSSRunnable.key = key;
         AliYunOSSRunnable.bucketName = bucketName;
    }  
    
	@Override
	public synchronized void run() {
		InputStream inputStream = null;
		try {
			logger.info("AliYunOSSUtil 线程开启 ");
			inputStream = file.getInputStream();
			// 跳到每个分块的开头
			inputStream.skip(this.startPos);
			// 创建UploadPartRequest，上传分块
			UploadPartRequest uploadPartRequest = new UploadPartRequest();
			uploadPartRequest.setBucketName(bucketName);
			uploadPartRequest.setKey(key);
			uploadPartRequest.setUploadId(this.uploadId);
			uploadPartRequest.setInputStream(inputStream);
			uploadPartRequest.setPartSize(this.partSize);
			uploadPartRequest.setPartNumber(this.partNumber);
			UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);
			logger.info("Part#" + this.partNumber + " done\n");  
			synchronized (partETags) {
				// 将返回的PartETag保存到List中。
				partETags.add(uploadPartResult.getPartETag());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					// 关闭文件流
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	 
	/**
	 * 将文件分块进行升序排序并执行文件上传。
	 */
	public static void completeMultipartUpload(OSSClient client,String uploadId) {
		// 将文件分块按照升序排序
		Collections.sort(partETags, new Comparator<PartETag>() {
			@Override
			public int compare(PartETag p1, PartETag p2) {
				return p1.getPartNumber() - p2.getPartNumber();
			}
		}); 
		CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(bucketName,
				key, uploadId, partETags);
		// 完成分块上传
		client.completeMultipartUpload(completeMultipartUploadRequest);
	}


}
