import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

class Solver {
    final static int INF = 100000000;

    PrintWriter out;
    int[][] step;
    boolean[] used;
    ArrayList <Stack <Integer> > state;

    void move(int source, int target) {
        int id = state.get(source).pop();
        String result = String.format("move %d from %d to %d", id, source, target);
        if (!state.get(target).empty()) {
            result += String.format(" atop %d", state.get(target).peek());
        }
        state.get(target).push(id);
        out.println(result);
    }

    void construct(int n, int m, int source, int target) {
//System.err.printf("%d, %d, %d, %d\n", n, m, source, target);
        if (n == 1) {
            move(source, target);
        } else {
            int k = 1;
            while (step[n][m] != step[k][m] + step[n - k][m - 1] + step[k][m]) {
                k ++;
            }
            int buffer = 1;
            while (buffer == source || buffer == target || used[buffer]) {
                buffer ++;
            }
            construct(k, m, source, buffer);
            used[buffer] = true;
            construct(n - k, m - 1, source, target);
            used[buffer] = false;
            construct(k, m, buffer, target);
        }
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        this.out = out;
        int n = in.nextInt();
        int m = in.nextInt();
        step = new int[n + 1][m + 1];
        for (int i = 1; i <= n; ++ i) {
            for (int j = 1; j <= m; ++ j) {
                step[i][j] = INF;
            }
        }
        for (int j = 2; j <= m; ++ j) {
            step[1][j] = 1;
        }
        for (int i = 2; i <= n; ++ i) {
            for (int j = 3; j <= m; ++ j) {
                for (int k = 1; k < i; ++ k) {
                    step[i][j] = Math.min(step[i][j], step[k][j] + step[i - k][j - 1] + step[k][j]);
                }
            }
        }
        state = new ArrayList <Stack <Integer> >();
        for (int j = 0; j <= m; ++ j) {
            state.add(new Stack <Integer>());
        }
        for (int i = n; i >= 1; -- i) {
            state.get(1).push(i);
        }
        out.println(step[n][m]);
        used = new boolean[m + 1];
        Arrays.fill(used, false);
        construct(n, m, 1, m);
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
