package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.CallMapper;
import com.egov.namul.service.CallService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("CallService")
public class CallServiceImpl extends EgovAbstractServiceImpl implements CallService {

	@Resource(name="CallMapper")
	private CallMapper callDAO;

	@Override
	public List<Map<String, Object>> organize(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_org_seq(param);
	}

	@Override
	public int work_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_work_cnt(param);
	}

	@Override
	public List<Map<String, Object>> work_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_work_list(param);
	}

	@Override
	public void work_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		callDAO.insert_work(param);
		
		callDAO.delete_work_detail(param);
		if(param.get("wkd_seq") != null) callDAO.insert_work_detail(param);
	}

	@Override
	public void work_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.put("section", 1);
		callDAO.delete_work(param);
		
		param.put("section", 2);
		callDAO.delete_work(param);
	}

	@Override
	public List<Map<String, Object>> work(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_work(param);
	}

	@Override
	public List<Map<String, Object>> work_detail(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_work_detail(param);
	}

	@Override
	public List<Map<String, Object>> work_report(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_work_report(param);
	}

	@Override
	public int overtime_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_overtime_cnt(param);
	}

	@Override
	public List<Map<String, Object>> overtime_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_overtime_list(param);
	}

	@Override
	public void overtime_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		callDAO.insert_overtime(param);
		
		callDAO.delete_overtime_detail(param);
		if(param.get("is_od_seq") != null) callDAO.insert_overtime_detail(param);
	}

	@Override
	public void overtime_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.put("section", 1);
		callDAO.delete_overtime(param);
		
		param.put("section", 2);
		callDAO.delete_overtime(param);
	}

	@Override
	public List<Map<String, Object>> overtime(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_overtime(param);
	}

	@Override
	public List<Map<String, Object>> overtime_detail(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_overtime_detail(param);
	}

	@Override
	public void status_update(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		callDAO.update_overtime_status(param);
	}

	@Override
	public List<Map<String, Object>> overtime_report(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_overtime_report(param);
	}

	@Override
	public int revenue_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_revenue_cnt(param);
	}

	@Override
	public List<Map<String, Object>> revenue_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_revenue_list(param);
	}

	@Override
	public List<Map<String, Object>> month_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_month_list(param);
	}

	@Override
	public List<Map<String, Object>> revenue_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_revenue_excel(param);
	}

	@Override
	public List<Map<String, Object>> overtime_report_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_overtime_report_excel(param);
	}
	
	@Override
	public List<Map<String, Object>> overtime_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_overtime_excel(param);
	}

	@Override
	public List<Map<String, Object>> overtime_detail_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_overtime_detail_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_work_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_work_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_work_detail_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return callDAO.select_work_detail_excel(param);
	}
}
