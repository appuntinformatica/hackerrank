package algorithms.bitManipulation.stringTransmission;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/string-transmission */
public class Solution {

    private final static int MAX_N = 1000;
    private final static int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tMax = scanner.nextInt();
        for (int tCount = 0; tCount < tMax; tCount++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            String line = scanner.next();
            
            int x = calcSNCR(n, k);
            
            int[] divisors = getDivisors(n);
            int numDivisors = (int) divisors.length;
            int[][] dp = new int[numDivisors][n + k + 1];
            boolean flag = false;

            for (int divisorIndex = 0; divisorIndex < numDivisors; ++divisorIndex) {
                int divisor = divisors[divisorIndex];
                int quotient = (int) (n / divisor);
                dp[divisorIndex][0] = 1;
                for (int i = 0; i < divisor; ++i) {
                    int zero = 0;
                    for (int j = i; j < n; j += divisor) {
                        zero += (line.charAt(j) == '0') ? 1 : 0;
                    }
                    int[] prev = dp[divisorIndex];
                    dp[divisorIndex] = new int[MAX_N];
                    for (int j = 0; j <= k; ++j) {
                        if (prev[j] > 0) {
                            dp[divisorIndex][j + zero] = mod(dp[divisorIndex][j + zero] + prev[j]);
                            dp[divisorIndex][j + quotient - zero] = mod(dp[divisorIndex][j + quotient - zero] + prev[j]);
                        }
                    }
                }
                flag = (dp[divisorIndex][0] > 0) ? true : flag;
                for (int i = 0; i < divisorIndex; ++i) {
                    if (divisor % divisors[i] == 0) {
                        for (int j = 0; j <= k; ++j) {
                            dp[divisorIndex][j] = mod(dp[divisorIndex][j] - dp[i][j]);
                        }
                    }
                }
                
                int i = 1;
                while (i <= k) {
                    x = mod(x - dp[divisorIndex][i]);
                    i++;
                }
            }
            x = (flag) ? mod(x - 1) : x;

            System.out.println(x);
        }
    }

    private static int[] getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            if (n % i == 0) {
                divisors.add(i);
            }
        }
        int i = 0;
        int[] out = new int[divisors.size()];
        for (int v : divisors) {
            out[i++] = v;
        }
        return out;
    }

    private static int mod(int n) {
        return (n % MOD + MOD) % MOD;
    }

    private static int calcSNCR(int n, int k) {
        int[] row = new int[k + 1];
        row[0] = 1;
        for (int i = 1; i <= k; ++i) {
            row[i] = 1;
            for (int j = i - 1; j > 0; --j) {
                row[j] = (row[j] + row[j - 1]) % MOD;
            }
        }
        for (int i = k + 1; i <= n; ++i) {
            for (int j = k; j > 0; --j) {
                row[j] = (row[j] + row[j - 1]) % MOD;
            }
        }
        int out = 0;
        int i = 0;
        while (i <= k) {
            out = (out + row[i]) % MOD;
            i++;
        }
        return out;
    }
}
