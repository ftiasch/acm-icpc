// Codeforces Round #143
// Problem C -- To Add or Not to Add
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int limit = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++ i) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        long[] sum = new long[n + 1];
        sum[n] = 0;
        for (int i = n - 1; i >= 0; -- i) {
            sum[i] = sum[i + 1] + a[i];
        }
        int result = 0;
        int minimum = 0;
        for (int i = 0, j = 0; i < n; ++ i) {
            while ((long)a[i] * (i - j) + (sum[i] - sum[j]) > limit) {
                j ++;
            }
            if (i - j + 1 > result) {
                result = i - j + 1;
                minimum = a[i];
            }
        }
        out.println(String.format("%d %d", result, minimum));
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
