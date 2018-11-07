package com.vandt.controllers;

import com.vandt.data.ProductData;
import com.vandt.data.CurrencyData;
import com.vandt.data.CurrencyData.CurrencyCode;
import com.vandt.model.Product;
import com.vandt.service.DataMapperService;
import com.vandt.service.ProductService;
import com.vandt.validator.ProductDataValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerUnitTest
{
    private static final String CURRENCYLOCALE = "currencylocale";

    @InjectMocks
    ProductController cut;

    @Mock
    private ProductService mockProductService;

    @Mock
    private DataMapperService mockDataMapperService;

    @Mock
    private HttpServletRequest mockHttpServletRequest;

    @Mock
    private HttpSession mockHttpSession;

    @Mock
    private Model mockModel;

    @Mock
    private Product mockProduct;

    @Mock
    private ProductData mockProductData;

    @Mock
    private BindingResult mockBindingResult;

    @Mock
    private Iterable<Product> mockProductIterable;

    @Mock
    private ArrayList<ProductData> mockProductDataList;

    @Mock
    private ProductDataValidator mockProductDataValidator;

    private CurrencyData currencyData;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        BDDMockito.given(mockHttpServletRequest.getSession()).willReturn(mockHttpSession);
        BDDMockito.given(mockProductService.getProductForId(anyLong())).willReturn(mockProduct);
    }

    @Test
    public void testListProductsCallsProductServiceGetProducts()
    {
        //Given - product controller accepts /product/list request

        // When
        cut.listProducts(mockHttpServletRequest, mockModel);

        // Then
        verify(mockProductService, times(1)).getProducts();
    }

    @Test
    public void testListProductsExecutedForFirstTimeWithoutSessionCurrency()
    {
        // Given
        BDDMockito.given(mockHttpSession.getAttribute(CURRENCYLOCALE)).willReturn(null);

        // When
        cut.listProducts(mockHttpServletRequest, mockModel);

        // Then
        verify(mockHttpSession, times(1)).setAttribute(any(),any());
        verify(mockProductService, times(1)).getProducts();
    }

    @Test
    public void testListProductsExecutedWithSessionCurrencySet()
    {
        // Given
        BDDMockito.given(mockHttpSession.getAttribute(CURRENCYLOCALE)).willReturn("GBP");

        // When
        cut.listProducts(mockHttpServletRequest, mockModel);

        // Then
        verify(mockHttpSession, times(0)).setAttribute(any(),any());
        verify(mockProductService, times(1)).getProducts();
    }

    @Test
    public void testGetProductById()
    {
        // Given
        BDDMockito.given(mockHttpSession.getAttribute(CURRENCYLOCALE)).willReturn("GBP");

        // When
        cut.getProductById(1L, mockHttpServletRequest, mockModel);

        // Then
        verify(mockProductService, times(1)).getProductForId(1L);
    }

    @Test
    public void testModifyCurrencyExecutedForFirstTimeWithoutSessionCurrency()
    {
        // Given
        BDDMockito.given(mockHttpSession.getAttribute(CURRENCYLOCALE)).willReturn(null);
        currencyData = new CurrencyData();
        currencyData.setCurrencyCode(CurrencyCode.GBP);

        // When
        cut.modifySessionCurrency(currencyData, mockHttpServletRequest, mockBindingResult);

        // Then
        verify(mockHttpSession, times(1)).setAttribute(any(),any());
    }

    @Test
    public void testModifyCurrencyExecutedWithSessionCurrencySet()
    {
        // Given
        BDDMockito.given(mockHttpSession.getAttribute(CURRENCYLOCALE)).willReturn(null);
        currencyData = new CurrencyData();
        currencyData.setCurrencyCode(CurrencyCode.EUR);

        // When
        cut.modifySessionCurrency(currencyData, mockHttpServletRequest, mockBindingResult);

        // Then (set session Currency will be called whether something is already present or not)
        verify(mockHttpSession, times(1)).setAttribute(any(),any());
    }

    @Test
    public void testListAllProductData()
    {
        // Given - product controller accepts /product/list request

        cut.listAllProductData();

        verify(mockProductService, times(1)).getProducts();
        verify(mockDataMapperService , times(1)).mapProductsToProductDataList(anyCollection());
    }

    @Test
    public void testEditProduct()
    {
        // Given - go to edit product page

        // When
        cut.editProduct(1L, mockHttpServletRequest, mockModel);

        // Then
        verify(mockProductService, times(1)).getProductForId(1L);
        verify(mockDataMapperService, times(1)).mapProductToProductData(mockProduct);
    }

    @Test
    public void testUpdateProductWhenErrorsInForm()
    {
        // Given - go to edit product page
        BDDMockito.given(mockBindingResult.hasErrors()).willReturn(true);

        // When
        cut.updateProduct(mockProductData, mockBindingResult);

        // Then
        verify(mockProductService, times(0)).getProductForId(anyLong());
        verify(mockProductService, times(0)).save(mockProduct);
    }

    @Test
    public void testUpdateProductWhenNoErrorsInForm()
    {
        // Given - go to edit product page
        BDDMockito.given(mockBindingResult.hasErrors()).willReturn(false);

        // When
        cut.updateProduct(mockProductData, mockBindingResult);

        // Then
        verify(mockProductService, times(1)).getProductForId(anyLong());
        verify(mockProductService, times(1)).save(mockProduct);
    }
}