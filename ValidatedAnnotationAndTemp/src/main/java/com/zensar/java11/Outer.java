package com.zensar.java11;

// Java's Nest-Based Access Control (introduced in Java 11)
public class Outer {
    private String secret = "Java 11 Rocks!";

    class Inner {
        void revealSecret() {
            System.out.println(secret); // Accessing private member of Outer
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.revealSecret();
    }
}