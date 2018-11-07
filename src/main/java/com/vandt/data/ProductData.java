package com.vandt.data;

import java.util.List;

/**
 * Product POJO : has one to many relation with PriceData
 */
public class ProductData
{
    private Long id;
    private String name;
    private String description;
    private List<PriceData> prices;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<PriceData> getPrices()
    {
        return prices;
    }

    public void setPrices(List<PriceData> prices)
    {
        this.prices = prices;
    }
}
