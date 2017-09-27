package com.sts.spring.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.swing.plaf.multi.MultiDesktopPaneUI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.sts.spring.bean.Demo;
import com.sts.spring.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {
	@Resource
	private DemoService demoService;

	@RequestMapping("/save")
	public String save() {
		Demo d = new Demo();
		d.setName("Bngel");
		demoService.save(d);// 保存数据.
		return "ok.DemoController.save";
	}
	
	@RequestMapping("/zeroException")
	public int zeroException(){
		return 100/0;
	}
	@RequestMapping("/getDemoById")
	public Demo getDemoById(long id) {
		return demoService.getDemoById(id);
	}
	@RequestMapping("/hello")
	public String hello(){
		return "Hello world!";
    }
	
	
}
