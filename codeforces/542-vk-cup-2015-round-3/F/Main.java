import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        List <List <Integer>> tasks = new ArrayList <List <Integer>>();
        for (int i = 0; i < m; ++ i) {
            tasks.add(new ArrayList <Integer>());
        }
        for (int i = 0; i < n; ++ i) {
            int t = reader.nextInt();
            int q = reader.nextInt();
            tasks.get(m - t).add(q);
        }
        for (int i = 0; i < m; ++ i) {
            Collections.sort(tasks.get(i));
        }
        int[] maximum = new int[n + 1];
        Arrays.fill(maximum, Integer.MIN_VALUE);
        maximum[1] = 0;
        for (int level = 0; level < m; ++ level) {
            for (int q : tasks.get(level)) {
                int[] newMaximum = maximum.clone();
                for (int i = 1; i <= n; ++ i) {
                    if (maximum[i] > Integer.MIN_VALUE) {
                        newMaximum[i - 1] = Math.max(newMaximum[i - 1], maximum[i] + q);
                    }
                }
                maximum = newMaximum;
            }
            int[] newMaximum = new int[n + 1];
            Arrays.fill(newMaximum, Integer.MIN_VALUE);
            for (int i = 0; i <= n; ++ i) {
                int ii = Math.min(i * 2, n);
                newMaximum[ii] = Math.max(newMaximum[ii], maximum[i]);
            }
            maximum = newMaximum;
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i <= n; ++ i) {
            result = Math.max(result, maximum[i]);
        }
        writer.println(result);
        writer.close();
    }

    Main() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    private InputReader reader;
    private PrintWriter writer;
}

class InputReader {
    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int nextChar() {
        if (charCount == -1) {
            throw new InputMismatchException();
        }
        if (head >= charCount) {
            head = 0;
            try {
                charCount = stream.read(buffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (charCount <= 0) {
                return -1;
            }
        }
        return buffer[head ++];
    }

    public int nextInt() {
        int c = nextChar();
        while (isSpaceChar(c)) {
            c = nextChar();
        }
        int sign = 1;
        if (c == '-') {
            sign = -1;
            c = nextChar();
        }
        int result = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            result *= 10;
            result += c - '0';
            c = nextChar();
        } while (!isSpaceChar(c));
        return sign * result;
    }

    public boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    private InputStream stream;
    private int head, charCount;
    private byte[] buffer = new byte[1024];
}
