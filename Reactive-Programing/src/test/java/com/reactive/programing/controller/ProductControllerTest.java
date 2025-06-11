package com.reactive.programing.controller;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ProductControllerTest {
	@Test
	void testSaleProducts() {
	    Flux<String> result = Flux.just("Hat", "Pants", "Shoes", "Purse")
	                              .filter(p -> p.startsWith("P"))
	                              .map(p -> p + "-OnSale");

	    StepVerifier.create(result)
	                .expectNext("Pants-OnSale", "Purse-OnSale")
	                .verifyComplete();
	}
	
	@Test
	void testRelatedAccessories() {
	    Flux<String> result = Flux.just("tablet", "mouse")
	                              .flatMap(id -> Mono.just("case for " + id));

	    StepVerifier.create(result)
	                .expectNext("case for tablet", "case for mouse")
	                .verifyComplete();
	}

	@Test
	void testProductCatalog() {
	    Flux<String> products = Flux.just("Book", "Pen");
	    Flux<Double> prices = Flux.just(19.99, 5.99);

	    Flux<String> result = products.zipWith(prices, (p, pr) -> p + ": $" + pr);

	    StepVerifier.create(result)
	                .expectNext("Book: $19.99", "Pen: $5.99")
	                .verifyComplete();
	}

}
