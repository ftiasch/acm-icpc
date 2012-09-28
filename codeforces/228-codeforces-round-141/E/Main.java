// Codeforces Round #141
// Problem E -- The Road to Berland is Paved With Good Intentions
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class Solver {
    int n;
    int[] color;
    int[][] graph;
    boolean conflict;

    void dfs(int i, int c) {
        if (color[i] == -1) {
            color[i] = c;
            for (int j = 0; j < n; ++ j) {
                if (graph[i][j] != -1) {
                    dfs(j, c ^ graph[i][j]);
                }
            }
        } else {
            conflict |= color[i] != c;
        }
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        n = in.nextInt();
        int m = in.nextInt();
        graph = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = -1;
            }
        }
        for (int i = 0; i < m; ++ i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graph[a][b] = graph[b][a] = in.nextInt() ^ 1;
        }
        color = new int[n];
        Arrays.fill(color, -1);
        conflict = false;
        for (int i = 0; i < n; ++ i) {
            if (color[i] == -1) {
                dfs(i, 0);
            }
        }
        if (conflict) {
            out.println("Impossible");
        } else {
            int count = 0;
            for (int i = 0; i < n; ++ i) {
                if (color[i] == 1) {
                    count ++;
                }
            }
            out.println(count);
            for (int i = 0; i < n; ++ i) {
                if (color[i] == 1) {
                    count --;
                    out.print(String.format("%d%c", i + 1, count == 0? '\n': ' '));
                }
            }
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
