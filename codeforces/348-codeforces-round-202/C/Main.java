import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int q = reader.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; ++ i) {
                a[i] = reader.nextInt();
            }
            int[] size = new int[m];
            int[][] sets = new int[m][];
            int totalSize = 0;
            for (int i = 0; i < m; ++ i) {
                size[i] = reader.nextInt();
                totalSize += size[i];
                sets[i] = new int[size[i]];
                for (int j = 0; j < size[i]; ++ j) {
                    sets[i][j] = reader.nextInt() - 1;
                }
            }
            int limit = (int)Math.sqrt(totalSize);
            int bigCount = 0;
            int[] type = new int[m];
            int[] bigSets = new int[m];
            Arrays.fill(type, -1);
            long[] answer = new long[m];
            for (int i = 0; i < m; ++ i) {
                if (size[i] >= limit) {
                    for (int j = 0; j < size[i]; ++ j) {
                        answer[i] += a[sets[i][j]];
                    }
                    bigSets[type[i] = bigCount ++] = i;
                }
            }
            int[] stamp = new int[n];
            int[][] intersection = new int[bigCount][m];
            Arrays.fill(stamp, -1);
            for (int i = 0; i < m; ++ i) {
                if (type[i] != -1) {
                    for (int j = 0; j < size[i]; ++ j) {
                        stamp[sets[i][j]] = i;
                    }
                    for (int k = 0; k < m; ++ k) {
                        for (int j = 0; j < size[k]; ++ j) {
                            if (stamp[sets[k][j]] == i) {
                                intersection[type[i]][k] ++;
                            }
                        }
                    }
                }
            }
            long[] delta = new long[m];
            while (q > 0) {
                q --;
                String buffer = reader.next();
                int i = reader.nextInt() - 1;
                if (buffer.equals("?")) {
                    if (type[i] == -1) {
                        long ret = 0;
                        for (int j = 0; j < size[i]; ++ j) {
                            ret += a[sets[i][j]];
                        }
                        for (int p = 0; p < bigCount; ++ p) {
                            int k = bigSets[p];
                            ret += delta[k] * intersection[p][i];
                        }
                        writer.println(ret);
                    } else {
                        writer.println(answer[i]);
                    }
                } else {
                    int d = reader.nextInt();
                    for (int p = 0; p < bigCount; ++ p) {
                        int k = bigSets[p];
                        answer[k] += (long)d * intersection[p][i];
                    }
                    if (type[i] == -1) {
                        for (int j = 0; j < size[i]; ++ j) {
                            a[sets[i][j]] += d;
                        }
                    } else {
                        delta[i] += d;
                    }
                }
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Main() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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
