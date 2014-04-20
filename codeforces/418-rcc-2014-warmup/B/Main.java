import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int b = reader.nextInt();
            Friend[] friends = new Friend[n];
            for (int i = 0; i < n; ++ i) {
                int cost = reader.nextInt();
                int monitor  = reader.nextInt();
                int count = reader.nextInt();
                int ability = 0;
                while (count > 0) {
                    count --;
                    ability |= 1 << reader.nextInt() - 1;
                }
                friends[i] = new Friend(cost, monitor, ability);
            }
            Arrays.sort(friends);
            long result = Long.MAX_VALUE;
            long[] minimum = new long[1 << m];
            Arrays.fill(minimum, Long.MAX_VALUE);
            minimum[0] = 0;
            for (Friend friend : friends) {
                for (int mask = (1 << m) - 1; mask >= 0; -- mask) {
                    if (minimum[mask] < Long.MAX_VALUE) {
                        minimum[mask | friend.ability] = Math.min(minimum[mask | friend.ability], minimum[mask] + friend.cost);
                    }
                }
                if (minimum[(1 << m) - 1] < Long.MAX_VALUE) {
                    result = Math.min(result, minimum[(1 << m) - 1] + (long)friend.monitor * b);
                }
            }
            writer.println(result < Long.MAX_VALUE ? result : -1);
        } catch (IOException ex) {
        }
        writer.close();
    }

    class Friend implements Comparable {
        Friend(int cost, int monitor, int ability) {
            this.cost = cost;
            this.monitor = monitor;
            this.ability = ability;
        }

        @Override
        public int compareTo(Object other) {
            Friend o = (Friend)other;
            return monitor - o.monitor;
        }

        int cost, monitor, ability;
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
