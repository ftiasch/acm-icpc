// SGU 230 -- Weighings
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

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[] degree = new int[n];
            ArrayList[] tos = new ArrayList[n];
            for (int i = 0; i < n; ++ i) {
                tos[i] = new ArrayList <Integer>();
            }
            for (int i = 0; i < m; ++ i) {
                int a = reader.nextInt() - 1;
                int b = reader.nextInt() - 1;
                tos[a].add(b);
                degree[b] ++;
            }
            ArrayList <Integer> queue = new ArrayList <Integer>();
            for (int i = 0; i < n; ++ i) {
                if (degree[i] == 0) {
                    queue.add(i);
                }
            }
            int[] answer = new int[n];
            for (int head = 0; head < queue.size(); ++ head) {
                int u = queue.get(head);
                answer[u] = head + 1;
                for (Object iter : tos[u]) {
                    int v = (Integer)iter;
                    degree[v] --;
                    if (degree[v] == 0) {
                        queue.add(v);
                    }
                }
            }
            if (queue.size() == n) {
                for (int i = 0; i < n; ++ i) {
                    writer.print(String.format("%d ", answer[i]));
                }
                writer.println();
            } else {
                writer.println("No solution");
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
