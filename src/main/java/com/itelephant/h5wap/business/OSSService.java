package com.itelephant.h5wap.business;

import java.io.File;
import java.util.Map;

public interface OSSService {
	public Map<String, Object> list(String prefix);

	public Map<String, Object> delete(String key);

	public Map<String, Object> put(File file);
}
