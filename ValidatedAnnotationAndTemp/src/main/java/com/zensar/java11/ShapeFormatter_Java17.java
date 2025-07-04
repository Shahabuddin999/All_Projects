package com.zensar.java11;
public class ShapeFormatter_Java17 {

    static String formatShape(Object shape) {
        return switch (shape) {
            case String s -> {
            	yield "String with length: " + s.length();
            	
            }
            case Integer i -> "Integer: " + i;
            case null -> "Null input";
            default -> "Unknown type";
        };
    }

    public static void main(String[] args) {
        System.out.println(formatShape("Hello"));       // String with length: 5
        System.out.println(formatShape(42));            // Integer: 42
        System.out.println(formatShape(null));          // Null input
        System.out.println(formatShape(3.14));          // Unknown type (Double)
        System.out.println(formatShape(true));          // Unknown type (Boolean)
    }
}

// Compile: javac --enable-preview --release 17 ShapeFormatter.java
// Run: java --enable-preview ShapeFormatter
// because its just preview in java 17 and finalized in java 21