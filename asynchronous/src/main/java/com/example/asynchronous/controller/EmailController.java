package com.example.asynchronous.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.asynchronous.service.EmailService;

@RestController
@RequestMapping("/send")
public class EmailController {

	private final EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/mail")
	public String send() {
		emailService.sendEmail();
		emailService.sendEmailAgain();
		emailService.sendEmailRepeatAgain();
		emailService.info();
		emailService.process();
		emailService.payment();
		for(int i=0; i<10; i++)
			System.out.println("main thread:"+Thread.currentThread().getName());
		return "Execution finished";
	}

	void disp(String get) {
		System.out.println("Outside data: "+get);
	}
	@PostMapping("/initiate")
	public String initiate() throws InterruptedException, ExecutionException {
		emailService.sendEmail();
		emailService.sendEmailAgain();
		emailService.sendEmailRepeatAgain();
		//String []value =new String[1];
		CompletableFuture<String> info = emailService.initiate();
		info.thenAccept(val->{
			System.out.println("recieved data : "+val);
			disp(val);
		});
		// thenCompose() is used to call another asynchronous method and returned value is being passed to initiate(String)
		emailService.initiate().thenCompose(result -> emailService.initiate(result))
		.thenAccept(value->System.out.println(value));
		//value[0] = info.get();
		emailService.process();
		emailService.info();
		emailService.payment();
		//System.out.println("recieved data again : "+value[0]);
		for(int i=0; i<10; i++)
			System.out.println("main thread:"+Thread.currentThread().getName());
		return "Execution finished";
	}
}
