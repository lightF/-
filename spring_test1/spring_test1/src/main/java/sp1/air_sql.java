package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class air_sql {
	Connection con = null;
	dbconfig db = new dbconfig();
	String sql = null;
	int msg = 0;	//결과값
	PreparedStatement ps = null;
	protected int insert(String mid,String mname,String mpost, String mtel, String mperson, String totalmoney)throws Exception {
			this.con = this.db.info();
			this.con.setAutoCommit(false); //transaction을 사용합니다.
			System.out.println(msg);
			//트랜지션 구현
		try {
			String count = "select count (air_person), as cnt from air_reserve where mcode=? "+mname+" and mperson => ?";
			this.ps = this.con.prepareStatement(count);
			this.ps.setString(1, mid);
			this.ps.setString(2, mname);
			this.ps.setString(3, mpost);
			this.ps.setString(4, mtel);
			this.ps.setString(5, mperson);
			this.ps.setString(6, totalmoney);
			if(this.msg ==1) {
				System.out.println(msg);
			}
			
			System.out.println(mid);
			System.out.println(mname);
			System.out.println(mpost);
			System.out.println(mtel);
			System.out.println(mperson);
			System.out.println(totalmoney);
			
			ResultSet rs = this.ps.executeQuery();
			if(rs.getString("cnt").equals("1")) {
				this.msg = 1;
				this.sql = "insert into air_person values('0',?,?,?,?,?,?,now())";
			}
			else {
				this.msg = 3; 
				System.out.println("여유좌석없음");
			}
			//this.sql = "insert into air_person values('0',?,?,?,?,?,now())";
			this.con.commit();
		}
		catch(Exception e) {
			System.out.println("에러");
			this.con.rollback(); //실패시 롤백
		}
		this.ps.close();
		this.con.close();
		System.out.println(this.msg);
		return this.msg;
	}
	
}
