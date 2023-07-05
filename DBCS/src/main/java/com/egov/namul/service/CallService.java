package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface CallService {
	List<Map<String, Object>> organize(Map<String, Object> param) throws Exception; //조직도 시퀀스 가져오기
	
	int work_total(Map<String, Object> param) throws Exception; //근무관리 리스트 개수
	
	List<Map<String, Object>> work_list(Map<String, Object> param) throws Exception; //근무관리 리스트
	
	void work_edit(Map<String, Object> param) throws Exception; //근무관리 추가/수정
	
	void work_delete(Map<String, Object> param) throws Exception; //콜 근무관리 삭제하기
	
	List<Map<String, Object>> work(Map<String, Object> param) throws Exception; //콜 근무관리 기본정보
	
	List<Map<String, Object>> work_detail(Map<String, Object> param) throws Exception; //콜 근무관리 상세내역 리스트
	
	List<Map<String, Object>> work_report(Map<String, Object> param) throws Exception; //콜 근무관리보고서
		
	int overtime_total(Map<String, Object> param) throws Exception; //연장근무관리 리스트 개수
	
	List<Map<String, Object>> overtime_list(Map<String, Object> param) throws Exception; //연장근무관리 리스트
	
	void overtime_edit(Map<String, Object> param) throws Exception; //연장근무관리 추가/수정
	
	void overtime_delete(Map<String, Object> param) throws Exception; //연장근무관리 삭제하기
	
	List<Map<String, Object>> overtime(Map<String, Object> param) throws Exception; //연장근무관리 기본정보
	
	List<Map<String, Object>> overtime_detail(Map<String, Object> param) throws Exception; //연장근무관리 상세내역 리스트
	
	void status_update(Map<String, Object> param) throws Exception; //연장근무관리 처리결과 변경
	
	List<Map<String, Object>> overtime_report(Map<String, Object> param) throws Exception; //연장근무관리보고서
	
	int revenue_total(Map<String, Object> param) throws Exception; //콜 근무 실적 리스트 개수
	
	List<Map<String, Object>> revenue_list(Map<String, Object> param) throws Exception; //콜 근무 실적 리스트
	
	List<Map<String, Object>> month_list(Map<String, Object> param) throws Exception; //월집계표 가져오기
	
	List<Map<String, Object>> revenue_excel(Map<String, Object> param) throws Exception; //콜근무실적 엑셀
	
	List<Map<String, Object>> overtime_report_excel(Map<String, Object> param) throws Exception; //연장근무관리보고서 엑셀
	
List<Map<String, Object>> overtime_excel(Map<String, Object> param) throws Exception; //연장근무관리 기본정보 엑셀
	
	List<Map<String, Object>> overtime_detail_excel(Map<String, Object> param) throws Exception; //연장근무관리 상세내역 리스트 엑셀
	
List<Map<String, Object>> select_work_excel(Map<String, Object> param) throws Exception; //콜 근무관리 기본정보 엑셀
	
	List<Map<String, Object>> select_work_detail_excel(Map<String, Object> param) throws Exception; //콜 근무관리 상세내역 리스트 엑셀
}
