// Codeforces Round #182
// Problem B -- Yaroslav and Time
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
            int d = reader.nextInt();
            int[] a = new int[n];
            for (int i = 1; i < n - 1; ++ i) {
                a[i] = reader.nextInt();
            }
            int[] x = new int[n];
            int[] y = new int[n];
            for (int i = 0; i < n; ++ i) {
                x[i] = reader.nextInt();
                y[i] = reader.nextInt();
            }
            int[] need = new int[n];
            Arrays.fill(need, Integer.MAX_VALUE);
            need[n - 1] = 0;
            boolean[] visit = new boolean[n];
            while (true) {
                int u = -1;
                for (int i = 0; i < n; ++ i) {
                    if (!visit[i] && (u == -1 || need[i] < need[u])) {
                        u = i;
                    }
                }
                if (u == -1) {
                    break;
                }
                visit[u] = true;
                for (int v = 0; v < n; ++ v) {
                    if (u != v) {
                        int cost = d * (Math.abs(x[u] - x[v]) + Math.abs(y[u] - y[v]));
                        int newValue = Math.max(need[u] + cost - a[v], 0);
                        if (newValue < need[v]) {
                            need[v] = newValue;
                        }
                    }
                }
            }
            writer.println(need[0]);
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
