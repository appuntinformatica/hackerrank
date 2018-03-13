package algorithms.implementation.dynamicProgramming.theLongestIncreasingSubsequence;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.regex.*;

public class Solution {
    
    private static int[] getVector() {
        try {
            List<String> lines = Files.readAllLines(new File("vector.txt").toPath(), Charset.defaultCharset());
            int output = Integer.parseInt(lines.get(0));
            int[] numbers = new int[lines.size() - 1];
            for (int index = 1; index < lines.size(); index++) {
                numbers[index - 1] = Integer.parseInt(lines.get(index));
            }
            return numbers;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    private static int _lis( int arr[], int n, Integer max_ref) {
        /* Base case */
        if (n == 1)
            return 1; 
        Integer max_ending_here = 1;
        int res; // length of LIS ending with arr[n-1]

        /* Recursively get all LIS ending with arr[0], arr[1] ... ar[n-2]. If 
           arr[i-1] is smaller than arr[n-1], and max ending with arr[n-1] needs
           to be updated, then update it */
        for(int i = 1; i < n; i++) {
            res = _lis(arr, i, max_ref);
            if (arr[i-1] < arr[n-1] && res + 1 > max_ending_here)
                max_ending_here = res + 1;
        }

        // Compare max_ending_here with the overall max. And update the
        // overall max if needed
        if (max_ref < max_ending_here)
           max_ref = max_ending_here;

        // Return length of LIS ending with arr[n-1]
        return max_ending_here;
    }
    
    private static int getLis2(int[] arr, int n) {
        int[] lis = new int[n];
        int lis_max = 0;
        for (int i = 0; i < arr.length; ++i) {
          // Find the smallest value greater than vec[i] in lis
            int lo = 0;
            int hi = lis_max - 1;
            while (lo <= hi) {
                double mid = Math.ceil((double) (lo + hi) / (double) 2.0);
                if (arr[i] > lis[(int) mid])
                    ++lo;
                else
                  --hi;
            }
            if (lo >= lis_max)
                ++lis_max;
            lis[lo] = arr[i];
        }
        return lis_max;     
    }

    /* lis() returns the length of the longest increasing subsequence in 
    arr[] of size n */
    private static int getLis( int arr[], int n ) {
       int i, j, max = 0;
       int[] lis = new int[n];

       /* Initialize LIS values for all indexes */
       for ( i = 0; i < n; i++ )
          lis[i] = 1;

       /* Compute optimized LIS values in bottom up manner */
       for ( i = 1; i < n; i++ )
          for ( j = 0; j < i; j++ )
             if ( arr[i] > arr[j] && lis[i] < lis[j] + 1)
                lis[i] = lis[j] + 1;

       /* Pick maximum of all LIS values */
       for ( i = 0; i < n; i++ )
          if ( max < lis[i] )
             max = lis[i];       
       return max;
    }
    
    public static void main(String[] args) {    
        int[] numbers = getVector();
        System.out.println(getLis2(numbers, numbers.length));
    }
    /*
        n = 5
        numbers = { 2 7 4 3 8 }
    */
    public static void main_(String[] args) {    
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int index = 0; index < n; index++) {
            numbers[index] = scanner.nextInt();
        }
        //int maxLIS = numbers.length;
        int maxLIS = 0;

        for (int index2 = numbers.length - 1; index2 > 0; index2--) {
            int max = numbers[index2];
            int maxLIS1 = 1;
            for (int index1 = index2 - 1; index1 >= 0; index1--) {
                if (numbers[index1] <= max) {
                    max = numbers[index1];
                    maxLIS1++;
                } else {
                    //max = numbers[index1 + 1];
                    //maxLIS1--;
                }
            }
            if (maxLIS1 > maxLIS)
                maxLIS = maxLIS1;
        }
        System.out.println(maxLIS);
    }
}
