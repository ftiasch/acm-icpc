// SGU 407 -- Number of Paths in the Empire
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
            if (m == 0) {
                writer.println(1);
            } else if (m == 1) {
                writer.println(0);
            } else {
                BigInteger p = BigInteger.ONE;
                BigInteger q = BigInteger.ZERO;
                BigInteger sum = BigInteger.ZERO;
                for (int i = 2; i <= m; ++ i) {
                    sum = sum.add(p);
                    p = q;
                    q = sum.multiply(BigInteger.valueOf(n));
                    sum = sum.shiftLeft(1);
                }
                writer.println(q);
            }
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
