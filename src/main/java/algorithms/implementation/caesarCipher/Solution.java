package algorithms.implementation.caesarCipher;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    /* https://www.hackerrank.com/challenges/caesar-cipher-1 */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int k = in.nextInt();
        int step = k % 26;
        char[] encodedString = new char[n];
        
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if ( ch >= 'A' && ch <= 'Z' ) {
                int diff = 'Z' - ch;                    
                if ( diff < step ) {
                    ch = 'A';
                    ch += (step - diff - 1);
                }
                else
                    ch += step;
            } else if ( ch >= 'a' && ch <= 'z' ) {
                int diff = 'z' - ch;
                if ( diff < step ) {
                    ch = 'a';
                    ch += (step - diff - 1);
                } 
                else 
                    ch += step;
            }
            encodedString[i] = ch;
        }
            
        System.out.println(new String(encodedString));
    }
}
