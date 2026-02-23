package com.mindtree.utility;

import java.sql.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Utility {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Value("${spring.datasource.username}")
	private String dbUsername;
	
	@Value("${spring.datasource.password}")
	private String dbPassword;
	
	private static String staticDbUrl;
	private static String staticDbUsername;
	private static String staticDbPassword;
	
	@Value("${spring.datasource.url}")
	public void setStaticDbUrl(String url) {
		Utility.staticDbUrl = url;
	}
	
	@Value("${spring.datasource.username}")
	public void setStaticDbUsername(String username) {
		Utility.staticDbUsername = username;
	}
	
	@Value("${spring.datasource.password}")
	public void setStaticDbPassword(String password) {
		Utility.staticDbPassword = password;
	}
	
	public Connection connect() {
		Connection con = null;
		try {
			String url = (staticDbUrl != null) ? staticDbUrl : "jdbc:mysql://localhost:3306/football_match_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
			String username = (staticDbUsername != null) ? staticDbUsername : "root";
			String password = (staticDbPassword != null) ? staticDbPassword : "Welcome123";
			
			con = DriverManager.getConnection(url, username, password); 
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
