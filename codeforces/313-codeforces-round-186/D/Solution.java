// Codeforces Round #186
// Problem D -- Ilya and Roads
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static long INF = 1000000000000000000L;

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int k = reader.nextInt();
            int[][] minCost = new int[n][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = i; j < n; ++ j) {
                    minCost[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int i = 0; i < m; ++ i) {
                int a = reader.nextInt() - 1;
                int b = reader.nextInt() - 1;
                minCost[a][b] = Math.min(minCost[a][b], reader.nextInt());
            }
            for (int i = 0; i < n; ++ i) {
                for (int j = n - 1; j >= i; -- j) {
                    if (i - 1 >= 0) {
                        minCost[i][j] = Math.min(minCost[i][j], minCost[i - 1][j]);
                    }
                    if (j + 1 < n) {
                        minCost[i][j] = Math.min(minCost[i][j], minCost[i][j + 1]);
                    }
                }
            }
            long[][] minimum = new long[n + 1][n + 1];
            Arrays.fill(minimum[n], INF);
            minimum[n][0] = 0;
            for (int i = n - 1; i >= 0; -- i) {
                for (int j = 0; j <= n; ++ j) {
                    minimum[i][j] = minimum[i + 1][j];
                    for (int x = i; x < n && x - i + 1 <= j && minCost[i][x] != Integer.MAX_VALUE; ++ x) {
                        minimum[i][j] = Math.min(minimum[i][j], minimum[x + 1][j - (x - i + 1)] + minCost[i][x]);
                    }
                }
            }
            long answer = INF;
            for (int j = k; j <= n; ++ j) {
                answer = Math.min(answer, minimum[0][j]);
            }
            writer.println(answer == INF ? -1 : answer);
        } catch (IOException ex) {
        }
        writer.close();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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
