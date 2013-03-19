// Codeforces Round #174
// Problem C -- Coin Troubles
import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    InputReader reader;
    PrintWriter writer;

    Main() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    final static int MOD = 1000000000 + 7;

    public void run() {
        try {
            int n = reader.nextInt();
            int q = reader.nextInt();
            int m = reader.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            boolean[] zero = new boolean[n];
            Arrays.fill(zero, true);
            boolean[][] greater = new boolean[n][n];
            while (q > 0) {
                q --;
                int x = reader.nextInt() - 1;
                int y = reader.nextInt() - 1;
                zero[x] = false;
                greater[x][y] = true;
            }
            for (int k = 0; k < n; ++ k) {
                for (int i = 0; i < n; ++ i) {
                    for (int j = 0; j < n; ++ j) {
                        greater[i][j] |= greater[i][k] && greater[k][j];
                    }
                }
            }
            boolean cycle = false;
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < i; ++ j) {
                    cycle |= greater[i][j] && greater[j][i];
                }
            }
            if (cycle) {
                writer.println(0);
            } else {
                int[] ways = new int[m + 1];
                ways[0] = 1;
                for (int i = 0; i < n; ++ i) {
                    greater[i][i] = true;
                    int size = 0;
                    for (int j = 0; j < n; ++ j) {
                        if (greater[j][i]) {
                            size += a[j];
                        }
                    }
                    if (!zero[i]) {
                        for (int j = m; j >= 0; -- j) {
                            ways[j] = j >= size ? ways[j - size] : 0;
                        }
                    }
                    for (int j = size; j <= m; ++ j) {
                        ways[j] += ways[j - size];
                        ways[j] %= MOD;
                    }
                }
                writer.println(ways[m]);
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
