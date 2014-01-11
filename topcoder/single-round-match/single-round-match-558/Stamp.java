import java.math.*;
import java.util.*;

public class Stamp {
    public int getMinimumCost(String colors, int stampCost, int pushCost) {
        int n = colors.length();
        boolean[][] valid = new boolean[n][n];
        for (int i = 0; i < n; ++ i) {
            Set <Character> set = new HashSet <Character>();
            for (int j = i; j < n; ++ j) {
                if (colors.charAt(j) != '*') {
                    set.add(colors.charAt(j));
                }
                valid[i][j] = set.size() <= 1;
            }
        }
        int result = Integer.MAX_VALUE;
        for (int length = 1; length <= n; ++ length) {
            int[] minimum = new int[n + 1];
            Arrays.fill(minimum, Integer.MAX_VALUE);
            minimum[0] = 0;
            for (int i = 0; i < n; ++ i) {
                if (minimum[i] < Integer.MAX_VALUE) {
                    for (int j = i + length - 1; j < n; ++ j) {
                        if (valid[i][j]) {
                            minimum[j + 1] = Math.min(minimum[j + 1], minimum[i] + (j - i + length) / length);
                        }
                    }
                }
            }
            if (minimum[n] < Integer.MAX_VALUE) {
                result = Math.min(result, length * stampCost + minimum[n] * pushCost);
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
			StampHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				StampHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class StampHarness {
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
			String desiredColor       = "RRGGBB";
			int stampCost             = 1;
			int pushCost              = 1;
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new Stamp().getMinimumCost(desiredColor, stampCost, pushCost));
		}
		case 1: {
			String desiredColor       = "R**GB*";
			int stampCost             = 1;
			int pushCost              = 1;
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new Stamp().getMinimumCost(desiredColor, stampCost, pushCost));
		}
		case 2: {
			String desiredColor       = "BRRB";
			int stampCost             = 2;
			int pushCost              = 7;
			int expected__            = 30;

			return verifyCase(casenum__, expected__, new Stamp().getMinimumCost(desiredColor, stampCost, pushCost));
		}
		case 3: {
			String desiredColor       = "R*RR*GG";
			int stampCost             = 10;
			int pushCost              = 58;
			int expected__            = 204;

			return verifyCase(casenum__, expected__, new Stamp().getMinimumCost(desiredColor, stampCost, pushCost));
		}
		case 4: {
			String desiredColor       = "*B**B**B*BB*G*BBB**B**B*";
			int stampCost             = 5;
			int pushCost              = 2;
			int expected__            = 33;

			return verifyCase(casenum__, expected__, new Stamp().getMinimumCost(desiredColor, stampCost, pushCost));
		}
		case 5: {
			String desiredColor       = "*R*RG*G*GR*RGG*G*GGR***RR*GG";
			int stampCost             = 7;
			int pushCost              = 1;
			int expected__            = 30;

			return verifyCase(casenum__, expected__, new Stamp().getMinimumCost(desiredColor, stampCost, pushCost));
		}

		// custom cases

/*      case 6: {
			String desiredColor       = ;
			int stampCost             = ;
			int pushCost              = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Stamp().getMinimumCost(desiredColor, stampCost, pushCost));
		}*/
/*      case 7: {
			String desiredColor       = ;
			int stampCost             = ;
			int pushCost              = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Stamp().getMinimumCost(desiredColor, stampCost, pushCost));
		}*/
/*      case 8: {
			String desiredColor       = ;
			int stampCost             = ;
			int pushCost              = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Stamp().getMinimumCost(desiredColor, stampCost, pushCost));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
