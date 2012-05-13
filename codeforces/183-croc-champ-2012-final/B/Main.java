// Croc Champ 2012 - Final
// Problem B -- Zoo
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

    class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        Point substract(Point other) {
            return new Point(x - other.x, y - other.y);
        }

        long det(Point other) {
            return x * other.y - y * other.x;
        }
    }

    public void run() {
        try {
            int n = nextInt();
            int m = nextInt();
            Point points[] = new Point[m];
            for (int i = 0; i < m; ++ i) {
                int x = nextInt();
                int y = nextInt();
                points[i] = new Point(x, y);
            }
            int results[] = new int[n + 1];
            Arrays.fill(results, 1);
            for (int i = 0; i < m; ++ i) {
                for (int j = i + 1; j < m; ++ j) {
                    if (points[i].y != points[j].y && points[i].det(points[j]) % (points[j].y - points[i].y) == 0) {
                        long x = points[i].det(points[j]) / (points[j].y - points[i].y);
                        if (1 <= x && x <= n) {
                            int count = 0;
                            for (int k = 0; k < m; ++ k) {
                                if (points[i].substract(points[k]).det(points[j].substract(points[k])) == 0) {
                                    count ++;
                                }
                            }
                            results[(int)x] = Math.max(results[(int)x], count);
                        }
                    }
                }
            }
            int result = 0;
            for (int i = 1; i <= n; ++ i) {
                result += results[i];
            }
            System.out.println(result);
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
