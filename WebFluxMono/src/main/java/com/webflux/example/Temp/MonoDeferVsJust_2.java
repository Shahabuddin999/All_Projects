package com.webflux.example.Temp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoDeferVsJust_2 {

	Flux<String> getFlux1(){
		return Flux.defer(()->{
			return Flux.just("Shahabuddin","Ansari","Koraon","Delhi");
			//return Mono.just(null);
		}).subscribeOn(Schedulers.boundedElastic()); // This line new separet thread if its taking time to complete its execution without this line thread will be died before completing its execution only when its taking time.
	}
	
	Flux<String> getFlux2(){
		return Flux.defer(()->{
			
			// Thread.sleep(1000); This line will not work without try, catch

			/*
			 * Thread.sleep(1000) This fails because:
			 * 
			 * defer() expects the lambda to return a Mono — not a blocking result.
			 * 
			 * You cannot use Thread.sleep() directly unless you handle
			 * InterruptedException.
			 * 
			 * Even if you handled the exception, this defeats the purpose of defer() which
			 * is to defer Mono creation, not execute heavy logic.
			 */
			return Flux.just("Nizam","Koraon","Allahabad");
		});
	}
	
	Mono<String> getMono1(){
		return Mono.defer(()->{
			return Mono.just("Shahabuddin");
		});
	}
	
	Mono<String> getMono2(){
		return Mono.fromCallable(()->{
			Thread.sleep(1000); 
			
			/*
			 * Thread.sleep(1000) Why does it work?
			 * 
			 * Because fromCallable() has been designed to wrap blocking code (e.g., database
			 * calls, file reads). and blocking code takes time to execute
			 * 
			 * It executes the lambda during subscription and emits the return value.
			 * 
			 * Recommended: run this on Schedulers.boundedElastic() to avoid blocking
			 * reactive threads.
			 */
			// return Mono.just("Shahabuddin"); This line will not work
			return "Shahabuddin";
		});
	}
	public static void main(String[] args) {
		MonoDeferVsJust_2 obj = new MonoDeferVsJust_2();
		Flux<String> flux1 = obj.getFlux1();
		Flux<String> flux2 = obj.getFlux2();
		Flux<String> map = Flux.zip(flux1, flux2).map(tuple->{
			return tuple.getT1()+":"+tuple.getT2();
		});
		map.subscribe(System.out::println);
		
		int count_1 = flux1.count().map(Long::intValue).block();
		int count_2 = flux2.count().map(Long::intValue).block();
		int max = Math.max(count_1, count_2);
		Flux<String> combinedZip = Flux.range(0, max).flatMap(index->{ 
			//flatMap: All Monos are started at the same time → faster ones complete earlier, so output order is random.
		//Flux<String> combinedZip = Flux.range(0, max).concatMap(index->{ 
			//concatMap: Waits for each Mono to complete before starting the next, hence order preserved. 
			Mono<String> val1 = index < count_1 ? flux1.elementAt(index) : Mono.just("null");
			Mono<String> val2 = index < count_2 ? flux2.elementAt(index): Mono.just("null");
			return Mono.zip(val1, val2).map(tuple->{
				return tuple.getT1()+":"+tuple.getT2();
			});
		});
		System.out.println("----------------------------------");
		combinedZip.subscribe(System.out::println);
	}
}
	/*
	 * defer() ensures fresh Mono on every subscription
	 * 
	 * fromCallable() runs the blocking code
	 * 
	 * Schedulers.boundedElastic() ensures it's on a thread suitable for blocking
	 * I/O
	 */
