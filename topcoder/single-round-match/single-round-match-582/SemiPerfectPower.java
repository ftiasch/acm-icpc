import java.math.*;
import java.util.*;

public class SemiPerfectPower {
    public long count(long low, long high) {
        return count(high) - count(low - 1);
    }

    long count(long max) {
        long result = 0;
        int maxB = 0;
        while ((long)maxB * maxB * maxB <= max) {
            maxB ++;
        }
        boolean[] isSquareFree = new boolean[maxB];
        Arrays.fill(isSquareFree, true);
        for (int d = 2; d * d < maxB; ++ d) {
            for (int i = 0; i < maxB; i += d * d) {
                isSquareFree[i] = false;
            }
        }
        for (int a = 1; a < maxB; ++ a) {
            if (isSquareFree[a]) {
                // b <= Math.sqrt(max / a)
                result += squareRoot(max / a) - a;
            }
        }
        int maxA = 0;
        while ((long)maxA * maxA * maxA * maxA <= max) {
            maxA ++;
        }
        boolean[] isCubeFree = new boolean[maxA];
        Arrays.fill(isCubeFree, true);
        for (int d = 2; d * d * d < maxA; ++ d) {
            for (int i = 0; i < maxA; i += d * d * d) {
                isCubeFree[i] = false;
            }
        }
        int[] miu = new int[Math.max(maxB, 2)];
        miu[1] = 1;
        for (int i = 1; i < maxB; ++ i) {
            for (int j = i + i; j < maxB; j += i) {
                miu[j] -= miu[i];
            }
        }
        int[][] count = new int[maxA][];
        for (int d = 1; d < maxA; ++ d) {
            count[d] = new int[Math.max((maxB + d - 1) / d, 1)];
            for (int i = 1; i < count[d].length; ++ i) {
                count[d][i] = count[d][i - 1] + miu[i * d] * miu[i * d];
            }
        }
        ArrayList <ArrayList <Integer>> divisors = new ArrayList <ArrayList <Integer>>();
        for (int a = 0; a < maxA; ++ a) {
            divisors.add(new ArrayList <Integer>());
        }
        for (int d = 1; d < maxA; ++ d) {
            if (miu[d] != 0) {
                for (int a = d; a < maxA; a += d) {
                    divisors.get(a).add(d);
                }
            }
        }
        for (int a = 1; a < maxA; ++ a) {
            if (isCubeFree[a]) {
                for (int m = 1; m * m * m <= a; ++ m) {
                    int a0 = a / gcd(a, m * m);
                    if (isSquareFree[a0]) {
                        int m0 = m * m / gcd(a, m * m);
                        int r0 = cubicRoot(max / a);
                        for (int d : divisors.get(a0)) {
                            result += miu[d] * count[d][r0 / m0 / d];
                            result -= miu[d] * count[d][a / m0 / d];
                        }
                    }
                }
            }
        }
        return result;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    int squareRoot(long n) {
        int r = Math.max((int)Math.pow(n, 1. / 2.) - 5, 0);
        while ((long)r * r <= n) {
            r ++;
        }
        return r - 1;
    }

    int cubicRoot(long n) {
        int r = Math.max((int)Math.pow(n, 1. / 3.) - 5, 0);
        while ((long)r * r * r <= n) {
            r ++;
        }
        return r - 1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SemiPerfectPowerHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SemiPerfectPowerHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SemiPerfectPowerHarness {
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
            long L                    = 18;
            long R                    = 58;
            long expected__           = 9;

            return verifyCase(casenum__, expected__, new SemiPerfectPower().count(L, R));
        }
        case 1: {
            long L                    = 1;
            long R                    = 10;
            long expected__           = 3;

            return verifyCase(casenum__, expected__, new SemiPerfectPower().count(L, R));
        }
        case 2: {
            long L                    = 60;
            long R                    = 70;
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new SemiPerfectPower().count(L, R));
        }
        case 3: {
            long L                    = 319268319114310L;
            long R                    = 35860463407469139L;
            long expected__           = 95023825161L;

            return verifyCase(casenum__, expected__, new SemiPerfectPower().count(L, R));
        }
        case 4: {
            long L                    = 1;
            long R                    = 80000000000000000L;
            long expected__           = 169502909978L;

            return verifyCase(casenum__, expected__, new SemiPerfectPower().count(L, R));
        }

        // custom cases

        case 5: {
            long L                    = 80000000000000000L;
            long R                    = 80000000000000000L;
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new SemiPerfectPower().count(L, R));
        }
/*      case 6: {
            long L                    = ;
            long R                    = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SemiPerfectPower().count(L, R));
        }*/
/*      case 7: {
            long L                    = ;
            long R                    = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SemiPerfectPower().count(L, R));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
