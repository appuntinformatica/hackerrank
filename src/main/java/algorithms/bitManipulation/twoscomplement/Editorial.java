package algorithms.bitManipulation.twoscomplement;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Editorial {

    public static long find_number_of_ones(long n) {
        if ( n == 0 )
            return 0;
        else if ( n % 2 == 0 )
            return find_number_of_ones(n - 1) + Long.bitCount(n);
        else
        return find_number_of_ones(n >> 1) * 2 + (n + 1) / 2;
    }

    public static long solve(int a, int b) {
        long ret;
        if ( b >= 0 && a >= 0 ) {
            ret = find_number_of_ones(b) - find_number_of_ones(Math.max(0, a - 1));
        } else if( b >= 0 && a < 0) {
            ret = find_number_of_ones(b);
            ret += (find_number_of_ones(((long) 1 << 32) - 1) - find_number_of_ones(((long) 1 << 32) + a - 1)) ;
        } else {
            ret = find_number_of_ones(((long) 1 << 32) + b) - find_number_of_ones(((long) 1 << 32) + a - 1);
        }
        return ret;
    }
    public static void main(String[] args) {
        int cs, t;
        int A, B;
        Scanner scanner = new Scanner(System.in);
        t = scanner.nextInt();
        for ( cs = 1; cs <= t; cs++ ) {
            A = scanner.nextInt();
            B = scanner.nextInt();
            System.out.println(solve(A, B));
        }
    }
}
