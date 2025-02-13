package com.practice.java.popularQuestionsNotOnLeetcode;

public class MissingNumber {
   // Search and return missing number in an array containing integers from 1 to n

   public static int searchMissingNumber(int[] nums, int n) {
       int expectedSum = (n * (n + 1))/2;
       int actualSum = 0;

       for(int num : nums) {
           actualSum += num;
       }

       return expectedSum - actualSum;
   }
   public static void main(String[] args) {
        int[] arr = new int[99];
        for(int i = 0, num = 1; i < arr.length; num++) {
            if(num == 10) continue;
            arr[i++] = num;
        }
        System.out.println(searchMissingNumber(arr, 100));
   }
}
