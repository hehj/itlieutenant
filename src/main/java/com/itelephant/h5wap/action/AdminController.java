package com.itelephant.h5wap.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itelephant.h5wap.business.ArticleService;
import com.itelephant.h5wap.entity.Article;

import net.sf.json.JSONObject;

@Controller
public class AdminController {
	
	@Autowired
	private ArticleService articleService;
	@Value("#{configProperties['site']}")
	private String site;

	@RequestMapping("/csa/editor")
	public String editor(Model model,Long id){
		
		if(null!=id){
			Article temp = articleService.whichOne(id);
			model.addAttribute("id", temp.getId());
			model.addAttribute("title", temp.getPostTitle());
			model.addAttribute("source", temp.getPostSource());
			model.addAttribute("keyword", temp.getPostKeywords());
			model.addAttribute("excerpt", temp.getPostExcerpt());
			model.addAttribute("content", temp.getPostContent());
		}

		return "/detail_editor";
	}
	
	@ResponseBody
	@RequestMapping("/csa/editor/post")
	public Map<String,String> editorPost(Article article){
	
		Map<String, String> result = new HashMap<String, String>();
		if(article==null || !"Master".equals(site) || !"Master".equals(article.getSaveCode())){
			result.put("status", "error");
			result.put("massges", "Server reject");
			return result;
		}

		articleService.merge(article);
		result.put("status", "success");
		result.put("massges", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/csa/editor/get")
	public String editorGet(Article article){
		
		if(article!=null && article.getId()!=null && "Master".equals(article.getSaveCode())){
			Article temp = articleService.whichOne(article.getId());
			JSONObject result = JSONObject.fromObject(temp);
			return result.toString();
		}
		
		return "{status:'error', massges:'Server reject'}";
	}
	

}
