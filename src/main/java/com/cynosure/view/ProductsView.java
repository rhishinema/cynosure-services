package com.cynosure.view;

import java.util.List;

import com.cynosure.pojo.Product;

public class ProductsView {

	private List<Product> products;
	
	public ProductsView(List<Product> products){
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
