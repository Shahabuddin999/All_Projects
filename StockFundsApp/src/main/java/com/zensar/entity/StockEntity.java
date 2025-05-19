package com.zensar.entity;

public class StockEntity {

	private int stockId;
	private String stockName;
	private String market;
	private double price;
	
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
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
	@Override
	public String toString() {
		return "StockEntity [stockId=" + stockId + ", stockName=" + stockName + ", market=" + market + ", price="
				+ price + "]";
	}
	
	
	
}
