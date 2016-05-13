import java.util.Arrays;

public class LCMGCD {
    static int MOD = (int)1e9 + 9;

    int countExp(int p, int n) {
        int result = 0;
        while (n % p == 0) {
            n /= p;
            result ++;
        }
        return result;
    }

    int getWays(int[] count) {
        Integer ref = memory[count[0]][count[1]][count[2]][count[3]];
        if (ref != null) {
            return ref;
        }
        int result = 0;
        for (int i = 0; i < 4; ++ i) {
            for (int j = 0; j < 4; ++ j) {
                int[] newCount = count.clone();
                int w = 1;
                w = (int)((long)w * newCount[i] % MOD);
                newCount[i] --;
                w = (int)((long)w * newCount[j] % MOD);
                newCount[j] --;
                if (w > 0) {
                    newCount[i | j] ++;
                    result += (int)((long)w * getWays(newCount) % MOD);
                    if (result >= MOD) {
                        result -= MOD;
                    }
                    newCount[i | j] --;
                    newCount[i & j] ++;
                    result += (int)((long)w * getWays(newCount) % MOD);
                    if (result >= MOD) {
                        result -= MOD;
                    }
                }
            }
        }
        memory[count[0]][count[1]][count[2]][count[3]] = result;
        return result;
    }

    int solve(int n, int[] x, int[] y, int a, int b) {
        int[] count = new int[4];
        for (int i = 0; i < n; ++ i) {
            count[(x[i] <= a ? 1 : 0) | (y[i] <= b ? 2 : 0)] ++;
        }
        return getWays(count);
    }

    public String isPossible(int[] a, int target) {
        int n = a.length;
        memory = new Integer[n + 1][n + 1][n + 1][n + 1];
        memory[0][0][0][1] = 1;
        int[] x = new int[n + 1];
        int[] y = new int[n + 1];
        for (int i = 0; i < n; ++ i) {
            x[i] = countExp(2, a[i]);
            y[i] = countExp(3, a[i]);
        }
        x[n] = countExp(2, target);
        y[n] = countExp(3, target);
        int result = solve(n, x, y, x[n], y[n]);
        result += MOD - solve(n, x, y, x[n] - 1, y[n]);
        result %= MOD;
        result += MOD - solve(n, x, y, x[n], y[n] - 1);
        result %= MOD;
        result += solve(n, x, y, x[n] - 1, y[n] - 1);
        result %= MOD;
        return result > 0 ? "Possible" : "Impossible";
    }

    Integer[][][][] memory;

    private void debug(Object...args) {

        System.err.println(Arrays.deepToString(args));
    }
}
