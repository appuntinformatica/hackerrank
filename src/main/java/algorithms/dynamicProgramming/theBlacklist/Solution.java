package algorithms.dynamicProgramming.theBlacklist;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/the-blacklist */
public class Solution {

    static int N, K;
    static int cost[][];
    static int DP[][];

    public static int solve(int pos, int mask) {
        if (DP[pos][mask] != -1) {
            return DP[pos][mask];
        }

        int minn = (int) 1e9;

        for (int i = 0; i < K; i++) {
            if ((mask & (1 << i)) == 0) {
                for (int j = pos; j < N; j++) {
                    minn = Math.min(minn, cost[i + 1][j + 1] - cost[i + 1][pos] + solve(j + 1, mask | (1 << i)));
                }
            }
        }

        return DP[pos][mask] = minn;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        InputStreamReader sr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(sr);

        String cad[] = br.readLine().split(" ");
        N = Integer.parseInt(cad[0]);
        K = Integer.parseInt(cad[1]);

        cost = new int[K + 1][N + 1];
        DP = new int[N + 1][(1 << K) + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < (1 << K); j++) {
                DP[i][j] = -1;
            }
        }

        for (int i = 1; i <= K; i++) {
            cad = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                cost[i][j] = cost[i][j - 1] + Integer.parseInt(cad[j - 1]);
            }
        }

        System.out.println(solve(0, 0));
    }
}
