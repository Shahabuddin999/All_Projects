package com.zensar.temp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private LocalDate date;
    private double amount;
    private String category;
    
    public static List<Transaction> getTrasaction(){
    	return Arrays.asList(
				new Transaction(LocalDate.of(2023, 5, 28), 100.0, "bus"),
				new Transaction(LocalDate.of(2023, 5, 28), 150.0, "car"),
				new Transaction(LocalDate.of(2023, 5, 28), 140.0, "car"),
				new Transaction(LocalDate.of(2023, 5, 29), 200.0, "bike"),
				new Transaction(LocalDate.of(2023, 5, 29), 50.0, "bike"),
				new Transaction(LocalDate.of(2023, 5, 30), 300.0, "cycle")
				);
    }
    
    public static List<Transaction> getTrasactionPlus(){
    	return Arrays.asList(
				new Transaction(LocalDate.of(2023, 5, 28), 100.0, "bus"),
				new Transaction(LocalDate.of(2024, 5, 28), 150.0, "car"),
				new Transaction(LocalDate.of(2024, 5, 28), 140.0, "car"),
				new Transaction(LocalDate.of(2025, 5, 29), 200.0, "bike"),
				new Transaction(LocalDate.of(2026, 5, 29), 50.0, "bike"),
				new Transaction(LocalDate.of(2027, 5, 30), 300.0, "cycle")
				);
    }
}
