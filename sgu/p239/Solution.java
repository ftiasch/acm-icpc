// SGU 239 -- Minesweeper
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    public void run() {
        try {
            int n = reader.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            BigInteger[] ways = new BigInteger[4];
            ways[0] = ways[1] = BigInteger.ONE;
            ways[2] = ways[3] = BigInteger.ZERO;
            for (int i = 0; i < n; ++ i) {
                BigInteger[] newWays = new BigInteger[4];
                Arrays.fill(newWays, BigInteger.ZERO);
                for (int mask = 0; mask < 4; ++ mask) {
                    for (int now = 0; now < 2; ++ now) {
                        int newMask = mask << 1 | now;
                        if (Integer.bitCount(newMask) == a[i]) {
                            newWays[newMask & 3] = newWays[newMask & 3].add(ways[mask]);
                        }
                    }
                }
                ways = newWays;
            }
            BigInteger answer = ways[0].add(ways[2]);
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

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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
