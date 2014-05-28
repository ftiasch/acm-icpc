import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    static int T = 201;
    static int MOD = (int)1e9 + 7;

    int b, m;
    int[] value;
    int[][] go;
    Integer[][][][] memory;

    int go(int[] number, int less, int first, int length, int state, int left) {
        if (left < 0) {
            return 0;
        }
        if (length == 0) {
            return less;
        }
        if (less == 1 && memory[first][length][state][left] != null) {
            return memory[first][length][state][left];
        }
        int result = 0;
        int newLess = less;
        for (int i = less == 1 ? b - 1 : number[length - 1]; i >= first; -- i) {
            int v = go[state][i];
            result += go(number, newLess, 0, length - 1, v, left - value[v]);
            if (result >= MOD) {
                result -= MOD;
            }
            newLess = 1;
        }
        if (less == 1) {
            memory[first][length][state][left] = result;
        }
        return result;
    }

    int count(int[] number) {
        int length = number.length;
        int result = 0;
        for (int i = 1; i <= length; ++ i) {
            result += go(number, i < length ? 1 : 0, 1, i, 0, m);
            if (result >= MOD) {
                result -= MOD;
            }
        }
        return result;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            b = reader.nextInt();
            m = reader.nextInt();
            int[] low;
            if (true) {
                int length = reader.nextInt();
                low = new int[length];
                for (int i = length - 1; i >= 0; -- i) {
                    low[i] = reader.nextInt();
                }
            }
            int[] high;
            if (true) {
                int length = reader.nextInt();
                high = new int[length];
                for (int i = length - 1; i >= 0; -- i) {
                    high[i] = reader.nextInt();
                }
                int delta = 1;
                for (int i = 0; i < length; ++ i) {
                    high[i] += delta;
                    delta = 0;
                    if (high[i] >= b) {
                        delta ++;
                        high[i] -= b;
                    }
                }
                if (delta > 0) {
                    high = Arrays.copyOf(high, length + 1);
                    high[length] = delta;
                }
            }
            int t = 1;
            go  = new int[T][b];
            for (int i = 0; i < T; ++ i) {
                Arrays.fill(go[i], -1);
            }
            value = new int[T];
            for (int i = 0; i < n; ++ i) {
                int length = reader.nextInt();
                int p = 0;
                for (int k = 0; k < length; ++ k) {
                    int c = reader.nextInt();
                    if (go[p][c] == -1) {
                        go[p][c] = t ++;
                    }
                    p = go[p][c];
                }
                int v = reader.nextInt();
                value[p] += v;
            }
            if (true) {
                int[] next = new int[T];
                Queue <Integer> queue = new LinkedList <Integer>();
                for (int i = 0; i < b; ++ i) {
                    int v = go[0][i];
                    if (v == -1) {
                        go[0][i] = 0;
                    } else {
                        next[v] = 0;
                        queue.offer(v);
                    }
                }
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    value[u] += value[next[u]];
                    for (int i = 0; i < b; ++ i) {
                        int v = go[u][i];
                        if (v == -1) {
                            go[u][i] = go[next[u]][i];
                        } else {
                            next[v] = go[next[u]][i];
                            queue.offer(v);
                        }
                    }
                }
            }
            memory = new Integer[2][T][T][m + 1];
            writer.println((count(high) + MOD - count(low)) % MOD);
        } catch (IOException ex) {
        }
        writer.close();
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
