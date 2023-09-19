package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AirQuery {
	private String sql = "";
	private PreparedStatement ps = null;
	private Connection con = null;

	public int result(String a,String b,String c,String d,String e,String f,String g,String h) {
		int msg = 0;
		try {
			sql = "insert into air_reserve values('0',?,?,?,?,?,?,?,?,now())";
			con = dbconfig.info();
			ps = con.prepareStatement(sql);
			ps.setString(1, a);
			ps.setString(2, b);
			ps.setString(3, c);
			ps.setString(4, d);
			ps.setString(5, e);
			ps.setString(6, f);
			ps.setString(7, g);
			ps.setString(8, h);
			msg = ps.executeUpdate();
			System.out.println(sql);
			ps.close();
			con.close();
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return msg;
	}
}
