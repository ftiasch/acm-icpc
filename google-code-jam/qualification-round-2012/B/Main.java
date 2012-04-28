import java.io.*;
import java.util.*;

public class Main implements Runnable {
    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintStream writer;

    Main() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
        writer = new PrintStream(new BufferedOutputStream(System.out));
    }

    public void run() {
        try {
            int testCount = nextInt();
            for (int test = 1; test <= testCount; ++ test) {
                int n = nextInt();
                int m = nextInt();
                int p = nextInt();
                boolean v[][][] = new boolean[31][2][2];
                for (int a = 0; a <= 10; ++ a) {
                    for (int b = a; b <= 10; ++ b) {
                        for (int c = 0; c <= 10; ++ c) {
                            if (c - a <= 2) {
                                v[a + b + c][c - a >= 2? 1: 0][c >= p? 1: 0] = true;
                            }
                        }
                    }
                }
                int dp[] = new int[m + 1];
                for (int j = 1; j <= m; ++ j) {
                    dp[j] = Integer.MIN_VALUE;
                }
                for (int i = 0; i < n; ++ i) {
                    int ti = nextInt();
                    int dp2[] = new int[m + 1];
                    for (int j = 0; j <= m; ++ j) {
                        dp2[j] = Integer.MIN_VALUE;
                        for (int x = 0; x < 2; ++ x) {
                            for (int y = 0; y < 2; ++ y) {
                                if (v[ti][x][y] && j >= x) {
                                    dp2[j] = Math.max(dp2[j], dp[j - x] + y);
                                }
                            }
                        }
                    }
                    dp = dp2;
                }
                System.out.printf("Case #%d: %d\n", test, dp[m]);
            }
        } catch (Exception e) {
        }
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

    public static void main(String args[]) {
        new Thread(new Main()).run();
    }
}
