package algorithms.implementation.extraLongFactorials;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    /* https://www.hackerrank.com/challenges/extra-long-factorials */

    private static BigInteger factorial(BigInteger n) {
        if ( n.equals(BigInteger.ONE) ) {
            return BigInteger.ONE;
        } else {
            return n.multiply( factorial( n.add(BigInteger.ONE.negate()) ) );
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BigInteger n = in.nextBigInteger();
        System.out.println( factorial(n) );
    }
}
