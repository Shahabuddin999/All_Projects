package com.reactive.programing.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Reactive {

	public static void main(String[] args) {
		
		Flux<Integer> even = Flux.range(1, 10).filter(v->v%2==0);
		even.subscribe(System.out::println);
		
		even = Flux.range(1, 10).flatMap(v->{
			if(v==7)
				throw new RuntimeException("Error caught : ");
			return Mono.just(v);
		}).onErrorResume(e->{
			System.err.println("Error message "+e.getMessage());
			return Mono.empty();
		});
		even.subscribe(System.out::println);
	}
}
