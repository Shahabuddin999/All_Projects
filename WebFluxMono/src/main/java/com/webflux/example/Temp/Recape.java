package com.webflux.example.Temp;

import java.time.Duration;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

public class Recape {

	public Flux<String> getFlux1(){
		return Flux.defer(()->{
			return Flux.just("a","b","c");
		}).subscribeOn(Schedulers.boundedElastic());
	}
	 
	public Flux<String> getFlux2(){
		return Flux.defer(()->{
			return Flux.just("d","e","f","g","h");
		}).subscribeOn(Schedulers.boundedElastic());
	}
	
	public Mono<String> getMono1(){
		return Mono.fromCallable(()->{
			//Thread.sleep(5000);
			return "Shahabuddin";
		});
	}
	
	public Mono<String> getMono2(){
		return Mono.fromCallable(()->{
			//Thread.sleep(9000);
			return "Ansari";
		});
	}
	
	public static void main(String[] args) {
		Recape recapeObj = new Recape();
		Flux<String> flux1 = recapeObj.getFlux1();
		Flux<String> flux2 = recapeObj.getFlux2();
		
		flux1.blockLast(); 
		flux2.blockLast();
		
		int count1 = flux1.count().map(Long::intValue).block();
		int count2 = flux2.count().map(Long::intValue).block();
		int max = Math.max(count1, count2);
		Flux<String> combined = Flux.range(0, max).flatMap(index->{
			Mono<String> val1 = index<count1? flux1.elementAt(index):Mono.just("null");
			Mono<String> val2 = index<count2? flux2.elementAt(index):Mono.just("null");
			return Flux.zip(val1, val2).map(tuple->tuple.getT1()+":"+tuple.getT2());
		});
		
		combined.subscribe(System.out::println);
//		Flux<String> fluxMap = Flux.zip(flux1, flux2).map(tuple->tuple.getT1()+":"+tuple.getT2());
//		fluxMap.subscribe(System.out::println);
		
		Mono<String> mono1 = recapeObj.getMono1();
		Mono<String> mono2 = recapeObj.getMono2();
		
		mono1.block();
		mono2.block();
		Mono<String> monoMap = Mono.zip(mono1, mono2).map(tuple->tuple.getT1()+":"+tuple.getT2());
		monoMap.subscribe(System.out::println);
		
		System.out.println("===============================OnErrorResume===========================================");
		 Flux<Integer> onErrorResume = Flux.just(1,8,0,3)
		.map(v-> 10/v)
		.retry(2)
		//.retryWhen(Retry.backoff(2, Duration.ofSeconds(1)))
		.onErrorResume(err -> {
			System.out.println(err.getMessage());
			return Mono.empty();
		});
		 //onErrorResume.blockLast();
		 onErrorResume.subscribe(System.out::println);
		
		//onErrorResume.blockLast();
		System.out.println("----");
		
	}

}
