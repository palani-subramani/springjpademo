package com.vandt.service;

import com.vandt.data.PriceData;
import com.vandt.data.ProductData;
import com.vandt.model.Price;
import com.vandt.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DataMapperServiceUnitTest
{
    @InjectMocks
    private DataMapperService cut;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapProductsToProductDataList()
    {
        // Given
        Iterable<Product> products = Arrays.asList(setupProduct());

        // When
        List<ProductData> productDataList = cut.mapProductsToProductDataList(products);

        // Then
        assertEquals(2, productDataList.size());
        assertEquals("test1", productDataList.get(0).getName());
        assertEquals("test2", productDataList.get(1).getName());
        assertEquals("2.25", productDataList.get(0).getPrices().get(0).getAmount());
        assertEquals("8.95", productDataList.get(1).getPrices().get(1).getAmount());
    }

    @Test
    public void testMapProductDataToProduct()
    {
        // Given
        Iterable<Product> products = Arrays.asList(setupProduct());
        Product product = products.iterator().next();
        ProductData productData = setupProductData();

        // When
        cut.mapProductDataToProduct(productData, product);

        // Then
        assertEquals("newName", product.getName());
        BigDecimal priceAmount = new BigDecimal("9999.99");
        Currency currency = Currency.getInstance("GBP");
        assertEquals(priceAmount, product.getPrices().get(currency).getAmount());
    }

    private Product[] setupProduct() {
        Product product1 = new Product();
        product1.setName("test1");
        Map<Currency, Price> prices1 = new HashMap<>();
        Price price11 = new Price();
        price11.setAmount(new BigDecimal("2.25"));
        Currency currency11 = Currency.getInstance("GBP");
        price11.setCurrency(currency11);
        prices1.put(currency11, price11);
        Price price12 = new Price();
        price12.setAmount(new BigDecimal("3.45"));
        Currency currency12 = Currency.getInstance("EUR");
        price12.setCurrency(currency12);
        prices1.put(currency12, price12);
        product1.setPrices(prices1);

        Product product2 = new Product();
        product2.setName("test2");
        Map<Currency, Price> prices2 = new HashMap<>();
        Price price21 = new Price();
        price21.setAmount(new BigDecimal("7.80"));
        Currency currency21 = Currency.getInstance("GBP");
        price21.setCurrency(currency21);
        prices2.put(currency21, price21);
        Price price22 = new Price();
        price22.setAmount(new BigDecimal("8.95"));
        Currency currency22 = Currency.getInstance("EUR");
        price22.setCurrency(currency22);
        prices2.put(currency22, price22);
        product2.setPrices(prices2);

        Product[] productArray = new Product[2];
        productArray[0] = product1;
        productArray[1] = product2;
        return productArray;
    }

    private ProductData setupProductData()
    {
        ProductData productData = new ProductData();
        productData.setName("newName");
        productData.setDescription("something new");
        PriceData priceData = new PriceData();
        priceData.setAmount("9999.99");
        priceData.setCurrency("GBP");
        productData.setPrices(Arrays.asList(priceData));
        return productData;
    }
}