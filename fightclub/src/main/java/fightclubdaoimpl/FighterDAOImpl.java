package fightclubdaoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fightclub.ConnFactory;
import fightclub.beans.FighterBean;
import fightclubdao.FighterDAO;

public class FighterDAOImpl implements FighterDAO {

	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public List<FighterBean> getAllFighers() {

		List<FighterBean> albumList = new ArrayList<FighterBean>();
		try {
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from \"fighters\"");
			FighterBean a = null;
			while (rs.next()) {
				a = new FighterBean(rs.getInt(1), rs.getString(2));
				albumList.add(a);
			}

			return albumList;
		} catch (SQLException e) {
			System.out.println("How'd we get here?");
		}
		return null;
	}

	@Override
	public FighterBean getFighterById(int id) {
		try {
			FighterBean fighter = null;
			Connection conn = cf.getConnection();
			String sql = "select * from \"fighters\" where \"fighterId\" = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				fighter = new FighterBean(rs.getInt(1), rs.getString(2));

			}
			return fighter;

		} catch (SQLException e) {
		}
		return null;
	}
}
