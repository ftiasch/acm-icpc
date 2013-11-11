import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    final static int MOD = (int)1e9 + 7;

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            String string = reader.next();
            int[] small = new int[n];
            int[] great = new int[n];
            for (int i = 0; i < n; ++ i) {
                small[i] = string.charAt(i) - 'a';
                great[i] = 'z' - string.charAt(i);
            }
            int[][] ways = new int[n + 1][m + 1];
            for (int i = 0; i <= n; ++ i) {
                ways[i][0] = 1;
            }
            for (int j = 0; j <= m; ++ j) {
                int sum = 0;
                for (int k = n - 1; k >= 0; -- k) {
                    sum += (int)((long)small[k] * ways[k + 1][j] % MOD);
                    sum %= MOD;
                    ways[k][j] += sum;
                    ways[k][j] %= MOD;
                    for (int i = k; i >= 0; -- i) {
                        int delta = (n - k) * (k - i + 1);
                        if (j + delta > m) {
                            break;
                        }
                        ways[i][j + delta] += (int)((long)great[k] * ways[k + 1][j] % MOD);
                        ways[i][j + delta] %= MOD;
                    }
                }
            }
            writer.println(ways[0][m]);
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
