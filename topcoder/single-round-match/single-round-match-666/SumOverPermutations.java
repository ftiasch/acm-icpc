import java.util.Arrays;

public class SumOverPermutations {
    static long MOD = (long)1e9 + 7;

    long add(long x, long a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
    }

    public int findSum(int n) {
        long[] binom = new long[n + 1];
        binom[0] = 1;
        long[] ways = new long[n + 1];
        ways[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            long[] newBinom = new long[n + 1];
            newBinom[0] = 1;
            for (int j = 1; j <= i; ++ j) {
                newBinom[j] = add(binom[j - 1], binom[j]);
            }
            for (int j = 0; j < i; ++ j) {
                long k = n - 2;
                if (j == 0) {
                    k ++;
                }
                if (j == i - 1) {
                    k ++;
                }
                ways[i] = add(ways[i], ways[j] * ways[i - 1 - j] % MOD * binom[j] % MOD * k % MOD);
            }
            binom = newBinom;
        }
        return (int)ways[n];
    }

    private void debug(Object...o) {
        System.err.println(Arrays.deepToString(o));
    }
}
