import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

abstract class Type {
    abstract void assign(String value);
    abstract String value();
}

class Variable extends Type {
    private static Map <String, String> variables = new HashMap <String, String>();

    String identifier;

    Variable(String identifier) {
        if (identifier.startsWith("$")) {
            this.identifier = identifier;
        } else {
            this.identifier = identifier.substring(1, identifier.length() - 1);
        }
    }

    void assign(String value) {
        if (!identifier.startsWith("$")) {
            throw new RuntimeException("Left value cannot be assigned.");
        }
        variables.put(identifier, value);
    }

    String value() {
        if (identifier.startsWith("$")) {
            if (!variables.containsKey(identifier)) {
                variables.put(identifier, new String());
            }
            return variables.get(identifier);
        }
        return identifier;
    }

    int length() {
        return this.value().length();
    }
}

class SubString extends Type {
    Variable variable;
    int begin, end;

    SubString(Variable variable, int begin, int end) {
        this.variable = variable;
        this.begin = begin;
        this.end = end;
    }

    String value() {
        return variable.value().substring(begin, end);
    }

    void assign(String value) {
        String raw = variable.value();
        variable.assign(raw.substring(0, begin) + value + raw.substring(end));
    }
}

class Solver {
    Type parse(String expression) {
        if (expression.startsWith("substr")) {
            // substr( )
            // 012345678
            int quoteCount = 0;
            int firstComma = -1;
            for (int i = 7; i < expression.length() && firstComma == -1; ++ i) {
                if (expression.charAt(i) == '"') {
                    quoteCount ^= 1;
                }
                if (quoteCount == 0 && expression.charAt(i) == ',') {
                    firstComma = i;
                }
            }
            Variable string = new Variable(expression.substring(7, firstComma));
            StringTokenizer tokenizer = new StringTokenizer(expression.substring(firstComma + 1, expression.length() - 1), ",");
            int offset = Integer.parseInt(tokenizer.nextToken());
            if (offset < 0) {
                offset = string.length() + offset;
            }
            int count = tokenizer.hasMoreTokens()? Integer.parseInt(tokenizer.nextToken()): string.length() - offset;
            if (count < 0) {
                count = string.length() - offset + count;
            }
            return new SubString(string, offset, offset + count);
        }
        return new Variable(expression);
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(tokenizer.nextToken()) + Integer.parseInt(tokenizer.nextToken());
        while (n > 0) {
            n --;
            char[] buffer = in.readLine().toCharArray();
            int length = 0;
            int quoteCount = 0;
            for (char token : buffer) {
                if (token == '"') {
                    quoteCount ^= 1;
                }
                if (token != ' ' || quoteCount == 1) {
                    buffer[length ++] = token;
                }
            }
            String expression = new String(buffer, 0, length - 1);
            if (expression.contains("=")) {
                int seperator = expression.indexOf("=");
                Type leftValue = parse(expression.substring(0, seperator));
                Type rightValue = parse(expression.substring(seperator + 1));
                leftValue.assign(rightValue.value());
            } else {
                // print( )
                // 01234567
                out.println(parse(expression.substring(6, expression.length() - 1)).value());
            }
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

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
    }

    String readLine() throws IOException {
        return reader.readLine();
    }
}
