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

    BigInteger[] solve(int n, int m, int type) {
        ArrayList <Integer> sizes = new ArrayList <Integer>();
        for (int sum = 0; sum <= n - 1 << 1; ++ sum) {
            if ((sum & 1) == type) {
                // 0 <= sum - i <= n - 1
                int ret = Math.min(n - 1, sum) - Math.max(0, sum - n + 1) + 1;
                if (ret > 0) {
                    sizes.add(ret);
                }
            }
        }
        Collections.sort(sizes);
        BigInteger[] ways = new BigInteger[m + 1];
        Arrays.fill(ways, BigInteger.ZERO);
        ways[0] = BigInteger.ONE;
        for (int size : sizes) {
            BigInteger[] newWays = new BigInteger[m + 1];
            Arrays.fill(newWays, BigInteger.ZERO);
            for (int i = 0; i <= m; ++ i) {
                if (ways[i].equals(BigInteger.ZERO)) {
                    continue;
                }
                newWays[i] = newWays[i].add(ways[i]);
                if (i + 1 <= m) {
                    newWays[i + 1] = newWays[i + 1].add(ways[i].multiply(BigInteger.valueOf(size - i)));
                }
            }
            ways = newWays;
        }
        return ways;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            BigInteger[][] ways = new BigInteger[2][];
            for (int i = 0; i < 2; ++ i) {
                ways[i] = solve(n, m, i);
            }
            BigInteger answer = BigInteger.ZERO;
            for (int i = 0; i <= m; ++ i) {
                answer = answer.add(ways[0][i].multiply(ways[1][m - i]));
            }
            writer.println(answer);
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
