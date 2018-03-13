package algorithms.implementation.libraryFine;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    /* https://www.hackerrank.com/challenges/library-fine */
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int d1 = in.nextInt();
        int m1 = in.nextInt();
        int y1 = in.nextInt();
        int d2 = in.nextInt();
        int m2 = in.nextInt();
        int y2 = in.nextInt();

        int hackos = 0;
        if ( y1 == y2 ) {
            if ( m1 == m2 ) {
                if ( d1 > d2 ) {
                    hackos = (d1 - d2) * 15;
                }
            } else if ( m2 < m1 ) {
                hackos = (m1 - m2) * 500;
            }
        } else if ( y1 > y2) {
            hackos = 10000;
        }
        System.out.println(hackos);
    }
}
