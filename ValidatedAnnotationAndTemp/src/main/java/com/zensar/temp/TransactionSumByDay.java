package com.zensar.temp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionSumByDay {
	public static void main(String[] args) {
		List<Transaction> transactions = Arrays.asList(
				new Transaction(LocalDate.of(2023, 5, 28), 100.0, "bus"),
				new Transaction(LocalDate.of(2023, 5, 28), 150.0, "car"),
				new Transaction(LocalDate.of(2023, 5, 28), 140.0, "car"),
				new Transaction(LocalDate.of(2023, 5, 29), 200.0, "bike"),
				new Transaction(LocalDate.of(2023, 5, 29), 50.0, "bike"),
				new Transaction(LocalDate.of(2023, 5, 30), 300.0, "cycle")
				);

		Map<LocalDate, Double> sumByDay = transactions.stream().collect(
				Collectors.groupingBy(val -> val.getDate(), Collectors.summingDouble(value -> value.getAmount())));

		sumByDay.forEach((date, total) -> System.out.println(date + " : " + total));
		
		Map<String, Double> sumByCategory = transactions.stream().collect(
				Collectors.groupingBy(val -> val.getCategory(), Collectors.summingDouble(value -> value.getAmount())));

		sumByCategory.forEach((category, total) -> System.out.println(category + " : " + total));
		
		
		Map<LocalDate, Map<String, Double>> result = transactions.stream()
			    		.collect(Collectors.groupingBy(val -> val.getDate(),
					        Collectors.groupingBy(value->value.getCategory(),
					            Collectors.summingDouble(amount->amount.getAmount())
			        )
			    ));
		result.forEach((date, total) -> System.out.println(date + " : " + total));
		
		Map<String, List<Transaction>> map=  transactions.stream().collect(Collectors.groupingBy(val->val.getCategory()));
		System.out.println(map);
		
		List<TransactionPlus> transactionsPlus = Arrays.asList(
				new TransactionPlus(LocalDate.of(2023, 5, 28), 100.0, "bus", "Shahabuddin", "koraon", "1234567"),
				new TransactionPlus(LocalDate.of(2023, 5, 28), 150.0, "car", "Shahabuddin", "koraon", "1234567"),
				new TransactionPlus(LocalDate.of(2023, 5, 28), 140.0, "car", "Nizam", "Allahabad", "123456789"),
				new TransactionPlus(LocalDate.of(2023, 5, 29), 200.0, "bike", "Kalam", "koraon", "123456732"),
				new TransactionPlus(LocalDate.of(2023, 5, 29), 50.0, "bike", "Kalam", "koraon", "123456721"),
				new TransactionPlus(LocalDate.of(2023, 5, 30), 300.0, "cycle", "Parvej", "koraon", "123456711")
				);
		
		Map<LocalDate, Map<String, Map<String, Double>>> output  = transactionsPlus.stream()
				.collect(Collectors.groupingBy(TransactionPlus::getDate,
					Collectors.groupingBy(TransactionPlus::getName, 
						Collectors.groupingBy(TransactionPlus::getAddress,
							Collectors.summingDouble(TransactionPlus::getAmount)))));  //    Map<Date, Map<String, Map<String,Double>>
		
		System.out.println(output);
	}
}
