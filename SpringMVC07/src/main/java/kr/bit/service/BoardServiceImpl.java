package kr.bit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.bit.entity.Board;
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

	
}
