package com.zensar.temp;
import java.util.List;
@FunctionalInterface
interface MyInterface{  
    public String getUserName();  
} 
public class MethodReference {  
	private String name;
	MethodReference(){
		this.setName("Zensar");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    public static void main(String[] args) { 
    	
    	List<Transaction> transaction = Transaction.getTrasaction();
    	transaction.stream().map(Transaction::getCategory).forEach(System.out::println);
    	
    	MethodReference obj = new MethodReference();
    	MyInterface fun = obj::getName;
    	System.out.println(fun.getUserName());
    }
}