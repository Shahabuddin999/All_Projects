package com.zensar.interview;

class Animal {

	public void intro(Object animalName){
		System.out.println("This is an animal class. Animal Name : "+animalName);
	}
}

class Cat extends Animal{
	
	public void intro(String catName){
		System.out.println("This as a cat class. Cat Name : "+catName);  
	}
}

public class Main{
	public static void main(String[] args){
	Animal unknown= new Cat();
	Object guessMe= "Doja";
	unknown.intro(guessMe);
	unknown.intro(null);
	}
}