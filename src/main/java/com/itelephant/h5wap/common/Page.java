package com.itelephant.h5wap.common;

import java.util.List;

public class Page<T> {

	private List<T> resultList;
	private int count;

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
