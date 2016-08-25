package com.itelephant.h5wap.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Common {

	public static String OSS_URL;

	public static String SOURCE_DIR;

	public static String ACCESSKEY;

	public static String SECRETKEY;

	public static String ENDPOINT;

	public static String BUCKET;

	public static String SITE;
	
	/* httpCode */
	public static final String HTTPCODE = "httpCode";
	/* message */
	public static final String MESSAGE = "message";

	/**
	 * 200 OK - [GET]： 服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。 201 CREATED -
	 * [POST/PUT/PATCH]： 用户新建或修改数据成功。 202 Accepted - [*]： 表示一个请求已经进入后台排队（异步任务）
	 * 204 NO CONTENT - [DELETE]： 用户删除数据成功。 400 INVALID REQUEST -
	 * [POST/PUT/PATCH]： 用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。 401 Unauthorized
	 * - [*]： 表示用户没有权限（令牌、用户名、密码错误）。 403 Forbidden - [*]
	 * 表示用户得到授权（与401错误相对），但是访问是被禁止的。 404 NOT FOUND - [*]：
	 * 用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。 406 Not Acceptable - [GET]：
	 * 用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。 410 Gone -[GET]：
	 * 用户请求的资源被永久删除，且不会再得到的。 422 Unprocesable entity - [POST/PUT/PATCH]
	 * 当创建一个对象时，发生一个验证错误。 500 INTERNAL SERVER ERROR - [*]：
	 * 服务器发生错误，用户将无法判断发出的请求是否成功。
	 */

	/* 正常访问 */
	public static final String OK_200_MSG = "OK";

	/* 正常访问 */
	public static final String CREATED_201_MSG = "CREATED";

	/* 正常访问 */
	public static final String ACCEPTED_202_MSG = "ACCEPTED";

	/* 正常访问 */
	public static final String DELETE_204_MSG = "DELETED";

	/* 错误的请求 */
	public static final String BADREQUEST_400_MSG = "用户发出的请求错误";

	/* 未认证编码 */
	public static final String UNAUTHORIZED_401_MSG = "用户没有权限（令牌、用户名、密码错误）";

	/* 无权限 */
	public static final String FORBIDDEN_403_MSG = "用户得到授权，但权限不足";

	/* 页面未找到 */
	public static final String NOTFOUND_404_MSG = "用户发出的请求针对的是不存在的记录，服务器没有进行操作";

	/* 请求超时 */
	public static final String EQUEST_TOO_LONG_413_MSG = "请求的内容过长";

	/* 服务端访问出错 */
	public static final String INTERNAL_SERVER_ERROR_500_MSG = "服务器发生错误，无法判断发出的请求是否成功";


	@Value("${site}")
	public void setSITE(String sITE) {
		SITE = sITE;
	}
	
	@Value("${oss.url}")
	public void setOSS_URL(String oSS_URL) {
		OSS_URL = oSS_URL;
	}
	
	@Value("${sourceDir}")
	public void setSOURCE_DIR(String sOURCE_DIR) {
		SOURCE_DIR = sOURCE_DIR;
	}

	@Value("${aliyun.accesskey}")
	public void setACCESSKEY(String aCCESSKEY) {
		ACCESSKEY = aCCESSKEY;
	}

	@Value("${aliyun.secretKey}")
	public void setSECRETKEY(String sECRETKEY) {
		SECRETKEY = sECRETKEY;
	}

	@Value("${oss.endpoin}")
	public void setENDPOINT(String eNDPOINT) {
		ENDPOINT = eNDPOINT;
	}

	@Value("${oss.bucketName}")
	public void setBUCKET(String bUCKET) {
		BUCKET = bUCKET;
	}

	
}
