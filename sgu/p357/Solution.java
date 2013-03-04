// SGU 357 -- Remote Control
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

    final static int ORDER[] = {1, 2, 3, 10, 4, 5, 6, 11, 7, 8, 9, 12, 0};

    Queue <Integer> queue;
    int[] need;

    void update(int k, int v) {
        if (v < need[k]) {
            need[k] = v;
            queue.offer(k);
        }
    }

    public void run() {
        try {
            boolean[] valid = new boolean[13];
            for (int i = 0; i < 13; ++ i) {
                int token = reader.nextInt();
                valid[ORDER[i]] = token == 1;
            }
            int source = reader.nextInt();
            int target = reader.nextInt();
            queue = new LinkedList <Integer>();
            queue.offer(source);
            need = new int[100];
            Arrays.fill(need, Integer.MAX_VALUE);
            need[source] = 0;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int i = 0; i < 10; ++ i) {
                    if (valid[i]) {
                        update(i, need[u] + 1);
                    }
                }
                if (valid[10]) {
                    update((u + 1) % 100, need[u] + 1);
                }
                if (valid[11]) {
                    update((u + 99) % 100, need[u] + 1);
                }
                if (valid[12]) {
                    for (int i = 0; i < 10; ++ i) {
                        for (int j = 0; j < 10; ++ j) {
                            if (valid[i] && valid[j]) {
                                update(i * 10 + j, need[u] + 3);
                            }
                        }
                    }
                }
            }
            writer.println(need[target] == Integer.MAX_VALUE ? -1 : need[target]);
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
