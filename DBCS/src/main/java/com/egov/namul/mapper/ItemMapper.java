package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ItemMapper")
public interface ItemMapper {	
	int select_item_cnt(Map<String, Object> param) throws Exception; 
	
	List<Map<String, Object>> select_item(Map<String, Object> param) throws Exception; //항목관리 리스트
	
	void delete_item(Map<String, Object> param) throws Exception; //항목관리 삭제
	
	void update_auto_increament(Map<String, Object> param) throws Exception; //항목관리 시퀀스 초기화
	
	void insert_item(Map<String, Object> param) throws Exception; //항목관리 추가/수정
}
