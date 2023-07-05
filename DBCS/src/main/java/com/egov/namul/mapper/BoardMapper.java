package com.egov.namul.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BoardMapper")
public interface BoardMapper {
	int select_notice_cnt(Map<String, Object> param) throws Exception; // 공지사항 리스트 개수 가져오기
	
	List<Map<String, Object>> select_notice_list(Map<String, Object> param) throws Exception; // 공지사항 리스트 가져오기
	
	void insert_notice(Map<String, Object> param) throws Exception; // 공지사항 추가/수정
	
	int select_notice_cnt_max(Map<String, Object> param) throws Exception;
	
	void update_notice_cnt(Map<String, Object> param) throws Exception; // 공지사항 조회 수정
	
	List<Map<String, Object>> select_notice(Map<String, Object> param) throws Exception; // 공지사항 상세정보 가져오기
	
	void delete_notice(Map<String, Object> param) throws Exception; // 공지사항 삭제하기
	
	
	
	int select_board_cnt(Map<String, Object> param) throws Exception; // 게시판 리스트 개수 가져오기
	
	List<Map<String, Object>> select_board_list(Map<String, Object> param) throws Exception; // 게시판 리스트 가져오기
			
	void insert_board(Map<String, Object> param) throws Exception; // 게시판 추가/수정
	
	int select_board_cnt_max(Map<String, Object> param) throws Exception;
	
	void update_board_cnt(Map<String, Object> param) throws Exception; // 게시판 조회 수정
	
	List<Map<String, Object>> select_board(Map<String, Object> param) throws Exception; // 게시판 상세정보 가져오기
	
	void delete_board(Map<String, Object> param) throws Exception; // 게시판 삭제하기
}
