// SGU 415 -- Necessary Coins
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Solver {
    boolean[][] solve(int n, int m, int[] a) {
        boolean[][] valid = new boolean[n + 1][m + 1];
        valid[0][0] = true;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j <= m; ++ j) {
                if (valid[i][j]) {
                    valid[i + 1][j] = true;
                    if (j + a[i] <= m) {
                        valid[i + 1][j + a[i]] = true;
                    }
                }
            }
        }
        return valid;
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++ i) {
            a[i] = in.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; ++ i) {
            b[i] = a[n - 1 - i];
        }
        boolean[][] prefix = solve(n, m, a);
        boolean[][] suffix = solve(n, m, b);
        ArrayList <Integer> result = new ArrayList <Integer>();
        for (int i = 0; i < n; ++ i) {
            boolean valid = false;
            for (int j = 0; j <= m && !valid; ++ j) {
                if (prefix[i][j] && suffix[n - 1 - i][m - j]) {
                    valid = true;
                }
            }
            if (!valid) {
                result.add(a[i]);
            }
        }
        out.println(result.size());
        for (int i = 0; i < result.size(); ++ i) {
            out.print(String.format("%d%c", result.get(i), i == result.size() - 1? '\n': ' '));
        }
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
