package com.example.demo;

import java.util.ArrayList;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.User;

@Service
public class servise {
	public String spel = "!@#$%^&*({";
	public String case_1 = "QWERTYUIOPASDFGHJKLZXCVBNM";
	@Autowired
	Repository user_rep;
	@Autowired
	rep2 user_rep2;
	@Autowired
	JWT_UTILS jwt;
	@Autowired
	product_time_rep produckt;

	public boolean profile(String name, String surname, String adres, String token) {

		if (jwt.validateToken(token) == true) {
			String login = jwt.getUsernameFromToken(token);
			UserEntity e = user_rep.findByLogin(login);
			e.addres = adres;
			e.name = name;
			e.surname = surname;
			user_rep.save(e);

			return true;
		}
		return false;

	}

	public String add_product(String name, String price, String description, String image, String token) {
		if (name != null && price != null && description != null && image != null && jwt.validateToken(token) == true) {
			String login = jwt.getUsernameFromToken(token);

			time_product_entity entity = new time_product_entity();
			entity.name = name;
			entity.price = price;
			entity.description = description;
			entity.image = image;
			entity.login = login;
			produckt.save(entity);
			return "OK";
		}
		return "not save";

	}

	public String message(String email, String name, String message) {
		if (email != null && name != null && message != null) {
			user_entity2 entity = new user_entity2();
			entity.setEmail(email);
			entity.setName(name);
			entity.setMassage(message);
			user_rep2.save(entity);
			return "OK";
		}
		return "not OK";

	}

	public String register_user(String login, String password) {
		String token = jwt.generalToken(login);
		if (password(password) == true) {
			return "Пароль слишком слабый";
		}
		UserEntity obj = user_rep.findByLogin(login);

		if (obj == null) {
			obj = new UserEntity();
			obj.setLogin(login);
			obj.setPassword(password);

			user_rep.save(obj);
			return token;
		} else {
			return "Логин уже занят";
		}

	}

	public String login(User user) {
		UserEntity finded_user = user_rep.findByLogin(user.getLogin());
		String token = jwt.generalToken(user.getLogin());

		if (finded_user == null) {
			return "логин занят";
		} else {
			if (user.getPassword().equals(finded_user.getPassword())) {
				return token;
			} else {
				return "Неверный логин или пароль";
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
					tgb = true;
				}
		}
		if (tgb != true) {
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

	public profile8 loadprofile(String token) {
		String login = jwt.getUsernameFromToken(token);
		user_rep.findByLogin(login);
		UserEntity g = user_rep.findByLogin(login);
		profile8 qwe = new profile8();
		qwe.name = g.name;
		qwe.surname = g.surname;
		qwe.addres = g.addres;

		return qwe;
	}

	public String check_admin(String token) {
		String login = jwt.getUsernameFromToken(token);
		UserEntity user = user_rep.findByLogin(login);

		System.out.println(login);
		if (user.role.equals("admin")) {
			return "admin";
		} else {
			return "not admin";
		}

	}

}
