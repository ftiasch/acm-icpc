import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int n = reader.nextInt();
            int k = reader.nextInt();
            if ((n - 1) / 2 < k) {
                writer.println(-1);
            } else {
                writer.println(n * k);
                for (int i = 0; i < n; ++ i) {
                    for (int j = 1; j <= k; ++ j) {
                        writer.println("" + (i + 1) + " " + ((i + j) % n + 1));
                    }
                }
            }
            // main
        } catch (IOException ex) {
        }
        writer.close();
    }

    Main() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    private InputReader reader;
    private PrintWriter writer;
}

class InputReader {
    InputReader(InputStream in) {
        reader    = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
    }

    private String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    public Integer nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    private BufferedReader  reader;
    private StringTokenizer tokenizer;
}
