// SGU 146 -- The Runner
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        BigInteger length = BigInteger.valueOf((long)(in.nextDouble() * 10000));
        int n = in.nextInt();
        BigInteger total = BigInteger.ZERO;
        for (int i = 0; i < n; ++ i) {
            long a = in.nextInt();
            long b = in.nextInt();
            total = total.add(BigInteger.valueOf(a * b * 10000));
        }
        total = total.remainder(length);
        long result = total.longValue();
        result = Math.min(result, length.longValue() - result);
        out.printf("%d.%04d\n", result / 10000, result % 10000);
        out.flush();
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

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }
}
