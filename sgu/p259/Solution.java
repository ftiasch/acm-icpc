// SGU 259 -- Printed PR
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    class Task implements Comparable <Task> {
        int printTime, deliverTime;

        Task(int printTime, int deliverTime) {
            this.printTime = printTime;
            this.deliverTime = deliverTime;
        }

        public int compareTo(Task other) {
            return other.deliverTime - deliverTime;
        }
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int[] printTime = new int[n];
            int[] deliverTime = new int[n];
            for (int i = 0; i < n; ++ i) {
                printTime[i] = reader.nextInt();
            }
            for (int i = 0; i < n; ++ i) {
                deliverTime[i] = reader.nextInt();
            }
            Task[] tasks = new Task[n];
            for (int i = 0; i < n; ++ i) {
                tasks[i] = new Task(printTime[i], deliverTime[i]);
            }
            Arrays.sort(tasks);
            int answer = 0;
            int now = 0;
            for (Task task : tasks) {
                now += task.printTime;
                answer = Math.max(answer, now + task.deliverTime);
            }
            writer.println(answer);
        } catch (IOException ex) {
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
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
