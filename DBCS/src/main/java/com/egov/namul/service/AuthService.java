package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface AuthService {
	List<Map<String, Object>> auth_list(Map<String, Object> param) throws Exception; //권한 리스트 가져오기
	
	void auth_edit(Map<String, Object> param) throws Exception; //권한 수정
	
	int select_auth_edit(Map<String, Object> param) throws Exception; //메뉴별 편집 권한
}
