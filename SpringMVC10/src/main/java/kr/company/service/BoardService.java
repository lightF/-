package kr.company.service;

import java.util.List;

import kr.company.entity.Board;

public interface BoardService {
	
	public List<Board> getList();	//리스트 가져오기
	public void register(Board vo);	//등록하기 메서드
}
