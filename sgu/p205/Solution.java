// SGU 205 -- Quantization Problem 
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

    public void run() {
        try {
            int n = reader.nextInt();
            int[] x = new int[n];
            for (int i = 0; i < n; ++ i) {
                x[i] = reader.nextInt();
            }
            int m = reader.nextInt();
            int s = reader.nextInt();
            int[][] l = new int[m][s];
            for (int i = 0; i < m; ++ i) {
                for (int j = 0; j < s; ++ j) {
                    l[i][j] = reader.nextInt();
                }
            }
            int[][] cost = new int[n + 1][m];
            int[][][] from = new int[n + 1][m][];
            for (int[] row : cost) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            cost[0][0] = 0;
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    if (cost[i][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    for (int k = 0; k < s; ++ k) {
                        int value = cost[i][j] + Math.abs(x[i] - l[j][k]);
                        if (value < cost[i + 1][k & m - 1]) {
                            cost[i + 1][k & m - 1] = value;
                            from[i + 1][k & m - 1] = new int[] {j, k};
                        }
                    }
                }
            }
            int answer = Integer.MAX_VALUE;
            for (int j = 0; j < m; ++ j) {
                answer = Math.min(answer, cost[n][j]);
            }
            writer.println(answer);
            for (int j = 0; j < m; ++ j) {
                if (cost[n][j] == answer) {
                    int[] solution = new int[n];
                    for (int i = n; i > 0; -- i) {
                        solution[i - 1] = from[i][j][1];
                        j = from[i][j][0];
                    }
                    for (int i = 0; i < n; ++ i) {
                        writer.print(String.format("%d%c", solution[i], i == n - 1 ? '\n' : ' '));
                    }
                    throw new Exception();
                }
            }
        } catch (Exception ex) {
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
