package algorithms.implementation.gridSearch;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static class Point2D {
        public int row;
        public int col;

        public Point2D(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public static void main(String[] args) {    
        Scanner scanner = new Scanner(System.in);
        int numbersOfTestCase = scanner.nextInt();
        for (int i = 0; i < numbersOfTestCase; i++) {
            int rowNumbers = scanner.nextInt();
            int colNumbers = scanner.nextInt();
            int[][] matrix = new int[rowNumbers][colNumbers];
            for (int r = 0; r < rowNumbers; r++) {
                String row = scanner.next();
                for (int c = 0; c < colNumbers; c++) {
                    matrix[r][c] = (int) row.charAt(c);
                }
            }
            int patternRowNumbers = scanner.nextInt();
            int patternColNumbers = scanner.nextInt();
            int[][] pattern = new int[patternRowNumbers][patternColNumbers];
            for (int r = 0; r < patternRowNumbers; r++) {
                String row = scanner.next();
                for (int c = 0; c < patternColNumbers; c++) {
                    pattern[r][c] = (int) row.charAt(c);
                }
            }

            List<Point2D> points = new ArrayList<>();
            int data = pattern[0][0];
            for (int r = 0; r < rowNumbers; r++) {
                for (int c = 0; c < colNumbers; c++) {
                    if ( matrix[r][c] == data && 
                            (r + patternRowNumbers) <= rowNumbers &&
                            (c + patternColNumbers) <= colNumbers ) {
                        points.add(new Point2D(r, c));
                    }
                }
            }

            boolean founded = false;
            for (int k = 0; k < points.size() && !founded; k++) {
                Point2D p = points.get(k);
                boolean failed = false;
                for (int r = 0; r < patternRowNumbers && !failed; r++) {
                    for (int c = 0; c < patternColNumbers && !failed; c++) {
                        if ( pattern[r][c] != matrix[p.row + r][p.col + c] ) {
                            failed = true;
                        }
                    }
                }
                if (!failed)
                    founded = true;
            }
            
            if ( founded )
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
