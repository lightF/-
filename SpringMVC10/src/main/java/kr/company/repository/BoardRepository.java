package kr.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.company.entity.Board;

@Repository	//생략가능 JpaRepository<테이블이름, primarykey> (CRUD METHOD)
public interface BoardRepository extends JpaRepository<Board, Long>{
	//public List<Board> findAll();
	//select * from board where idx=#{idx}
	//쿼리 method findBy
	public Board findBywriter(String writer);
	//select * from Board where wrier =#{}
	
}
