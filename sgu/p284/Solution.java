import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.math.BigInteger;

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        String pattern = in.nextToken();
        String[] texts = new String[n + 2];
        BigInteger[] result = new BigInteger[n + 2];
        for (int i = 0; i < n + 2; ++ i) {
            result[i] = BigInteger.ZERO;
            String text = "";
            if (i < 2) {
                text += String.format("%c", 'a' + i);
            } else {
                int length = in.nextInt();
                for (int k = 0; k < length; ++ k) {
                    String token = in.nextToken();
                    int id = token.equals("a") || token.equals("b")? token.charAt(0) - 'a': Integer.parseInt(token) + 1;
                    result[i] = result[i].add(result[id]);
                    text += texts[id];
                }
            }
            texts[i] = text.length() < pattern.length()? text: text.substring(0, pattern.length() - 1) + "$" + text.substring(text.length() - pattern.length() + 1);
            text = pattern + "#" + text;
//System.err.println(text);
            int length = text.length();
            int[] next = new int[length];
            next[0] = -1;
            for (int k = 0; k + 1 < length; ++ k) {
                next[k + 1] = -1;
                int j = k;
                while (j != -1 && next[k + 1] == -1) {
                    j = next[j];
                    if (text.charAt(j + 1) == text.charAt(k + 1)) {
                        next[k + 1] = j + 1;
                    }
                }
            }
//System.err.println(Arrays.toString(next));
            boolean[] valid = new boolean[length];
            valid[pattern.length() - 1] = true;
            for (int k = pattern.length(); k < length; ++ k) {
                if (next[k] != -1) {
                    valid[k] |= valid[next[k]];
                }
                if (valid[k]) {
                    result[i] = result[i].add(BigInteger.ONE);
                }
            }
        }
        out.println(result[n + 1]);
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
