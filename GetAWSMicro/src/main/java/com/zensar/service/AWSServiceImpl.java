package com.zensar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zensar.dto.Product;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class AWSServiceImpl implements AWSService{

	@Autowired
	RestTemplate restTemplate;
	@Override
	public List<Product> getAllProducts() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "");
		HttpEntity entity = new HttpEntity(headers);

		ResponseEntity<List> result1 = this.restTemplate.exchange("http://zenproductapwithdatabase-env.eba-gtw2q3pd.us-east-1.elasticbeanstalk.com/product",HttpMethod.GET, entity, List.class);
		//ResponseEntity<List> result = restTemplate.getForEntity("http://zenproductapwithdatabase-env.eba-gtw2q3pd.us-east-1.elasticbeanstalk.com/product",List.class);
		return result1.getBody();
	}

	
}
