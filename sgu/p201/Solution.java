// SGU 201 -- Non Absorbing DFA
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.math.BigInteger;

class Solver {
    int[] findDestination(int[] next) {
        int stateCount = next.length;
        int[] degree = new int[stateCount];
        for (int i = 0; i < stateCount; ++ i) {
            if (next[i] != -1) {
                degree[next[i]] ++;
            }
        }
        int[] queue = new int[stateCount];
        int tail = 0;
        for (int i = 0; i < stateCount; ++ i) {
            if (degree[i] == 0) {
                queue[tail ++] = i;
            }
        }
        int head = 0;
        while (head != tail) {
            int i = queue[head ++];
            if (next[i] != -1) {
                degree[next[i]] --;
                if (degree[next[i]] == 0) {
                    queue[tail ++] = next[i];
                }
            }
        }
        int[] destination = new int[stateCount];
        Arrays.fill(destination, -1);
        for (int k = tail - 1; k >= 0; -- k) {
            int i = queue[k];
            destination[i] = next[i] == -1? i: destination[next[i]];
        }
        return destination;
    }

    void run(InputReader in, PrintWriter out) throws IOException {
        String alphabet = in.nextToken();
        int alphabetSize = alphabet.length();
        int stateCount = in.nextInt();
        int initialState = in.nextInt() - 1;
        int terminalStateCount = in.nextInt();
        int[] terminalStates = new int[terminalStateCount];
        for (int i = 0; i < terminalStateCount; ++ i) {
            terminalStates[i] = in.nextInt() - 1;
        }
        int[][] phi = new int[stateCount][alphabetSize];
        for (int i = 0; i < stateCount; ++ i) {
            for (int j = 0; j < alphabetSize; ++ j) {
                phi[i][j] = in.nextInt() - 1;
            }
        }
        int[][] xi = new int[stateCount][alphabetSize];
        for (int i = 0; i < stateCount; ++ i) {
            for (int j = 0; j < alphabetSize; ++ j) {
                xi[i][j] = in.nextInt();
            }
        }
        int[][] transfer = new int[stateCount][alphabetSize];
        for (int token = 0; token < alphabetSize; ++ token) {
            int[] next = new int[stateCount];
            for (int i = 0; i < stateCount; ++ i) {
                next[i] = xi[i][token] == 0? -1: phi[i][token];
            }
            int[] destination = findDestination(next);
            for (int i = 0; i < stateCount; ++ i) {
                transfer[i][token] = destination[i] == -1? -1: phi[destination[i]][token];
            }
        }
        int length = in.nextInt();
        BigInteger[][] ways = new BigInteger[2][stateCount];
        for (int i = 0; i < stateCount; ++ i) {
            ways[0][i] = BigInteger.ZERO;
        }
        for (int i = 0; i < terminalStateCount; ++ i) {
            ways[0][terminalStates[i]] = BigInteger.ONE;
        }
        for (int i = 1; i <= length; ++ i) {
            for (int j = 0; j < stateCount; ++ j) {
                ways[i & 1][j] = BigInteger.ZERO;
                for (int token = 0; token < alphabetSize; ++ token) {
                    if (transfer[j][token] != -1) {
                        ways[i & 1][j] = ways[i & 1][j].add(ways[(i + 1) & 1][transfer[j][token]]);
                    }
                }
            }
        }
        out.println(ways[length & 1][initialState]);
        out.flush();
    }
}

public class Solution {
    public static void main(String args[]) {
        try {
            new Solver().run(new InputReader(System.in), new PrintWriter(System.out));
        } catch (IOException e) {
        }
    }
}

class InputReader {
    BufferedReader reader;
    StringTokenizer tokenizer;

    InputReader(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
        tokenizer = new StringTokenizer("");
    }

    String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
}
