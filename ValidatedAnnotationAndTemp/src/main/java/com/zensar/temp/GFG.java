package com.zensar.temp;
class GFG {
	public static void main(String[] args) {
		Geek g1 = new Geek("aa", 1, 20);
		Geek g2 = new Geek("aa", 1, 21);
		if (g1.hashCode() == g2.hashCode()) {
			if (g1.equals(g2))
				System.out.println("Both Objects are equal. ");
			else
				System.out.println("Both Objects are not equal. ");
		} else {
			System.out.println("Both Objects are not equal.... ");
		}
		
//		String name1 = "Shahabuddin";
//		String name2 = "Shahabuddin";
//		String name3 = new String("Shahabuddin");
//		String name4 = new String("Shahabuddin");
//		System.out.println(name1.hashCode());
//		System.out.println(name2.hashCode());
//		System.out.println(name3.hashCode());
//		System.out.println(name4.hashCode());
	}
}
