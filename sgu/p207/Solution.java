// SGU 207 -- Robbers
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

    class Data implements Comparable <Data> {
        int id;
        double weight;

        Data(int id, double weight) {
            this.id = id;
            this.weight = weight;
        }

        public int compareTo(Data o) {
            return weight < o.weight? -1 : weight > o.weight? 1: 0;
        }
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int y = reader.nextInt();
            int[] x = new int[n];
            Data[] datas = new Data[n];
            int left = m;
            int[] k = new int[n];
            for (int i = 0; i < n; ++ i) {
                x[i] = reader.nextInt();
                k[i] = m * x[i] / y;
                left -= k[i];
                datas[i] = new Data(i, Math.abs(((double)k[i] + 1) / m - (double)x[i] / y) - Math.abs((double)k[i] / m - (double)x[i] / y));
            }
            Arrays.sort(datas);
            for (int i = 0; i < n && left > 0; ++ i) {
                left --;
                k[datas[i].id] ++;
            }
            for (int i = 0; i < n; ++ i) {
                writer.print(String.format("%d%c", k[i], i == n - 1 ? '\n' : ' '));
            }
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
