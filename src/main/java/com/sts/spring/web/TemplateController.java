package com.sts.spring.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {
	
	@RequestMapping("/helloThymeleaf")
	public String helloThymeleaf(Map<String, Object> map ){
		
		map.put("hello", "Thymeleaft123");
		
		return "/HelloThymeleaf";
    }
}
