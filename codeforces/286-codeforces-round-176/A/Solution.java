// Codeforces Round #176
// Problem A -- Lucky Permutation
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    public void run() {
        try {
            int n = reader.nextInt();
            if (n % 4 >= 2) {
                writer.println(-1);
            } else {
                int[] p = new int[n];
                for (int i = 0; i < n; ++ i) {
                    p[i] = i;
                }
                for (int i = 0; i + 1 < n - i - 2; i += 2) {
                    p[i] = n - i - 2;
                    p[n - i - 2] = p[n - i - 1];
                    p[n - i - 1] = p[i + 1];
                    p[i + 1] = i;
                }
                for (int i = 0; i < n; ++ i) {
                    writer.print(String.format("%d%c", p[i] + 1, i == n - 1 ? '\n' : ' '));
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
