package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MainMapper")
public interface MainMapper {		
	int select_menu_use(Map<String, Object> param) throws Exception; //메뉴 사용여부
	
	Map<String, Object> select_auth(Map<String, Object> param) throws Exception; //메뉴별 권한 가져오기
	
	List<Map<String, Object>> select_menu(Map<String, Object> param) throws Exception; //메뉴 가져오기
	
	List<Map<String, Object>> select_bookmark(Map<String, Object> param) throws Exception; //북마크 리스트
	
	void delete_bookmark(Map<String, Object> param) throws Exception; //북마크 삭제
	
	void insert_bookmark(Map<String, Object> param) throws Exception; //북마크 추가/수정
	
	List<Map<String, Object>> select_register_chart(Map<String, Object> param) throws Exception; //등록 통계
	
	List<Map<String, Object>> select_breakdown_chart(Map<String, Object> param) throws Exception; //주간 고장접수 신고지수
	
	List<Map<String, Object>> select_action_chart(Map<String, Object> param) throws Exception; //주간 고장조치 신고지수
	
	List<Map<String, Object>> select_work(Map<String, Object> param) throws Exception; //근무일정 가져오기
	
	List<Map<String, Object>> select_worker_chart(Map<String, Object> param) throws Exception; //근무자 통계
}
