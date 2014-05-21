import java.math.*;
import java.util.*;

public class KleofasTail {
    long count(long k, long n) {
        if (k >= n) {
            return 0;
        }
        long result = 0;
        for (int level = 0; (k << level) < n; ++ level) {
            long start = k << level;
            long length = 1L << level;
            result += Math.min(n - start, length);
        }
        return result;
    }

    long solve(long k, long n) {
        long result = count(k, n);
        if ((k & 1) == 0) {
            result += count(k + 1, n);
        }
        return result;
    }

    public long countGoodSequences(long k, long a, long b) {
        if (k == 0) {
            return b + 1 - a;
        }
        return solve(k, b + 1) - solve(k, a);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            KleofasTailHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                KleofasTailHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class KleofasTailHarness {
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
            long K                    = 3;
            long A                    = 4;
            long B                    = 8;
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new KleofasTail().countGoodSequences(K, A, B));
        }
        case 1: {
            long K                    = 1;
            long A                    = 23457;
            long B                    = 123456;
            long expected__           = 100000;

            return verifyCase(casenum__, expected__, new KleofasTail().countGoodSequences(K, A, B));
        }
        case 2: {
            long K                    = 1234567890123456L;
            long A                    = 10;
            long B                    = 1000000;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new KleofasTail().countGoodSequences(K, A, B));
        }
        case 3: {
            long K                    = 0;
            long A                    = 0;
            long B                    = 2;
            long expected__           = 3;

            return verifyCase(casenum__, expected__, new KleofasTail().countGoodSequences(K, A, B));
        }
        case 4: {
            long K                    = 2;
            long A                    = 3;
            long B                    = 3;
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new KleofasTail().countGoodSequences(K, A, B));
        }
        case 5: {
            long K                    = 13;
            long A                    = 12345;
            long B                    = 67890123;
            long expected__           = 8387584;

            return verifyCase(casenum__, expected__, new KleofasTail().countGoodSequences(K, A, B));
        }

        // custom cases

/*      case 6: {
            long K                    = ;
            long A                    = ;
            long B                    = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new KleofasTail().countGoodSequences(K, A, B));
        }*/
/*      case 7: {
            long K                    = ;
            long A                    = ;
            long B                    = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new KleofasTail().countGoodSequences(K, A, B));
        }*/
/*      case 8: {
            long K                    = ;
            long A                    = ;
            long B                    = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new KleofasTail().countGoodSequences(K, A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
