// Codeforces Beta Round #93
// Problem B -- Canvas Frames
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int[] count = new int[100];
        for (int i = 0; i < n; ++ i) {
            count[in.nextInt() - 1] ++;
        }
        int result = 0;
        for (int i = 0; i < 100; ++ i) {
            result += count[i] >> 1;
        }
        out.println(result >> 1);
        out.close();
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
