package com.zensar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	// Path parameter based api versioning
	@GetMapping(value = "/api/v1/contact")
	public ContactV1 getContact1() {
		return new ContactV1("tom", 123);
	}
	// Path parameter based api versioning
	@GetMapping(value = "/api/v2/contact")
	public ContactV2 getContact2() {
		return new ContactV2("tom", 123);
	}
	
	//http://localhost:8080/api/contact?version=v1
	// Request/Query parameter based api versioning
	@GetMapping(value = "/api/contact", params = "version=v1")
	public ContactV1 getContactRequest() {
		return new ContactV1("tom", 123);
	}
	//http://localhost:8080/api/contact?version=v2
	//  Request/Query parameter based api versioning
	@GetMapping(value = "/api/contact", params = "version=v2")
	public ContactV2 getContactRequest2() {
		return new ContactV2("tom", 123);
	}
	// http://localhost:8080/api/contact and send in header "version" as key and "v1" as value
    //  Header parameter based api versioning
	@GetMapping(value = "/api/contact", headers = "version=v1")
	public ContactV1 getContactHeader() {
		return new ContactV1("tom", 123);
	}
	// http://localhost:8080/api/contact and send in header "version" as key and "v2" as value
    //  Header parameter based api versioning
	@GetMapping(value = "/api/contact", headers  = "version=v2")
	public ContactV2 getContactHeader2() {
		return new ContactV2("tom", 123);
	}
	
	// http://localhost:8080/api/contact and send in header "accept" as key and "application/contact.v1+json" as value
	// Content Negotiation based API versioning
	@GetMapping(value = "/api/contact", produces  = "application/contact.v1+json")
	public ContactV1 getContactContentNegotiation1() {
		return new ContactV1("tom", 123);
	}
	
	// http://localhost:8080/api/contact and send in header "accept" as key and "application/contact.v2+json" as value
	// Content Negotiation based API versioning
	@GetMapping(value = "/api/contact", produces  = "application/contact.v2+json")
	public ContactV2 getContactContentNegotiation2() {
		return new ContactV2("tom", 123);
	}
}

class ContactV1 {
	private String name;
	private int mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public ContactV1(String name, int mobile) {
		super();
		this.name = name;
		this.mobile = mobile;
	}

	public ContactV1() {
		super();
		// TODO Auto-generated constructor stub
	}

}

class ContactV2 {
	private String name;
	private int mobileNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}

	public ContactV2(String name, int mobileNo) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
	}

	public ContactV2() {
		super();
		// TODO Auto-generated constructor stub
	}
}
