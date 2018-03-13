package algorithms.gameTheory.gameOfStones;


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/game-of-stones-1*/
public class Solution {

    private static enum Player {
        First,
        Second;
    }
    
    private static Player winner(int n, Map<Integer, Player> steps) {
        Player result = Player.First;
        if ( n == 1 ) {
            result = Player.Second;
        } else if ( 2 <= n  && n <= 5 ){
            result = Player.First;
        } else if ( steps.get(n) == null ) {
            steps.put(n, Player.First);
            Player p = winner(n - 5, steps);
            if ( p == Player.First ) {
                p = winner(n - 3, steps);
                if ( p == Player.First ) {
                    p = winner(n - 2, steps);
                    if ( p == Player.First ) {
                        steps.put(n, Player.Second);        
                    }
                }
            }
            result = steps.get(n);
        } else {
            result = steps.get(n);
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tMax = in.nextInt();

        for (int tCount = 0; tCount < tMax; tCount++) {
            int stones = in.nextInt();

            TreeMap<Integer, Player> steps = new TreeMap<>();
            System.out.println( winner(stones, steps) );
        }
    }
    
}
