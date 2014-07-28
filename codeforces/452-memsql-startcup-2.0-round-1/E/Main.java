import java.io.*;
import java.math.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class Main {
    static int MOD = (int)1e9 + 7;

    public void run() {
        try {
            String[] strings = new String[3];
            for (int i = 0; i < 3; ++ i) {
                strings[i] = reader.readLine();
            }
            State.states = new ArrayList <State>();
            State start = new State(0);
            if (true) {
                State p = start;
                for (int i = 0; i < 3; ++ i) {
                    String string = strings[i];
                    for (int j = 0; j < string.length(); ++ j) {
                        p = p.extend(start, string.charAt(j) - 'a');
                        p.count[i] ++;
                    }
                    p = p.extend(start, 26 + i);
                }
            }
            List <State> states = State.states;
            Collections.sort(states);
            for (State state : states) {
                if (state.parent != null) {
                    for (int i = 0; i < 3; ++ i) {
                        state.parent.count[i] += state.count[i];
                    }
                }
            }
            int maxLength = Integer.MAX_VALUE;
            for (int i = 0; i < 3; ++ i) {
                maxLength = Math.min(maxLength, strings[i].length());
            }
            int[] sum = new int[maxLength + 1];
            for (State state : states) {
                if (state.parent != null) {
                    int ways = 1;
                    for (int i = 0; i < 3; ++ i) {
                        ways = (int)((long)ways * state.count[i] % MOD);
                    }
                    if (ways > 0) {
                        sum[state.length] += ways;
                        sum[state.length] %= MOD;
                        sum[state.parent.length] += MOD - ways;
                        sum[state.parent.length] %= MOD;
                    }
                }
            }
            for (int i = maxLength; i >= 1; -- i) {
                sum[i - 1] += sum[i];
                sum[i - 1] %= MOD;
            }
            for (int i = 1; i <= maxLength; ++ i) {
                writer.print(sum[i] + (i == maxLength ? "\n" : " "));
            }
        } catch (IOException ex) {
        }
        writer.close();
    }

    Main() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    private BufferedReader reader;
    private PrintWriter writer;
}

class State implements Comparable {
    State(int length) {
        this.count = new int[3];
        this.length = length;
        this.parent = null;
        this.go = new State[26 + 3];
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

    int[]   count;
    int     length;
    State   parent;
    State[] go;

    static  List <State> states;
}

