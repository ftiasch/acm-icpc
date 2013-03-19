// SGU 330 -- Numbers
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

    long find(long n) {
        while ((n & 1) == 0) {
            n >>= 1;
        }
        for (int i = 2; (long)i * i <= n; ++ i) {
            if (n % i == 0) {
                return i;
            }
        }
        return -1;
    }

    public void run() {
        try {
            long a = reader.nextLong();
            long b = reader.nextLong();
            long x = (a & 1) == 0 ? a : a + find(a);
            long y = (b & 1) == 0 ? b : b - find(b);
            if (x < a || y > b || x > y || x == 2) {
                writer.println("Impossible");
            } else {
                if (a != x) {
                    writer.println(a);
                }
                while (true) {
                    writer.println(x);
                    if (x < y) {
                        int i = 0;
                        while ((x >> i & 1) == 0) {
                            i ++;
                        }
                        while ((1L << i) == x || x + (1L << i) > y) {
                            i --;
                        }
                        x += 1L << i;
                    } else {
                        break;
                    }
                }
                if (y != b) {
                    writer.println(b);
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
    
    Long nextLong() throws IOException {
        return Long.parseLong(next());
    }
}
