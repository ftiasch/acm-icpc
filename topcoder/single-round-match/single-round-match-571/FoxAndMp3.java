import java.math.*;
import java.util.*;

public class FoxAndMp3 {
    int count;
    String[] songs;

    void generate(long x, long n) {
        if (x > n || count >= 50) {
            return;
        }
        songs[count ++] = "" + x + ".mp3";
        for (int i = 0; i < 10; ++ i) {
            generate(x * 10 + i, n);
        }
    }

    public String[] playList(int n) {
        count = 0;
        songs = new String[Math.min(n, 50)];
        for (int i = 1; i < 10; ++ i) {
            generate(i, n);
        }
        return songs;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxAndMp3Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxAndMp3Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndMp3Harness {
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

    static boolean compareOutput(String[] expected, String[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (!expected[i].equals(result[i])) return false; return true; }

    static String formatResult(String[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" \"%s\"", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, String[] expected, String[] received) {
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
            int n                     = 3;
            String[] expected__       = {"1.mp3", "2.mp3", "3.mp3" };

            return verifyCase(casenum__, expected__, new FoxAndMp3().playList(n));
        }
        case 1: {
            int n                     = 10;
            String[] expected__       = {"1.mp3", "10.mp3", "2.mp3", "3.mp3", "4.mp3", "5.mp3", "6.mp3", "7.mp3", "8.mp3", "9.mp3" };

            return verifyCase(casenum__, expected__, new FoxAndMp3().playList(n));
        }
        case 2: {
            int n                     = 16;
            String[] expected__       = {"1.mp3", "10.mp3", "11.mp3", "12.mp3", "13.mp3", "14.mp3", "15.mp3", "16.mp3", "2.mp3", "3.mp3", "4.mp3", "5.mp3", "6.mp3", "7.mp3", "8.mp3", "9.mp3" };

            return verifyCase(casenum__, expected__, new FoxAndMp3().playList(n));
        }
        case 3: {
            int n                     = 32;
            String[] expected__       = {"1.mp3", "10.mp3", "11.mp3", "12.mp3", "13.mp3", "14.mp3", "15.mp3", "16.mp3", "17.mp3", "18.mp3", "19.mp3", "2.mp3", "20.mp3", "21.mp3", "22.mp3", "23.mp3", "24.mp3", "25.mp3", "26.mp3", "27.mp3", "28.mp3", "29.mp3", "3.mp3", "30.mp3", "31.mp3", "32.mp3", "4.mp3", "5.mp3", "6.mp3", "7.mp3", "8.mp3", "9.mp3" };

            return verifyCase(casenum__, expected__, new FoxAndMp3().playList(n));
        }
        case 4: {
            int n                     = 100000009;
            String[] expected__       = {"1.mp3", "10.mp3", "100.mp3", "1000.mp3", "10000.mp3", "100000.mp3", "1000000.mp3", "10000000.mp3", "100000000.mp3", "100000001.mp3", "100000002.mp3", "100000003.mp3", "100000004.mp3", "100000005.mp3", "100000006.mp3", "100000007.mp3", "100000008.mp3", "100000009.mp3", "10000001.mp3", "10000002.mp3", "10000003.mp3", "10000004.mp3", "10000005.mp3", "10000006.mp3", "10000007.mp3", "10000008.mp3", "10000009.mp3", "1000001.mp3", "10000010.mp3", "10000011.mp3", "10000012.mp3", "10000013.mp3", "10000014.mp3", "10000015.mp3", "10000016.mp3", "10000017.mp3", "10000018.mp3", "10000019.mp3", "1000002.mp3", "10000020.mp3", "10000021.mp3", "10000022.mp3", "10000023.mp3", "10000024.mp3", "10000025.mp3", "10000026.mp3", "10000027.mp3", "10000028.mp3", "10000029.mp3", "1000003.mp3" };

            return verifyCase(casenum__, expected__, new FoxAndMp3().playList(n));
        }
        case 5: {
            int n                     = 1;
            String[] expected__       = {"1.mp3" };

            return verifyCase(casenum__, expected__, new FoxAndMp3().playList(n));
        }

        // custom cases

/*      case 6: {
            int n                     = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new FoxAndMp3().playList(n));
        }*/
/*      case 7: {
            int n                     = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new FoxAndMp3().playList(n));
        }*/
/*      case 8: {
            int n                     = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new FoxAndMp3().playList(n));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
