import java.util.*;
import java.math.*;

public class SurveillanceSystem {
    public String getContainerInfo(String containers, int[] reports, int length) {
        int n = containers.length();
        int[] sum = new int[n + 1];
        for (int i = n - 1; i >= 0; -- i) {
            sum[i] = sum[i + 1];
            if (containers.charAt(i) == 'X') {
                sum[i] ++;
            }
        }
        int[] count = new int[n + 1];
        for (int report : reports) {
            count[report] ++;
        }
        String answer = "";
        for (int i = 0; i < n; ++ i) {
            boolean canBe = false;
            int[] newCount = count.clone();
            for (int j = 0; j + length <= n; ++ j) {
                int size = sum[j] - sum[j + length];
                if (j <= i && i < j + length) {
                    if (count[size] > 0) {
                        canBe = true;
                    }
                } else {
                    newCount[size] --;
                }
            }
            boolean cannotBe = true;
            for (int j = 0; j <= n; ++ j) {
                cannotBe &= newCount[j] <= 0;
            }
            if (canBe && cannotBe) {
                answer += '?';
            } else {
                if (canBe) {
                    answer += '+';
                }
                if (cannotBe) {
                    answer += '-';
                }
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			SurveillanceSystemHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SurveillanceSystemHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SurveillanceSystemHarness {
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
			String containers         = "-X--XX";
			int[] reports             = {1, 2};
			int L                     = 3;
			String expected__         = "??++++";

			return verifyCase(casenum__, expected__, new SurveillanceSystem().getContainerInfo(containers, reports, L));
		}
		case 1: {
			String containers         = "-XXXXX-";
			int[] reports             = {2};
			int L                     = 3;
			String expected__         = "???-???";

			return verifyCase(casenum__, expected__, new SurveillanceSystem().getContainerInfo(containers, reports, L));
		}
		case 2: {
			String containers         = "------X-XX-";
			int[] reports             = {3, 0, 2, 0};
			int L                     = 5;
			String expected__         = "++++++++++?";

			return verifyCase(casenum__, expected__, new SurveillanceSystem().getContainerInfo(containers, reports, L));
		}
		case 3: {
			String containers         = "-XXXXX---X--";
			int[] reports             = {2, 1, 0, 1};
			int L                     = 3;
			String expected__         = "???-??++++??";

			return verifyCase(casenum__, expected__, new SurveillanceSystem().getContainerInfo(containers, reports, L));
		}
		case 4: {
			String containers         = "-XX--X-XX-X-X--X---XX-X---XXXX-----X";
			int[] reports             = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
			int L                     = 7;
			String expected__         = "???++++?++++++++++++++++++++??????--";

			return verifyCase(casenum__, expected__, new SurveillanceSystem().getContainerInfo(containers, reports, L));
		}

		// custom cases

/*      case 5: {
			String containers         = ;
			int[] reports             = ;
			int L                     = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new SurveillanceSystem().getContainerInfo(containers, reports, L));
		}*/
/*      case 6: {
			String containers         = ;
			int[] reports             = ;
			int L                     = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new SurveillanceSystem().getContainerInfo(containers, reports, L));
		}*/
/*      case 7: {
			String containers         = ;
			int[] reports             = ;
			int L                     = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new SurveillanceSystem().getContainerInfo(containers, reports, L));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
