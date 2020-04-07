package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.testDao;
import com.service.testService;

@Service
public class testServiceImple implements testService {
	@Autowired
	private testDao d;

	@Override
	@Transactional
	public String getStr() {
		System.out.println(d.getStr());

		return "";
	}
}
