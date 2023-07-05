package com.egov.namul.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egov.namul.mapper.BoardMapper;
import com.egov.namul.service.BoardService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("BoardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService {

	@Resource(name="BoardMapper")
	private BoardMapper boardDAO;
	
	@Resource(name = "fileUploadProperty")
	private Properties fileUploadProperty;

	@Override
	public int notice_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.select_notice_cnt(param);
	}

	@Override
	public List<Map<String, Object>> notice_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.select_notice_list(param);
	}

	@Override
	public void notice_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.insert_notice(param);
	}

	@Override
	public List<Map<String, Object>> notice(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int cnt = boardDAO.select_notice_cnt_max(param);
		param.put("cnt", cnt);
		boardDAO.update_notice_cnt(param); //조회수 카운트
		return boardDAO.select_notice(param);
	}

	@Override
	public void notice_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.delete_notice(param);
	}

	@Override
	public int board_total(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.select_board_cnt(param);
	}

	@Override
	public List<Map<String, Object>> board_list(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.select_board_list(param);
	}

	@Override
	public void board_edit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.insert_board(param);
	}

	@Override
	public List<Map<String, Object>> board(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int cnt = boardDAO.select_board_cnt_max(param);
		param.put("cnt", cnt);
		boardDAO.update_board_cnt(param); //조회수 카운트
		return boardDAO.select_board(param);
	}

	@Override
	public void board_delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.delete_board(param);
	}	
	
}
