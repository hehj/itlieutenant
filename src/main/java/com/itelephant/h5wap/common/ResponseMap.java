package com.itelephant.h5wap.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

/**
 * 通用的RestController返回Map
 * 
 * @Description:
 * @Copyright: 福建卓智网络科技有限公司 (c)2016
 * @Created Date : 2016年7月14日
 * @author hehangjie
 * @vesion 1.0
 */
public class ResponseMap {

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

	private Map<String, Object> MAP = null;
	private Object MAP_INNER_RESULT = null;

	public ResponseMap(int httpCode, String message) {
		MAP = new HashMap<>();
		MAP.put(Common.HTTPCODE, httpCode);
		MAP.put(Common.MESSAGE, message);
	}

	public void setMessage(String message) {
		MAP.put(Common.MESSAGE, message);
	}

	public void setHttpCode(String httpCode) {
		MAP.put(Common.HTTPCODE, httpCode);
	}

	/**
	 * @Description: 成功的ResponseMap
	 * @Create: 2016年7月14日 下午2:15:25
	 * @author hehangjie
	 * @update logs
	 * @return
	 * @return ResponseMap
	 */
	public static ResponseMap SuccessInstance() {
		return new ResponseMap(HttpStatus.SC_OK, Common.OK_200_MSG);
	}

	public static ResponseMap CreatedInstance() {
		return new ResponseMap(HttpStatus.SC_CREATED, Common.OK_200_MSG);
	}

	/**
	 * @Description: 失败的ResponseMap
	 * @Create: 2016年7月14日 下午2:15:25
	 * @author hehangjie
	 * @update logs
	 * @return
	 * @return ResponseMap
	 */
	public static ResponseMap ErrorInstance() {
		return new ResponseMap(HttpStatus.SC_INTERNAL_SERVER_ERROR, Common.INTERNAL_SERVER_ERROR_500_MSG);
	}

	/**
	 * @Description: 未认证的ResponseMap，一般是SignFilter拦截了错误的签名
	 * @Create: 2016年7月15日 下午2:03:56
	 * @author hehangjie
	 * @update logs
	 * @return
	 * @return ResponseMap
	 */
	public static ResponseMap UnauthorizedInstance() {
		return new ResponseMap(HttpStatus.SC_UNAUTHORIZED, Common.UNAUTHORIZED_401_MSG);
	}

	/**
	 * @Description: 无权限，类似于黄晓明去删除宋仲基的微博，这种事是不被允许的
	 * @Create: 2016年7月15日 下午2:39:38
	 * @author hehangjie
	 * @update logs
	 * @return
	 * @return ResponseMap
	 */
	public static ResponseMap ForbiddenInstance() {
		return new ResponseMap(HttpStatus.SC_FORBIDDEN, Common.FORBIDDEN_403_MSG);
	}

	/**
	 * @Description: 参数错误的ResponseMap，比如邮箱和手机为二选一的参数，而前端二者都没有传
	 * @Create: 2016年7月15日 下午2:17:11
	 * @author hehangjie
	 * @update logs
	 * @return
	 * @return ResponseMap
	 */
	public static ResponseMap BadRequestInstance() {
		return new ResponseMap(HttpStatus.SC_BAD_REQUEST, Common.BADREQUEST_400_MSG);
	}

	/**
	 * @Description: 对象不存在的ResponseMap，比如传入了一个id，但查出此对象
	 * @Create: 2016年7月15日 下午2:17:11
	 * @author hehangjie
	 * @update logs
	 * @return
	 * @return ResponseMap
	 */
	public static ResponseMap NotfoundInstance(String msg) {
		return new ResponseMap(HttpStatus.SC_NOT_FOUND, msg);
	}

	/**
	 * @Description: 长度超出了限制
	 * @Create: 2016年7月15日 下午2:17:11
	 * @author hehangjie
	 * @update logs
	 * @return
	 * @return ResponseMap
	 */
	public static ResponseMap TooLongInstance(String msg) {
		return new ResponseMap(HttpStatus.SC_REQUEST_TOO_LONG, msg);
	}

	/**
	 * @Description: 转成Map
	 * @Create: 2016年7月14日 下午2:19:00
	 * @author hehangjie
	 * @update logs
	 * @return
	 * @return Map<String,Object>
	 */
	public Map<String, Object> toMap() {
		if (MAP_INNER_RESULT != null) {
			MAP.put("result", MAP_INNER_RESULT);
		}

		return MAP;
	}

	/**
	 * @Description: 把Key put到result下面
	 * @Create: 2016年7月20日 下午5:03:00
	 * @author HeHangjie
	 * @update logs
	 * @param key
	 * @param value
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void putResultValue(Object key, Object value) {
		if (MAP_INNER_RESULT == null) {
			MAP_INNER_RESULT = new HashMap<>();
		}

		if (MAP_INNER_RESULT instanceof Map) {
			((Map<Object, Object>) MAP_INNER_RESULT).put(key, value);
		} else {
			MAP.put("result", value);
		}

	}

	/**
	 * @Description: put result对象
	 * @Create: 2016年7月20日 下午5:04:27
	 * @author HeHangjie
	 * @update logs
	 * @param map
	 * @return void
	 */
	public void putResult(Object map) {
		MAP_INNER_RESULT = map;
	}

	/**
	 * @Description: 是否成功
	 * @Create: 2016年7月21日 下午5:29:52
	 * @author HeHangjie
	 * @update logs
	 * @return
	 * @return boolean
	 */
	public static boolean check(Map<String,Object> map) {
		try {
			int status = Integer.parseInt(map.get(Common.HTTPCODE).toString());

			if (status == 200 || status == 201 || status == 202 || status == 204) {
				return true;
			}

		} catch (NullPointerException ex) {
			return false;
		} catch (NumberFormatException ex) {
			return false;
		}

		return false;
	}

}
