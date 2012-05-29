import java.io.*;
import java.util.*;
import java.math.*;

public class Solution implements Runnable {
    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintStream writer;

    Solution() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
        writer = new PrintStream(new BufferedOutputStream(System.out));
    }

    public void run() {
        try {
            int a = nextInt();
            int b = nextInt();
            System.out.println(BigInteger.valueOf(a).pow(b).subtract(BigInteger.valueOf(b).pow(a)));
        } catch (Exception e) {
        }
    }

    String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public static void main(String args[]) {
        new Thread(new Solution()).run();
    }
}
