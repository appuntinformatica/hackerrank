package algorithms.implementation.almostSorted;

import java.io.*;
import java.util.*;

/* https://www.hackerrank.com/challenges/almost-sorted */
public class Solution {

    private static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    private static void reverse(int[] a, int startIndex, int endIndex) {
        while ( startIndex < endIndex ) {
            int swap = a[startIndex];
            a[startIndex] = a[endIndex];
            a[endIndex] = swap;
            startIndex++;
            endIndex--;
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] d = new int[n];

        for (int index = 0; index < d.length; index++) {
            d[index] = in.nextInt();
        }

        int back = d[0];
        int index1 = -1;
        int index2 = -1;
        for (int index = 1; index < d.length; index++) {
            if ( back > d[index] ) {
                if ( index1 == -1 ) {
                    index1 = index - 1;
                    index2 = index;
                } else
                    index2 = index;
            }
            back = d[index];
        }
        
        if ( index2 == -1 )
            index2 = d.length - 1;
        
        int[] d1 = Arrays.copyOf(d, d.length);
        if ( index1 == -1 && index2 == -1 ) {
            System.out.println("yes");
        } else if ( isSorted(d1) ) {
            System.out.println("yes");
        } else {
            int swap = d1[index1];
            d1[index1] = d1[index2];
            d1[index2] = swap;
            if ( isSorted(d1) ) {
                System.out.println("yes");
                System.out.println("swap " + (index1 + 1) + " " + (index2 + 1));
            } else {
                reverse(d, index1, index2);
                if ( isSorted(d) ) {
                    System.out.println("yes");
                    System.out.println("reverse " + (index1 + 1) + " " + (index2 + 1));
                } else {
                    System.out.println("no");
                }
            }
        }
    }
}