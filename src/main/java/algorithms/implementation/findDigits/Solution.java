package algorithms.implementation.findDigits;

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
            int number = scanner.nextInt();
            String line = Integer.toString(number);
            int s = 0;
            for (int j = 0; j < line.length(); j++) {
                int d = Integer.parseInt( line.substring(j, j + 1) );
                if (d != 0 && number % d == 0) {
                    s++;
                }
            }
            System.out.println(s);
        }
    }
}
