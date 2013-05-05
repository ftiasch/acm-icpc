// Croc Champ 2013 - Round 2
// Problem D -- Ksusha and Square
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

    class Point implements Comparable <Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point flip() {
            return new Point(y, x);
        }

        public int compareTo(Point o) {
            if (x != o.x) {
                return x - o.x;
            }
            return y - o.y;
        }

        Point subtract(Point o) {
            return new Point(x - o.x, y - o.y);
        }
    }

    long det(Point a, Point b) {
        return (long)a.x * b.y - (long)a.y * b.x;
    }

    boolean isConvex(Point a, Point b, Point c) {
        return det(b.subtract(a), c.subtract(b)) >= 0;
    }

    Point[] getConvex(Point[] points) {
        ArrayList <Point> hull = new ArrayList <Point>();
        for (Point p : points) {
            while (hull.size() >= 2 && !isConvex(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p)) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }
        while (hull.size() >= 2 && hull.get(hull.size() - 1).x == hull.get(hull.size() - 2).x) {
            hull.remove(hull.size() - 1);
        }
        return hull.toArray(new Point[0]);
    }

    Point[] reverse(Point[] points) {
        int n = points.length;
        Point[] ret = new Point[n];
        for (int i = 0; i < n; ++ i) {
            ret[i] = points[n - 1 - i];
        }
        return ret;
    }

    double solve(Point[] points) {
        int n = points.length;
        Arrays.sort(points);
        Point[] down = getConvex(points);
        Point[] up = reverse(getConvex(reverse(points)));
        int xMin = down[0].x;
        int xMax = down[down.length - 1].x;
        long total = 0;
        double sum = 0.0;
        double squareSum = 0.0;
        for (int x = xMin, i = 0, j = 0; x <= xMax; ++ x) {
            while (i + 1 < down.length && down[i + 1].x < x) {
                i ++;
            }
            while (j + 1 < up.length && up[j + 1].x < x) {
                j ++;
            }
            int yMin = (int)Math.ceil((double)(x - down[i].x) / (down[i + 1].x - down[i].x) * (down[i + 1].y - down[i].y)) + down[i].y;
            int yMax = (int)Math.floor((double)(x - up[j].x) / (up[j + 1].x - up[j].x) * (up[j + 1].y - up[j].y)) + up[j].y;
            int count = yMax - yMin + 1;
            total += count;
            sum += (double)count * x;
            squareSum += (double)count * x * x;
        }
        return (total * squareSum - sum * sum) / ((double)total * (total - 1));
    }

    public void run() {
        try {
            int n = reader.nextInt();
            Point[] points = new Point[n];
            for (int i = 0; i < n; ++ i) {
                int x = reader.nextInt();
                int y = reader.nextInt();
                points[i] = new Point(x, y);
            }
            double answer = solve(points);
            for (int i = 0; i < n; ++ i) {
                points[i] = points[i].flip();
            }
            answer += solve(points);
            writer.println(String.format("%.8f", answer));
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
