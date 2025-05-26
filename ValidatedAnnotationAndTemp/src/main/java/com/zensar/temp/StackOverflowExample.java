package com.zensar.temp;
public class StackOverflowExample {
    public static void recursiveMethod() {
        recursiveMethod(); // No base case leads to infinite recursion
    }

    public static void main(String[] args) {
        recursiveMethod();
    }
}
