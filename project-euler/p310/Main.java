import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        int n = 100000;
        int[] sg = new int[n + 1];
        sg[0] = 0;
        int m = 0;
        for (int i = 1; i <= n; ++ i) {
            Set <Integer> set = new HashSet <Integer>();
            for (int j = 1; j * j <= i; ++ j) {
                set.add(sg[i - j * j]);
            }
            while (set.contains(sg[i])) {
                sg[i] ++;
            }
            m = Math.max(m, sg[i]);
        }
        int[] count = new int[m + 1];
        long answer = 0;
        for (int b = 0; b <= n; ++ b) {
            count[sg[b]] ++;
            for (int c = b; c <= n; ++ c) {
                int a = sg[b] ^ sg[c];
                if (a <= m) {
                    answer += count[a];
                }
            }
        }
        System.out.println(answer);
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

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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
