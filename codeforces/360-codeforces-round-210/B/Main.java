import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    boolean check(int[] a, int m, long delta) {
        int n = a.length;
        int[] maximum = new int[n];
        int max = 0;
        for (int i = 0; i < n; ++ i) {
            maximum[i] = 1;
            for (int j = 0; j < i; ++ j) {
                if (Math.abs(a[i] - a[j]) <= delta * (i - j)) {
                    maximum[i] = Math.max(maximum[i], maximum[j] + 1);
                }
            }
            max = Math.max(max, maximum[i]);
        }
        return max >= n - m;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            long low = 0;
            long high = (long)2e9;
            while (low < high) {
                long middle = low + high >> 1;
                if (check(a, m, middle)) {
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
