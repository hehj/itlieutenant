package com.itelephant.h5wap.action;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.itelephant.h5wap.business.OSSService;
import com.itelephant.h5wap.common.Common;
import com.itelephant.h5wap.common.ResponseMap;
import org.apache.log4j.Logger;


@RestController
public class OSSController extends BaseController {

	private static final long serialVersionUID = 4099279768501577329L;
	
	private static final Logger logger = Logger.getLogger(OSSController.class); 
	
	@Autowired
	private OSSService oSSService;

	@RequestMapping(value = "/oss", method = RequestMethod.GET)
	public Map<String, Object> list(String prefix) {
		return oSSService.list(prefix);
	}
	
	@RequestMapping(value = "/oss", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@RequestParam String key){
		return oSSService.delete(key);
	}

	@RequestMapping(value = "/oss", method = RequestMethod.POST)
	public Map<String, Object> put(HttpServletRequest request) {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			String path = multipartRequest.getSession().getServletContext().getRealPath(Common.SOURCE_DIR);
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			Set<Entry<String, MultipartFile>> set = fileMap.entrySet();
			for (Entry<String, MultipartFile> item : set) {
				MultipartFile file = item.getValue();
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				String fileName = UUID.randomUUID().toString() + "." + extension;
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					file.transferTo(targetFile);
					return oSSService.put(targetFile);
				} catch (Exception e) {
					logger.error(e);
					return ResponseMap.ErrorInstance().toMap();
				}
			}
		}
		
		return ResponseMap.NotfoundInstance("文件没有上传").toMap();

	}
}
