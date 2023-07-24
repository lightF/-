package kr.bit.entity;

import lombok.Data;

@Data
public class Criteria {
	private int page; //현재 페이지 번호
	private int perPageNum; //페이지에 보여줄 게시글의 수
	public Criteria() {
		this.page=1;
		this.perPageNum=5;	//조정
		
	}
	//현재 페이지 게시글의 시작번호 
	public int getPageStart() {		// 1page    2page       3page   limit 쿼리
		return(page-1)*perPageNum;	// 0부터 시작 10부터 시작 20부터 시작  :limit ? , ? ,?
		//${pageStart},->getPagestart
		//${perPageNum},->setPerPageNum
		
	}
	
}
