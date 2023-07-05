package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("UserMapper")
public interface UserMapper {	
	List<Map<String, Object>> select_member(Map<String, Object> param) throws Exception; // 멤버 정보 가져오기
	
	List<Map<String, Object>> select_org_seq(Map<String, Object> param) throws Exception; // 사업단/팀 시퀀스 가져오기
	
	List<Map<String, Object>> select_task_seq(Map<String, Object> param) throws Exception; // 담당업무 시퀀스 가져오기
	
	List<Map<String, Object>> select_position_seq(Map<String, Object> param) throws Exception; // 직위 시퀀스 가져오기
	
	List<Map<String, Object>> select_grade_seq(Map<String, Object> param) throws Exception; // 직급 시퀀스 가져오기
	
	List<Map<String, Object>> select_job_seq(Map<String, Object> param) throws Exception; // 직책 시퀀스 가져오기
	
	int select_member_count(Map<String, Object> param) throws Exception; // 직원 리스트 총 갯수 가져오기
	
	List<Map<String, Object>> select_member_list(Map<String, Object> param) throws Exception; // 직원 리스트 가져오기
	
	void insert_member(Map<String, Object> param) throws Exception; // 직원 기본 정보 추가/수정
	
	void delete_member_school(Map<String, Object> param) throws Exception; // 학력사항 삭제
	
	void insert_member_school(Map<String, Object> param) throws Exception; // 학력사항 추가/수정
	
	void delete_member_edu(Map<String, Object> param) throws Exception; // 교육이력 삭제
	
	void insert_member_edu(Map<String, Object> param) throws Exception; // 교육이력 추가/수정
	
	void delete_member_license(Map<String, Object> param) throws Exception; // 자격면허 삭제
	
	void insert_member_license(Map<String, Object> param) throws Exception; // 자격면허 추가/수정
	
	void delete_member_career(Map<String, Object> param) throws Exception; // 타사이력 삭제
	
	void insert_member_career(Map<String, Object> param) throws Exception; // 타사이력 추가/수정
	
	void delete_member_project(Map<String, Object> param) throws Exception; // 프로젝트 삭제
	
	void insert_member_project(Map<String, Object> param) throws Exception; // 프로젝트 추가/수정
	
	void delete_member_order(Map<String, Object> param) throws Exception; // 발주처 삭제
	
	void insert_member_order(Map<String, Object> param) throws Exception; // 발주처 추가/수정
	
	void delete_member_society(Map<String, Object> param) throws Exception; // 협회경력 삭제
	
	void insert_member_society(Map<String, Object> param) throws Exception; // 협회경력 추가/수정

	List<Map<String, Object>> select_file(Map<String, Object> param) throws Exception; // 프로필 파일 정보 가져오기

	List<Map<String, Object>> select_level(Map<String, Object> param) throws Exception; // 경력 정보 가져오기
	
	void delete_member_level(Map<String, Object> param) throws Exception; // 경력 정보 삭제
	
	void insert_member_level(Map<String, Object> param) throws Exception; // 경력 정보 추가/수정
	
	void insert_file(Map<String, Object> param) throws Exception; // 프로필 파일 정보 추가/수정
	
	void delete_file(Map<String, Object> param) throws Exception; // 프로필 파일 정보 삭제
	
	void update_file(Map<String, Object> param) throws Exception; // 프로필 파일 정보 업데이트
	
	List<Map<String, Object>> select_profile(Map<String, Object> param) throws Exception; // 프로필 정보 가져오기
	
	void delete_member(Map<String, Object> param) throws Exception; // 직원 삭제
	
	int select_career_status(Map<String, Object> param) throws Exception; // 직원 정보 변동 상태 확인
	
	List<Map<String, Object>> select_career_history(Map<String, Object> param) throws Exception; // 자사이력 가져오기
	
	void insert_career(Map<String, Object> param) throws Exception; // 자사이력 추가/수정
	
	List<Map<String, Object>> select_person_cnt(Map<String, Object> param) throws Exception; // 직급별 인원현황 테이블
	
	int select_person_total(Map<String, Object> param) throws Exception; // 통계 전체 사원수 구하기
	
	List<Map<String, Object>> select_person_rate(Map<String, Object> param) throws Exception; // 통계 구하기
	
	int select_user_id(Map<String, Object> param) throws Exception; // 사번 중복 여부 체크하기
	
	List<Map<String, Object>> select_org_group(Map<String, Object> param) throws Exception; // 사업단 리스트 가져오기
	
	List<Map<String, Object>> select_job_grade(Map<String, Object> param) throws Exception; // 인력현황 헤더리스트 가져오기
	
List<Map<String, Object>> select_member_list_excel(Map<String, Object> param) throws Exception; // 직원 리스트 가져오기 엑셀
	
	List<Map<String, Object>> select_profile_excel(Map<String, Object> param) throws Exception; // 직원 정보 가져오기 엑셀
}
