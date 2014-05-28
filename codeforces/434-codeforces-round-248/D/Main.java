import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int n = reader.nextInt();
            int q = reader.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            int[] c = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
                b[i] = reader.nextInt();
                c[i] = reader.nextInt();
            }
            int[] l = new int[n];
            int[] r = new int[n];
            int verticesCount  = 0;
            int offset = Integer.MIN_VALUE;
            int[] start = new int[n];
            for (int i = 0; i < n; ++ i) {
                l[i] = reader.nextInt();
                r[i] = reader.nextInt();
                for (int x = l[i]; x <= r[i]; ++ x) {
                    offset = Math.max(offset, a[i] * x * x + b[i] * x + c[i]);
                }
                start[i] = verticesCount;
                verticesCount += r[i] - l[i] + 2;
            }
            Network network = new Network(verticesCount + 2);
            int source = verticesCount;
            int target = source + 1;
            int[][] vertices = new int[n][];
            for (int i = 0; i < n; ++ i) {
                int m = r[i] - l[i] + 1;
                vertices[i] = new int[m + 1];
                for (int j = 0; j <= m; ++ j) {
                    vertices[i][j] = start[i] + j;
                }
                for (int j = 0; j < m; ++ j) {
                    int x = l[i] + j;
                    network.addEdge(vertices[i][j], vertices[i][j + 1], offset - (a[i] * x * x + b[i] * x + c[i]));
                }
                network.addEdge(source, vertices[i][0], Integer.MAX_VALUE);
                network.addEdge(vertices[i][m], target, Integer.MAX_VALUE);
            }
            for (int i = 0; i < q; ++ i) {
                int u = reader.nextInt() - 1;
                int v = reader.nextInt() - 1;
                int d = reader.nextInt();
                for (int j = l[u]; j <= r[u]; ++ j) {
                    int k = Math.min(j - d, r[v] + 1);
                    if (l[v] <= k) {
                        network.addEdge(vertices[u][j - l[u]], vertices[v][k - l[v]], Integer.MAX_VALUE);
                    }
                }
            }
            int result = n * offset - network.getMaxFlow(source, target);
            writer.println(result);
        } catch (IOException ex) {
        }
        writer.close();
    }

    class Network {
        public void addEdge(int from, int to, int capacity) {
            Edge e = new Edge(to, capacity);
            Edge f = new Edge(from, 0);
            e.back = f;
            edges.get(from).add(e);
            f.back = e;
            edges.get(to).add(f);
        }

        public int getMaxFlow(int source, int target) {
            int[] level = new int[n];
            int[] queue = new int[n];
            int result = 0;
            while (true) {
                Arrays.fill(level, -1);
                level[target] = 0;
                int tail = 0;
                queue[tail ++] = target;
                for (int head = 0; head < tail && level[source] == -1; ++ head) {
                    int v = queue[head];
                    for (Edge e : edges.get(v)) {
                        int u = e.to;
                        if (e.back.capacity > 0 && level[u] == -1) {
                            level[u] = level[v] + 1;
                            queue[tail ++] = u;
                        }
                    }
                }
                if (level[source] == -1) {
                    break;
                }
                result += augment(level, source, target, Integer.MAX_VALUE);
            }
            return result;
        }

        private int augment(int[] level, int u, int target, int left) {
            if (u == target) {
                return left;
            }
            int delta = 0;
            for (Edge e : edges.get(u)) {
                if (e.capacity > 0 && level[u] == level[e.to] + 1) {
                    int result = augment(level, e.to, target, Math.min(left - delta, e.capacity));
                    e.capacity -= result;
                    e.back.capacity += result;
                    delta += result;
                    if (delta == left) {
                        return delta;
                    }
                }
            }
            level[u] = -1;
            return delta;
        }

        Network(int n) {
            this.n = n;
            edges = new ArrayList <List <Edge>>();
            for (int i = 0; i < n; ++ i) {
                edges.add(new ArrayList <Edge>());
            }
        }

        class Edge {
            Edge(int to, int capacity) {
                this.to = to;
                this.capacity = capacity;
            }

            int  to, capacity;
            Edge back;
        }

        int n;
        List <List <Edge>> edges;
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
