package com.vandt.service;

import com.vandt.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ProductRepositoryConfiguration.class})
public class ProductRepositoryIntegrationTest
{
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(final ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Test
    public void testProductSave()
    {
        Product product = new Product();
        product.setName("Test1");

        assertNull(product.getId());
        productRepository.save(product);
        assertNotNull(product.getId());

        Product savedProduct = productRepository.findById(product.getId());

        assertNotNull(savedProduct);
        assertEquals("Test1", savedProduct.getName());
    }

    @Test
    public void testProductFindAll()
    {
        Iterable<Product> products = productRepository.findAll();
        assertNotNull(products);
    }

    @Test
    public void testProductFindById()
    {
        Product product = new Product();
        product.setName("Test2");

        assertNull(product.getId());
        productRepository.save(product);
        assertNotNull(product.getId());

        Long productId = product.getId();

        Product createdProduct = StreamSupport.stream(productRepository.findAll().spliterator(), false).filter(p -> productId.equals(p.getId())).findFirst().orElse(null);

        assertNotNull(createdProduct);
        assertEquals("Test2", createdProduct.getName());

    }
}