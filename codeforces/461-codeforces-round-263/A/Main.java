import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() throws IOException {
        int n = reader.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++ i) {
            a[i] = reader.nextInt();
        }
        Arrays.sort(a);
        long result = 0;
        for (int i = 0; i < n; ++ i) {
            result += (long)a[i] * Math.min(i + 2, n);
        }
        writer.println(result);
        writer.close();
    }

    Main() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
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

    public String next() throws IOException {
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
