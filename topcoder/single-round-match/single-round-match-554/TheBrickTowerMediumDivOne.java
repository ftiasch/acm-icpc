import java.math.*;
import java.util.*;

public class TheBrickTowerMediumDivOne {
    public int[] find(int[] heights) {
        int n = heights.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; ++ i) {
            pairs[i] = new Pair(heights[i], i);
        }
        Arrays.sort(pairs);
        int minimum = 0;
        for (int i = 1; i < n; ++ i) {
            minimum += pairs[i].value;
        }
        int[] result = new int[n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; ++ i) {
            int j = 0;
            while (j < n) {
                if (!used[j]) {
                    used[j] = true;
                    result[i] = j;
                    int[] newResult = result.clone();
                    int length = i + 1;
                    for (int k = 0; k < n; ++ k) {
                        if (!used[pairs[k].index]) {
                            newResult[length ++] = pairs[k].index;
                        }
                    }
                    int newMinimum = 0;
                    for (int k = 1; k < n; ++ k) {
                        newMinimum += Math.max(heights[newResult[k]], heights[newResult[k - 1]]);
                    }
                    if (newMinimum == minimum) {
                        break;
                    }
                    used[j] = false;
                }
                j ++;
            }
            assert j < n;
        }
        return result;
    }

    private class Pair implements Comparable {
        int value, index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Object other) {
            return value - ((Pair)other).value;
        }
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TheBrickTowerMediumDivOneHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheBrickTowerMediumDivOneHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheBrickTowerMediumDivOneHarness {
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
			int[] heights             = {4, 7, 5};
			int[] expected__          = {0, 2, 1 };

			return verifyCase(casenum__, expected__, new TheBrickTowerMediumDivOne().find(heights));
		}
		case 1: {
			int[] heights             = {4, 4, 4, 4, 4, 4, 4};
			int[] expected__          = {0, 1, 2, 3, 4, 5, 6 };

			return verifyCase(casenum__, expected__, new TheBrickTowerMediumDivOne().find(heights));
		}
		case 2: {
			int[] heights             = {2, 3, 3, 2};
			int[] expected__          = {0, 3, 1, 2 };

			return verifyCase(casenum__, expected__, new TheBrickTowerMediumDivOne().find(heights));
		}
		case 3: {
			int[] heights             = {13, 32, 38, 25, 43, 47, 6};
			int[] expected__          = {0, 6, 3, 1, 2, 4, 5 };

			return verifyCase(casenum__, expected__, new TheBrickTowerMediumDivOne().find(heights));
		}

		// custom cases

/*      case 4: {
			int[] heights             = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new TheBrickTowerMediumDivOne().find(heights));
		}*/
/*      case 5: {
			int[] heights             = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new TheBrickTowerMediumDivOne().find(heights));
		}*/
/*      case 6: {
			int[] heights             = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new TheBrickTowerMediumDivOne().find(heights));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
