package algorithms.implementation.theTimeInWords;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 /* https://www.hackerrank.com/challenges/the-time-in-words */

    private static Map<Integer, String> numbers = new HashMap() {
            {
                put(1, "one");
                put(2, "two");
                put(3, "three");
                put(4, "four");
                put(5, "five");
                put(6, "six");
                put(7, "seven");
                put(8, "eight");
                put(9, "nine");
                put(10, "ten");
                put(11, "eleven");
                put(12, "twenty");
                put(13, "thirteen");
                put(20, "twenty");
            }
        };
    
    private static String composeNumbers(int n) {
        String compose = numbers.get(n);
        if ( compose == null ) {
            int n1 = n / 10;
            n1 = n1 * 10;
            int n0 = n - n1;
            compose = numbers.get(n1) + " " + numbers.get(n0);
        }
        return compose;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int m = in.nextInt();
        
        String output = "";
        if ( m == 0) {
            output = numbers.get(h) + " o' clock";
        } else if (m == 1) {
            output = "one minute past " + numbers.get(h);
        } else if (m == 15) {
            output = "quarter past " + numbers.get(h);
        } else if (m < 30) {
            output = composeNumbers(m) + " minutes past " + numbers.get(h);
        } else if (m == 30) {
            output = "half past " + numbers.get(h);
        } else if (m == 45) {
            output = "quarter to " + numbers.get(h + 1);
        } else {
            m = 60 - m;
            output = composeNumbers(m) + " minutes to " + numbers.get(h + 1);
        }
        
        System.out.println(output);
    }
}
