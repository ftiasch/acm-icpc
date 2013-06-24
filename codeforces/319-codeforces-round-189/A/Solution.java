// Codeforces Round #189
// Problem A -- Malek Dance Club
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static int MOD = (int)1e9 + 7;

    public void run() {
        try {
            String x = reader.next();
            int n = x.length();
            int[] power = new int[n];
            power[0] = 1;
            for (int i = 1; i < n; ++ i) {
                power[i] = power[i - 1] * 2 % MOD;
            }
            int answer = 0;
            for (int i = 0; i < n; ++ i) {
                if (x.charAt(i) == '1') {
                    answer += (int)((long)power[i] * power[n - i - 1] % MOD * power[n - i - 1] % MOD);
                    answer %= MOD;
                }
            }
            writer.println(answer);
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
