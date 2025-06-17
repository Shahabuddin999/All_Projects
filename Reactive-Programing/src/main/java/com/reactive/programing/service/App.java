package com.reactive.programing.service;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class App {
	public Flux<Integer> testFlux(){
		Flux<Integer> fluxNumbers = Flux.just(1,2,3,4,5).log();
		return fluxNumbers;
	}
	
	public Mono<Integer> testMono(){
		Mono<Integer> monoNumbers = Mono.just(1).log();
		
//		Flux.range(1, 1_000_000) // emits 1 million items
//	    .onBackpressureBuffer(1000) // buffer up to 1000 items
//	    .delayElements(Duration.ofMillis(1)) // simulate slow consumer
//	    .subscribe(System.out::println);
//		
		
		Flux<Integer> val = Flux.range(1,100).map(v->v)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("processing count:" + i));
		
		val.blockLast();
		
		val.subscribe(System.out::println);
		//Thread.sleep(1000000000);
		return monoNumbers;
	}
	
	public static void main(String[] args) {
		App app = new App();
		//Flux<Integer> fluxNumbers = app.testFlux();
		//Mono<Integer> monoNumbers = app.testMono();
		
		//fluxNumbers.subscribe(System.out::println);
		//monoNumbers.subscribe(System.out::println);
		getFlux();
		
	}
	static Flux<String>  getFlux(){
		Mono<String> mono = Mono.just("aaa");
		Flux<String> flux = Flux.just("bbb","cccc","ddd");
		
		Mono<String> mono2 = mono.flatMap(v->{
			return Mono.just(v+" hi");
		});
		mono2.subscribe(System.out::println);
		
		mono2 = mono.map(v->{
			return v+" hello";
		});
		mono2.subscribe(System.out::println);
		
		Flux<String> flux2 = flux.flatMap(v->{
			return Mono.just(v+" hi flatMap"); // It flatterns all mono into a single Flux.just("bbb hi flatMap","cccc hi flatMap","ddd hi flatMap");
		});
		flux2.subscribe(System.out::println);
		
		flux2 = flux.map(v->{
			return v+" hi flatMap2"; // It put into Flux.just("bbb hi flatMap2","cccc hi flatMap2","ddd hi flatMap2")
		});
		flux2.subscribe(System.out::println);
		
	
		return null;
	}

}
