import java.math.*;
import java.util.*;

public class TheMatrix {
    public int MaxArea(String[] board) {
        int n = board.length;
        int m = board[0].length();
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            boolean[] valid = new boolean[m];
            Arrays.fill(valid, true);
            for (int j = i; j < n; ++ j) {
                if (j > i) {
                    for (int k = 0; k < m; ++ k) {
                        valid[k] &= board[j - 1].charAt(k) != board[j].charAt(k);
                    }
                }
                int length = 0;
                for (int k = 0; k < m; ++ k) {
                    if (valid[k]) {
                        if (k > 0 && board[j].charAt(k) == board[j].charAt(k - 1)) {
                            length = 0;
                        }
                        length ++;
                        result = Math.max(result, length * (j - i + 1));
                    } else {
                        length = 0;
                    }
                }
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheMatrixHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheMatrixHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheMatrixHarness {
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
            String[] board            = {"1",
 "0"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }
        case 1: {
            String[] board            = {"0000"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }
        case 2: {
            String[] board            = {"01"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }
        case 3: {
            String[] board            = {"001",
 "000",
 "100"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }
        case 4: {
            String[] board            = {"0"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }
        case 5: {
            String[] board            = {"101",
 "010"};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }
        case 6: {
            String[] board            = {"101",
 "011",
 "101",
 "010"};
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }
        case 7: {
            String[] board            = {"11001110011000110001111001001110110011010110001011",
 "10100100010111111011111001011110101111010011100001",
 "11101111001110100110010101101100011100101000010001",
 "01000010001010101100010011111000100100110111111000",
 "10110100000101100000111000100001011101111101010010",
 "00111010000011100001110110010011010110010011100100",
 "01100001111101001101001101100001111000111001101010",
 "11010000000011011010100010000000111011001001100101",
 "10100000000100010100100011010100110110110001000001",
 "01101010101100001100000110100110100000010100100010",
 "11010000001110111111011010011110001101100011100010",
 "11101111000000011010011100100001100011111111110111",
 "11000001101100100011000110111010011001010100000001",
 "00100001111001010000101101100010000001100100001000",
 "01001110110111101011010000111111101011000110010111",
 "01001010000111111001100000100010101100100101010100",
 "11111101001101110011011011011000111001101100011011",
 "10000100110111000001110110010000000000111100101101",
 "01010011101101101110000011000110011111001111011100",
 "01101010011111010000011001111101011010011100001101",
 "11011000011000110010101111100000101011011111101100",
 "11100001001000110010100011001010101101001010001100",
 "11011011001100111101001100111100000101011101101011",
 "11110111100100101011100101111101000111001111110111",
 "00011001100110111100111100001100101001111100001111",
 "10001111100101110111001111100000000011110000100111",
 "10101010110110100110010001001010000111100110100011",
 "01100110100000001110101001101011001010001101110101",
 "10110101110100110110101001100111110000101111100110",
 "01011000001001101110100001101001110011001001110001",
 "00100101010001100110110101001010010100001011000011",
 "00011101100100001010100000000011000010100110011100",
 "11001001011000000101111111000000110010001101101110",
 "10101010110110010000010011001100110101110100111011",
 "01101001010111010001101000100011101001110101000110",
 "00110101101110110001110101110010100100110000101101",
 "11010101000111010011110011000001101111010011110011",
 "10010000010001110011011101001110110010001100011100",
 "00111101110001001100101001110100110010100110110000",
 "00010011011000101000100001101110111100100000010100",
 "01101110001101000001001000001011101010011101011110",
 "00000100110011001011101011110011011101100001110111",
 "00110011110000011001011100001110101010100110010110",
 "00111001010011011111010100000100100000101101110001",
 "10101101101110111110000011111011001011100011110001",
 "00101110010101111000001010110100001110111011100011",
 "01111110010100111010110001111000111101110100111011"};
            int expected__            = 12;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }

        // custom cases

/*      case 8: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }*/
/*      case 9: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }*/
/*      case 10: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheMatrix().MaxArea(board));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
