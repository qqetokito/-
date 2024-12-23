package com.example.demo;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class servise {
	public String spel = "!@#$%^&*({";
	public String case_1 = "QWERTYUIOPASDFGHJKLZXCVBNM"; 
	@Autowired
	Repository user_rep;

	public String register_user(String login, String password){
		if(password(password)== true) {
			return "Пароль слишком слабый";
		}
	    UserEntity obj = user_rep.findByLogin(login);
	    if(obj == null){
	        obj = new UserEntity();
	        obj.setLogin(login);
	        obj.setPassword(password);
	        user_rep.save(obj);
	        return "Регистрация успешна";
	    }
	    else {
	    	return "Логин уже занят";
	    }
	   
	    
	}
	public String login (User user) {
		UserEntity finded_user =user_rep.findByLogin(user.getLogin());
		if (finded_user == null){
			return"логин занят";
		}
		else {
			if(user.getPassword().equals(finded_user.getPassword())) {
				return"Вход выполнен успешно";
			}
			else {
				return"Неверный логин или пароль";
			}
			
		}
	}

	public boolean password(String pas) {
		if (pas.length() < 8) {
			return true;

		}
		System.out.println("ok1");
		boolean tgb = false;
		boolean yy = false;
		for (int i = 0; i < pas.length(); i++) {
			for (int j = 0; j < case_1.length(); j++)
				if (pas.charAt(i) == case_1.charAt(j)) {
					tgb =true;
				}
		}
		if (tgb !=  true) {
			return true;
		}
		System.out.println("ok2");
		for (int i = 0; i < pas.length(); i++) {
			for (int j = 0; j < spel.length(); j++)
				if (pas.charAt(i) == spel.charAt(j)) {
					yy = true;
				}
		
		}
		try {
			if (yy != true) {
				return true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return false;

	    
	}
}
	
 