package com.Client;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 2150803753401346604L;
	private String user;
	private String pass;
	
	public Usuario(String name, String pass) {
		this.user = name;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}
	
	
}
