// 356. Extrasensory Perception
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.util.Arrays;

public class Solution {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        int k = in.nextInt();
        int n = in.nextInt();
        BigInteger[][] binom = new BigInteger[n + 1][n + 1];
        for (int i = 0; i <= n; ++ i) {
            Arrays.fill(binom[i], BigInteger.ZERO);
            binom[i][0] = BigInteger.ONE;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = binom[i - 1][j].add(binom[i - 1][j - 1]);
            }
        }
        BigInteger[] derangement = new BigInteger[Math.max(n + 1, 3)];
        derangement[1] = BigInteger.ZERO;
        derangement[0] = derangement[2] = BigInteger.ONE;
        for (int i = 3; i <= n; ++ i) {
            derangement[i] = BigInteger.valueOf(i - 1).multiply(derangement[i - 1].add(derangement[i - 2]));
        }
        BigInteger ways = derangement[n - k].multiply(binom[n][k]);
        BigInteger total = BigInteger.ONE;
        for (int i = 1; i <= n; ++ i) {
            total = total.multiply(BigInteger.valueOf(i));
        }
        if (true) {
            BigInteger d = ways.gcd(total);
            ways = ways.divide(d);
            total = total.divide(d);
        }
        if (ways.equals(BigInteger.ZERO)) {
            out.println(ways);
        } else {
            out.println(String.format("%d/%d", ways, total));
        }
        out.close();
    }
}

class InputReader {
    BufferedReader reader;
    StringTokenizer tokenizer;

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
    }

    String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
}
