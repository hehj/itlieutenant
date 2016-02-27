package com.itelephant.h5wap.business.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itelephant.h5wap.business.ArticleService;
import com.itelephant.h5wap.common.Page;
import com.itelephant.h5wap.dao.ArticleMapper;
import com.itelephant.h5wap.entity.Article;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleDao;
	
	public Article whichOne(Long id) {
		Article article =  articleDao.whichOneByPrimaryKey(id);
		article.setPostHits(article.getPostHits()+1);
		articleDao.updateByPrimaryKey(article);
		return article;
	}
	
	public void ilikeit(Long id){
		Article article =  articleDao.whichOneByPrimaryKey(id);
		article.setPostLike(article.getPostLike()+1);
		articleDao.updateByPrimaryKey(article);
	}

	public Page<Article> listMore(String query, Long id) {

		Page<Article> page = new Page<Article>();
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("query", query);
		args.put("id", id);
		page.setResultList(articleDao.listMore(args));
		return page;
	}
	
	public Page<Article> listByQuery(String query, Integer n, Integer offSet, String orderColumn) {
		
		int x = (n-1)*offSet,y = offSet;
		
		Page<Article> page = new Page<Article>();
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("q", query.trim());
		args.put("x", x);
		args.put("y", y);
		args.put("orderColumn", orderColumn);
		page.setResultList(articleDao.listByQuery(args));
		return page;
	}

	public Page<Article> listBylast(Integer n) {
		Page<Article> page = new Page<Article>();
		page.setResultList(articleDao.listBylast(n));
		return page;
	}

}
