import java.io.*;
import java.util.*;

public class PickAndDelete {
    static int MOD = (int)1e9 + 7;

    public int count(String[] s) {
        return solve(parse(s));
    }

    int[] parse(String[] ss) {
        String r = "";
        for (String s : ss) {
            r += s;
        }
        ss = r.split(" ");
        int n = ss.length;
        int[] s = new int[n];
        for (int i = 0; i < n; ++ i) {
            s[i] = Integer.parseInt(ss[i]);
        }
        return s;
    }

    int add(int x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
    }

    int solve(int[] s) {
        int n = s.length;
        Arrays.sort(s);
        int[] value = new int[n];
        int[] count = new int[n];
        int m = 0;
        for (int i = 0; i < n; ++ i) {
            if (m == 0 || value[m - 1] != s[i]) {
                value[m ++] = s[i];
            }
            count[m - 1] ++;
        }
        int[] prefixCount = new int[m + 1];
        for (int i = 1; i <= m; ++ i) {
            prefixCount[i] = prefixCount[i - 1] + count[i - 1];
        }
        int[][] binom = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = add(binom[i - 1][j - 1], binom[i - 1][j]);
            }
        }
        int[][] ways = new int[m + 1][n + 1];
        ways[0][0] = 1;
        for (int i = 0; i < m; ++ i) {
            int candidates = value[i];
            if (i > 0) {
                candidates -= value[i - 1];
            }
            for (int j = 0; j <= n; ++ j) {
                int power = 1;
                for (int k = 0; j + k <= n; ++ k) {
                    if (j + k >= prefixCount[i + 1]) {
                        ways[i + 1][j + k] = add(ways[i + 1][j + k], (int)((long)ways[i][j] * binom[j + k][k] % MOD * power % MOD));
                    }
                    power = (int)((long)power * candidates % MOD);
                }
            }
        }
        return ways[m][n];
    }

    static void debug(Object...args) {
        System.out.println(Arrays.deepToString(args));
    }
}
