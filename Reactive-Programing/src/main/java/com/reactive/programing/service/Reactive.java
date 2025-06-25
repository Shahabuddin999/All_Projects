package com.reactive.programing.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Reactive {

	public static void main(String[] args) {

		Flux<Integer> even = Flux.range(1, 10).filter(v -> v % 2 == 0);
		even.subscribe(System.out::println);

		even = Flux.range(1, 10).flatMap(v -> {
			if (v == 7)
				throw new RuntimeException("Error caught : ");
			return Flux.just(v);
		}).onErrorResume(e -> {
			System.err.println("Error message " + e.getMessage());
			return Flux.empty();
		});
		even.subscribe(System.out::println);

		Flux<Integer> numbers = Flux.just(1, 2, 3).map(n -> n * 10);

		// Flux -> Mono: still returns Flux
		Flux<String> flux1 = Flux.just("a", "b").flatMap(s -> Mono.just(s.toUpperCase())); // Returns Flux<String>

		// Flux -> Flux: returns flattened Flux
		Flux<String> flux2 = Flux.just("a", "b").flatMap(s -> Flux.just(s.toUpperCase())); // Returns Flux<String>
		flux2.subscribe(System.out::print);
		Mono<String> mono = Mono.just("hello").map(s ->s.toUpperCase()); // Returns Mono<String>

		Flux<Integer> flux = Flux.just(1, 2, 3).flatMap(n -> { return Flux.just(n * 10);}); // Returns Flux<Integer>

		Mono<String> mono2 = Mono.just("hello").flatMap(s -> Mono.just(s.toUpperCase())); // Returns Mono<String>
	}
}
