import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    final static long MOD = (int)1e9 + 7;

    public void run() throws IOException {
        int n = reader.nextInt();
        int[] parent = new int[n];
        for (int i = 1; i < n; ++ i) {
            parent[i] = reader.nextInt();
        }
        long[][] ways = new long[n][2];
        for (int i = 0; i < n; ++ i) {
            ways[i][reader.nextInt()] = 1;
        }
        for (int u = n - 1; u >= 1; -- u) {
            int p = parent[u];
            ways[p] = new long[]{(ways[p][0] * ways[u][0] % MOD + ways[p][0] * ways[u][1] % MOD) % MOD,
                                 (ways[p][0] * ways[u][1] % MOD + ways[p][1] * ways[u][0] % MOD + ways[p][1] * ways[u][1] % MOD) % MOD};
        }
        writer.println(ways[0][1]);
        writer.close();
    }

    Main() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
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

    public String next() throws IOException {
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
