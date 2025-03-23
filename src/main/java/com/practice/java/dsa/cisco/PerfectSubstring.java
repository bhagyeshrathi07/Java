package com.practice.java.dsa.cisco;

/**
 * A string s is comprised of digits from 0 to 9.
 * A substring is considered perfect if all the elements within a substring occur exactly k times.
 * Calculate the number of perfect substrings in s.
 */
public class PerfectSubstring {

    public static int perfectSubstring(String str, int k) {
        if(str.isEmpty() || k <= 0) return 0;
        int count = 0;
        int n = str.length();
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(isPerfect(str.substring(i, j+1), k)) count++;
            }
        }
        return count;
    }

    public static boolean isPerfect(String subStr, int k) {
        int[] freq = new int[10];
        for(char c : subStr.toCharArray()) {
            freq[c - '0']++;
        }
        for(int i : freq) {
            if(i != 0 && i != k) return false;
        }
        return true;
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        // Test case from the example
        String s = "1102021222";
        int k = 2;
        int result = perfectSubstring(s, k);
        System.out.println("Number of perfect substrings: " + result);
        System.out.println("Expected result: 6 perfect substrings");

        // Additional test case
        String s2 = "1221";
        int k2 = 2;
        int result2 = perfectSubstring(s2, k2);
        System.out.println("Number of perfect substrings for test case 2: " + result2);
        System.out.println("Expected result for test case 2: 2 perfect substrings (12, 21)");
    }
}
