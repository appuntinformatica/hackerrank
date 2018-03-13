package algorithms.implementation.encryption;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
       /* https://www.hackerrank.com/challenges/encryption */

  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.next();
        int L = input.length();
        double e = Math.pow(L, 0.5);
        int rows = (int) Math.floor(e);
        int cols = (int) Math.ceil(e);
        
        while ( rows * cols < L ) {
            if (rows < cols) {
                rows++;
            } else {
                cols++;
            }
        }
        
        char[][] matrix = new char[rows][cols];
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (index < input.length()) {
                    matrix[r][c] = input.charAt(index);
                    index++;
                }
            }    
        }
        String output = "";
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if (matrix[r][c] != 0) {
                    output += matrix[r][c];
                }
            }
            output += " ";
        }
        output = output.substring(0, output.length() - 1);
        System.out.println(output);
    }

    
}
