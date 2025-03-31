package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface  product_time_rep extends JpaRepository<time_product_entity,Long> {
  
}
