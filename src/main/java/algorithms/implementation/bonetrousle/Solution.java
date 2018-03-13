package algorithms.implementation.bonetrousle;

import java.io.*;
import java.util.*;

/* https://www.hackerrank.com/challenges/bonetrousle */
public class Solution {
	/*
	
		n = 12
		k = 8 ---> number : { 1, 2, 3, 4, 5, 6, 7, 8 }
		b = 3 ---> result : { a, b, c } ===> a + b + c = 12
		
		sommaMin = 1 + 2 + 3
		sommaMax = 6 + 7 + 8
		
	
		1 + 2 + 3
		1 + 2 + 4
		1 + 2 + 5
		1 + 2 + 6
		1 + 2 + 7
		1 + 2 + 8
		
		1 + 3 + 4
		1 + 3 + 5
		1 + 3 + 6
		1 + 3 + 7
		1 + 3 + 8
		
		.........
		
		1 + 7 + 8
		
		2 + 3 + 4
		2 + 3 + 5
		.........
		2 + 7 + 8
		
		3 + 4 + 5
		3 + 4 + 6
		.........
		3 + 7 + 8
		
		.........
		
		6 + 7 + 8
		
		
		index = 0
		sum = 0
		for i in (1..b)
			sum += numbers[index]
			index++
		
	
	*/
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        for (int c = 0; c < t; c++) {
            long n = input.nextLong();
            long k = input.nextLong();
            int b = input.nextInt();
            
            long low = b * (1 + b) / 2;
            long high = b * ((k - b + 1) + k) / 2;

            long[] r = new long[b];
            if ( n == low ) {
                for (int i = 0; i < b; i++) {
                    r[i] = i + 1;
                }
            } else if ( n == high ) {
                for (int i = 0; i < b; i++) {
                    r[i] = i + k - b + 1;
                }
            } else if ( low < n && n < high ) {
                for (int i = 0; i < b; i++) {
                    r[i] = i + 1;
                }
                long s = low;
                for (int i = r.length - 1; i >= 0; i--) {
                    s -= r[i];
                    long rem = n - s;
                    r[i] = Math.min(k, rem);
                    s += r[i];
                    k = r[i] - 1;
                    if ( s == n)
                        break;
                }
            } else {
                r = new long[1];
                r[0] = -1;
            }
            String out = "";
            for (int i = 0; i < r.length; i++) {
                if ( i == r.length ) {
                    out += r[i];
                } else {
                    out += r[i] + " ";
                }
            }
            out = out.substring(0, out.length() - 1);
            System.out.println(out);
        }
    }
    
    
    private static class FirstFounded extends Exception {
        public FirstFounded() {
        }
    }
    
    private static void combination(int[] numbers, int size, int startIndex, int[] partial, int target, List<int[]> results) throws FirstFounded {
        if ( partial == null )
            partial = new int[size];
        if ( size != 0) {
            for (int index = startIndex; index <= numbers.length - size; index++) {
                partial[partial.length - size] = numbers[index];
                combination(numbers, size - 1, index + 1, partial, target, results);
            }
        } else {
            int sum = 0;
            for (int index = 0; index < partial.length; index++)
                sum += partial[index];
            if ( sum == target ) {
                int[] copy = Arrays.copyOf(partial, partial.length);
                results.add(copy);
                throw new FirstFounded();
            }
        }
    }
    
    public static void main_(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        for (int c = 0; c < t; c++) {
            int n = input.nextInt();
            int k = input.nextInt();
            int b = input.nextInt();
            
            int[] d = new int[k];
            for (int i = 0; i < d.length; i++)
                d[i] = i + 1;
            
            List<int[]> results = new ArrayList<>();
            try {
                combination(d, b, 0, null, n, results);
            } catch (FirstFounded f) {
                
            }
            
            if ( results.size() == 0 ) {
                System.out.println("-1");
            } else {
                String out = "";
                for (int i : results.get(0)) {
                    out += i + " ";
                }
                out = out.substring(0, out.length() - 1);
                System.out.println(out);
            }
        }
    }

}
