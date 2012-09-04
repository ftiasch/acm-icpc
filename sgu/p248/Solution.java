// SGU 248 -- Integer Linear Programming
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solver {
    final static int INFINITY = 1000000000;

    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int[] c = new int[n];
        for (int i = 0; i < n; ++ i) {
            c[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] best = new int[m + 1];
        Arrays.fill(best, INFINITY);
        best[0] = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = c[i]; j <= m; ++ j) {
                best[j] = Math.min(best[j], best[j - c[i]] + 1);
            }
        }
        out.println(best[m] == INFINITY? -1: best[m]);
        out.close();
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
