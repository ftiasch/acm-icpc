import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    long cost(long s, long a, long b) {
        return a <= s && s <= b ? Math.min(s - a, b - s) + b - a : Math.max(s - a, b - s);
    }

    boolean check(long[] a, long[] b, long maxCost) {
        int i = 0;
        for (long x : a) {
            int j = i;
            while (j < b.length && cost(x, b[i], b[j]) <= maxCost) {
                j ++;
            }
            i = j;
        }
        return i == b.length;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextLong();
            }
            long[] b = new long[m];
            for (int i = 0; i < m; ++ i) {
                b[i] = reader.nextLong();
            }
            long low = 0;
            long high = 20000000000L;
            while (low < high) {
                long middle = low + high >> 1;
                if (check(a, b, middle)) {
                    high = middle;
                } else {
                    low = middle + 1;
                }
            }
            writer.println(high);
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

    Long nextLong() throws IOException {
        return Long.parseLong(next());
    }
}
