package com.mindtree.utility;

import java.sql.*;

public class Utility {
	public Connection connect() {
		Connection con = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/football_match_db","root","Welcome123"); 
			if (con == null) {
	            System.out.println("Connection cannot be established");
	        }return con;
			}
			catch(Exception e)
			{ 
				 System.out.println(e);
			}
		return null;
	}
public static void main(String[] args) {
	Utility u = new Utility();
	u.connect();
}
}
