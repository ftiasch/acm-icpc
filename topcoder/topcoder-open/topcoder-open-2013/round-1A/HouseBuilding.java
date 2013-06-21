import java.util.*;
import java.math.*;

public class HouseBuilding {
    public int getMinimum(String[] area) {		
        int n = area.length;
        int m = area[0].length();
        int answer = Integer.MAX_VALUE;
        for (int h = 0; h + 1 < 10; ++ h) {
            int count = 0;
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    int a = area[i].charAt(j) - '0';
                    if (a < h) {
                        count += h - a;
                    }
                    if (a > h + 1) {
                        count += a - h - 1;
                    }
                }
            }
            answer = Math.min(answer, count);
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			HouseBuildingHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				HouseBuildingHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class HouseBuildingHarness {
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
			String[] area             = {"10",
 "31"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new HouseBuilding().getMinimum(area));
		}
		case 1: {
			String[] area             = {"54454",
 "61551"};
			int expected__            = 7;

			return verifyCase(casenum__, expected__, new HouseBuilding().getMinimum(area));
		}
		case 2: {
			String[] area             = {"989"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new HouseBuilding().getMinimum(area));
		}
		case 3: {
			String[] area             = {"90"};
			int expected__            = 8;

			return verifyCase(casenum__, expected__, new HouseBuilding().getMinimum(area));
		}
		case 4: {
			String[] area             = {"5781252",
 "2471255",
 "0000291",
 "1212489"};
			int expected__            = 53;

			return verifyCase(casenum__, expected__, new HouseBuilding().getMinimum(area));
		}

		// custom cases

/*      case 5: {
			String[] area             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new HouseBuilding().getMinimum(area));
		}*/
/*      case 6: {
			String[] area             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new HouseBuilding().getMinimum(area));
		}*/
/*      case 7: {
			String[] area             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new HouseBuilding().getMinimum(area));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
