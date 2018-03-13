package algorithms.bitManipulation.xoringNinja;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/xoring-ninja */
public class Solution {

    private static final long MOD = 1000000007;

    private static long power(long n, long k) {
        long result, pow;
        if (k == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        result = n % MOD;
        pow = 1;
        while (pow * 2 <= k) {
            pow = pow * 2;
            result = (result * result);
            result = result % MOD;
        }

        if (k - pow != 0) {
            result = result * power(n, k - pow);
        }
        result = result % MOD;

        return result;
    }

    private static void process(List<Long> list, List<List<Long>> sublists) {
        int n = list.size();
        for (long i = 0; i < (1 << n); i++) {
            List<Long> sublist = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    sublist.add(list.get(j));
                }
            }
            sublists.add(sublist);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tMax = in.nextInt();
        for (int tCount = 0; tCount < tMax; tCount++) {
            int n = in.nextInt();
            long ans = 0, temp;
            for (int index = 0; index < n; index++) {
                temp = in.nextLong();
                ans |= temp;
            }
            ans *= power(2, n - 1);
            ans %= MOD;
            System.out.println(ans);
        }

    }

    public static void main_(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int tMax = in.nextInt();
        int module = (int) Math.pow(10, 9) + 7;
        for (int tCount = 0; tCount < tMax; tCount++) {
            int n = in.nextInt();
            int[] data = new int[n];
            int resultSum = 0;
            int resultPositiveSum = 0;
            int resultNegateSum = 0;
            Integer resultXORSum = null;

            for (int index = 0; index < n; index++) {
                data[index] = in.nextInt();
                resultSum += data[index];
                resultNegateSum |= ~data[index];
                resultPositiveSum |= data[index];

                if (resultXORSum != null) {
                    resultXORSum = resultXORSum ^ data[index];
                } else {
                    resultXORSum = data[index];
                }
            }
            int partialSum = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    partialSum |= data[i] ^ data[j];
                }
            }

            resultSum = resultSum + resultXORSum + (resultPositiveSum & resultNegateSum);
            resultSum = resultSum % module;
            System.out.println(resultSum);
        }
    }
}
