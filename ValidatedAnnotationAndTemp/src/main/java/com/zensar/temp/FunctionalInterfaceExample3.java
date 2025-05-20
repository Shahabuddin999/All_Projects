package com.zensar.temp;

class ParentClass {
    public void hello() {
        System.out.println("Hello ParentClass!");
    }
}

interface InterfaceFoo {
    public default void hello() {
        System.out.println("Hello InterfaceFoo!");
    }
}

interface InterfaceBar {
    public default void hello() {
        System.out.println("Hello InterfaceBar!");
    }
}

public class FunctionalInterfaceExample3 extends ParentClass implements InterfaceFoo, InterfaceBar {
    @Override
	public void hello() {
        super.hello(); // (note: ParentClass.super could not be used)
        InterfaceFoo.super.hello(); // This statement can be called inside the instance method only not in static method exp: can't be called inide main()
        InterfaceBar.super.hello();
    }
    
	/*
	 * public void Demo() { super.hello(); // (note: ParentClass.super could not be
	 * used) InterfaceFoo.super.hello(); // This statement can be called inside the
	 * instance method only not in static method exp: can't be called inide main()
	 * InterfaceBar.super.hello(); }
	 */
    
    public static void main(String[] args) {
    	FunctionalInterfaceExample3 obj = new FunctionalInterfaceExample3();
    	obj.hello();
    	//obj.Demo();
        // InterfaceFoo.super.hello(); this will not work in static method it will work inside instance method
        // InterfaceBar.super.hello();
    }
}

