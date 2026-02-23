package com.mindtree.utility;

import java.sql.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Utility {
	
	private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/football_match_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String DEFAULT_DB_USERNAME = "root";
	private static final String DEFAULT_DB_PASSWORD = "Welcome123";
	
	@Value("${spring.datasource.url:" + DEFAULT_DB_URL + "}")
	private String dbUrl;
	
	@Value("${spring.datasource.username:" + DEFAULT_DB_USERNAME + "}")
	private String dbUsername;
	
	@Value("${spring.datasource.password:" + DEFAULT_DB_PASSWORD + "}")
	private String dbPassword;
	
	public Connection connect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 
			if (con == null) {
	            System.out.println("Database connection could not be established");
	        }
			return con;
		}
		catch(Exception e) { 
			System.err.println("Error connecting to database: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
public static void main(String[] args) {
	Utility u = new Utility();
	u.connect();
}
}
