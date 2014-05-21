import java.math.*;
import java.util.*;

public class NewArenaPassword {
    int[] parent;

    int find(int i) {
        if (parent[i] == -1) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    void merge(int i, int j) {
        if (find(i) != find(j)) {
            parent[find(i)] = find(j);
        }
    }

    public int minChange(String password, int k) {
        int n = password.length();
        parent = new int[n];
        Arrays.fill(parent, -1);
        for (int i = 0; i < k; ++ i) {
            merge(i, n - k + i);
        }
        int[] need = new int[n];
        Arrays.fill(need, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++ i) {
            int count = 0;
            for (int j = 0; j < n; ++ j) {
                if (find(i) == find(j) && password.charAt(i) != password.charAt(j)) {
                    count ++;
                }
            }
            need[find(i)] = Math.min(need[find(i)], count);
        }
        int answer = 0;
        for (int i = 0; i < n; ++ i) {
            if (find(i) == i) {
                answer += need[i];
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            NewArenaPasswordHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                NewArenaPasswordHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class NewArenaPasswordHarness {
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
            String oldPassword        = "topcoderopen";
            int K                     = 5;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new NewArenaPassword().minChange(oldPassword, K));
        }
        case 1: {
            String oldPassword        = "puyopuyo";
            int K                     = 4;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new NewArenaPassword().minChange(oldPassword, K));
        }
        case 2: {
            String oldPassword        = "loool";
            int K                     = 3;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new NewArenaPassword().minChange(oldPassword, K));
        }
        case 3: {
            String oldPassword        = "arena";
            int K                     = 5;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new NewArenaPassword().minChange(oldPassword, K));
        }
        case 4: {
            String oldPassword        = "amavckdkz";
            int K                     = 7;
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new NewArenaPassword().minChange(oldPassword, K));
        }

        // custom cases

/*      case 5: {
            String oldPassword        = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new NewArenaPassword().minChange(oldPassword, K));
        }*/
/*      case 6: {
            String oldPassword        = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new NewArenaPassword().minChange(oldPassword, K));
        }*/
/*      case 7: {
            String oldPassword        = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new NewArenaPassword().minChange(oldPassword, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
