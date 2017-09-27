package com.sts.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.sts.spring.bean.Demo;

public interface DemoRepository extends CrudRepository<Demo, Long> {

}
