package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("OrganizeMapper")
public interface OrganizeMapper {
	
	List<Map<String, Object>> select_member_org(Map<String, Object> param) throws Exception; // 인사관리에서 해당 구성원 조직 시퀀스 가져오기
	
	List<Map<String, Object>> select_organize_depth(Map<String, Object> param) throws Exception; // 상위 레벨 가져오기
	
	int select_organize_cnt(Map<String, Object> param) throws Exception; // 조직도 리스트 개수 가져오기
	
	List<Map<String, Object>> select_organize_list(Map<String, Object> param) throws Exception; // 조직도 리스트 가져오기
	
	List<Map<String, Object>> select_organize(Map<String, Object> param) throws Exception; // 조직도 정보 가져오기
	
	List<Map<String, Object>> select_organize_seq(Map<String, Object> param) throws Exception; // 하위 레벨 조직도 시퀀스 가져오기
	
	int select_member_cnt(Map<String, Object> param) throws Exception; // 조직도 구성원 개수 가져오기
	
	List<Map<String, Object>> select_member_list(Map<String, Object> param) throws Exception; // 조직도 구성원 리스트 가져오기
	
	int select_organize_level(Map<String, Object> param) throws Exception; // 조직도 레벨 최대인지 가져오기
	
	void insert_organize(Map<String, Object> param) throws Exception; // 조직도 추가/수정
	
	void delete_organize(Map<String, Object> param) throws Exception; // 조직도 삭제
	
	List<Map<String, Object>> select_organize_list_excel(Map<String, Object> param) throws Exception; // 조직도 리스트 엑셀
}
