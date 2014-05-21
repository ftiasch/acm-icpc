import java.math.*;
import java.util.*;

public class ThreeColorability {
    int n, m;

    boolean check(String[] cells) {
        int[] color = new int[n + m];
        Arrays.fill(color, -1);
        for (int i = 0; i < n + m; ++ i) {
            if (color[i] == -1) {
                color[i] = 0;
                Queue <Integer> queue = new LinkedList <Integer>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int v = 0; v < n + m; ++ v) {
                        if ((u < n) != (v < n)) {
                            char token = u < n ? cells[u].charAt(v - n) : cells[v].charAt(u - n);
                            if (token != '?') {
                                int value = token == 'N' ? 0 : 1;
                                if (color[v] == -1) {
                                    color[v] = color[u] ^ value;
                                    queue.offer(v);
                                } else if ((color[u] ^ color[v]) != value) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public String[] lexSmallest(String[] cells) {
        n = cells.length;
        m = cells[0].length();
        if (check(cells)) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    if (cells[i].charAt(j) == '?') {
                        cells[i] = cells[i].substring(0, j) + 'N' + cells[i].substring(j + 1);
                        if (!check(cells)) {
                            cells[i] = cells[i].substring(0, j) + 'Z' + cells[i].substring(j + 1);
                        }
                    }
                }
            }
            return cells;
        }
        return new String[]{};
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ThreeColorabilityHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ThreeColorabilityHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ThreeColorabilityHarness {
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

    static boolean compareOutput(String[] expected, String[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (!expected[i].equals(result[i])) return false; return true; }

    static String formatResult(String[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" \"%s\"", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, String[] expected, String[] received) {
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
            String[] cells            = {"Z"};
            String[] expected__       = {"Z" };

            return verifyCase(casenum__, expected__, new ThreeColorability().lexSmallest(cells));
        }
        case 1: {
            String[] cells            = {"??", "?N"};
            String[] expected__       = {"NN", "NN" };

            return verifyCase(casenum__, expected__, new ThreeColorability().lexSmallest(cells));
        }
        case 2: {
            String[] cells            = {"ZZZ", "ZNZ"};
            String[] expected__       = { };

            return verifyCase(casenum__, expected__, new ThreeColorability().lexSmallest(cells));
        }
        case 3: {
            String[] cells            = {"N?N??NN","??ZN??Z","NN???Z?","ZZZ?Z??","Z???NN?","N?????N","ZZ?N?NN"};
            String[] expected__       = { };

            return verifyCase(casenum__, expected__, new ThreeColorability().lexSmallest(cells));
        }
        case 4: {
            String[] cells            = {"ZZZZ","ZZZZ","ZZZZ"};
            String[] expected__       = {"ZZZZ", "ZZZZ", "ZZZZ" };

            return verifyCase(casenum__, expected__, new ThreeColorability().lexSmallest(cells));
        }

        // custom cases

/*      case 5: {
            String[] cells            = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new ThreeColorability().lexSmallest(cells));
        }*/
/*      case 6: {
            String[] cells            = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new ThreeColorability().lexSmallest(cells));
        }*/
/*      case 7: {
            String[] cells            = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new ThreeColorability().lexSmallest(cells));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
