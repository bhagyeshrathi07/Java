package com.practice.java.dsa.cisco;

import java.util.Arrays;

/**
 * The janitor of a high school is extremely efficient. By the end of each day,
 * all of the school's waste is in plastic bags weighing between 1.01 pounds and 3.00 pounds.
 * All plastic bags are then taken to the trash bins outside.
 * One trip is described as selecting a number of bags which together do not weigh more than 3.00 pounds,
 * dumping them in the outside trash can and returning to the school.
 * Given the number of plastic bags, n, and the weights of each bag, determine the minimum number of trips the janitor has to make.
 */
public class EfficientJanitor {

    public static int minTrips(float[] trashWeights) {
        int trips = 0;
        int n = trashWeights.length;
        Arrays.sort(trashWeights);
        float[] weights = new float[n];
        for(int i = 0; i < n; i++) {
            weights[i] = trashWeights[n-1-i];
        }
        int left = 0;
        int right = weights.length-1;
        while(left <= right) {
            if(left == right) {
                trips++;
                break;
            }
            if((weights[left] + weights[right]) <= 3) {
                left++;
                right--;
            } else {
                left++;
            }
            trips++;
        }
        return trips;
    }

    // Main method to test the implementation with expected results
    public static void main(String[] args) {
        // Test case where ascending order fails
        float[] weights = {1.4f, 1.5f, 1.6f, 1.7f};

        int resultAscending = minTrips(weights.clone());
        System.out.println("Trips with ascending order: " + resultAscending);
    }
}
