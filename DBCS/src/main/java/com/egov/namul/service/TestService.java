package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface TestService {
	List<Map<String, Object>> select_password(Map<String, Object> param) throws Exception;
	
	void insert_password(Map<String, Object> param) throws Exception;
}
