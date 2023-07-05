package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("FacilityMapper")
public interface FacilityMapper {
	List<Map<String, Object>> select_standard_seq(Map<String, Object> param) throws Exception; //표준명 시퀀스 가져오기
	
	List<Map<String, Object>> select_organize_seq(Map<String, Object> param) throws Exception; //팀(조직도 시퀀스) 가져오기
	
	List<Map<String, Object>> select_device_status(Map<String ,Object> param) throws Exception; //이전 상태인 기기 시퀀스 가져오기
	
	int select_device_cnt(Map<String, Object> param) throws Exception; //기기 갯수 가져오기
	
	List<Map<String, Object>> select_device_list(Map<String ,Object> param) throws Exception; //기기 리스트 가져오기
	
	int select_device_check(Map<String ,Object> param) throws Exception; //다른 테이블에 기기 존재 여부
	
	void insert_device(Map<String ,Object> param) throws Exception; //기기 관리 추가/수정
	
	void insert_device_history(Map<String ,Object> param) throws Exception; //이전 및 철거기록 추가/수정
	
	void delete_device(Map<String ,Object> param) throws Exception; //기기 삭제
	
	List<Map<String, Object>> select_device(Map<String ,Object> param) throws Exception; //기기 기본정보 가져오기
	
	List<Map<String, Object>> select_device_history(Map<String ,Object> param) throws Exception; //기기 이전기록 가져오기
	
	List<Map<String, Object>> select_file(Map<String, Object> param) throws Exception; // 파일 정보 가져오기
	
	void insert_file(Map<String, Object> param) throws Exception; // 파일 정보 추가/수정
	
	void delete_file(Map<String, Object> param) throws Exception; // 파일 정보 삭제
	
	void update_file(Map<String, Object> param) throws Exception; // 파일 정보 업데이트
	
	List<Map<String, Object>> select_standard_parent(Map<String, Object> param) throws Exception; // 표준명 상위 레벨 시퀀스 가져오기
	
	int select_standard_cnt(Map<String, Object> param) throws Exception; // 표준명 리스트 갯수 가져오기
	
	List<Map<String, Object>> select_standard_list(Map<String, Object> param) throws Exception; // 표준명 리스트 가져오기
	
	List<Map<String, Object>> select_standard(Map<String, Object> param) throws Exception; // 표준명 상세정보 가져오기
	
	int select_standard_pcode(Map<String, Object> param) throws Exception; // 표준명 부모코드 가져오기 
	
	int select_standard_level(Map<String, Object> param) throws Exception; // 표준명 등급 확인 하기
	
	int select_depth_cnt(Map<String, Object> param) throws Exception; // 표준명 하위 레벨이 총 0개인지 99개인지 판단
	
	void insert_standard(Map<String, Object> param) throws Exception; // 표준명 추가/수정
	
	List<Map<String, Object>> select_standard_depth(Map<String, Object> param) throws Exception; // 표준명 하위 레벨 시퀀스 가져오기
	
	int select_standard_status(Map<String, Object> param) throws Exception; // 표준명 하위 레벨까지 기기관리에 포함되어 있는지 확인
	
	void delete_standard(Map<String, Object> param) throws Exception; // 표준명 삭제
	
	int select_account_cnt(Map<String, Object> param) throws Exception; // 거래처 관리 리스트 개수 구하기
	
	List<Map<String, Object>> select_account_list(Map<String, Object> param) throws Exception; // 거래처 관리 리스트 구하기
	
	void insert_account(Map<String, Object> param) throws Exception; // 거래처 관리 추가/수정
	
	List<Map<String, Object>> select_account(Map<String, Object> param) throws Exception; // 거래처 상세 정보 가져오기
	
	int select_account_table(Map<String, Object> param) throws Exception; // 거래처 관련 테이블 저장이 되어있는지 확인하기
	
	void delete_account(Map<String, Object> param) throws Exception; // 거래처 삭제하기
	
	int select_standard1_cnt(Map<String, Object> param) throws Exception; // 기기(표준명1) 개수 가져오기
	
	int select_organize_cnt(Map<String, Object> param) throws Exception; // 사업단 개수 가져오기
	
	List<Map<String, Object>> select_organize_table(Map<String, Object> param) throws Exception; // 사업단설비현황
	
	int select_history_cnt(Map<String, Object> param) throws Exception; // 이전/철거현황 개수 가져오기
	
	List<Map<String, Object>> select_history_table(Map<String, Object> param) throws Exception; // 이전/철거현황
	
	List<Map<String, Object>> select_history_excel(Map<String, Object> param) throws Exception; // 이전/철거현황 엑셀
	
List<Map<String, Object>> select_device_excel(Map<String, Object> param) throws Exception; //기기 기본정보 엑셀
	
	List<Map<String, Object>> select_device_history_excel(Map<String, Object> param) throws Exception; //이전 및 철거기록 엑셀
	
	List<Map<String, Object>> select_account_excel(Map<String, Object> param) throws Exception; //거래처 상세 정보 엑셀
}
