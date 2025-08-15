package com.webflux.example.controller;

import java.time.Duration;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class AsyncService {

	public Flux<String> getFlux1() {
		return Flux.defer(() -> {
			String[] names = { "AA", "BB", "CC" };
			Flux<String> flux = Flux.fromArray(names);
			System.out.println("inside getFlux1() : "+Thread.currentThread().getName());
			return flux;
		}).subscribeOn(Schedulers.boundedElastic())
		  .delayElements(Duration.ofSeconds(1));
	}

	public Flux<String> getFlux2() {
		String name ="AAAAAA";
		return Flux.defer(() -> {
			System.out.println("inside getFlux2() : "+Thread.currentThread().getName());
			return Flux.just("Nizam", "Kalam", "Shahab", "Abhay",name);
		}).subscribeOn(Schedulers.boundedElastic())
		  .delayElements(Duration.ofSeconds(1));
	}

	public Flux<String> getFluxUsingLoop() {
		return Flux.<String>create(emitter -> {
			for (int i = 1; i <= 5; i++) {
				System.out.println("inside getFluxUsingLoop() : "+Thread.currentThread().getName());
				emitter.next("Value " + i);
			}
			emitter.complete();
		}).delayElements(Duration.ofSeconds(1));
	}

	// combineflux
	public Flux<String> combinedZip() {
		Flux<String> flux1 = getFlux1();
		Flux<String> flux2 = getFlux2();
		Flux<String> flux3 = getFluxUsingLoop();
		System.out.println("inside combinedZip() : "+Thread.currentThread().getName());
		flux1.subscribe(System.out::println);
		flux2.subscribe(System.out::println);
		flux3.subscribe(System.out::println);
		
		Flux<String> combined = Flux.zip(flux1, flux2).map(tuple -> tuple.getT1() + " : " + tuple.getT2() + "\n");
		return combined;
	}
	
	public Mono<String> asyncCallOne(String firstName) {
		return Mono.fromCallable(() -> {
			System.out.println("inside asyncCallOne() :  "+Thread.currentThread().getName());
			Thread.sleep(5000);
			return firstName;
		}).subscribeOn(Schedulers.boundedElastic()); // offload to another thread
	}

	public Mono<String> asyncCallTwo(String lastName) {
		return Mono.fromCallable(() -> {
			System.out.println("inside asyncCallTwo() : "+Thread.currentThread().getName());
			Thread.sleep(9000);
			return lastName;
		}).subscribeOn(Schedulers.boundedElastic());
	}
	
	public Mono<String> getMono1(String lastName) {
			return Mono.just(lastName);
	}
	
	public Flux<String> getFlux1(String lastName) {
		return Flux.just(lastName,"Shahab","Ansari","Koraon");
	}
	
	//combine
	public Mono<String> combinedAsyncCallsAsFlux() {
		Mono<String> mono1 = asyncCallOne("Shahabuddin");
		Mono<String> mono2 = asyncCallTwo("Ansari");
		
		getFlux1("Nizam").subscribe(System.out::println);
		getMono1("Kalam").subscribe(System.out::println);
		return Mono.zip(mono1, mono2).map(tuple -> "Combined: " + tuple.getT1() + " + " + tuple.getT2());
	}
}
