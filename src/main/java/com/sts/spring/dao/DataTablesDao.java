package com.sts.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sts.spring.bean.DataTables;


@Repository
public class DataTablesDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public List<DataTables> getDataTablesList(){
		String strSql = " select * from data_tables";
		RowMapper<DataTables> rowMapper = new BeanPropertyRowMapper<DataTables>(DataTables.class);
		return jdbcTemplate.query(strSql, rowMapper);
	}
}
