package com.itelephant.h5wap.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itelephant.h5wap.business.ArticleService;
import com.itelephant.h5wap.common.Page;
import com.itelephant.h5wap.entity.Article;

@Controller
public class ListController {
		@Autowired
		private ArticleService articleService;
		
		@RequestMapping("/list")
		public String village(HttpServletRequest request, Model model) {
			String key = request.getParameter("q");
			if(null==key){
				key = "";
			}
			Page<Article> page = articleService.listByQuery(key,1,20,"post_date");
			model.addAttribute("list", page.getResultList());
			model.addAttribute("key",key);
			return "/list";
		}
}
