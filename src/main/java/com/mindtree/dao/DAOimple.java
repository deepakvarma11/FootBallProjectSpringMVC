package com.mindtree.dao;

import java.text.ParseException;
import java.util.ArrayList;

import com.mindtree.entity.Teams;
import com.mindtree.exception.FetchException;
import com.mindtree.exception.InvalidInputException;

public interface DAOimple {
	public void team_details(String name) throws FetchException;
	public ArrayList<Teams> DisplayTeams();
	public void addMatchDetails(String date, String first,String Second,int fs,int ss) throws ParseException, InvalidInputException;
}
