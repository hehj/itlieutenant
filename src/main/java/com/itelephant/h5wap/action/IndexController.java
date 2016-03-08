package com.itelephant.h5wap.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itelephant.h5wap.business.ArticleService;
import com.itelephant.h5wap.entity.Article;

@Controller
public class IndexController {
	
	@Autowired
	private ArticleService articleService;
	@Value("#{configProperties['site']}")
	private String site;

	@RequestMapping("/")
	public String index(Model model){
		
		List<Article> javaResult = articleService.listByQuery("java",1,5).getResultList();
		List<Article> linuxResult = articleService.listByQuery("linux",1,5).getResultList();
		List<Article> htmlResult = articleService.listByQuery("html",1,5).getResultList();
		List<Article> dsResult = articleService.listByQuery("business",1,5).getResultList();
		List<Article> wxResult = articleService.listByQuery("weixin",1,5).getResultList();
		List<Article> beaconResult = articleService.listByQuery("ibeacon",1,5).getResultList();
		
		model.addAttribute("javaResult", javaResult);
		model.addAttribute("linuxResult", linuxResult);
		model.addAttribute("htmlResult", htmlResult);
		model.addAttribute("dsResult", dsResult);
		model.addAttribute("wxResult", wxResult);
		model.addAttribute("beaconResult", beaconResult);
		
		model.addAttribute("site", site);
		return "/index";
	}
	
	@RequestMapping("octThirtyOne")
	public String octThirtyOne(Model model){
		return "/151031/index";
	}
	

}
