// SGU 365 -- Ships of the Desert
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    InputReader reader;
    PrintWriter writer;

    Solution() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Solution().run();
    }

    public void run() {
        try {
            int n = reader.nextInt();
            long[][] ways = new long[10][2];
            for (int i = 0; i < 10; ++ i) {
                ways[i][0] = 1;
            }
            for (int i = 1; i < n; ++ i) {
                long[][] newWays = new long[10][2];
                for (int now = 0; now < 10; ++ now) {
                    for (int type = 0; type < 2; ++ type) {
                        for (int next = 0; next < 10; ++ next) {
                            if (type == 1 && now < next) {
                                continue;
                            }
                            if (now <= next) {
                                newWays[next][type] += ways[now][type];
                            }
                            if (now > next) {
                                newWays[next][1] += ways[now][type];
                            }
                        }
                    }
                }
                ways = newWays;
            }
            long answer = 0;
            for (int now = 0; now < 10; ++ now) {
                for (int type = 0; type < 2; ++ type) {
                    answer += ways[now][type];
                }
            }
            writer.println(answer);
        } catch (IOException ex) {
        }
        writer.close();
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
