import java.io.*;
import java.math.*;
import java.util.*;

public class C {
    public void run() {
        try {
            int n = reader.nextInt();
            Request[] requests = new Request[n];
            for (int i = 0; i < n; ++ i) {
                int size = reader.nextInt();
                int value = reader.nextInt();
                requests[i] = new Request(size, value, i);
            }
            Arrays.sort(requests);
            int m = reader.nextInt();
            Table[] tables = new Table[m];
            for (int i = 0; i < m; ++ i) {
                tables[i] = new Table(reader.nextInt(), i);
            }
            Arrays.sort(tables);
            int[][] maximum = new int[n + 1][m + 1];
            Arrays.fill(maximum[n], Integer.MIN_VALUE);
            maximum[n][0] = 0;
            for (int i = n - 1; i >= 0; -- i) {
                Arrays.fill(maximum[i], Integer.MIN_VALUE);
                for (int j = 0; j <= m; ++ j) {
                    if (maximum[i + 1][j] > Integer.MIN_VALUE) {
                        maximum[i][j] = Math.max(maximum[i][j], maximum[i + 1][j]);
                        if (j < m && requests[i].size <= tables[m - 1 - j].size) {
                            maximum[i][j + 1] = Math.max(maximum[i][j + 1], maximum[i + 1][j] + requests[i].value);
                        }
                    }
                }
            }
            int result = Integer.MIN_VALUE;
            for (int i = 0; i <= m; ++ i) {
                result = Math.max(result, maximum[0][i]);
            }
            int taken = 0;
            while (maximum[0][taken] != result) {
                taken ++;
            }
            writer.println(String.format("%d %d", taken, result));
            for (int i = 0; i < n; ++ i) {
                if (taken > 0 && requests[i].size <= tables[m - taken].size && maximum[i][taken] == maximum[i + 1][taken - 1] + requests[i].value) {
                    writer.println(String.format("%d %d", requests[i].id + 1, tables[m - taken].id + 1));
                    taken --;
                }
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    class Request implements Comparable {
        Request(int size, int value, int id) {
            this.size = size;
            this.value = value;
            this.id = id;
        }

        @Override
        public int compareTo(Object other) {
            Request o = (Request)other;
            if (size != o.size) {
                return size - o.size;
            }
            return value - o.value;
        }

        int size, value, id;
    }

    class Table implements Comparable {
        Table(int size, int id) {
            this.size = size;
            this.id = id;
        }

        @Override
        public int compareTo(Object other) {
            Table o = (Table)other;
            return size - o.size;
        }

        int size, value, id;
    }


    C() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new C().run();
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

    public String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    public Integer nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public Long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    private BufferedReader  reader;
    private StringTokenizer tokenizer;
}
