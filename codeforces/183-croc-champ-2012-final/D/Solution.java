// Croc Champ 2012 - Final
// Problem D -- T-shirt
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    int n, m;

    void update(double[] fit, double[] atLeast) {
        double old = atLeast[0];
        atLeast[0] = 0.0;
        for (int i = 1; i <= n; ++ i) {
            double ret = old * fit[i - 1] + atLeast[i - 1] * (1 - fit[i - 1]);
            old = atLeast[i];
            atLeast[i] = ret;
        }
    }

    public void run() {
        try {
            n = reader.nextInt();
            m = reader.nextInt();
            double[][] fit = new double[m][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    fit[j][i] = reader.nextInt() / 1000.0;
                }
            }
            double[][] atLeast = new double[m][n + 1];
            for (int i = 0; i < m; ++ i) {
                Arrays.fill(atLeast[i], 1.0);
                update(fit[i], atLeast[i]);
            }
            double answer = 0.0;
            for (int _ = 0; _ < n; ++ _) {
                int x = 0;
                for (int i = 0; i < m; ++ i) {
                    if (atLeast[i][n] > atLeast[x][n]) {
                        x = i;
                    }
                }
                answer += atLeast[x][n];
                update(fit[x], atLeast[x]);
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

    Double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}
