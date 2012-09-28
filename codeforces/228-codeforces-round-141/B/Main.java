// Codeforces Round #141
// Problem B -- Two Tables
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class Solver {
    final static int MAX = 100;

    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; ++ i) {
            a[i] = in.nextToken();
        }
        n = in.nextInt();
        m = in.nextInt();
        String[] b = new String[n];
        for (int i = 0; i < n; ++ i) {
            b[i] = in.nextToken();
        }
        int best = Integer.MIN_VALUE;
        int bestX = -1;
        int bestY = -1;
        for (int x = -MAX; x <= MAX; ++ x) {
            for (int y = -MAX; y <= MAX; ++ y) {
                int result = 0;
                for (int i = 0; i < a.length; ++ i) {
                    for (int j = 0; j < a[i].length(); ++ j) {
                        if (0 <= i + x && i + x < b.length && 
                                0 <= j + y && j + y < b[i + x].length()
                                && a[i].charAt(j) == '1' && b[i + x].charAt(j + y) == '1') {
                            result ++;
                        }
                    }
                }
                if (result > best) {
                    best = result;
                    bestX = x;
                    bestY = y;
                }
            }
        }
        out.println(String.format("%d %d\n", bestX, bestY));
        out.close();
    }
}

class InputReader {
    BufferedReader reader;
    StringTokenizer tokenizer;

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
    }

    String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
}
