// SGU 342 -- Reihenfolge
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
            BigInteger n = new BigInteger(reader.next());
            int b = reader.nextInt();
            if (b == 1) {
                writer.println(n);
            } else {
                ArrayList <Integer> digits = new ArrayList <Integer>();
                BigInteger bigB = BigInteger.valueOf(b);
                while (n.signum() > 0) {
                    BigInteger[] ret = n.divideAndRemainder(bigB);
                    digits.add((int)ret[1].longValue());
                    n = ret[0];
                }
                int[] cost = new int[] {0, Integer.MAX_VALUE};
                for (int digit : digits) {
                    int[] newCost = new int[2];
                    newCost[0] = newCost[1] = Integer.MAX_VALUE;
                    for (int i = 0; i < 2; ++ i) {
                        if (cost[i] == Integer.MAX_VALUE) {
                            continue;
                        }
                        int now = digit + i;
                        if (now == b) {
                            newCost[1] = Math.min(newCost[1], cost[i]);
                        } else {
                            newCost[0] = Math.min(newCost[0], cost[i] + now);
                            newCost[1] = Math.min(newCost[1], cost[i] + b - now);
                        }
                    }
                    cost = newCost;
                }
                writer.println(Math.min(cost[0], cost[1] + 1));
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
