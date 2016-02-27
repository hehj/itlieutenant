package com.itelephant.h5wap.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
			
		@RequestMapping("/test")
		public String test(Model model) {
			return "/test/test";
		}
		
		@RequestMapping("/test/list")
		public String list(Model model) {
			return "/test/list";
		}
		
		@RequestMapping("/test/detail")
		public String detail(Model model) {
			return "/test/detail";
		}
		
		
}
