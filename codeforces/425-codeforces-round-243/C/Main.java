import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    static int N = 100000;

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int s = reader.nextInt();
            int e = reader.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt() - 1;
            }
            int[] b = new int[m];
            for (int i = 0; i < m; ++ i) {
                b[i] = reader.nextInt() - 1;
            }
            int[] counter = new int[N];
            for (int i = 0; i < m; ++ i) {
                counter[b[i]] ++;
            }
            int[][] indices = new int[N][];
            for (int i = 0; i < N; ++ i) {
                indices[i] = new int[counter[i] + 1];
                counter[i] = 1;
                indices[i][0] = m;
            }
            for (int i = m - 1; i >= 0; -- i) {
                indices[b[i]][counter[b[i]] ++] = i;
            }
            int result = 0;
            int t = s / e;
            int[] minimum = new int[n];
            Arrays.fill(minimum, m);
            for (int k = 1; k <= t; ++ k) {
                Arrays.fill(counter, 0);
                int start = k > 1 ? m : 0;
                for (int i = 0; i < n; ++ i) {
                    int[] ids = indices[a[i]];
                    while (counter[a[i]] + 1 < ids.length && ids[counter[a[i]] + 1] >= start) {
                        counter[a[i]] ++;
                    }
                    int backup = minimum[i];
                    minimum[i] = ids[counter[a[i]]];
                    if (minimum[i] < m) {
                        int left = i + minimum[i] + 2;
                        if (k * e + left <= s) {
                            result = Math.max(result, k);
                        }
                    }
                    start = Math.min(start, backup + 1);
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
