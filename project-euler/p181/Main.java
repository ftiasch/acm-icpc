import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        int n = 60;
        int m = 40;
        BigInteger[][] ways = new BigInteger[n + 1][m + 1];
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= m; ++ j) {
                ways[i][j] = BigInteger.ZERO;
            }
        }
        ways[0][0] = BigInteger.ONE;
        for (int a = 0; a <= n; ++ a) {
            for (int b = 0; b <= m; ++ b) {
                if (a + b > 0) {
                    for (int i = 0; i + a <= n; ++ i) {
                        for (int j = 0; j + b <= m; ++ j) {
                            ways[i + a][j + b] = ways[i + a][j + b].add(ways[i][j]);
                        }
                    }
                }
            }
        }
        System.out.println(ways[n][m]);
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
