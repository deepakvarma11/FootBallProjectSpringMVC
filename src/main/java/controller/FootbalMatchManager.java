package controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.utility.*;
import com.mindtree.dao.*;
import com.mindtree.entity.*;
import com.mindtree.exception.*;

@RestController
@Component
@CrossOrigin("*")
public class FootbalMatchManager{
	Utility u = new Utility();
	DaoTeams dt =new  DaoTeams();
	DaoMatches dm = new DaoMatches();
	Scanner s = new Scanner(System.in);
	
	@ResponseBody
	@RequestMapping(value="/Team")
	public ArrayList<Teams> Display() 
	{
		ArrayList<Teams> list= dt.DisplayTeams();
		for(Teams t: list)
		{
			System.out.println(t.getTeamcity()+"\t"+t.getTeamName());
		}
		return list;
				
	}
	@ResponseBody
	@RequestMapping(value="/addMatch",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Object addMatch(@RequestBody Match match) throws Exception
	{
		try {
		if(match.getFirstTeam().equals(match.getSecondteam()))
		{
			throw new FetchException("Teams should not be equal");
		}
		}
		catch(FetchException e)
		{
			return new Response("Teams should not be equal");
		}
		try {
		if(match.getFirstTeamScore()<0||match.getSecondTeamScore()<0)
		{
			System.out.println("Invalid");
			throw new InvalidInputException("Invalid Input");
		}
		}
		catch(InvalidInputException i)
		{
			return new Response("Invalid Input");
		}
		dm.addMatchDetails(match);
		return new Response("added");
//		String date=match.getMatchdate();
//		String first=match.getFirstTeam();
//		String second=match.getSecondteam();
//		int a=dm.DuplicateValue(date, first, second);
//		try {
//			if(a==1) {
//				return"Match already Exists";	
//			}else {
//				
//			}
//			}catch(InvalidInputException i)
//			{
//				return new Response("Match already Exists");
//			}
		
	}
	
	@RequestMapping(value="/DisplayMatches",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Display> team_details(String name) throws FetchException, InvalidInputException {
		

		ArrayList<Match> list_display =dm.displayMatches(name);
		ArrayList<Display> list_duplicate = new ArrayList<Display>();
		String s=null;
		int difference;
		for(int i =0;i<list_display.size();i++) {
			Match m1 = list_display.get(i);
		
		if(m1.getFirstTeam().equalsIgnoreCase(name))
		{
			s= m1.getSecondteam();
		}
		else //if(m1.getSecondteam().equals(name)) 
		{
			s=m1.getFirstTeam();
		}
		if(m1.getFirstTeamScore()>m1.getSecondTeamScore()) {
			difference = m1.getFirstTeamScore()-m1.getSecondTeamScore();
		}
		else
		{
			difference = m1.getSecondTeamScore()-m1.getFirstTeamScore();
		}
		list_duplicate.add(new Display(m1.getMatchdate(),s,m1.getFirstTeamScore(),m1.getSecondTeamScore(),difference));
		Collections.sort(list_duplicate);
		}
		
		return list_duplicate;
	}
	
	@ResponseBody
	@RequestMapping(value="/addteams",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Object AddTeam(@RequestBody Teams Team) throws SQLException
	{
		dt.AddTeams(Team);
		return new Response("Added");
		
		
	}
}
	


