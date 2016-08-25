package com.itelephant.h5wap.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 控制器基类
 * @author longweier
 *
 */
public class BaseController implements Serializable{
	
	private static final long serialVersionUID = 934922550784904536L;

	/**
	 * 获取远程客户端IP地址
	 * @return
	 */
	String getHost(){
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)localRequestAttributes;
		HttpServletRequest request = requestAttributes.getRequest();
		return request.getRemoteAddr();
	}
	
	HttpServletRequest getRequest(){
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)localRequestAttributes;
		return requestAttributes.getRequest();
	}
	
	String getApiVersion(){
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)localRequestAttributes;
		HttpServletRequest request =  requestAttributes.getRequest();
		String apiVersion = request.getHeader("apiVersion");
		if(apiVersion == null){
			apiVersion= "0.1.0";
		}
		
		return apiVersion;
	}
	
	String getToken(){
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)localRequestAttributes;
		HttpServletRequest request =  requestAttributes.getRequest();
		String token = request.getHeader("token");
		return token;
	}
	
	String getWebRootPath(){
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)localRequestAttributes;
		return requestAttributes.getRequest().getSession().getServletContext().getRealPath("/");
	}
}
