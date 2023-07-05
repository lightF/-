package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.PartMapper;
import com.egov.namul.service.PartService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("PartService")
public class PartServiceImpl extends EgovAbstractServiceImpl implements PartService {
	
	@Resource(name="PartMapper")
	private PartMapper partDAO;
	
	@Resource(name = "fileUploadProperty")
	private Properties fileUploadProperty;

	@Override
	public List<Map<String, Object>> organize(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_organize_seq(param);
	}

	@Override
	public List<Map<String, Object>> system(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_system_seq(param);
	}

	@Override
	public List<Map<String, Object>> budget(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_budget_seq(param);
	}

	@Override
	public List<Map<String, Object>> payment_seq(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_payment_seq(param);
	}

	@Override
	public List<Map<String, Object>> storage_org(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_storage_org(param);
	}

	@Override
	public int part_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_part_cnt(param);
	}

	@Override
	public List<Map<String, Object>> part_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_part_list(param);
	}

	@Override
	public void part_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		partDAO.insert_part(param);
		
		partDAO.delete_spec(param);
		if(param.get("sp_seq") != null) partDAO.insert_spec(param);
	}

	@Override
	public List<Map<String, Object>> part(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_part(param);
	}

	@Override
	public List<Map<String, Object>> spec(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_spec(param);
	}

	@Override
	public int part_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.put("section", 1);
		int status = partDAO.select_part_status(param);
		
		if(status == 1001) {
			param.put("section", 2);
			status = partDAO.select_part_status(param);
		}
		
		if(status == 1001) {
			param.put("section", 3);
			status = partDAO.select_part_status(param);
		}
		
		if(status == 1001) {
			param.put("section", 1);
			partDAO.delete_part(param);
			
			param.put("section", 2);
			partDAO.delete_part(param);
		}
		
		return status;
	}

	@Override
	public int payment_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_payment_cnt(param);
	}

	@Override
	public List<Map<String, Object>> payment_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_payment_list(param);
	}

	@Override
	public void payment_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		partDAO.insert_payment(param);
		param.put("section", 2);
		partDAO.delete_order_request(param);
		if(param.get("or_seq") != null) partDAO.insert_order_request(param);
	}

	@Override
	public List<Map<String, Object>> payment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_payment(param);
	}

	@Override
	public List<Map<String, Object>> order_request(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_order_request(param);
	}

	@Override
	public void payment_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> data = partDAO.select_order_seq(param);
		
		if(data.size() > 0) {
			param.put("order", data);
			param.put("section", 1);
			partDAO.delete_order_request(param);
		}
		
		param.put("section", 2);
		partDAO.delete_order_request(param);
		partDAO.delete_payment(param);		
	}

	@Override
	public int order_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_order_cnt(param);
	}

	@Override
	public List<Map<String, Object>> order_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_order_list(param);
	}

	@Override
	public List<Map<String, Object>> payment_create(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_payment_create(param);
	}

	@Override
	public int storage_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_storage_cnt(param);
	}

	@Override
	public List<Map<String, Object>> storage_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_storage_list(param);
	}

	@Override
	public void storage_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		partDAO.insert_storage(param);
		
		partDAO.delete_storage_group(param);
		if(param.get("sg_seq") != null) partDAO.insert_storage_group(param);
	}

	@Override
	public List<Map<String, Object>> storage(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_storage(param);
	}

	@Override
	public List<Map<String, Object>> storage_group(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_storage_group(param);
	}

	@Override
	public int storage_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int status = partDAO.select_storage_status(param);
		
		if(status == 1001) {
			param.put("section", 1);
			partDAO.delete_storage(param);
			
			param.put("section", 2);
			partDAO.delete_storage(param);
		}
		
		return status;
	}

	@Override
	public int storage_check(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub		
		return partDAO.select_storage_check(param);
	}

	@Override
	public List<Map<String, Object>> stock(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_stock_table(param);
	}

	@Override
	public List<Map<String, Object>> organize_group(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_organize(param);
	}

	@Override
	public List<Map<String, Object>> organize_depth(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_organize_depth(param);
	}	
	
	@Override
	public List<Map<String, Object>> part_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_part_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_spec_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_spec_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_payment_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_payment_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_order_request_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_order_request_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_storage_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_storage_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_storage_group_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return partDAO.select_storage_group_excel(param);
	}
}
