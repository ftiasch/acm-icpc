// SGU 318 -- Grants
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            boolean[] occur = new boolean[n];
            boolean[][][] relation = new boolean[n][n][2];
            while (m > 0) {
                m --;
                int size = reader.nextInt();
                int[] has = new int[n];
                while (size > 0) {
                    size --;
                    int id = reader.nextInt() - 1;
                    has[id] = 1;
                    occur[id] = true;
                }
                for (int i = 0; i < n; ++ i) {
                    for (int j = 0; j < n; ++ j) {
                        relation[i][j][has[i] ^ has[j]] = true;
                    }
                }
            }
            int answer = 0;
            boolean[] visit = new boolean[n];
            for (int i = 0; i < n; ++ i) {
                if (!visit[i]) {
                    answer ++;
                    for (int j = 0; j < n; ++ j) {
                        if (!relation[i][j][1]) {
                            visit[j] = true;
                        }
                    }
                }
            }
            for (int i = 0; i < n; ++ i) {
                if (!occur[i]) {
                    answer --;
                    break;
                }
            }
            writer.println(answer);
        } catch (Exception ex) {
        }
        writer.close();
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
