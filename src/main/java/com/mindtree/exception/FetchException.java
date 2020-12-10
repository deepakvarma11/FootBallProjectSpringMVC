package com.mindtree.exception;

public class FetchException extends Exception {
	public FetchException(String string){
		super();
		//System.out.println("Team name Doesnot Exists");
		System.out.println(string);
	}

}
