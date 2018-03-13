package algorithms.implementation.matrixLayerRotation;

import java.io.*;
import java.util.*;

/* https://www.hackerrank.com/challenges/matrix-rotation-algo */
public class Solution {
	
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int M = input.nextInt();
        int N = input.nextInt();
        int R = input.nextInt();
        
        int[][] matrix = new int[M][N];
        for (int row = 0; row < M; row++)
            for (int col = 0; col < N; col++)
                matrix[row][col] = input.nextInt();

        for (int count = 0; count < R; count++) {
            int startRow = 0;
            int startCol = 0;
            int maxRow = M;
            int maxCol = N;
            while ( startRow < maxRow && startCol < maxCol ) {
                int swap = matrix[startRow][startCol];
                for (int col = startCol + 1; col < maxCol; col++) {
                    matrix[startRow][col - 1] = matrix[startRow][col];    
                }
                for (int row = startRow; row < maxRow - 1; row++) {
                    matrix[row][maxCol - 1] = matrix[row + 1][maxCol - 1];
                }
                for (int col = maxCol - 1; col > startCol; col--) {
                    matrix[maxRow - 1][col] = matrix[maxRow - 1][col - 1];    
                }
                for (int row = maxRow - 1; row > startRow; row--) {
                    matrix[row][startCol] = matrix[row - 1][startCol];    
                }
                matrix[startRow + 1][startCol] = swap;
                startRow++;
                startCol++;
                maxRow--;
                maxCol--;
            }            
        }
        
        String out = "";
        for (int row = 0; row < matrix.length; row++) {
            String s = "";
            for (int col = 0; col < matrix[row].length; col++) {
                s += matrix[row][col] + " ";
            }
            s = s.substring(0, s.length() - 1);
            out += s + "\n";
        }        
        System.out.println(out);
    }
}
