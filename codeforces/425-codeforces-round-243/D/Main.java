import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    static int MAX_VALUE = 100001;

    public void run() {
        try {
            int n = reader.nextInt();
            int[] x = new int[n];
            int[] y = new int[n];
            int[] count = new int[MAX_VALUE];
            for (int i = 0; i < n; ++ i) {
                x[i] = reader.nextInt();
                y[i] = reader.nextInt();
                count[x[i]] ++;
            }
            int[][] rows = new int[MAX_VALUE][];
            for (int i = 0; i < MAX_VALUE; ++ i) {
                rows[i] = new int[count[i]];
            }
            Arrays.fill(count, 0);
            for (int i = 0; i < n; ++ i) {
                rows[x[i]][count[x[i]] ++] = y[i];
            }
            for (int i = 0; i < MAX_VALUE; ++ i) {
                Arrays.sort(rows[i]);
            }
            int limit = (int)Math.sqrt(n);
            int result = 0;
            Set <Point> set = new HashSet <Point>();
            for (int i = 0; i < MAX_VALUE; ++ i) {
                int[] rowi = rows[i];
                if (rowi.length >= limit) {
                    boolean[] visited = new boolean[MAX_VALUE];
                    for (int yy : rowi) {
                        visited[yy] = true;
                    }
                    for (int j = 0; j < MAX_VALUE; ++ j) {
                        int[] rowj = rows[j];
                        if (rowj.length < limit || i < j) {
                            int l = Math.abs(i - j);
                            for (int p = 0, q = 0; p < rowj.length; ++ p) {
                                while (rowj[p] - rowj[q] > l) {
                                    q ++;
                                }
                                if (rowj[p] - rowj[q] == l && visited[rowj[p]] && visited[rowj[q]]) {
                                    result ++;
                                }
                            }
                        }
                    }
                } else {
                    for (int q = 0; q < rowi.length; ++ q) {
                        for (int p = 0; p < q; ++ p) {
                            int l = rowi[q] - rowi[p];
                            if (set.contains(new Point(i - l, rowi[p])) && set.contains(new Point(i - l, rowi[q]))) {
                                result ++;
                            }
                        }
                    }
                    for (int yy : rowi) {
                        set.add(new Point(i, yy));
                    }
                }
            }
            writer.println(result);
        } catch (IOException ex) {
        }
        writer.close();
    }

    class Point {
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x * 100001 + y;
        }

        @Override
        public boolean equals(Object other) {
            Point o = (Point)other;
            return x == o.x && y == o.y;
        }

        int x, y;
    }

    Main() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    private InputReader reader;
    private PrintWriter writer;
}

class InputReader {
    InputReader(InputStream in) {
        reader    = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
    }

    private String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    public Integer nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    private BufferedReader  reader;
    private StringTokenizer tokenizer;
}
