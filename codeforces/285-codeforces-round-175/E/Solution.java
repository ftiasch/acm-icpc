// Codeforces Round #175
// Problem E -- Positions in Permutations
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static int MOD = (int)1e9 + 7;

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[][] binom = new int[n + 1][n + 1];
            for (int i = 0; i <= n; ++ i) {
                binom[i][0] = 1;
                for (int j = 1; j <= i; ++ j) {
                    binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
                }
            }
            int[][] ways = new int[n + 1][4];
            ways[0][1] = 1;
            for (int i = 0; i < n; ++ i) {
                int[][] newWays = new int[n + 1][4];
                for (int j = 0; j <= n; ++ j) {
                    for (int mask = 0; mask < 4; ++ mask) {
                        if (ways[j][mask] == 0) {
                            continue;
                        }
                        newWays[j][(mask << 1 | 1) & 3] += ways[j][mask];
                        newWays[j][(mask << 1 | 1) & 3] %= MOD;
                        if ((mask >> 1 & 1) == 1) {
                            newWays[j + 1][(mask << 1 | 1) & 3] += ways[j][mask];
                            newWays[j + 1][(mask << 1 | 1) & 3] %= MOD;
                        }
                        if (i + 1 < n) {
                            newWays[j + 1][mask << 1 & 3] += ways[j][mask];
                            newWays[j + 1][mask << 1 & 3] %= MOD;
                        }
                    }
                }
                ways = newWays;
            }
            int[] factorial = new int[n + 1];
            factorial[0] = 1;
            for (int i = 1; i <= n; ++ i) {
                factorial[i] = (int)((long)factorial[i - 1] * i % MOD);
            }
            int[] atLeast = new int[n + 1];
            for (int i = 0; i <= n; ++ i) {
                for (int mask = 0; mask < 4; ++ mask) {
                    atLeast[i] += (long)ways[i][mask] * factorial[n - i] % MOD;
                    atLeast[i] %= MOD;
                }
            }
            int[] exactly = new int[n + 1];
            for (int i = n; i >= 0; -- i) {
                exactly[i] = atLeast[i];
                for (int j = i + 1; j <= n; ++ j) {
                    exactly[i] -= (long)exactly[j] * binom[j][i] % MOD;
                    exactly[i] %= MOD;
                }
            }
            writer.println((exactly[m] + MOD) % MOD);
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
                    
    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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
