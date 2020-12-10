package com.mindtree.entity;

public class Response {
	private Object responsemessage;

	public Object getResponsemessage() {
		return responsemessage;
	}

	public void setResponsemessage(Object responsemessage) {
		this.responsemessage = responsemessage;
	}

	public Response(Object responsemessage) {
		super();
		this.responsemessage = responsemessage;
	}

}