package com.webflux.example.Temp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorHandlingExample {

    public static void main(String[] args) {

        System.out.println("== onErrorResume() ==");
        Flux.just(1, 2, 0, 3)
            .map(i -> 10 / i)
            .onErrorResume(e -> {
                System.out.println("Fallback triggered: " + e.getMessage());
                return Flux.just(-9,-8);
            })
            .subscribe(System.out::println);

        System.out.println("\n== onErrorReturn() ==");
        Flux.just(1, 2, 0, 3)
            .map(i -> 10 / i)
            .onErrorReturn(-99)
            .subscribe(System.out::println);

        System.out.println("\n== retry() ==");
        Flux<Integer> errorFlux = Flux.<Integer>create(sink -> {
            System.out.println("Trying...");
            sink.next(1);
            sink.next(2);
            
            sink.error(new RuntimeException("Something failed!"));
            sink.next(100);
            //sink.complete(); if you have used sink.complete(); then no any next line will be read all other line will be ignored within current curly braces.
            
        });

        errorFlux
            .retry(2) // Retry 2 times
            //.onErrorReturn(-1)
            .onErrorResume(e-> {  
            	System.out.println(e.getMessage()); return Mono.just(-1);
            })
            .subscribe(System.out::println);

        System.out.println("\n== doOnError() ==");
        Flux.just(1, 2, 0, 3)
            .map(i -> 10 / i)
            .doOnError(e -> System.out.println("Logged error: " + e.getMessage()))
            .onErrorReturn(-5)
            .subscribe(System.out::println);
    }
}
