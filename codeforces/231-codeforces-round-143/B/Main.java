// Codeforces Round #143
// Problem B -- Magic, Wizardry and Wonders
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class Solver {
    boolean check(int n, int d, int l) {
        int positive = n + 1 >> 1;
        int negative = n >> 1;
        int maximum = positive * l - negative;
        int minimum = positive - negative * l;
        return minimum <= d && d <= maximum;
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int d = in.nextInt();
        int l = in.nextInt();
        if (!check(n, d, l)) {
            out.println(-1);
        } else {
            int[] result = new int[n];
            for (int i = n - 1; i >= 0; -- i) {
                int sign = (i & 1) == 1 ? -1 : 1;
                for (int j = 1; j <= l; ++ j) {
                    if (check(i, d - sign * j, l)) {
                        d -= sign * j;
                        result[i] = j;
                        break;
                    }
                }
            }
            for (int i = 0; i < n; ++ i) {
                out.print(String.format("%d ", result[i]));
            }
            out.println();
        }
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
