package com.zensar.java17;
// Sealed class

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


