// Codeforces Beta Round #56
// Problem C -- Mushroom Strife
import java.io.*;
import java.math.*;
import java.util.*;

public class Solution {
    int n;
    int[] number;
    int[][] gcd, lcm;

    ArrayList <Integer> factorize(int n) {
        ArrayList <Integer> divisors = new ArrayList <Integer>();
        for (int i = 1; i * i <= n; ++ i) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }
        return divisors;
    }

    ArrayList <Integer> nodes;

    void label(int u) {
        nodes.add(u);
        for (int v = 0; v < n; ++ v) {
            if (gcd[u][v] != 0 && number[v] == 0) {
                number[v] = Math.max(lcm[u][v] / number[u] * gcd[u][v], 1);
                label(v);
            }
        }
    }

    int getGCD(int a, int b) {
        return b == 0 ? a : getGCD(b, a % b);
    }
    
    int getLCM(int a, int b) {
        return a / getGCD(a, b) * b;
    }

    public void run() {
        try {
            n = reader.nextInt();
            int m = reader.nextInt();
            gcd = new int[n][n];
            lcm = new int[n][n];
            for (int i = 0; i < m; ++ i) {
                int a = reader.nextInt() - 1;
                int b = reader.nextInt() - 1;
                gcd[a][b] = gcd[b][a] = reader.nextInt();
                lcm[a][b] = lcm[b][a] = reader.nextInt();
            }
            number = new int[n];
            nodes = new ArrayList <Integer>();
            for (int u = 0; u < n; ++ u) {
                if (number[u] == 0) {
                    ArrayList <Integer> divisors = null;
                    for (int v = 0; v < n; ++ v) {
                        if (gcd[u][v] != 0) {
                            divisors = factorize(lcm[u][v]);
                            break;
                        }
                    }
                    if (divisors == null) {
                        number[u] = 1;
                    } else {
                        boolean found = false;
                        for (int divisor : divisors) {
                            number[u] = divisor;
                            label(u);
                            boolean valid = true;
                            for (int i : nodes) {
                                for (int j : nodes) {
                                    if (gcd[i][j] != 0) {
                                        valid &= getGCD(number[i], number[j]) == gcd[i][j] && getLCM(number[i], number[j]) == lcm[i][j];
                                    }
                                }
                            }
                            if (valid) {
                                found = true;
                                break;
                            }
                            for (int node : nodes) {
                                number[node] = 0;
                            }
                            nodes.clear();
                        }
                        if (!found) {
                            writer.println("NO");
                            throw new RuntimeException();
                        }
                    }
                }
            }
            writer.println("YES");
            for (int i = 0; i < n; ++ i) {
                writer.print(number[i] + " ");
            }
            writer.println();
        } catch (Exception ex) {
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
}
