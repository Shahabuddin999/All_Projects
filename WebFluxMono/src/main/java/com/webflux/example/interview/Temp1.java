package com.webflux.example.interview;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Temp1 {

	Flux<String> getFlux1(String str){
		return Flux.defer(()->{
			return Flux.just(str,"Shahabuddin","Ansari");
		});//.subscribeOn(Schedulers.boundedElastic()).delayElements(Duration.ofSeconds(1));
	}
	
	Flux<String> getFlux2(String str){
		return Flux.defer(()->{
			return Flux.just(str, "Nizam","Ansari","Koraon","Allahabad");
		});
	}
	
	Flux<String> getEmitter(String str){
		return Flux.create(emmiter->{
			emmiter.next(str);
			emmiter.next("Abhay");
			if(10<20)
				emmiter.error(new RuntimeException("Error"));
			emmiter.next("Kalam");
		});
	}
	
	Mono<String> getMono1(String str){
		return Mono.fromCallable(()->{
			return "Shahabuddin "+str;
		});
	}
	
	Mono<String> getMono2(String str){
		return Mono.fromCallable(()->{
			return "Nizamuddin "+str;
		});
	}
	
	void general() {
		System.out.println("----------");
		Flux<Integer> flx = Flux.just(10,20,0,40).map(data->100/data).onErrorResume(e->{
			System.err.println("Error: "+e.getMessage());
			return Mono.empty();
		});
		flx.subscribe(System.out::println);
		
		System.out.println("---------------------");
		Flux<Integer> flux = Flux.just(5,10,0,40).map(data->500/data).retry(2).onErrorResume(e->{
			System.err.println("Error caught: "+e.getMessage());
			return Mono.empty();
		});
		flux.subscribe(System.out::println);
		
		System.out.println("---------------------");
		Flux<String> flux2 = Flux.just("Shahab","nizam","prime","") // Flux can not have null. if you put null it will throw NullPointerException
				.map(data->data.isEmpty() ? "Empty": data);
		flux2.subscribe(System.out::println);
		
		System.out.println("--------");
		Flux.just() // empty flux
	    .defaultIfEmpty("Empty") // 
	    .subscribe(System.out::println);
		
		System.out.println("--------");
		Flux.just() // empty flux
	    .switchIfEmpty(Flux.just("Abhay", "Nizam"))
	    .subscribe(System.out::println);
		
		System.out.println("-------------------------------");
		Flux.range(1, 10)
	    //.log() // log request signals
	    .limitRate(10) // request 10 items at a time
	    .subscribe(item -> {
	        System.out.println("Received: " + item);
	        try {
	            Thread.sleep(100); // simulate slow subscriber
	        } catch (InterruptedException e) { }
	    });

		/*
		faultIfEmpty() and switchIfEmpty() behaviour niche h
		dono ek hi jaisa kaam karte h bs ek dusre ka alternative option hai
		Ye sirf tab trigger hota hai jab Flux ya Mono bilkul empty ho (kisi value ko emit nahi karta).
		Agar empty hai → ek default value emit karega.
		Agar values present hain → unko hi pass karega, koi change nahi karega.
		*/
		
		System.out.println("----------");
		Flux<Integer> flx2 = Flux.just(10,20,30,0,15).map(data->100/data).onErrorReturn(-10);
		flx2.subscribe(System.out::println);
		
		System.out.println("----------");
		
		Flux.just("A", "AB", "ABC")
	    .delayElements(Duration.ofMillis(200))
	    .switchMap(str -> Flux.just("Result for " + str)
	                          .delayElements(Duration.ofMillis(400))
	                          )
	    .subscribe(System.out::println);
		
		System.out.println("----------");
		Flux.just("A", "B", "C")
	    .map(value -> {
	        if ("B".equals(value)) throw new RuntimeException("Boom!");
	        return value.toLowerCase();
	    })
	    .onErrorResume(e -> Flux.just("fallback1", "fallback2")) // declarative handling
	    .subscribe(System.out::println);


	}
	
	public static void main(String[] args) throws InterruptedException {
		Temp1 obj = new Temp1();
		Flux<String> flux1 = obj.getFlux1("Japan");
		Flux<String> flux2= obj.getFlux2("Arab");
		Flux<String> flux3= obj.getEmitter("America");
		
		Mono<String> mono1 = obj.getMono1("Bombay");
		Mono<String> mono2 = obj.getMono2("Delhi");

		int f1 = flux1.count().map(Long::intValue).block();
		int f2 = flux2.count().map(Long::intValue).block();
		int max = Math.max(f1, f2);
		Flux<String> flx = Flux.range(0, max).flatMap(index->{
			Mono<String> m1 = index<f1? flux1.elementAt(index):Mono.just("null");	
			Mono<String> m2 = index<f2? flux2.elementAt(index):Mono.just("null");
			return Mono.zip(m1, m2).map(tuple->tuple.getT1()+":"+tuple.getT2());
		});
		
		flx.subscribe(System.out::println);
		
		obj.general();
		
		Thread.sleep(5000);
	}
}
