package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("TestMapper")
public interface TestMapper {
	List<Map<String, Object>> select_password(Map<String, Object> param) throws Exception;
	
	void insert_password(Map<String, Object> param) throws Exception;
}
