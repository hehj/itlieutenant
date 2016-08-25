package com.itelephant.h5wap.business.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aliyun.oss.model.OSSObjectSummary;
import com.itelephant.h5wap.business.OSSService;
import com.itelephant.h5wap.common.Common;
import com.itelephant.h5wap.common.ResponseMap;
import com.itelephant.h5wap.utils.AliOSSUtils;

@Service
public class OSSServiceImpl implements OSSService {

	@Override
	public Map<String, Object> list(String prefix) {
		ResponseMap resp = ResponseMap.SuccessInstance();
		AliOSSUtils ossUtils = AliOSSUtils.getInstance(Common.ENDPOINT, Common.ACCESSKEY, Common.SECRETKEY);
		List<OSSObjectSummary> result = ossUtils.listObjects(Common.BUCKET, prefix);
		resp.putResultValue("result", result);
		return resp.toMap();
	}

	@Override
	public Map<String, Object> delete(String key) {
		ResponseMap resp = ResponseMap.SuccessInstance();
		resp.putResultValue("result", key);
		AliOSSUtils ossUtils = AliOSSUtils.getInstance(Common.ENDPOINT, Common.ACCESSKEY, Common.SECRETKEY);
		if(!ossUtils.doesObjectExist(Common.BUCKET, key)){
			 resp = ResponseMap.NotfoundInstance("文件不存在！");
			 return resp.toMap();
		}
		ossUtils.deleteObject(Common.BUCKET, key);
		return resp.toMap();
	}

	@Override
	public Map<String, Object> put(File targetFile) {
		ResponseMap resp = ResponseMap.SuccessInstance();
		AliOSSUtils ossUtils = AliOSSUtils.getInstance(Common.ENDPOINT, Common.ACCESSKEY, Common.SECRETKEY);
		ossUtils.putObject(targetFile, targetFile.getName(), Common.BUCKET);
		resp.putResultValue("key", targetFile.getName());
		return resp.toMap();
	}

}
