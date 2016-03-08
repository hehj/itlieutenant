package com.itelephant.h5wap.dao;

import java.util.List;
import java.util.Map;

import com.itelephant.h5wap.entity.Article;

public interface ArticleMapper {
	
	/**
	 * 根据ID取得文章
	 * @param id
	 * @return
	 */
	Article whichOneByPrimaryKey(Long id);
	/**
	 * 根据ID列表取得文章列表
	 * @param ids
	 * @return
	 */
	List<Article> listMore(Map<String,Object> args);
	/**
	 * 根据关键字取得文章列表
	 * key为""时，查出全部，按时间DESC
	 * @param key
	 * @return
	 */
	List<Article> listByQuery(Map<String,Object> args);
	/**
	 * 修改文章（点击率）
	 * @param article
	 * @return
	 */
	Integer updateByPrimaryKey(Article article);
	/**
	 * 取得最近d天里的文章数
	 * @param d
	 * @return
	 */
	Integer countNew(String d);
	/**
	 * 最新的N篇文章
	 * @param num
	 * @return
	 */
	List<Article> listBylast(Integer n);
	
	void insert(Article article);
	
	void update(Article article);
}
