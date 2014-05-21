import java.math.*;
import java.util.*;

public class CrossingTheRiver {
    public String isItEvenPossible(int waterWidth, int landWidth, int[] blockHeight, int depth) {
        int depthCount = 0;
        for (int block : blockHeight) {
            if (block == depth) {
                depthCount ++;
            }
        }
        if (depthCount >= waterWidth) {
            return "POSSIBLE";
        }
        for (int waterLeft = depth; waterLeft <= depth + 1; ++ waterLeft) {
            for (int waterRight = waterLeft; waterRight < waterLeft + waterWidth; ++ waterRight) {
                for (int landLeft = waterRight - depth; landLeft <= waterRight - depth + 1; ++ landLeft) {
                    for (int landRight = landLeft; landRight < landLeft + landWidth; ++ landRight) {
                        if (waterRight > 100 || landRight > 100) {
                            continue;
                        }
                        int[] count = new int[101];
                        for (int i = waterLeft; i <= waterRight; ++ i) {
                            count[i] ++;
                        }
                        for (int i = landLeft; i <= landRight; ++ i) {
                            count[i] ++;
                        }
                        int waterCount = 0;
                        int landCount = 0;
                        int total = 0;
                        for (int block : blockHeight) {
                            count[block] --;
                            boolean found = false;
                            if (waterLeft <= block && block <= waterRight) {
                                waterCount ++;
                                found = true;
                            }
                            if (landLeft <= block && block <= landRight) {
                                landCount ++;
                                found = true;
                            }
                            if (found) {
                                total ++;
                            }
                        }
                        boolean valid = true;
                        for (int c : count) {
                            valid &= c <= 0;
                        }
                        if (valid && waterCount >= waterWidth && landCount >= landWidth && total >= waterWidth + landWidth) {
                            return "POSSIBLE";
                        }
                    }
                }
            }
        }
        return "IMPOSSIBLE";
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            CrossingTheRiverHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                CrossingTheRiverHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class CrossingTheRiverHarness {
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

    static boolean compareOutput(String expected, String result) { return expected.equals(result); }
    static String formatResult(String res) {
        return String.format("\"%s\"", res);
    }

    static int verifyCase(int casenum, String expected, String received) {
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
            int waterWidth            = 3;
            int landWidth             = 3;
            int[] blockHeight         = {3,4,4,5,5, 6};
            int depth                 = 2;
            String expected__         = "POSSIBLE";

            return verifyCase(casenum__, expected__, new CrossingTheRiver().isItEvenPossible(waterWidth, landWidth, blockHeight, depth));
        }
        case 1: {
            int waterWidth            = 3;
            int landWidth             = 3;
            int[] blockHeight         = {3,4,4,5,6, 6};
            int depth                 = 2;
            String expected__         = "IMPOSSIBLE";

            return verifyCase(casenum__, expected__, new CrossingTheRiver().isItEvenPossible(waterWidth, landWidth, blockHeight, depth));
        }
        case 2: {
            int waterWidth            = 5;
            int landWidth             = 2;
            int[] blockHeight         = {4,4,4,4,4};
            int depth                 = 4;
            String expected__         = "POSSIBLE";

            return verifyCase(casenum__, expected__, new CrossingTheRiver().isItEvenPossible(waterWidth, landWidth, blockHeight, depth));
        }
        case 3: {
            int waterWidth            = 5;
            int landWidth             = 5;
            int[] blockHeight         = {5,5,5,5,5,5, 2,3,4,4,6, 7, 3, 2};
            int depth                 = 2;
            String expected__         = "POSSIBLE";

            return verifyCase(casenum__, expected__, new CrossingTheRiver().isItEvenPossible(waterWidth, landWidth, blockHeight, depth));
        }
        case 4: {
            int waterWidth            = 5;
            int landWidth             = 7;
            int[] blockHeight         = {6,6,6,6,6,6,6, 6,6,6,6,6,7,8,9,10};
            int depth                 = 5;
            String expected__         = "POSSIBLE";

            return verifyCase(casenum__, expected__, new CrossingTheRiver().isItEvenPossible(waterWidth, landWidth, blockHeight, depth));
        }
        case 5: {
            int waterWidth            = 1;
            int landWidth             = 1;
            int[] blockHeight         = {5,3,4};
            int depth                 = 2;
            String expected__         = "IMPOSSIBLE";

            return verifyCase(casenum__, expected__, new CrossingTheRiver().isItEvenPossible(waterWidth, landWidth, blockHeight, depth));
        }

        // custom cases

/*      case 6: {
            int waterWidth            = ;
            int landWidth             = ;
            int[] blockHeight         = ;
            int depth                 = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new CrossingTheRiver().isItEvenPossible(waterWidth, landWidth, blockHeight, depth));
        }*/
/*      case 7: {
            int waterWidth            = ;
            int landWidth             = ;
            int[] blockHeight         = ;
            int depth                 = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new CrossingTheRiver().isItEvenPossible(waterWidth, landWidth, blockHeight, depth));
        }*/
/*      case 8: {
            int waterWidth            = ;
            int landWidth             = ;
            int[] blockHeight         = ;
            int depth                 = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new CrossingTheRiver().isItEvenPossible(waterWidth, landWidth, blockHeight, depth));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
