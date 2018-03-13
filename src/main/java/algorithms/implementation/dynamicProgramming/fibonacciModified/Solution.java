package algorithms.implementation.dynamicProgramming.fibonacciModified;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
         
    public static void main(String[] args) {    
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        
        BigInteger tn = BigInteger.valueOf(a);
        BigInteger tn1 = BigInteger.valueOf(b);
        BigInteger tn2 = BigInteger.valueOf(0);
        for (int i = 3; i <= n; i++) {
            tn2 = tn1.multiply(tn1).add(tn);
            tn = tn1;
            tn1 = tn2;
        }
        System.out.println(tn2);
    }
}
