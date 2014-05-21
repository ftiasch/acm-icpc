import java.math.*;
import java.util.*;

public class PenguinEmperor {
    static int MOD = (int)1e9 + 7;

    int[] multiply(int[] a, int[] b) {
        int n = a.length;
        int[] c = new int[n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                c[(i + j) % n] += (int)((long)a[i] * b[j] % MOD);
                if (c[(i + j) % n] >= MOD) {
                    c[(i + j) % n] -= MOD;
                }
            }
        }
        return c;
    }

    int[] power(int[] a, long m) {
        int n = a.length;
        int[] result = new int[n];
        result[0] = 1;
        while (m > 0) {
            if (m % 2 == 1) {
                result = multiply(result, a);
            }
            a = multiply(a, a);
            m >>= 1;
        }
        return result;
    }

    public int countJourneys(int n, long m) {
        m ++;
        int[] ways = new int[n];
        ways[0] = 1;
        for (int i = 1; i < n; ++ i) {
            int[] newWays = new int[n];
            for (int j = 0; j < n; ++ j) {
                newWays[j] = ways[(j + i) % n];
                if (2 * i != n) {
                    newWays[j] += ways[(j + n - i) % n];
                    if (newWays[j] >= MOD) {
                        newWays[j] -= MOD;
                    }
                }
            }
            ways = newWays;
        }
        int[] result = new int[n];
        result[0] = 1;
        result = multiply(result, power(ways, m / n));
        for (int i = 1; i < m % n; ++ i) {
            int[] newResult = new int[n];
            for (int j = 0; j < n; ++ j) {
                newResult[j] = result[(j + i) % n];
                if (2 * i != n) {
                    newResult[j] += result[(j + n - i) % n];
                    if (newResult[j] >= MOD) {
                        newResult[j] -= MOD;
                    }
                }
            }
            result = newResult;
        }
        return result[0];
    }


    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PenguinEmperorHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                PenguinEmperorHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PenguinEmperorHarness {
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
            int numCities             = 3;
            long daysPassed           = 2;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }
        case 1: {
            int numCities             = 4;
            long daysPassed           = 3;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }
        case 2: {
            int numCities             = 5;
            long daysPassed           = 36;
            int expected__            = 107374182;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }
        case 3: {
            int numCities             = 300;
            long daysPassed           = 751;
            int expected__            = 413521250;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }
        case 4: {
            int numCities             = 300;
            long daysPassed           = 750;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }
        case 5: {
            int numCities             = 350;
            long daysPassed           = 1000000000000000000L;
            int expected__            = 667009739;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }
        case 6: {
            int numCities             = 5;
            long daysPassed           = 7;
            int expected__            = 12;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }

        // custom cases

/*      case 7: {
            int numCities             = ;
            long daysPassed           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }*/
/*      case 8: {
            int numCities             = ;
            long daysPassed           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }*/
/*      case 9: {
            int numCities             = ;
            long daysPassed           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PenguinEmperor().countJourneys(numCities, daysPassed));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
