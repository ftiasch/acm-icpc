import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    int n;
    int[] level, current;
    int[][] capacity, cut;

    boolean bfs(int source, int target) {
        level = new int[n];
        Arrays.fill(level, -1);
        level[source] = 0;
        Queue <Integer> queue = new LinkedList <Integer>();
        queue.offer(source);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < n; ++ v) {
                if (capacity[u][v] > 0 && level[v] == -1) {
                    level[v] = level[u] + 1;
                    queue.offer(v);
                }
            }
        }
        return level[target] != -1;
    }

    int dfs(int u, int target, int limit) {
        if (u == target) {
            return limit;
        }
        int delta = 0;
        while (current[u] < n) {
            int v = current[u];
            if (capacity[u][v] > 0 && level[u] + 1 == level[v]) {
                int ret = dfs(v, target, Math.min(limit - delta, capacity[u][v]));
                delta += ret;
                capacity[u][v] -= ret;
                capacity[v][u] += ret;
            }
            if (delta == limit) {
                break;
            }
            current[u] ++;
        }
        return delta;
    }

    int maxFlow(int source, int target) {
        int[][] backup = new int[n][];
        for (int i = 0; i < n; ++ i) {
            backup[i] = capacity[i].clone();
        }
        int flow = 0;
        while (bfs(source, target)) {
            current = new int[n];
            flow += dfs(source, target, Integer.MAX_VALUE);
        }
        capacity = backup;
        return flow;
    }

    void prepare(ArrayList <Integer> vertices) {
        if (vertices.size() >= 2) {
            int flow = maxFlow(vertices.get(0), vertices.get(1));
            ArrayList <Integer> sourceSet = new ArrayList <Integer>();
            ArrayList <Integer> targetSet = new ArrayList <Integer>();
            for (int v : vertices) {
                if (level[v] == -1) {
                    targetSet.add(v);
                } else {
                    sourceSet.add(v);
                }
            }
            for (int u : sourceSet) {
                for (int v : targetSet) {
                    cut[u][v] = cut[v][u] = Math.min(cut[u][v], flow);
                }
            }
            prepare(sourceSet);
            prepare(targetSet);
        }
    }

    ArrayList <Integer> order;

    int construct(ArrayList <Integer> vertices) {
        if (vertices.size() >= 2) {
            int flow = Integer.MAX_VALUE;
            int source = -1;
            int target = -1;
            for (int u : vertices) {
                for (int v : vertices) {
                    if (cut[u][v] < flow) {
                        flow = cut[u][v];
                        source = u;
                        target = v;
                    }
                }
            }
            maxFlow(source, target);
            ArrayList <Integer> sourceSet = new ArrayList <Integer>();
            ArrayList <Integer> targetSet = new ArrayList <Integer>();
            for (int v : vertices) {
                if (level[v] == -1) {
                    targetSet.add(v);
                } else {
                    sourceSet.add(v);
                }
            }
            return flow + construct(sourceSet) + construct(targetSet);
        }
        order.add(vertices.get(0));
        return 0;
    }

    public void run() {
        try {
            n = reader.nextInt();
            capacity = new int[n][n];
            int m = reader.nextInt();
            for (int i = 0; i < m; ++ i) {
                int a = reader.nextInt() - 1;
                int b = reader.nextInt() - 1;
                int c = reader.nextInt();
                capacity[a][b] += c;
                capacity[b][a] += c;
            }
            ArrayList <Integer> vertices = new ArrayList <Integer>();
            for (int i = 0; i < n; ++ i) {
                vertices.add(i);
            }
            cut = new int[n][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    cut[i][j] = Integer.MAX_VALUE;
                }
            }
            prepare(vertices);
            order = new ArrayList <Integer>();
            writer.println(construct(vertices));
            for (int i = 0; i < n; ++ i) {
                writer.print(String.format("%d%c", order.get(i) + 1, i == n - 1 ? '\n' : ' '));
            }
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
