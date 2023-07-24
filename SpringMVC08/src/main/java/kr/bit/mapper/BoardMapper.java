package kr.bit.mapper;

import java.util.List;

import kr.bit.entity.Board;
import kr.bit.entity.Criteria;
import kr.bit.entity.Member;

//데이터 베이스 연동 메서드
public interface BoardMapper {
	public List<Board> getList(Criteria cri);
	public void insert(Board vo);
	public void insertSelectKey(Board vo);
	public Member login(Member vo); //SQL
	public Board read(int idx); 	//게시판 번호 읽기(read)
	public void update(Board vo);
	public void delete(int idx);
	public void replySeqUpdate(Board parent); //부모글 ->parent로 받는다
	public void replyInsert(Board vo);
	public int totalCount();
}
	
