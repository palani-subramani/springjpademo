package com.vandt.data;

/**
 * Price POJO : has many to one relation with ProductData
 */
public class PriceData
{
    private String currency;
    private String amount;

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }
}
