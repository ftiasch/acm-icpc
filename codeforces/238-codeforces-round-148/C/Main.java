// Codeforces Round #148
// Problem C -- World Eater Brothers
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class Solver {
    int n, edgeCount;
    int[] firstEdge, to, nextEdge;

    void addEdge(int u, int v) {
        to[edgeCount] = v;
        nextEdge[edgeCount] = firstEdge[u];
        firstEdge[u] = edgeCount ++;
    }

    int[] parentEdge, down, up;
    ArrayList <Integer> order;

    void dfs(int p, int u) {
        order.add(u);
        down[u] = 0;
        for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
            int v = to[iter];
            if (v != p) {
                parentEdge[v] = iter;
                dfs(u, v);
                down[u] += down[v] + (iter & 1);
            }
        }
    }

    int solve(int parent, int root) { 
        order = new ArrayList <Integer>();
        parentEdge = new int[n];
        Arrays.fill(parentEdge, -1);
        down = new int[n];
        dfs(parent, root);
        int ret = Integer.MAX_VALUE;
        up = new int[n];
        for (int u : order) {
            int e = parentEdge[u];
            if (e != -1) {
                int p = to[e ^ 1];
                up[u] = up[p] + (~e & 1) + down[p] - (down[u] + (e & 1));
            } else {
                up[u] = 0;
            }
            ret = Math.min(ret, down[u] + up[u]);
        }
        return ret;
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        n = in.nextInt();
        firstEdge = new int[n];
        Arrays.fill(firstEdge, -1);
        to = new int[n << 1];
        nextEdge = new int[n << 1];
        for (int i = 0; i < n - 1; ++ i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            addEdge(a, b);
            addEdge(b, a);
        }
        int answer = solve(-1, 0);
        for (int i = 0; i < edgeCount; i += 2) {
            int a = to[i];
            int b = to[i ^ 1];
            answer = Math.min(answer, solve(a, b) + solve(b, a));
        }
        out.println(answer);
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
