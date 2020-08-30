package fightclub.beans;

public class MatchBean {
	private int matchId;
	private String result;
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public MatchBean(int matchId, String result) {
		super();
		this.matchId = matchId;
		this.result = result;
	}
	@Override
	public String toString() {
		return "Match ID: " + matchId + ", Match Result: " + result + "\n";
	}
}
