package com.zensar.temp;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
 class BuilderPattern {

	private final String name;
	private final String mail;
	
	@Builder.Default
	private final String address = "You have not entered Address...";
	private final String mobile;
}

public class BuilderDesignPattern{
	public static void main(String[] args) {
		BuilderPattern object = BuilderPattern.builder()
				.name("Shahab")
				.mobile("8127958035")
				//.address("Allahabad")
				.mail("Shahab@gmail.com")
				.build();
		System.out.println(object);
	}
}
