package com.reactive.programing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
public class ProductController {

    @GetMapping("/sale-products")
    public Flux<String> getSaleProducts() {
        return Flux.just("Hat", "Pants", "Shoes", "Purse")
                   .filter(product -> product.startsWith("P"))
                   .map(product -> product + "-OnSale");
    }
	
	@GetMapping("/related-accessories")
	public Flux<String> getRelatedAccessories() {
	    return Flux.just("tablet", "mouse")
	               .flatMap(productId -> Mono.just("case for " + productId))
	               .log();
	}

	
	@GetMapping("/product-catalog")
	public Flux<String> getProductCatalog() {
	    Flux<String> products = Flux.just("Book", "Pen");
	    Flux<Double> prices = Flux.just(19.99, 5.99);

	    return products.zipWith(prices, (product, price) -> product + ": $" + price);
	}


}
