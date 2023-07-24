package kr.company.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
//DATABASE TABLE
@Entity	
@Data
public class Board {	//	VO <---orm---> TALBE
	@Id	//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//IDENTITY 자동증가
	//int보다 호환성이 있게 사용하므로 LONG
	//TABLE COLUMN과 MAPPING 된다.
	private Long idx; //자동증가 컬럼 지정
	private String title;
	
	@Column(length = 2000)
	private String content;
	
	@Column(updatable = false)
	private String writer;
	
	//insert시 빼라,,
	@Column(insertable = false, updatable = false, columnDefinition = "datetime default now()")
	private Date indate;	//MYSQL NOW()날짜,
	@Column(insertable = false, updatable = false, columnDefinition = "int default 0")
	private Long count;
}
