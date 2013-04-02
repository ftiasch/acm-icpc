// SGU 247 -- Difficult Choice
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

    final static int N = 1000;

    public void run() {
        try {
            BigInteger[] binom = new BigInteger[N + 1];
            binom[1] = BigInteger.valueOf(2);
            for (int i = 2; i <= N; ++ i) {
                binom[i] = binom[i - 1].multiply(BigInteger.valueOf(2 * i)).multiply(BigInteger.valueOf(2 * i - 1)).divide(BigInteger.valueOf(i)).divide(BigInteger.valueOf(i));
            }
            int testCount = reader.nextInt();
            while (testCount > 0) {
                testCount --;
                int p = reader.nextInt();
                if (p == 2) {
                    writer.println(2);
                } else {
                    writer.println(binom[p].subtract(BigInteger.valueOf(2)).divide(BigInteger.valueOf(p)).add(BigInteger.valueOf(2)));
                }
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
