package com.zensar.interview;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Position{
	private BigDecimal value;
	private Long id;
	private String desc;
}
class Account {
	List<Position> list = List.of(new Position(BigDecimal.valueOf(10), 20L, null),new Position(BigDecimal.valueOf(30), 20L, "shahab"),new Position(BigDecimal.valueOf(10), 20L, "shahab"));
}
public class Temp8 {
	
	
	public static void main(String[] args) {
		Account object = new Account();
		BigDecimal reduce = object.list.stream().map(obj-> obj.getValue()).reduce(new BigDecimal(0),(a,b)->a.add(b));
		System.out.println(reduce);
		
		List<Position> sorted = object.list.stream().filter(obj->obj.getDesc()!=null).sorted((obj1, obj2)->obj1.getDesc().compareTo(obj2.getDesc())).collect(Collectors.toList());
		System.out.println(sorted);
	}
	
}
