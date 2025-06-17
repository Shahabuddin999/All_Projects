package com.reactive.programing.service;

import java.time.Duration;
import java.util.Objects;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BackpressorAndErrors {

	public static void main(String[] args) {
		Flux<Integer> f = Flux.range(1, 5)
		// basicaly onBackpressureBuffer is handled internally by Flux.range() we should not manually handle
		// .onBackpressureBuffer(2000,drop->System.out.println("Dropped>>>>>>>>> : "+drop),BufferOverflowStrategy.DROP_OLDEST )
		.delayElements(Duration.ofMillis(1));//.doOnNext(i -> System.out.println("Received:: " + i));
		f.subscribe(i -> System.out.println("Received:: " + i));
		f.blockLast();
		
		System.out.println("===============================================");
		
		Flux<Integer> flux = Flux.range(1, 5).map(i -> {
			if (i == 3)
				throw new RuntimeException("Boom at 3!");
			return i;
		}).doOnError(e -> System.out.println("Logged error: " + e.getMessage()))
		.onErrorResume(e -> Flux.just(100, 200));

		flux.subscribe(i -> System.out.println("Received:>>> " + i));
		
		System.out.println("===============================================");
		Flux<Integer> flux1 = Flux.range(1, 5)
				.flatMap(i -> {
					try {
						if (i == 3)
							throw new RuntimeException("Boom at 3!");
						return Mono.just(i);
					} catch (Exception e) {
						System.out.println("Caught error for i = " + i + ": " + e.getMessage());
						return Mono.empty(); // Skip this item
					}
				});

		flux1.subscribe(i -> System.out.println("Received:!!!! " + i));
		
		System.out.println("===============================================");
		
		Flux<Integer> v1 = Flux.just(1,2,3,4,5,6).filter(v->v==3);
		v1.subscribe(System.out::println);
		
		System.out.println("===============================================");
		
		v1 = Flux.just(1,2,3,4,5,6).flatMap(v->{
			 if (v == 3)
		            return Mono.empty();  // Skip this value safely
		        else
		            return Mono.just(v); 
		});
		v1.subscribe(System.out::println);
		
		System.out.println("===============================================");

		flux1 = Flux.range(1, 5)
				.map(i -> {
					if (i == 3)
						throw new RuntimeException("Boom at 3!");
					return i;
				}).onErrorResume(e -> {
					System.out.println("Caught error: " + e.getMessage());
					return Flux.empty(); // Entire stream becomes empty
				});
		flux1.subscribe(System.out::println);
	}

}
