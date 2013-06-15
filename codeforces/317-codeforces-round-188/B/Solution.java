// Codeforces Round #188
// Problem B -- Ants
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static int[] DELTA_X = {-1, 0, 0, 1};
    final static int[] DELTA_Y = {0, -1, 1, 0};

    final static int N = 75;

    public void run() {
        try {
            int[][] count = new int[N << 1][N << 1];
            count[N][N] = reader.nextInt();
            boolean changed = true;
            while (changed) {
                changed = false;
                for (int i = 0; i < N << 1; ++ i) {
                    for (int j = 0; j < N << 1; ++ j) {
                        if (count[i][j] >= 4) {
                            changed = true;
                            int delta = count[i][j] / 4;
                            for (int k = 0; k < 4; ++ k) {
                                count[i + DELTA_X[k]][j + DELTA_Y[k]] += delta;
                            }
                            count[i][j] %= 4;
                        }
                    }
                }
            }
            int t = reader.nextInt();
            while (t > 0) {
                t --;
                int x = N + reader.nextInt();
                int y = N + reader.nextInt();
                if (0 <= x && x < N << 1 && 0 <= y && y < N << 1) {
                    writer.println(count[x][y]);
                } else {
                    writer.println(0);
                }
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
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
