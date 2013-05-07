// Codeforces Round #182
// Problem D -- Yaroslav and Divisors
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

    class Query implements Comparable <Query> {
        int id, l, r;

        Query(int id, int l, int r) {
            this.id = id;
            this.l = l;
            this.r = r;
        }

        public int compareTo(Query o) {
            return r - o.r;
        }
    }

    @SuppressWarnings("unchecked")
    public void run() {
        try {
            int n = reader.nextInt();
            int q = reader.nextInt();
            int[] position = new int[n + 1];
            for (int i = 1; i <= n; ++ i) {
                position[reader.nextInt()] = i;
            }
            ArrayList[] lefts = new ArrayList[n + 1];
            for (int i = 1; i <= n; ++ i) {
                lefts[i] = new ArrayList <Integer>();
            }
            for (int i = 1; i <= n; ++ i) {
                for (int j = i; j <= n; j += i) {
                    int u = position[i];
                    int v = position[j];
                    lefts[Math.max(u, v)].add(Math.min(u, v));
                }
            }
            Query[] queries = new Query[q];
            for (int i = 0; i < q; ++ i) {
                int l = reader.nextInt();
                int r = reader.nextInt();
                queries[i] = new Query(i, l, r);
            }
            Arrays.sort(queries);
            int[] count = new int[n + 1];
            int[] answer = new int[q];
            for (int i = 1, j = 0; i <= n; ++ i) {
                for (Object l : lefts[i]) {
                    for (int k = (Integer)l; k >= 1; k -= -k & k) {
                        count[k] ++;
                    }
                }
                while (j < q && queries[j].r == i) {
                    for (int k = queries[j].l; k <= n; k += -k & k) {
                        answer[queries[j].id] += count[k];
                    }
                    j ++;
                }
            }
            for (int i = 0; i < q; ++ i) {
                writer.println(answer[i]);
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
