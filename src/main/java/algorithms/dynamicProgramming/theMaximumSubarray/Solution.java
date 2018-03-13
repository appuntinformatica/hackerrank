package algorithms.dynamicProgramming.theMaximumSubarray;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static int maxsumRic(int array[], int i, int j) {
        if (i > j)
            return 0;
        if (i == j)
            return Math.max(0, array[j]);
        int m = (int) Math.ceil((i + j) / 2);
        int maxS = maxsumRic(array, i, m);
        int maxD = maxsumRic(array, m + 1, j);
        int sum = 0, maxS1 = 0, maxD1 = 0;
        for (int k = m; k >= 0; k--) {
            sum += array[k];
            if (sum > maxS1)
                maxS1 = sum;
        }
        sum = 0;
        for (int k = m + 1; k <= j; k++) {
            sum += array[k];
            if (sum > maxD1)
                maxD1 = sum;
        }
        return Math.max( Math.max(maxS, maxD), (maxS1 + maxD1) );
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);      
        int numbersOfTestcases = in.nextInt();
        for (int i = 0; i < numbersOfTestcases; i++) {
            int n = in.nextInt();
            int[] data = new int[n];
            for (int index = 0; index < n; index++) {
                data[index] = in.nextInt();
            }
            int maxSum = maxsumRic(data, 0, n - 1);
            int maxSum2 = maxSum;
            System.out.println(maxSum + " " + maxSum2);
        }    
    }
}
