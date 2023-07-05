package com.egov.namul.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	void multi_upload(Map<String, Object> param, List<MultipartFile> multiFiles, String[] fileSeq, String folderPath) throws Exception;
	
	void multi_resize_upload(Map<String, Object> param, List<MultipartFile> multiFiles, String folderPath, String[] fileSeq, String[] fileDel, int width, int height) throws Exception;

	void single_upload(Map<String, Object> param, MultipartFile multiFile, String folderPath, int fileSeq) throws Exception;
	
	void single_resize_upload(Map<String, Object> param, MultipartFile multiFile, String folderPath, int fileSeq, int width, int height) throws Exception;
	
	void single_resize_upload(Map<String, Object> param, MultipartFile multiFile, String folderPath, int fileSeq, int width, int height, int code) throws Exception;
	
	List<Map<String, Object>> file(Map<String, Object> param) throws Exception;
	
	void delete(Map<String, Object> param, String folderPath) throws Exception;
}
