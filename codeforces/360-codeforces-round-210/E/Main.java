import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    boolean check(ArrayList <ArrayList <Edge> > edges, int s1, int s2, int t, int strategy) {
        int n = edges.size();
        long[] distance = new long[n];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[s1] = 1;
        distance[s2] = 0;
        boolean[] visit = new boolean[n];
        visit[s1] = visit[s2] = true;
        Queue <Integer> queue = new LinkedList <Integer>();
        queue.offer(s1);
        queue.offer(s2);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visit[u] = false;
            for (Edge edge : edges.get(u)) {
                int v = edge.to;
                edge.length = (distance[u] & 1) == strategy ? edge.minLength : edge.maxLength;
                if (distance[u] + 2 * edge.length < distance[v]) {
                    distance[v] = distance[u] + 2 * edge.length;
                    if (!visit[v]) {
                        visit[v] = true;
                        queue.offer(v);
                    }
                }
            }
        }
        return (distance[t] & 1) == strategy;
    }

    void print(String message, int m1, int m2) {
        writer.println(message);
        ArrayList <Edge> edgeList = Edge.edges;
        for (int i = m1; i < m1 + m2; ++ i) {
            writer.println(edgeList.get(i).length);
        }
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m1 = reader.nextInt();
            int m2 = reader.nextInt();
            int s1 = reader.nextInt() - 1;
            int s2 = reader.nextInt() - 1;
            int t = reader.nextInt() - 1;
            ArrayList <ArrayList <Edge> > edges = new ArrayList <ArrayList <Edge> >();
            for (int i = 0; i < n; ++ i) {
                edges.add(new ArrayList <Edge>());
            }
            for (int i = 0; i < m1; ++ i) {
                int u = reader.nextInt() - 1;
                int v = reader.nextInt() - 1;
                int w = reader.nextInt();
                Edge edge = new Edge(u, v, w, w);
                edges.get(u).add(edge);
            }
            for (int i = 0; i < m2; ++ i) {
                int u = reader.nextInt() - 1;
                int v = reader.nextInt() - 1;
                int a = reader.nextInt();
                int b = reader.nextInt();
                Edge edge = new Edge(u, v, a, b);
                edges.get(u).add(edge);
            }
            if (check(edges, s1, s2, t, 1)) {
                print("WIN", m1, m2);
            } else if (check(edges, s2, s1, t, 0)) {
                print("DRAW", m1, m2);
            } else {
                writer.println("LOSE");
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

class Edge {
    Edge(int from, int to, int minLength, int maxLength) {
        this.from = from;
        this.to = to;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.length = minLength;
        edges.add(this);
    }

    int id, from, to, minLength, maxLength, length;

    static ArrayList <Edge> edges = new ArrayList <Edge>();
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
