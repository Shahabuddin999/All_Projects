package com.example.asynchronous.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class EmailService {


    public void sendEmail() {
        try {
            Thread.sleep(3000);
            System.out.println("sendEmail : "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void sendEmailAgain() {
        try {
            Thread.sleep(3000);
            System.out.println("sendEmailAgain : "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void sendEmailRepeatAgain() {
        try {
            Thread.sleep(3000);
            System.out.println("sendEmailRepeatAgain : "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Async("taskExecutor")
    public void info() {
        // Simulate delay
        try {
            Thread.sleep(9000);
            System.out.println("info : "+Thread.currentThread().getName());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Async("taskExecutor")
    public void process() {
        try {
            Thread.sleep(5000);
            System.out.println("process : "+Thread.currentThread().getName());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Async("taskExecutor")
    public void payment() {
        try {
            Thread.sleep(7000);
            System.out.println("payment : "+Thread.currentThread().getName());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Async("taskExecutor")
    public CompletableFuture<String> initiate() {
        try {
            Thread.sleep(7000);
            System.out.println("initiate : "+Thread.currentThread().getName());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture("initiate Done");
    }
}
