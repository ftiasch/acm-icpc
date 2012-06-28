import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;

class Matrix {
    final static BigInteger TWO = BigInteger.valueOf(2);

    static int size;
    static int mod;
    int[][] matrix;

    Matrix(boolean identity) {
        matrix = new int[size][size];
        if (identity) {
            for (int i = 0; i < size; ++ i) {
                matrix[i][i] = 1 % mod;
            }
        }
    }

    int get(int i, int j) {
        return matrix[i][j];
    }

    void set(int i, int j, int v) {
        matrix[i][j] = v;
    }

    Matrix multiply(Matrix other) {
        Matrix result = new Matrix(false);
        for (int i = 0; i < size; ++ i) {
            for (int j = 0; j < size; ++ j) {
                long tmp = 0;
                for (int k = 0; k < size; ++ k) {
                    tmp += get(i, k) * other.get(k, j);
                }
                result.set(i, j, (int)(tmp % mod));
            }
        }
        return result;
    }

    Matrix pow(BigInteger n) {
        Matrix result = new Matrix(true);
        Matrix base = this;
        while (!n.equals(BigInteger.ZERO)) {
            if (n.remainder(TWO).equals(BigInteger.ONE)) {
                result = result.multiply(base);
            }
            n = n.divide(TWO);
            base = base.multiply(base);
        }
        return result;
    }
}

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        BigInteger n = new BigInteger(in.nextToken());
        int m = in.nextInt();
        Matrix.size = 1 << m;
        int p = Matrix.mod = in.nextInt();
        Matrix transform = new Matrix(false);
        for (int i = 0; i < (1 << m); ++ i) {
            for (int j = 0; j < (1 << m); ++ j) {
                int k = i & j;
                if ((k & (k >> 1)) > 0) {
                    continue;
                }
                k = (~i & ~j) & ((1 << m) - 1);
                if ((k & (k >> 1)) == 0) {
                    transform.set(i, j, 1);
                }
            }
        }
        Matrix init = new Matrix(false);
        for (int i = 0; i < (1 << m); ++ i) {
            init.set(0, i, 1);
        }
        init = init.multiply(transform.pow(n.subtract(BigInteger.ONE)));
        int result = 0;
        for (int i = 0; i < (1 << m); ++ i) {
            result = (result + init.get(0, i)) % p;
        }
        out.println(result);
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
