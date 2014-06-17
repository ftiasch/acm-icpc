import java.math.*;
import java.util.*;

public class MatrixPower {
    public int[] getElements(int d, int q, int n, int k, int[] rows, int[] columns) {
        int m = rows.length;
        int[] result = new int[m];
        for (int i = 0; i < m; ++ i) {
            result[i] = work(d, q, n, k, rows[i], columns[i]);
        }
        return result;
    }

    static int MOD = (int)1e9 + 7;

    int work(int d, int q, int n, int k, int row, int column) {
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; ++ i) {
            power[i] = (int)((long)power[i - 1] * q % MOD);
        }
        if (k == 1) {
            return (int)(((long)d * row % MOD + power[column]) % MOD);
        }
        int sumD = 0;
        int sumQ = 0;
        int sumDQ = 0;
        for (int i = 0; i < n; ++ i) {
            sumD = add(sumD, (int)((long)d * i % MOD));
            sumQ = add(sumQ, power[i]);
            sumDQ = add(sumDQ, (int)((long)d * i % MOD * power[i] % MOD));
        }
        assert sumQ != 0;
        int[][] t = new int[][]{{sumD, (int)((long)n * sumQ % MOD)}, {(int)((long)inverse(sumQ) * sumDQ % MOD), sumQ}};
        int[][] a = new int[][]{{(int)((long)d * row % MOD), sumQ}, {0, 0}};
        int[][] b = new int[][]{{sumD, (int)((long)n * power[column] % MOD)}, {(int)((long)inverse(sumQ) * sumDQ % MOD), power[column]}};
        int[][] p = multiply(a, multiply(power(t, k - 2), b));
        return add(p[0][0], p[0][1]);
    }

    int inverse(int a) {
        return a == 1 ? 1 : (int)((long)(MOD - MOD / a) * inverse(MOD % a) % MOD);
    }

    int add(int x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
    }

    int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; ++ i) {
            for (int j = 0; j < 2; ++ j) {
                for (int k = 0; k < 2; ++ k) {
                    c[i][j] = add(c[i][j], (int)((long)a[i][k] * b[k][j] % MOD));
                }
            }
        }
        return c;
    }

    int[][] power(int[][] a, int n) {
        int[][] result = new int[][]{{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                result = multiply(result, a);
            }
            a = multiply(a, a);
            n >>= 1;
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MatrixPowerHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MatrixPowerHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MatrixPowerHarness {
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

    static boolean compareOutput(int[] expected, int[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(int[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, int[] expected, int[] received) {
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
            int d                     = 1;
            int q                     = 2;
            int n                     = 2;
            int k                     = 2;
            int[] rows                = {0,0,1,1};
            int[] columns             = {0,1,0,1};
            int[] expected__          = {5, 8, 8, 13 };

            return verifyCase(casenum__, expected__, new MatrixPower().getElements(d, q, n, k, rows, columns));
        }
        case 1: {
            int d                     = 0;
            int q                     = 1;
            int n                     = 10;
            int k                     = 3;
            int[] rows                = {0,1,2,3,4,5,6,7,8,9};
            int[] columns             = {0,1,2,3,4,5,6,7,8,9};
            int[] expected__          = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };

            return verifyCase(casenum__, expected__, new MatrixPower().getElements(d, q, n, k, rows, columns));
        }
        case 2: {
            int d                     = 0;
            int q                     = 1000000000;
            int n                     = 1;
            int k                     = 1000000000;
            int[] rows                = {0,0,0};
            int[] columns             = {0,0,0};
            int[] expected__          = {1, 1, 1 };

            return verifyCase(casenum__, expected__, new MatrixPower().getElements(d, q, n, k, rows, columns));
        }
        case 3: {
            int d                     = 1000000000;
            int q                     = 1000000000;
            int n                     = 100;
            int k                     = 1000000000;
            int[] rows                = {0};
            int[] columns             = {0};
            int[] expected__          = {380113608 };

            return verifyCase(casenum__, expected__, new MatrixPower().getElements(d, q, n, k, rows, columns));
        }

        // custom cases

/*      case 4: {
            int d                     = ;
            int q                     = ;
            int n                     = ;
            int k                     = ;
            int[] rows                = ;
            int[] columns             = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new MatrixPower().getElements(d, q, n, k, rows, columns));
        }*/
/*      case 5: {
            int d                     = ;
            int q                     = ;
            int n                     = ;
            int k                     = ;
            int[] rows                = ;
            int[] columns             = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new MatrixPower().getElements(d, q, n, k, rows, columns));
        }*/
/*      case 6: {
            int d                     = ;
            int q                     = ;
            int n                     = ;
            int k                     = ;
            int[] rows                = ;
            int[] columns             = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new MatrixPower().getElements(d, q, n, k, rows, columns));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
