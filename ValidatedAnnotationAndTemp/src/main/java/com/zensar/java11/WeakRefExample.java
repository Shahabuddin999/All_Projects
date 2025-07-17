package com.zensar.java11;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WeakRefExample {

	public static void main(String[] args) {
		List<Float> list = List.of(10000f,15000f,18000f,30000f);
		list.stream().map(sal-> sal<20000 ? sal+(10f/100f)*sal: sal).forEach(System.out::println);
		System.out.println((float)10/100);
		
		MyObject strong = new MyObject("demo");
        WeakReference<MyObject> weak = new WeakReference<>(strong);

        System.out.println("Before GC: " + weak.get());

        strong = null;
        System.gc();

        System.out.println("After GC: " + weak.get()); // May print null
        
        String name = "Shahabuddin ansari koraon prayagraj";
        Optional<String> reduce1 = name.chars().mapToObj(str->String.valueOf((char)str)).reduce((a,b)->b+a);
        System.out.println(reduce1.get());
        for (int i = name.length() - 1; i >= 0; i--) {
            System.out.print(name.charAt(i));
        }
        System.out.println();
        Arrays.stream(name.split(" ")).forEach(str->{
        	Optional<String> reduce = str.chars().mapToObj(ch->String.valueOf((char)ch)).reduce((a,b)->b+a);
        	System.out.print(reduce.get()+" ");
        });
       
        
	}
}

class MyObject {
    String name;
    MyObject(String name) { this.name = name; }
    public String toString() { return name; }
}

