import java.io.*;
import java.math.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class Main {
    final static long INFINITY = (long)1e18 + 1;

    long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[5][5];
        for (int i = 0; i < 5; ++ i) {
            for (int j = 0; j < 5; ++ j) {
                c[i][j] = INFINITY;
                for (int k = 0; k < 5; ++ k) {
                    c[i][j] = Math.min(c[i][j], a[i][k] + b[k][j]);
                }
            }
        }
        return c;
    }

    long[][] power(long[][] a, long n) {
        long[][] result = new long[5][5];
        for (int i = 0; i < 5; ++ i) {
            for (int j = 0; j < 5; ++ j) {
                result[i][j] = i == j ? 0 : INFINITY;
            }
        }
        while (n > 0) {
            if (n % 2 == 1) {
                result = multiply(result, a);
            }
            a = multiply(a, a);
            n >>= 1;
        }
        return result;
    }

    long getMinimum(long[][] minimum, long need) {
        long[][] result = power(minimum, need);
        long m = INFINITY;
        for (int i = 0; i < 4; ++ i) {
            m = Math.min(m, result[i][4]);
        }
        return m;
    }

    public void run() throws IOException {
        long n = reader.nextLong();
        String s = reader.next();
        State.states = new ArrayList <State>();
        State start = new State(0);
        if (true) {
            State p = start;
            for (int i = 0; i < s.length(); ++ i) {
                p = p.extend(start, s.charAt(i) - 'A');
            }
        }
        Collections.sort(State.states);
        for (State p : State.states) {
            p.need = new long[4];
            Arrays.fill(p.need, INFINITY);
            for (int c = 0; c < 4; ++ c) {
                State q = p.go[c];
                if (q == null) {
                    p.need[c] = 0;
                } else {
                    for (int i = 0; i < 4; ++ i) {
                        p.need[i] = Math.min(p.need[i], q.need[i] + 1);
                    }
                }
            }
        }
        long[][] minimum = new long[5][5];
        for (int i = 0; i < 4; ++ i) {
            minimum[i] = Arrays.copyOf(start.go[i].need, 5);
            for (int j = 0; j < 5; ++ j) {
                minimum[i][j] ++;
            }
        }
        Arrays.fill(minimum[4], INFINITY);
        long low = 0;
        long high = n;
        while (low < high) {
            long middle = low + high + 1 >> 1;
            if (getMinimum(minimum, middle) <= n) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        writer.println(low);
        writer.close();
    }

    Main() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    private InputReader reader;
    private PrintWriter writer;
}

class State implements Comparable {
    State(int length) {
        this.length = length;
        this.parent = null;
        go = new State[4];
        states.add(this);
    }

    State extend(State start, int c) {
        State p = this;
        State np = new State(length + 1);
        while (p != null && p.go[c] == null) {
            p.go[c] = np;
            p = p.parent;
        }
        if (p == null) {
            np.parent = start;
        } else {
            State q = p.go[c];
            if (p.length + 1 == q.length) {
                np.parent = q;
            } else {
                State nq = new State(p.length + 1);
                nq.go = q.go.clone();
                nq.parent = q.parent;
                np.parent = q.parent = nq;
                while (p != null && p.go[c] == q) {
                    p.go[c] = nq;
                    p = p.parent;
                }
            }
        }
        return np;
    }

    @Override
    public int compareTo(Object other) {
        State o = (State)other;
        return o.length - length;
    }

    int     length;
    long[]  need;
    State   parent;
    State[] go;

    static  List <State> states;
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
