import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.math.BigInteger;

class Solver {
    BigInteger[] power;

    BigInteger getPower(int n) {
        if (power[n].equals(BigInteger.ZERO)) {
            power[n] = (n & 1) == 1
                    ? getPower(n - 1).multiply(power[1])
                    : getPower(n >> 1).multiply(getPower(n >> 1));
        }
        return power[n];
    }

    int gcd(int a, int b) {
        return b == 0? a: gcd(b, a % b);
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        power = new BigInteger[n + 1];
        Arrays.fill(power, BigInteger.ZERO);
        power[0] = BigInteger.ONE;
        power[1] = BigInteger.valueOf(2);
        int[] count = new int[n + 1];
        for (int i = 0; i < n; ++ i) {
            count[gcd(i, n)] ++;
        }
        BigInteger result = BigInteger.ZERO;
        for (int i = 1; i <= n; ++ i) {
            if (count[i] > 0) {
                result = result.add(getPower(i).multiply(BigInteger.valueOf(count[i])));
            }
        }
        out.println(result.divide(BigInteger.valueOf(n)));
        out.close();
    }
}

public class Solution {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
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
