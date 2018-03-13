package algorithms.implementation.acmICPCTeam;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/acm-icpc-team */
public class Solution {
    
    private static int bitwiseXor(String s1, String s2, int size) {
        int count = 0;
        for (int index = 0; index < size; index++) {
            if ( s1.charAt(index) == '1' || s2.charAt(index) == '1' ) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        String topic[] = new String[n];
        for (int topic_i = 0; topic_i < n; topic_i++) {
            topic[topic_i] = in.next();
        }
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int count = bitwiseXor(topic[i], topic[j], m);
                counts.add(count);
            }
        }
        Collections.sort(counts);
        int max = counts.get(counts.size() - 1);
        int index = counts.size() - 2;
        int count = 1;
        boolean exit = false;
        while (!exit && index >= 0) {            
            int e = counts.get(index);
            if ( e == max) {
                count++;
            } else {
                exit = true;
            }
            index--;
        }
        System.out.println(max);
        System.out.println(count);
    }
}
