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
public class Movie {
	private int id;
	@NotBlank
	@Size(min = 10, max = 50, message="Type must be between 10 to 50 Chars")
	private String title;
	@NotBlank
	@Size(min = 10, max = 50, message="Director must be between 10 to 50 Chars")
	private String director;
	@NotBlank
	@Size(min = 3, max = 10, message="Language must be between 10 to 50 Chars")
	private String language;
	@Min(1800)
	@Max(2025)
	private int year;
}
