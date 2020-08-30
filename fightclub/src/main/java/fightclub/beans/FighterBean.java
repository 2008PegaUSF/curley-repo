package fightclub.beans;

public class FighterBean {
	int fighterId;
	String fighterName;

	public FighterBean(int fighterId, String fighterName) {
		super();
		this.fighterId = fighterId;
		this.fighterName = fighterName;
	}

	public int getFighterId() {
		return fighterId;
	}

	public void setFighterId(int fighterId) {
		this.fighterId = fighterId;
	}

	public String getFighterName() {
		return fighterName;
	}

	public void setFighterName(String fighterName) {
		this.fighterName = fighterName;
	}

	@Override
	public String toString() {
		return fighterId + ": " + fighterName.toUpperCase() + "!!!\n";
	}

}
