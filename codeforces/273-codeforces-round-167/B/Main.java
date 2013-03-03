// Codeforces Round #167
// Problem B -- Dima and Two Sequences
import java.io.*;
import java.math.*;
import java.util.*;


public class Main {
    InputReader reader;
    PrintWriter writer;

    Main() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            int[] b = new int[n];
            for (int i = 0; i < n; ++ i) {
                b[i] = reader.nextInt();
            }
            int m = reader.nextInt();
            int two = 0;
            for (int i = 0; i < n; ++ i) {
                if (a[i] == b[i]) {
                    two ++;
                }
            }
            Map <Integer, Integer> map = new HashMap <Integer, Integer>();
            count(map, a);
            count(map, b);
            int answer = 1;
            for (Map.Entry <Integer, Integer> entry : map.entrySet()) {
                int count = entry.getValue();
                for (int i = 1; i <= count; ++ i) {
                    int ret = i;
                    while (two > 0 && ret % 2 == 0) {
                        two --;
                        ret /= 2;
                    }
                    answer = (int)((long)answer * ret % m);
                }
            }
            writer.println(answer);
        } catch (IOException ex) {
        }
        writer.close();
    }

    void count(Map <Integer, Integer> map, int[] as) {
        for (int a : as) {
            if (!map.containsKey(a)) {
                map.put(a, 0);
            }
            map.put(a, map.get(a) + 1);
        }
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
