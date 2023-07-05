package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.TestMapper;
import com.egov.namul.service.TestService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("TestService")
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestService {

	@Resource(name="TestMapper")
	private TestMapper testDAO;
	
	@Override
	public List<Map<String, Object>> select_password(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return testDAO.select_password(param);
	}

	@Override
	public void insert_password(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		testDAO.insert_password(param);
	}

}
