// SGU 299 -- Triangle
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
        BigInteger[] edges = new BigInteger[n];
        for (int i = 0; i < n; ++ i) {
            edges[i] = new BigInteger(in.nextToken());
        }
        Arrays.sort(edges);
        boolean found = false;
        for (int i = 0; i + 2 < n && !found; ++ i) {
            if (edges[i].add(edges[i + 1]).compareTo(edges[i + 2]) > 0) {
                out.println(String.format("%d %d %d", edges[i], edges[i + 1], edges[i + 2]));
                found = true;
            }
        }
        if (!found) {
            out.println("0 0 0");
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
