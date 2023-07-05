package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.BreakdownMapper;
import com.egov.namul.service.BreakdownService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("BreakdownService")
public class BreakdownServiceImpl extends EgovAbstractServiceImpl implements BreakdownService {

	@Resource(name="BreakdownMapper")
	private BreakdownMapper breakdownDAO;

	@Override
	public List<Map<String, Object>> organize(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_organize_seq(param);
	}

	@Override
	public List<Map<String, Object>> system(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_system_seq(param);
	}

	@Override
	public List<Map<String, Object>> budget(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_budget_seq(param);
	}

	@Override
	public int breakdown_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_cnt(param);
	}

	@Override
	public List<Map<String, Object>> breakdown_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_list(param);
	}

	@Override
	public void breakdown_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		breakdownDAO.insert_breakdown(param); //고장접수 기본 정보
		
		breakdownDAO.delete_breakdown_worker(param); //작업자 삭제
		if(param.get("bw_seq") != null) breakdownDAO.insert_breakdown_worker(param); //작업자 추가/수정
		
		breakdownDAO.delete_breakdown_compose(param); //구성부 삭제
		if(param.get("bc_seq") != null) breakdownDAO.insert_breakdown_compose(param); //구성부 추가/수정
	}

	@Override
	public void storage_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		breakdownDAO.delete_breakdown_storage(param); //저장품 삭제
		if(param.get("bs_seq1") != null) breakdownDAO.insert_breakdown_storage(param); //저장품 추가/수정
		
		breakdownDAO.delete_breakdown_spare(param); //예비품 삭제
		if(param.get("bs_seq2") != null) breakdownDAO.insert_breakdown_spare(param); //예비품 추가/수정
	}

	@Override
	public List<Map<String, Object>> breakdown(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown(param);
	}

	@Override
	public List<Map<String, Object>> breakdown_worker(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_worker(param);
	}

	@Override
	public List<Map<String, Object>> breakdown_compose(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_compose(param);
	}

	@Override
	public List<Map<String, Object>> breakdown_storage(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_storage(param);
	}

	@Override
	public List<Map<String, Object>> breakdown_spare(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_spare(param);
	}

	@Override
	public int breakdown_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int status = breakdownDAO.select_breakdown_status(param);
		
		if(status == 1001) {
			param.put("section", 1);
			breakdownDAO.delete_breakdown(param);
			
			param.put("section", 2);
			breakdownDAO.delete_breakdown(param);
			
			param.put("section", 3);
			breakdownDAO.delete_breakdown(param);
			
			param.put("section", 4);
			breakdownDAO.delete_breakdown(param);
			
			param.put("section", 5);
			breakdownDAO.delete_breakdown(param);
		}
		
		return status;
	}

	@Override
	public int part_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_part_cnt(param);
	}

	@Override
	public List<Map<String, Object>> part_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_part_list(param);
	}

	@Override
	public int action_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_action_cnt(param);
	}

	@Override
	public List<Map<String, Object>> action_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_action_list(param);
	}

	@Override
	public List<Map<String, Object>> action_count(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_action_count(param);
	}

	@Override
	public void action_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		breakdownDAO.insert_action(param);
	}

	@Override
	public void action_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		breakdownDAO.delete_action(param);
	}

	@Override
	public List<Map<String, Object>> action(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_action(param);
	}

	@Override
	public List<Map<String, Object>> action_date(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_action_date(param);
	}

	@Override
	public List<Map<String, Object>> action_report(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_action_report(param);
	}

	@Override
	public int shift_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_shift_cnt(param);
	}

	@Override
	public List<Map<String, Object>> shift_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_shift_list(param);
	}

	@Override
	public void shift_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		breakdownDAO.insert_shift(param); //당직일지 기본 정보
		
		breakdownDAO.delete_worker(param); //근무자 삭제
		if(param.get("wk_seq") != null) breakdownDAO.insert_worker(param); //근무자 추가/수정
		
		breakdownDAO.delete_contact(param); //지시/연락사항 삭제
		if(param.get("ct_seq") != null) breakdownDAO.insert_contact(param); //지시/연락사항 추가/수정
		
		breakdownDAO.delete_report(param); //보고사항 삭제
		if(param.get("rp_seq") != null) breakdownDAO.insert_report(param); //보고사항 추가/수정
	}

	@Override
	public List<Map<String, Object>> shift(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_shift(param);
	}

	@Override
	public List<Map<String, Object>> worker(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_worker(param);
	}

	@Override
	public List<Map<String, Object>> contact(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_contact(param);
	}

	@Override
	public List<Map<String, Object>> report(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_report(param);
	}

	@Override
	public void shift_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.put("section", 1);
		breakdownDAO.delete_shift(param);
		
		param.put("section", 2);
		breakdownDAO.delete_shift(param);
		
		param.put("section", 3);
		breakdownDAO.delete_shift(param);
		
		param.put("section", 4);
		breakdownDAO.delete_shift(param);
	}

	@Override
	public List<Map<String, Object>> shift_report(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_shift_report(param);
	}

	@Override
	public List<Map<String, Object>> system_table(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int section = Integer.parseInt(param.get("section").toString());
		
		if(section > 1) {
			int days = breakdownDAO.select_days(param);
			param.put("days", days);
		}		
		
		return breakdownDAO.select_system_table(param);
	}
	
	@Override
	public List<Map<String, Object>> select_breakdown_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_breakdown_worker_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_worker_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_breakdown_compose_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_compose_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_breakdown_storage_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_storage_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_breakdown_spare_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_breakdown_spare_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_action_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_action_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_action_date_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_action_date_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_shift_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_shift_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_worker_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_worker_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_contact_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_contact_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_report_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return breakdownDAO.select_report_excel(param);
	}		
}
