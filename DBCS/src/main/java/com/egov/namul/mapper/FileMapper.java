package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("FileMapper")
public interface FileMapper {
	List<Map<String, Object>> select_delete_file(Map<String, Object> param) throws Exception; // 삭제할 파일 리스트 가져오기
	
	void delete_file(Map<String, Object> param) throws Exception; // 필요없는 파일 삭제하기
	
	void insert_file(Map<String, Object> param) throws Exception; // 파일 정보 저장
	
	List<Map<String, Object>> select_file(Map<String, Object> param) throws Exception; //파일 정보 가져오기
}
