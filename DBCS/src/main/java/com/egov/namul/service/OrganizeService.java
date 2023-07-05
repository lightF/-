package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface OrganizeService {
	List<Map<String, Object>> member_org(Map<String, Object> param) throws Exception; //인력 사업단 또는 팀 시퀀스 가져오기
	
	List<Map<String, Object>> organize_depth(Map<String, Object> param) throws Exception; // 상위 레벨 가져오기
	
	int total(Map<String, Object> param) throws Exception; // 조직도 리스트 개수 가져오기
	
	List<Map<String, Object>> list(Map<String, Object> param) throws Exception; // 조직도 리스트 가져오기
	
	List<Map<String, Object>> organize(Map<String, Object> param) throws Exception; // 조직도 상세정보 가져오기
	
	List<Map<String, Object>> organize_level(Map<String, Object> param) throws Exception; // 하위 레벨 조직도 시퀀스 가져오기
	
	int member_total(Map<String, Object> param) throws Exception; // 조직도 구성원 리스트 개수 가져오기
	
	List<Map<String, Object>> member_list(Map<String, Object> param) throws Exception; // 조직도 구성원 리스트 가져오기
	
	int edit(Map<String, Object> param) throws Exception; // 조직도 편집
	
	int delete(Map<String, Object> param) throws Exception; // 조직도 삭제
	
	List<Map<String, Object>> select_organize_list_excel(Map<String, Object> param) throws Exception; // 조직도 리스트 엑셀
}
