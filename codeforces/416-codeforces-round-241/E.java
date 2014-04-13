import java.io.*;
import java.math.*;
import java.util.*;

public class E {
    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[][] graph = new int[n][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int i = 0; i < m; ++ i) {
                int a = reader.nextInt() - 1;
                int b = reader.nextInt() - 1;
                graph[a][b] = graph[b][a] = reader.nextInt();
            }
            int[][] floyd = new int[n][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    floyd[i][j] = graph[i][j];
                }
                floyd[i][i] = 0;
            }
            for (int k = 0; k < n; ++ k) {
                for (int i = 0; i < n; ++ i) {
                    for (int j = 0; j < n; ++ j) {
                        if (floyd[i][k] < Integer.MAX_VALUE && floyd[k][j] < Integer.MAX_VALUE) {
                            floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                        }
                    }
                }
            }
            int[][] count = new int[n][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    if (floyd[i][j] < Integer.MAX_VALUE) {
                        for (int k = 0; k < n; ++ k) {
                            if (graph[i][k] < Integer.MAX_VALUE && floyd[k][j] < Integer.MAX_VALUE && graph[i][k] + floyd[k][j] == floyd[i][j]) {
                                count[i][j] ++;
                            }
                        }
                    }
                }
            }
            int[][] result = new int[n][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = i + 1; j < n; ++ j) {
                    if (floyd[i][j] < Integer.MAX_VALUE) {
                        for (int k = 0; k < n; ++ k) {
                            if (floyd[i][k] < Integer.MAX_VALUE && floyd[k][j] < Integer.MAX_VALUE && floyd[i][k] + floyd[k][j] == floyd[i][j]) {
                                result[i][j] += count[k][j];
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < n; ++ i) {
                for (int j = i + 1; j < n; ++ j) {
                    writer.print(result[i][j] + " ");
                }
            }
            writer.println();
        } catch (IOException ex) {
        }
        writer.close();
    }

    E() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new E().run();
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

    public Long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    private BufferedReader  reader;
    private StringTokenizer tokenizer;
}
