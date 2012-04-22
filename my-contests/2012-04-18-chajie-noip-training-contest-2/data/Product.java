import java.io.*;
import java.util.*;

public class Product implements Runnable {
    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintStream writer;

    Product () {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
        writer = new PrintStream(new BufferedOutputStream(System.out));
    }

    int gcd(int a, int b) {
        return b == 0? a: gcd(b, a % b);
    }

    int sqrt(int n) {
        int m = Math.max((int)Math.sqrt(n) - 5, 0);
        while ((m + 1) * (m + 1) <= n) {
            m ++;
        }
        return m;
    }

    public void run() {
        try {
            int n = nextInt();
            ArrayList <Integer> odds = new ArrayList <Integer>();
            ArrayList <Integer> evens = new ArrayList <Integer>();
            for (int i = 0; i < n * (n - 1) / 2; ++ i) {
                int b = nextInt();
                if (b % 2 == 0) {
                    evens.add(b);
                } else {
                    odds.add(b);
                }
            }
            int d = 0;
            for (int it : evens) {
                d = gcd(d, it);
            }
            int sequence[] = new int[n - 1];
            for (int i = 0; i < n - 1; ++ i) {
                sequence[i] = evens.get(i) / d;
            }
            Arrays.sort(sequence);
            Collections.sort(odds);
            int t = sqrt(odds.get(0) / (sequence[0] * sequence[1]));
            System.out.printf("%d", d / t);
            for (int i = 0; i < n - 1; ++ i) {
                System.out.printf(" %d", sequence[i] * t);
            }
            System.out.printf("\n");
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
        new Thread(new Product()).run();
    }
}
