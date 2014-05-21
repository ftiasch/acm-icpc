import java.math.*;
import java.util.*;

public class History {
    public String verifyClaims(String[] dynasties, String[] parts, String[] queries) {
        int n = dynasties.length;
        int[][] years = new int[n][];
        for (int i = 0; i < n; ++ i) {
            StringTokenizer tokenizer = new StringTokenizer(dynasties[i]);
            years[i] = new int[tokenizer.countTokens()];
            for (int j = 0; j < years[i].length; ++ j) {
                years[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        String[] battles = concat(parts).split(" ");
        battles = Arrays.copyOf(battles, battles.length + 1);
        String result = "";
        for (String query : queries) {
            battles[battles.length - 1] = query;
            result += String.format("%c", check(years, battles) ? 'Y' : 'N');
        }
        return result;
    }

    boolean check(int[][] years, String[] battles) {
        int n = years.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
        for (String battle : battles) {
            int a = battle.charAt(0) - 'A';
            int x = battle.charAt(1) - '0';
            int b = battle.charAt(3) - 'A';
            int y = battle.charAt(4) - '0';
            addEdge(graph, b, a, years[b][y + 1] - years[a][x] - 1);
            addEdge(graph, a, b, years[a][x + 1] - years[b][y] - 1);
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    if (graph[i][k] < Integer.MAX_VALUE && graph[k][j] < Integer.MAX_VALUE) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }
        for (int i = 0; i < n; ++ i) {
            if (graph[i][i] < 0) {
                return false;
            }
        }
        return true;
    }

    String concat(String[] parts) {
        StringBuffer buffer = new StringBuffer();
        for (String part : parts) {
            buffer.append(part);
        }
        return buffer.toString();
    }

    void addEdge(int[][] graph, int a, int b, int c) {
        graph[a][b] = Math.min(graph[a][b], c);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            HistoryHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                HistoryHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class HistoryHarness {
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

    static boolean compareOutput(String expected, String result) { return expected.equals(result); }
    static String formatResult(String res) {
        return String.format("\"%s\"", res);
    }

    static int verifyCase(int casenum, String expected, String received) {
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
            String[] dynasties        = {"1 2 4",
 "1 2 3"};
            String[] battles          = {"A1-B0"};
            String[] queries          = {"A0-B0",
 "A0-B1",
 "A1-B0",
 "A1-B1"};
            String expected__         = "NNYY";

            return verifyCase(casenum__, expected__, new History().verifyClaims(dynasties, battles, queries));
        }
        case 1: {
            String[] dynasties        = {"1000 2000 3000 10000",
 "600 650 2000",
 "1 1001 20001"};
            String[] battles          = {"B1-C0 A0-B0 A2-C1 B1-C1"};
            String[] queries          = {"A0-B1",
 "A1-B1",
 "A2-B1",
 "C0-A0",
 "B0-A2",
 "C1-B0"};
            String expected__         = "YYYYNN";

            return verifyCase(casenum__, expected__, new History().verifyClaims(dynasties, battles, queries));
        }
        case 2: {
            String[] dynasties        = {"1 4 5",
 "10 13 17"};
            String[] battles          = {"A0-B0 A0-B0 B0-A0"};
            String[] queries          = {"A1-B0",
 "A0-B1",
 "A1-B1"};
            String expected__         = "YYY";

            return verifyCase(casenum__, expected__, new History().verifyClaims(dynasties, battles, queries));
        }
        case 3: {
            String[] dynasties        = {"1 5 6",
 "1 2 5"};
            String[] battles          = {"A0",
 "-B0 A",
 "1-B1"};
            String[] queries          = {"A0-B0",
 "A1-B0",
 "A0-B1",
 "A1-B1"};
            String expected__         = "YNYY";

            return verifyCase(casenum__, expected__, new History().verifyClaims(dynasties, battles, queries));
        }
        case 4: {
            String[] dynasties        = {"2294 7344","366 384 449 965 1307 1415","307 473 648 688 1097","1145 1411 1569 2606","87 188 551 598 947 998 1917 1942"}
;
            String[] battles          = {"A0-B4 B4-E2 B3-E2 D2-E4 A0-E4 B1-C3 A0-E3 A0-E6 D0","-E2 B2-E1 B4-E3 B4-D0 D0-E3 A0-D1 B2-C3 B1-C3 B4-E","3 D0-E1 B3-D0 B3-E2"}
;
            String[] queries          = {"A0-C2","E6-C2","A0-E4","B3-C1","C0-D2","B0-C1","D1-C3","C3-D0","C1-E3","D1-A0"};
            String expected__         = "YNYNNYNNNY";

            return verifyCase(casenum__, expected__, new History().verifyClaims(dynasties, battles, queries));
        }

        // custom cases

/*      case 5: {
            String[] dynasties        = ;
            String[] battles          = ;
            String[] queries          = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new History().verifyClaims(dynasties, battles, queries));
        }*/
/*      case 6: {
            String[] dynasties        = ;
            String[] battles          = ;
            String[] queries          = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new History().verifyClaims(dynasties, battles, queries));
        }*/
/*      case 7: {
            String[] dynasties        = ;
            String[] battles          = ;
            String[] queries          = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new History().verifyClaims(dynasties, battles, queries));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
