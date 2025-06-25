package com.webflux.example.Temp;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class React {

	public Flux<String> getFluxFromLoop() {
	    return Flux.create(emitter -> {
	        for (int i = 1; i <= 5; i++) {
	            emitter.next("Value " + i);
	        }
	        emitter.complete(); // Always complete it
	    });
	}
	
	Mono<String> getMono() {

		return Mono.fromCallable(() -> {
			String name = "Shahabuddin";
			//Thread.sleep(1000);
			return name;
		}).subscribeOn(Schedulers.boundedElastic()).delayElement(Duration.ofSeconds(1));
	}

	Mono<String> getMono2() {
		return Mono.fromCallable(() -> {
			String name = "Kalamuddin";
			//Thread.sleep(10000);
			return name;
		}).subscribeOn(Schedulers.boundedElastic()).delayElement(Duration.ofSeconds(1));
	}

	Flux<String> getFlux1() {
		return Flux.defer(() -> {
			return Flux.just("Shahab", "Nizam", "Kalam","Amma","Abba");
		}).subscribeOn(Schedulers.boundedElastic());
	}

	Flux<String> getFlux2() {
		return Flux.defer(() -> {
			return Flux.just("Parvej", "Shabnam", "Naziya", "Shaba");
		}).subscribeOn(Schedulers.boundedElastic());
	}

	public static void main(String[] args){
		React react = new React();
		Mono<String> mono1 = react.getMono();
		Mono<String> mono2 = react.getMono2();

		Mono<String> map = Mono.zip(mono1, mono2).map(tuple -> {
			return tuple.getT1() + " : " + tuple.getT2();
		});
		String block = map.block(); // This line is not required in Rest API/Controller/Service this is blocking code, in Spring, Spring will handle internally
		System.out.println("Result1: " + block);
		Flux<String> flux1 = react.getFlux1();
		Flux<String> flux2 = react.getFlux2();
		Flux<String> map2 = Flux.zip(flux1, flux2).map(tuple -> {
			String value = tuple.getT1() + ":::" + tuple.getT2();
			return value;
		});
		map2.blockLast();  // This line is not required in Rest API/Controller/Service this is blocking code, in Spring, Spring will handle internally
		map2.subscribe(System.out::println);
		
		System.out.println("================================");
		
		int f1count = flux1.count().map(Long::intValue).block();
		int f2count = flux2.count().map(Long::intValue).block();
		int max = Math.max(f1count,f2count);
		
		Flux<String> paired = Flux.range(0, max).flatMap(i-> {
			Mono<String> val1 = i< f1count ? flux1.elementAt(i) : Mono.just("null");
			Mono<String> val2 = i< f2count ? flux2.elementAt(i) : Mono.just("null");

			return Mono.zip(val1, val2).map(tuple->{ 
				return tuple.getT1()+" : "+tuple.getT2();
			});
		});
		
		paired.subscribe(System.out::println);

	}

}
