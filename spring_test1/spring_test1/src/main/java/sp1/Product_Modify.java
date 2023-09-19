package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Product_Modify {
	Connection conn = null;
	PreparedStatement ps = null;

	private void dbconn() {
		try {
			this.conn = dbconfig.info();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected ArrayList<String> view_ok(String idx) {
		ArrayList<String> list1 = new ArrayList<>();
		
		try {
			dbconn();
			String sql = "select * from product where pidx=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			ResultSet rs = ps.executeQuery();
			rs.next();
//			dto_product dp = new dto_product();
			list1.add(rs.getString("pidx"));
			list1.add(rs.getString("pcode"));
			list1.add(rs.getString("pname"));
			list1.add(rs.getString("pmoney"));
			list1.add(rs.getString("pimg"));
			list1.add(rs.getString("psale"));
			list1.add(rs.getString("puse"));
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list1;
	}
}
