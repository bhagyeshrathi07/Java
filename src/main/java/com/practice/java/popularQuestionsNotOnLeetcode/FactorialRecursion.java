package com.practice.java.popularQuestionsNotOnLeetcode;

public class FactorialRecursion {

    public static int factorial(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;

        return n * factorial(n-1);
    }

    public static void main(String[] args) {
        System.out.println(factorial(2));
        System.out.println(factorial(3));
        System.out.println(factorial(4));
        System.out.println(factorial(5));
    }
}
