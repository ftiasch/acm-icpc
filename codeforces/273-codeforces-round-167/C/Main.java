// Codeforces Round #167
// Problem C -- Dima and Horses
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

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[] degree = new int[n];
            int[][] graph = new int[n][3];
            for (int i = 0; i < m; ++ i) {
                int a = reader.nextInt() - 1;
                int b = reader.nextInt() - 1;
                graph[a][degree[a] ++] = b;
                graph[b][degree[b] ++] = a;
            }
            int[] color = new int[n];
            int[] conflict = degree.clone();
            Queue <Integer> queue = new LinkedList <Integer>();
            for (int i = 0; i < n; ++ i) {
                if (conflict[i] > 1) {
                    queue.offer(i);
                }
            }
            boolean[] visit = new boolean[n];
            Arrays.fill(visit, true);
            while (!queue.isEmpty()) {
                int u = queue.poll();
                visit[u] = false;
                if (conflict[u] <= 1) {
                    continue;
                }
                color[u] ^= 1;
                conflict[u] = degree[u] - conflict[u];
                for (int i = 0; i < degree[u]; ++ i) {
                    int v = graph[u][i];
                    conflict[v] += color[u] == color[v] ? 1 : -1;
                    if (conflict[v] > 1 && !visit[v]) {
                        visit[v] = true;
                        queue.offer(v);
                    }
                }
            }
            for (int i = 0; i < n; ++ i) {
                writer.print(color[i]);
            }
            writer.println();
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
