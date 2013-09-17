import java.io.*;
import java.math.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class Main {
    int nodeCount;
    int[] size, position;
    ArrayList[] edges;

    void prepare(int p, int u) {
        size[u] = 1;
        position[u] = nodeCount ++;
        for (Object obj : edges[u]) {
            int v = (Integer)obj;
            if (v != p) {
                prepare(u, v);
                size[u] += size[v];
            }
        }
    }

    class SegmentTree {
        int[] weight, maximum;

        SegmentTree(int n) {
            weight = new int[n << 1];
            maximum = new int[n << 1];
        }

        int id(int l, int r) {
            return l + r | (l != r ? 1 : 0);
        }

        void insert(int l, int r, int a, int b, int c) {
            if (b < l || r < a) {
                return;
            }
            int i = id(l, r);
            if (a <= l && r <= b) {
                weight[i] = Math.max(weight[i], c);
                maximum[i] = Math.max(maximum[i], c);
            } else {
                int m = l + r >> 1;
                insert(l, m, a, b, c);
                insert(m + 1, r, a, b, c);
                maximum[i] = Math.max(weight[i], Math.max(maximum[id(l, m)], maximum[id(m + 1, r)]));
            }
        }

        int query(int l, int r, int a, int b) {
            if (b < l || r < a) {
                return Integer.MIN_VALUE;
            }
            int i = id(l, r);
            if (a <= l && r <= b) {
                return maximum[i];
            }
            int m = l + r >> 1;
            return Math.max(weight[i], Math.max(query(l, m, a, b), query(m + 1, r, a, b)));
        }
    };

    public void run() {
        try {
            int n = reader.nextInt();
            edges = new ArrayList[n];
            for (int i = 0; i < n; ++ i) {
                edges[i] = new ArrayList <Integer>();
            }
            for (int i = 0; i < n - 1; ++ i) {
                int a = reader.nextInt() - 1;
                int b = reader.nextInt() - 1;
                edges[a].add(b);
                edges[b].add(a);
            }
            nodeCount = 0;
            size = new int[n];
            position = new int[n];
            prepare(-1, 0);
            SegmentTree fill = new SegmentTree(n);
            SegmentTree empty = new SegmentTree(n);
            int q = reader.nextInt();
            for (int i = 1; i <= q; ++ i) {
                int t = reader.nextInt();
                int u = reader.nextInt() - 1;
                if (t == 1) {
                    fill.insert(0, n - 1, position[u], position[u] + size[u] - 1, i);
                } else if (t == 2) {
                    empty.insert(0, n - 1, position[u], position[u], i);
                } else if (t == 3) {
                    int f = fill.query(0, n - 1, position[u], position[u]);
                    int e = empty.query(0, n - 1, position[u], position[u] + size[u] - 1);
                    writer.println(f > e ? 1 : 0);
                }
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
