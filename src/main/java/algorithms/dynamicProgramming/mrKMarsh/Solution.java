package algorithms.dynamicProgramming.mrKMarsh;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/mr-k-marsh */

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        boolean[][] matrix = new boolean[m][n];                
        for (int row = 0; row < m; row++) {
            String line = scanner.next();
            for (int col = 0; col < n; col++) {
                if ( line.charAt(col) == 'x' )
                    matrix[row][col] = true;
                else
                    matrix[row][col] = false;
            }
        }
        int[][] horizontal = new int[m][n];
        int[][] vertical = new int[m][n];
        
        for (int row = 0; row < m; row++) {
            int count = 0;
            for (int col = 0; col < n; col++) {
                if (matrix[row][col]) {
                    count = -1;
                }
                horizontal[row][col] = count;
                count++;
            }
        }
        for (int col = 0; col < n; col++) {
            int count = 0;
            for (int row = 0; row < m; row++) {
                if (matrix[row][col]) {
                    count = -1;
                }
                vertical[row][col] = count;
                count++;
            }
        }
        
        int maxPerimetre = -1;
        
        for (int row1 = 0; row1 < m; row1++) {
            for (int row2 = row1 + 1; row2 < m; row2++) {
                List<Integer> v = new ArrayList<>();
                int rdiff = row2 - row1;
                for (int j = 0; j < n; j++) {
                    if (vertical[row2][j] >= rdiff)
                        v.add(j);
                }
                int l = 0;
                for (int row = 0;row < v.size(); row++) {
                    int min_left = v.get(row) - Math.min( horizontal[row1][v.get(row)] , horizontal[row2][v.get(row)] );
                    while (v.get(l) < min_left)
                        l++;
                    if (v.get(row) > v.get(l))
                        maxPerimetre = Math.max(maxPerimetre, 2 * (rdiff) + 2 * (v.get(row) - v.get(l)) );
                }
            }
        }

        if (maxPerimetre == -1)
            System.out.println("impossible");
        else
            System.out.println(maxPerimetre);
    }
}
