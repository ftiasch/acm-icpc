// Codechef April Chalenge 2012
// Fit to Play
import java.io.*;
import java.util.*;

public class Main implements Runnable {
    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintStream writer;

    Main() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
        writer = new PrintStream(new BufferedOutputStream(System.out));
    }

    public void run() {
        try {
            int testCount = nextInt();
            while (testCount > 0) {
                testCount --;
                int n = nextInt();
                int maxDifference = 0;
                int minScore = Integer.MAX_VALUE;
                for (int i = 0; i < n; ++ i) {
                    int score = nextInt();
                    if (score > minScore) {
                        maxDifference = Math.max(maxDifference, score - minScore);
                    } else {
                        minScore = score;
                    }
                }
                if (maxDifference > 0) {
                    System.out.println(maxDifference);
                } else {
                    System.out.println("UNFIT");
                }
            }
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
        new Thread(new Main()).run();
    }
}
