package com.zensar.java17;
// Sealed class
//Sealed abstract base class
 sealed class Shape permits Circle, Rectangle, Triangle {
  double area() {
	  return 10;
  }
}

//Final subclass - cannot be extended
 final class Rectangle extends Shape {
 @Override
  double area() {
     return 10.0;
 }
}

//Sealed subclass - controls its own subclassing
 sealed class Triangle extends Shape permits EquilateralTriangle {
 @Override
  double area() {
     return 15.0;
 }
}

//Final permitted subclass of Triangle
 final class EquilateralTriangle extends Triangle {
 @Override
  double area() {
     return 12.0;
 }
}

//Non-sealed subclass - open for further extension
 non-sealed class Circle extends Shape {
 @Override
  double area() {
     return 20.0;
 }
}

//A class extending non-sealed Circle
 class ColoredCircle extends Circle {
 @Override
  double area() {
     return 25.0;
 }
}


 public class TestSealed {
	    public static void main(String[] args) {
	        Shape s1 = new Rectangle();
	        Shape s2 = new Circle();
	        Shape s3 = new EquilateralTriangle();
	        Shape s4 = new ColoredCircle();

	        System.out.println(s1.area()); // 10.0
	        System.out.println(s2.area()); // 20.0
	        System.out.println(s3.area()); // 12.0
	        System.out.println(s4.area()); // 25.0
	    }
	}