// Codeforces Round #132
// Problem E -- Periodical Numbers
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    int miu(int n) {
        int ret = 1;
        for (int i = 2; i * i <= n; ++ i) {
            if (n % i == 0) {
                ret *= -1;
                n /= i;
                if (n % i == 0) {
                    return 0;
                }
            }
        }
        if (n > 1) {
            ret *= -1;
        }
        return ret;
    }

    long solve(long n) {
        long ret = n - 1;
        ArrayList <Integer> digits = new ArrayList <Integer>();
        for (long t = n; t > 0; t >>= 1) {
            digits.add((int)(t & 1));
        }
        for (int length = 1; length <= digits.size(); ++ length) {
            long[] count = new long[length + 1];
            for (int period = 1; period <= length; ++ period) {
                if (length % period != 0) {
                    continue;
                }
                if (length < digits.size()) {
                    count[period] = 1L << period - 1;
                } else {
                    for (int i = 2; i <= period; ++ i) {
                        if (digits.get(length - i) == 1) {
                            count[period] += 1L << period - i;
                        }
                    }
                    long now = 0;
                    for (int i = 0; i < length; ++ i) {
                        now *= 2;
                        now += digits.get(length - 1 - (i % period));
                    }
                    if (now < n) {
                        count[period] ++;
                    }
                }
                ret -= count[period] * miu(length / period);
            }
        }
        return ret;
    }

    public void run() {
        try {
            long l = reader.nextLong();
            long r = reader.nextLong();
            writer.println(solve(r + 1) - solve(l));
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
