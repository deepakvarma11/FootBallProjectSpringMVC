package com.mindtree.entity;

public class Display implements Comparable<Display>{
	public String matchDate;
	public String teamName;
	public int firstTeamScore;
	public int secondTeamScore;
	public int goals;
	public Display(String matchDate,String teamName,int firstTeamScore, int secondTeamScore,int goals)
	{
		super();
		this.matchDate=matchDate;
		this.teamName=teamName;
		this.firstTeamScore= firstTeamScore;
		this.secondTeamScore =secondTeamScore;
		this.goals =goals;
	}
	@Override
	public int compareTo(Display d) {
		
		return this.goals-d.goals;
	}
	
	
}
