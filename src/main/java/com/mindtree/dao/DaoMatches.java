package com.mindtree.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mindtree.utility.*;

//import ch.qos.logback.classic.pattern.SyslogStartConverter;

import com.mindtree.entity.*;
import com.mindtree.exception.FetchException;
import com.mindtree.exception.InvalidInputException;
public class DaoMatches implements DaoMatch{
	static Utility u = new Utility();
	Connection con= u.connect();
	private Connection con1;
	private java.sql.Statement   statement;
	
    Statement query = null;
    String q;
    
    public String addMatchDetails(Match match) throws FetchException, Exception
    {
    	
	  try {	    	  
	   
	   PreparedStatement stmt=con.prepareStatement("insert into matches(match_date,first_team_name,second_team_name,first_team_goals,second_team_goals) values(?,?,?,?,?)");
	   stmt.setString(1,match.getMatchdate());
	   stmt.setString(2,match.getFirstTeam());
	   stmt.setString(3,match.getSecondteam());
	   stmt.setInt(4,match.getFirstTeamScore());
	   stmt.setInt(5,match.getSecondTeamScore());
	   int i=stmt.executeUpdate();  
	   System.out.println(i+" records inserted");
	   
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      return "Added";
	}
    
    public ArrayList<Match> displayMatches(String teamName){
    	ArrayList<Match> matches = new ArrayList<Match>();
    	try {
    		query = con.createStatement();
    		q ="select * from matches where First_Team_Name='"+teamName+"' or Second_Team_Name='"+teamName+"'";
    		ResultSet rs= query.executeQuery(q);
    		while(rs.next())
    		{
    			matches.add(new Match(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
    		}
//    		con.close();
    	}catch(SQLException e) {
    		System.out.println(e);
    	}
    	return matches;
    }
    
    public boolean search(String name) throws SQLException
    {
    	query=con.createStatement();
		ResultSet rs=query.executeQuery("select * from teams where TEAM_NAME ='"+name+"'");
		return rs.next();
    }
    public int DuplicateValue(String date,String first,String sec) throws ClassNotFoundException, SQLException {
    	int flag=0;
    
    	
    		Class.forName("com.mysql.jdbc.Driver");
    	    try {
				con1 =DriverManager.getConnection("jdbc:mysql://localhost:3306/football_match_db", "root", "Welcome123");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    statement=con1.createStatement();
    	    
    		String query1= "select * from matches where MATCH_DATE= '"+date+"' and FIRST_TEAM_NAME =' "+first+"' AND SECOND_TEAM_NAME='"+sec+"'";
    		PreparedStatement st=con1.prepareStatement(query1);
    		ResultSet rs= st.executeQuery(query1);
    		int count =0;
    		
    		while(rs.next()) {
    			count++;
    		}
    		System.out.println(count);
    		if(count>0) {
    			flag=1;    			
    		}

    		return flag;
    	
    
    }
    	

}

