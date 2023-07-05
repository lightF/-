package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.OrganizeMapper;
import com.egov.namul.service.OrganizeService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("OrganizeService")
public class OrganizeServiceImpl extends EgovAbstractServiceImpl implements OrganizeService {
	
	@Resource(name="OrganizeMapper")
	private OrganizeMapper organizeDAO;

	@Override
	public List<Map<String, Object>> member_org(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return organizeDAO.select_member_org(param);
	}

	@Override
	public List<Map<String, Object>> organize_depth(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return organizeDAO.select_organize_depth(param);
	}

	@Override
	public int total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return organizeDAO.select_organize_cnt(param);
	}

	@Override
	public List<Map<String, Object>> list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return organizeDAO.select_organize_list(param);
	}

	@Override
	public List<Map<String, Object>> organize(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return organizeDAO.select_organize(param);
	}
	
	@Override
	public List<Map<String, Object>> organize_level(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return organizeDAO.select_organize_seq(param);
	}

	@Override
	public int member_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return organizeDAO.select_member_cnt(param);
	}

	@Override
	public List<Map<String, Object>> member_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return organizeDAO.select_member_list(param);
	}

	@Override
	public int edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int status = 1001;
		
		int level = organizeDAO.select_organize_level(param);	
		param.put("level", level);
		if(status == 1001) organizeDAO.insert_organize(param);
		
		return status;
	}

	@Override
	public int delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int status = 1001;	
		param.put("section", 1);
		int cnt = organizeDAO.select_member_cnt(param);
		
		if(cnt > 0) status = 2001;
		else organizeDAO.delete_organize(param);
		
		return status;
	}
	
	@Override
	public List<Map<String, Object>> select_organize_list_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return organizeDAO.select_organize_list_excel(param);
	}
}
