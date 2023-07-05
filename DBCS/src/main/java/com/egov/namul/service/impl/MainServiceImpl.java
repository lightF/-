package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.MainMapper;
import com.egov.namul.service.MainService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService {

	@Resource(name="MainMapper")
	private MainMapper mainDAO;
	
	@Override
	public List<Map<String, Object>> menu(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.select_menu(param);
	}

	@Override
	public List<Map<String, Object>> bookmark(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.select_bookmark(param);
	}

	@Override
	public void bookmark_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.put("section", 2);
		mainDAO.insert_bookmark(param);
	}

	@Override
	public void bookmark_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		mainDAO.delete_bookmark(param);
		
		List<Map<String, Object>> data = mainDAO.select_bookmark(param);
		
		if(data.size() > 0) {
			param.put("menu", data);
			param.put("section", 1);
			mainDAO.insert_bookmark(param);
		}
	}

	@Override
	public List<Map<String, Object>> register_chart(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.select_register_chart(param);
	}

	@Override
	public List<Map<String, Object>> breakdown_chart(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.select_breakdown_chart(param);
	}

	@Override
	public List<Map<String, Object>> action_chart(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.select_action_chart(param);
	}

	@Override
	public List<Map<String, Object>> work(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.select_work(param);
	}

	@Override
	public List<Map<String, Object>> worker_chart(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.select_worker_chart(param);
	}

}
