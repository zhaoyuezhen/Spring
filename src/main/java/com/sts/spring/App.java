package com.sts.spring;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.spi.ValidationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.sts.spring.scheduling.SchedulingConfig;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ServletComponentScan
public class App {
	@Resource
	private SchedulingConfig schedulingConfig;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		/*List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < 51; i++) {
			list.add(i);
		}
		paging(list, 10);*/
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void paging(List list, Integer pageSize) {
		int totalCount = list.size();
		int pageCount = 0;
		int m = totalCount % pageSize;

		if (m > 0) {
			pageCount = totalCount / pageSize + 1;
		} else {
			pageCount = totalCount / pageSize;
		}
		
		List<Integer> subList = new ArrayList<>();
		for (int i = 1; i <= pageCount; i++) {
			if (m == 0) {
				subList = list.subList((i - 1) * pageSize, i * pageSize);
				System.out.println(subList);
			} else {
				if (i == pageCount) {
					subList = list.subList((i - 1) * pageSize, totalCount);
					System.out.println(subList);
				} else {
					subList = list.subList((i - 1) * pageSize, i * pageSize);
					System.out.println(subList);
				}
			}
		}
	}

}
