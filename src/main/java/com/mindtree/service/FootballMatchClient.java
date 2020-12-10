package com.mindtree.service;
import com.mindtree.entity.Display;
import com.mindtree.entity.Match;
import com.mindtree.entity.Teams;
import com.mindtree.exception.*;

import controller.FootbalMatchManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;

public class FootballMatchClient {
	public static void main(String[] args) throws ParseException, InvalidInputException, FetchException  {
		Scanner s = new Scanner(System.in);
//		DAO d = new DAO();
		FootbalMatchManager fb= new FootbalMatchManager();
		Match match = new Match();
		Teams team = new Teams();
		boolean f = true;
		while(f) {
		System.out.println("Enter Choice [1/2/3]");
		int n = 0;
		try{
			n= s.nextInt();
		}catch(InputMismatchException e)
		{
			System.out.println("enter the CHOICE as 1 or 2 or 3 only:");
		}
		s.nextLine();
		switch(n)
		{
		case 1:
//			ArrayList<Teams> teamList1 = 
			fb.Display();
//			for(Teams t: teamList1)
//			{
//				System.out.println(t.getTeamcity()+"\t"+t.getTeamName());
//			}
			System.out.println("Enter the FirstTeamName");
			String first_team_name = s.nextLine();
			match.setFirstTeam(first_team_name);
			
			System.out.println("Enter the SecondTeamName");
			String second_team_name =s.nextLine();
			match.setSecondteam(second_team_name);
			
			System.out.println("Enter the match date in the form of yyyy/mm/dd");
			String date = s.nextLine();
			match.setMatchdate(date);
			
			System.out.println("Enter the first team goals");
			int first_team_goals =s.nextInt();
			match.setFirstTeamScore(first_team_goals);
			
			System.out.println("Enter the second team goals");
			int second_team_goals = s.nextInt();
			match.setSecondTeamScore(second_team_goals);
//			fb.addMatch(match);
			break;
		case 2:
			System.out.println("Enter the team name:");
			String name = s.nextLine();
			ArrayList<Display> match_details =fb.team_details(name);
			for(Display d: match_details)
			{
				System.out.println(d.matchDate+"\t"+d.teamName+"\t"+d.firstTeamScore+"\t"+d.secondTeamScore+"\t"+d.goals);
			}
			//fb.inputTeam();
			break;
		case 3:
			System.out.println("exit....");
			f = false;
		}
	}

	}
}
