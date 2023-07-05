package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.AuthMapper;
import com.egov.namul.service.AuthService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("AuthService")
public class AuthServiceImpl extends EgovAbstractServiceImpl implements AuthService {

	@Resource(name="AuthMapper")
	private AuthMapper authDAO;

	@Override
	public List<Map<String, Object>> auth_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return authDAO.select_auth_list(param);
	}

	@Override
	public void auth_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		authDAO.insert_auth(param);
	}

	@Override
	public int select_auth_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return authDAO.select_auth_edit(param);
	}
}
