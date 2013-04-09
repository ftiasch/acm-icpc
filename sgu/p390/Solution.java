// SGU 390 -- Tickets
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

    final static int LENGTH = 19;

    int limit;
    int[] low, high;

    int[] parse(long n) {
        int[] ret = new int[LENGTH];
        for (int i = 0; i < LENGTH; ++ i) {
            ret[i] = (int)(n % 10);
            n /= 10;
        }
        return ret;
    }

    class Data {
        int left;
        long total;

        Data(int left, long total) {
            this.left = left;
            this.total = total;
        }
    }

    Data[][][] memory;

    int bool(boolean flag) {
        return flag ? 1 : 0;
    }

    Data count(int length, int isGreater, int isLess, int sum, int start) {
        if (length == 0) {
            if (isGreater == 1 && isLess == 1) {
                if (start + sum >= limit) {
                    return new Data(0, 1);
                }
                return new Data(start + sum, 0);
            }
            return new Data(start, 0);
        } else {
            if (isGreater == 1 && isLess == 1 && memory[length][sum][start] != null) {
                return memory[length][sum][start];
            }
            int left = start;
            long total = 0;
            for (int digit = 0; digit < 10; ++ digit) {
                if (digit < low[length - 1] && isGreater == 0) {
                    continue;
                }
                if (digit > high[length - 1] && isLess == 0) {
                    continue;
                }
                Data ret = count(length - 1, isGreater | bool(digit > low[length - 1]), isLess | bool(digit < high[length - 1]), sum + digit, left);
                left = ret.left;
                total += ret.total;
            }
            Data ret = new Data(left, total);
            if (isGreater == 1 && isLess == 1) {
                memory[length][sum][start] = ret;
            }
            return ret;
        }
    }

    public void run() {
        try {
            low = parse(reader.nextLong() - 1);
            high = parse(reader.nextLong() + 1);
            limit = reader.nextInt();
            memory = new Data[LENGTH + 1][LENGTH * 9][limit];
            writer.println(count(LENGTH, 0, 0, 0, 0).total);
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

    Long nextLong() throws IOException {
        return Long.parseLong(next());
    }
}
