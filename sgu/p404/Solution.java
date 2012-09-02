// SGU 404 -- Fortune-telling with camomile
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int need = (n - 1) % m;
        for (int i = 0; i < m; ++ i) {
            String word = in.nextToken();
            if (i == need) {
                out.println(word);
            }
        }
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
