package com.egov.namul.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.CarMapper;
import com.egov.namul.service.CarService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("CarService")
public class CarServiceImpl extends EgovAbstractServiceImpl implements CarService {
	
	@Resource(name="CarMapper")
	private CarMapper carDAO;

	//운행일지
	@Override
	public List<Map<String, Object>> drive_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_drive_list(param);
	}

	@Override
	public int drive_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_drive_cnt(param);
	}

	@Override
	public void drive_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		 carDAO.insert_drive(param);
		 
		 param.put("section", 3);
		 carDAO.delete_drive(param);
		 if(param.get("rh_seq") != null) carDAO.insert_drive_history(param);
		 
		 int total = carDAO.select_drive_total(param); //차량 주행거리 가져오기
		 param.put("drive_total", total);
		 
		 carDAO.update_vehicle_drive(param); //차량 주행거리 저장하기
	}

	@Override
	public List<Map<String, Object>> drive(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_drive(param);
	}

	@Override
	public List<Map<String, Object>> drive_history(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_drive_history(param);
	}

	@Override
	public List<Map<String, Object>> drive_report(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_drive_report(param);
	}

	@Override
	public void drive_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int seq = carDAO.select_vehicle_seq(param); //차량 시퀀스 가져오기
		param.put("ve_seq", seq);
		
		param.put("section", 1);
		carDAO.delete_drive(param);
		
		param.put("section", 2);
		carDAO.delete_drive(param);
		
		if(seq > 0) {
			int total = carDAO.select_drive_total(param); //차량 주행거리 가져오기
			param.put("drive_total", total);
			 
			carDAO.update_vehicle_drive(param); //차량 주행거리 저장하기
		}	
	}

	//사고일지
	@Override
	public List<Map<String, Object>> accident_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_accident_list(param);
	}	
	
	@Override
	public int accident_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_accident_cnt(param);
	}

	@Override
	public void accident_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		carDAO.insert_accident(param);
	}

	@Override
	public void accident_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		carDAO.delete_accident(param);		
	}

	@Override
	public List<Map<String, Object>> accident(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_accident(param);
	}	
	
	@Override
	public List<Map<String, Object>> accident_report(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_accident_report(param);
	}
	
	
	

	//차량관리
	@Override
	public List<Map<String, Object>> organize(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_organize_seq(param);
	}

	@Override
	public int vehicle_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_vehicle_cnt(param);
	}

	@Override
	public List<Map<String, Object>> vehicle_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_vehicle_list(param);
	}

	@Override
	public List<Map<String, Object>> vehicle(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_vehicle(param);
	}

	@Override
	public List<Map<String, Object>> vehicle_log(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_vehicle_log(param);
	}

	@Override
	public void vehicle_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		if(param.get("seq") != null && !"".equals(param.get("seq"))) { //수정인 경우
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

			param.put("section", 1); //사업단 변경
			int status = carDAO.select_vehicle_section(param);
			
			if(status == 2) { //변경사항이 있는 경우
				Map<String, Object> map = carDAO.select_vehicle_change(param);
				map.put("divide", 1);
				data.add(map);
			}
			
			param.put("section", 2); //작성자 변경
			status = carDAO.select_vehicle_section(param);
			
			if(status == 2) { //변경사항이 있는 경우
				Map<String, Object> map = carDAO.select_vehicle_change(param);
				map.put("divide", 2);
				data.add(map);
			}
			
			param.put("section", 3); //차량사용상태 변경
			status = carDAO.select_vehicle_section(param);
			
			if(status == 2) { //변경사항이 있는 경우
				Map<String, Object> map = carDAO.select_vehicle_change(param);
				map.put("divide", 3);
				data.add(map);
			}
			
			if(data.size() > 0) { //변동사항이 있는경우 로그 남기기
				param.put("add_log", data);
				param.put("section", 2);				
				carDAO.insert_vehicle_log(param);
			}
			
			if(param.get("vl_seq") != null) { //근거 수정
				param.put("section", 1);				
				carDAO.insert_vehicle_log(param);
			}
		}
		
		carDAO.insert_vehicle(param);
	}

	@Override
	public void vehicle_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		param.put("section", 1);				
		carDAO.delete_vehicle(param);
		
		param.put("section", 2);				
		carDAO.delete_vehicle(param);
	}

	@Override
	public List<Map<String, Object>> system_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_system_list(param);
	}

	@Override
	public List<Map<String, Object>> revenue_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int days = carDAO.select_days(param);
		param.put("days", days);
		
		return carDAO.select_revenue_list(param);
	}

	@Override
	public List<Map<String, Object>> revenue_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int days = carDAO.select_days(param);
		param.put("days", days);
		
		return carDAO.select_revenue_total(param);
	}

	@Override
	public List<Map<String, Object>> liter_chart(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int days = carDAO.select_days(param);
		param.put("days", days);
		
		return carDAO.select_liter_chart(param);
	}

	@Override
	public List<Map<String, Object>> day_chart(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int days = carDAO.select_days(param);
		param.put("days", days);
		
		return carDAO.select_day_chart(param);
	}
	
	@Override
	public List<Map<String, Object>> drive_list_excel(Map<String, Object> param) throws Exception {
		
		return carDAO.drive_list_excel(param);
	}
	
	@Override
	public List<Map<String, Object>> drive_detail_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.drive_detail_excel(param);
	}
	
	@Override
	public List<Map<String, Object>> drive_history_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.drive_history_excel(param);
	}

	@Override
	public List<Map<String, Object>> accident_list_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.accident_list_excel(param);
	}

	@Override
	public List<Map<String, Object>> accident_detail_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.accident_detail_excel(param);
	}

	@Override
	public List<Map<String, Object>> vehicle_list_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_vehicle_list_excel(param);
	}

	@Override
	public List<Map<String, Object>> vehicle_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_vehicle_excel(param);
	}

	@Override
	public List<Map<String, Object>> select_vehicle_log_excel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return carDAO.select_vehicle_log_excel(param);
	}
}
