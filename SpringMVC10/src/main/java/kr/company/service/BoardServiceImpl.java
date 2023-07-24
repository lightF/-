package kr.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.company.entity.Board;
import kr.company.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardRepository boardrepository;
	
	@Override
	public List<Board> getList() {
		// TODO Auto-generated method stub
		List<Board> list=boardrepository.findAll();
		return null;
	}
	@Override
	public void register(Board vo) {
		// TODO Auto-generated method stub
		boardrepository.save(vo);
	}

}
