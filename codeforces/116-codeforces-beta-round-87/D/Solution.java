// Codeforces Beta Round #87
// Problem D -- Lawnmower
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            String[] map = new String[n];
            for (int i = 0; i < n; ++ i) {
                map[i] = reader.next();
            }
            int[] maximum = new int[n + 1];
            int[] minimum = new int[n + 1];
            boolean[] has = new boolean[n + 1];
            has[n] = false;
            for (int i = n; i >= 0; -- i) {
                maximum[i] = Integer.MIN_VALUE;
                minimum[i] = Integer.MAX_VALUE;
                if (i < n) {
                    has[i] = has[i + 1];
                    for (int j = 0; j < m; ++ j) {
                        if (map[i].charAt(j) == 'W') {
                            maximum[i] = Math.max(maximum[i], j);
                            minimum[i] = Math.min(minimum[i], j);
                            has[i] = true;
                        }
                    }
                }
            }
            int answer = Integer.MAX_VALUE;
            for (int i = 0, now = 0, total = -1; i < n; ++ i) {
                total ++;
                if ((i & 1) == 0) {
                    int max = Math.max(maximum[i], maximum[i + 1]);
                    if (now < max) {
                        total += max - now;
                        now = max;
                    }
                } else {
                    int min = Math.min(minimum[i], minimum[i + 1]);
                    if (now > min) {
                        total += now - min;
                        now = min;
                    }
                }
                if (!has[i + 1]) {
                    answer = Math.min(answer, total);
                }
            }
            writer.println(answer);
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
