package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface MainService {
	List<Map<String, Object>> menu(Map<String, Object> param) throws Exception; //메뉴 가져오기
	
	List<Map<String, Object>> bookmark(Map<String, Object> param) throws Exception; //북마크 리스트
	
	void bookmark_edit(Map<String, Object> param) throws Exception; //북마크 추가
	
	void bookmark_delete(Map<String, Object> param) throws Exception; //북마크 삭제
	
	List<Map<String, Object>> register_chart(Map<String, Object> param) throws Exception; //등록 통계
	
	List<Map<String, Object>> breakdown_chart(Map<String, Object> param) throws Exception; //주간 고장접수 신고지수
	
	List<Map<String, Object>> action_chart(Map<String, Object> param) throws Exception; //주간 고장조치 신고지수
	
	List<Map<String, Object>> work(Map<String, Object> param) throws Exception; //근무일정 가져오기
	
	List<Map<String, Object>> worker_chart(Map<String, Object> param) throws Exception; //근무자 통계
}
