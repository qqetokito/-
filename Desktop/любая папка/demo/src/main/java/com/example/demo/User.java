package com.example.demo;

public class User {

	private String login;
	private String password;
	public String name;
	public String surname;
	public String addres;

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setLogin(String l) {
		login = l;
	}

	public void setPassword(String p) {
		password = p;
	}

	public void test() {
		System.out.println(addres);
	}
}