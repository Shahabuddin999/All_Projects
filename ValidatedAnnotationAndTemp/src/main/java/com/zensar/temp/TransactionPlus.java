package com.zensar.temp;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionPlus {
    private LocalDate date;
    private double amount;
    private String category;
    private String name;
    private String address;
    private String mobile;
}
