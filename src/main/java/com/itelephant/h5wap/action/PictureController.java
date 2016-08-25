package com.itelephant.h5wap.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PictureController {
	
		
		@RequestMapping("/picture")
		public String picture(Model model) {
			
			return "/imagebox";
		}
}
