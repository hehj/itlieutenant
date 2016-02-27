package com.itelephant.h5wap.business.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeixinServiceImpl {
	
	@Value("#{configProperties['tencent.pay.appid.js']}")
	private String appid;
	@Value("#{configProperties['tencent.pay.secret.js']}")
	private String secret;
	
	public String getAppid() {
		return appid;
	}
	
	public String getSecret() {
		return secret;
	}
	
	 
}
