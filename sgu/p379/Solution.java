// SGU 379 -- Elevator
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

    long getTime(int[] a, int c) {
        long ret = 0;
        long sum = 0;
        int n = a.length - 1;
        for (int i = n; i >= 1; -- i) {
            sum += a[i];
            if (sum > 0) {
                long tmp = (sum + c - 1) / c;
                sum -= tmp * c;
                ret += i * tmp;
            }
        }
        return ret;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int c = reader.nextInt();
            int p = reader.nextInt();
            int t = reader.nextInt();
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; ++ i) {
                a[i] = reader.nextInt();
            }
            int answer = 0;
            for (int i = 1; i <= n; ++ i) {
                int[] b = new int[n + 1];
                for (int j = 1; j < i; ++ j) {
                    b[j] = a[j];
                }
                int low = -1;
                int high = a[i];
                while (low < high) {
                    int middle = low + high + 1 >> 1;
                    b[i] = middle;
                    if (2 * p * getTime(b, c) <= t) {
                        low = middle;
                    } else {
                        high = middle - 1;
                    }
                }
                if (low >= 0) {
                    int sum = low;
                    for (int j = 1; j < i; ++ j) {
                        sum += a[j];
                    }
                    answer = Math.max(answer, sum);
                }
            }
            writer.println(answer);
        } catch (IOException ex) {
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
