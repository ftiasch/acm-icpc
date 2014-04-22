import java.math.*;
import java.util.*;

public class EllysNumberGuessing {
    public int getNumber(int[] guesses, int[] answers) {
        int count = 0;
        int result = -1;
        if (check(guesses, answers, guesses[0] - answers[0])) {
            result = guesses[0] - answers[0];
            count ++;
        }
        if (answers[0] > 0 && check(guesses, answers, guesses[0] + answers[0])) {
            result = guesses[0] + answers[0];
            count ++;
        }
        if (count == 0) {
            return -2;
        }
        if (count == 2) {
            return -1;
        }
        return result;
    }

    boolean check(int[] guesses, int[] answers, int number) {
        if (number < 1 || number > 1000000000) {
            return false;
        }
        for (int i = 0; i < guesses.length; ++ i) {
            if (Math.abs(guesses[i] - number) != answers[i]) {
                return false;
            }
        }
        return true;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysNumberGuessingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysNumberGuessingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysNumberGuessingHarness {
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
            int[] guesses             = {600, 594};
            int[] answers             = {6, 12};
            int expected__            = 606;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }
        case 1: {
            int[] guesses             = {100, 50, 34, 40};
            int[] answers             = {58, 8, 8, 2};
            int expected__            = 42;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }
        case 2: {
            int[] guesses             = {500000, 600000, 700000};
            int[] answers             = {120013, 220013, 79987};
            int expected__            = -2;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }
        case 3: {
            int[] guesses             = {500000000};
            int[] answers             = {133742666};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }
        case 4: {
            int[] guesses             = {76938260, 523164588, 14196746, 296286419, 535893832, 41243148, 364561227, 270003278, 472017422, 367932361, 395758413, 301278456, 186276934, 316343129, 336557549, 52536121, 98343562, 356769915, 89249181, 335191879};
            int[] answers             = {466274085, 20047757, 529015599, 246925926, 7318513, 501969197, 178651118, 273209067, 71194923, 175279984, 147453932, 241933889, 356935411, 226869216, 206654796, 490676224, 444868783, 186442430, 453963164, 208020466};
            int expected__            = 543212345;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }
        case 5: {
            int[] guesses             = {42};
            int[] answers             = {42};
            int expected__            = 84;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }
        case 6: {
            int[] guesses             = {999900000};
            int[] answers             = {100001};
            int expected__            = 999799999;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }

        // custom cases

/*      case 7: {
            int[] guesses             = ;
            int[] answers             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }*/
/*      case 8: {
            int[] guesses             = ;
            int[] answers             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }*/
/*      case 9: {
            int[] guesses             = ;
            int[] answers             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysNumberGuessing().getNumber(guesses, answers));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
