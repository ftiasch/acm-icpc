// SGU 362 -- Robot-Annihilator
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

    final static String NAMES = "DLUR";
    final static int[] DELTA_X = {1, 0, -1, 0};
    final static int[] DELTA_Y = {0, -1, 0, 1};

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int x = reader.nextInt() - 1;
            int y = reader.nextInt() - 1;
            boolean[][] visit = new boolean[n][m];
            while (true) {
                visit[x][y] = true;
                int i = 0;
                while (i < 4) {
                    int xx = x + DELTA_X[i];
                    int yy = y + DELTA_Y[i];
                    if (0 <= xx && xx < n && 0 <= yy && yy < m && !visit[xx][yy]) {
                        x = xx;
                        y = yy;
                        writer.print(NAMES.charAt(i));
                        break;
                    }
                    i ++;
                }
                if (i == 4) {
                    break;
                }
            }
            writer.println();
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
