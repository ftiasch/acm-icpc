import java.math.*;
import java.util.*;

public class SwitchesAndLamps {
    public int theMin(String[] switches, String[] lamps) {
        int n = switches[0].length();
        int m = switches.length;
        Map <String, Integer> switchesMap = new HashMap <String, Integer>();
        Map <String, Integer> lampsMap = new HashMap <String, Integer>();
        for (int i = 0; i < n; ++ i) {
            String mask = "";
            for (int j = 0; j < m; ++ j) {
                mask += switches[j].charAt(i);
            }
            int old = switchesMap.containsKey(mask) ? switchesMap.get(mask) : 0;
            switchesMap.put(mask, old + 1);
        }
        for (int i = 0; i < n; ++ i) {
            String mask = "";
            for (int j = 0; j < m; ++ j) {
                mask += lamps[j].charAt(i);
            }
            int old = lampsMap.containsKey(mask) ? lampsMap.get(mask) : 0;
            lampsMap.put(mask, old + 1);
        }
        if (switchesMap.equals(lampsMap)) {
            int maximum = 0;
            for (Map.Entry <String, Integer> iter : switchesMap.entrySet()) {
                maximum = Math.max(maximum, iter.getValue());
            }
            int answer = 0;
            while (maximum > 1) {
                answer ++;
                maximum = maximum + 1 >> 1;
            }
            return answer;
        }
        return -1;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			SwitchesAndLampsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SwitchesAndLampsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SwitchesAndLampsHarness {
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
			String[] switches         = {"NYNN", "NNYN"};
			String[] lamps            = {"NNNY", "NNYN"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new SwitchesAndLamps().theMin(switches, lamps));
		}
		case 1: {
			String[] switches         = {"YYN", "YNY", "YYN"};
			String[] lamps            = {"YNY", "NYY", "YNY"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new SwitchesAndLamps().theMin(switches, lamps));
		}
		case 2: {
			String[] switches         = {"NYYYNYNNYYYNYNNNNY", "NYYYNYNNYYYNYNNNNY"};
			String[] lamps            = {"YYYNNNYNNYNYNYNYNY", "YYYNNNYNNYNYYNNYNY"};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new SwitchesAndLamps().theMin(switches, lamps));
		}
		case 3: {
			String[] switches         = {"YYNNN", "NNYYN"};
			String[] lamps            = {"NYNNY", "NNNYY"};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new SwitchesAndLamps().theMin(switches, lamps));
		}
		case 4: {
			String[] switches         = {"YNNYNNYNYY", "NYNNYNYNYN", "YNYNYYYYYN", "NNYYNYNYNN"};
			String[] lamps            = {"NYYNYNNYNY", "NYYYNNYNNN", "YYYYNYNNYY", "YNNNNYNYYN"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new SwitchesAndLamps().theMin(switches, lamps));
		}

		// custom cases

/*      case 5: {
			String[] switches         = ;
			String[] lamps            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SwitchesAndLamps().theMin(switches, lamps));
		}*/
/*      case 6: {
			String[] switches         = ;
			String[] lamps            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SwitchesAndLamps().theMin(switches, lamps));
		}*/
/*      case 7: {
			String[] switches         = ;
			String[] lamps            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SwitchesAndLamps().theMin(switches, lamps));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
