package com.webflux.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.webflux.demo.service.S3Service;
/*
 * This endpoint will work when you will deploy it on EC2 instance because it can not access DefaultCredentialsProvider from AWS  
 * I have created awsCommand.txt file in resource folder where I have written some command go through and follow
 */


@RestController
@RequestMapping("/s3")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        String key = "uploads/" + file.getOriginalFilename(); // You can customize path
        return s3Service.uploadFile(file, key);
    }

    @GetMapping("/read")
    public String read(@RequestParam("key") String key) throws Exception {
        return s3Service.readFile(key);
    }
}
