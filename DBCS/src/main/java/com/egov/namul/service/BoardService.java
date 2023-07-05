package com.egov.namul.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
	int notice_total(Map<String, Object> param) throws Exception; // 공지사항 리스트 개수
	
	List<Map<String, Object>> notice_list(Map<String, Object> param) throws Exception; // 공지사항 리스트
	
	void notice_edit(Map<String, Object> param) throws Exception; // 공지사항 추가/수정
	
	List<Map<String, Object>> notice(Map<String, Object> param) throws Exception; // 공지사항 상세정보
	
	void notice_delete(Map<String, Object> param) throws Exception; // 공지사항 삭제
	
	int board_total(Map<String, Object> param) throws Exception; // 게시판 리스트 개수
	
	List<Map<String, Object>> board_list(Map<String, Object> param) throws Exception; // 게시판 리스트
	
	void board_edit(Map<String, Object> param) throws Exception; // 게시판 추가/수정
	
	List<Map<String, Object>> board(Map<String, Object> param) throws Exception; // 게시판 상세정보
	
	void board_delete(Map<String, Object> param) throws Exception; // 게시판 삭제
}
