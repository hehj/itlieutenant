package com.itelephant.h5wap.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itelephant.h5wap.business.ArticleService;
import com.itelephant.h5wap.common.Common;
import com.itelephant.h5wap.common.Page;
import com.itelephant.h5wap.entity.Article;

@Controller
public class ArticleController {
		@Autowired
		private ArticleService articleService;
		
		@RequestMapping("/article/ilikeit/{id}")
		@ResponseBody
		public String ilikeit(@PathVariable Long id){
			articleService.ilikeit(id);
			return "";
		}
		
		@RequestMapping("/article/{id}")
		public String village(@PathVariable Long id, Model model) {
			Article article = articleService.whichOne(id);

			model.addAttribute("id", article.getId());
			model.addAttribute("title", article.getPostTitle());
			model.addAttribute("keyword", article.getPostKeywords());
			model.addAttribute("content", article.getPostContent());
			model.addAttribute("source", article.getPostSource());
			model.addAttribute("hits", article.getPostHits());
			model.addAttribute("like", article.getPostLike());
			model.addAttribute("time", article.getPostDate().substring(0, 10));
			
			model.addAttribute("site", Common.SITE);
			
			String[] keys = article.getPostKeywords().split(" ");
			Page<Article> more = articleService.listMore(keys[0], id);
			model.addAttribute("more", more.getResultList());
			return "/detail";
		}
}
