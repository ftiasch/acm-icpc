import java.math.*;
import java.util.*;

public class CandidateKeys {
    public int[] getKeys(String[] table) {
        int n = table.length;
        int m = table[0].length();
        boolean[] candidate = new boolean[1 << m];
        for (int mask = 0; mask < 1 << m; ++ mask) {
            boolean valid = true;
            for (int i = 0; i < n && valid; ++ i) {
                for (int j = i + 1; j < n && valid; ++ j) {
                    boolean found = false;
                    for (int k = 0; k < m && !found; ++ k) {
                        found |= (mask >> k & 1) == 1 && table[i].charAt(k) != table[j].charAt(k);
                    }
                    valid &= found;
                }
            }
            candidate[mask] = valid;
        }
        int minSize = Integer.MAX_VALUE;
        int maxSize = Integer.MIN_VALUE;
        for (int mask = (1 << m) - 1; mask >= 0; -- mask) {
            for (int subMask = (mask - 1) & mask; subMask > 0; subMask = (subMask - 1) & mask) {
                if (candidate[subMask]) {
                    candidate[mask] = false;
                }
            }
            if (candidate[mask]) {
                int one = 0;
                for (int k = 0; k < m; ++ k) {
                    one += mask >> k & 1;
                }
                minSize = Math.min(minSize, one);
                maxSize = Math.max(maxSize, one);
            }
        }
        if (minSize == Integer.MAX_VALUE) {
            return new int[] {};
        }
        return new int[] {minSize, maxSize};
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			CandidateKeysHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CandidateKeysHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CandidateKeysHarness {
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
			String[] table            = {
"ABC",
"ABC",
"ABC"
};
			int[] expected__          = { };

			return verifyCase(casenum__, expected__, new CandidateKeys().getKeys(table));
		}
		case 1: {
			String[] table            = {
"ABC",
"ABD",
"ABE"
};
			int[] expected__          = {1, 1 };

			return verifyCase(casenum__, expected__, new CandidateKeys().getKeys(table));
		}
		case 2: {
			String[] table            = {
"ABC",
"ACD",
"BBE"
};
			int[] expected__          = {1, 2 };

			return verifyCase(casenum__, expected__, new CandidateKeys().getKeys(table));
		}
		case 3: {
			String[] table            = {"A","B"};
			int[] expected__          = {1, 1 };

			return verifyCase(casenum__, expected__, new CandidateKeys().getKeys(table));
		}
		case 4: {
			String[] table            = {
"AABB",
"BABA",
"CAAB",
"DAAA",
"EBBB",
"FBBA",
"GBAB",
"HBAA"
};
			int[] expected__          = {1, 3 };

			return verifyCase(casenum__, expected__, new CandidateKeys().getKeys(table));
		}

		// custom cases

      case 5: {
			String[] table            = {"AAABA", "BBBAB", "BBBAA", "AAABB"};
			int[] expected__          = {2, 2};

			return verifyCase(casenum__, expected__, new CandidateKeys().getKeys(table));
		}
/*      case 6: {
			String[] table            = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new CandidateKeys().getKeys(table));
		}*/
/*      case 7: {
			String[] table            = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new CandidateKeys().getKeys(table));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
