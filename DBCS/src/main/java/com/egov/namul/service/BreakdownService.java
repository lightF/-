package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface BreakdownService {
	List<Map<String, Object>> organize(Map<String, Object> param) throws Exception; //조직도 시퀀스 가져오기
	
	List<Map<String, Object>> system(Map<String, Object> param) throws Exception; // 검색용 부품 시퀀스 가져오기
	
	List<Map<String, Object>> budget(Map<String, Object> param) throws Exception; // 검색용 예산과목 시퀀스 가져오기
	
	int breakdown_total(Map<String, Object> param) throws Exception; // 고장접수관리 리스트 개수
	
	List<Map<String, Object>> breakdown_list(Map<String, Object> param) throws Exception; // 고장접수관리 리스트
	
	void breakdown_edit(Map<String, Object> param) throws Exception; // 고장접수관리 추가/수정
	
	void storage_edit(Map<String, Object> param) throws Exception; // 저장품 또는 예비품 추가/수정/삭제
	
	List<Map<String, Object>> breakdown(Map<String, Object> param) throws Exception; // 고장접수관리 상세정보
	
	List<Map<String, Object>> breakdown_worker(Map<String, Object> param) throws Exception; // 작업자 상세정보
	
	List<Map<String, Object>> breakdown_compose(Map<String, Object> param) throws Exception; // 구성부 상세정보
	
	List<Map<String, Object>> breakdown_storage(Map<String, Object> param) throws Exception; // 저장품내역 상세정보
	
	List<Map<String, Object>> breakdown_spare(Map<String, Object> param) throws Exception; // KHC 상세정보
	
	int breakdown_delete(Map<String, Object> param) throws Exception; // 고장접수관리 삭제
	
	int part_total(Map<String, Object> param) throws Exception; // 저장품 또는 예비품 팝업 리스트 개수 가져오기
	
	List<Map<String, Object>> part_list(Map<String, Object> param) throws Exception; // 저장품 또는 예비품 팝업 리스트 가져오기
	
	int action_total(Map<String, Object> param) throws Exception; // 고장조치관리 리스트 개수
	
	List<Map<String, Object>> action_list(Map<String, Object> param) throws Exception; // 고장조치관리 리스트
	
	List<Map<String, Object>> action_count(Map<String, Object> param) throws Exception; // 고장조치관리 리스트 처리완료/미처리
	
	void action_edit(Map<String, Object> param) throws Exception; // 고장조치관리 추가/수정
	
	void action_delete(Map<String, Object> param) throws Exception; // 고장조치관리 삭제
	
	List<Map<String, Object>> action(Map<String, Object> param) throws Exception; // 고장조치관리 상세 정보
	
	List<Map<String, Object>> action_date(Map<String, Object> param) throws Exception; // 고장조치 상세 리스트
	
	List<Map<String, Object>> action_report(Map<String, Object> param) throws Exception; // 고장조치보고서
	
	int shift_total(Map<String, Object> param) throws Exception; // 당직일지 리스트 개수
	
	List<Map<String, Object>> shift_list(Map<String, Object> param) throws Exception; // 당직일지 리스트
	
	void shift_edit(Map<String, Object> param) throws Exception; // 당직일지 추가/수정
	
	List<Map<String, Object>> shift(Map<String, Object> param) throws Exception; // 당직일지	
	
	List<Map<String, Object>> worker(Map<String, Object> param) throws Exception; // 당직일지 근무자 	
	
	List<Map<String, Object>> contact(Map<String, Object> param) throws Exception; // 당직일지 지시/연락사항
	
	List<Map<String, Object>> report(Map<String, Object> param) throws Exception; // 당직일지 보고사항
	
	void shift_delete(Map<String, Object> param) throws Exception; // 당직일지 삭제
	
	List<Map<String, Object>> shift_report(Map<String, Object> param) throws Exception; // 당직일지 보고서
	
	List<Map<String, Object>> system_table(Map<String, Object> param) throws Exception; // 고장통계
	
List<Map<String, Object>> select_breakdown_excel(Map<String, Object> param) throws Exception; // 고장접수관리 엑셀
	
	List<Map<String, Object>> select_breakdown_worker_excel(Map<String, Object> param) throws Exception; // 고장접수관리 작업자 엑셀
	
	List<Map<String, Object>> select_breakdown_compose_excel(Map<String, Object> param) throws Exception; //고장접수관리 구성부 엑셀
	
	List<Map<String, Object>> select_breakdown_storage_excel(Map<String, Object> param) throws Exception; //고장접수관리 저장품내역 엑셀
	
	List<Map<String, Object>> select_breakdown_spare_excel(Map<String, Object> param) throws Exception; //고장접수관리 KHC예비품/재료비 엑셀
	
	List<Map<String, Object>> select_action_excel(Map<String, Object> param) throws Exception; //고장조치관리 상세 정보 엑셀
	
	List<Map<String, Object>> select_action_date_excel(Map<String, Object> param) throws Exception; //고장조치 리스트 엑셀

	List<Map<String, Object>> select_shift_excel(Map<String, Object> param) throws Exception; //당직일지 엑셀
	
	List<Map<String, Object>> select_worker_excel(Map<String, Object> param) throws Exception; //당직일지 근무자 엑셀
	
	List<Map<String, Object>> select_contact_excel(Map<String, Object> param) throws Exception; //당직일지 지시/연락사항 엑셀

	List<Map<String, Object>> select_report_excel(Map<String, Object> param) throws Exception; //당직일지 보고사항 엑셀
}
