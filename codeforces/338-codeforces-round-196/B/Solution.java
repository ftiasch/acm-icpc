import java.io.*;
import java.math.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class Solution {
    int[] search(ArrayList[] tree, int source) {
        int n = tree.length;
        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        distance[source] = 0;
        Queue <Integer> queue = new LinkedList <Integer>();
        queue.offer(source);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Object obj : tree[u]) {
                int v = (Integer)obj;
                if (distance[v] == -1) {
                    distance[v] = distance[u] + 1;
                    queue.offer(v);
                }
            }
        }
        return distance;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int d = reader.nextInt();
            boolean[] affected = new boolean[n];
            for (int i = 0; i < m; ++ i) {
                int p = reader.nextInt() - 1;
                affected[p] = true;
            }
            ArrayList[] tree = new ArrayList[n];
            for (int i = 0; i < n; ++ i) {
                tree[i] = new ArrayList <Integer>();
            }
            for (int i = 0; i < n - 1; ++ i) {
                int a = reader.nextInt() - 1;
                int b = reader.nextInt() - 1;
                tree[a].add(b);
                tree[b].add(a);
            }
            int u = 0;
            while (!affected[u]) {
                u ++;
            }
            int[] distance = search(tree, u);
            int v = u;
            for (int i = 0; i < n; ++ i) {
                if (affected[i] && distance[i] > distance[v]) {
                    v = i;
                }
            }
            distance = search(tree, v);
            u = v;
            for (int i = 0; i < n; ++ i) {
                if (affected[i] && distance[i] > distance[u]) {
                    u = i;
                }
            }
            int[] anotherDistance = search(tree, u);
            int answer = 0;
            for (int i = 0; i < n; ++ i) {
                if (Math.max(distance[i], anotherDistance[i]) <= d) {
                    answer ++;
                }
            }
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
