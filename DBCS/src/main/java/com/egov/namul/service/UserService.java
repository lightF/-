package com.egov.namul.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
	List<Map<String, Object>> login(Map<String, Object> param) throws Exception; //로그인
	
	List<Map<String, Object>> organize(Map<String, Object> param) throws Exception; //사업단 및 팀 시퀀스 담기
	
	List<Map<String, Object>> assign_task(Map<String, Object> param) throws Exception; //담당업무 시퀀스 담기
	
	List<Map<String, Object>> position(Map<String, Object> param) throws Exception; //직위 시퀀스 담기
	
	List<Map<String, Object>> job_grade(Map<String, Object> param) throws Exception; //직급 시퀀스 담기
	
	List<Map<String, Object>> job(Map<String, Object> param) throws Exception; // 직책 시퀀스 담기
	
	int total(Map<String, Object> param) throws Exception; //직원 총 인원수
	
	List<Map<String, Object>> list(Map<String, Object> param) throws Exception; //직원관리목록
	
	void edit(Map<String, Object> param, MultipartFile multiFile) throws Exception; //직원 정보 편집
	
	List<Map<String, Object>> profile(Map<String, Object> param) throws Exception; //직원 정보 가져오기
	
	List<Map<String, Object>> select_level(Map<String, Object> param) throws Exception; // 경력 정보 가져오기
	
	void delete(Map<String, Object> param) throws Exception; //직원 정보 삭제하기
	
	List<Map<String, Object>> table(Map<String, Object> param) throws Exception; // 직급별 인원현황 테이블
	
	int person_total(Map<String, Object> param) throws Exception; // 통계 전체 사원수 구하기
	
	List<Map<String, Object>> rate(Map<String, Object> param) throws Exception; // 통계 구하기
	
	int user_id(Map<String, Object> param) throws Exception; // 사번 중복 체크
	
	List<Map<String, Object>> org_group(Map<String, Object> param) throws Exception; // 사업단 리스트 가져오기
	
	List<Map<String, Object>> job_grade_list(Map<String, Object> param) throws Exception; // 인력현황 헤더리스트 가져오기
	
List<Map<String, Object>> select_member_list_excel(Map<String, Object> param) throws Exception; // 직원 리스트 가져오기 엑셀
	
	List<Map<String, Object>> select_profile_excel(Map<String, Object> param) throws Exception; // 직원 정보 가져오기 엑셀
}
