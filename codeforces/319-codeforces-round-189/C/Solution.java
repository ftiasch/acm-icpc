// Codeforces Round #189
// Problem C -- Kalila and Dimna in the Logging Industry
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    class Line {
        long a, b;

        Line(long a, long b) {
            this.a = a;
            this.b = b;
        }

        long at(long x) {
            return a * x + b;
        }
    }

    double intersect(Line u, Line v) {
        return (double)(u.b - v.b) / (v.a - u.a);
    }

    boolean isConvex(Line u, Line v, Line w) {
        return intersect(u, v) < intersect(v, w);
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            for (int i = 0; i < n; ++ i) {
                b[i] = reader.nextInt();
            }
            long[] minimum = new long[n];
            Line[] lines = new Line[n];
            int head = 0;
            int tail = 0;
            for (int i = 0; i < n; ++ i) {
                if (i > 0) {
                    while (tail - head >= 2 && lines[head].at(a[i]) > lines[head + 1].at(a[i])) {
                        head ++;
                    }
                    minimum[i] = lines[head].at(a[i]);
                }
                Line line = new Line(b[i], minimum[i]);
                while (tail - head >= 2 && !isConvex(lines[tail - 2], lines[tail - 1], line)) {
                    tail --;
                }
                lines[tail ++] = line;
            }
            writer.println(minimum[n - 1]);
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
