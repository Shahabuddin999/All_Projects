package com.reactive.programing.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class App {
	public Flux<Integer> testFlux(){
		Flux<Integer> fluxNumbers = Flux.just(1,2,3,4,5).log();
		return fluxNumbers;
	}
	
	public Mono<Integer> testMono(){
		Mono<Integer> monoNumbers = Mono.just(1).log();
		return monoNumbers;
	}
	
	public static void main(String[] args) {
		App app = new App();
		Flux<Integer> fluxNumbers = app.testFlux();
		Mono<Integer> monoNumbers = app.testMono();
		
		fluxNumbers.subscribe(System.out::println);
		monoNumbers.subscribe(System.out::println);
	}

}
