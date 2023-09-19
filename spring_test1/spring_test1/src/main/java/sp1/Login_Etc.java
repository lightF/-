package sp1;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Login_Etc extends Thread {

	Connection con = null;
	PreparedStatement ps = null;
	String id = ""; // part가 web일 경우 mname
	String nick = "";
	String mail = "";// part가 web일경우 mpw
	String part = "";
	int call = 0;

	public void setCall(int call) {
		this.call = call;
	}

	public int getCall() {
		return call;
	}
	public Login_Etc() {
		
	}
	public Login_Etc(String a, String b, String c, String d) throws Exception {
		if (d.equals("web")) {
			id = a;
			mail = b;
		} else {
			id = a;
			mail = b;
			nick = c;
			part = d;
		}
		start();
	}

	public void run() {// data저장
		try {
			String pw = mail;
			// MessageDigest : 해당 문자열을 암호화 형태 구성
			// md5,
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(pw.getBytes());
			byte[] se = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte s : se) {
				sb.append(String.format("%02x", s));
				// (%02x)2자리 문자로 변환 : 0 숫자로 넣어 2자리 제작(01,02,03,04)
				// (%01x)1자리 문자로 변환 : 1자리 출력(1,2,3,4)
			}
			// db에 저장할때 pw자리에 sb를 저장
			String sql = "insert into login values('0',?,?,?,?,?,?,now())";
			con = new dbconfig().info();
			ps = con.prepareStatement(sql);
			ps.setString(1, part);
			ps.setString(2, id);
			ps.setString(3, nick);
			ps.setString(4, sb.toString());
			ps.setString(5, mail);
			ps.setString(6, nick);
			setCall(ps.executeUpdate());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
