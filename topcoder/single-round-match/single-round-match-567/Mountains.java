import java.math.*;
import java.util.*;

public class Mountains {
    static int MOD = (int)1e9 + 9;

    public int countPlacements(int[] heights, String[] visibility) {
        int n = heights.length;
        int w = visibility[0].length();
        Map <ArrayList <Integer>, Integer> states = new HashMap <ArrayList <Integer>, Integer>();
        if (true) {
            ArrayList <Integer> empty = new ArrayList <Integer>();
            for (int i = 0; i < w; ++ i) {
                empty.add(0);
            }
            states.put(empty, 1);
        }
        for (int k = n - 1; k >= 0; -- k) {
            int h = heights[k];
            Map <ArrayList <Integer>, Integer> newStates = new HashMap <ArrayList <Integer>, Integer>();
            for (Map.Entry <ArrayList <Integer>, Integer> entry : states.entrySet()) {
                ArrayList <Integer> state = entry.getKey();
                for (int x = 0; x < w; ++ x) {
                    boolean valid = true;
                    for (int i = 0; i < w && valid; ++ i) {
                        valid &= (visibility[k].charAt(i) == 'X') == (h - Math.abs(i - x) > state.get(i));
                    }
                    if (valid) {
                        ArrayList <Integer> newState = new ArrayList <Integer>();
                        for (int i = 0; i < w; ++ i) {
                            newState.add(Math.max(state.get(i), h - Math.abs(i - x)));
                        }
                        if (!newStates.containsKey(newState)) {
                            newStates.put(newState, entry.getValue());
                        } else {
                            newStates.put(newState, (newStates.get(newState) + entry.getValue()) % MOD);
                        }
                    }
                }
            }
            states = newStates;
        }
        int result = 0;
        for (Map.Entry <ArrayList <Integer>, Integer> entry : states.entrySet()) {
            result += entry.getValue();
            if (result >= MOD) {
                result -= MOD;
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
			MountainsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				MountainsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class MountainsHarness {
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
			int[] heights             = {2, 3, 2};
			String[] visibility       = {"------",
 "XXXX--",
 "---XXX"};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new Mountains().countPlacements(heights, visibility));
		}
		case 1: {
			int[] heights             = {4, 3, 4};
			String[] visibility       = {"XXXXX--------",
 "----------XXX",
 "----XXXXXXX--"};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new Mountains().countPlacements(heights, visibility));
		}
		case 2: {
			int[] heights             = {13, 2, 3, 2};
			String[] visibility       = {"XXXXXXXXX",
 "-XXX-----",
 "----XXXXX",
 "-----XXX-"};
			int expected__            = 9;

			return verifyCase(casenum__, expected__, new Mountains().countPlacements(heights, visibility));
		}
		case 3: {
			int[] heights             = {8, 2, 9, 3, 10};
			String[] visibility       = {"X------",
 "-------",
 "------X",
 "-------",
 "XXXXXXX"};
			int expected__            = 98;

			return verifyCase(casenum__, expected__, new Mountains().countPlacements(heights, visibility));
		}
		case 4: {
			int[] heights             = {20, 20, 20, 20, 20, 20, 45, 50, 49, 50};
			String[] visibility       = {"-------------------",
 "-------------------",
 "-------------------",
 "-------------------",
 "-------------------",
 "-------------------",
 "-------------------",
 "------------XXXXXXX",
 "XXXXXXX------------",
 "XXXXXXXXXXXXXXXXXXX"}
;
			int expected__            = 973726691;

			return verifyCase(casenum__, expected__, new Mountains().countPlacements(heights, visibility));
		}

		// custom cases

/*      case 5: {
			int[] heights             = ;
			String[] visibility       = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Mountains().countPlacements(heights, visibility));
		}*/
/*      case 6: {
			int[] heights             = ;
			String[] visibility       = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Mountains().countPlacements(heights, visibility));
		}*/
/*      case 7: {
			int[] heights             = ;
			String[] visibility       = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Mountains().countPlacements(heights, visibility));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
