import java.math.*;
import java.util.*;

public class FlippingBitsDiv1 {
    final static int INF = 1000000000;

    public int getmin(String[] strings, int m) {
        String s = concatenate(strings);
        int n = s.length();
        int answer = n;
        if (n / m <= m) {
            int[][] count = new int[2][m];
            for (int mask = 0; mask < 1 << n / m; ++ mask) {
                Arrays.fill(count[0], 0);
                Arrays.fill(count[1], 0);
                int flip = 0;
                for (int i = n - 1; i >= 0; -- i) {
                    if (i % m == m - 1) {
                        flip ^= mask >> i / m & 1;
                    }
                    int t = s.charAt(i) - '0' ^ flip;
                    count[t][i % m] ++;
                }
                int cost = 0;
                for (int i = 0; i < m; ++ i) {
                    cost += Math.min(count[0][i], count[1][i]);
                }
                answer = Math.min(answer, cost + Integer.bitCount(mask));
            }
        } else {
            int part = n / m;
            int[] need = new int[part + 1];
            int[][] minimum = new int[part + 1][2];
            for (int mask = 0; mask < 1 << m; ++ mask) {
                Arrays.fill(need, 0);
                for (int i = 0; i < n; ++ i) {
                    need[i / m] += s.charAt(i) - '0' ^ mask >> i % m & 1;
                }
                minimum[part][0] = need[part];
                minimum[part][1] = INF;
                for (int i = part - 1; i >= 0; -- i) {
                    minimum[i][0] = minimum[i][1] = INF;
                    for (int j = 0; j < 2; ++ j) {
                        for (int k = 0; k < 2; ++ k) {
                            minimum[i][j ^ k] = Math.min(minimum[i][j ^ k], minimum[i + 1][j] + k + ((j ^ k) == 0 ? need[i] : m - need[i]));
                        }
                    }
                }
                answer = Math.min(answer, Math.min(minimum[0][0], minimum[0][1]));
            }
        }
        return answer;
    }

    String concatenate(String[] strings) {
        StringBuffer buffer = new StringBuffer();
        for (String string : strings) {
            buffer.append(string);
        }
        return buffer.toString();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FlippingBitsDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FlippingBitsDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FlippingBitsDiv1Harness {
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
            String[] S                = {"00111000"};
            int M                     = 1;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new FlippingBitsDiv1().getmin(S, M));
        }
        case 1: {
            String[] S                = {"101100001101"};
            int M                     = 3;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new FlippingBitsDiv1().getmin(S, M));
        }
        case 2: {
            String[] S                = {"11111111"};
            int M                     = 4;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new FlippingBitsDiv1().getmin(S, M));
        }
        case 3: {
            String[] S                = {"1101001000"};
            int M                     = 8;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new FlippingBitsDiv1().getmin(S, M));
        }
        case 4: {
            String[] S                = {"1","10","11","100","101","110"};
            int M                     = 5;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new FlippingBitsDiv1().getmin(S, M));
        }
        case 5: {
            String[] S                = {"1001011000101010001111111110111011011100110001001"};
            int M                     = 2;
            int expected__            = 16;

            return verifyCase(casenum__, expected__, new FlippingBitsDiv1().getmin(S, M));
        }

        // custom cases

/*      case 6: {
            String[] S                = ;
            int M                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FlippingBitsDiv1().getmin(S, M));
        }*/
/*      case 7: {
            String[] S                = ;
            int M                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FlippingBitsDiv1().getmin(S, M));
        }*/
/*      case 8: {
            String[] S                = ;
            int M                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FlippingBitsDiv1().getmin(S, M));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
