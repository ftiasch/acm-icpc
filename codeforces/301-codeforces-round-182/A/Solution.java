// Codeforces Round #182
// Problem A -- Yaroslav and Sequence
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
            int sum = 0;
            int min = Integer.MAX_VALUE;
            int negative = 0;
            for (int i = 0; i < (n << 1) - 1; ++ i) {
                int a = reader.nextInt();
                if (a < 0) {
                    negative ++;
                }
                a = Math.abs(a);
                sum += a;
                min = Math.min(min, a);
            }
            if (negative % 2 == 1 && n % 2 == 0) {
                sum -= 2 * min;
            }
            writer.println(sum);
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
