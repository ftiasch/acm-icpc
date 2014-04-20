import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[] row = generate(n);
            int[] column = generate(m);
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    writer.print(String.format("%d%c", row[i] * column[j], j == m - 1 ? '\n' : ' '));
                }
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    int[] generate(int n) {
        if (n == 1) {
            return new int[]{1};
        }
        if (n == 2) {
            return new int[]{3, 4};
        }
        int[] result = new int[n];
        Arrays.fill(result, 1);
        if (n % 2 == 0) {
            result[0] = n / 2 - 1;
        } else {
            result[0] = (n + 1) / 2;
            result[1] = 2;
        }
        return result;
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
