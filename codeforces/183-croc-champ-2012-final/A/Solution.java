// Croc Champ 2012 - Final
// Problem A -- Headquarters
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    final static String DIRECTIONS = "LRDU";
    final static int[][] DELTA = {{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    public void run() {
        try {
            int n = reader.nextInt();
            int[][] minMax = new int[2][2];
            while (n > 0) {
                n --;
                String instruction = reader.next();
                int[][] newMinMax = new int[][]{{Integer.MAX_VALUE, Integer.MIN_VALUE}, {Integer.MAX_VALUE, Integer.MIN_VALUE}};
                for (int i = 0; i < instruction.length(); ++ i) {
                    int t = DIRECTIONS.indexOf(instruction.charAt(i));
                    for (int p = 0; p < 2; ++ p) {
                        newMinMax[p][0] = Math.min(newMinMax[p][0], minMax[p][0] + DELTA[t][p]);
                        newMinMax[p][1] = Math.max(newMinMax[p][1], minMax[p][1] + DELTA[t][p]);
                    }
                }
                minMax = newMinMax;
//debug(minMax);
            }
            writer.println((long)((minMax[0][1] - minMax[0][0]) / 2 + 1) * ((minMax[1][1] - minMax[1][0]) / 2 + 1));
        } catch (IOException ex) {
        }
        writer.close();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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
