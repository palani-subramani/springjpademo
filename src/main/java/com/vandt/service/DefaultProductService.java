package com.vandt.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.vandt.model.Product;

@Component("productService")
@Transactional
public class DefaultProductService implements ProductService {

	private final ProductRepository productRepository;

	public DefaultProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Iterable<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductForId(Long id) {
		Assert.notNull(id, "Id must not be null");
		return productRepository.findById(id);
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

}
