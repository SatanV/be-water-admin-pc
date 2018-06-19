package com.dia.app.support.oss;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.ListPartsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PartListing;
import com.aliyun.oss.model.PartSummary;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;

/**
 * @author: Dia
 * @Description: 阿里云OSS
 * @version: v1.0.0
 * @date: 2018年5月19日 上午10:54:35
 */
@Component
public class AliyunOssManger {

	@Value("${oss.endpoint}")
	private String ossEndpoint;

	@Value("${oss.accessKeyId}")
	private String ossAccessKeyId;

	@Value("${oss.accessKeySecret}")
	private String ossAccessKeySecret;

	@Value("${oss.expiration}")
	private Long expiration;

	@Value("${oss.style}")
	private String style;

	@Value("${oss.bucket}")
	private String bucketName;

	private static Logger logger = LoggerFactory.getLogger(AliyunOssManger.class);
	
	private OSSClient ossClient = null;
	
	public void deleteFile(String bucketName, String ossId) {
		OSSClient ossClient = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			ossClient.deleteObject(bucketName, ossId);
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}

	}

	public void deleteFiles(String bucketName, List<String> keys) {
		OSSClient ossClient = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}
	}

	public void createBucket(String bucketName) {
		OSSClient ossClient = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			if (ossClient.doesBucketExist(bucketName)) {

			} else {
				ossClient.createBucket(bucketName);
			}
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}
	}

	public boolean existsFile(String bucketName, String ossId) {
		OSSClient ossClient = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			if (ossClient.doesObjectExist(bucketName, ossId)) {
				return true;
			}

			return false;
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}
	}

	public String copyFile(String sourceBucketName, String sourceKey, String destinationBucketName,
			String destinationKey) {
		OSSClient ossClient = null;
		String link = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			// 如果源文件存在，则拷贝
			if (ossClient.doesObjectExist(sourceBucketName, sourceKey)) {
				ossClient.copyObject(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
				// 设置URL过期时间
				Date expirationTime = new Date(System.currentTimeMillis() + expiration);
				URL url = ossClient.generatePresignedUrl(destinationBucketName, destinationKey, expirationTime);
				link = url.toString();
			}

			return link;

		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}

	}

	public String putFile(String ossId, String bucketName, InputStream is) {

		OSSClient ossClient = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			ossClient.putObject(bucketName, ossId, is);

			Date expirationTime = new Date(System.currentTimeMillis() + expiration);

			return ossClient.generatePresignedUrl(bucketName, ossId, expirationTime).toString();
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String putFile(String ossId, InputStream is) {

		OSSClient ossClient = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			ossClient.putObject(bucketName, ossId, is);

			Date expirationTime = new Date(System.currentTimeMillis() + expiration);

			return ossClient.generatePresignedUrl(bucketName, ossId, expirationTime).toString();
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<String> getAllFile(String bucketName) {
		OSSClient ossClient = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			// 构造ListObjectsRequest请求
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName).withMaxKeys(1000);

			// 列出Object
			ObjectListing listing = ossClient.listObjects(listObjectsRequest);

			// 获取所有key
			List<String> keys = new ArrayList<>();
			for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
				keys.add(objectSummary.getKey());
			}

			return keys;
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}
	}

	public String compressFile(String bucketName, String ossId) {
		OSSClient ossClient = null;
		String link = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, ossId);

			Date expirationTime = new Date(System.currentTimeMillis() + expiration);
			request.setExpiration(expirationTime);
			request.setProcess(style);

			URL url = ossClient.generatePresignedUrl(request);
			link = url.toString();

			return link;

		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}
	}

	public boolean existsBucket(String bucketName) {
		OSSClient ossClient = null;

		try {
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);

			return ossClient.doesBucketExist(bucketName);
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		}
	}
	
	
	/**
	 * 初始化一个分片上传事件
	 * @param key
	 * @return
	 */
	public String initiateMultipartUpload(String key) {
		
		try { 
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);
			InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
			InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
			String uploadId = result.getUploadId();
			return uploadId; 
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		} 
	}
	
	
	/**
	 * 上传分片
	 * @param MultipartFile 
	 */
	public PartETag uploadPart(MultipartFile file,String key,String uploadId,int chunk) { 
		InputStream instream = null;
		PartETag partETage = null;
		try { 
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);
			instream = file.getInputStream();
			
			if(null == instream) {
				throw new RuntimeException("分片文件格式损坏");
			}
			UploadPartRequest uploadPartRequest = new UploadPartRequest();
			uploadPartRequest.setBucketName(bucketName);
			uploadPartRequest.setKey(key);
			uploadPartRequest.setUploadId(uploadId);
			uploadPartRequest.setInputStream(instream);
			uploadPartRequest.setPartSize(file.getSize());
			uploadPartRequest.setPartNumber(chunk);
			UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
			partETage = uploadPartResult.getPartETag(); 
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
			if( null != instream) {
				try {
					instream.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
		}
		return partETage;  
	}
	
	public String completeMultipartUpload(String key,String uploadId,List<PartETag> partETags) {
		URL url = null;
		try { 
			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);
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
			ossClient.completeMultipartUpload(completeMultipartUploadRequest);
			Date now = new Date(new Date().getTime() + expiration);
			url = ossClient.generatePresignedUrl(bucketName, key, now);
			return url.toString();
		} finally {
			if (ossClient != null) {
				ossClient.shutdown();
			}
		} 
	}
	
//	
//	public String CompleteMultipartUpload(String key,String uploadId) {
//
//		try {
//			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);
//			// 获取所有已上传分片
//			PartListing partListing = null;
//			ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, key, uploadId);
//			
//			List<PartETag> partETags = new ArrayList<PartETag>();
//			do {
//			    partListing = ossClient.listParts(listPartsRequest); 
//			    for (PartSummary part : partListing.getParts()) {
//			        // 分片号，上传时候指定
//			        part.getPartNumber();
//			        // 分片数据大小
//			        part.getSize();
//			        // Part的ETag
//			        part.getETag();
//			        // Part的最后修改上传
//			        part.getLastModified();
//			       
//			    }
//			    listPartsRequest.setPartNumberMarker(partListing.getNextPartNumberMarker());
//			} while (partListing.isTruncated()); 
//			
//			 ossClient.getClientConfiguration();
//			Collections.sort(partETags, new Comparator<PartETag>() {
//			    @Override
//			    public int compare(PartETag p1, PartETag p2) {
//			        return p1.getPartNumber() - p2.getPartNumber();
//			    }
//			});
//			CompleteMultipartUploadRequest completeMultipartUploadRequest = 
//			        new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
//			ossClient.completeMultipartUpload(completeMultipartUploadRequest);
//			
//		} finally {
//			if (ossClient != null) {
//				ossClient.shutdown();
//			}
//		}
//		return null;
//	}
//	
//	public String shardUpload(String key, MultipartFile file) {
//		OSSClient ossClient = null;
//
//		try {
//			ossClient = new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);
//			
//			// 创建一个可重用固定线程数的线程池。若同一时间线程数大于10，则多余线程会放入队列中依次执行
//			ExecutorService executorService = Executors.newFixedThreadPool(5);
//			
//			InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
//			InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
//			String uploadId = result.getUploadId();
//
//			// 设置分片大小，除最后一个分片外，其它分片要大于1MB
//			final long partSize = 1 * 1024 *  1024L;
//			
//			// 计算分块数目
//			long fileLength = file.getSize();
//			int partCount = (int) (fileLength / partSize);
//			if (fileLength % partSize != 0) {
//				partCount ++;
//			}
//			// 分块 号码的范围是1~10000。如果超出这个范围，OSS将返回InvalidArgument的错误码。
//			if (partCount > 10000) {
//				throw new RuntimeException("文件过大(分块大小不能超过10000)");
//			} else {
//				logger.info(file.getOriginalFilename() + "文件一共分了 " + partCount + " 块 ");
//			}
//			// 将分好的文件块加入到list集合中
//			for (int i = 0; i < partCount; i++) {
//				long startPos = i * partSize;
//				long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
//				// 线程执行。将分好的文件块加入到list集合中
//				executorService
//						.execute(new AliYunOSSRunnable(ossClient,file, startPos, curPartSize, i + 1, uploadId, key, bucketName));
//			}
//			// 关闭线程池（不关闭会导致内存泄漏）（线程不马上关闭），执行提交的任务，但不接受新任务。
//			executorService.shutdown();
//			// 如果关闭后所有任务都已完成，则返回 true。
//			while (!executorService.isTerminated()) {
//				try {
//					// 用于等待子线程结束，再继续执行下面的代码
//					executorService.awaitTermination(5, TimeUnit.SECONDS);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
////			// partETags(上传块的ETag与块编号（PartNumber）的组合) 如果校验与之前计算的分块大小不同，则抛出异常
////			logger.info(AliYunOSSRunnable.partETags.size() + " -----   " + partCount);
////			if (AliYunOSSRunnable.partETags.size() != partCount) {
////				throw new IllegalStateException("OSS分块大小与文件所计算的分块大小不一致");
////			} else {
////				logger.info("将要上传的文件名  " + key + "\n");
////			}
////			// 完成上传
////			AliYunOSSRunnable.completeMultipartUpload(ossClient, uploadId);
//			Date now = new Date(new Date().getTime() + expiration);
//			URL url = ossClient.generatePresignedUrl(bucketName, key, now);
//			return url.toString();
//		} finally {
//			if (ossClient != null) {
//				ossClient.shutdown();
//			}
//		}
//	}

}
