package algorithms.implementation.cavityMap;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    /* https://www.hackerrank.com/challenges/cavity-map */

    private static class Point {
        public int row;
        public int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public Point[] getPoints() {
            return new Point[] {
                new Point(row - 1, col),
                new Point(row, col - 1),
                new Point(row, col + 1),
                new Point(row + 1, col)
            };
        }
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] gridIn = new int[n][n];
        char[][] gridOut = new char[n][n];

        for (int row = 0; row < n; row++) {
            String line = in.next();
            for (int col = 0; col < line.length(); col++) {
                gridIn[row][col] = Integer.parseInt(Character.toString(line.charAt(col)));
                gridOut[row][col] = Character.forDigit(gridIn[row][col], 10);
            }
        }

        for (int row = 1; row < n - 1; row++) {
            for (int col = 1; col < n - 1; col++) {
                Point point = new Point(row, col);
                Point[] points = point.getPoints();
                boolean cavity = true;
                for (int index = 0; index < points.length && cavity; index++) {
                    if ( gridIn[point.row][point.col] <= gridIn[points[index].row][points[index].col] ) {
                        cavity = false;
                    }
                }
                if (cavity) {
                    gridOut[point.row][point.col] = 'X';
                }
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                System.out.print(gridOut[row][col]);
            }
            System.out.println("");
        }
    }
}
