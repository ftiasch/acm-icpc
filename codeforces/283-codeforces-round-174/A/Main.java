// Codeforces Round #174
// Problem A -- Cows and Sequence
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
            int length = 1;
            long sum = 0;
            long[] delta = new long[200000 + 1];
            int n = reader.nextInt();
            while (n > 0) {
                n --;
                int type = reader.nextInt();
                if (type == 1) {
                    int a = reader.nextInt();
                    int x = reader.nextInt();
                    sum += a * x;
                    delta[a - 1] += x;
                } else if (type == 2) {
                    delta[length] = reader.nextInt();
                    if (length > 0) {
                        delta[length - 1] -= delta[length];
                    }
                    sum += delta[length ++];
                } else {
                    sum -= delta[-- length];
                    if (length > 0) {
                        delta[length - 1] += delta[length];
                    }
                }
                writer.println(String.format("%.6f", (double)sum / length));
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
