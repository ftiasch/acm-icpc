import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
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
            if (c < '0' || c > '9')
                throw new InputMismatchException();
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
