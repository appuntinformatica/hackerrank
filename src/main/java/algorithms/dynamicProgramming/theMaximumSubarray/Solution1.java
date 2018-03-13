package algorithms.dynamicProgramming.theMaximumSubarray;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution1 {


    
    public static int maxSubArraySum(int array[]) {
        int maxSoFar = 0, maxEndingHere = 0; 
        for (int i = 0; i < array.length; i++) {
        maxEndingHere = maxEndingHere + array[i];
        if (maxEndingHere < 0)
            maxEndingHere = 0;
        if (maxSoFar < maxEndingHere)
            maxSoFar = maxEndingHere;
        }
        return maxSoFar;
    }
    
    public static int maxSubArraySum2(int array[]) {
        int maxSoFar = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0)
                maxSoFar += array[i];
        }
        return maxSoFar;
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
            int maxSum = maxSubArraySum(data);
            int maxSum2 = maxSubArraySum2(data);
            System.out.println(maxSum + " " + maxSum2);
        }    
    }
}
