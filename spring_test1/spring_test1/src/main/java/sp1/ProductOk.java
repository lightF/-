package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductOk {
	Connection conn = null;
	PreparedStatement ps = null;

	private void dbconn() {
		try {
			this.conn = dbconfig.info();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected int modify_sql(String pidx, String pcode, String pname, String pmoney, String psale, String puse) {
		int sign = 0;
		try {
			dbconn();
			String sql = "update product set pname=?,pmoney=?,psale=?,puse=? where pidx=? and pcode=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pname);
			ps.setString(2, pmoney);
			ps.setString(3, psale);
			ps.setString(4, puse);
			ps.setString(5, pidx);
			ps.setString(6, pcode);
			sign = ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return sign;
	}
}
