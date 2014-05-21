import java.math.*;
import java.util.*;

public class StrangeCountry {
    int parent[], nodeCount[], edgeCount[];

    int findParent(int i) {
        if (parent[i] != i) {
            parent[i] = findParent(parent[i]);
        }
        return parent[i];
    }

    int merge(int i, int j) {
        i = findParent(i);
        j = findParent(j);
        if (i != j) {
            nodeCount[i] += nodeCount[j];
            edgeCount[i] += edgeCount[j];
            parent[j] = i;
        }
        return i;
    }

    public int transform(String[] graph) {
        int n = graph.length;
        if (n == 1) {
            return 0;
        }
        parent = new int[n];
        nodeCount = new int[n];
        edgeCount = new int[n];
        for (int i = 0; i < n; ++ i) {
            parent[i] = i;
            nodeCount[i] = 1;
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = i + 1; j < n; ++ j) {
                if (graph[i].charAt(j) == 'Y') {
                    edgeCount[merge(i, j)] ++;
                }
            }
        }
        int result = 0;
        int componentCount = 0;
        for (int i = 0; i < n; ++ i) {
            if (parent[i] == i) {
                componentCount ++;
                if (nodeCount[i] == 1) {
                    return -1;
                }
            }
        }
        while (componentCount > 1) {
            int i = 0;
            while (i < n && (parent[i] != i || edgeCount[i] < nodeCount[i])) {
                i ++;
            }
            if (i == n) {
                return -1;
            }
            int j = 0;
            while (j < n && (parent[j] != j || j == i)) {
                j ++;
            }
            merge(i, j);
            componentCount --;
            result ++;
        }
        return result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            StrangeCountryHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                StrangeCountryHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class StrangeCountryHarness {
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
            String[] g                = {"NY",
 "YN"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new StrangeCountry().transform(g));
        }
        case 1: {
            String[] g                = {"NYYNN",
 "YNYNN",
 "YYNNN",
 "NNNNY",
 "NNNYN"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new StrangeCountry().transform(g));
        }
        case 2: {
            String[] g                = {"NYYNNNN",
 "YNYNNNN",
 "YYNNNNN",
 "NNNNYYN",
 "NNNYNYY",
 "NNNYYNY",
 "NNNNYYN"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new StrangeCountry().transform(g));
        }
        case 3: {
            String[] g                = {"NYNYNNNNNNNN",
 "YNYNNNNNNNNN",
 "NYNYYNNNNNNN",
 "YNYNNNNNNNNN",
 "NNYNNYYNNNNN",
 "NNNNYNYNNNNN",
 "NNNNYYNNNNNN",
 "NNNNNNNNYYNN",
 "NNNNNNNYNYNN",
 "NNNNNNNYYNNN",
 "NNNNNNNNNNNY",
 "NNNNNNNNNNYN"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new StrangeCountry().transform(g));
        }
        case 4: {
            String[] g                = {"NYNNNN",
 "YNYNNN",
 "NYNYNN",
 "NNYNNN",
 "NNNNNY",
 "NNNNYN"};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new StrangeCountry().transform(g));
        }

        // custom cases

/*      case 5: {
            String[] g                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StrangeCountry().transform(g));
        }*/
/*      case 6: {
            String[] g                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StrangeCountry().transform(g));
        }*/
/*      case 7: {
            String[] g                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StrangeCountry().transform(g));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
