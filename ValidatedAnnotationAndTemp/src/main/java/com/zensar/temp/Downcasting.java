package com.zensar.temp;

interface Person{
	public void b();
	public void a();
}
public class Downcasting implements Person{
	public void c() {
		System.out.println("C method");
	}
	public Downcasting() {
	}
	@Override
	public void b() {
		System.out.println("B method");
	}
	@Override
	public void a() {
		System.out.println("A method");
	}
	public static void main(String[] args) {
		Person p = new Downcasting();
		p.a();
		p.b();
		((Downcasting)p).c();
	}
}
