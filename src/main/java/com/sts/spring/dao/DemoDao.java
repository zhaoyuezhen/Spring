package com.sts.spring.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sts.spring.bean.Demo;

@Repository
public class DemoDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public Demo getDemoById(long id) {
		String strSql = " select * from Demo where id = ? ";
		RowMapper<Demo> rowMapper = new BeanPropertyRowMapper<Demo>(Demo.class);
		return jdbcTemplate.queryForObject(strSql, rowMapper,id);
	}
}
