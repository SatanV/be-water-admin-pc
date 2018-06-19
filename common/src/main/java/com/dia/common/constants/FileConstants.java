package com.dia.common.constants;

import java.util.HashMap;
import java.util.Map;

/**   
* @author: Dia
* @Description: 
* @version: v1.0.0
* @date: 2018年5月25日 上午9:42:22 
*/
public class FileConstants {
	/**
     * 异常信息统一头信息
     */
    public static final String EXCEPTION_HEAD = "错误信息";
    /**
     * 缓存键值
     */
    public static final Map<Class<?>, String> cacheKeyMap = new HashMap<>();
    /**
     * 保存文件所在路径的key，eg.FILE_MD5:1243jkalsjflkwaejklgjawe
     */
    public static final String FILE_MD5_KEY = "FILE_MD5:";
    /**
     * 保存上传文件的状态
     */
    public static final String FILE_UPLOAD_STATUS = "FILE_UPLOAD_STATUS";

}
