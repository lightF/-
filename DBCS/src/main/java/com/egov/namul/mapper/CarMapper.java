package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CarMapper")
public interface CarMapper {
List<Map<String, Object>> select_drive_list(Map<String, Object> param) throws Exception; //운행일지 리스트
	
	int select_drive_cnt(Map<String, Object> param) throws Exception; //운행일지 리스트 개수 가져오기
	
	void insert_drive(Map<String, Object> param) throws Exception; // 운행일지 추가/수정
	
	void insert_drive_history(Map<String, Object> param) throws Exception; // 운행기록 추가/수정
	
	int select_vehicle_seq(Map<String, Object> param) throws Exception; // 운행일지에서 차량 시퀀스 가져오기
	
	int select_drive_total(Map<String, Object> param) throws Exception; // 차량 누적 주행거리 합계 가져오기
	
	void update_vehicle_drive(Map<String, Object> param) throws Exception; // 차량 누적 주행거리 저장하기

	void delete_drive(Map<String, Object> param) throws Exception; //운행일지 삭제

	List<Map<String, Object>> select_drive_report(Map<String, Object> param) throws Exception; // 차량 운행일지 보고서 가져오기
	
	List<Map<String, Object>> select_drive(Map<String ,Object> param) throws Exception; //기기 기본정보 가져오기
	
	List<Map<String, Object>> select_drive_history(Map<String ,Object> param) throws Exception; //기기 이전기록 가져오기
	
	List<Map<String, Object>> select_accident_list(Map<String, Object> param) throws Exception; // 사고일지 리스트
	
	int select_accident_cnt(Map<String, Object> param) throws Exception; //사고일지 리스트 개수 가져오기
	
	void insert_accident(Map<String, Object> param) throws Exception; // 사고일지 추가/수정
	
	void delete_accident(Map<String, Object> param) throws Exception; //사고일지 삭제
	
	List<Map<String, Object>> select_accident(Map<String ,Object> param) throws Exception; //사고일지 기본정보 가져오기
		
	List<Map<String, Object>> select_accident_report(Map<String ,Object> param) throws Exception; //사고일지 보고서 가져오기
	

	
	
	List<Map<String, Object>> select_organize_seq(Map<String, Object> param) throws Exception; //검색용 사업단 시퀀스 가져오기
	
	int select_vehicle_cnt(Map<String, Object> param) throws Exception; //차량관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_vehicle_list(Map<String, Object> param) throws Exception; //차량관리 리스트 가져오기
	
	List<Map<String, Object>> select_vehicle(Map<String, Object> param) throws Exception; //차량관리 기본정보 가져오기
	
	List<Map<String, Object>> select_vehicle_log(Map<String, Object> param) throws Exception; //차량관리 로그 가져오기
	
	void insert_vehicle(Map<String, Object> param) throws Exception; //차량관리 추가/수정
	
	int select_vehicle_section(Map<String, Object> param) throws Exception; //차량관리 변동사항 구분
	
	Map<String, Object> select_vehicle_change(Map<String, Object> param) throws Exception; //차량관리 변동사항
	
	void insert_vehicle_log(Map<String, Object> param) throws Exception; //차량관리 변동사항 추가/수정
	
	void delete_vehicle(Map<String, Object> param) throws Exception; //차량관리 삭제
	
	int select_days(Map<String, Object> param) throws Exception; //일수 가져오기
	
	List<Map<String, Object>> select_system_list(Map<String, Object> param) throws Exception; //설비 목록 가져오기
	
	List<Map<String, Object>> select_revenue_list(Map<String, Object> param) throws Exception; //차량운행실적
	
	List<Map<String, Object>> select_revenue_total(Map<String, Object> param) throws Exception; //차량운행실적 합계
	
	List<Map<String, Object>> select_liter_chart(Map<String, Object> param) throws Exception; //L당 가동거리
	
	List<Map<String, Object>> select_day_chart(Map<String, Object> param) throws Exception; //일평균 이동거리
	
List<Map<String, Object>> drive_list_excel(Map<String, Object> param) throws Exception; //운행일지 리스트 엑셀
	
	List<Map<String, Object>> drive_detail_excel(Map<String ,Object> param) throws Exception;//운행일지 팝업 엑셀
	
	List<Map<String, Object>> drive_history_excel(Map<String, Object> param) throws Exception;//운행일지기록  엑셀
	
	List<Map<String, Object>> accident_list_excel(Map<String, Object> param) throws Exception; //사고일지 리스트 엑셀
	
	List<Map<String, Object>> accident_detail_excel(Map<String ,Object> param) throws Exception;//사고일지 팝업 엑셀
	
	List<Map<String, Object>> select_vehicle_list_excel(Map<String, Object> param) throws Exception; //차량관리 리스트 엑셀
	
	List<Map<String, Object>> select_vehicle_excel(Map<String ,Object> param) throws Exception; //차량관리 기본정보 엑셀
	
	List<Map<String, Object>> select_vehicle_log_excel(Map<String ,Object> param) throws Exception; //차량관리 기본정보 로그 가져오기 엑셀
}
