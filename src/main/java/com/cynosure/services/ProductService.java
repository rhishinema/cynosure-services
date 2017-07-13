package com.cynosure.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
	public Product createProduct(Product product) {
		product = productRepository.save(product);
		return product;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductbyId(Long productId) {
		return productRepository.findOne(productId);
	}

	public List<Product> getTrendingProducts() {
		List<Product> trendingProducts = new ArrayList<Product>();

		trendingProducts = productRepository.findByStockGreaterThan(10);
		if (CollectionUtils.isNotEmpty(trendingProducts)) {
			if (trendingProducts.size() > 3) {
				return trendingProducts.subList(0, 3);
			} else {
				return trendingProducts;
			}
		}

		return new ArrayList<Product>();
	}
}
