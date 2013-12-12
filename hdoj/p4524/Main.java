import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    BigDecimal solve(int n) {
        int totalNumber = (n + 1) * n / 2;
        long[][][] ways = new long[n + 2][2][totalNumber + 1];
        ways[0][1][0] = 1;
        for (int length = 0; length <= n; ++ length) {
            for (int parity = 0; parity < 2; ++ parity) {
                for (int number = 0; number <= totalNumber; ++ number) {
                    if (ways[length][parity][number] > 0) {
                        for (int next = 1; length + next <= n + 1; ++ next) {
                            ways[length + next][parity ^ 1][number + next * (next - 1) / 2] += ways[length][parity][number];
                        }
                    }
                }
            }
        }
        assert ways[n + 1][n & 1][0] > 0;
        ways[n + 1][n & 1][0] --;
        BigDecimal result = BigDecimal.ONE;
        for (int parity = 0; parity < 2; ++ parity) {
            for (int number = 0; number < totalNumber; ++ number) {
                if (ways[n + 1][parity][number] > 0) {
                    BigDecimal probability = BigDecimal.valueOf(number).divide(BigDecimal.valueOf(totalNumber), 30, RoundingMode.HALF_UP);
                    BigDecimal effort = BigDecimal.ONE.divide(BigDecimal.ONE.subtract(probability), 30, RoundingMode.HALF_UP).subtract(BigDecimal.ONE);
                    if (parity == 0) {
                        effort = effort.negate();
                    }
                    result = result.add(effort.multiply(BigDecimal.valueOf(ways[n + 1][parity][number])));
                }
            }
        }
        return result.setScale(15, RoundingMode.HALF_UP);
    }

    public void run() {
        try {
            int testCount = reader.nextInt();
            while (testCount > 0) {
                testCount --;
                int n = reader.nextInt();
                writer.println(solve(n));
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Main() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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
