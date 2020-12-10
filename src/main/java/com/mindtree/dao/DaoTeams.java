package com.mindtree.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mindtree.entity.Teams;
import com.mindtree.utility.Utility;
public class DaoTeams{
	static Utility u = new Utility();
	static Connection con= u.connect();
    static Statement query = null;
	 public ArrayList<Teams> DisplayTeams() {
		 ArrayList<Teams> teams = new ArrayList<Teams>();
			try {
				//u.connect();
			query=con.createStatement();
			ResultSet rs=query.executeQuery("select * from teams");  
			while(rs.next()) {
				teams.add(new Teams(rs.getString(2),rs.getString(1)));
			}
			}catch(Exception e1) {
				System.out.println(e1);
			}
			return teams;
			
		}
	 public String AddTeams(Teams Team) throws SQLException {
		 PreparedStatement stmt=con.prepareStatement("insert into teams values(?,?)");
		 stmt.setString(1,Team.getTeamName());
		 stmt.setString(2,Team.getTeamcity());
		 int i=stmt.executeUpdate();  
		 System.out.println(i+" records inserted");
		return "Added";
		 
	 }
	 
//	 
}
