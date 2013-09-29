import java.io.*;
import java.math.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class Main {
    int[] apples;
    ArrayList[] trees;

    class Tree {
        long init, speed;

        Tree(long init, long speed) {
            this.init = init;
            this.speed = speed;
        }
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    Tree solve(int p, int u) {
        ArrayList <Tree> children = new ArrayList <Tree>();
        for (Object obj : trees[u]) {
            int v = (Integer)obj;
            if (v != p) {
                children.add(solve(u, v));
            }
        }
        if (children.isEmpty()) {
            return new Tree(apples[u], 1);
        }
        long init = Long.MAX_VALUE;
        for (Tree tree : children) {
            init = Math.min(init, tree.init);
        }
        long speed = 1;
        for (Tree tree : children) {
            if (tree.speed / gcd(tree.speed, speed) >= 1 + init / speed) {
                init = 0;
                speed = 1;
                break;
            } else {
                speed = tree.speed / gcd(tree.speed, speed) * speed;
            }
        }
        init -= init % speed;
        int k = children.size();
        return new Tree(init * k, speed * k);
    }

    public void run() {
        try {
            int n = reader.nextInt();
            apples = new int[n];
            long drop = 0;
            for (int i = 0; i < n; ++ i) {
                apples[i] = reader.nextInt();
                drop += apples[i];
            }
            trees = new ArrayList[n];
            for (int i = 0; i < n; ++ i) {
                trees[i] = new ArrayList <Integer>();
            }
            for (int i = 0; i < n - 1; ++ i) {
                int a = reader.nextInt() - 1;
                int b = reader.nextInt() - 1;
                trees[a].add(b);
                trees[b].add(a);
            }
            drop -= solve(-1, 0).init;
            writer.println(drop);
        } catch (IOException ex) {
        }
        writer.close();
    }

    InputReader reader;
    PrintWriter writer;

    Main() {
        reader = new InputReader();
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
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
