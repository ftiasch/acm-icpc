import java.math.*;
import java.util.*;

public class EqualizeStrings {
    int distance(int a, int b) {
        int d = Math.abs(a - b);
        return Math.min(d, 26 - d);
    }

    public String getEq(String s, String t) {
        int n = s.length();
        String result = "";
        for (int i = 0; i < n; ++ i) {
            int best = Integer.MAX_VALUE;
            char candidate = 'a';
            for (int c = 0; c < 26; ++ c) {
                int tmp = distance(c, s.charAt(i) - 'a') + distance(c, t.charAt(i) - 'a');
                if (tmp < best) {
                    best = tmp;
                    candidate = (char)('a' + c);
                }
            }
            result += candidate;
        }
        return result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			EqualizeStringsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				EqualizeStringsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class EqualizeStringsHarness {
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
			String s                  = "cat";
			String t                  = "dog";
			String expected__         = "caa";

			return verifyCase(casenum__, expected__, new EqualizeStrings().getEq(s, t));
		}
		case 1: {
			String s                  = "abcdefghijklmnopqrstuvwxyz";
			String t                  = "bcdefghijklmnopqrstuvwxyza";
			String expected__         = "abcdefghijklmnopqrstuvwxya";

			return verifyCase(casenum__, expected__, new EqualizeStrings().getEq(s, t));
		}
		case 2: {
			String s                  = "programmingcompetitionsrule";
			String t                  = "programmingcompetitionsrule";
			String expected__         = "programmingcompetitionsrule";

			return verifyCase(casenum__, expected__, new EqualizeStrings().getEq(s, t));
		}
		case 3: {
			String s                  = "topcoderopen";
			String t                  = "onlinerounds";
			String expected__         = "onlcndaoondn";

			return verifyCase(casenum__, expected__, new EqualizeStrings().getEq(s, t));
		}

		// custom cases

/*      case 4: {
			String s                  = ;
			String t                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new EqualizeStrings().getEq(s, t));
		}*/
/*      case 5: {
			String s                  = ;
			String t                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new EqualizeStrings().getEq(s, t));
		}*/
/*      case 6: {
			String s                  = ;
			String t                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new EqualizeStrings().getEq(s, t));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
