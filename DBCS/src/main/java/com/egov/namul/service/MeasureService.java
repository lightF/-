package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface MeasureService {
List<Map<String, Object>> organize(Map<String, Object> param) throws Exception; //조직도 시퀀스 값 가져오기
	
	List<Map<String, Object>> model(Map<String, Object> param) throws Exception; //계측기 모델 시퀀스 값 가져오기
	
	List<Map<String, Object>> safe(Map<String, Object> param) throws Exception; //안전용품 시퀀스 가져오기
	
	int measure_total(Map<String, Object> param) throws Exception; //계측기관리 리스트 개수 가져오기
	
	List<Map<String, Object>> measure_list(Map<String, Object> param) throws Exception; //계측기관리 리스트 가져오기
	
	List<Map<String, Object>> measure(Map<String, Object> param) throws Exception; //계측기관리 기본정보 가져오기
	
	List<Map<String, Object>> measure_detail(Map<String, Object> param) throws Exception; //계측기관리 검교정 리스트 가져오기
	
	List<Map<String, Object>> measure_sale(Map<String, Object> param) throws Exception; //계측기관리 매각 리스트 가져오기
	
	void measure_edit(Map<String, Object> param) throws Exception; //계측기관리 추가/수정
	
	void measure_delete(Map<String, Object> param) throws Exception; //계측기관리 삭제
	
	List<Map<String, Object>> measure_report(Map<String, Object> param) throws Exception; //계측기보고서	
	
	int safety_total(Map<String, Object> param) throws Exception; //안전용품관리 리스트 개수 가져오기
	
	List<Map<String, Object>> safety_list(Map<String, Object> param) throws Exception; //안전용품관리 리스트 가져오기
	
	List<Map<String, Object>> safety(Map<String, Object> param) throws Exception; //안전용품관리 기본정보 가져오기
	
	List<Map<String, Object>> safety_detail(Map<String, Object> param) throws Exception; //안전용품관리 검교정 리스트 가져오기
	
	void safety_edit(Map<String, Object> param) throws Exception; //안전용품관리 추가/수정
	
	void safety_delete(Map<String, Object> param) throws Exception; //안전용품관리 삭제
	
	List<Map<String, Object>> measure_table(Map<String, Object> param) throws Exception; //계측기통계 테이블 가져오기
	
List<Map<String, Object>> safety_list_excel(Map<String, Object> param) throws Exception; //안전용품관리 리스트 엑셀
	
	List<Map<String, Object>> safety_excel(Map<String, Object> param) throws Exception; //안전용품관리 기본정보 엑셀
	
	List<Map<String, Object>> safety_detail_excel(Map<String, Object> param) throws Exception; //안전용품관리 입출정보 엑셀
	
	List<Map<String, Object>> measure_list_excel(Map<String, Object> param) throws Exception; //계측관리 리스트 엑셀
	
	List<Map<String, Object>> measure_excel(Map<String, Object> param) throws Exception; // 계측관리 기본정보 엑셀
	
	List<Map<String, Object>> measure_detail_excel(Map<String, Object> param) throws Exception; //계측기관리 검교정 리스트 엑셀
	
	List<Map<String, Object>> measure_sale_excel(Map<String, Object> param) throws Exception; //계측기관리 매각 리스트 엑셀
}
