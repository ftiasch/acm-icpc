// SGU 122 -- The book
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Solution {
    public static void main(String args[]) {
        try {
            new Solver().solve();
        } catch (IOException e) {
        }
    }
}

class Solver {
    int n, m;
    int[] degree;
    int[][] adjacent;
    boolean[][] graph;

    void addCycle(int i) {
        m ++;
        for (int j = 0; j < n; ++ j) {
            if (graph[i][j]) {
                degree[j] --;
            }
        }
    }

    int expand(int p, int t) {
        for (int i = 0; i < n; ++ i) {
            if (graph[p][i] && adjacent[i][0] == -1 && adjacent[i][1] == -1) {
                addCycle(i);
                adjacent[p][t] = i;
                adjacent[i][t ^ 1] = p;
                return expand(i, t);
            }
        }
        return p;
    }

    void join(int x, int y) {
        adjacent[x][1] = y;
        adjacent[y][0] = x;
    }

    void reverse(int a, int b) {
        ArrayList <Integer> sequences = new ArrayList <Integer>();
        for (int p = a; p != b; p = adjacent[p][1]) {
            sequences.add(p);
        }
        sequences.add(b);
        for (int p : sequences) {
            int tmp = adjacent[p][0];
            adjacent[p][0] = adjacent[p][1];
            adjacent[p][1] = tmp;
        }
    }

    void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        graph = new boolean[n][n];
        degree = new int[n];
        adjacent = new int[n][2];
        for (int i = 0; i < n; ++ i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            while (tokenizer.hasMoreTokens()) {
                int j = Integer.parseInt(tokenizer.nextToken()) - 1;
                graph[i][j] = true;
            }
            graph[i][i] = false;
            for (int j = 0; j < n; ++ j) {
                if (graph[i][j]) {
                    degree[i] ++;
                }
            }
            adjacent[i][0] = adjacent[i][1] = -1;
        }
        m = 0;
        addCycle(0);
        adjacent[0][0] = adjacent[0][1] = 0;
//for (int i = 0; i < n; ++ i) System.out.printf("%d: %d, ", i, degree[i]); System.out.printf("\n");
        while (m < n) {
            int y = 0;
            while (degree[y] == 0) {
                y = adjacent[y][1];
            }
            int x = adjacent[y][1];
            adjacent[y][1] = adjacent[x][0] = -1;
            x = expand(x, 0);
            y = expand(y, 1);
            if (graph[x][y]) {
                join(y, x);
            } else {
                int p = adjacent[x][1];
                while (!(graph[p][y] && graph[adjacent[p][1]][x])) {
                    p = adjacent[p][1];
                }
                int q = adjacent[p][1];
                adjacent[p][1] = adjacent[q][0] = -1;
                reverse(x, p);
                join(x, q);
                join(y, p);
            }
        }
        int p = 0;
        do {
            System.out.printf("%d ", p + 1);
            p = adjacent[p][1];
        } while (p != 0);
        System.out.printf("%d\n", 1);
    }
}
