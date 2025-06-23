package com.webflux.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(@Value("${aws.region}") String region) {
        var credentialsProvider = DefaultCredentialsProvider.create();

        // Diagnostic: Print credentials provider info
        var credentials = credentialsProvider.resolveCredentials();
        System.out.println("Using AWS credentials from: " + credentialsProvider.getClass().getSimpleName());
        System.out.println("Access Key ID: " + credentials.accessKeyId());

        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(credentialsProvider)
                .build();
    }


    public String uploadFile(MultipartFile file, String key) throws Exception {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );

        return "File uploaded to S3 with key: " + key;
    }

    public String readFile(String key) throws Exception {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        try (ResponseInputStream<?> s3Object = s3Client.getObject(request);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(s3Object, StandardCharsets.UTF_8))) {

            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
