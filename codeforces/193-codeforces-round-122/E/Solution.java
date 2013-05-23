// Codeforces Round #122
// Problem E -- Fibonacci Number
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static long MOD = 10000000000000L;

    long multiply(long a, long b) {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).remainder(BigInteger.valueOf(MOD)).longValue();
    }

    long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        for (int i = 0; i < 2; ++ i) {
            for (int j = 0; j < 2; ++ j) {
                c[i][j] = (multiply(a[i][0], b[0][j]) + multiply(a[i][1], b[1][j])) % MOD;
            }
        }
        return c;
    }

    long[][] pow(long[][] a, long n) {
        long[][] ret = new long[][] {{1, 0}, {0, 1}};
        while (n > 0) {
            if (n % 2 == 1) {
                ret = multiply(ret, a);
            }
            a = multiply(a, a);
            n >>= 1;
        }
        return ret;
    }

    long fibonacci(long n) {
        return multiply(new long[][] {{0, 1}, {0, 0}}, pow(new long[][]{{0, 1}, {1, 1}}, n))[0][0];
    }

    public void run() {
        try {
            long m = reader.nextLong();
            ArrayList <Long> indices = new ArrayList <Long>();
            indices.add(0L);
            long period = 1;
            for (long mod = 10; mod <= MOD; mod *= 10) {
                ArrayList <Long> newIndices = new ArrayList <Long>();
                int times = mod == 10 ? 60 : mod <= 1000 ? 5 : 10;
                for (long iter : indices) {
                    long now = iter;
                    for (int i = 0; i < times; ++ i) {
                        if (fibonacci(now) % mod == m % mod) {
                            newIndices.add(now);
                        }
                        now += period;
                    }
                }
                period *= times;
                indices = newIndices;
            }
            Collections.sort(indices);
            writer.println(indices.isEmpty() ? -1 : indices.get(0));
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

    Long nextLong() throws IOException {
        return Long.parseLong(next());
    }
}
