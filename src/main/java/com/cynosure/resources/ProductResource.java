package com.cynosure.resources;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cynosure.pojo.Product;
import com.cynosure.services.ProductService;
import com.cynosure.view.ErrorView;
import com.cynosure.view.ProductsView;

@RestController
@RequestMapping("/v1/products")
public class ProductResource {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Object> createProdcut(@RequestBody Product product){
		String error = "";
		try{
			product = productService.createProduct(product);
			if(product.getProductId() == null){
				error = "Error while creating product";
				throw new Exception(error);
			}
			return new ResponseEntity<Object>("product created", HttpStatus.ACCEPTED);
		}catch(Exception e){
			if(StringUtils.isEmpty(error)){
				error = "Error while creating product.";
			}
			ErrorView errorView = new ErrorView(error);
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object> getAllProducts(@PathParam("productId") Long productId){
		try{
			if(productId == null || productId == 0){
				List<Product> allProductsList = productService.getAllProducts();
				return new ResponseEntity<Object>(new ProductsView(allProductsList), HttpStatus.ACCEPTED);
			}
			Product product = productService.getProductbyId(productId);
			return new ResponseEntity<Object>(product, HttpStatus.ACCEPTED);
		}catch(Exception e){
			ErrorView errorView = new ErrorView("Error while getting all products");
			return new ResponseEntity<Object>(errorView, HttpStatus.BAD_REQUEST);
		}
	}
}
