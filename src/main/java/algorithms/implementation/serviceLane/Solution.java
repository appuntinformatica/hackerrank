package algorithms.implementation.serviceLane;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    /* https://www.hackerrank.com/challenges/service-lane */
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        int width[] = new int[n];
        for (int width_i = 0; width_i < n; width_i++) {
            width[width_i] = in.nextInt();
        }
        for (int a0 = 0; a0 < t; a0++) {
            int i = in.nextInt();
            int j = in.nextInt();
            int min = width[i];
            for (int index = i + 1; index <= j; index++) {
                if ( min > width[index] ) {
                    min = width[index];
                }
            }
            System.out.println(min);
            
        }
    }
}
