package com.practice.java.popularQuestionsNotOnLeetcode;

import java.util.HashSet;

public class FindNumberNotPresentInSecondArray {

    public static int find(int[] arr1, int[] arr2) {
        HashSet<Integer> elements = new HashSet<>();
        for(int num : arr2) {
            elements.add(num);
        }
        for(int num : arr1) {
            if(!elements.contains(num)) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {2,3,1,0,5};

        System.out.println(find(arr1, arr2));
    }
}
