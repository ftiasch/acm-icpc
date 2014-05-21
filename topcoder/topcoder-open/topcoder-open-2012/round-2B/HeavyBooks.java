import java.util.*;
import java.math.*;

public class HeavyBooks {
    final static int INF = 1000000000;

    class Data {
        int difference, sum;

        Data(int difference, int sum) {
            this.difference = difference;
            this.sum = sum;
        }

        Data add(int value) {
            return new Data(difference + value, sum + Math.abs(value));
        }

        Data update(Data other) {
            if (difference != other.difference) {
                return difference > other.difference ? this : other;
            }
            return sum > other.sum ? this : other;
        }

        public String toString() {
            return String.format("(%d, %d)", difference, sum);
        }
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    public int[] findWeight(int[] books, int[] moves) {
        int n = books.length;
        int m = moves[0];
        int[] owner = new int[m];
        Arrays.fill(owner, 1);
        for (int k = 1; k < moves.length; ++ k) {
            int move = moves[k];
            int from = (k & 1) == 1 ? 1 : -1;
            for (int i = moves[0] - 1; i >= 0 && move > 0; -- i) {
                if (owner[i] == from) {
                    move --;
                    owner[i] *= -1;
                }
            }
        }
        Arrays.sort(books);
        Data[] optimum = new Data[m + 1];
        Arrays.fill(optimum, new Data(-INF, -INF));
        optimum[0] = new Data(0, 0);
        for (int book : books) {
            for (int i = m; i >= 1; -- i) {
                optimum[i] = optimum[i].update(optimum[i - 1].add(owner[i - 1] * book));
            }
        }
        Data result = optimum[m];
        return new int[]{(result.sum - result.difference) / 2, (result.sum + result.difference) / 2};
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            HeavyBooksHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                HeavyBooksHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class HeavyBooksHarness {
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
            int[] books               = {5,2,3,1};
            int[] moves               = {3,2,1,1,1};
            int[] expected__          = {3, 7 };

            return verifyCase(casenum__, expected__, new HeavyBooks().findWeight(books, moves));
        }
        case 1: {
            int[] books               = {10, 100, 1000};
            int[] moves               = {2, 3};
            int[] expected__          = {110, 0 };

            return verifyCase(casenum__, expected__, new HeavyBooks().findWeight(books, moves));
        }
        case 2: {
            int[] books               = {155870,565381};
            int[] moves               = {1,1,1};
            int[] expected__          = {0, 565381 };

            return verifyCase(casenum__, expected__, new HeavyBooks().findWeight(books, moves));
        }
        case 3: {
            int[] books               = {500,500,500,500};
            int[] moves               = {4,1,1,3,2};
            int[] expected__          = {500, 1500 };

            return verifyCase(casenum__, expected__, new HeavyBooks().findWeight(books, moves));
        }
        case 4: {
            int[] books               = {1,1,1,1,1000000};
            int[] moves               = {1};
            int[] expected__          = {0, 1000000 };

            return verifyCase(casenum__, expected__, new HeavyBooks().findWeight(books, moves));
        }
        case 5: {
            int[] books               = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
            int[] moves               = {20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
            int[] expected__          = {110, 100 };

            return verifyCase(casenum__, expected__, new HeavyBooks().findWeight(books, moves));
        }

        // custom cases

/*      case 6: {
            int[] books               = ;
            int[] moves               = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new HeavyBooks().findWeight(books, moves));
        }*/
/*      case 7: {
            int[] books               = ;
            int[] moves               = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new HeavyBooks().findWeight(books, moves));
        }*/
/*      case 8: {
            int[] books               = ;
            int[] moves               = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new HeavyBooks().findWeight(books, moves));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
