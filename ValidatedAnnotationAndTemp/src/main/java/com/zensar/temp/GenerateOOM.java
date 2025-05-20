package com.zensar.temp;

public class GenerateOOM {
    public static void main(String[] args) throws Exception {
        GenerateOOM memoryTest = new GenerateOOM();
        memoryTest.generateOOM();
    }
    public void generateOOM() throws Exception {
        int iteratorValue = 20;
        for(int i=0;i<100;i++) {
        	iteratorValue = iteratorValue*100;
        	Integer []a=new Integer[iteratorValue];
        	System.out.println(" Free Memory: " +Runtime.getRuntime().freeMemory());
        }
        System.out.println("\n=================> OOM test started..\n");
    }
}