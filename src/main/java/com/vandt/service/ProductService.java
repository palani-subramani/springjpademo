package com.vandt.service;

import com.vandt.model.Product;

public interface ProductService {

	Iterable<Product> getProducts();
	Product getProductForId(Long id);
	Product save(Product product);
}
