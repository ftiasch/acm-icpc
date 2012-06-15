import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.math.BigInteger;

class Solver {
    ArrayList <Integer> factorize(int n) {
        ArrayList <Integer> result = new ArrayList <Integer>();
        for (int i = 2; i * i <= n; ++ i) {
            if (n % i == 0) {
                int item = 1;
                while (n % i == 0) {
                    n /= i;
                    item *= i;
                }
                result.add(item);
            }
        }
        if (n > 1) {
            result.add(n);
        }
        return result;
    }

    BigInteger[] extendedGcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return new BigInteger[] {BigInteger.ONE, BigInteger.ZERO};
        } else {
            BigInteger[] ret = extendedGcd(b, a.remainder(b));
            return new BigInteger[] {ret[1], ret[0].subtract(a.divide(b).multiply(ret[1]))};
        }
    }

    BigInteger inverse(BigInteger a, BigInteger m) {
        BigInteger[] ret = extendedGcd(a, m);
        return ret[0].remainder(m).add(m).remainder(m);
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int b = in.nextInt();
        int n = in.nextInt();
        ArrayList <Integer> factors = factorize(b);
        int m = factors.size();
        BigInteger[] modulus = new BigInteger[m];
        for (int i = 0; i < m; ++ i) {
            modulus[i] = BigInteger.valueOf(factors.get(i)).pow(n);
        }
        BigInteger[] products = new BigInteger[m];
        BigInteger[] inverses = new BigInteger[m];
        for (int i = 0; i < m; ++ i) {
            products[i] = BigInteger.ONE;
            for (int j = 0; j < m; ++ j) {
                if (i != j) {
                    products[i] = products[i].multiply(modulus[j]);
                }
            }
            inverses[i] = inverse(products[i], modulus[i]);
        }
        BigInteger base = BigInteger.ONE;
        for (int i = 1; i < n; ++ i) {
            base = base.multiply(BigInteger.valueOf(b));
        }
        ArrayList <BigInteger> result = new ArrayList <BigInteger>();
        for (int mask = 0; mask < (1 << m); ++ mask) {
            BigInteger sum = BigInteger.ZERO;            
            for (int i = 0; i < m; ++ i) {
                if ((mask >> i & 1) == 1) {
                    sum = sum.add(products[i].multiply(inverses[i]));
                }
            }
            sum = sum.remainder(base.multiply(BigInteger.valueOf(b)));
            if (sum.compareTo(base) >= 0) {
                result.add(sum);
            }
        }
        if (n == 1) {
            out.println(result.size() + 1);
            out.println(0);
        } else {
            out.println(result.size());
        }
        for (int i = 0; i < result.size(); ++ i) {
            BigInteger a = result.get(i);
            ArrayList <Integer> buffer = new ArrayList <Integer>();
            while (a.compareTo(BigInteger.ZERO) > 0) {
                buffer.add((int)a.remainder(BigInteger.valueOf(b)).longValue());
                a = a.divide(BigInteger.valueOf(b));
            }
            for (int j = n - 1; j >= 0; -- j) {
                int d = buffer.get(j);
                out.printf("%c", d < 10? '0' + d: 'A' + d - 10);
            }
            out.printf("\n");
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
