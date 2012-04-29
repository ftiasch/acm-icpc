import java.math.*;
import java.util.*;

public class ArtShift {
    int gcd(int a, int b) {
        return b == 0? a: gcd(b, a % b);
    }

    int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    void search(HashSet <Integer> values[][], int white, int black, int value) {
        if (!values[white][black].contains(value)) {
            values[white][black].add(value);
            for (int i = 1; i <= white; ++ i) {
                for (int j = 1; j <= black; ++ j) {
                    search(values, white - i, black - j, lcm(value, i + j));
                }
            }
        }
    }
    
    public int maxShifts(String sequence) {
        int n = sequence.length();
        int black = 0;
        int white = 0;
        for (int i = 0; i < n; ++ i) {
            if (sequence.charAt(i) == 'W') {
                white ++;
            } else {
                black ++;
            }
        }
        HashSet values[][] = new HashSet[white + 1][black + 1];
        for (int i = 0; i <= white; ++ i) {
            for (int j = 0; j <= black; ++ j) {
                values[i][j] = new HashSet <Integer>();
            }
        }
        search(values, white, black, 1);
        int result = 0;
        for (int i = 0; i <= white; ++ i) {
            for (int j = 0; j <= black; ++ j) {
                for (Object v: values[i][j]) {
                    result = Math.max(result, (Integer)v);
                }
            }
        }
        return result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			ArtShiftHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ArtShiftHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ArtShiftHarness {
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
			String sequence           = "BW";
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new ArtShift().maxShifts(sequence));
		}
		case 1: {
			String sequence           = "BBBWBBB";
			int expected__            = 7;

			return verifyCase(casenum__, expected__, new ArtShift().maxShifts(sequence));
		}
		case 2: {
			String sequence           = "BWBWB";
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new ArtShift().maxShifts(sequence));
		}
		case 3: {
			String sequence           = "WWWWWWWWW";
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new ArtShift().maxShifts(sequence));
		}
		case 4: {
			String sequence           = "WWWWWWWWWBBWB";
			int expected__            = 60;

			return verifyCase(casenum__, expected__, new ArtShift().maxShifts(sequence));
		}

		// custom cases

/*      case 5: {
			String sequence           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ArtShift().maxShifts(sequence));
		}*/
/*      case 6: {
			String sequence           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ArtShift().maxShifts(sequence));
		}*/
/*      case 7: {
			String sequence           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ArtShift().maxShifts(sequence));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
