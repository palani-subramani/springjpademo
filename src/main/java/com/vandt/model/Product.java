package com.vandt.model;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "CLOB")
	private String description;

	@OneToMany(mappedBy = "product")
	@MapKey(name = "currency")
	private Map<Currency, Price> prices = new HashMap<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<Currency, Price> getPrices() {
		return prices;
	}

	public void setPrices(Map<Currency, Price> prices) {
		this.prices = prices;
	}
}
