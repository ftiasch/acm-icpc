// SGU 206 -- Roads
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
    }

    int edgeCount;
    int[] firstEdge;
    int[] to, weight, nextEdge;
    int[] depth, parentEdge;

    int[][] graph;

    void addEdge(int u, int v) {
        to[edgeCount] = v;
        nextEdge[edgeCount] = firstEdge[u];
        firstEdge[u] = edgeCount ++;
    }

    void prepare(int p, int u) {
        depth[u] = p == -1 ? 0 : depth[p] + 1;
        for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
            int v = to[iter];
            if (v != p) {
                parentEdge[v] = iter;
                prepare(u, v);
            }
        }
    }

    final static int INF = 1000000000;

    int v;
    boolean[] visitX, visitY;
    int[] labelX, labelY, match, slack;

    boolean find(int i) {
        if (visitX[i]) {
            return false;
        }
        visitX[i] = true;
        for (int j = 0; j < v; ++ j) {
            if (labelX[i] + labelY[j] == graph[i][j]) {
                visitY[j] = true;
                if (match[j] == -1 || find(match[j])) {
                    match[j] = i;
                    return true;
                }
            } else {
                slack[j] = Math.min(slack[j], labelX[i] + labelY[j] - graph[i][j]);
            }
        }
        return false;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();

            edgeCount = 0;
            firstEdge = new int[n];
            Arrays.fill(firstEdge, -1);
            to = new int[n - 1 << 1];
            weight = new int[n - 1 << 1];
            nextEdge = new int[n - 1 << 1];

            int[] a = new int[m];
            int[] b = new int[m];
            int[] weight = new int[m];
            for (int i = 0; i < m; ++ i) {
                a[i] = reader.nextInt() - 1;
                b[i] = reader.nextInt() - 1;
                weight[i] = reader.nextInt();
                if (i < n - 1) {
                    addEdge(a[i], b[i]);
                    addEdge(b[i], a[i]);
                }
            }

            depth = new int[n];
            parentEdge = new int[n];
            prepare(-1, 0);

            v = Math.max(n - 1, m - n + 1);
            graph = new int[v][v];
            //for (int[] row : graph) {
            //    Arrays.fill(row, -INF);
            //}

            for (int i = n - 1; i < m; ++ i) {
                int x = a[i];
                int y = b[i];
                while (x != y) {
                    if (depth[x] < depth[y]) {
                        x ^= y;
                        y ^= x;
                        x ^= y;
                    }
                    int id = parentEdge[x] >> 1;
                    if (weight[id] >= weight[i]) {
                        graph[id][i - n + 1] = weight[id] - weight[i];
                    }
                    x = to[parentEdge[x] ^ 1];
                }
            }

            labelX = new int[v];
            labelY = new int[v];
            visitX = new boolean[v];
            visitY = new boolean[v];
            match = new int[v];
            slack = new int[v];
            for (int i = 0; i < v; ++ i) {
                labelX[i] = -INF;
                for (int j = 0; j < v; ++ j) {
                    labelX[i] = Math.max(labelX[i], graph[i][j]);
                }
                labelY[i] = 0;
            }
            Arrays.fill(match, -1);
            for (int i = 0; i < v; ++ i) {
                while (true) {
                    Arrays.fill(visitX, false);
                    Arrays.fill(visitY, false);
                    Arrays.fill(slack, INF);
                    if (find(i)) {
                        break;
                    }
                    int delta = INF;
                    for (int j = 0; j < v; ++ j) {
                        if (!visitY[j]) {
                            delta = Math.min(delta, slack[j]);
                        }
                    }
                    for (int j = 0; j < v; ++ j) {
                        if (visitX[j]) {
                            labelX[j] -= delta;
                        }
                        if (visitY[j]) {
                            labelY[j] += delta;
                        }
                    }
                }
            }
            for (int i = 0; i < n - 1; ++ i) {
                writer.println(weight[i] - labelX[i]);
            }
            for (int i = 0; i < m - n + 1; ++ i) {
                writer.println(weight[n - 1 + i] + labelY[i]);
            }
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
