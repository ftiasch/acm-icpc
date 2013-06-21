import java.math.*;
import java.util.*;

public class TheDevice {
    public int minimumAdditional(String[] plates) {
        int n = plates.length;
        int m = plates[0].length();
        int answer = 0;
        for (int j = 0; j < m; ++ j) {
            int[] count = new int[2];
            for (int i = 0; i < n; ++ i) {
                count[plates[i].charAt(j) - '0'] ++;
            }
            answer = Math.max(answer, 3 - Math.min(count[0], 1) - Math.min(count[1], 2));
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TheDeviceHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheDeviceHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheDeviceHarness {
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
			String[] plates           = {"010",
 "011",
 "101"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new TheDevice().minimumAdditional(plates));
		}
		case 1: {
			String[] plates           = {"0",
 "1",
 "0",
 "1"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new TheDevice().minimumAdditional(plates));
		}
		case 2: {
			String[] plates           = {"01010101",
 "10101010"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new TheDevice().minimumAdditional(plates));
		}
		case 3: {
			String[] plates           = {"10010101011",
 "00010101001",
 "00100010111",
 "00101010101",
 "01010111101"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new TheDevice().minimumAdditional(plates));
		}
		case 4: {
			String[] plates           = {"1101001011010",
 "0010000010101",
 "1010101011110",
 "1101010100111",
 "1011111110111"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new TheDevice().minimumAdditional(plates));
		}

		// custom cases

/*      case 5: {
			String[] plates           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheDevice().minimumAdditional(plates));
		}*/
/*      case 6: {
			String[] plates           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheDevice().minimumAdditional(plates));
		}*/
/*      case 7: {
			String[] plates           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheDevice().minimumAdditional(plates));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
