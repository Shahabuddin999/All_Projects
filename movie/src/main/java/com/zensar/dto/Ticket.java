package com.zensar.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	private int id;
	@NotBlank
	@Size(min = 5, max = 50, message="Title must be between 10 to 50 Chars")
	private String title; // Movie title
	@NotBlank
	@Size(min = 5, max = 50, message="Type must be between 10 to 50 Chars")
	private String type; // Balcony or Stalls
	@Min(100)
	@Max(1500)
	private double unitPrice;
	@Min(1)
	@Max(10)
	private int noOfTickets;
	@Min(100)
	@Max(1500)
	private double price;
}
