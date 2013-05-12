// Testing Round $6
// Problem C -- White, Black and White Again
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static int MOD = (int)1e9 + 9;

    public void run() {
        try {
            int n = reader.nextInt();
            int w = reader.nextInt();
            int b = reader.nextInt();
            int[][] binom = new int[4000][4000];
            for (int i = 0; i < 4000; ++ i) {
                binom[i][0] = 1;
                for (int j = 1; j <= i; ++ j) {
                    binom[i][j] = (binom[i - 1][j] + binom[i - 1][j - 1]) % MOD;
                }
            }
            int answer = 0;
            for (int sum = 2; sum < n; ++ sum) {
                answer += (int)((long)binom[w - 1][sum - 1] * (sum - 1) % MOD * binom[b - 1][n - sum - 1]  % MOD);
                answer %= MOD;
            }
            for (int i = 1; i <= w; ++ i) {
                answer = (int)((long)answer * i % MOD);
            }
            for (int i = 1; i <= b; ++ i) {
                answer = (int)((long)answer * i % MOD);
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
