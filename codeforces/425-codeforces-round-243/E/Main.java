import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    static int MOD = (int)1e9 + 7;

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[] pow = new int[n + 1];
            pow[0] = 1;
            for (int i = 1; i <= n; ++ i) {
                pow[i] = (pow[i - 1] << 1) % MOD;
            }
            int[][] ways = new int[m + 1][1];
            ways[0][0] = 1;
            for (int i = 0; i < n; ++ i) {
                int[][] newWays = new int[m + 1][i + 2];
                for (int maximum = 0; maximum <= m && maximum <= i; ++ maximum) {
                    for (int last = 0; last <= i; ++ last) {
                        long value = ways[maximum][last];
                        newWays[maximum][last] = add(newWays[maximum][last], (int)value);
                        newWays[maximum][last] = add(newWays[maximum][last], (int)(value * (pow[last] - 1) % MOD));
                        if (maximum + 1 <= m) {
                            newWays[maximum + 1][i + 1] = add(newWays[maximum + 1][i + 1], (int)(value * (pow[i + 1] - pow[last]) % MOD));
                        }
                    }
                }
                ways = newWays;
            }
            int result = 0;
            for (int last = 0; last <= n; ++ last) {
                result = add(result, ways[m][last]);
            }
            writer.println(result);
        } catch (IOException ex) {
        }
        writer.close();
    }

    int add(int x, int a) {
        if (a < 0) {
            a += MOD;
        }
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
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
