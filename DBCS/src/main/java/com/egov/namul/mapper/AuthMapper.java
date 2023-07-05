package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("AuthMapper")
public interface AuthMapper {
	List<Map<String, Object>> select_auth_list(Map<String, Object> param) throws Exception; //권한 리스트 가져오기
	
	void insert_auth(Map<String, Object> param) throws Exception; //권한 수정
	
	int select_auth_edit(Map<String, Object> param) throws Exception; //메뉴별 편집 권한
}
