package com.cynosure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cynosure.pojo.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
