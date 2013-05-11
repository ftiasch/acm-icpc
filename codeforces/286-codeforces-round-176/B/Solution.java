// Codeforces Round #176
// Problem B -- Shifting
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    public void run() {
        try {
            int n = reader.nextInt();
            int[] p = new int[n << 1];
            for (int i = 0; i < n; ++ i) {
                p[i] = i;
            }
            int offset = 0;
            for (int k = 2; k <= n; ++ k) {
                int i = offset + k * ((n - 1) / k);
                p[offset + n] = p[i];
                for (; i > offset; i -= k) {
                    p[i] = p[i - k];
                }
                offset ++;
            }
            for (int i = 0; i < n; ++ i) {
                writer.print((p[offset + i] + 1) + " ");
            }
            writer.println();
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
