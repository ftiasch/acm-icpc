import java.math.*;
import java.util.*;

public class PerfectSquare {
    public int ways(int[] numbers) {
        List <Integer> factors = new ArrayList <Integer>();
        for (int number : numbers) {
            int n = number;
            for (int p = 2; p * p <= n; ++ p) {
                if (n % p == 0) {
                    factors.add(p);
                    while (n % p == 0) {
                        n /= p;
                    }
                }
            }
            if (n > 1) {
                factors.add(n);
            }
        }
        if (true) {
            Set <Integer> set = new HashSet <Integer>();
            set.addAll(factors);
            factors = new ArrayList <Integer>();
            factors.addAll(set);
        }
        int n = (int)Math.sqrt(numbers.length);
        int m = (n << 1) + factors.size();
        int l = (n * n >> 6) + 1;
        long[][] coefficient = new long[m][l];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                int p = i * n + j;
                coefficient[i][p >> 6] ^= 1L << (p & 63);
            }
            coefficient[i][n * n >> 6] ^= 1L << (n * n & 63);
        }
        for (int j = 0; j < n; ++ j) {
            for (int i = 0; i < n; ++ i) {
                int p = i * n + j;
                coefficient[n + j][p >> 6] ^= 1L << (p & 63);
            }
            coefficient[n + j][n * n >> 6] ^= 1L << (n * n & 63);
        }
        for (int k = 0; k < (int)factors.size(); ++ k) {
            int p = factors.get(k);
            for (int i = 0; i < n * n; ++ i) {
                int number = numbers[i];
                while (number % p == 0) {
                    number /= p;
                    coefficient[(n << 1) + k][i >> 6] ^= 1L << (i & 63);
                }
            }
        }
        int rank = 0;
        boolean[] used = new boolean[m];
        for (int i = 0; i < n * n; ++ i) {
            int pivot = 0;
            while (pivot < m && (used[pivot] || (coefficient[pivot][i >> 6] >> (i & 63) & 1) == 0)) {
                pivot ++;
            }
            if (pivot < m) {
                rank ++;
                used[pivot] = true;
                for (int j = 0; j < m; ++ j) {
                    if (j != pivot && (coefficient[j][i >> 6] >> (i & 63) & 1) == 1) {
                        for (int k = 0; k < l; ++ k) {
                            coefficient[j][k] ^= coefficient[pivot][k];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; ++ i) {
            coefficient[i][n * n >> 6] ^= 1L << (n * n & 63);
            boolean valid = false;
            for (int j = 0; j < l; ++ j) {
                valid |= coefficient[i][j] != 0;
            }
            if (!valid) {
                return 0;
            }
        }
        long result = 1;
        for (int i = rank; i < n * n; ++ i) {
            result *= 2;
            result %= (int)1e9 + 7;
        }
        return (int)result;
    }


    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PerfectSquareHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                PerfectSquareHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PerfectSquareHarness {
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
            int[] x                   = {1, 1, 1, 2} ;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new PerfectSquare().ways(x));
        }
        case 1: {
            int[] x                   = {620, 620, 620, 620} ;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new PerfectSquare().ways(x));
        }
        case 2: {
            int[] x                   = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new PerfectSquare().ways(x));
        }
        case 3: {
            int[] x                   = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new PerfectSquare().ways(x));
        }
        case 4: {
            int[] x                   = {2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13, 14,15,16,17};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new PerfectSquare().ways(x));
        }
        case 5: {
            int[] x                   = {9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690, 9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690, 9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690, 9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690, 9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690, 9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690, 9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690, 9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690, 9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690, 9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690,9699690};
            int expected__            = 993282280;

            return verifyCase(casenum__, expected__, new PerfectSquare().ways(x));
        }

        // custom cases

/*      case 6: {
            int[] x                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PerfectSquare().ways(x));
        }*/
/*      case 7: {
            int[] x                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PerfectSquare().ways(x));
        }*/
/*      case 8: {
            int[] x                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PerfectSquare().ways(x));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
