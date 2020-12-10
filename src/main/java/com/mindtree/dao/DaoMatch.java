package com.mindtree.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mindtree.entity.Match;
import com.mindtree.exception.FetchException;
import com.mindtree.exception.InvalidInputException;

public interface DaoMatch {
	Object addMatchDetails(Match match) throws InvalidInputException,FetchException, SQLException, Exception;
	ArrayList<Match> displayMatches(String teamName) throws FetchException;

}
