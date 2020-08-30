package fightclubdao;

import java.util.List;

import fightclub.beans.MatchBean;

public interface MatchDAO {
	public List<MatchBean> getAllMatches();
	public MatchBean getMatchById(int id);
	public void createNewMatch(int winnerid);
}
