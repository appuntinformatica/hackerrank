package algorithms.dynamicProgramming.cutTree;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* https://www.hackerrank.com/challenges/cuttree */
public class Solution {
    
    private final static int N_MAX = 55;
    
    
    private static List<Integer>[] g = new List[N_MAX];
    private static List<Integer>[] tr = new List[N_MAX];
    
    
    private static long[][][][] dp = new long[N_MAX][N_MAX][N_MAX][2];
    private static boolean[] used = new boolean[N_MAX];
    
    private static void dfs(int u) {
        used[u] = true;
        for (int i = 0; i < g[u].size(); i++) {
            int v = g[u].get(i);
            if (!used[v]) {
                if (tr[u] == null) {
                    tr[u] = new ArrayList<Integer>();
                }
                tr[u].add(v);
                dfs(v);
            }
        }
    }
    
    private static long findsSubtreesNumbers(int u, int nson, int cut, int choose) {
        if (cut < 0) 
            return 0;
        if (nson == 0) 
            if (cut == 0) 
                return 1;
            else 
                return 0;
        long ret = dp[u][nson][cut][choose];
        if (ret != -1)
            return ret;
        ret = 0;
        if (choose == 0) {
            for (int i = 0; i < nson; i++) {
                int v = g[u].get(i);
                ret += findsSubtreesNumbers(v, g[v] != null ? g[v].size() : 0, cut, 0);
                ret += findsSubtreesNumbers(v, g[v] != null ? g[v].size() : 0, cut - 1, 1);
            }
            if (cut == 0) 
                ret -= nson - 1;
        } else {
            int rson = g[u].get(nson - 1);
            ret += findsSubtreesNumbers(u, nson - 1, cut - 1, 1);
            for (int i = 0; i <= cut; i++) {
                ret += findsSubtreesNumbers(u, nson - 1, cut - i, 1) * findsSubtreesNumbers(rson, g[rson] != null ? g[rson].size() : 0, i, 1);
            }
        }
        dp[u][nson][cut][choose] = ret;
        return ret;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            --u;
            --v;
            if (g[u] == null) {
                g[u] = new ArrayList<>();
            }
            g[u].add(v);
            if (g[v] == null) {
                g[v] = new ArrayList<>();
            }
            g[v].add(u);
        }
        for (int index = 0; index < used.length; index++)
            used[index] = false;
        dfs(0);

        for (int index = 0; index < N_MAX; index++) {
            g[index] = tr[index];
        }
  
        for (int i1 = 0; i1 < dp.length; i1++) {
            for (int i2 = 0; i2 < dp[i1].length; i2++) {
                for (int i3 = 0; i3 < dp[i1][i2].length; i3++) {
                    for (int i4 = 0; i4 < dp[i1][i2][i3].length; i4++) {
                        dp[i1][i2][i3][i4] = -1;
                    }
                }
            }
        }

        long ret = 0;
        for (int cut = 0; cut <= k; cut++) {
            long r1 = findsSubtreesNumbers(0, g[0].size(), cut, 0);
            long r2 = findsSubtreesNumbers(0, g[0].size(), cut, 1);
            ret += r1 + r2;
        }
        System.out.println(ret);
    }
}
