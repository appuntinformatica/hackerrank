package algorithms.implementation.cutTheSticks;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    /* https://www.hackerrank.com/challenges/cut-the-sticks */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> data = new ArrayList<>(n);
        for (int arr_i = 0; arr_i < n; arr_i++) {
            data.add( in.nextInt() );
        }
        Collections.sort(data);
        int size = n, cutLength = 0, diff = 0;
        while (size > 0) {
            System.out.println(size);
            List<Integer> cuttedData = new ArrayList<>(n);
            cutLength = data.get(0);
            for (Integer e : data) {
                diff = e - cutLength;
                if (diff > 0) {
                    cuttedData.add(diff);
                }
            }
            size = cuttedData.size();
            data = cuttedData;
        }
    }
}
