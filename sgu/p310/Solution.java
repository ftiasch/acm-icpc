// SGU 310 -- Hippopotamus
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int k = reader.nextInt();
            int[] one = new int[1 << m - 1];
            one[0] = 0;
            for (int mask = 1; mask < 1 << m - 1; ++ mask) {
                one[mask] = one[mask >> 1] + (mask & 1);
            }
            long[] ways = new long[1 << m - 1];
            for (int mask = 0; mask < 1 << m - 1; ++ mask) {
                ways[mask] = 1;
            }
            int all = (1 << m - 1) - 1;
            for (int i = m; i <= n; ++ i) {
                long[] newWays = new long[1 << m - 1];
                for (int mask = 0; mask < 1 << m - 1; ++ mask) {
                    if (one[mask] >= k) {
                        newWays[mask << 1 & all] += ways[mask];
                    }
                    if (one[mask] + 1 >= k) {
                        newWays[(mask << 1 | 1) & all] += ways[mask];
                    }
                }
                ways = newWays;
            }
            long answer = 0;
            for (int mask = 0; mask < 1 << m - 1; ++ mask) {
                answer += ways[mask];
            }
            writer.println(answer);
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
