package algorithms.dynamicProgramming.mrKMarsh;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/mr-k-marsh */
public class Solution2 {
    
    /*
    http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
    http://www.scriptscoop.net/t/131e6ab5f958/algorithm-dynamic-programming-find-the-rectangle-in-a-grid-that-has-the-largest-.html
    https://github.com/indy256
    */
    
    public static boolean checkMatrix(boolean[][] matrix, int row, int col, int height, int width) {
        for (int i = row; i <= row + height; i++) {
            if (matrix[i][col] || matrix[i][col + width])
                return false;
        }
        for (int j = col; j <= col + width; j++) {
            if (matrix[row][j] || matrix[row + height][j])
                return false;
        }
        return true;
    }
    
    public static boolean checkMatrix(int[][] up, int[][] left, int row, int col, int height, int width) {
        /*
        if (left[row][col] == -1)
            return false;
        if (left[row + height][col] == -1)
            return false;
        if (up[row][col] == -1)
            return false;
        if (up[row][col + width] == -1)
            return false;
        */
/*
        if ( left[row][col] != left[row + height][col] )
            return false;
        if ( left[row][col + width] != left[row + height][col + width] )
            return false;
        if ( up[row][col] != up[row][col + width] )
            return false;
        if ( up[row + height][col] != up[row + height][col + width] )
            return false;
        */
/*
        if ( left[row][col] != left[row + height][col] )
            return false;
        if ( up[row][col] != up[row][col + width] )
            return false;
        if ( left[row][col + width] != left[row + height][col + width] )
            return false;
        if ( up[row + height][col] != up[row + height][col + width] )
            return false;
        */
        return checkMatrixOK(up, left, row, col, height, width);
    }
    
    private static int WIDTH;
    private static int HEIGHT;
    private static int ROW;
    private static int COL;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        boolean[][] matrix = new boolean[m][n];
        Map<Integer, List<Integer>> marsh = new TreeMap<>();
        for (int row = 0; row < m; row++) {
            String line = scanner.next();
            for (int col = 0; col < n; col++) {
                if ( line.charAt(col) == 'x' ) {
                    matrix[row][col] = true;
                } else {
                    matrix[row][col] = false;
                    if ( marsh.get(row) == null ) {
                        marsh.put(row, new ArrayList<Integer>());
                    }
                    marsh.get(row).add(col);
                }
            }
        }
        int[][] left = new int[m][n];
        for (int row = 0; row < m; row++) {
            int count = 0;
            for (int col = 0; col < n; col++) {
                if (matrix[row][col])
                    count = -1;
                left[row][col] = count;
                count++;
            }
        }
        int[][] up = new int[m][n];
        for (int col = 0; col < n; col++) {
            int count = 0;
            for (int row = 0; row < m; row++) {
                if (matrix[row][col])
                    count = -1;
                up[row][col] = count;
                count++;
            }
        }

        int maxPerimetre = 0;
        
        for (int row : marsh.keySet()) {
            for (int col : marsh.get(row)) {
                for (int height = m - row - 1; height >= 1; height--) {
                    for (int width = n - col - 1; width >= 1; width--) {
                        int currPerimetre = 2 * (height + width);
                        if (currPerimetre > maxPerimetre) {
                            if (checkMatrix(up, left, row, col, height, width)) {
                                maxPerimetre = currPerimetre;
                                ROW = row;
                                COL = col;
                                WIDTH = width;
                                HEIGHT = height;
                            }
                        }
                    }
                }
            }
        }
        
        
        /*
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                for (int height = m - row - 1; height >= 1; height--) {
                    for (int width = n - col - 1; width >= 1; width--) {
                        int currPerimetre = 2 * (height + width);
                        if (currPerimetre > maxPerimetre) {
                            if (checkMatrix(up, left, row, col, height, width)) {
                                maxPerimetre = currPerimetre;
                                ROW = row;
                                COL = col;
                                WIDTH = width;
                                HEIGHT = height;
                            }
                        }
                    }
                }
            }
        
*/

        if (maxPerimetre != 0) {
            System.out.println(maxPerimetre);
            if (false) {
                System.err.println(ROW + " " + COL + " " + WIDTH + " " + HEIGHT);
                
                for (int row = ROW; row <= ROW + HEIGHT; row++) {
                    for (int col = COL; col <= COL + WIDTH; col++) {
                        if (matrix[row][col])
                            System.err.print("x ");
                        else
                            System.err.print(". ");
                    }
                    System.err.println(" ");
                }

            }
        } else
            System.out.println("impossible");
    }    
    
    
    public static boolean checkMatrixOK(int[][] up, int[][] left, int row, int col, int height, int width) {
        int diff = left[row][col + width] - Math.abs(left[row][col]) - width;
        if ( diff != 0 )
            return false;
        diff = left[row + height][col + width] - Math.abs(left[row + height][col]) - width;
        if ( diff != 0 )
            return false;
        diff = up[row + height][col] - Math.abs(up[row][col]) - height;
        if ( diff != 0 )
            return false;
        diff = up[row + height][col + width] - Math.abs(up[row][col + width]) - height;
        if ( diff != 0 )
            return false;
        return true;
    }
}
