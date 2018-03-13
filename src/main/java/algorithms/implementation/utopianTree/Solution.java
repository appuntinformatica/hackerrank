package algorithms.implementation.utopianTree;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
         
    public static void main(String[] args) {    
        Scanner scanner = new Scanner(System.in);
        int numbersOfTestCase = scanner.nextInt();
        for (int i = 0; i < numbersOfTestCase; i++) {
            int n = scanner.nextInt();
            int h = 1;
            for (int j = 0; j < n; j++) {
                if (j % 2 == 0)
                    h *= 2;
                else
                    h += 1;
            }
            System.out.println(h);
        }
    }
}
