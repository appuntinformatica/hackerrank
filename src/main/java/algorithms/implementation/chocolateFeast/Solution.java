package algorithms.implementation.chocolateFeast;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) {    
        Scanner scanner = new Scanner(System.in);
        int numbersOfTestCase = scanner.nextInt();
        for (int i = 0; i < numbersOfTestCase; i++) {
            int n = scanner.nextInt();
            int c = scanner.nextInt();
            int m = scanner.nextInt();

            int answer = 0;
            int wrapper = 0;
            while (n > 0 && n >= c) { 
                n -= c;
                answer += 1;
                wrapper += 1;
                if (wrapper == m) {
                    answer += 1;
                    wrapper = 1;
                }
            } 
            System.out.println(answer);
        }
    }
}