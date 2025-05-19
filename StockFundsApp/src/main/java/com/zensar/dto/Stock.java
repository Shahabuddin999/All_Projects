package com.zensar.dto;

public class Stock {

	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String stockName;
	private double price;
	public Stock() {}
	public Stock(String stockName, double price) {
		super();
		this.stockName = stockName;
		this.price = price;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Stock [id=" + id + ", stockName=" + stockName + ", price=" + price + "]";
	}
	
	
}
