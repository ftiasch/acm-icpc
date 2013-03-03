// Codeforces Round #167
// Problem D -- Dima and Figure
import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    InputReader reader;
    PrintWriter writer;

    Main() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    final static int MOD = 1000000000 + 7;

    int size;

    int[][] transform(int[][] sum) {
        int[][] value = new int[size][size];
        for (int i = 0; i < size; ++ i) {
            for (int j = 0; j < size; ++ j) {
                long ret = sum[i][j];
                if (i > 0) {
                    ret += value[i - 1][j];
                }
                if (j > 0) {
                    ret += value[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    ret -= value[i - 1][j - 1];
                }
                value[i][j] = (int)(ret % MOD);
            }
        }
        return value;
    }

    void clear(int[][] array) {
        for (int[] row : array) {
            Arrays.fill(row, 0);
        }
    }

    void apply(int[][] sum, int x_0, int x_1, int y_0, int y_1, int delta) {
        if (x_0 <= x_1 && y_0 <= y_1 && delta != 0) {
            sum[x_0][y_0] += delta;
            sum[x_0][y_0] %= MOD;
            sum[x_1 + 1][y_0] -= delta;
            sum[x_1 + 1][y_0] %= MOD;
            sum[x_0][y_1 + 1] -= delta;
            sum[x_0][y_1 + 1] %= MOD;
            sum[x_1 + 1][y_1 + 1] += delta;
            sum[x_1 + 1][y_1 + 1] %= MOD;
        }
    }

    boolean valid(int x, int dx) {
        return !(x == 1 && dx == -1);
    }

    int turn(int x, int dx) {
        return (x == 0 && dx == 1) ? 1 : x;
    }

    int getLow(int i, int j, int d) {
        if (d == 0) {
            return i;
        }
        return d == -1 ? 0 : i + 1;
    }

    int getHigh(int i, int j, int d) {
        if (d == 0) {
            return i;
        }
        return Math.min(d == 1 ? size - 1 : i - 1, size - 1 - j);
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = size = reader.nextInt();
            int[][][][] sum = new int[2][2][m + 1][m + 1];
            int answer = 0;
            for (int row = 0; row < n; ++ row) {
                int[][][][] ways = new int[2][2][][];
                for (int x = 0; x < 2; ++ x) {
                    for (int y = 0; y < 2; ++ y) {
                        ways[x][y] = transform(sum[x][y]);
                        clear(sum[x][y]);
                    }
                }
                for (int i = 0; i < m; ++ i) {
                    for (int j = 0; i + j < m; ++ j) {
                        ways[0][0][i][j] ++;
                    }
                }
                //for (int x = 0; x < 2; ++ x) {
                //    for (int y = 0; y < 2; ++ y) {
                //        for (int i = 0; i < m; ++ i) {
                //            for (int j = 0; i + j < m; ++ j) {
                //                if (ways[x][y][i][j] != 0) {
                //                    System.err.println(String.format("%d %d %d %d %d %d", row, x, y, i, j, ways[x][y][i][j]));
                //                }
                //            }
                //        }
                //    }
                //}
                for (int x = 0; x < 2; ++ x) {
                    for (int y = 0; y < 2; ++ y) {
                        for (int i = 0; i < m; ++ i) {
                            for (int j = 0; i + j < m; ++ j) {
                                answer += ways[x][y][i][j];
                                answer %= MOD;
                                for (int dx = -1; dx <= 1; ++ dx) {
                                    for (int dy = -1; dy <= 1; ++ dy) {
                                        if (valid(x, dx) && valid(y, dy)) {
                                            apply(sum[turn(x, dx)][turn(y, dy)], 
                                                    getLow(i, j, dx),
                                                    getHigh(i, j, dx),
                                                    getLow(j, i, dy),
                                                    getHigh(j, i, dy),
                                                    ways[x][y][i][j]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            writer.println((answer + MOD) % MOD);
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
