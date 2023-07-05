package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface ItemService {
	int item_total(Map<String, Object> param) throws Exception; // 항목관리 리스트 총 개수
	
	List<Map<String, Object>> item(Map<String, Object> param) throws Exception; // 항목관리 리스트
	
	void item_edit(Map<String, Object> param) throws Exception; // 항목관리 추가/수정/삭제
}
