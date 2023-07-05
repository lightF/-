package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface FacilityService {
	List<Map<String, Object>> standard_seq(Map<String, Object> param) throws Exception; //표준명 시퀀스 가져오기
	
	List<Map<String, Object>> organize_seq(Map<String, Object> param) throws Exception; //팀(조직도 시퀀스) 가져오기
	
	List<Map<String, Object>> status(Map<String, Object> param) throws Exception; //이전 상태인 기기 시퀀스 가져오기
	
	int device_total(Map<String, Object> param) throws Exception; //기기 갯수 가져오기
	
	List<Map<String, Object>> device_list(Map<String ,Object> param) throws Exception; //기기 리스트 가져오기
	
	void device_edit(Map<String ,Object> param) throws Exception; //기기 관리 추가/수정

	int device_delete(Map<String ,Object> param) throws Exception; //기기 삭제
	
	List<Map<String, Object>> device(Map<String ,Object> param) throws Exception; //기기 기본정보 가져오기
	
	List<Map<String, Object>> device_history(Map<String ,Object> param) throws Exception; //기기 이전기록 가져오기
	
	List<Map<String, Object>> standard_parent(Map<String, Object> param) throws Exception; // 표준명 상위 레벨 시퀀스
	
	int standard_total(Map<String, Object> param) throws Exception; //표준명 갯수 가져오기
	
	List<Map<String, Object>> standard_list(Map<String, Object> param) throws Exception; //표준명 리스트 가져오기
	
	int standard_edit(Map<String, Object> param) throws Exception; //표준명 추가/수정
	
	List<Map<String, Object>> standard(Map<String, Object> param) throws Exception; //표준명 정보 가져오기
	
	int standard_delete(Map<String, Object> param) throws Exception; //표준명 삭제하기

	int account_total(Map<String, Object> param) throws Exception; //거래처 갯수 가져오기
	
	List<Map<String, Object>> account_list(Map<String, Object> param) throws Exception; //거래처 리스트 가져오기
	
	void account_edit(Map<String, Object> param) throws Exception; //거래처 추가/수정
	
	List<Map<String, Object>>account(Map<String, Object> param) throws Exception; //거래처 정보 가져오기
	
	int account_delete(Map<String, Object> param) throws Exception; //거래처 삭제하기
	
	int standard1_total(Map<String, Object> param) throws Exception; //기기(표준명1) 개수 가져오기
	
	int organize_cnt(Map<String, Object> param) throws Exception; //사업단 개수 가져오기
	
	List<Map<String, Object>> organize_table(Map<String, Object> param) throws Exception; //사업단설비현황
	
	int history_total(Map<String, Object> param) throws Exception; //이전/철거현황 갯수 가져오기
	
	List<Map<String, Object>> history_list(Map<String ,Object> param) throws Exception; //이전/철거현황 리스트 가져오기
	
	List<Map<String, Object>> history_excel(Map<String, Object> param) throws Exception; // 이전/철거현황 엑셀
	
List<Map<String, Object>> device_excel(Map<String ,Object> param) throws Exception; //기기 기본정보 엑셀
	
	List<Map<String, Object>> device_history_excel(Map<String, Object> param) throws Exception; //기기 이전기록 가져오기
	
	List<Map<String, Object>> account_excel(Map<String, Object> param) throws Exception; //거래처 상세 정보 엑셀
}
