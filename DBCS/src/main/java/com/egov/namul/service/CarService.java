package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface CarService {
	List<Map<String, Object>> drive_list(Map<String, Object> param) throws Exception; //차량운행일지 리스트 개수
	
	int drive_total(Map<String, Object> param) throws Exception; //차량운행일지 리스트 개수

	void drive_edit(Map<String ,Object> param) throws Exception; //운행일지 추가/수정
	
	void drive_delete(Map<String ,Object> param) throws Exception; //운행기록 삭제
	
	List<Map<String, Object>> drive(Map<String ,Object> param) throws Exception; //운행일지 기본정보 가져오기
	
	List<Map<String, Object>> drive_history(Map<String ,Object> param) throws Exception; //운행일지 이전기록 가져오기
	
	List<Map<String, Object>> drive_report(Map<String ,Object> param) throws Exception; //운행일지 보고서

	List<Map<String, Object>> accident_list(Map<String, Object> param) throws Exception; //사고일지 리스트
	
	int accident_total(Map<String, Object> param) throws Exception; //사고일지 리스트 개수
	
	void accident_edit(Map<String ,Object> param) throws Exception; // 사고일지 추가/삭제
	
	void accident_delete(Map<String ,Object> param) throws Exception; //사고일지기록 삭제
	
	List<Map<String, Object>> accident(Map<String ,Object> param) throws Exception; // 사고기록 기본정보 가져오기
	
	List<Map<String, Object>> accident_report(Map<String ,Object> param) throws Exception; //사고일지 보고서

	
	List<Map<String, Object>> organize(Map<String, Object> param) throws Exception; //검색용 사업단 시퀀스 가져오기
	
	int vehicle_total(Map<String, Object> param) throws Exception; //차량관리 리스트 개수 가져오기
	
	List<Map<String, Object>> vehicle_list(Map<String, Object> param) throws Exception; //차량관리 리스트 가져오기
	
	List<Map<String, Object>> vehicle(Map<String, Object> param) throws Exception; //차량관리 기본정보 가져오기
	
	List<Map<String, Object>> vehicle_log(Map<String, Object> param) throws Exception; //차량관리 로그 리스트 가져오기
	
	void vehicle_edit(Map<String, Object> param) throws Exception; //차량관리 추가/수정
	
	void vehicle_delete(Map<String, Object> param) throws Exception; //차량관리 삭제
	
	List<Map<String, Object>> system_list(Map<String, Object> param) throws Exception; //설비 목록
	
	List<Map<String, Object>> revenue_list(Map<String, Object> param) throws Exception; //차량운행실적
	
	List<Map<String, Object>> revenue_total(Map<String, Object> param) throws Exception; //차량운행실적 합계
	
	List<Map<String, Object>> liter_chart(Map<String, Object> param) throws Exception; //L당 가동거리
	
	List<Map<String, Object>> day_chart(Map<String, Object> param) throws Exception; //일평균 이동거리
	
List<Map<String, Object>> drive_list_excel(Map<String, Object> param) throws Exception; //차량운행일지 리스트 엑셀
	
	List<Map<String, Object>> drive_detail_excel(Map<String ,Object> param) throws Exception; //차량운행일지 리스트 엑셀
	
	List<Map<String, Object>> drive_history_excel(Map<String, Object> param) throws Exception; //차량운행기록 리스트 엑셀
	
	List<Map<String, Object>> accident_list_excel(Map<String, Object> param) throws Exception; //사고일지 리스트 엑셀
	
	List<Map<String, Object>> accident_detail_excel(Map<String ,Object> param) throws Exception; //사고일지 팝업 엑셀
	
	List<Map<String, Object>> vehicle_list_excel(Map<String, Object> param) throws Exception; //차량관리 리스트 엑셀
	
	List<Map<String, Object>> vehicle_excel(Map<String ,Object> param) throws Exception; //차량관리 기본정보 엑셀
	
	List<Map<String, Object>> select_vehicle_log_excel(Map<String ,Object> param) throws Exception; //차량관리 기본정보 로그 가져오기 엑셀
}
