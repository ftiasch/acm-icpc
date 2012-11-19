// Codeforces Testing Round #4
// Problem B -- Smile House
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class Solver {
    final static int INF = 1000000000;

    int n;

    int[][] multiply(int[][] a, int [][] b) {
        int[][] c = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                c[i][j] = -INF;
                for (int k = 0; k < n; ++ k) {
                    c[i][j] = Math.max(c[i][j], a[i][k] + b[k][j]);
                }
            }
        }
        return c;
    }

    boolean isValid(int[][] a) {
        for (int i = 0; i < n; ++ i) {
            if (a[i][i] > 0) {
                return true;
            }
        }
        return false;
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        n = in.nextInt();
        int m = in.nextInt();
        int[][] adj = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                adj[i][j] = i == j ? 0 : -INF;
            }
        }
        for (int i = 0; i < m; ++ i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj[a][b] = in.nextInt();
            adj[b][a] = in.nextInt();
        }
        int[][][] pow = new int[10][n][n];
        pow[0] = adj;
        for (int i = 1; i < 10; ++ i) {
            pow[i] = multiply(pow[i - 1], pow[i - 1]);
        }
        if (isValid(pow[9])) {
            int result = 0;
            adj = new int[n][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    adj[i][j] = i == j ? 0 : -INF;
                }
            }
            for (int i = 9; i >= 0; -- i) {
                int[][] ret = multiply(adj, pow[i]);
                if (!isValid(ret)) {
                    adj = ret;
                    result += 1 << i;
                }
            }
            out.println(result + 1);
        } else {
            out.println(0);
        }
        out.close();
    }
}

class InputReader {
    BufferedReader reader;
    StringTokenizer tokenizer;

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
    }

    String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
}
