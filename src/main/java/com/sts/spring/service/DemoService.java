package com.sts.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sts.spring.bean.Demo;
import com.sts.spring.dao.DemoDao;
import com.sts.spring.dao.DemoRepository;

@Service
public class DemoService {
	
	@Resource
	private DemoRepository demoRepository;
	@Resource
	private DemoDao demoDao;
	
	public void save(Demo demo) {
		demoRepository.save(demo);
	}
	public Demo getDemoById(long id) {
		return demoDao.getDemoById(id);
	}
}
