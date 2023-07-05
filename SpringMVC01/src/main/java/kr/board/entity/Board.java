package kr.board.entity;

import lombok.Data;

//LOMBOK API 자동생성
//DB연결 객체
//@DATA 롬복

@Data
public class Board {
//6개의 멤버변수
		private int idx;
		private String title;
		private String content;
		private String writer;
		private String indate;
		private int count;
		//setter or getter 메서드 생성
		
		
		
}
