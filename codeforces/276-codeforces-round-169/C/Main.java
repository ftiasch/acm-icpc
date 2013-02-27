// Codeforces Round #169
// Problem D -- Little Girl and Maximum XOR
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
            int q = reader.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            Arrays.sort(a);
            int[] delta = new int[n];
            for (int i = 0; i < q; ++ i) {
                int l = reader.nextInt() - 1;
                int r = reader.nextInt() - 1;
                delta[r] ++;
                if (l > 0) {
                    delta[l - 1] --;
                }
            }
            for (int i = n - 1; i >= 1; -- i) {
                delta[i - 1] += delta[i];
            }
            Arrays.sort(delta);
            long answer = 0;
            for (int i = 0; i < n; ++ i) {
                answer += (long)a[i] * delta[i];
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
}
