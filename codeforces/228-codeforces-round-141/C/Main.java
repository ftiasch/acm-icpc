// Codeforces Round #141
// Problem C -- Fractal Detector
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
    final static int[] DELTA_X = {0, 0, 1, 1};
    final static int[] DELTA_Y = {0, 1, 0, 1};

    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        String[] graph = new String[n]; 
        for (int i = 0; i < n; ++ i) {
            graph[i] = in.nextToken();
        }
        int maxD = 0;
        while ((1 << maxD <= n) && (1 << maxD) <= m) {
            maxD ++;
        }
        boolean[][][] all = new boolean[maxD][n][m];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                all[0][i][j] = graph[i].charAt(j) == '*';
            }
        }
        for (int d = 1; d < maxD; ++ d) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    all[d][i][j] = true;
                    for (int k = 0; k < 4; ++ k) {
                        int x = i + (DELTA_X[k] << d - 1);
                        int y = j + (DELTA_Y[k] << d - 1);
                        all[d][i][j] &= x < n && y < m && all[d - 1][x][y];
                    }
                }
            }
        }
        int result = 0;
        for (int mask = 0; mask < 1 << 4; ++ mask) {
            boolean[][][] valid = new boolean[maxD][n][m];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    valid[0][i][j] = graph[i].charAt(j) == '.';
                }
            }
            for (int d = 1; d < maxD; ++ d) {
                for (int i = 0; i < n; ++ i) {
                    for (int j = 0; j < m; ++ j) {
                        valid[d][i][j] = true;
                        for (int k = 0; k < 4; ++ k) {
                            int x = i + (DELTA_X[k] << d - 1);
                            int y = j + (DELTA_Y[k] << d - 1);
                            if (x < n && y < m) {
                                if ((mask >> k & 1) == 1) {
                                    valid[d][i][j] &= all[d - 1][x][y];
                                } else {
                                    valid[d][i][j] &= valid[d - 1][x][y];
                                }
                            } else {
                                valid[d][i][j] = false;
                            }
                        }
                        if (d > 1 && valid[d][i][j]) {
                            result ++;
                        }
                    }
                }
            }
        }
        out.println(result);
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
