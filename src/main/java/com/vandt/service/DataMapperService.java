package com.vandt.service;

import com.vandt.data.PriceData;
import com.vandt.data.ProductData;
import com.vandt.model.Price;
import com.vandt.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Added as mapper service for simplicity - valid populators and reverse populators should be used.
 */

@Component("dataMapperService")
public class DataMapperService
{
    public List<ProductData> mapProductsToProductDataList(Iterable<Product> products)
    {
        final List<ProductData> productDataList = new ArrayList<>();
        for(Product product : StreamSupport.stream(products.spliterator(), false).collect(Collectors.toList()))
        {
            final ProductData productData = mapProductToProductData(product);
            productDataList.add(productData);
        }
        return productDataList;
    }

    public ProductData mapProductToProductData(Product product)
    {
        final ProductData productData = new ProductData();
        productData.setId(product.getId());
        productData.setDescription(product.getDescription());
        productData.setName(product.getName());
        final List<PriceData> prices = new ArrayList<>();
        for(Price price : product.getPrices().values())
        {
            final PriceData priceData = new PriceData();
            priceData.setCurrency(price.getCurrency().getCurrencyCode());
            priceData.setAmount(price.getAmount().toString());
            prices.add(priceData);
        }
        productData.setPrices(prices);
        return productData;
    }

    public void mapProductDataToProduct(final ProductData productData, final Product product)
    {
        product.setName(productData.getName());
        product.setDescription(productData.getDescription());
        for(PriceData priceData : productData.getPrices())
        {
            final Currency currency = Currency.getInstance(priceData.getCurrency());
            final Price price = product.getPrices().get(currency);
            price.setCurrency(currency);
            price.setAmount(new BigDecimal(priceData.getAmount()));

        }
    }
}
