package com.vandt.data;

/**
 * CurrencyData to set session currency
 */
public class CurrencyData
{
    private CurrencyCode currencyCode;

    public enum CurrencyCode
    {
        GBP, EUR
    }

    public CurrencyCode getCurrencyCode()
    {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode)
    {
        this.currencyCode = currencyCode;
    }
}
