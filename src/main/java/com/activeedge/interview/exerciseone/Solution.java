package com.activeedge.interview.exerciseone;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] ar = {1, 3, 6, 4, 1, 2};
        int[] ar2 = {5, -1, -3};

        getTheIndexOfTheLowestNonrecurringInteger(ar);
    }

    private static void getTheIndexOfTheLowestNonrecurringInteger(int[] ar) {
        int[] tempAr = Arrays.copyOf(ar, ar.length);

        logIndexOfSmallestNonOccurringInteger(ar, smallestNonRepeatingInteger(sort(tempAr), ar.length));
    }

    private static void logIndexOfSmallestNonOccurringInteger(int[] ar, int smallestNonRepeatingInteger) {
        for (int i = 0; i < ar.length; i++)
            if (Math.abs(ar[i]) == smallestNonRepeatingInteger) System.out.println(i);
    }

    public static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int temp = Math.abs(arr[j]);
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = Math.abs(arr[j - 1]);
                j--;
            }
            arr[j] = temp;
        }
        return arr;
    }


    static int smallestNonRepeatingInteger(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            int j;
            for (j = 0; j < n; j++)
                if (i != j && arr[i] == arr[j])
                    break;
            if (j == n) return arr[i];
        }

        throw new UnsupportedOperationException("No non occurring element");
    }


}


