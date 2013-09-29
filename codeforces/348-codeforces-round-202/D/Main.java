import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    final static int MOD = (int)1e9 + 7;

    long count(String[] map, int x0, int y0, int x1, int y1) {
        int n = map.length;
        int m = map[0].length();
        int[][] ways = new int[n][m];
        if (map[x0].charAt(y0) == '.') {
            ways[x0][y0] = 1;
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (map[i].charAt(j) == '.') {
                    if (i > 0) {
                        ways[i][j] += ways[i - 1][j];
                    }
                    if (j > 0) {
                        ways[i][j] += ways[i][j - 1];
                    }
                    ways[i][j] %= MOD;
                }
            }
        }
        return ways[x1][y1];
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            String[] map = new String[n];
            for (int i = 0; i < n; ++ i) {
                map[i] = reader.next();
            }
            long w00 = count(map, 0, 1, n - 2, m - 1);
            long w01 = count(map, 0, 1, n - 1, m - 2);
            long w10 = count(map, 1, 0, n - 2, m - 1);
            long w11 = count(map, 1, 0, n - 1, m - 2);
            writer.println(((w00 * w11 - w01 * w10) % MOD + MOD) % MOD);
        } catch (IOException ex) {
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Main() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
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
