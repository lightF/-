package kr.bit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.bit.entity.Board;
import kr.bit.entity.Member;
import kr.bit.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardMapper boardmapper;
	
	@Override
	public List<Board> getList() {
		// TODO Auto-generated method stub
		List<Board> list = boardmapper.getList();
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


	
	

	
}
