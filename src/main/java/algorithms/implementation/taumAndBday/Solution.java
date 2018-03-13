package algorithms.implementation.taumAndBday;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/taum-and-bday */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            long b = in.nextLong();
            long w = in.nextLong();
            long x = in.nextLong();
            long y = in.nextLong();
            long z = in.nextLong();

            long costBlack = b * x;
            long costWhite = w * y;
            long cost = costBlack + costWhite;
            if (x > y) {
                long reducedCost = costWhite + b * (y + z);
                if (reducedCost < cost) {
                    cost = reducedCost;
                }
            } else if (x < y) {
                long reducedCost = costBlack + w * (x + z);
                if (reducedCost < cost) {
                    cost = reducedCost;
                }
            }
            System.out.println(cost);
        }
    }
}
