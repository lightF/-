package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BreakdownMapper")
public interface BreakdownMapper {
	List<Map<String, Object>> select_organize_seq(Map<String, Object> param) throws Exception; // 조직도 시퀀스 값 가져오기
	
	List<Map<String, Object>> select_system_seq(Map<String, Object> param) throws Exception; // 검색용 부품 시퀀스 가져오기
	
	List<Map<String, Object>> select_budget_seq(Map<String, Object> param) throws Exception; // 검색용 예산과목 시퀀스 가져오기
	
	int select_breakdown_cnt(Map<String, Object> param) throws Exception; // 고장접수관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_breakdown_list(Map<String, Object> param) throws Exception; // 고장접수관리 리스트 가져오기
	
	void insert_breakdown(Map<String, Object> param) throws Exception; // 고장접수관리 추가/수정
	
	void delete_breakdown_worker(Map<String, Object> param) throws Exception; // 고장접수관리 작업자 삭제
	
	void delete_breakdown_compose(Map<String, Object> param) throws Exception; // 고장접수관리 구성부 삭제
	
	void insert_breakdown_worker(Map<String, Object> param) throws Exception; // 고장접수관리 작업자 추가/수정
	
	void insert_breakdown_compose(Map<String, Object> param) throws Exception; // 고장접수관리 구성부 추가/수정
		
	void delete_breakdown_storage(Map<String, Object> param) throws Exception; // 고장접수관리 저장품내역 삭제
	
	void delete_breakdown_spare(Map<String, Object> param) throws Exception; // 고장접수관리 KHC예비품/재료비 삭제
	
	void insert_breakdown_storage(Map<String, Object> param) throws Exception; // 고장접수관리 저장품내역 추가/수정
	
	void insert_breakdown_spare(Map<String, Object> param) throws Exception; // 고장접수관리 KHC예비품/재료비 추가/수정
	
	List<Map<String, Object>> select_breakdown(Map<String, Object> param) throws Exception; // 고장접수관리 가져오기
	
	List<Map<String, Object>> select_breakdown_worker(Map<String, Object> param) throws Exception; // 고장접수관리 작업자 가져오기
	
	List<Map<String, Object>> select_breakdown_compose(Map<String, Object> param) throws Exception; // 고장접수관리 구성부 가져오기
	
	List<Map<String, Object>> select_breakdown_storage(Map<String, Object> param) throws Exception; // 고장접수관리 저장품내역 가져오기
	
	List<Map<String, Object>> select_breakdown_spare(Map<String, Object> param) throws Exception; // 고장접수관리 KHC예비품/재료비 가져오기
	
	int select_breakdown_status(Map<String, Object> param) throws Exception; // 고장접수관리 시퀀스 사용유무 확인
	
	void delete_breakdown(Map<String, Object> param) throws Exception; // 고장접수관리 삭제
	
	int select_part_cnt(Map<String, Object> param) throws Exception; // 저장품 또는 예비품 팝업 리스트 개수 가져오기
	
	List<Map<String, Object>> select_part_list(Map<String, Object> param) throws Exception; // 저장품 또는 예비품 팝업 리스트 가져오기
	
	int select_action_cnt(Map<String, Object> param) throws Exception; // 고장조치관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_action_list(Map<String, Object> param) throws Exception; // 고장조치관리 리스트 가져오기
	
	List<Map<String, Object>> select_action_count(Map<String, Object> param) throws Exception; // 고장조치관리 리스트 처리완료/미처리
	
	void insert_action(Map<String, Object> param) throws Exception; // 고장조치관리 추가/수정
	
	List<Map<String, Object>> select_action(Map<String, Object> param) throws Exception; // 고장조치관리 상세 정보 가져오기
	
	List<Map<String, Object>> select_action_date(Map<String, Object> param) throws Exception; // 고장조치 상세 리스트 가져오기
	
	List<Map<String, Object>> select_action_report(Map<String, Object> param) throws Exception; // 고장조치보고서 가져오기
	
	void delete_action(Map<String, Object> param) throws Exception; // 고장조치관리 삭제
	
	int select_shift_cnt(Map<String, Object> param) throws Exception; // 당직일지 리스트 개수 가져오기
	
	List<Map<String, Object>> select_shift_list(Map<String, Object> param) throws Exception; // 당직일지 리스트 가져오기	
	
	void insert_shift(Map<String, Object> param) throws Exception; // 당직일지 추가/수정
	
	void delete_worker(Map<String, Object> param) throws Exception; // 당직일지 근무자 삭제
	
	void insert_worker(Map<String, Object> param) throws Exception; // 당직일지 근무자 추가/수정
	
	void delete_contact(Map<String, Object> param) throws Exception; // 당직일지 지시/연락사항 삭제
	
	void insert_contact(Map<String, Object> param) throws Exception; // 당직일지 지시/연락사항 추가/수정
		
	void delete_report(Map<String, Object> param) throws Exception; // 당직일지 보고사항 삭제
	
	void insert_report(Map<String, Object> param) throws Exception; // 당직일지 보고사항 추가/수정	
	
	List<Map<String, Object>> select_shift(Map<String, Object> param) throws Exception; // 당직일지	
	
	List<Map<String, Object>> select_worker(Map<String, Object> param) throws Exception; // 당직일지 근무자 	
	
	List<Map<String, Object>> select_contact(Map<String, Object> param) throws Exception; // 당직일지 지시/연락사항
	
	List<Map<String, Object>> select_report(Map<String, Object> param) throws Exception; // 당직일지 보고사항
	
	void delete_shift(Map<String, Object> param) throws Exception; // 당직일지 삭제
	
	int select_days(Map<String, Object> param) throws Exception; // 일수 가져오기
	
	List<Map<String, Object>> select_shift_report(Map<String, Object> param) throws Exception; // 당직일지 보고서
	
	List<Map<String, Object>> select_system_table(Map<String, Object> param) throws Exception; // 고장통계
	
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
