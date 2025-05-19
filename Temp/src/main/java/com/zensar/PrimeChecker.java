package com.zensar;

public class PrimeChecker {
	public static boolean isPrime(int n) {
		System.out.println(Math.sqrt(n));
		if (n <= 1)
			return false; // 0 and 1 are not prime
		if (n == 2)
			return true; // 2 is prime
		if (n % 2 == 0)
			return false; // even numbers greater than 2 are not prime

		for (int i = 3; i <= Math.sqrt(n); i += 2) {
			System.out.println(i);
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int number = 97;
		if (isPrime(number)) {
			System.out.println(number + " is a prime number.");
		} else {
			System.out.println(number + " is not a prime number.");
		}
	}
}
