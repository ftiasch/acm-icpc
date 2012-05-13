// Codeforces Round #106
// Problem E -- Martian Strings
import java.io.*;
import java.util.*;

public class Main implements Runnable {
    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintStream writer;

    Main() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
        writer = new PrintStream(new BufferedOutputStream(System.out));
    }

    String reverse(String string) {
        return new StringBuffer(string).reverse().toString();
    }

    class Node {
        int firstOccurance;
        Node fail, children[];

        Node() {
            firstOccurance = Integer.MAX_VALUE;
            fail = null;
            children = new Node[26];
        }
    }

    int[][] prepare(String text, String patterns[]) {
        Node root = new Node();
        for (String pattern : patterns) {
            Node pointer = root;
            for (int i = 0; i < pattern.length(); ++ i) {
                int token = pattern.charAt(i) - 'A';
                if (pointer.children[token] == null) {
                    pointer.children[token] = new Node();
                }
                pointer = pointer.children[token];
            }
        }
        Queue <Node> queue = new LinkedList <Node>();
        ArrayList <Node> order = new ArrayList <Node>();
        for (int token = 0; token < 26; ++ token) {
            if (root.children[token] != null) {
                root.children[token].fail = root;
                queue.offer(root.children[token]);
                order.add(root.children[token]);
            }
        }
        while (!queue.isEmpty()) {
            Node u = queue.poll();
            for (int token = 0; token < 26; ++ token) {
                if (u.children[token] != null) {
                    u.children[token].fail = root;
                    Node p = u;
                    while (p != root) {
                        p = p.fail;
                        if (p.children[token] != null) {
                            u.children[token].fail = p.children[token];
                            break;
                        }
                    }
                    queue.offer(u.children[token]);
                    order.add(u.children[token]);
                }
            }
        }
        {
            Node pointer = root;
            for (int i = 0; i < text.length(); ++ i) {
                int token = text.charAt(i) - 'A';
                if (pointer.children[token] != null) {
                    pointer = pointer.children[token];
                } else {
                    while (pointer != root) {
                        pointer = pointer.fail;
                        if (pointer.children[token] != null) {
                            pointer = pointer.children[token];
                            break;
                        }
                    }
                }
                pointer.firstOccurance = Math.min(pointer.firstOccurance, i);
            }
        }
        for (int i = order.size() - 1; i >= 0; -- i) {
            Node u = order.get(i);
            u.fail.firstOccurance = Math.min(u.fail.firstOccurance, u.firstOccurance);
        }
        int n = patterns.length;
        int result[][] = new int[n][];
        for (int i = 0; i < n; ++ i) {
            int m = patterns[i].length();
            result[i] = new int[m + 1];
            result[i][0] = -1;
            Node pointer = root;
            for (int j = 0; j < m; ++ j) {
                int token = patterns[i].charAt(j) - 'A';
                pointer = pointer.children[token];
                result[i][j + 1] = pointer.firstOccurance;
            }
        }
        return result;
    }

    public void run() {
        try {
            String text = nextToken();
            String reversedText = reverse(text);
            int n = nextInt();
            String patterns[] = new String[n];
            String reversedPatterns[] = new String[n];
            for (int i = 0; i < n; ++ i) {
                patterns[i] = nextToken();
                reversedPatterns[i] = reverse(patterns[i]);
            }
            int prefixOccurance[][] = prepare(text, patterns);
            int suffixOccurance[][] = prepare(reversedText, reversedPatterns);
//for (int i = 0; i < n; ++ i) {
//    int m = patterns[i].length();
//    for (int j = 0; j <= m; ++ j) {
//        System.out.printf("%d, ", prefixOccurance[i][j]);
//    }
//    System.out.println("");
//}
            int result = 0;
            for (int i = 0; i < n; ++ i) {
                int m = patterns[i].length();
                if (m >= 2) {
                    boolean found = false;
                    for (int j = 0; j <= m; ++ j) {
                        if (prefixOccurance[i][j] != Integer.MAX_VALUE && suffixOccurance[i][m - j] != Integer.MAX_VALUE && prefixOccurance[i][j] + 1 + suffixOccurance[i][m - j] + 1 <= text.length()) {
                            found = true;
                        }
                    }
                    if (found) {
                        result ++;
                    }
                }
            }
            System.out.println(result);
        } catch (IOException e) {
        }
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

    public static void main(String args[]) {
        new Thread(new Main()).run();
    }
}
