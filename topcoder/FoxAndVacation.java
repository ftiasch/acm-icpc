import java.util.*;
import java.math.*;

public class FoxAndVacation {
    public int maxCities(int total, int[] d) {
        Arrays.sort(d);
        int n = d.length;
        int answer = 0;
        while (answer < n && total >= d[answer]) {
            total -= d[answer ++];
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxAndVacationHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxAndVacationHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndVacationHarness {
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

    static int runTestCase(int casenum) {
        switch(casenum) {
        case 0: {
            int total                 = 5;
            int[] d                   = {2,2,2};
            int expected__            = 2;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }
        case 1: {
            int total                 = 5;
            int[] d                   = {5,6,1};
            int expected__            = 1;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }
        case 2: {
            int total                 = 5;
            int[] d                   = {6,6,6};
            int expected__            = 0;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }
        case 3: {
            int total                 = 6;
            int[] d                   = {1,1,1,1,1};
            int expected__            = 5;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }
        case 4: {
            int total                 = 10;
            int[] d                   = {7,1,5,6,1,3,4};
            int expected__            = 4;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }
        case 5: {
            int total                 = 50;
            int[] d                   = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
            int expected__            = 9;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }
        case 6: {
            int total                 = 21;
            int[] d                   = {14,2,16,9,9,5,5,23,25,20,8,25,6,12,3,2,4,5,10,14,19,12,25,15,14};
            int expected__            = 6;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }

        // custom cases

/*      case 7: {
            int total                 = ;
            int[] d                   = ;
            int expected__            = ;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }*/
/*      case 8: {
            int total                 = ;
            int[] d                   = ;
            int expected__            = ;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }*/
/*      case 9: {
            int total                 = ;
            int[] d                   = ;
            int expected__            = ;

            return verifyCase(casenum, expected__, new FoxAndVacation().maxCities(total, d));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
