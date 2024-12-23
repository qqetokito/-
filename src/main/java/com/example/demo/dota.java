package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dota {

    @Autowired
    servise servise;
    
   
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user
    		) {
    	String result = servise.register_user(user.getLogin(), user.getPassword());
    	

        switch (result) {
            case "Регистрация успешна":
                return new ResponseEntity<>(result, HttpStatus.OK);

            case "Пароль слишком слабый":
                return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);

            case "Логин уже занят":
                return new ResponseEntity<>(result, HttpStatus.GONE);
          
            default:
                return new ResponseEntity<>("Ошибка регистрации", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
  
    @PostMapping("/login")
    
    public ResponseEntity<String> atovierUser (@RequestBody User user) {
 
    	String result = servise.login(user);
    	switch(result){
    	case"Вход выполнен успешно":
    		return new ResponseEntity<>(result, HttpStatus.OK);
    	
    	case"Неверный логин или пароль":
    	 	return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
    	
    	case"логин занят":
    		return new ResponseEntity<>(result, HttpStatus.GONE);

    		
    	
    	 default:
             return new ResponseEntity<>("Ошибка ввхода", HttpStatus.INTERNAL_SERVER_ERROR);

    	
    	
    	}
 
       
          

    }
    
}


