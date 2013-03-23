// SGU 269 -- Rooks
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

    int[] a;

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            Arrays.sort(a);
            BigInteger[] ways = new BigInteger[m + 1];
            Arrays.fill(ways, BigInteger.ZERO);
            ways[0] = BigInteger.ONE;
            for (int i = 0; i < n; ++ i) {
                BigInteger[] newWays = new BigInteger[m + 1];
                Arrays.fill(newWays, BigInteger.ZERO);
                for (int j = 0; j <= m; ++ j) {
                    newWays[j] = ways[j];
                    if (j > 0) {
                        newWays[j] = newWays[j].add(ways[j - 1].multiply(BigInteger.valueOf(a[i] - (j - 1))));
                    }
                }
                ways = newWays;
            }
            writer.println(ways[m]);
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
