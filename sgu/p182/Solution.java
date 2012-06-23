// SGU 182 -- Open the brackets
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Node {
    char token;
    Node left, right;

    Node(char token, Node left, Node right) {
        this.token = token;
        this.left = left;
        this.right = right;
    }

    static int getPriority(char operator) {
        if (operator == '!') {
            return 0;
        }
        if (operator == '&') {
            return 1;
        }
        if (operator == '|' || operator == '=' || operator == '>' || operator == '#') {
            return 2;
        }
        return -1;
    }

    static Node parse(String expression) {
        if (expression.length() == 1) {
            return new Node(expression.charAt(0), null, null);
        }
        int bracketCount = 0;
        int maximumPriority = -1;
        int operatorIndex = -1;
        for (int i = expression.length() - 1; i >= 0; -- i) {
            char token = expression.charAt(i);
            if (token == ')') {
                bracketCount ++;
            }
            if (token == '(') {
                bracketCount --;
            }
            if (bracketCount == 0 && getPriority(token) > maximumPriority) {
                operatorIndex = i;
                maximumPriority = getPriority(token);
            }
        }
        if (operatorIndex == -1) {
            return parse(expression.substring(1, expression.length() - 1));
        }
        char operator = expression.charAt(operatorIndex);
        if (operator == '!') {
            return new Node('!', null, parse(expression.substring(1)));
        }
        return new Node(operator, parse(expression.substring(0, operatorIndex)), parse(expression.substring(operatorIndex + 1)));
    }

    boolean evaluate(int mask) {
        if (getPriority(token) == -1) {
            return (mask >> (token - 'a') & 1) == 1;
        }
        if (token == '!') {
            return !right.evaluate(mask);
        }
        if (token == '&') {
            return left.evaluate(mask) && right.evaluate(mask);
        }
        if (token == '|') {
            return left.evaluate(mask) || right.evaluate(mask);
        }
        if (token == '=') {
            return left.evaluate(mask) == right.evaluate(mask);
        }
        if (token == '>') {
            return !left.evaluate(mask) || right.evaluate(mask);
        }
        return left.evaluate(mask) ^ right.evaluate(mask);
    }
}

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        String expression = in.nextToken().replace("||", "|").replace("<=>", "=").replace("=>", ">");
        Node root = Node.parse(expression);
        boolean first = true;
        for (int mask = 0; mask < (1 << 10); ++ mask) {
            if (root.evaluate(mask)) {
                if (first) {
                    first = false;
                } else {
                    out.print("||");
                }
                for (int i = 0; i < 10; ++ i) {
                    if (i > 0) {
                        out.print("&");
                    }
                    if ((mask >> i & 1) == 0) {
                        out.print("!");
                    }
                    out.print((char)('a' + i));
                }
            }
        }
        if (first) {
            out.println("a&!a");
        } else {
            out.println();
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
