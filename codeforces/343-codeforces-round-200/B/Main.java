import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            String string = reader.next();
            Stack <Character> stack = new Stack <Character>();
            for (int i = 0; i < string.length(); ++ i) {
                if (stack.isEmpty() || stack.peek() != string.charAt(i)) {
                    stack.push(string.charAt(i));
                } else {
                    stack.pop();
                }
            }
            writer.println(stack.isEmpty() ? "Yes" : "No");
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
