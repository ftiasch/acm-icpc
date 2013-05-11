// Codeforces Round #176
// Problem C -- Main Sequence
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    public void run() {
        try {
            int n = reader.nextInt();
            int[] type = new int[n];
            for (int i = 0; i < n; ++ i) {
                type[i] = reader.nextInt();
            }
            boolean[] negative = new boolean[n];
            int t = reader.nextInt();
            for (int i = 0; i < t; ++ i) {
                negative[reader.nextInt() - 1] = true;
            }
            Stack <Integer> stack = new Stack <Integer>();
            for (int i = n - 1; i >= 0; -- i) {
                if (negative[i]) {
                    stack.push(type[i]);
                    type[i] *= -1;
                } else if (!stack.isEmpty() && type[i] == stack.peek()) {
                    stack.pop();
                } else {
                    stack.push(type[i]);
                    type[i] *= -1;
                }
            }
            if (stack.isEmpty()) {
                writer.println("YES");
                for (int i = 0; i < n; ++ i) {
                    writer.print(type[i] + " ");
                }
                writer.println();
            } else {
                writer.println("NO");
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
