// SGU 389 -- Strange Planet
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
    }

    final static int MOD = 1000000000 + 7;

    long inverse(long a) {
        return a == 1 ? 1 : (MOD - MOD / a) * inverse(MOD % a) % MOD;
    }

    long[] factorial;

    long choose(int n, int k) {
        return factorial[n] * inverse(factorial[n - k]) % MOD * inverse(factorial[k]) % MOD;
    }

    public void run() {
        try {
            String a = reader.next();
            String b = reader.next();
            String c = reader.next();
            int n = a.length();
            factorial = new long[n + 1];
            factorial[0] = 1;
            for (int i = 1; i <= n; ++ i) {
                factorial[i] = (long)factorial[i - 1] * i % MOD;
            }
            long answer = 1;
            int[] count = new int[3];
            for (int i = 0; i < n; ++ i) {
                int mask = a.charAt(i) - '0' << 2 | b.charAt(i) - '0' << 1 | c.charAt(i) - '0';
                if ((mask >> 2) == 1) {
                    mask ^= 7;
                }
                if (mask == 0) {
                    answer *= 2;
                    answer %= MOD;
                } else if (mask == 1) {
                    count[0] ++;
                } else if (mask == 2) {
                    count[1] ++;
                } else {
                    count[2] ++;
                }
            }
            long total = 0;
            for (int d = -n; d <= n; ++ d) {
                long ret = 1;
                for (int i = 0; i < 3; ++ i) {
                    if ((count[i] - d) % 2 == 0 && -count[i] <= d && d <= count[i]) {
                        ret *= choose(count[i], (count[i] + d) / 2);
                        ret %= MOD;
                    } else {
                        ret = 0;
                    }
                }
                total += ret;
                total %= MOD;
            }
            answer *= total;
            answer %= MOD;
            writer.println(answer);
        } catch (IOException ex) {
        }
        writer.close();
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
