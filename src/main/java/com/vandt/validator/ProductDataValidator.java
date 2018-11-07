package com.vandt.validator;

import com.vandt.data.PriceData;
import com.vandt.data.ProductData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Currency;

/**
 * ProductData validator
 */
@Component
public class ProductDataValidator implements Validator
{

    @Override
    public boolean supports(Class<?> clazz)
    {
        return ProductData.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        ProductData productData = (ProductData) target;
        if(!isValidName(productData.getName()))
        {
            errors.rejectValue("name", "invalid.product.name");
        }

        int counter = 0;
        for(PriceData priceData: productData.getPrices())
        {
            if(!isValidCurrency(priceData.getCurrency()))
            {
                errors.rejectValue("prices[" + counter + "].currency", "invalid.price.currency");
            }

            if(!isValidAmount(priceData.getAmount()))
            {
                errors.rejectValue("prices[" + counter + "].amount", "invalid.price.amount");
            }
            ++counter;
        }
    }

    public boolean isValidName(final String name)
    {
        try
        {
            return !name.isEmpty() && !(name.length() > 50);
        }
        catch (Exception e)
        {
            // ignore and return false
            return false;
        }
    }

    public boolean isValidCurrency(final String currencyCode)
    {
        try
        {
            Currency.getInstance(currencyCode);
        }
        catch (Exception e)
        {
            // ignore and return false
            return false;
        }
        return true;
    }

    public boolean isValidAmount(final String amount)
    {
        try
        {
            return amount.matches("\\d+(\\.\\d+)?") && !amount.matches("[0]");  //match a number with optional '-' and decimal.
        }
        catch (Exception e)
        {
            // ignore and return false
            return false;
        }
    }
}
