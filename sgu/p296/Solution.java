// SGU 296 -- Sasha vs. Kate
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        String s = in.nextToken();
        int k = s.length() - in.nextInt();
        int start = 0;
        while (k > 0) {
            int choice = start;
            for (int i = start; i + k <= s.length(); ++ i) {
                if (s.charAt(i) > s.charAt(choice)) {
                    choice = i;
                }
            }
            out.print(s.charAt(choice));
            start = choice + 1;
            k --;
        }
        out.println();
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
