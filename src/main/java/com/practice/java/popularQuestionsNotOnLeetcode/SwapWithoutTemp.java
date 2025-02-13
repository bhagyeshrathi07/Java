package com.practice.java.popularQuestionsNotOnLeetcode;

public class SwapWithoutTemp {

    public static void swap(int x, int y) {
        System.out.println("elements before swap: " + x + ", " + y);
        x = x + y;
        y = x - y;
        x = x - y;
        System.out.println("elements after swap: " + x + ", "+ y);
    }

    public static void main(String[] args) {
        swap(23, 25);
    }
}
