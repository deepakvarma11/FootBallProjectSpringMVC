package com.mindtree.entity;

public class Teams {
	private String teamcity;
	private String teamName;
	
	public String getTeamcity() {
		return teamcity;
	}
	public void setTeamcity(String teamcity) {
		this.teamcity = teamcity;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Teams(String teamcity, String teamName) {
		super();
		this.teamcity = teamcity;
		this.teamName = teamName;
	}
	public Teams() {
		super();
	}

}
