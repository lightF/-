package kr.bit.mapper;

import java.util.List;

import kr.bit.entity.Board;

//데이터 베이스 연동 메서드
public interface BoardMapper {
	public List<Board> getList();
	public void insert(Board vo);
	public void insertSelectKey(Board vo);
}
	
