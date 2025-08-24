package com.zensar.interview;
@FunctionalInterface
interface aa{
	void disp();
}
@FunctionalInterface
interface bb extends aa{
	void disp();
}
class A{
	static{
		System.out.println("static A");
	}
	{
		System.out.println("init A_1");
	}
	{
		System.out.println("init A_2");
	}
	A(){
		System.out.println("A constructor");
	}
}

class B extends A{
	static{
		System.out.println("static B");
	}
	{
		System.out.println("init B_1");
	}
	{
		System.out.println("init B_2");
	}
	B(){
		System.out.println("B constructor");
	}
}

public class Test11 {
    @SuppressWarnings("finally")
	public static int testReturn() {
        try {
            System.out.println("In try");
            return 0;
        } catch (Exception e) {
            System.out.println("In catch");
            return 1;
        } finally {
            System.out.println("In finally");
            return 2;
        }
    }

    public static void main(String[] args) {
    	new B();
    	System.out.println("==================================");
        System.out.println("Returned: " + testReturn());
    }
}
