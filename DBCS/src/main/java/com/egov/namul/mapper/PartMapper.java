package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("PartMapper")
public interface PartMapper {
	List<Map<String, Object>> select_organize_seq(Map<String, Object> param) throws Exception; // 조직도 시퀀스 값 가져오기
	
	List<Map<String, Object>> select_system_seq(Map<String, Object> param) throws Exception; // 검색용 부품 시퀀스 가져오기
	
	List<Map<String, Object>> select_budget_seq(Map<String, Object> param) throws Exception; // 검색용 예산과목 시퀀스 가져오기
	
	List<Map<String, Object>> select_payment_seq(Map<String, Object> param) throws Exception; // 부품명 검색 후 수불관리 시퀀스 가져오기
	
	List<Map<String, Object>> select_storage_org(Map<String, Object> param) throws Exception; // 창고 관리부서 검색
	
	int select_part_cnt(Map<String, Object> param) throws Exception; // 부품관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_part_list(Map<String, Object> param) throws Exception; // 부품관리 리스트 가져오기
	
	void insert_part(Map<String, Object> param) throws Exception; // 부품관리 추가/수정
	
	void delete_spec(Map<String, Object> param) throws Exception; // 추가스펙 삭제
	
	void insert_spec(Map<String, Object> param) throws Exception; // 추가스펙 추가/수정
	
	List<Map<String, Object>> select_part(Map<String, Object> param) throws Exception; // 부품관리 상세정보 가져오기
	
	List<Map<String, Object>> select_spec(Map<String, Object> param) throws Exception; // 부품관리 추가스펙 가져오기
	
	int select_part_status(Map<String, Object> param) throws Exception; // 부품관리 사용여부 확인하기
	
	void delete_part(Map<String, Object> param) throws Exception; // 부품관리 삭제하기
	
	int select_payment_cnt(Map<String, Object> param) throws Exception; // 수불관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_payment_list(Map<String, Object> param) throws Exception; // 수불관리 리스트 가져오기
	
	void insert_payment(Map<String, Object> param) throws Exception; // 수불관리 추가/수정
	
	List<Map<String, Object>> select_order_seq(Map<String, Object> param) throws Exception; // 발주요청 시퀀스 가져오기
	
	void delete_payment(Map<String, Object> param) throws Exception; // 수불관리 삭제
	
	void delete_order_request(Map<String, Object> param) throws Exception; // 발주요청 삭제
	
	void insert_order_request(Map<String, Object> param) throws Exception; // 발주요청 추가/수정
	
	List<Map<String, Object>> select_payment(Map<String, Object> param) throws Exception; // 수불관리 상세정보 가져오기
	
	List<Map<String, Object>> select_order_request(Map<String, Object> param) throws Exception; // 발주요청 정보 가져오기
	
	int select_order_cnt(Map<String, Object> param) throws Exception; // 임의 발주 / 요청 발주 리스트 개수 가져오기
	
	List<Map<String, Object>> select_order_list(Map<String, Object> param) throws Exception; // 임의 발주 / 요청 발주 리스트 가져오기
	
	List<Map<String, Object>> select_payment_create(Map<String, Object> param) throws Exception; // 수불관리 자동정보
	
	int select_storage_cnt(Map<String, Object> param) throws Exception; // 창고관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_storage_list(Map<String, Object> param) throws Exception; // 창고관리 리스트 가져오기
	
	void insert_storage(Map<String, Object> param) throws Exception; // 창고관리 추가/수정
	
	void delete_storage_group(Map<String, Object> param) throws Exception; // 창고관리 관리부서 삭제하기
	
	void insert_storage_group(Map<String, Object> param) throws Exception; // 창고관리 관리부서 추가/수정
	
	List<Map<String, Object>> select_storage(Map<String, Object> param) throws Exception; // 창고관리 상세정보 가져오기
	
	List<Map<String, Object>> select_storage_group(Map<String, Object> param) throws Exception; // // 창고관리 관리부서 가져오기
	
	int select_storage_status(Map<String, Object> param) throws Exception; // 창고관리 팝업 사용 유무 확인하기
	
	void delete_storage(Map<String, Object> param) throws Exception; // 창고관리 삭제하기
	
	int select_storage_check(Map<String, Object> param) throws Exception; // 창고사용가능 여부 확인하기
	
	List<Map<String, Object>> select_stock_table(Map<String, Object> param) throws Exception; // 부품재고조회 테이블 가져오기
	
	List<Map<String, Object>> select_order_info(Map<String, Object> param) throws Exception; // 부서/창고/부품정보/수량/단가 가져오기
	
	void insert_order(Map<String, Object> param) throws Exception; // 승인 이후 재고 업데이트하기
	
	// 상세 재고 내역 업데이트
	
	List<Map<String, Object>> select_organize(Map<String, Object> param) throws Exception; // 사업단 정보 가져오기
	
	List<Map<String, Object>> select_organize_depth(Map<String, Object> param) throws Exception; // 하위 레벨 조직도 시퀀스 가져오기
	
List<Map<String, Object>> select_part_excel(Map<String, Object> param) throws Exception; // 부품관리 상세정보 엑셀
	
	List<Map<String, Object>> select_spec_excel(Map<String, Object> param) throws Exception; // 부품관리 추가스펙 엑셀
	
	List<Map<String, Object>> select_payment_excel(Map<String, Object> param) throws Exception; //  수불관리 상세정보 엑셀 
	
	List<Map<String, Object>> select_order_request_excel(Map<String, Object> param) throws Exception; //  발주요청 정보 엑셀
	
	List<Map<String, Object>> select_storage_excel(Map<String, Object> param) throws Exception; //  창고관리 상세정보 엑셀
	
	List<Map<String, Object>> select_storage_group_excel(Map<String, Object> param) throws Exception; // 창고관리 관리부서 가져오기
}
