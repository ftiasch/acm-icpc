import java.math.*;
import java.util.*;

public class FoxAndHandle {
    boolean check(String string, String result, int[] count) {
        int p = 0;
        for (int i = 0; i < result.length(); ++ i) {
            while (p < string.length() && result.charAt(i) != string.charAt(p)) {
                p ++;
            }
            if (p == string.length()) {
                return false;
            }
            p ++;
        }
        count = count.clone();
        for (int i = p; i < string.length(); ++ i) {
            count[string.charAt(i) - 'a'] --;
        }
        for (int i = 0; i < 26; ++ i) {
            if (count[i] > 0) {
                return false;
            }
        }
        return true;
    }

    public String lexSmallestName(String string) {
        int n = string.length();
        int[] count = new int[26];
        for (int i = 0; i < n; ++ i) {
            count[string.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < 26; ++ i) {
            count[i] /= 2;
        }
        String result = "";
        for (int i = 0; i < n / 2; ++ i) {
            boolean found = false;
            int c = 0;
            for (; c < 26 && !found; ++ c) {
                if (count[c] > 0) {
                    count[c] --;
                    found |= check(string, result + String.format("%c", c + 'a'), count);
                    if (found) {
                        break;
                    }
                    count[c] ++;
                }
            }
            result += String.format("%c", c + 'a');
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FoxAndHandleHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FoxAndHandleHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndHandleHarness {
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
			String S                  = "foxfox";
			String expected__         = "fox";

			return verifyCase(casenum__, expected__, new FoxAndHandle().lexSmallestName(S));
		}
		case 1: {
			String S                  = "ccieliel";
			String expected__         = "ceil";

			return verifyCase(casenum__, expected__, new FoxAndHandle().lexSmallestName(S));
		}
		case 2: {
			String S                  = "abaabbab";
			String expected__         = "aabb";

			return verifyCase(casenum__, expected__, new FoxAndHandle().lexSmallestName(S));
		}
		case 3: {
			String S                  = "bbbbaaaa";
			String expected__         = "bbaa";

			return verifyCase(casenum__, expected__, new FoxAndHandle().lexSmallestName(S));
		}
		case 4: {
			String S                  = "fedcbafedcba";
			String expected__         = "afedcb";

			return verifyCase(casenum__, expected__, new FoxAndHandle().lexSmallestName(S));
		}
		case 5: {
			String S                  = "nodevillivedon";
			String expected__         = "deilvon";

			return verifyCase(casenum__, expected__, new FoxAndHandle().lexSmallestName(S));
		}

		// custom cases

/*      case 6: {
			String S                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new FoxAndHandle().lexSmallestName(S));
		}*/
/*      case 7: {
			String S                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new FoxAndHandle().lexSmallestName(S));
		}*/
/*      case 8: {
			String S                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new FoxAndHandle().lexSmallestName(S));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
