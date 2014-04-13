import java.io.*;
import java.math.*;
import java.util.*;

public class A {
    public void run() {
        try {
            int n = reader.nextInt();
            int min = -2000000000;
            int max = 2000000000;
            for (int i = 0; i < n; ++ i) {
                String operator = reader.next();
                int value = reader.nextInt();
                String answer = reader.next();
                if (answer.equals("N")) {
                    if (operator.equals("<")) {
                        operator = ">=";
                    } else if (operator.equals(">")) {
                        operator = "<=";
                    } else if (operator.equals(">=")) {
                        operator = "<";
                    } else {
                        operator = ">";
                    }
                }
                if (operator.equals("<")) {
                    max = Math.min(max, value - 1);
                } else if (operator.equals(">")) {
                    min = Math.max(min, value + 1);
                } else if (operator.equals(">=")) {
                    min = Math.max(min, value);
                } else {
                    max = Math.min(max, value);
                }
            }
            if (min <= max) {
                writer.println(min);
            } else {
                writer.println("Impossible");
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    A() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new A().run();
    }

    private void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    private InputReader reader;
    private PrintWriter writer;
}

class InputReader {
    InputReader(InputStream in) {
        reader    = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
    }

    public String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    public Integer nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public Long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    private BufferedReader  reader;
    private StringTokenizer tokenizer;
}
