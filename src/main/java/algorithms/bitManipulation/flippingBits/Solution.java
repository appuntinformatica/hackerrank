package algorithms.bitManipulation.flippingBits;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/* https://www.hackerrank.com/challenges/flipping-bits */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tMax = in.nextInt();
        long[] powers = new long[32];
        powers[0] = 1;
        for (int p = 1; p < 32; p++) {
            powers[p] = 2 * powers[p - 1];
        }
        for (int tCount = 0; tCount < tMax; tCount++) {
            long input = in.nextLong();
            boolean[] data = new boolean[32];
            int index = 0;
            while (input != 0) {
                data[index] = input % 2 != 0;
                input = input / 2;
                index++;
            }
            long output = 0;
            for (index = 0; index < data.length; index++) {
                if (!data[index]) {
                    output += powers[index];
                }
            }
            System.out.println(output);
        }
    }
}
