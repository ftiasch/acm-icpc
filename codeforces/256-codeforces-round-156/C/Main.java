// Codeforces Round #156
// Problem C -- Furlo and Rublo and Game
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class Solver {
    static long square(long x) {
        return x * x;
    }

    static int getLower(long n) {
        int low = 0;
        int high = 10000;
        while (low < high) {
            int middle = low + high >> 1;
            if (square(square(middle)) >= n) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return high;
    }

    static int getUpper(long n) {
        int low = 0;
        int high = 1000000000;
        while (low < high) {
            int middle = low + high + 1 >> 1;
            if (square(middle) <= n) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        return low;
    }

    final static long MAX = 777777777777L;
    final static int M = getUpper(MAX);

    int[] sg;
    int[][] sum;

    int getSG(long a) {
        if (a <= M) {
            return sg[(int)a];
        }
        int l = getLower(a);
        int r = getUpper(a);
        int ret = 0;
        while (sum[ret][l] - sum[ret][r + 1] > 0) {
            ret ++;
        }
        return ret;
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        sg = new int[M + 1];
        int[] mark = new int[M + 1];
        Arrays.fill(mark, -1);
        for (int i = 0; i <= M; ++ i) {
            for (int j = getLower(i); j < i && j * j <= i; ++ j) {
                if (sg[j] <= i) {
                    mark[sg[j]] = i;
                }
            }
            sg[i] = 0;
            while (mark[sg[i]] == i) {
                sg[i] ++;
            }
        }
        int maxSG = 0;
        for (int i = 0; i <= M; ++ i) {
            maxSG = Math.max(maxSG, sg[i]);
        }
        maxSG ++;
        sum = new int[maxSG + 1][M + 2];
        for (int i = 0; i <= maxSG; ++ i) {
            sum[i][M + 1] = 0;
            for (int j = M; j >= 0; -- j) {
                sum[i][j] = sum[i][j + 1];
                if (sg[j] == i) {
                    sum[i][j] ++;
                }
            }
        }
        int n = in.nextInt();
        int nimSum = 0;
        for (int i = 0; i < n; ++ i) {
            long a = in.nextLong();
            nimSum ^= getSG(a);
        }
        out.println(nimSum == 0 ? "Rublo" : "Furlo");
        out.close();
    }
}

class InputReader {
    BufferedReader reader;
    StringTokenizer tokenizer;

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
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

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }
}
