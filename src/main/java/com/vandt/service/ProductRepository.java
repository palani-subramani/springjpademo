package com.vandt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.vandt.model.Product;

interface ProductRepository extends Repository<Product, Long> {

	Iterable<Product> findAll();

	Page<Product> findAll(Pageable pageable);

	Product findById(Long id);

	Product save(Product product);
}
