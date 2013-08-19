import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static int MOD = (int)1e9 + 9;

    long pow(long a, long n) {
        long ret = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ret *= a;
                ret %= MOD;
            }
            a *= a;
            a %= MOD;
            n >>= 1;
        }
        return ret;
    }

    long solve(long n, long m, long k) {
        if (k * (n - m + 1) - 1 >= n) {
            return m;
        }
        long a = n - k * (n - m);
        return (2L * k * pow(2, a / k) % MOD + (MOD - 2L * k % MOD) + (a % k) + (k - 1) * (n - m) % MOD) % MOD;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int k = reader.nextInt();
            writer.println(solve(n, m, k));
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
