package com.activeedge.interview.exerciseone;

public class Solution {
    public static void main(String[] args) {
        int[] ar = {1, 3, 6, 4, 1, 2};
        int[] tempAr = {1, 3, 6, 4, 1, 2};
        int[] sortedArr = sort(tempAr);

        logIndexOfSmallestNonOccurringInteger(ar, smallestNonRepeatingInteger(sortedArr, ar.length));
    }

    private static void logIndexOfSmallestNonOccurringInteger(int[] ar, int smallestNonRepeatingInteger) {
        for (int i = 0; i < ar.length; i++)
            if (ar[i] == smallestNonRepeatingInteger) System.out.println(i);
    }

    public static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int temp = arr[j];
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
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


