import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int m = reader.nextInt();
            int n = reader.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt() - 1;
            }
            int[] next = new int[n];
            int[] hash = new int[m];
            Arrays.fill(hash, n);
            for (int i = n - 1; i >= 0; -- i) {
                next[i] = hash[a[i]];
                hash[a[i]] = i;
            }
            long sum = 0;
            for (int i = 1; i < n; ++ i) {
                sum += Math.abs(a[i] - a[i - 1]);
            }
            boolean[] visited = new boolean[n];
            long result = Long.MAX_VALUE;
            for (int i = 0; i < n; ++ i) {
                if (!visited[i]) {
                    List <Integer> numbers = new ArrayList <Integer>();
                    long newSum = sum;
                    for (int j = i; j < n; j = next[j]) {
                        visited[j] = true;
                        if (j - 1 >= 0 && a[j - 1] != a[j]) {
                            numbers.add(a[j - 1]);
                            newSum -= Math.abs(a[j] - a[j - 1]);
                        }
                        if (j + 1 < n && a[j + 1] != a[j]) {
                            numbers.add(a[j + 1]);
                            newSum -= Math.abs(a[j] - a[j + 1]);
                        }
                    }
                    if (!numbers.isEmpty()) {
                        Collections.sort(numbers);
                        int middle = numbers.get(numbers.size() >> 1);
                        for (int number : numbers) {
                            newSum += Math.abs(middle - number);
                        }
                    }
                    result = Math.min(result, newSum);
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
