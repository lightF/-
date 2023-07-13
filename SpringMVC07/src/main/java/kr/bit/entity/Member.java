package kr.bit.entity;

import lombok.Data;

@Data
public class Member {
	
	private String memID;
	private String memPwd;
	private String memName;
	private String memPhone;
	private String memAddr;
	private double ladtitude;
	private String longitude;
}
