package kr.bit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.bit.entity.Board;
import kr.bit.entity.Criteria;
import kr.bit.entity.Member;
import kr.bit.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardMapper boardmapper;
	
	@Override
	public List<Board> getList(Criteria cri) {
		// TODO Auto-generated method stub
		List<Board> list = boardmapper.getList(cri);
		return list;
	}

	@Override
	public Member login(Member vo) {
		// TODO Auto-generated method stub
		Member mvo=boardmapper.login(vo);
		return mvo;
	}

	@Override
	public void register(Board vo) {
		// TODO Auto-generated method stub
		boardmapper.insertSelectKey(vo);
	}

	@Override
	public Board get(int idx) {
		// TODO Auto-generated method stub
		Board vo=boardmapper.read(idx);
		return vo;
	}

	@Override
	public void modify(Board vo) {
		// TODO Auto-generated method stub
		boardmapper.update(vo);
	}

	@Override
	public void remove(int idx) {
		// TODO Auto-generated method stub
		boardmapper.delete(idx);
	}

	@Override
	public void replyProcess(Board vo) {
		// TODO Auto-generated method stub
		//댓글에 필요한 처리 
		//BOARDSERVICE 레이어에 REPLY 처리
		// 부모글(게시글)의 정보를 가져오기(VO->IDX)
		Board parent=boardmapper.read(vo.getIdx());
		//2.부모글의 boardGroup의 값을 -> 답글(vo)정보에 저장하기
		vo.setBoardGroup(parent.getBoardGroup());
		//3.부모글의 boardsequence 값 1을 더해서 -> 답글(vo)정보에 저장하기
		vo.setBoardSequence(parent.getBoardSequence()+1);
		//4. 부모글의 boardLevel의 값을 1더해서 ->답글(vo)에 정보에 저장하기
		vo.setBoardLevel(parent.getBoardLevel()+1);
		// 5. 같은 boardGroup에 있는 글 중에서 부모글의 
		// boardsequence보다 큰 값들을 모두 1씩 업데이트하기  
		boardmapper.replySeqUpdate(parent);
		// 6. vo에있는 
		boardmapper.replyInsert(vo);
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return boardmapper.totalCount();
	}

	
}
