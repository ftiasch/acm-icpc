import java.io.*;
import java.math.*;
import java.util.*;

public class Main implements Runnable {
    final static BigInteger TWO = BigInteger.valueOf(2);
    final static BigInteger HUNDRED = BigInteger.valueOf(100);

    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintStream writer;

    Main() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
        writer = new PrintStream(new BufferedOutputStream(System.out));
    }

    class Range {
        BigInteger value[];

        Range(BigInteger min, BigInteger max) {
            value = new BigInteger[] {min, max};
        }

        Range add(Range other) {
            return new Range(value[0].add(other.value[0]), value[1].add(other.value[1]));
        }

        Range subtract(Range other) {
            return new Range(value[0].subtract(other.value[1]), value[1].subtract(other.value[0]));
        }

        Range multiply(Range other) {
            BigInteger result[] = new BigInteger[4];
            for (int i = 0; i < 2; ++ i) {
                for (int j = 0; j < 2; ++ j) {
                    result[(i << 1) | j] = value[i].multiply(other.value[j]);
                }
            }
            Arrays.sort(result);
            return new Range(result[0], result[3]);
        }

        BigInteger divideTwo(BigInteger x) {
            if (x.signum() < 0) {
                return x.subtract(BigInteger.ONE).divide(TWO);
            }
            return x.divide(TWO);
        }

        Range divideTwo() {
            BigInteger result[] = new BigInteger[]{divideTwo(value[0]), divideTwo(value[1])};
            Arrays.sort(result);
            return new Range(result[0], result[1]);
        }

        Range min(Range other) {
            return new Range(value[0].min(other.value[0]), value[1].min(other.value[1]));
        }

        Range max(Range other) {
            return new Range(value[0].max(other.value[0]), value[1].max(other.value[1]));
        }
    }

    int match[];

    Range evaluate(String expression, int begin, int end) {
        if (expression.charAt(begin) == '(' && expression.charAt(end - 1) == ')' && begin == match[end - 1]) {
            return evaluate(expression, begin + 1, end - 1);
        }
        int index = -1;
        for (int i = end - 1; i >= begin; -- i) {
            if (expression.charAt(i) == ')') {
                i = match[i];
            } else if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                index = i;
                break;
            }
        }
        if (index == -1) {
            for (int i = end - 1; i >= begin; -- i) {
                if (expression.charAt(i) == ')') {
                    i = match[i];
                } else if (expression.charAt(i) == '*') {
                    index = i;
                    break;
                }
            }
        }
        if (index == -1) {
            if (expression.charAt(begin) == 'd') {
                return evaluate(expression, begin + 5, end - 1).divideTwo();
            } else if (expression.charAt(begin) == 'm') {
                int comma = -1;
                for (int i = end - 2; i >= begin + 4; -- i) {
                    if (expression.charAt(i) == ')') {
                        i = match[i];
                    } else if (expression.charAt(i) == ',') {
                        comma = i;
                    }
                }
                Range lhs = evaluate(expression, begin + 4, comma);
                Range rhs = evaluate(expression, comma + 1, end - 1);
                if (expression.charAt(begin + 1) == 'i') {
                    return lhs.min(rhs);
                }
                return lhs.max(rhs);
            } else if (expression.charAt(begin) == '?') {
                return new Range(BigInteger.ONE, HUNDRED);
            }
            BigInteger x = BigInteger.valueOf(Integer.parseInt(expression.substring(begin, end)));
            return new Range(x, x);
        }
        Range lhs = evaluate(expression, begin, index);
        Range rhs = evaluate(expression, index + 1, end);
        if (expression.charAt(index) == '+') {
            return lhs.add(rhs);
        } else if (expression.charAt(index) == '-') {
            return lhs.subtract(rhs);
        }
        return lhs.multiply(rhs);
    }

    public void run() {
        try {
            String expression = nextToken();
            int length = expression.length();
            match = new int[length];
            Arrays.fill(match, -1);
            Stack <Integer> stack = new Stack <Integer>();
            for (int i = 0; i < length; ++ i) {
                if (expression.charAt(i) == '(') {
                    stack.push(i);
                } else if (expression.charAt(i) == ')') {
                    match[i] = stack.pop();
                }
            }
            //debug(match);
            Range result = evaluate(expression, 0, length);
            if (result.value[0].equals(result.value[1])) {
                System.out.println(result.value[0]);
            } else {
                System.out.println("?");
            }
        } catch (Exception e) {
        }
    }

    public static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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

    public static void main(String args[]) {
        new Thread(new Main()).run();
    }
}
