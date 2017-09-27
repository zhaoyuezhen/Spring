package com.sts.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ActionController {
	
	@RequestMapping("/getPage1")
	public ModelAndView page1() {
		/*ModelAndView mav = new ModelAndView("/page1");*/
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/page1");
		mav.addObject("content","123");
		return mav;
	}
	
	@RequestMapping("/getPage2")
	public String page2(Model model) {
		model.addAttribute("content","456");
		return "/page1";
	}
	
}
