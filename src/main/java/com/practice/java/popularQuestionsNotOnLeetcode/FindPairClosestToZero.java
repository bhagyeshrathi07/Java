package com.practice.java.popularQuestionsNotOnLeetcode;

import java.util.Arrays;

public class FindPairClosestToZero {

    public static int[] findPair(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;
        int minSum = Integer.MAX_VALUE;
        int[] pair = new int[2];
        while(left < right) {
            int sum = nums[left] + nums[right];
            if(Math.abs(sum) < Math.abs(minSum)) {
                minSum = sum;
                pair = new int[] {nums[left], nums[right]};
            }
            if(sum == 0) {
                return pair;
            }
            else if(sum < 0) {
                left++;
            }
            else {
                right--;
            }
        }
        return pair;
    }

    public static void main(String[] args) {
        int[] arr = {1, 60, -10, 70, -80, 85};

        int[] res = findPair(arr);
        for(int n : res) {
            System.out.println(n);
        }
    }
}
