// Codeforces Round #177
// Problem D -- Polo the Penguin and Trees
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    int n;
    ArrayList[] edges;

    long answer;

    long pairs(long n) {
        return n * (n - 1) / 2;
    }

    int solve(int p, int u) {
        int size = 1;
        ArrayList <Integer> subtrees = new ArrayList <Integer>();
        for (Object iter : edges[u]) {
            int v = (Integer)iter;
            if (v != p) {
                int ret = solve(u, v);
                size += ret;
                subtrees.add(ret);
            }
        }
        long pass = pairs(size);
        for (int subtree : subtrees) {
            pass -= pairs(subtree);
        }
        answer -= pass * pass;
        long out = (n - size) * size;
        answer -= 2 * out * pass;
        return size;
    }

    @SuppressWarnings("unchecked")
    public void run() {
        try {
            n = reader.nextInt();
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
            answer = pairs(n) * pairs(n);
            solve(-1, 0);
            writer.println(answer);
        } catch (IOException ex) { 
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
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
