package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@RestController
public class Controller {

    @Autowired
    servise servise;
    @Autowired
    JWT_UTILS jwt;
    @Autowired
    product_time_rep produckt;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = servise.register_user(user.getLogin(), user.getPassword());

        switch (result) {

            case "Пароль слишком слабый":
                return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);

            case "Логин уже занят":
                return new ResponseEntity<>(result, HttpStatus.GONE);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping("/login")

    public ResponseEntity<String> atovierUser(@RequestBody User user) {
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        String result = servise.login(user);
        switch (result) {

            case "Неверный логин или пароль":
                return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);

            case "логин занят":
                return new ResponseEntity<>(result, HttpStatus.GONE);

        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping("/message")
    public ResponseEntity<String> massageUser(@RequestBody user_message message) {
        System.out.println(message.getMessage());

        String result = servise.message(message.getEmail(), message.getName(), message.getMessage());
        if (result.equals("OK")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.GONE);
        }

    }

    @PostMapping("buy")
    public ResponseEntity<String> buy(@RequestBody String token) {
        if (jwt.validateToken(token)) {

            return new ResponseEntity<>("", HttpStatus.OK);
        }

        return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

    }

    @PostMapping("/profile")
    public ResponseEntity<String> profile(@RequestBody profile8 profile) {
        servise.profile(profile.name, profile.surname, profile.addres, profile.token);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

    @GetMapping("loadprofile")
    public ResponseEntity<profile8> loadprofile(@RequestHeader("Authorization") String token) {
        if (!jwt.validateToken(token))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        profile8 profile = servise.loadprofile(token);
        return new ResponseEntity<>(profile, HttpStatus.OK);

    }

    @PostMapping("admin")
    public ResponseEntity<String> admin(@RequestBody String token) {
        if (servise.check_admin(token).equals("admin")) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("addproduct")
    public ResponseEntity<time_product> addproduct(@RequestBody time_product product) {
        if (servise.add_product(product.name, product.price, product.description, product.image, product.token)
                .equals("OK")) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/products")
    public ResponseEntity<List<time_product_entity>> getProducts() {
        List<time_product_entity> products = produckt.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        produckt.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}