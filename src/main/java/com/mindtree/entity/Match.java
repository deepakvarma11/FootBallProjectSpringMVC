package com.mindtree.entity;
//import com.mindtree.entity.*;
public class Match {
	private String matchdate;
	private int firstTeamScore;
	private int secondTeamScore;
	private String firstTeam;
	private String secondteam;
	
	public String getMatchdate() {
		return matchdate;
	}
	public int getFirstTeamScore() {
		return firstTeamScore;
	}
	public int getSecondTeamScore() {
		return secondTeamScore;
	}
	public String getFirstTeam() {
		return firstTeam;
	}
	public String getSecondteam() {
		return secondteam;
	}
	public void setMatchdate(String matchdate) {
		this.matchdate = matchdate;
	}
	
	public void setFirstTeamScore(int firstTeamScore) {
		this.firstTeamScore = firstTeamScore;
	}
	
	public void setSecondTeamScore(int secondTeamScore) {
		this.secondTeamScore = secondTeamScore;
	}
	
	public void setFirstTeam(String firstTeam) {
		this.firstTeam =firstTeam;
	}
	
	public void setSecondteam(String secondteam) {
		this.secondteam = secondteam;
	}
	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Match(String matchdate, String string, String string2, int i, int j) {
		super();
		this.matchdate = matchdate;
		this.firstTeam = string;
		this.secondteam = string2;
		this.firstTeamScore = i;
		this.secondTeamScore = j;
		
	}

	
}
