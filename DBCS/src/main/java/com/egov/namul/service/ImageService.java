package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface ImageService {
	List<Map<String, Object>> select_person_image(Map<String, Object> param) throws Exception;
}
