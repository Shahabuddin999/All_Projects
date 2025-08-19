package com.webflux.example;
import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class MonoFluxTest {

    @Test
    void testMono() {
        Mono<String> mono = Mono.just("Shahab");

        StepVerifier.create(mono)
                .expectNext("Shahab")  // expect the value
                .expectComplete()      // should complete successfully
                .verify();
    }
    
    @Test
    void testFlux() {
        Flux<String> flux = Flux.just("A", "B", "C");

        StepVerifier.create(flux)
                .expectNext("A","B","C")       // first element
                .expectComplete()      // should complete
                .verify();
    }
    
    @Test
    void testFluxWithError() {
        Flux<String> flux = Flux.just("X", "Y")
                .concatWith(Flux.error(new RuntimeException("Boom!")));

        StepVerifier.create(flux)
                .expectNext("X","Y")
                .expectErrorMessage("Boom!") // verify error
                .verify();
    }
    
    @Test
    void testDelayedFlux() {
        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1)).take(3); // emits 0,1,2 with 1s delay

        StepVerifier.withVirtualTime(() -> flux)
                .thenAwait(Duration.ofSeconds(3)) // advance virtual time
                .expectNext(0L, 1L, 2L)
                .expectComplete()
                .verify();
    }
}
