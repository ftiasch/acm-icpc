// SGU 406 -- Goggle
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

    boolean match(ArrayList <Integer> sequence, ArrayList <Integer> query) {
        for (int q : query) {
            boolean found = false;
            for (int a : sequence) {
                if (a == q) {
                    found = true;
                }
                if (q < 0 && a == -q) {
                    return false;
                }
            }
            if (q > 0 && !found) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        try {
            int n = reader.nextInt();
            int m = reader.nextInt();
            ArrayList[] sequences = new ArrayList[n];
            for (int i = 0; i < n; ++ i) {
                sequences[i] = reader.nextList();
            }
            while (m > 0) {
                m --;
                ArrayList <Integer> query = reader.nextList();
                ArrayList <Integer> result = new ArrayList <Integer>();
                for (int i = 0; i < n; ++ i) {
                    if (match(sequences[i], query)) {
                        result.add(i);
                    }
                }
                writer.println(result.size());
                for (int i : result) {
                    writer.print(sequences[i].size());
                    for (Object a : sequences[i]) {
                        writer.print(String.format(" %d", (Integer)a));
                    }
                    writer.println();
                }
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

    ArrayList <Integer> nextList() throws IOException {
        ArrayList <Integer> ret = new ArrayList <Integer>();
        int size = nextInt();
        while (size > 0) {
            size --;
            ret.add(nextInt());
        }
        return ret;
    }
}
