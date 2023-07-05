package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

//@mapper -mybatis API
@Mapper
public interface BoardMapper {
	public List<Board> getLists(); //전체리스트 메서드
	//Mapper getList에서 BoardMapper와 연동
	public void boardInsert(Board vo);
	public Board boardContent(int idx);
	
	public void boardDelete(int idx);
	
	public void boardUpdate(Board vo);
	
	@Update("update myboard set count=count+1 where idx=#{idx}") //mybatis에서 제공하는 API
	public void boardCount(int idx); //조회수증가
}


