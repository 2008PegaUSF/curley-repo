package fightclubdaoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import fightclub.ConnFactory;
import fightclub.beans.MatchBean;
import fightclubdao.MatchDAO;

public class MatchDAOImpl implements MatchDAO {

	public static ConnFactory cf = ConnFactory.getInstance();

	
	@Override
	public ArrayList<MatchBean> getAllMatches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatchBean getMatchById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewMatch(int winnerid) {
		try {
			
			Connection conn = cf.getConnection();
			String sql = "insert into \"matchhistory\" (\"winner\") values (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, winnerid);
			ps.executeUpdate();
			
			System.out.println("Successfully wrote result.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
