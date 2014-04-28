import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int k = reader.nextInt();
            int[][] a = new int[n][m];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    a[i][j] = reader.nextInt();
                }
            }
            int result = Integer.MAX_VALUE;
            if (n > k) {
                for (int unchanged = 0; unchanged < n; ++ unchanged) {
                    int total = 0;
                    for (int i = 0; i < n; ++ i) {
                        int cost = 0;
                        for (int j = 0; j < m; ++ j) {
                            cost += a[i][j] ^ a[unchanged][j];
                        }
                        cost = Math.min(cost, m - cost);
                        total += cost;
                    }
                    result = Math.min(result, total);
                }
            } else {
                for (int mask = 0; mask < 1 << n; ++ mask) {
                    int total = 0;
                    for (int j = 0; j < m; ++ j) {
                        int cost = 0;
                        for (int i = 0; i < n; ++ i) {
                            cost += a[i][j] ^ (mask >> i & 1);
                        }
                        cost = Math.min(cost, n - cost);
                        total += cost;
                    }
                    result = Math.min(result, total);
                }
            }
            writer.println(result > k ? -1 : result);
        } catch (IOException ex) {
        }
        writer.close();
    }

    Main() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    private InputReader reader;
    private PrintWriter writer;
}

class InputReader {
    InputReader(InputStream in) {
        reader    = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
    }

    private String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    public Integer nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    private BufferedReader  reader;
    private StringTokenizer tokenizer;
}
