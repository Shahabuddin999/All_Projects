package com.webflux.example.Temp;

import reactor.core.publisher.Mono;

public class MonoDeferVsJust {
    public static void main(String[] args) {
        Mono<String> just = Mono.just(getData());
        Mono<String> defer = Mono.defer(() -> Mono.just(getData()));

        System.out.println("== just ==");
        just.subscribe(System.out::println);

        System.out.println("== defer ==");
        defer.subscribe(System.out::println);
        /*
          Explanation:
          Mono.just(getData()) calls the method immediately — at declaration time.
          Mono.defer(...) delays the call until someone subscribes.
        */
        
        System.out.println("== defaultIfEmpty vs switchIfEmpty =="); 
        disp();
        
        
    }

    static String getData() {
        System.out.println("getData() called");
        return "Hello";
    }
    
    
    static void disp() {
    	Mono<Object> result = Mono.just("hello")
    		    .flatMap(val -> Mono.empty())
    		    .defaultIfEmpty("fallback")
    		    .switchIfEmpty(Mono.error(new RuntimeException("No data")));

    		result.subscribe(System.out::println, System.err::println);
    		
			/*
			 * Explanation: flatMap → Mono.empty() causes stream to be empty.
			 * 
			 * defaultIfEmpty("fallback") provides a fallback value.
			 * 
			 * switchIfEmpty() is not executed because defaultIfEmpty() already filled it.
			 */
    }
    
}
