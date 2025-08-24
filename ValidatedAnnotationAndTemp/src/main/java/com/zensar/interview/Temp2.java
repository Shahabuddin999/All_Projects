package com.zensar.interview;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zensar.temp.Customer;

public class Temp2 {

	public static void main(String[] args) {
		
//		LongStream doubleStream = IntStream.range(1, 10).filter(i -> i % 2 != 0).asLongStream();
//		System.out.println(doubleStream.toArray()[1]);
//
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//		int sum = numbers.stream().mapToInt(Integer::intValue).sum();
//		System.out.println(sum); // Output: 15
		
//		 System.out.println("version: " + SpringVersion.getVersion());
//		 System.out.println("version: " + SpringBootVersion.getVersion());

		List<Customer> list = Customer.getCustomers();
		
		List<List<String>> inMultipleListUsingWithoutFlatMap = list.stream().map(customer->customer.getPhoneNumbers()).collect(Collectors.toList());
		System.out.println("Using map() : " + inMultipleListUsingWithoutFlatMap);
		
		List<String> inSingleListUsingFlatMap = list.stream().flatMap(customer->customer.getPhoneNumbers().stream()).collect(Collectors.toList());
		System.out.println("Using flatMap() : " + inSingleListUsingFlatMap);
		
		Optional<Customer> c = list.stream().min((obj1,obj2)->obj1.getAge()-obj2.getAge());
		System.out.println(c.get());
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		
        Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
        System.out.println(sum.get());  // Output: 15
        
        Optional<Customer> cus = list.stream().reduce((v1,v2)->v1.getAge()>v2.getAge()?v1:v2);
        System.out.println(cus.get());
        
        Optional<Integer> obj = list.stream().filter(custo->custo.getAge()%2==0).map(customer1->customer1.getAge()).reduce((val1,val2)->(val1+val2));
        System.out.println("Sum of Age: "+obj.get());
        
        cus = list.stream().reduce((v1,v2)->v1.getAge()>v2.getAge()?v1:v2);
        System.out.println(cus+"...");
        
        Optional<Customer> customer = list.stream().max((v1,v2)->v1.getAge()-v2.getAge());
        System.out.println(customer.get());

        System.out.println(list.stream().sorted((v1,v2)->v1.getAge()-v2.getAge()).collect(Collectors.toList()));
        
        Customer dto = new Customer(10,"Shahabuddin Ansari",Stream.of("123","4567","6789").collect(Collectors.toList()));
        Customer newDto = new Customer();
        for (Field dtoField : dto.getClass().getDeclaredFields()) {
            dtoField.setAccessible(true);
            try {
                Object value = dtoField.get(dto);
                if (value != null) {
                    Field entityField = Customer.class.getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);
                    entityField.set(newDto, value);
                    System.out.println(entityField.getName()+":"+value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error updating field: " + dtoField.getName(), e);
            }
        }
		System.out.println("New DTO"+newDto);
	}

}
