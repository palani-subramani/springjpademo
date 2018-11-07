package com.vandt.service;

import com.vandt.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductServiceUnitTest
{
    @InjectMocks
    private DefaultProductService cut;

    @Mock
    private ProductRepository mockProductRepository;

    @Before
    public void setUp()
    {
        cut = new DefaultProductService(mockProductRepository);
    }

    @After
    public void tearDown()
    {
        cut = null;
    }

    @Test
    public void testSaveProductCallsProductRepositorySave()
    {
        Product product = new Product();
        product.setId(1234L);
        product.setName("testPerfectProduct1");

        cut.save(product);
        verify(mockProductRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void testGetProductForIdCallsProductRepositoryFindById()
    {
        cut.getProductForId(1234L);
        verify(mockProductRepository, Mockito.times(1)).findById(any());
    }

    @Test
    public void testGetProductsCallsProductRepositoryFindAll()
    {
        cut.getProducts();
        verify(mockProductRepository, Mockito.times(1)).findAll();
    }
}