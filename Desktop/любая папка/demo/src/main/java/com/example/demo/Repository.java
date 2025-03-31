package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<UserEntity, Long> {
     UserEntity findByLogin(String login);

}