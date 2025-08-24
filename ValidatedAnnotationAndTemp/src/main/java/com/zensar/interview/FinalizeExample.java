package com.zensar.interview;
public class FinalizeExample {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize method called for object");
    }

    public static void main(String[] args) {
        FinalizeExample obj1 = new FinalizeExample();
        FinalizeExample obj2 = new FinalizeExample();

        obj1 = null; // eligible for GC
        obj2 = null; // eligible for GC

        // Request JVM to run Garbage Collector
        System.gc();

        System.out.println("End of main method");
    }
}
