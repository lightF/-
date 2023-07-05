package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CallMapper")
public interface CallMapper {
	List<Map<String, Object>> select_org_seq(Map<String, Object> param) throws Exception; //조직도 시퀀스 가져오기
	
	int select_work_cnt(Map<String, Object> param) throws Exception; //근무관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_work_list(Map<String, Object> param) throws Exception; //근무관리 리스트 가져오기
	
	void insert_work(Map<String, Object> param) throws Exception; //근무관리 추가/수정
	
	void delete_work_detail(Map<String, Object> param) throws Exception; //콜 근무관리 상세내역 삭제
	
	void insert_work_detail(Map<String, Object> param) throws Exception; //콜 근무관리 상세내역 추가/수정
	
	void delete_work(Map<String, Object> param) throws Exception; //콜 근무관리 삭제하기
	
	List<Map<String, Object>> select_work(Map<String, Object> param) throws Exception; //콜 근무관리 기본정보
	
	List<Map<String, Object>> select_work_detail(Map<String, Object> param) throws Exception; //콜 근무관리 상세내역 리스트
	
	List<Map<String, Object>> select_work_report(Map<String, Object> param) throws Exception; //콜 근무관리보고서 가져오기
	
	int select_overtime_cnt(Map<String, Object> param) throws Exception; //연장근무관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_overtime_list(Map<String, Object> param) throws Exception; //연장근무관리 리스트 가져오기
	
	void insert_overtime(Map<String, Object> param) throws Exception; //연장근무관리 추가/수정
	
	void delete_overtime_detail(Map<String, Object> param) throws Exception; //연장근무관리 상세내역 삭제 
	
	void insert_overtime_detail(Map<String, Object> param) throws Exception; //연장근무관리 상세내역 추가/수정
	
	List<Map<String, Object>> select_overtime(Map<String, Object> param) throws Exception; //연장근무관리 기본정보
	
	List<Map<String, Object>> select_overtime_detail(Map<String, Object> param) throws Exception; //연장근무관리 상세정보
	
	void delete_overtime(Map<String, Object> param) throws Exception; //연장근무관리 삭제
	
	void update_overtime_status(Map<String, Object> param) throws Exception; //연장근무관리 처리결과 변경
	
	List<Map<String, Object>> select_overtime_report(Map<String, Object> param) throws Exception; //연장근무관리 보고서
	
	int select_revenue_cnt(Map<String, Object> param) throws Exception; //콜 근무 실적 리스트 개수 가져오기
	
	List<Map<String, Object>> select_revenue_list(Map<String, Object> param) throws Exception; //콜 근무 실적 리스트 가져오기
	
	List<Map<String, Object>> select_month_list(Map<String, Object> param) throws Exception; //월집계표 가져오기
	
	List<Map<String, Object>> select_revenue_excel(Map<String, Object> param) throws Exception; //콜근무실적 엑셀
	
	List<Map<String, Object>> select_overtime_report_excel(Map<String, Object> param) throws Exception; //연장근무관리보고서 엑셀
	
List<Map<String, Object>> select_overtime_excel(Map<String, Object> param) throws Exception; //연장근무관리 기본정보 엑셀 
	
	List<Map<String, Object>> select_overtime_detail_excel(Map<String, Object> param) throws Exception; //연장근무관리 상세정보 엑셀 
	
	List<Map<String, Object>> select_work_excel(Map<String, Object> param) throws Exception; //콜 근무관리 기본정보 엑셀
	
	List<Map<String, Object>> select_work_detail_excel(Map<String, Object> param) throws Exception; //콜 근무관리 상세내역 리스트 엑셀
}
