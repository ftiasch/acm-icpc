import java.math.*;
import java.util.*;

public class BoardPainting {
    int n, m;
    int[] match;
    boolean[] visit;
    ArrayList[] graph;

    boolean find(int i) {
        if (visit[i]) {
            return false;
        }
        visit[i] = true;
        for (Object iter : graph[i]) {
            int j = (Integer)iter;
            if (match[j] == -1 || find(match[j])) {
                match[j] = i;
                return true;
            }
        }
        return false;
    }

    int getID(int x, int y) {
        return x * m + y;
    }

    public int minimalSteps(String[] target) {
        n = target.length;
        m = target[0].length();
        graph = new ArrayList[n * m];
        for (int i = 0; i < n * m; ++ i) {
            graph[i] = new ArrayList <Integer>();
        }
        int answer = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (target[i].charAt(j) == '#') {
                    answer ++;
                    int up = i >= 1 && target[i - 1].charAt(j) == '#' ? getID(i - 1, j) : -1;
                    int down = i + 1 < n && target[i + 1].charAt(j) == '#' ? getID(i, j) : -1;
                    int left = j >= 1 && target[i].charAt(j - 1) == '#' ? getID(i, j - 1) : -1;
                    int right = j + 1 < m && target[i].charAt(j + 1) == '#' ? getID(i, j) : -1;
                    if (down != -1) {
                        answer --;
                    }
                    if (right != -1) {
                        answer --;
                    }
                    if (up != -1) {
                        if (left != -1) {
                            graph[up].add(left);
                        }
                        if (right != -1) {
                            graph[up].add(right);
                        }
                    }
                    if (down != -1) {
                        if (left != -1) {
                            graph[down].add(left);
                        }
                        if (right != -1) {
                            graph[down].add(right);
                        }
                    }
                }
            }
        }
        match = new int[n * m];
        visit = new boolean[n * m];
        Arrays.fill(match, -1);
        for (int i = 0; i < n * m; ++ i) {
            if (find(i)) {
                answer ++;
                Arrays.fill(visit, false);
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BoardPaintingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BoardPaintingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BoardPaintingHarness {
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

    static boolean compareOutput(int expected, int result) { return expected == result; }
    static String formatResult(int res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, int expected, int received) {
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
            String[] target           = {"#####"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }
        case 1: {
            String[] target           = {"#####",
 ".....",
 "#####",
 ".....",
 "#####"};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }
        case 2: {
            String[] target           = {"..#..",
 "..#..",
 "#####",
 "..#..",
 "..#.."};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }
        case 3: {
            String[] target           = {"#####",
 "..#..",
 "#####",
 "..#..",
 "#####"};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }
        case 4: {
            String[] target           = {".#.#.",
 "#####",
 ".#.#.",
 "#####",
 ".#.#."};
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }
        case 5: {
            String[] target           = {".##.####.####.#########.##..",
 "##.#.####################.##",
 ".##.###.##.###.###.###.###..",
 "#..###..#########..###.##.##",
 "####..#######.#.#####.######",
 "##.######.#..#.#############",
 "##.######.###########.######",
 "#######.#######.#..###.#.###",
 "#####..#######.#####.#.###.#",
 "#..#################.#.####.",
 "##.######..#.#####.######.##",
 "..#.#############.#.##....#.",
 "....##..##..#.#####.#.###.##",
 "##.#########...#..#.#.######",
 "##.#..###########..#..####.#",
 "#.####.###.########.########",
 "#####.#########.##.##.######",
 ".##.####..###.###...######.#"};
            int expected__            = 88;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }
        case 6: {
            String[] target           = {"...................."};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }

        // custom cases

/*      case 7: {
            String[] target           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }*/
/*      case 8: {
            String[] target           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }*/
/*      case 9: {
            String[] target           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BoardPainting().minimalSteps(target));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
