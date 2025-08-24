package com.zensar.interview;
class ExceptionPropagation {
    void m1() {
        int data = 50 / 0;  // ArithmeticException
    }

    void m2() {
        m1();  // m1() call
    }

    void m3() {
        m2();  // m2() call
    }

    public static void main(String[] args) {
        ExceptionPropagation t = new ExceptionPropagation();
        t.m3();   // main call
    }
}
