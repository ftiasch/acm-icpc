// Codeforces Beta Round #91
// Problem D -- Lucky Segments
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    BigInteger[] prepare(long[] ends) {
        Arrays.sort(ends);
        int n = ends.length;
        BigInteger[] sum = new BigInteger[n + 1];
        sum[n] = BigInteger.ZERO;
        for (int i = n - 1; i >= 0; -- i) {
            sum[i] = sum[i + 1].add(BigInteger.valueOf(ends[i]));
        }
        return sum;
    }
    
    final static int M = (1 << 19) - 2;

    BigInteger getCost(long[] ends, BigInteger[] sum, long x) {
        int n = ends.length;
        int low = 0;
        int high = n;
        while (low < high) {
            int middle = low + high >> 1;
            if (ends[middle] <= x) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return sum[high].subtract(BigInteger.valueOf(x).multiply(BigInteger.valueOf(n - high)));
    }

    public void run() {
        try {
            long[] numbers = new long[M];
            if (true) {
                int total = 0;
                for (int length = 1; length <= 18; ++ length) {
                    for (int mask = 0; mask < 1 << length; ++ mask) {
                        long now = 0;
                        for (int i = 1; i <= length; ++ i) {
                            now *= 10;
                            now += (mask >> (length - i) & 1) == 0 ? 4 : 7;
                        }
                        numbers[total ++] = now;
                    }
                }
            }
            int n = reader.nextInt();
            long k = reader.nextLong();
            long[] left = new long[n];
            long[] right = new long[n];
            long maxLength = Long.MAX_VALUE;
            for (int i = 0; i < n; ++ i) {
                left[i] = reader.nextLong();
                right[i] = reader.nextLong();
                maxLength = Math.min(maxLength, right[i] - left[i]);
                right[i] *= -1;
            }
            BigInteger[] leftSum = prepare(left);
            BigInteger[] rightSum = prepare(right);
            int answer = 0;
            for (int i = 0, j = 0; j < M; ++ j) {
                while (numbers[j] - numbers[i] > maxLength) {
                    i ++;
                }
                while (i <= j) {
                    BigInteger cost = getCost(left, leftSum, numbers[i])
                                 .add(getCost(right, rightSum, -numbers[j]));
                    if (cost.compareTo(BigInteger.valueOf(k)) <= 0) {
                        break;
                    }
                    i ++;
                }
                answer = Math.max(answer, j - i + 1);
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

    Long nextLong() throws IOException {
        return Long.parseLong(next());
    }
}
