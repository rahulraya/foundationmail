//package com.example.foundationMail.Controller;
//
////public class SampleClass {
////	public static void main(String[] args) {
////		System.out.println("Hello, World!");
////	}
////
////}
//import java.util.Scanner;
//
//public class NegativeSubarrays {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        
//        // Read the length of the array
//        int n = scanner.nextInt();
//        int[] array = new int[n];
//        
//        // Read the elements of the array
//        for (int i = 0; i < n; i++) {
//            array[i] = scanner.nextInt();
//        }
//        
//        // Variable to count negative subarrays
//        int negativeSubarrayCount = 0;
//        
//        // Iterate over all possible subarrays
//        for (int start = 0; start < n; start++) {
//            int sum = 0;
//            for (int end = start; end < n; end++) {
//                // Calculate sum of the subarray from 'start' to 'end'
//                sum += array[end];
//                // Check if the sum is negative
//                if (sum < 0) {
//                    negativeSubarrayCount++;
//                }
//            }
//        }
//        
//        // Print the count of negative subarrays
//        System.out.println(negativeSubarrayCount);
//        
//        scanner.close();
//    }
//}