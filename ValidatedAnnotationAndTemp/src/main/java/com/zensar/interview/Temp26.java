package com.zensar.interview;

public class Temp26 {

	public static void main(String[] args) {
//		String name = "abcdabcdeabcdefgpqrstab";
//		name.chars().mapToObj(ch->{
//			if(ch == )
//			return null;
//		});
		
		//Input - [7,1, 5, 3, 6, 4]
				 
		int arr[] = {15,5,9,6,4};
		
		int max=0,min=9;
		for(int i =0; i<arr.length; i++) {
			if(arr[i]>max) {
				max = arr[i];
			}
			if(min>arr[i]) {
				min=arr[i];
			}
		}
		System.out.println(max);
		System.out.println(min);
	}
}
