import java.io.*;
import java.math.*;
import java.util.*;

public class B {
    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[][] cost = new int[n][m];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    cost[i][j] = reader.nextInt();
                }
            }
            int[] result = new int[n];
            boolean[] isPainted = new boolean[n];
            boolean[] isPainting = new boolean[m];
            PriorityQueue <Event> events = new PriorityQueue <Event>();
            isPainted[0] = isPainting[0] = true;
            events.add(new Event(0, 0, cost[0][0]));
            while (!events.isEmpty()) {
                Event event = events.poll();
                int picture = event.picture;
                int painter = event.painter;
                int time = event.time;
                isPainted[picture] = false;
                isPainting[painter] = false;
                if (painter == m - 1) {
                    result[picture] = time;
                } else if (!isPainting[painter + 1]) {
                    isPainted[picture] = true;
                    isPainting[painter + 1] = true;
                    events.add(new Event(picture, painter + 1, time + cost[picture][painter + 1]));
                }
                if (picture + 1 < n && !isPainted[picture + 1]) {
                    isPainted[picture + 1] = true;
                    isPainting[painter] = true;
                    events.add(new Event(picture + 1, painter, time + cost[picture + 1][painter]));
                }
            }
            for (int i = 0; i < n; ++ i) {
                writer.print(result[i] + " ");
            }
            writer.println();
        } catch (IOException ex) {
        }
        writer.close();
    }

    class Event implements Comparable {
        int picture, painter, time;

        Event(int picture, int painter, int time) {
            this.picture = picture;
            this.painter = painter;
            this.time = time;
        }

        @Override
        public int compareTo(Object other) {
            Event o = (Event)other;
            return time - o.time;
        }
    }

    B() {
        reader = new InputReader(System.in);
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new B().run();
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
