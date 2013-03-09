// SGU 405 -- Totalizator
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

    private int signum(int x) {
        if (x == 0) {
            return 0;
        }
        return x < 0 ? -1 : 1;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[] score = new int[n];
            for (int i = 0; i < m; ++ i) {
                int a = reader.nextInt();
                int b = reader.nextInt();
                for (int j = 0; j < n; ++ j) {
                    int x = reader.nextInt();
                    int y = reader.nextInt();
                    if (signum(a - b) == signum(x - y)) {
                        score[j] += 2;
                    }
                    if (a - b == x - y) {
                        score[j] += 3;
                    }
                    if (a == x) {
                        score[j] += 1;
                    }
                    if (b == y) {
                        score[j] += 1;
                    }
                }
            }
            for (int i = 0; i < n; ++ i) {
                writer.print(String.format("%d%c", score[i], i == n - 1 ? '\n': ' '));
            }
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
