import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            int result = Integer.MIN_VALUE;
            for (int i = 0; i < n; ++ i) {
                for (int j = i; j < n; ++ j) {
                    int sum = 0;
                    List <Integer> in = new ArrayList <Integer>();
                    List <Integer> out = new ArrayList <Integer>();
                    for (int k = 0; k < n; ++ k) {
                        if (i <= k && k <= j) {
                            sum += a[k];
                            in.add(a[k]);
                        } else {
                            out.add(a[k]);
                        }
                    }
                    Collections.sort(in);
                    Collections.sort(out);
                    result = Math.max(result, sum);
                    for (int k = 1; k <= m && k <= in.size() && k <= out.size(); ++ k) {
                        sum -= in.get(k - 1);
                        sum += out.get(out.size() - k);
                        result = Math.max(result, sum);
                    }
                }
            }
            writer.println(result);
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
