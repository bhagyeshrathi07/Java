package com.practice.java.dsa.cisco;

/**
 * A number is considered a double base palindrome if it is palindromic in both decimal (base 10) and binary (base 2).
 * Write a program to find the sum of all double base palindrome numbers that are less than or equal to a given number.
 * The numbers should be included in the sum if they satisfy the palindrome condition in both bases.
 */
public class DoubleBasePalindromes {
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length()-1;
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isDoubleBasePalindrome(int num) {
        return isPalindrome(String.valueOf(num)) && isPalindrome(Integer.toBinaryString(num));
    }

    public static int sumDoubleBasePalindromes(int n) {
        int sum = 0;
        for(int i = 0; i <= n; i++) {
            if(isDoubleBasePalindrome(i)) {
                sum += i;
            }
        }
        return sum;
    }
    // Main method to test the function
    public static void main(String[] args) {
        int n = 5; // Example input
        int result = sumDoubleBasePalindromes(n);
        System.out.println("Sum of double base palindromes up to " + n + " is: " + result); // 9
    }
}
