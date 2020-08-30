package fightclubdao;

import java.util.List;

import fightclub.beans.FighterBean;

public interface FighterDAO {
	public List<FighterBean> getAllFighers();
	public FighterBean getFighterById( int id);
}
