import java.math.*;
import java.util.*;

public class OrderedNim {
    public String winner(int[] layout) {
        int n = layout.length;
        int win = 1;
        for (int i = n - 2; i >= 0; -- i) {
            if (layout[i] == 1) {
                win ^= 1;
            } else {
                win = 1;
            }
        }
        return win == 1 ? "Alice" : "Bob";
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			OrderedNimHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				OrderedNimHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class OrderedNimHarness {
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
			int[] layout              = {5};
			String expected__         = "Alice";

			return verifyCase(casenum__, expected__, new OrderedNim().winner(layout));
		}
		case 1: {
			int[] layout              = {1,2};
			String expected__         = "Bob";

			return verifyCase(casenum__, expected__, new OrderedNim().winner(layout));
		}
		case 2: {
			int[] layout              = {2,1};
			String expected__         = "Alice";

			return verifyCase(casenum__, expected__, new OrderedNim().winner(layout));
		}
		case 3: {
			int[] layout              = {10,9,8,7,6,5,4,3,2,1};
			String expected__         = "Alice";

			return verifyCase(casenum__, expected__, new OrderedNim().winner(layout));
		}
		case 4: {
			int[] layout              = {1,1,1,1};
			String expected__         = "Bob";

			return verifyCase(casenum__, expected__, new OrderedNim().winner(layout));
		}

		// custom cases

/*      case 5: {
			int[] layout              = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new OrderedNim().winner(layout));
		}*/
/*      case 6: {
			int[] layout              = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new OrderedNim().winner(layout));
		}*/
/*      case 7: {
			int[] layout              = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new OrderedNim().winner(layout));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
