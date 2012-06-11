// SGU 133 -- Border
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solver {
    class Pair implements Comparable <Pair> {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int compareTo(Pair other) {
            return this.a - other.a;
        }
    }

    class Trie {
        Trie[] children;

        Trie() {
            children = new Trie[2];
            children[0] = children[1] = null;
        }
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; ++ i) {
            int a = in.nextInt();
            int b = in.nextInt();
            pairs[i] = new Pair(a, b);
        }
        Arrays.sort(pairs);
        Trie root = new Trie();
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            boolean found = false;
            Trie p = root;
            for (int j = 30; j >= 0; -- j) {
                int token = pairs[i].b >> j & 1;
                if (token == 0 && p.children[1] != null) {
                    found = true;
                }
                if (p.children[token] == null) {
                    p.children[token] = new Trie();
                }
                p = p.children[token];
            }
            if (found) {
                result ++;
            }
        }
        out.println(result);
        out.flush();
    }
}

public class Solution {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
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
