package com.egov.namul.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.FacilityMapper;
import com.egov.namul.service.FacilityService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("FacilityService")
public class FacilityServiceImpl extends EgovAbstractServiceImpl implements FacilityService {

	@Resource(name="FacilityMapper")
	private FacilityMapper facilityDAO;
	
	@Resource(name = "fileUploadProperty")
	private Properties fileUploadProperty;
	
	@Override
	public List<Map<String, Object>> standard_seq(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_standard_seq(param);
	}

	@Override
	public List<Map<String, Object>> organize_seq(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub		
		return facilityDAO.select_organize_seq(param);
	}

	@Override
	public List<Map<String, Object>> status(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_device_status(param);
	}

	@Override
	public int device_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_device_cnt(param);
	}

	@Override
	public List<Map<String, Object>> device_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_device_list(param);
	}

	@Override
	public void device_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		facilityDAO.insert_device(param); //기기 기본정보 추가
		
		if(param.get("seq") == null || "".equals(param.get("seq"))) param.put("section", 1); //신규등록인 경우
		else {
			param.put("section", 3); //
			facilityDAO.delete_device(param); //기기 이전기록 삭제
			param.put("section", 2); //수정인 경우
		}		
		
		if(param.get("dh_seq") != null) facilityDAO.insert_device_history(param); //기기 이전기록 추가
	}

	@Override
	public int device_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int status = facilityDAO.select_device_check(param);	
		
		if(status == 1001) {
			param.put("section", 1);
			facilityDAO.delete_device(param);	
			
			param.put("section", 2);
			facilityDAO.delete_device(param);	
		}
		
		return status;
	}

	@Override
	public List<Map<String, Object>> device(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_device(param);
	}

	@Override
	public List<Map<String, Object>> device_history(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_device_history(param);
	}
	
	@Override
	public List<Map<String, Object>> standard_parent(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_standard_parent(param);
	}

	@Override
	public int standard_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_standard_cnt(param);
	}

	@Override
	public List<Map<String, Object>> standard_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_standard_list(param);
	}

	@Override
	public int standard_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		if(param.get("seq") != null) {
			int pcode = facilityDAO.select_standard_pcode(param);
			param.put("ds_pcode", pcode);
		}
		
		int status = facilityDAO.select_standard_level(param);		
		if(status == 2001) return status; //6레벨인 경우
		
		status = facilityDAO.select_depth_cnt(param);
		if(status == 3001) return status; //99개인 경우
		
		param.put("status", status);
		facilityDAO.insert_standard(param);
		status = 1001;
		
		return status;
	}

	@Override
	public List<Map<String, Object>> standard(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_standard(param);
	}

	@Override
	public int standard_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub		
		List<Map<String, Object>> depth = facilityDAO.select_standard_depth(param); //하위레벨 표준명 시퀀스 가져오기
				
		if(depth.size() > 0) { 
			Set<Integer> set = new HashSet<Integer>();			
			String[] column = {"one", "two", "three", "four", "five", "six"};
			
			for(String col : column) {
				for(Map<String, Object> list : depth) {
					if(list.get(col) != null) set.add(Integer.parseInt(list.get(col).toString()));					
				}				
			}		
		
			param.put("depth_seq", set);
		}
		
		int status = facilityDAO.select_standard_status(param);		
		if(status == 1001) facilityDAO.delete_standard(param);
		
		return status;
	}

	@Override
	public int account_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_account_cnt(param);
	}

	@Override
	public List<Map<String, Object>> account_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_account_list(param);
	}

	@Override
	public void account_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		facilityDAO.insert_account(param);
	}

	@Override
	public List<Map<String, Object>> account(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_account(param);
	}

	@Override
	public int account_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.put("section", 1);
		int status = facilityDAO.select_account_table(param);
		
		if(status == 1001) {
			param.put("section", 2);
			status = facilityDAO.select_account_table(param);
		}
		
		if(status == 1001) facilityDAO.delete_account(param);
		
		return status;
	}

	@Override
	public int standard1_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_standard1_cnt(param);
	}

	@Override
	public int organize_cnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_organize_cnt(param);
	}

	@Override
	public List<Map<String, Object>> organize_table(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub	
		return facilityDAO.select_organize_table(param);
	}

	@Override
	public int history_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_history_cnt(param);
	}

	@Override
	public List<Map<String, Object>> history_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_history_table(param);
	}

	@Override
	public List<Map<String, Object>> history_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_history_excel(param);
	}
	
	@Override
	public List<Map<String, Object>> device_excel(Map<String ,Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_device_excel(param);
	}

	@Override
	public List<Map<String, Object>> device_history_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_device_history_excel(param);
	}

	@Override
	public List<Map<String, Object>> account_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return facilityDAO.select_account_excel(param);
	}
}
