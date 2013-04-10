// SGU 327 -- Yet Another Palindrome
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

    String reverse(String string) {
        return new StringBuffer(string).reverse().toString();
    }

    public void run() {
        try {
            int n = reader.nextInt();
            String[] words = new String[n << 1];
            for (int i = 0; i < n; ++ i) {
                words[i << 1] = reader.next();
                words[(i << 1) + 1] = reverse(words[i << 1]);
            }
            boolean[] covered = new boolean[n];
            for (int i = 0; i < n << 1; ++ i) {
                for (int j = 0; j < n << 1; ++ j) {
                    if ((i >> 1) != (j >> 1) && words[i].contains(words[j]) && (i < j || !words[i].equals(words[j]))) {
                        covered[j >> 1] = true;
                    }
                }
            }
            String solution = "";
            for (int type = 0; type < 2; ++ type) {
                int[][] overlap = new int[n << 1][n << 1];
                for (int i = 0; i < n << 1; ++ i) {
                    for (int j = 0; j < n << 1; ++ j) {
                        if (i == j) {
                            for (int k = 0; k < words[i].length(); ++ k) {
                                if (words[i].substring(k).startsWith(reverse(words[i].substring(0, k + type)))) {
                                    overlap[i][i] = words[i].length() - k;
                                }
                            }
                        } else {
                            overlap[i][j] = words[j].length();
                            for (int k = 1; k <= Math.min(words[i].length(), words[j].length()); ++ k) {
                                if (words[i].substring(words[i].length() - k).equals(words[j].substring(0, k))) {
                                    overlap[i][j] = words[j].length() - k;
                                }
                            }
                        }
                    } 
                }
                int[][] minimum = new int[1 << n][n << 1];
                for (int[] row : minimum) {
                    Arrays.fill(row, Integer.MAX_VALUE);
                }
                int[][] back = new int[1 << n][n << 1];
                for (int i = 0; i < n << 1; ++ i) {
                    if (overlap[i][i] < minimum[1 << (i >> 1)][i]) {
                        minimum[1 << (i >> 1)][i] = overlap[i][i];
                        back[1 << (i >> 1)][i] = i;
                    }
                }
                for (int mask = 0; mask < 1 << n; ++ mask) {
                    for (int i = 0; i < n << 1; ++ i) {
                        if ((mask >> (i >> 1) & 1) == 0) {
                            continue;
                        }
                        for (int j = 0; j < n << 1; ++ j) {
                            if ((mask >> (j >> 1) & 1) == 1) {
                                continue;
                            }
                            if (minimum[mask][i] + overlap[i][j] < minimum[mask | (1 << (j >> 1))][j]) {
                                minimum[mask | (1 << (j >> 1))][j] = minimum[mask][i] + overlap[i][j];
                                back[mask | (1 << (j >> 1))][j] = i;
                            }
                        }
                    }
                }
                int all = 0;
                for (int i = 0; i < n; ++ i) {
                    if (!covered[i]) {
                        all |= 1 << i;
                    }
                }
                int answer = Integer.MAX_VALUE;
                for (int i = 0; i < n << 1; ++ i) {
                    answer = Math.min(answer, minimum[all][i]);
                }
                int j = 0;
                while (minimum[all][j] != answer) {
                    j ++;
                }
                String text = "";
                while (all > 0) {
                    int i = back[all][j];
                    for (int k = 1; k <= overlap[i][j]; ++ k) {
                        text += words[j].charAt(words[j].length() - k);
                    }
                    all ^= 1 << (j >> 1);
                    j = i;
                }
                for (int i = text.length() - 1 - type; i >= 0; -- i) {
                    text += text.charAt(i);
                }
                if (solution.equals("") || text.length() < solution.length()) {
                    solution = text;
                }
            }
            writer.println(solution);
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
