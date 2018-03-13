package algorithms.implementation.theBombermanGame;

import java.util.Scanner;

/* https://www.hackerrank.com/challenges/bomber-man */
public class Solution {
    
    private static class Cell {
        public boolean bomb;
        public int counter;
        
        public Cell() {
            this.bomb = false;
            this.counter = 1;
        }
    }
    
    private static void update1(Cell[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if ( matrix[row][col].bomb ) {
                    matrix[row][col].counter++;
                } else {
                    matrix[row][col].bomb = true;
                    matrix[row][col].counter = 0;
                }
            }
        }
    }
    
    private static void update2(Cell[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if ( matrix[row][col].bomb ) {
                    if ( matrix[row][col].counter >= 3 ) {
                        matrix[row][col] = new Cell();
                        
                        if ( row < matrix.length - 1 )
                            matrix[row + 1][col] = new Cell();
                        if ( row > 0 )
                            matrix[row - 1][col] = new Cell();
                        
                        if ( col < matrix[row].length - 1 )
                            matrix[row][col + 1] = new Cell();
                        if ( col > 0 )
                            matrix[row][col - 1] = new Cell();
                    } else {
                        matrix[row][col].counter++;
                    }
                }
            }
        }
    }
    
    private static void print(Cell[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if ( matrix[row][col].bomb ) {
                    System.out.print("O");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }
    
    private static void printDebug(Cell[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if ( matrix[row][col].bomb ) {
                    System.err.print("O");
                } else {
                    System.err.print(".");
                }
            }
            System.err.println("");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt();
        int C = in.nextInt();
        int N = in.nextInt();
        
        Cell[][] matrix = new Cell[R][C];
        for (int row = 0; row < R; row++) {
            String line = in.next();
            for (int col = 0; col < C; col++) {
                matrix[row][col] = new Cell();
                if ( line.charAt(col) == 'O' ) {
                    matrix[row][col].bomb = true;
                    matrix[row][col].counter = 1;
                }
            }
        }
              
        int nextUpdate = 1;
        for (int time = 0; time < N; time++) {
            if ( nextUpdate == 1 ) {
                update1(matrix);
                nextUpdate = 2;
            } else {
                update2(matrix);
                nextUpdate = 1;
            }
            printDebug(matrix);
            System.err.println("---------------------");
        }
        
        print(matrix);
    }

}
