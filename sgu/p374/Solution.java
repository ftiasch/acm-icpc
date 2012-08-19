// SGU 374 -- Save Vasya
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.math.BigInteger;

class Solver {
    BigInteger[] multiply(BigInteger[] a, BigInteger[] b) {
        BigInteger[] c = new BigInteger[a.length + b.length - 1];
        Arrays.fill(c, BigInteger.ZERO);
        for (int i = 0; i < a.length; ++ i) {
            for (int j = 0; j < b.length; ++ j) {
                c[i + j] = c[i + j].add(a[i].multiply(b[j]));
            }
        }
        return c;
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        BigInteger[][] cofficient = new BigInteger[k + 1][];
        cofficient[1] = new BigInteger[] {BigInteger.valueOf(b), 
                                          BigInteger.valueOf(a)};
        for (int i = 2; i <= k; ++ i) {
            cofficient[i] = multiply(cofficient[i - 1], cofficient[1]);
        }
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i <= k; ++ i) {
            result = result.add(cofficient[k][i]);
        }
        out.println(result);
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
