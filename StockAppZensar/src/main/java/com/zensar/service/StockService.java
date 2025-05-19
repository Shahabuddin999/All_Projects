package com.zensar.service;

import java.util.List;

import com.zensar.Entity.StockEntity;
import com.zensar.dto.Stock;
public interface StockService {
	List<Stock> getStocks();
	Stock getStockById(int id);
	Stock createStock(Stock stock);
	Stock updateStock(Stock updatedStock, int stockId);
	boolean deleteAllStock();
	boolean deleteByStockId(int stockId);
	boolean getAccount(int id,String authData);
	public List<Stock> getStocksByName(String stockName);
	public List<Stock> getStocksSortedByName(String sortType);
	public List<Stock> getStocksByPage(int startIndex, int records);
	public List<Stock> getStocksByNameLike(String namelike);
}
