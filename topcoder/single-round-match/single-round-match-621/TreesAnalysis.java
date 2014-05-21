import java.math.*;
import java.util.*;

public class TreesAnalysis {
    public long treeSimilarity(int[] tree1, int[] tree2) {
        Tree a = new Tree(tree1);
        Tree b = new Tree(tree2);
        return a.enumerate(b);
    }

    class Tree {
        Tree(int[] tree) {
            n = tree.length + 1;
            head = new int[n];
            Arrays.fill(head, -1);
            to = new int[n - 1 << 1];
            next = new int[n - 1 << 1];
            m = 0;
            for (int i = 0; i < n - 1; ++ i) {
                addEdge(i, tree[i]);
                addEdge(tree[i], i);
            }
        }

        void addEdge(int a, int b) {
            to[m] = b;
            next[m] = head[a];
            head[a] = m ++;
        }

        long enumerate(Tree other) {
            int[] type = new int[n];
            int[][] count = new int[n][2];
            long result = 0;
            for (int i = 0; i < n - 1; ++ i) {
                int a = to[i << 1];
                int b = to[i << 1 | 1];
                int total0 = label(type, a, b, 0);
                int total1 = label(type, b, a, 1);
                other.prepare(type, count, -1, 0);
                for (int j = 1; j < n; ++ j) {
                    result += square(Math.max(Math.max(count[j][0], count[j][1]), Math.max(total0 - count[j][0], total1 - count[j][1])));
                }
            }
            return result;
        }

        int square(int x) {
            return x * x;
        }

        int label(int[] type, int p, int u, int c) {
            type[u] = c;
            int result = 1;
            for (int it = head[u]; it != -1; it = next[it]) {
                if (to[it] != p) {
                    result += label(type, u, to[it], c);
                }
            }
            return result;
        }

        void prepare(int[] type, int[][] count, int p, int u) {
            count[u][0] = count[u][1] = 0;
            count[u][type[u]] = 1;
            for (int it = head[u]; it != -1; it = next[it]) {
                if (to[it] != p) {
                    prepare(type, count, u, to[it]);
                    count[u][0] += count[to[it]][0];
                    count[u][1] += count[to[it]][1];
                }
            }
        }

        int   n, m;
        int[] head, to, next;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TreesAnalysisHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TreesAnalysisHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TreesAnalysisHarness {
    public static void run_test(int casenum) {
        if (casenum != -1) {
            if (runTestCase(casenum) == -1)
                System.err.println("Illegal input! Test case " + casenum + " does not exist.");
            return;
        }

        int correct = 0, total = 0;
        for (int i=0;; ++i) {
            int x = runTestCase(i);
            if (x == -1) {
                if (i >= 100) break;
                continue;
            }
            correct += x;
            ++total;
        }

        if (total == 0) {
            System.err.println("No test cases run.");
        } else if (correct < total) {
            System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
        } else {
            System.err.println("All " + total + " tests passed!");
        }
    }

    static boolean compareOutput(long expected, long result) { return expected == result; }
    static String formatResult(long res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, long expected, long received) {
        System.err.print("Example " + casenum + "... ");
        if (compareOutput(expected, received)) {
            System.err.println("PASSED");
            return 1;
        } else {
            System.err.println("FAILED");
            System.err.println("    Expected: " + formatResult(expected));
            System.err.println("    Received: " + formatResult(received));
            return 0;
        }
    }

    static int runTestCase(int casenum__) {
        switch(casenum__) {
        case 0: {
            int[] tree1               = {1};
            int[] tree2               = {1};
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new TreesAnalysis().treeSimilarity(tree1, tree2));
        }
        case 1: {
            int[] tree1               = {2, 4, 1, 0};
            int[] tree2               = {1, 4, 4, 4};
            long expected__           = 111;

            return verifyCase(casenum__, expected__, new TreesAnalysis().treeSimilarity(tree1, tree2));
        }
        case 2: {
            int[] tree1               = {1, 2, 3, 4};
            int[] tree2               = {1, 2, 3, 4} ;
            long expected__           = 128;

            return verifyCase(casenum__, expected__, new TreesAnalysis().treeSimilarity(tree1, tree2));
        }
        case 3: {
            int[] tree1               = {2, 3, 4, 4, 5, 8, 5, 6, 10, 8};
            int[] tree2               = {9, 0, 1, 0, 3, 0, 5, 0, 7, 10};
            long expected__           = 6306;

            return verifyCase(casenum__, expected__, new TreesAnalysis().treeSimilarity(tree1, tree2));
        }
        case 4: {
            int[] tree1               = {222, 261, 167, 133, 174, 150, 165, 311, 208, 268, 111, 222, 154, 277, 282, 201, 46, 124, 194, 331, 4, 216, 111, 275, 72, 322, 137, 216, 241, 48, 72, 101, 232, 165, 151, 263, 139, 16, 122, 140, 84, 135, 314, 106, 309, 126, 102, 151, 208, 27, 242, 93, 83, 314, 136, 77, 82, 215, 16, 232, 286, 156, 294, 38, 67, 204, 206, 137, 174, 282, 188, 143, 84, 279, 236, 136, 158, 10, 65, 332, 122, 44, 329, 62, 174, 67, 102, 216, 245, 296, 287, 307, 93, 197, 169, 268, 266, 294, 157, 277, 95, 68, 214, 135, 211, 127, 82, 108, 212, 161, 243, 212, 207, 119, 119, 158, 97, 290, 21, 217, 230, 85, 171, 13, 138, 294, 304, 204, 318, 115, 70, 210, 195, 223, 37, 164, 149, 3, 164, 328, 316, 108, 330, 48, 38, 324, 222, 193, 50, 41, 184, 93, 148, 41, 151, 139, 106, 301, 264, 80, 249, 110, 244, 109, 212, 223, 279, 330, 67, 27, 301, 165, 236, 194, 3, 157, 1, 217, 311, 87, 105, 4, 286, 37, 6, 31, 111, 66, 230, 227, 244, 322, 196, 65, 69, 305, 112, 133, 231, 68, 153, 206, 309, 248, 329, 58, 69, 69, 328, 0, 29, 233, 243, 305, 167, 80, 65, 194, 190, 179, 142, 196, 324, 206, 134, 50, 272, 261, 10, 147, 329, 322, 14, 142, 169, 21, 296, 284, 241, 55, 304, 150, 166, 230, 167, 304, 87, 156, 156, 97, 274, 324, 196, 101, 82, 106, 260, 242, 233, 207, 305, 10, 166, 53, 18, 154, 233, 217, 296, 263, 168, 138, 30, 115, 135, 188, 98, 309, 292, 204, 150, 210, 332, 330, 166, 96, 70, 24, 229, 215, 201, 285, 40, 287, 142, 68, 133, 208, 268, 161, 310, 41, 203, 73, 275, 184, 163, 227, 89, 110, 328, 108, 112, 125, 164, 127, 179, 267, 221, 49, 139, 1, 84, 136, 38, 6, 70, 246, 243, 3, 188, 297};
            int[] tree2               = {174, 262, 195, 288, 157, 278, 36, 133, 230, 273, 222, 138, 97, 23, 189, 141, 296, 55, 45, 301, 81, 199, 188, 289, 187, 164, 113, 58, 138, 300, 289, 282, 329, 91, 130, 178, 92, 143, 48, 81, 311, 133, 151, 286, 171, 196, 199, 80, 83, 121, 65, 151, 277, 136, 49, 111, 58, 36, 259, 14, 31, 9, 136, 181, 122, 324, 249, 114, 9, 37, 259, 242, 165, 174, 34, 36, 298, 92, 301, 237, 178, 82, 65, 295, 110, 311, 274, 235, 68, 56, 259, 180, 195, 52, 110, 68, 140, 71, 52, 296, 290, 115, 213, 82, 209, 209, 74, 178, 302, 131, 99, 205, 296, 309, 288, 180, 329, 71, 143, 58, 152, 273, 196, 7, 169, 88, 231, 331, 213, 181, 80, 249, 170, 246, 16, 127, 75, 276, 332, 174, 21, 180, 163, 78, 242, 312, 295, 199, 89, 142, 85, 195, 115, 119, 95, 94, 279, 290, 3, 33, 93, 284, 20, 47, 47, 78, 331, 271, 113, 179, 249, 331, 92, 324, 9, 71, 232, 46, 28, 289, 80, 28, 80, 134, 20, 280, 277, 48, 205, 107, 52, 320, 4, 191, 160, 182, 189, 227, 295, 115, 54, 195, 78, 292, 189, 273, 301, 69, 305, 36, 222, 167, 326, 106, 48, 45, 74, 61, 181, 311, 292, 270, 201, 34, 314, 218, 214, 92, 269, 18, 37, 151, 142, 209, 11, 227, 327, 198, 28, 272, 152, 22, 47, 143, 332, 253, 273, 35, 78, 130, 295, 223, 181, 329, 18, 238, 300, 186, 274, 99, 300, 322, 41, 185, 311, 288, 198, 2, 37, 83, 238, 133, 122, 178, 107, 106, 66, 238, 69, 90, 38, 109, 246, 278, 288, 250, 321, 269, 130, 28, 115, 122, 33, 185, 275, 99, 130, 99, 152, 268, 133, 249, 180, 30, 210, 201, 324, 29, 290, 143, 3, 269, 68, 106, 230, 1, 269, 29, 120, 259, 324, 328, 23, 243, 9, 61, 14, 118, 199, 146, 237, 14};
            long expected__           = 11478648052L;

            return verifyCase(casenum__, expected__, new TreesAnalysis().treeSimilarity(tree1, tree2));
        }

        // custom cases

        case 5: {
            int[] tree1               = new int[3999];
            for (int i = 0; i < 3999; ++ i) {
                tree1[i] = i + 1;
            }
            int[] tree2               = tree1;
            long expected__           = -1;

            return verifyCase(casenum__, expected__, new TreesAnalysis().treeSimilarity(tree1, tree2));
        }
/*      case 6: {
            int[] tree1               = ;
            int[] tree2               = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new TreesAnalysis().treeSimilarity(tree1, tree2));
        }*/
/*      case 7: {
            int[] tree1               = ;
            int[] tree2               = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new TreesAnalysis().treeSimilarity(tree1, tree2));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
