package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface PartService {
	List<Map<String, Object>> organize(Map<String, Object> param) throws Exception; //조직도 시퀀스 가져오기
	
	List<Map<String, Object>> system(Map<String, Object> param) throws Exception; // 검색용 부품 시퀀스 가져오기
	
	List<Map<String, Object>> budget(Map<String, Object> param) throws Exception; // 검색용 예산과목 시퀀스 가져오기
	
	List<Map<String, Object>> payment_seq(Map<String, Object> param) throws Exception; // 부품명 검색 후 수불관리 시퀀스 가져오기
	
	List<Map<String, Object>> storage_org(Map<String, Object> param) throws Exception; //창고 관리부서 검색
	
	int part_total(Map<String, Object> param) throws Exception; //부품관리 갯수 가져오기
	
	List<Map<String, Object>> part_list(Map<String ,Object> param) throws Exception; //부품관리 리스트 가져오기
	
	void part_edit(Map<String ,Object> param) throws Exception; //부품관리 추가/수정
	
	List<Map<String, Object>> part(Map<String ,Object> param) throws Exception; //부품관리 기본정보 가져오기
	
	List<Map<String, Object>> spec(Map<String ,Object> param) throws Exception; //추가스펙 기본정보 가져오기
	
	int part_delete(Map<String ,Object> param) throws Exception; //부품관리 삭제
	
	int payment_total(Map<String, Object> param) throws Exception; //수불관리 갯수 가져오기
	
	List<Map<String, Object>> payment_list(Map<String ,Object> param) throws Exception; //수불관리 리스트 가져오기
	
	void payment_edit(Map<String ,Object> param) throws Exception; //수불관리 추가/수정
	
	List<Map<String, Object>> payment(Map<String ,Object> param) throws Exception; //수불관리 기본정보 가져오기
	
	List<Map<String, Object>> order_request(Map<String ,Object> param) throws Exception; //발주요청 기본정보 가져오기
	
	void payment_delete(Map<String ,Object> param) throws Exception; //수불관리 삭제
	
	int order_total(Map<String, Object> param) throws Exception; //임의 발주 / 요청 발주 갯수 가져오기
	
	List<Map<String, Object>> order_list(Map<String ,Object> param) throws Exception; //임의 발주 / 요청 발주 리스트 가져오기
	
	List<Map<String, Object>> payment_create(Map<String, Object> param) throws Exception; // 수불관리 자동정보
	
	int storage_total(Map<String, Object> param) throws Exception; //창고 갯수 가져오기
	
	List<Map<String, Object>> storage_list(Map<String ,Object> param) throws Exception; //창고 리스트 가져오기
	
	void storage_edit(Map<String ,Object> param) throws Exception; //창고 추가/수정
	
	List<Map<String, Object>> storage(Map<String ,Object> param) throws Exception; //창고 기본정보 가져오기
	
	List<Map<String, Object>> storage_group(Map<String ,Object> param) throws Exception; //창고 관리부서 가져오기
	
	int storage_delete(Map<String ,Object> param) throws Exception; //창고 삭제
	
	int storage_check(Map<String, Object> param) throws Exception; // 창고사용가능 여부 확인하기
	
	List<Map<String, Object>> stock(Map<String ,Object> param) throws Exception; //부품재고조회 테이블
	
	List<Map<String, Object>> organize_group(Map<String, Object> param) throws Exception; // 사업단 정보 가져오기
	
	List<Map<String, Object>> organize_depth(Map<String, Object> param) throws Exception; // 하위 레벨 조직도 시퀀스 가져오기
	
List<Map<String, Object>> part_excel(Map<String, Object> param) throws Exception; // 부품관리 상세정보 엑셀
	
	List<Map<String, Object>> select_spec_excel(Map<String, Object> param) throws Exception; // 부품관리 추가스펙 엑셀
	
	List<Map<String, Object>> select_payment_excel(Map<String, Object> param) throws Exception; //  수불관리 상세정보 엑셀 
	
	List<Map<String, Object>> select_order_request_excel(Map<String, Object> param) throws Exception; //  발주요청 정보 엑셀
	
	List<Map<String, Object>> select_storage_excel(Map<String, Object> param) throws Exception; //  창고관리 상세정보 엑셀
	
	List<Map<String, Object>> select_storage_group_excel(Map<String, Object> param) throws Exception; // 창고관리 관리부서 가져오기
}
