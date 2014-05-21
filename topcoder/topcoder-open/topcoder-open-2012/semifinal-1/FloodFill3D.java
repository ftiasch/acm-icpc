import java.math.*;
import java.util.*;

public class FloodFill3D {
    long count(String string, char c) {
        int ret = 0;
        for (int i = 0; i < string.length(); ++ i) {
            if (string.charAt(i) == c) {
                ret ++;
            }
        }
        return ret;
    }

    long middle(String string, char c) {
        int i = 0;
        while (i < string.length() && string.charAt(i) == c) {
            i ++;
        }
        int j = string.length();
        while (j > 0 && string.charAt(j - 1) == c) {
            j --;
        }
        int ret = 0;
        for (; i < j; ++ i) {
            if (string.charAt(i) == c) {
                ret ++;
            }
        }
        return ret;
    }

    long solve(String s, String t, String u, char c) {
        return count(s, c) * count(t, c) * count(u, c) - middle(s, c) * middle(t, c) * middle(u, c);
    }

    public long countBlack(String[] sArray, String[] tArray, String[] uArray) {
        String s = concate(sArray);
        String t = concate(tArray);
        String u = concate(uArray);
        return (long)s.length() * t.length() * u.length() - solve(s, t, u, 'o') - solve(s, t, u, 'x');
    }

    String concate(String[] strings) {
        StringBuffer buffer = new StringBuffer("");
        for (String string : strings) {
            buffer.append(string);
        }
        return buffer.toString();
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FloodFill3DHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FloodFill3DHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FloodFill3DHarness {
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
            String[] SArray           = {"oxo"};
            String[] TArray           = {"oxo"};
            String[] UArray           = {"oxo"};
            long expected__           = 19;

            return verifyCase(casenum__, expected__, new FloodFill3D().countBlack(SArray, TArray, UArray));
        }
        case 1: {
            String[] SArray           = {"ooo"};
            String[] TArray           = {"oo"};
            String[] UArray           = {"o"};
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new FloodFill3D().countBlack(SArray, TArray, UArray));
        }
        case 2: {
            String[] SArray           = {"xxo", "oox", "o"};
            String[] TArray           = {"x", "o", "x", "o"};
            String[] UArray           = {"ooo", "xxxoo", "oxx"};
            long expected__           = 242;

            return verifyCase(casenum__, expected__, new FloodFill3D().countBlack(SArray, TArray, UArray));
        }
        case 3: {
            String[] SArray           = {"xxxxxxxxxxxxxxxxxxxx"
,"xxooooooooooooooooxx"
,"xxooooooooooooooooxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxooooxxxxxxxx"
,"xxxxxxxxxxxxxxxxxxxx"}
;
            String[] TArray           = {"xxxxxxxxxxxxxxxxxxxx"
,"xxxxxxxoooooooxxxxxx"
,"xxxxxoooooooooooxxxx"
,"xxxxooooooooooooxxxx"
,"xxxxooooxxxxxoooxxxx"
,"xxxxoooxxxxxxxxxxxxx"
,"xxxxoooxxxxxxxxxxxxx"
,"xxxxoooxxxxxxxxxxxxx"
,"xxxxooooxxxxoooxxxxx"
,"xxxxoooooooooooxxxxx"
,"xxxxxooooooooooxxxxx"
,"xxxxxxoooooooxxxxxxx"
,"xxxxxxxxxxxxxxxxxxxx"}
;
            String[] UArray           = {"xxxxxxxxxxxxxxxxxxxx"
,"xxxxxxxoooooxxxxxxxx"
,"xxxxoooooooooooxxxxx"
,"xxoooooooooooooooxxx"
,"xxoooooxxxxxoooooxxx"
,"xxooooxxxxxxxooooxxx"
,"xxooooxxxxxxxooooxxx"
,"xxooooxxxxxxxooooxxx"
,"xxooooxxxxxxxooooxxx"
,"xxoooooxxxxxoooooxxx"
,"xxxxoooooooooooxxxxx"
,"xxxxxxxoooooxxxxxxxx"
,"xxxxxxxxxxxxxxxxxxxx"}
;
            long expected__           = 15027148;

            return verifyCase(casenum__, expected__, new FloodFill3D().countBlack(SArray, TArray, UArray));
        }

        // custom cases

/*      case 4: {
            String[] SArray           = ;
            String[] TArray           = ;
            String[] UArray           = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new FloodFill3D().countBlack(SArray, TArray, UArray));
        }*/
/*      case 5: {
            String[] SArray           = ;
            String[] TArray           = ;
            String[] UArray           = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new FloodFill3D().countBlack(SArray, TArray, UArray));
        }*/
/*      case 6: {
            String[] SArray           = ;
            String[] TArray           = ;
            String[] UArray           = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new FloodFill3D().countBlack(SArray, TArray, UArray));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
