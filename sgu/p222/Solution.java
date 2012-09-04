// SGU 222 -- Little Rooks
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;

class Solver {
    BigInteger choose(int n, int k) {
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < k; ++ i) {
            result = result.multiply(BigInteger.valueOf(n - i));
            result = result.divide(BigInteger.valueOf(i + 1));
        }
        return result;
    }

    BigInteger square(BigInteger x) {
        return x.multiply(x);
    }

    BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; ++ i) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    BigInteger solve(int n, int k) {
        if (n < k) {
            return BigInteger.ZERO;
        }
        return square(choose(n, k)).multiply(factorial(k));
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();
        out.println(solve(n, k));
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
