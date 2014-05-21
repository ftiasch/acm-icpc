import java.math.*;
import java.util.*;

public class WolfDelaymasterHard {
    static int MOD = (int)1e9 + 7;

    public int countWords(int n, int wlen, int w0, int wmul, int wadd, int olen, int o0, int omul, int oadd) {
        char[] string = new char[n];
        Arrays.fill(string, '.');
        if (true) {
            long x = w0;
            for (int i = 0; i < wlen; ++ i) {
                string[(int)x] = 'w';
                x = (x * wmul + wadd) % n;
            }
            x = o0;
            for (int i = 0; i < olen; ++ i) {
                string[(int)x] = 'o';
                x = (x * omul + oadd) % n;
            }
        }
        int[] nextW = new int[n + 1];
        nextW[n] = n;
        for (int i = n - 1; i >= 0; -- i) {
            nextW[i] = nextW[i + 1];
            if (string[i] == 'w') {
                nextW[i] = i;
            }
        }
        int[] prevW = new int[n + 1];
        prevW[0] = -1;
        for (int i = 0; i < n; ++ i) {
            prevW[i + 1] = prevW[i];
            if (string[i] == 'w') {
                prevW[i + 1] = i;
            }
        }
        int[] sum = new int[n + 3];
        sum[n] = 1;
        int nextO = n;
        for (int i = n - 1; i >= 0; -- i) {
            if (string[i] == 'o') {
                nextO = i;
            }
            if (i % 2 == 0) {
                int length = Math.min(nextO - i, (n - i) / 2);
                long count = 0;
                while (length > 0) {
                    while (nextW[i + length] < i + length + length) {
                        length = (nextW[i + length] - i) / 2;
                    }
                    int minLength = Math.max(prevW[i + length] - i, 0) + 1;
                    count += sum[i + minLength * 2] - sum[i + length * 2 + 2];
                    length = minLength - 1;
                }
                sum[i] = (int)((sum[i + 2] + count) % MOD);
            }
        }
        return (sum[0] + MOD - sum[2]) % MOD;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            WolfDelaymasterHardHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                WolfDelaymasterHardHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class WolfDelaymasterHardHarness {
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
            int N                     = 8;
            int wlen                  = 5;
            int w0                    = 2;
            int wmul                  = 3;
            int wadd                  = 2;
            int olen                  = 0;
            int o0                    = 6;
            int omul                  = 7;
            int oadd                  = 1;
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }
        case 1: {
            int N                     = 20;
            int wlen                  = 19;
            int w0                    = 12;
            int wmul                  = 9;
            int wadd                  = 15;
            int olen                  = 1;
            int o0                    = 8;
            int omul                  = 11;
            int oadd                  = 1;
            int expected__            = 26;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }
        case 2: {
            int N                     = 40;
            int wlen                  = 24;
            int w0                    = 34;
            int wmul                  = 5;
            int wadd                  = 11;
            int olen                  = 33;
            int o0                    = 35;
            int omul                  = 23;
            int oadd                  = 27;
            int expected__            = 296;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }
        case 3: {
            int N                     = 60;
            int wlen                  = 35;
            int w0                    = 8;
            int wmul                  = 55;
            int wadd                  = 3;
            int olen                  = 11;
            int o0                    = 20;
            int omul                  = 9;
            int oadd                  = 29;
            int expected__            = 10058904;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }
        case 4: {
            int N                     = 2000;
            int wlen                  = 183;
            int w0                    = 994;
            int wmul                  = 862;
            int wadd                  = 468;
            int olen                  = 148;
            int o0                    = 433;
            int omul                  = 1247;
            int oadd                  = 1989;
            int expected__            = 532199331;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }
        case 5: {
            int N                     = 2000000;
            int wlen                  = 419443;
            int w0                    = 1305303;
            int wmul                  = 1761823;
            int wadd                  = 1007025;
            int olen                  = 874640;
            int o0                    = 1516339;
            int omul                  = 229519;
            int oadd                  = 1473199;
            int expected__            = 861766906;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }
        case 6: {
            int N                     = 8;
            int wlen                  = 6;
            int w0                    = 0;
            int wmul                  = 1;
            int wadd                  = 1;
            int olen                  = 3;
            int o0                    = 3;
            int omul                  = 6;
            int oadd                  = 1;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }

        // custom cases

/*      case 7: {
            int N                     = ;
            int wlen                  = ;
            int w0                    = ;
            int wmul                  = ;
            int wadd                  = ;
            int olen                  = ;
            int o0                    = ;
            int omul                  = ;
            int oadd                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }*/
/*      case 8: {
            int N                     = ;
            int wlen                  = ;
            int w0                    = ;
            int wmul                  = ;
            int wadd                  = ;
            int olen                  = ;
            int o0                    = ;
            int omul                  = ;
            int oadd                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }*/
/*      case 9: {
            int N                     = ;
            int wlen                  = ;
            int w0                    = ;
            int wmul                  = ;
            int wadd                  = ;
            int olen                  = ;
            int o0                    = ;
            int omul                  = ;
            int oadd                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WolfDelaymasterHard().countWords(N, wlen, w0, wmul, wadd, olen, o0, omul, oadd));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
