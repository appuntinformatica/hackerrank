package algorithms.dynamicProgramming.stockMaximize;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/stockmax */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tMax = scanner.nextInt();
        for (int tCount = 0; tCount < tMax; tCount++) {
            int n = scanner.nextInt();
            int[] data = new int[n];
            for (int i = 0; i < data.length; i++) {
                data[i] = scanner.nextInt();
            }
            
            int max = 0;
            Deque<Integer> sellPoints = new ArrayDeque<Integer>();
            for (int i = n; i-- > 0;){
                if (data[i] > max){
                    max = data[i];
                    sellPoints.push(i);
                }
            }
            long profit = 0;
            int s = sellPoints.size();
            int i = 0;
            while (s > 0) {
                int j = sellPoints.pop();
                while (i < j) {
                    profit += data[j] - data[i];
                    i++;
                }
                i++;
                s--;
            }
            System.out.println(profit);
        }
    }
}
