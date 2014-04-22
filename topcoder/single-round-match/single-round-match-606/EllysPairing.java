import java.math.*;
import java.util.*;

public class EllysPairing {
    public int getMax(int m, int[] count, int[] first, int[] mult, int[] add) {
        int result = 0;
        int name = 0;
        int number = 0;
        for (int i = 0; i < count.length; ++ i) {
            int next = first[i];
            for (int j = 0; j < count[i]; ++ j) {
                if (number == 0 || name == next) {
                    name = next;
                    number ++;
                } else {
                    number --;
                    result ++;
                }
                next = next * mult[i] + add[i] & m - 1;
            }
        }
        number = 0;
        int sum = 0;
        for (int i = 0; i < count.length; ++ i) {
            sum += count[i];
            int next = first[i];
            for (int j = 0; j < count[i]; ++ j) {
                if (name == next) {
                    number ++;
                }
                next = next * mult[i] + add[i] & m - 1;
            }
        }
        return number * 2 <= sum ? sum / 2 : sum - number;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysPairingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysPairingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysPairingHarness {
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
            int M                     = 16;
            int[] count               = {4, 7};
            int[] first               = {5, 3};
            int[] mult                = {2, 3};
            int[] add                 = {1, 0};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new EllysPairing().getMax(M, count, first, mult, add));
        }
        case 1: {
            int M                     = 8;
            int[] count               = {6, 4, 3};
            int[] first               = {0, 3, 2};
            int[] mult                = {3, 7, 5};
            int[] add                 = {0, 3, 2};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new EllysPairing().getMax(M, count, first, mult, add));
        }
        case 2: {
            int M                     = 128;
            int[] count               = {42, 13, 666, 17, 1337, 42, 1};
            int[] first               = {18, 76, 3, 122, 0, 11, 11};
            int[] mult                = {33, 17, 54, 90, 41, 122, 20};
            int[] add                 = {66, 15, 10, 121, 122, 1, 30};
            int expected__            = 1059;

            return verifyCase(casenum__, expected__, new EllysPairing().getMax(M, count, first, mult, add));
        }
        case 3: {
            int M                     = 1048576;
            int[] count               = {4242, 42, 9872, 13, 666, 21983, 17, 1337, 42, 1};
            int[] first               = {19, 18, 76, 42, 3, 112, 0, 11, 11, 12};
            int[] mult                = {27, 33, 10, 8, 17, 9362, 90, 41, 122, 20};
            int[] add                 = {98, 101, 66, 15, 10, 144, 3, 1, 5, 1};
            int expected__            = 16232;

            return verifyCase(casenum__, expected__, new EllysPairing().getMax(M, count, first, mult, add));
        }
        case 4: {
            int M                     = 1073741824;
            int[] count               = {894602, 946569, 887230, 856152, 962583, 949356, 904847, 829100, 842183, 958440, 811081, 864078, 809209, 938727, 949135, 892809, 816528, 961237, 979142, 890922};
            int[] first               = {844085078, 898937259, 243490172, 887804102, 187696417, 156820442, 237600210, 618812924, 153000239, 912364033, 254936966, 650298774, 982988140, 649258331, 566444626, 201481721, 492943390, 1061953081, 492672963, 960519711};
            int[] mult                = {1036482037, 583219072, 819168094, 253755052, 548208982, 401830167, 638626082, 43642932, 123607749, 485521178, 860368129, 30334704, 219771462, 733375600, 130839219, 415503960, 294132484, 1044831462, 256910484, 198852170};
            int[] add                 = {889169006, 604831366, 967292994, 980686280, 844875791, 1027687492, 357734882, 295879743, 48284748, 421729100, 1049536313, 327207332, 948053446, 271229570, 664579359, 795815285, 842856528, 876662975, 675611938, 634229925};
            int expected__            = 8971965;

            return verifyCase(casenum__, expected__, new EllysPairing().getMax(M, count, first, mult, add));
        }

        // custom cases

/*      case 5: {
            int M                     = ;
            int[] count               = ;
            int[] first               = ;
            int[] mult                = ;
            int[] add                 = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysPairing().getMax(M, count, first, mult, add));
        }*/
/*      case 6: {
            int M                     = ;
            int[] count               = ;
            int[] first               = ;
            int[] mult                = ;
            int[] add                 = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysPairing().getMax(M, count, first, mult, add));
        }*/
/*      case 7: {
            int M                     = ;
            int[] count               = ;
            int[] first               = ;
            int[] mult                = ;
            int[] add                 = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysPairing().getMax(M, count, first, mult, add));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
