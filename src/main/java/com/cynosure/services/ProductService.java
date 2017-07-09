package com.cynosure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cynosure.dao.ProductRepository;
import com.cynosure.pojo.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public Product createProduct(Product product){
		product = productRepository.save(product);
		return product;
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product getProductbyId(Long productId){
		return productRepository.findOne(productId);
	}
}
