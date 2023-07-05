package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.MeasureMapper;
import com.egov.namul.service.MeasureService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MeasureService")
public class MeasureServiceImpl extends EgovAbstractServiceImpl implements MeasureService {

	@Resource(name="MeasureMapper")
	private MeasureMapper measureDAO;

	@Override
	public List<Map<String, Object>> organize(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_org_seq(param);
	}

	@Override
	public List<Map<String, Object>> model(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_model_seq(param);
	}

	@Override
	public List<Map<String, Object>> safe(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_safe_seq(param);
	}

	@Override
	public int measure_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_cnt(param);
	}

	@Override
	public List<Map<String, Object>> measure_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_list(param);
	}

	@Override
	public List<Map<String, Object>> measure(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure(param);
	}

	@Override
	public List<Map<String, Object>> measure_detail(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_detail(param);
	}

	@Override
	public List<Map<String, Object>> measure_sale(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_sale(param);
	}

	@Override
	public void measure_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		measureDAO.insert_measure(param);
		
		measureDAO.delete_measure_detail(param);
		if(param.get("msd_seq") != null)measureDAO.insert_measure_detail(param);
		
		measureDAO.delete_measure_sale(param);
		if(param.get("mss_seq") != null) measureDAO.insert_measure_sale(param);
	}

	@Override
	public void measure_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.put("section", 1);
		measureDAO.delete_measure(param);
		
		param.put("section", 2);
		measureDAO.delete_measure(param);
		
		param.put("section", 3);
		measureDAO.delete_measure(param);
	}

	@Override
	public List<Map<String, Object>> measure_report(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_report(param);
	}

	@Override
	public int safety_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_safety_cnt(param);
	}

	@Override
	public List<Map<String, Object>> safety_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_safety_list(param);
	}

	@Override
	public List<Map<String, Object>> safety(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_safety(param);
	}

	@Override
	public List<Map<String, Object>> safety_detail(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_safety_detail(param);
	}

	@Override
	public void safety_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		measureDAO.insert_safety(param);
		
		measureDAO.delete_safety_detail(param);
		if(param.get("sd_seq") != null) measureDAO.insert_safety_detail(param);
	}

	@Override
	public void safety_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.put("section", 1);
		measureDAO.delete_safety(param);
		
		param.put("section", 2);
		measureDAO.delete_safety(param);
	}

	@Override
	public List<Map<String, Object>> measure_table(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_table(param);
	}
	
	@Override
	public List<Map<String, Object>> safety_list_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_safety_list_excel(param);
	}

	@Override
	public List<Map<String, Object>> safety_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_safety_excel(param);
	}

	@Override
	public List<Map<String, Object>> safety_detail_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.safety_detail_excel(param);
	}

	@Override
	public List<Map<String, Object>> measure_list_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_list_excel(param);
	}

	@Override
	public List<Map<String, Object>> measure_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_excel(param);
	}

	@Override
	public List<Map<String, Object>> measure_detail_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_detail_excel(param);
	}

	@Override
	public List<Map<String, Object>> measure_sale_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return measureDAO.select_measure_sale_excel(param);
	}
}
