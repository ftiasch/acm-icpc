import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[] t = new int[m];
            int[] l = new int[m];
            int[] r = new int[m];
            int[] d = new int[m];
            for (int i = 0; i < m; ++ i) {
                t[i] = reader.nextInt();
                l[i] = reader.nextInt() - 1;
                r[i] = reader.nextInt();
                d[i] = reader.nextInt();
            }
            int[] init = new int[n];
            int[] delta = new int[n];
            Arrays.fill(init, 1000000000);
            for (int i = 0; i < m; ++ i) {
                if (t[i] == 1) {
                    for (int j = l[i]; j < r[i]; ++ j) {
                        delta[j] += d[i];
                    }
                } else {
                    for (int j = l[i]; j < r[i]; ++ j) {
                        init[j] = Math.min(init[j], d[i] - delta[j]);
                    }
                }
            }
            Arrays.fill(delta, 0);
            boolean valid = true;
            for (int i = 0; i < m && valid; ++ i) {
                if (t[i] == 1) {
                    for (int j = l[i]; j < r[i]; ++ j) {
                        delta[j] += d[i];
                    }
                } else {
                    int max = Integer.MIN_VALUE;
                    for (int j = l[i]; j < r[i]; ++ j) {
                        max = Math.max(max, init[j] + delta[j]);
                    }
                    valid &= max == d[i];
                }
            }
            if (valid) {
                writer.println("YES");
                for (int i = 0; i < n; ++ i) {
                    writer.print("" + init[i] + " ");
                }
                writer.println();
            } else {
                writer.println("NO");
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Main() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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
