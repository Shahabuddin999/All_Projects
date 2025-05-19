package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.Entity.StockEntity;
import com.zensar.dto.Stock;
import com.zensar.exception.InvalidStockIdException;
import com.zensar.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public List<Stock> getStocks() {
		List<StockEntity> list = stockRepository.findAll();
		List<Stock> stock = new ArrayList<Stock>();
		for(StockEntity stk : list) {
			stock.add(this.convertStockEntityIntoStockDto(stk));
		}
		return stock;
	}

	@Override
	public Stock getStockById(int stockId) {
		Optional<StockEntity> stockEntity =  stockRepository.findById(stockId);
		if(stockEntity.isPresent()) {
			Stock stock = convertStockEntityIntoStockDto(stockEntity.get());
			return stock;
		}
		throw new InvalidStockIdException(""+stockId);
	}

	@Override
	public Stock createStock(Stock stock) {
		StockEntity stockEntity = convertStockDtoIntoStockEntity(stock);
		stockEntity = stockRepository.save(stockEntity);
		stock = convertStockEntityIntoStockDto(stockEntity);
		return stock;
	}

	@Override
	public Stock updateStock(Stock updatedStock, int stockId) {
		Optional<StockEntity> stockEntity = stockRepository.findById(stockId);
		if(stockEntity.isPresent()) {
			StockEntity stockEntityObject = this.convertStockDtoIntoStockEntity(updatedStock);
			stockEntityObject.setId(stockId);
			stockEntityObject = stockRepository.save(stockEntityObject);
			updatedStock = this.convertStockEntityIntoStockDto(stockEntityObject);
		} 
		return updatedStock;
	}

	@Override
	public boolean deleteAllStock() {
		stockRepository.deleteAll();
		return true;
	}

	@Override
	public boolean deleteByStockId(int stockId) {
		Optional<StockEntity> stockEntity =  stockRepository.findById(stockId);
		if(stockEntity.isPresent()) {
			stockRepository.delete(stockEntity.get());
			return true;
		} else {
			throw new InvalidStockIdException(""+stockId);
		}
	}

	@Override
	public boolean getAccount(int id, String authData) {
		return true;
	}
	
	private Stock convertStockEntityIntoStockDto(StockEntity stockEntity) {
		TypeMap<StockEntity, Stock> typeMap = this.modelMapper.typeMap(StockEntity.class,Stock.class);
		typeMap.addMappings(mapper->{
			mapper.map(source->source.getMarketName(),Stock::setMarket);
		});
		
		Stock stock = this.modelMapper.map(stockEntity, Stock.class);
		return stock;
	}
	
	private StockEntity convertStockDtoIntoStockEntity(Stock stock) {
		TypeMap<Stock,StockEntity> typeMap = this.modelMapper.typeMap(Stock.class,StockEntity.class);
		typeMap.addMappings(mapper->{
			mapper.map(source->source.getMarket(),StockEntity::setMarketName);
		});
		
		StockEntity stockEntity = this.modelMapper.map(stock, StockEntity.class);
		return stockEntity;
	}

	@Override
	public List<Stock> getStocksByName(String stockName) {
		List<StockEntity> entities = stockRepository.findByName(stockName);
		List<Stock> stock = new ArrayList<Stock>();
		for(StockEntity entity : entities) {
			stock.add(convertStockEntityIntoStockDto(entity));
		}
		return stock;
	}

	@Override
	public List<Stock> getStocksSortedByName(String sortType) {
		Sort.Order order = null;
		if(sortType.equalsIgnoreCase("ASC")) {
			order = new Sort.Order(Sort.Direction.ASC, "name");
		} else {
			order = new Sort.Order(Sort.Direction.DESC, "name");
		}
		Sort sort = Sort.by(order);
		List<StockEntity> entities = stockRepository.findAll(sort);
		List<StockEntity> sortByCustomeFunction = stockRepository.findByOrderByNameDesc();
		
		List<Stock> stock = new ArrayList<Stock>();
		for(StockEntity entity : entities) {
			stock.add(convertStockEntityIntoStockDto(entity));
		}
		return stock;
	}

	@Override
	public List<Stock> getStocksByPage(int startIndex, int records) {
		Pageable myPageble = PageRequest.of(startIndex, records);
		List<StockEntity> entities = stockRepository.findAll(myPageble).getContent();
		List<Stock> stock = new ArrayList<Stock>();
		for(StockEntity entity : entities) {
			stock.add(convertStockEntityIntoStockDto(entity));
		}
		return stock;
	}

	@Override
	public List<Stock> getStocksByNameLike(String namelike) {
		List<StockEntity> entities = stockRepository.findByNameContains(namelike);
		
		List<StockEntity> entityBySql = stockRepository.findByNameBySql(namelike);
		List<StockEntity> entityByKpql = stockRepository.findByNameByJpql(namelike);
		
		List<Stock> stock = new ArrayList<Stock>();
		for(StockEntity entity : entities) {
			stock.add(convertStockEntityIntoStockDto(entity));
		}
		return stock;
	}
}
