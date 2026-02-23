package com.mindtree.utility;

import java.sql.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Utility {
	
	@Value("${spring.datasource.url:jdbc:mysql://localhost:3306/football_match_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC}")
	private String dbUrl;
	
	@Value("${spring.datasource.username:root}")
	private String dbUsername;
	
	@Value("${spring.datasource.password:Welcome123}")
	private String dbPassword;
	
	public Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
			if (con == null) {
	            System.out.println("Connection cannot be established");
	        }
			return con;
		}
		catch(Exception e) { 
			System.out.println(e);
		}
		return null;
	}
	
public static void main(String[] args) {
	Utility u = new Utility();
	u.connect();
}
}
