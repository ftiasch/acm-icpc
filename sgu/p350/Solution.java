// SGU 350 -- XOR-omania
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

    int m;
    int[] b;

    int hasCommon(int i, int j) {
        for (int k = 0; k < m; ++ k) {
            if ((b[i] ^ b[j]) == b[k]) {
                return k;
            }
        }
        return -1;
    }

    public void run() {
        try {
            m = reader.nextInt();
            b = new int[m];
            for (int i = 0; i < m; ++ i) {
                b[i] = reader.nextInt();
            }
            if (m == 1) {
                writer.println(String.format("%d %d", 0, b[0]));
            } else {
                ArrayList <Integer> a = new ArrayList <Integer>();
                for (int i = 0; i < m; ++ i) {
                    for (int j = 0; j < i; ++ j) {
                        int k = hasCommon(i, j);
                        if (k != -1) {
                            a.add(0);
                            a.add(b[i]);
                            a.add(b[j]);
                            for (int x = 0; x < m; ++ x) {
                                if (x != i && x != j && x != k && hasCommon(x, i) != -1 && hasCommon(x, j) != -1) {
                                    a.add(b[x]);
                                }
                            }
                            for (int x: a) {
                                writer.print(String.format("%d ", x));
                            }
                            writer.println();
                            throw new Exception();
                        }
                    }
                }
            }
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
