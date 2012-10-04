// October Challenge 2012
// Need More Diamonds
// Problem code: DIAMOND
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
    final static int MAXN = 2000;

    static double[][] probability, coefficient;

    static {
        probability = new double[MAXN + 1][MAXN + 1];
        // probability[n][k] = chooce(n, k) / 2 ^ n
        probability[0][0] = 1.0;
        for (int i = 1; i <= MAXN; ++ i) {
            probability[i][0] = probability[i - 1][0] / 2.0;
            for (int j = 1; j <= i; ++ j) {
                probability[i][j] = (probability[i - 1][j] + probability[i - 1][j - 1]) / 2.0;
            }
        }
        coefficient = new double[MAXN + 1][MAXN + 1];
        for (int j = 0; j <= MAXN; ++ j) {
            for (int i = 1; i <= MAXN; ++ i) {
                coefficient[i][j] = coefficient[i - 1][j];
                if ((i & 1) == 1) {
                    coefficient[i][j] += probability[i - 1][j] / 2.0;
                }
            }
        }
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int testCount = in.nextInt();
        while (testCount > 0) {
            testCount --;
            int n = in.nextInt();
            double result = 0.0;
            for (int i = 1; i <= n; ++ i) {
                int a = in.nextInt();
                result += coefficient[n][i - 1] * a;
                result += coefficient[n][n - i] * a;
            }
            out.println(String.format("%.3f", result));
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
