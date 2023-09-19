package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Product_del {
	Connection conn = null;

	public Product_del() {
		try {
			this.conn = dbconfig.info();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	protected int delete_ok(String idx) throws Exception{
		String sql = "delete from product where pidx=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, idx);
		int oksign = ps.executeUpdate();
		ps.close();
		conn.close();
		return oksign;
	}
}
