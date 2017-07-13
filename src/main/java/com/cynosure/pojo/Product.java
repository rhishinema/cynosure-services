package com.cynosure.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name="t_product_details", schema="[cynosure-schema]")
public class Product {

	@Id
	@GeneratedValue
	@Column(name="PRODUCT_ID")
	private Long productId;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="PRODUCT_DETAIL")
	private String productDetail;
	
	@Column(name="PRICE")
	private Long price;
	
	@Column(name="STORE_NAME")
	private String storeName;
	
	@Column(name="STOCK")
	private Long stock;
	
	@Column(name="product_image")
	private String productImage;
	
	@Transient
	private String productImageFullUrl;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductImageFullUrl() {
		if(StringUtils.isEmpty(productImage)){
			return "";
		}else{
			return "https://cynosureblob.blob.core.windows.net/cynosure-movies/" + productImage;
		}
	}

	public void setProductImageFullUrl(String productImageFullUrl) {
		this.productImageFullUrl = productImageFullUrl;
	}
}
