import java.math.*;
import java.util.*;

public class KingdomAndCities {
    static long MOD = (long)1e9 + 7;

    long plus(long a, long b) {
        a += b;
        if (a >= MOD) {
            a -= MOD;
        }
        return a;
    }

    public int howMany(int n, int m, int k) {
        int e = n * (n - 1) / 2;
        long[][] binom = new long[e + 1][Math.max(e, k) + 1];
        for (int i = 0; i <= e; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = plus(binom[i - 1][j - 1], binom[i - 1][j]);
            }
        }
        long[][] graph = new long[n + 1][k + 1];
        graph[0][0] = 1;
        for (int i = 1; i <= n; ++ i) {
            for (int j = 0; j <= k; ++ j) {
                graph[i][j] = binom[i * (i - 1) / 2][j];
                for (int i0 = 1; i0 < i; ++ i0) {
                    int i1 = i - i0;
                    for (int j0 = 0; j0 <= j; ++ j0) {
                        graph[i][j] = plus(graph[i][j], MOD - (graph[i0][j0] * binom[i - 1][i0 - 1] % MOD * binom[i1 * (i1 - 1) / 2][j - j0] % MOD) % MOD);
                    }
                }
            }
        }
        long result = 0;
        if (m == 0) {
            result = graph[n][k];
        } else if (m == 1) {
            if (k >= 2) {
                result = plus(result, graph[n - 1][k - 2] * ((n - 1) * (n - 2) / 2) % MOD);
                for (int i0 = 1; i0 < n - 1; ++ i0) {
                    int i1 = n - 1 - i0;
                    for (int j0 = 0; j0 <= k - 2; ++ j0) {
                        int j1 = k - 2 - j0;
                        result = plus(result, graph[i0][j0] * graph[i1][j1] % MOD * binom[n - 2][i0 - 1] % MOD * (i0 * i1) % MOD);
                    }
                }
            }
        } else {
            if (k >= 3) {
                result = plus(result, graph[n - 2][k - 3] * (n - 2) * (n - 2) % MOD);
                for (int i0 = 1; i0 < n - 2; ++ i0) {
                    int i1 = n - 2 - i0;
                    for (int j0 = 0; j0 <= k - 3; ++ j0) {
                        int j1 = k - 3 - j0;
                        result = plus(result, graph[i0][j0] * graph[i1][j1] % MOD * binom[n - 3][i0 - 1] % MOD * (i0 * i1 * 2) % MOD);
                    }
                }
            }
            if (k >= 4) {
                if (true) {
                    long ways = (n - 2) * (n - 3) / 2;
                    result = plus(result, graph[n - 2][k - 4] * (ways * ways) % MOD);
                }
                for (int i0 = 1; i0 < n - 2; ++ i0) {
                    int i1 = n - 2 - i0;
                    for (int j0 = 0; j0 <= k - 4; ++ j0) {
                        int j1 = k - 4 - j0;
                        long ways = graph[i0][j0] * graph[i1][j1] % MOD * binom[n - 3][i0 - 1] % MOD;
                        result = plus(result, ways * (i0 * i1 * i0 * i1) % MOD);
                        result = plus(result, ways * (i0 * i1 * (i0 * (i0 - 1) + i1 * (i1 - 1))) % MOD);
                    }
                }
                for (int i0 = 1; i0 < n - 2; ++ i0) {
                    for (int i1 = 1; i0 + i1 < n - 2; ++ i1) {
                        int i2 = n - 2 - i0 - i1;
                        for (int j0 = 0; j0 <= k - 4; ++ j0) {
                            for (int j1 = 0; j0 + j1 <= k - 4; ++ j1) {
                                int j2 = k - 4 - j0 - j1;
                                long ways = graph[i0][j0] * graph[i1][j1] % MOD * graph[i2][j2] % MOD * binom[n - 3][i0 - 1] % MOD * binom[n - 3 - i0][i1 - 1] % MOD;
                                result = plus(result, ways * (i0 * i1 * i1 * i2 * 2L) % MOD);
                                result = plus(result, ways * (i1 * i2 * i2 * i0 * 2L) % MOD);
                                result = plus(result, ways * (i2 * i0 * i0 * i1 * 2L) % MOD);
                            }
                        }
                    }
                }
            }
        }
        return (int)result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            KingdomAndCitiesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                KingdomAndCitiesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class KingdomAndCitiesHarness {
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
            int N                     = 3;
            int M                     = 0;
            int K                     = 3;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new KingdomAndCities().howMany(N, M, K));
        }
        case 1: {
            int N                     = 4;
            int M                     = 1;
            int K                     = 4;
            int expected__            = 9;

            return verifyCase(casenum__, expected__, new KingdomAndCities().howMany(N, M, K));
        }
        case 2: {
            int N                     = 5;
            int M                     = 2;
            int K                     = 11;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new KingdomAndCities().howMany(N, M, K));
        }
        case 3: {
            int N                     = 5;
            int M                     = 0;
            int K                     = 8;
            int expected__            = 45;

            return verifyCase(casenum__, expected__, new KingdomAndCities().howMany(N, M, K));
        }
        case 4: {
            int N                     = 10;
            int M                     = 2;
            int K                     = 20;
            int expected__            = 150810825;

            return verifyCase(casenum__, expected__, new KingdomAndCities().howMany(N, M, K));
        }

        // custom cases

/*      case 5: {
            int N                     = ;
            int M                     = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new KingdomAndCities().howMany(N, M, K));
        }*/
/*      case 6: {
            int N                     = ;
            int M                     = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new KingdomAndCities().howMany(N, M, K));
        }*/
/*      case 7: {
            int N                     = ;
            int M                     = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new KingdomAndCities().howMany(N, M, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
