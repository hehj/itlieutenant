package com.itelephant.h5wap.business;

import com.itelephant.h5wap.common.Page;
import com.itelephant.h5wap.entity.Article;

public interface ArticleService {
	/**
	 * 根据ID取得文章
	 * @param id
	 * @return
	 */
	public Article whichOne(Long id);
	/**
	 * 根据ID列表取得文章列表
	 * @param ids
	 * @return
	 */
	public Page<Article> listMore(String query, Long id);
	/**
	 * 根据关键字Key查询列表
	 * @param query
	 * @return
	 */
	public Page<Article> listByQuery(String query, Integer n, Integer offSet);
	
	/**
	 * 点赞
	 * @param id
	 */
	public void ilikeit(Long id);
	
	/**
	 * 最新的N篇文章
	 * @param num
	 * @return
	 */
	public Page<Article> listBylast(Integer n); 
	
	/**
	 * 新增后修改一篇文章
	 * @param article
	 */
	public void merge(Article article);
}
