package com.sts.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sts.spring.bean.DataTables;
import com.sts.spring.dao.DataTablesDao;

@Service
public class DataTablesService {
	@Resource
	DataTablesDao dataTablesDao;

	public List<DataTables> getDataTablesList() {
		return dataTablesDao.getDataTablesList();
	}
}
