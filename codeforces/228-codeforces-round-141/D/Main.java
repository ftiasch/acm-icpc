// Codeforces Round #141
// Problem D -- Zigzag
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

class BinaryIndexedTree {
    int n;
    long[] count;

    BinaryIndexedTree(int n) {
        this.n = n;
        this.count = new long[n];
        Arrays.fill(count, 0);
    }

    void add(int key, int delta) {
        for (int i = key; i < n; i += ~i & i + 1) {
            count[i] += delta;
        }
    }

    long query(int key) {
        long result = 0;
        for (int i = key; i >= 0; i -= ~i & i + 1) {
            result += count[i];
        }
        return result;
    }
}


class Solver {
    long query(BinaryIndexedTree tree, int n, int mod, int remainder) {
        while (n >= 0 && n % mod != remainder) {
            n --;
        }
        if (n < 0) {
            return 0;
        }
        return tree.query(n / mod);
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        int[][] zigzag = new int[7][];
        for (int z = 2; z <= 6; ++ z) {
            int mod = z - 1 << 1;
            zigzag[z] = new int[mod];
            for (int i = 1; i <= mod; ++ i) {
                if (i % mod == 0) {
                    zigzag[z][i - 1] = 2;
                } else if (i % mod <= z) {
                    zigzag[z][i - 1] = i % mod;
                } else {
                    zigzag[z][i - 1] = (z << 1) - i % mod;
                }
            }
        }
//for (int z = 2; z <= 6; ++ z) {
//    out.println(Arrays.toString(zigzag[z]));
//}
        int n = in.nextInt();
        BinaryIndexedTree[][] tree = new BinaryIndexedTree[7][];
        for (int z = 2; z <= 6; ++ z) {
            int mod = z - 1 << 1;
            tree[z] = new BinaryIndexedTree[mod];
            for (int r = 0; r < mod; ++ r) {
                tree[z][r] = new BinaryIndexedTree(n / mod + 1);
            }
        }
        int[] a = new int[n];
        for (int i = 0; i < n; ++ i) {
            a[i] = in.nextInt();
            for (int z = 2; z <= 6; ++ z) {
                int mod = z - 1 << 1;
                tree[z][i % mod].add(i / mod, a[i]);
            }
        }
        int m = in.nextInt();
        while (m > 0) {
            m --;
            if (in.nextInt() == 1) {
                int i = in.nextInt() - 1;
                int v = in.nextInt();
                for (int z = 2; z <= 6; ++ z) {
                    int mod = z - 1 << 1;
                    tree[z][i % mod].add(i / mod, v - a[i]);
                }
                a[i] = v;
            } else {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                int z = in.nextInt();
                int mod = z - 1 << 1;
                long result = 0;
                for (int d = 0; d < mod; ++ d) {
                    int remainder = (l + d) % mod;
                    result += zigzag[z][d] * query(tree[z][remainder], r, mod, remainder);
                    result -= zigzag[z][d] * query(tree[z][remainder], l - 1, mod, remainder);
                }
                out.println(result);
            }
        }
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
}
