package com.retry.example.retrydemo.controller;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.retry.example.retrydemo.service.RetryService;
 
@org.springframework.web.bind.annotation.RestController
public class RestController {
     
    @Autowired
    private RetryService retryService;
     
    @PostMapping(value="/post")
    public void post(@RequestBody Map<String,Integer> request) throws Exception{
         
        retryService.retry(request);
    }
 
}