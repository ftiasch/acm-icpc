// SGU 193 -- Chinese Girls' Amusement
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;

class Solver {
    final static BigInteger TWO = BigInteger.valueOf(2);
    void run(InputReader in, PrintWriter out) throws IOException {
        BigInteger n = new BigInteger(in.nextToken());
        BigInteger r = n.divide(TWO);
        if (n.remainder(TWO).equals(BigInteger.ONE)) {
            out.println(r);
        } else {
            r = r.subtract(BigInteger.ONE);
            if (r.remainder(TWO).equals(BigInteger.ZERO)) {
                r = r.subtract(BigInteger.ONE);
            }
            out.println(r);
        }
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
}
