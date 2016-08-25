package com.itelephant.h5wap.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itelephant.h5wap.business.ArticleService;
import com.itelephant.h5wap.common.Common;
import com.itelephant.h5wap.common.Page;
import com.itelephant.h5wap.entity.Article;

@Controller
public class IndexController {
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/")
	public String index(Model model){
		
		List<Article> javaResult = articleService.listByQuery("java",1,8).getResultList();
		List<Article> linuxResult = articleService.listByQuery("linux",1,8).getResultList();
		List<Article> htmlResult = articleService.listByQuery("html",1,8).getResultList();
		List<Article> dsResult = articleService.listByQuery("电商",1,8).getResultList();
		List<Article> wxResult = articleService.listByQuery("微信",1,8).getResultList();
		List<Article> beaconResult = articleService.listByQuery("旅游",1,8).getResultList();
		
		model.addAttribute("javaResult", javaResult);
		model.addAttribute("linuxResult", linuxResult);
		model.addAttribute("htmlResult", htmlResult);
		model.addAttribute("dsResult", dsResult);
		model.addAttribute("wxResult", wxResult);
		model.addAttribute("beaconResult", beaconResult);
		
		String key = "";
		Page<Article> page = articleService.listByQuery(key,1,20);
		model.addAttribute("list", page.getResultList());
		model.addAttribute("key", key);
		
		model.addAttribute("site", Common.SITE);
		return "/index";
	}
	
	@RequestMapping("octThirtyOne")
	public String octThirtyOne(Model model){
		return "/151031/index";
	}
	

}
