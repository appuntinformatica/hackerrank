package algorithms.dynamicProgramming.mrKMarsh;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/mr-k-marsh */
public class Solution1 {
    
    /*
    http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
    http://www.scriptscoop.net/t/131e6ab5f958/algorithm-dynamic-programming-find-the-rectangle-in-a-grid-that-has-the-largest-.html
    https://github.com/indy256
    */
    
    public static class Size {
        final int width;
        final int height;

        public Size(int width, int height) {
            this.width = width;
            this.height = height;
        }

    }

    public static Size getMaxSize(boolean[][] matrix, Size[][] data, int row, int col, int m, int n) {
        for (int height = m - row - 1; height >= 1; height--) {
            for (int width = n - col - 1; width >= 1; width--) {
                if (checkMatrix(matrix, row, col, height, width)) {
                    return new Size(width, height);
                }
            }
        }
        return null;
    }
    
    public static int getMaxPerimetre(boolean[][] matrix, int row, int col, int m, int n) {
        int height = m - row - 1;
        int width = n - col - 1;
        boolean reducedWidth = false;
        while (height >= 1 && width >= 1) {
            if (checkMatrix(matrix, row, col, height, width)) {
                return (2 * (height + width));
            }
            if (reducedWidth) {
                height--;
                reducedWidth = false;
            } else {
                width--;
                reducedWidth = true;
            }
        }
        
        /*
        for (int height = m - row - 1; height >= 1; height--) {
            for (int width = n - col - 1; width >= 1; width--) {
                if (checkMatrix(matrix, row, col, height, width)) {
                    return (2 * (height + width));
                }
            }
        }
        */
        return 0;
    }
    
    public static int getMaxPerimetre(boolean[][] matrix, int m, int n) {
        int maxPerimetre = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int currPerimetre = getMaxPerimetre(matrix, row, col, m, n);
                if (currPerimetre > maxPerimetre) {
                    maxPerimetre = currPerimetre;
                }
            }
        }
        return maxPerimetre;
    }
   
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
        
        int maxPerimetre = getMaxPerimetre(matrix, m, n);

        if (maxPerimetre != 0)
            System.out.println(maxPerimetre);
        else
            System.out.println("impossible");
    }
    
    
    
    
    public static void main_ok(String[] args) {
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

        int maxPerimetre = 0;
        
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                for (int height = m - row - 1; height >= 1; height--) {
                    for (int width = n - col - 1; width >= 1; width--) {
                        int currPerimetre = 2 * (height + width);
                        if (currPerimetre > maxPerimetre) {
                            if ( checkMatrix(matrix, row, col, height, width) ) {
                                maxPerimetre = currPerimetre;
                            }
                        }
                    }
                }
            }
        }
        
        if (maxPerimetre != 0)
            System.out.println(maxPerimetre);
        else
            System.out.println("impossible");
    }
    
    
    
    
    
     
    public static int calculateMaximumPerimetre(char[][] matrix, int m, int n) {
        int maximumPerimetre = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                for (int height = 1; height < m - row; height++) {
                    for (int width = 1; width < n - col; width++) {
                        int currentPerimetre = 2 * (height + width);                        
                        if (currentPerimetre > maximumPerimetre) {
                            boolean failed = false;

                            for (int j = col; j <= col + width && !failed; j++) {
                                /*                            
                                if (matrix[row][j] == 'x' || matrix[row + height][j] == 'x')
                                    failed = true;
                                */
                            }
                            for (int i = row; i <= row + height && !failed; i++) {
                                /*
                                if (matrix[i][col] == 'x' || matrix[i][col + width] == 'x')
                                    failed = true;
                                */
                            }

                            if (!failed) {
                                maximumPerimetre = currentPerimetre;
                            }
                        }
                    }
                }
            }
        }
        return maximumPerimetre;
    }
    
    
    public static void main_(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        char[][] land = new char[m][n];
        int[][] sums = new int[m][n];
        for (int i = 0; i < m; i++) {
            String line = in.next();
            for (int j = 0; j < line.length(); j++) {
                land[i][j] = line.charAt(j);
                sums[i][j] = 0;
            }
        }
        
        int perimetre = calculateMaximumPerimetre(land, m, n);

        if (perimetre != 0) {
            System.out.println(perimetre);
        } else 
            System.out.println("impossible");
    }
    
    
    public static int calculateMaximumPerimetreBurteForce(char[][] matrix, int m, int n) {
        int maximumPerimetre = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                for (int height = 1; height < m - row; height++) {
                    for (int width = 1; width < n - col; width++) {
                        boolean failed = false;
                        for (int j = col; j <= col + width && !failed; j++) {
                            if (matrix[row][j] == 'x' || matrix[row + height][j] == 'x')
                                failed = true;
                        }
                        for (int i = row; i <= row + height && !failed; i++) {
                            if (matrix[i][col] == 'x' || matrix[i][col + width] == 'x')
                                failed = true;
                        }                        
                        if (!failed) {
                            int currentPerimetre = 2 * (height + width);
                            if (currentPerimetre > maximumPerimetre)
                                maximumPerimetre = currentPerimetre;
                        }
                    }
                }
            }
        }
        return maximumPerimetre;
    }
}
