// Codeforces Round #162
// Problem D -- Good Sequences
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static int M = (int)1e5;

    @SuppressWarnings("unchecked")
    public void run() {
        try {
            ArrayList[] divisors = new ArrayList[M + 1];
            for (int i = 1; i <= M; ++ i) {
                divisors[i] = new ArrayList <Integer>();
            }
            for (int i = 2; i <= M; ++ i) {
                for (int j = i; j <= M; j += i) {
                    divisors[j].add(i);
                }
            }
            int answer = 1;
            int[] maximum = new int[M + 1];
            int n = reader.nextInt();
            for (int i = 0; i < n; ++ i) {
                int a = reader.nextInt();
                int length = 0;
                for (Object iter : divisors[a]) {
                    int d = (Integer)iter;
                    length = Math.max(length, maximum[d] + 1);
                }
                answer = Math.max(answer, length);
                for (Object iter : divisors[a]) {
                    int d = (Integer)iter;
                    maximum[d] = Math.max(maximum[d], length);
                }
            }
            writer.println(answer);
        } catch (IOException ex) {
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
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
