package com.zensar.interview;
class ExceptionPropagationwithHandling {
    void m1() {
        int data = 50 / 0;  // ArithmeticException
    }

    void m2() {
        m1();
    }

    void m3() {
        try {
            m2();
        } catch (ArithmeticException e) {
            System.out.println("Exception handled in m3: " + e);
        }
    }

    public static void main(String[] args) {
        ExceptionPropagationwithHandling t = new ExceptionPropagationwithHandling();
        t.m3();  // Safe execution
        System.out.println("Program continues...");
    }
}
