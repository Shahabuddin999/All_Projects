package com.zensar.temp;

import java.util.Arrays;

public class BinarySearchExample {
	public static void main(String[] args) {

		int[] numbers = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		Arrays.sort(numbers);
		int target = 9;
		int index = binarySearch(numbers, target);

		if (index == -1) {
			System.out.println("Number not found in the list.");
		} else {
			System.out.println("Number found at position: " + (index + 1));
		}
	}

	// Binary search method
	public static int binarySearch(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				return mid; // Return the index if found
			} else if (array[mid] < target) {
				left = mid + 1; // Search in the right half
			} else {
				right = mid - 1; // Search in the left half l=0,r=6, mid=5
			}
		}

		return -1; // Return -1 if not found
	}
}
