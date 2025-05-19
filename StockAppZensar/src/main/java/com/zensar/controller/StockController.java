package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Stock;
import com.zensar.exception.InvalidStockIdException;
import com.zensar.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/stockapp")
@CrossOrigin(origins ="*")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class StockController {
	
	@Autowired
	StockService stockService;
	
	/*
	 Whenever Spring Boot application gets an exception, it handles exception with below sequence:
		1) Checks whether exception is handled locally.
		2) Checks whether exception is handled globally.
		3) Checks whether exception has mentioned @ResponseStatus
		4) Throw the Internal Server error 500 to the client
	 */
	@ExceptionHandler(InvalidStockIdException.class)
	public ResponseEntity<String> getCustomisedLocalExceptionHandler(InvalidStockIdException exception){
		return new ResponseEntity<String>("{\"Local exception error\": \"" + exception.toString() +"\"}", HttpStatus.FORBIDDEN);
	}
	
	@GetMapping(value = "/stock", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read all Stocks", notes = "Returns all stock to client")
	ResponseEntity<List<Stock>> getStocks() {
		return new ResponseEntity(stockService.getStocks(), HttpStatus.FOUND);
	}

	@GetMapping(value = "/stock/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read all Stocks", notes = "Returns specific stock to client") // This @ApiOperation and @ApiParam is belonging to swagger
	ResponseEntity<Stock> getStockById(@ApiParam(value="stock Id", name="id", required = true) @PathVariable("id") int id) {
		return new ResponseEntity(stockService.getStockById(id),HttpStatus.FOUND);
	}

	@PostMapping(value = "/stock", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
		return new ResponseEntity(stockService.createStock(stock),HttpStatus.CREATED);
	}

	@PutMapping(value = "/stock/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Stock> updateStock(@RequestBody Stock stock, @PathVariable("id") int stockId) {
		return new ResponseEntity(stockService.updateStock(stock, stockId),HttpStatus.OK);
	}

	@DeleteMapping(value = "/stock")
	public ResponseEntity<Boolean> deleteAllStock() {
		return new ResponseEntity(stockService.deleteAllStock(),HttpStatus.OK);
	}

	@DeleteMapping(value = "/stock/{id}")
	public ResponseEntity<Boolean> deleteByStockId(@PathVariable("id") int stockId) {
		return new ResponseEntity(stockService.deleteByStockId(stockId), HttpStatus.OK);
	}
	
	@GetMapping(value="/stock/name/{name}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Stock>> getStocksByName(@PathVariable("name") String stockName) {
		return new ResponseEntity(stockService.getStocksByName(stockName),HttpStatus.FOUND);	
	}
	
	@GetMapping(value="/stock/sort/{sortType}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Stock>> getStocksSortedByName(@PathVariable("sortType") String sortType) {
		return new ResponseEntity(stockService.getStocksSortedByName(sortType), HttpStatus.FOUND);
	}
	
	@GetMapping(value="/stock/sort/{startIndex}/{records}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Stock>> getStocksByPage(@PathVariable("startIndex") int startIndex,
	@PathVariable("records") int records) {
		return new ResponseEntity(stockService.getStocksByPage(startIndex,records),HttpStatus.FOUND);
	}
	
	@GetMapping(value="/stock/name/like/{namelike}", produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Stock>> getStocksByNameLike(@PathVariable("namelike") String nameLike) {
		return new ResponseEntity(stockService.getStocksByNameLike(nameLike),HttpStatus.FOUND);	
	}
	 
	@GetMapping(value = "/account")
	public ResponseEntity<Boolean> getAccount(@RequestParam("id") int id, @RequestHeader("authdata") String authData) {
		System.out.println("id : "+ id);
		System.out.println("authData : "+ authData);
		return new ResponseEntity(stockService.getAccount(id, authData),HttpStatus.OK);
	}

}
