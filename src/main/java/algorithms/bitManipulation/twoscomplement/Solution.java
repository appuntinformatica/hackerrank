package algorithms.bitManipulation.twoscomplement;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int countOnes(int n) {
        int result = 0;
        
        if ( n < 0 ) {
            for ( int index = 0; index < 32; index++ ) {
                if ( n % 2 != 0 ) {
                    result++;
                }
                n = n >> 2;
            }
        } else if ( n > 0 ) {
            for ( int index = 0; index < 32; index++ ) {
                if ( n % 2 == 0 ) {
                    result++;
                }
                n = n >> 2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tMax = scanner.nextInt();
        for (int tCount = 0; tCount < tMax; tCount++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            
            int count = 0;
            for ( int n = A; n <= B; n++ ) {
                count += countOnes( n );
            }
            
            System.out.println("" + count);
        }
    } 
}