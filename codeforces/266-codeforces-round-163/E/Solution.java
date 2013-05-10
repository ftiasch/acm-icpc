import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static int MOD = 1000000000 + 7;

    long[][] powerSum;

    long power(long a, long n) {
        long ret = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = ret * a % MOD;
            }
            a = a * a % MOD;
            n >>= 1;
        }
        return ret;
    }

    int[] flag;
    long[][] sum;

    void assignNode(int t, int l, int r, int v) {
        flag[t] = v;
        for (int i = 0; i <= 5; ++ i) {
            sum[t][i] = v * (powerSum[i][r] + MOD - powerSum[i][l - 1]) % MOD;
        }
    }

    long[] merge(long[] a, long[] b) {
        long[] c = new long[6];
        for (int i = 0; i <= 5; ++ i) {
            c[i] = (a[i] + b[i]) % MOD;
        }
        return c;
    }

    void build(int t, int l, int r, int[] a) {
        if (l < r) {
            int m = l + r >> 1;
            build(t * 2, l, m, a);
            build(t * 2 + 1, m + 1, r, a);
            sum[t] = merge(sum[t * 2], sum[t * 2 + 1]);
        } else {
            assignNode(t, l, r, a[l]);
        }
    }

    void pushDown(int t, int l, int r) {
        if (flag[t] != -1) {
            int m = l + r >> 1;
            assignNode(t * 2, l, m, flag[t]);
            assignNode(t * 2 + 1, m + 1, r, flag[t]);
            flag[t] = -1;
        }
    }

    void assign(int t, int l, int r, int a, int b, int v) {
        if (b < l || r < a) {
            return;
        }
        if (a <= l && r <= b) {
            assignNode(t, l, r, v);
        } else {
            pushDown(t, l, r);
            int m = l + r >> 1;
            assign(t * 2, l, m, a, b, v);
            assign(t * 2 + 1, m + 1, r, a, b, v);
            sum[t] = merge(sum[t * 2], sum[t * 2 + 1]);
        }
    }

    long[] query(int t, int l, int r, int a, int b) {
        if (b < l || r < a) {
            return new long[6];
        }
        if (a <= l && r <= b) {
            return sum[t];
        }
        pushDown(t, l, r);
        int m = l + r >> 1;
        return merge(query(t * 2, l, m, a, b),
                     query(t * 2 + 1, m + 1, r, a, b));
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            powerSum = new long[6][n + 1];
            for (int i = 0; i <= 5; ++ i) {
                powerSum[i][0] = 0;
                for (int j = 1; j <= n; ++ j) {
                    powerSum[i][j] = (powerSum[i][j - 1] + power(j, i)) % MOD;
                }
            }
            long[][] binom = new long[6][6];
            for (int i = 0; i <= 5; ++ i) {
                binom[i][0] = 1;
                for (int j = 1; j <= i; ++ j) {
                    binom[i][j] = (binom[i - 1][j] + binom[i - 1][j - 1]) % MOD;
                }
            }
            flag = new int[n << 2];
            Arrays.fill(flag, -1);
            sum = new long[n << 2][6];
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; ++ i) {
                a[i] = reader.nextInt();
            }
            build(1, 1, n, a);
            while (m > 0) {
                m --;
                String o = reader.next();
                int l = reader.nextInt();
                int r = reader.nextInt();
                if (o.equals("=")) {
                    int x = reader.nextInt();
                    assign(1, 1, n, l, r, x);
                } else {
                    long[] ret = query(1, 1, n, l, r);
                    int k = reader.nextInt();
                    long answer = 0;
                    int base = MOD - l + 1;
                    for (int i = 0; i <= k; ++ i) {
                        answer += binom[k][i] * ret[i] % MOD * power(base, k - i) % MOD;
                    }
                    writer.println(answer % MOD);
                }
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
    }

}

class InputReader {
    BufferedReader reader;
    StringTokenizer tokenizer;

    InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
    }

    String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    Integer nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
