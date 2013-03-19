// Codeforces Round #174
// Problem B -- Cow Program
import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    InputReader reader;
    PrintWriter writer;

    Main() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int[] a = new int[n];
            for (int i = 1; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            int[] go = new int[n << 1];
            long[] sum = new long[n << 1];
            for (int i = 0; i < n << 1; ++ i) {
                go[i] = (i >> 1) + ((i & 1) == 1 ? -1 : 1) * a[i >> 1];
                if (go[i] < 0 || go[i] >= n) {
                    go[i] = -1;
                } else {
                    go[i] <<= 1;
                    go[i] |= ~i & 1;
                }
                sum[i] = a[i >> 1];
            }
            for (int _ = 0; _ < 20; ++ _) {
                int[] newGo = go.clone();
                long[] newSum = sum.clone();
                for (int i = 0; i < n << 1; ++ i) {
                    if (go[i] != -1) {
                        newGo[i] = go[go[i]];
                        newSum[i] += sum[go[i]];
                    }
                }
                go = newGo;
                sum = newSum;
            }
            for (int i = 0; i < n - 1; ++ i) {
                int begin = (i + 1) << 1 | 1;
                int end = go[begin];
                if (end != -1) {
                    writer.println(-1);
                } else {
                    writer.println(i + 1 + sum[begin]);
                }
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
