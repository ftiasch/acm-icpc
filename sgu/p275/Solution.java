// SGU 275 -- To xor or not to xor
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Solver {
    void run(InputReader in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; ++ i) {
            a[i] = in.nextLong();
        }
        int rank = 0;
        for (int k = 60; k >= 0; -- k) {
            int pivot = rank;
            while (pivot < n && (a[pivot] >> k & 1) == 0) {
                pivot ++;
            }
            if (pivot == n) {
                continue;
            }
            long tmp = a[pivot];
            a[pivot] = a[rank];
            a[rank] = tmp;
            for (int i = 0; i < n; ++ i) {
                if (i != rank && (a[i] >> k & 1) == 1) {
                    a[i] ^= a[rank];
                }
            }
            rank ++;
        }
        long result = 0;
        for (int i = 0; i < n; ++ i) {
            result ^= a[i];
        }
        out.println(result);
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

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }
}
