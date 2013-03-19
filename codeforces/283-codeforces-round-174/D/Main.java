// Codeforces Round #174
// Problem D -- Cows and Cool Sequences
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

    long[] a;
    int[] p;

    boolean valid(int i, int j) {
        if (a[i] % a[j] != 0) {
            return false;
        }
        if (p[j] == 0) {
            return true;
        }
        if (p[j] - p[i] == j - i) {
            return true;
        }
        return p[j] <= j - i - 1;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            a = new long[n];
            p = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextLong();
                while (a[i] % 2 == 0) {
                    p[i] ++;
                    a[i] /= 2;
                }
            }
            int[] need = new int[n];
            int answer = n - 1;
            need[0] = 0;
            for (int i = 1; i < n; ++ i) {
                need[i] = i;
                for (int j = 0; j < i; ++ j) {
                    if (valid(j, i)) {
                        need[i] = Math.min(need[i], need[j] + i - j - 1);
                    }
                }
                answer = Math.min(answer, need[i] + n - i - 1);
            }
            writer.println(answer);
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

    Long nextLong() throws IOException {
        return Long.parseLong(next());
    }
}
