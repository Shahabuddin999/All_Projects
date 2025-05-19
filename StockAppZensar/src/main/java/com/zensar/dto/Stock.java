package com.zensar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Stock DTO")
public class Stock {
	@ApiModelProperty(value="Stock Identifier")
	private int id;
	@ApiModelProperty(value="Stock Name")
	private String name;
	@ApiModelProperty(value="Stock Market")
	private String market;
	@ApiModelProperty(value="Stock Price")
	private double price;
	
	public Stock( int id, String name, String market, double price) {
		super();
		this.name = name;
		this.market = market;
		this.price = price;
		this.id = id;
	}

	public Stock(String name, String market, double price) {
		super();
		this.name = name;
		this.market = market;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Stock() {
		super();
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", market=" + market + ", price=" + price + "]";
	}
	
}
