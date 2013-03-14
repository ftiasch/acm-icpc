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

    int getKey(int n) {
        int total = 0;
        int last = 0;
        for (int i = 2; i * i <= n; ++ i) {
            if (n % i == 0) {
                total += i;
                last = i;
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n > 1) {
            total += n;
            last = n;
        }
        return last * 2 - total;
    }

    public void run() {
        try {
            int a = reader.nextInt();
            int b = reader.nextInt();
            writer.println(getKey(a) > getKey(b) ? 'a' : 'b');
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
