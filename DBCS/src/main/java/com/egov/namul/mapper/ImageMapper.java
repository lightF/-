package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ImageMapper")
public interface ImageMapper {
	List<Map<String, Object>> select_person_image(Map<String, Object> param) throws Exception;
	
	void insert_files(Map<String, Object> param) throws Exception;
}
