// Codeforces Beta Round #18
// Problem D -- Seller Bob
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static int M = 2001;

    public void run() {
        try {
            int n = reader.nextInt();
            int[] hash = new int[M];
            Arrays.fill(hash, -1);
            int[][] order = new int[M][];
            for (int i = 0; i < n; ++ i) {
                String type = reader.next();
                int x = reader.nextInt();
                if (type.equals("win")) {
                    hash[x] = i;
                } else if (hash[x] != -1) {
                    order[x] = new int[]{hash[x], i};
                }
            }
            boolean[] used = new boolean[n];
            boolean[] pick = new boolean[M];
            for (int i = M - 1; i >= 0; -- i) {
                if (order[i] == null) {
                    continue;
                }
                boolean valid = true;
                for (int x = order[i][0]; x <= order[i][1]; ++ x) {
                    valid &= !used[x];
                }
                if (valid) {
                    pick[i] = true;
                    for (int x = order[i][0]; x <= order[i][1]; ++ x) {
                        used[x] = true;
                    }
                }
            }
            BigInteger answer = BigInteger.ZERO;
            BigInteger power = BigInteger.ONE;
            for (int i = 0; i < M; ++ i) {
                if (pick[i]) {
                    answer = answer.add(power);
                }
                power = power.multiply(BigInteger.valueOf(2));
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
