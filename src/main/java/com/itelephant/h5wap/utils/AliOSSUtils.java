package com.itelephant.h5wap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.util.StringUtils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * @Description: OSS工具类
 * @Copyright: 福建卓智网络科技有限公司 (c)2016
 * @Created Date : 2016年6月22日
 * @author hehangjie
 * @vesion 1.0
 */
public class AliOSSUtils {

	private static AliOSSUtils single = null;
	private OSSClient ossClient = null;

	public static AliOSSUtils getInstance(String endpoint, String accessKeyId, String accessKeySecret) {
		if (single == null) {
			single = new AliOSSUtils(endpoint, accessKeyId, accessKeySecret);
		}

		return single;
	}

	public AliOSSUtils(String endpoint, String accessKeyId, String accessKeySecret) {
		if (ossClient == null) {
			ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		}
	}

	/**
	 * @Description: 上传文件
	 * @Create: 2016年6月22日 下午3:21:12
	 * @author hehangjie
	 * @update logs
	 * @param file
	 * @param key
	 * @param bucketName
	 * @param contentType
	 * @return void
	 */
	public PutObjectResult putObject(File file, String key, String bucketName) {
		InputStream inStream = null;
		PutObjectResult result = null;
		try {
			inStream = new FileInputStream(file);

			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();

			meta.setContentType(contentType(key));

			// 必须设置ContentLength
			meta.setContentLength(file.length());
			result = ossClient.putObject(bucketName, key, inStream, meta);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			file.delete();
		}

		return result;
	}

	public void copyObject(String key, String bucketName, String destinationKey) {
		ossClient.copyObject(bucketName, key, bucketName, destinationKey);
	}

	/**
	 * @Description: 下载文件
	 * @Create: 2016年6月22日 下午3:21:39
	 * @author hehangjie
	 * @update logs
	 * @param buketName
	 * @param key
	 * @param targetPath
	 * @return void
	 */
	public void getObject(String bucketName, String key, String targetPath) {
		ossClient.getObject(new GetObjectRequest(bucketName, key), new File(targetPath));
	}

	/**
	 * @Description: 文件是否存在
	 * @Create: 2016年6月22日 下午3:30:45
	 * @author hehangjie
	 * @update logs
	 * @param bucketName
	 * @param key
	 * @return
	 * @return boolean
	 */
	public boolean doesObjectExist(String bucketName, String key) {
		return ossClient.doesObjectExist(bucketName, key);
	}
	
	/**
	 * @Description: 删除文件
	 * @Create: 2016年6月22日 下午3:30:45
	 * @author hehangjie
	 * @update logs
	 * @param bucketName
	 * @param key
	 * @return
	 * @return boolean
	 */
	public void deleteObject(String bucketName, String key) {
		ossClient.deleteObject(bucketName, key);
	}

	/**
	 * @Description: 返回以prefix为前辍的对象列表
	 * @Create: 2016年6月22日 下午3:30:45
	 * @author hehangjie
	 * @update logs
	 * @param bucketName
	 * @param key
	 * @return
	 * @return boolean
	 */
	public List<OSSObjectSummary> listObjects(String bucketName, String prefix) {
		if (StringUtils.isEmpty(prefix)) {
			return ossClient.listObjects(bucketName).getObjectSummaries();
		}
		return ossClient.listObjects(bucketName, prefix).getObjectSummaries();
	}

	/**
	 * @Description: 取得不同文件的contentType
	 * @Create: 2016年6月22日 下午3:30:45
	 * @author hehangjie
	 * @update logs
	 * @param bucketName
	 * @param key
	 * @return
	 * @return boolean
	 */
	public String contentType(String key) {
		if (key.endsWith(".BMP") || key.endsWith(".bmp")) {
			return "image/bmp";
		}
		if (key.endsWith(".GIF") || key.endsWith(".gif")) {
			return "image/gif";
		}
		if (key.endsWith(".JPEG") || key.endsWith(".jpeg") || key.endsWith(".JPG") || key.endsWith(".jpg")
				|| key.endsWith(".PNG") || key.endsWith(".png")) {
			return "image/jpeg";
		}
		if (key.endsWith(".HTML") || key.endsWith(".html")) {
			return "text/html";
		}
		if (key.endsWith(".TXT") || key.endsWith(".txt")) {
			return "text/plain";
		}
		if (key.endsWith(".VSD") || key.endsWith(".vsd")) {
			return "application/vnd.visio";
		}
		if (key.endsWith(".PPTX") || key.endsWith(".pptx") || key.endsWith(".PPT") || key.endsWith(".ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (key.endsWith(".DOCX") || key.endsWith(".docx") || key.endsWith(".DOC") || key.endsWith(".doc")) {
			return "application/msword";
		}
		if (key.endsWith(".XML") || key.endsWith(".xml")) {
			return "text/xml";
		}
		if (key.endsWith(".MP4") || key.endsWith(".mp4")) {
			return "video/mpeg4";
		}
		if (key.endsWith(".MP3") || key.endsWith(".mp3")) {
			return "audio/mp3";
		}
		return null;
	}
}
