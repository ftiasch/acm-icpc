import java.math.*;
import java.util.*;

public class ConversionMachine {
    final static long MOD = (int)1e9 + 7;

    private int get(int zero, int one, int n) {
        int id = 0;
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; i + j <= n; ++ j) {
                if (zero == i && one == j) {
                    return id;
                }
                id ++;
            }
        }
        return -1;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int size = a.length;
        long[][] c = new long[size][size];
        for (int i = 0; i < size; ++ i) {
            for (int j = 0; j < size; ++ j) {
                for (int k = 0; k < size; ++ k) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }

    private long[][] power(long[][] a, long n) {
        int size = a.length;
        long[][] result = new long[size][size];
        for (int i = 0; i < size; ++ i) {
            result[i][i] = 1;
        }
        while (n > 0) {
            if (n % 2 == 1) {
                result = multiply(result, a);
            }
            a = multiply(a, a);
            n >>= 1;
        }
        return result;
    }

    public int countAll(String init, String target, int[] costs, int maxCost) {
        int n = init.length();
        int[] count = new int[3];
        for (int i = 0; i < n; ++ i) {
            count[((target.charAt(i) - init.charAt(i)) % 3 + 3) % 3] ++;
            for (int c = init.charAt(i) - 'a'; c != target.charAt(i) - 'a'; c = (c + 1) % 3) {
                maxCost -= costs[c];
                if (maxCost < 0) {
                    return 0;
                }
            }
        }
        int length = count[1] * 1 + count[2] * 2 + maxCost / (costs[0] + costs[1] + costs[2]) * 3;
        int size = (n + 1) * (n + 2) / 2 + 1;
        long[][] transfer = new long[size][size];
        for (int zero = 0; zero <= n; ++ zero) {
            for (int one = 0; zero + one <= n; ++ one) {
                int id = get(zero, one, n);
                int[] state = new int[]{zero, one, n - zero - one};
                for (int type = 0; type < 3; ++ type) {
                    if (state[type] >= 1) {
                        int[] newState = state.clone();
                        newState[type] --;
                        newState[(type + 2) % 3] ++;
                        int newID = get(newState[0], newState[1], n);
                        transfer[id][newID] += state[type];
                        if (newState[0] == n) {
                            transfer[id][size - 1] += state[type];
                        }
                    }
                }
            }
        }
        transfer[size - 1][size - 1] += 1;
        long[][] seed = new long[size][size];
        seed[0][get(count[0], count[1], n)] += 1;
        if (count[0] == n) {
            seed[0][size - 1] += 1;
        }
        long[][] result = multiply(seed, power(transfer, length));
        return (int)result[0][size - 1];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ConversionMachineHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ConversionMachineHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ConversionMachineHarness {
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
            String word1              = "a";
            String word2              = "b";
            int[] costs               = {100,2,3};
            int maxCost               = 205;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new ConversionMachine().countAll(word1, word2, costs, maxCost));
        }
        case 1: {
            String word1              = "abcba";
            String word2              = "abcba";
            int[] costs               = {67,23,43};
            int maxCost               = 0;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new ConversionMachine().countAll(word1, word2, costs, maxCost));
        }
        case 2: {
            String word1              = "cccccccc";
            String word2              = "aaaaaaaa";
            int[] costs               = {10000000,1,1};
            int maxCost               = 100;
            int expected__            = 40320;

            return verifyCase(casenum__, expected__, new ConversionMachine().countAll(word1, word2, costs, maxCost));
        }
        case 3: {
            String word1              = "aa";
            String word2              = "cc";
            int[] costs               = {1,10,100};
            int maxCost               = 1787;
            int expected__            = 123611681;

            return verifyCase(casenum__, expected__, new ConversionMachine().countAll(word1, word2, costs, maxCost));
        }

        // custom cases

      case 4: {
            String word1              = "a";
            String word2              = "b";
            int[] costs               = {1, 1, 1};
            int maxCost               = 0;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new ConversionMachine().countAll(word1, word2, costs, maxCost));
        }
/*      case 5: {
            String word1              = ;
            String word2              = ;
            int[] costs               = ;
            int maxCost               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ConversionMachine().countAll(word1, word2, costs, maxCost));
        }*/
/*      case 6: {
            String word1              = ;
            String word2              = ;
            int[] costs               = ;
            int maxCost               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ConversionMachine().countAll(word1, word2, costs, maxCost));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
