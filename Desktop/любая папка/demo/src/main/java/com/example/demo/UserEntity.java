package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String login;
	private String password;
	public String name;
	public String surname;
	public String addres;
	public String role ="user";

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

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}
	

}
