package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MeasureMapper")
public interface MeasureMapper {
List<Map<String, Object>> select_org_seq(Map<String, Object> param) throws Exception; //조직도 시퀀스 값 가져오기
	
	List<Map<String, Object>> select_model_seq(Map<String, Object> param) throws Exception; //계측기 모델 시퀀스 값 가져오기
	
	List<Map<String, Object>> select_safe_seq(Map<String, Object> param) throws Exception; //안전용품 시퀀스 가져오기
	
	int select_measure_cnt(Map<String, Object> param) throws Exception; //계측기관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_measure_list(Map<String, Object> param) throws Exception; //계측기관리 리스트 가져오기
	
	List<Map<String, Object>> select_measure(Map<String, Object> param) throws Exception; //계측기관리 기본정보 가져오기
	
	List<Map<String, Object>> select_measure_detail(Map<String, Object> param) throws Exception; //계측기관리 검교정 리스트 가져오기
	
	List<Map<String, Object>> select_measure_sale(Map<String, Object> param) throws Exception; //계측기관리 매각 리스트 가져오기
	
	void insert_measure(Map<String, Object> param) throws Exception; //계측기관리 추가/수정
	
	void delete_measure_detail(Map<String, Object> param) throws Exception; //계측기관리 검교정 삭제
	
	void insert_measure_detail(Map<String, Object> param) throws Exception; //계측기관리 검교정 추가/수정
	
	void delete_measure_sale(Map<String, Object> param) throws Exception; //계측기관리 매각 삭제
	
	void insert_measure_sale(Map<String, Object> param) throws Exception; //계측기관리 매각 추가/수정
	
	void delete_measure(Map<String, Object> param) throws Exception; //계측기관리 삭제
	
	List<Map<String, Object>> select_measure_report(Map<String, Object> param) throws Exception; //계측기보고서
	
	int select_safety_cnt(Map<String, Object> param) throws Exception; //안전용품관리 리스트 개수 가져오기
	
	List<Map<String, Object>> select_safety_list(Map<String, Object> param) throws Exception; //안전용품관리 리스트 가져오기
	
	List<Map<String, Object>> select_safety(Map<String, Object> param) throws Exception; //안전용품관리 기본정보 가져오기
	
	List<Map<String, Object>> select_safety_detail(Map<String, Object> param) throws Exception; //안전용품관리 검교정 리스트 가져오기
	
	void insert_safety(Map<String, Object> param) throws Exception; //안전용품관리 추가/수정
	
	void delete_safety_detail(Map<String, Object> param) throws Exception; //안전용품관리 검교정 삭제
	
	void insert_safety_detail(Map<String, Object> param) throws Exception; //안전용품관리 검교정 추가/수정
	
	void delete_safety(Map<String, Object> param) throws Exception; //안전용품관리 삭제
	
	List<Map<String, Object>> select_measure_table(Map<String, Object> param) throws Exception; //계측기통계 테이블 가져오기
	
List<Map<String, Object>> select_safety_list_excel(Map<String, Object> param) throws Exception; //안전용품관리 리스트 엑셀
	
	List<Map<String, Object>> select_safety_excel(Map<String, Object> param) throws Exception; //안전용품관리 기본정보 엑셀
	
	List<Map<String, Object>> safety_detail_excel(Map<String, Object> param) throws Exception; //안전용품관리 입출정보 엑셀
	
	List<Map<String, Object>> select_measure_list_excel(Map<String, Object> param) throws Exception; //계측관리 리스트 엑셀
	
	List<Map<String, Object>> select_measure_excel(Map<String, Object> param) throws Exception; //계측관리 기본정보 엑셀
	
	List<Map<String, Object>> select_measure_detail_excel(Map<String, Object> param) throws Exception; //계측기관리 검교정 리스트 엑셀
	
	List<Map<String, Object>> select_measure_sale_excel(Map<String, Object> param) throws Exception; //계측기관리 매각 리스트 엑셀
}
