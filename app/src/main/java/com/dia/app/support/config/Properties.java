package com.dia.app.support.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * 系统.properties配置文件 
 * 
 * @author myt
 * @createdate 2018-05-07 11:04
 * 
 * @UpdateUser:
 * @UpdateDate: 
 * @UpdateRemark:
 * 
 * @version 1.0
 *
 */
public class Properties {
    
    /** 系统运行模式 */
    @Value("${system.mode}")
    public String systemMode;
    
	/** 公钥 */
	@Value("${system.key.public}")
	public String publicKey;
	
	/** 私钥 */
	@Value("${system.key.private}")
	public String privateKey;

}
