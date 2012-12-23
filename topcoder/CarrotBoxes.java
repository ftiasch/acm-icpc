import java.math.*;
import java.util.*;

public class CarrotBoxes {
    public double theProbability(String[] information) {
        int n = information.length;
        boolean[][] reachable = new boolean[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                reachable[i][j] = information[i].charAt(j) == 'Y';
            }
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    reachable[i][j] |= reachable[i][k] && reachable[k][j];
                }
            }
        }
        boolean found = false;
        boolean[] mark = new boolean[n];
        ArrayList <Integer> sources = new ArrayList <Integer>();
        ArrayList <Integer> extras = new ArrayList <Integer>();
        for (int i = 0; i < n; ++ i) {
            if (!mark[i]) {
                int size = 0;
                for (int j = 0; j < n; ++ j) {
                    if (reachable[i][j] && reachable[j][i]) {
                        size ++;
                        mark[j] = true;
                    }
                }
                boolean valid = true;
                for (int j = 0; j < n; ++ j) {
                    if (i != j) {
                        valid &= !reachable[j][i] || reachable[i][j];
                    }
                }
                if (valid) {
                    sources.add(i);
                    if (size == 1) {
                        extras.add(i);
                    }
                }
            }
        }
        int answer = sources.size();
        for (int e : extras) {
            Arrays.fill(mark, false);
            for (int i : sources) {
                if (i != e) {
                    for (int j = 0; j < n; ++ j) {
                        if (reachable[i][j]) {
                            mark[j] = true;
                        }
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < n; ++ i) {
                if (!mark[i]) {
                    count ++;
                }
            }
            if (count <= 1) {
                answer = sources.size() - 1;
            }
        }
        return 1 - (double)answer / n;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			CarrotBoxesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CarrotBoxesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CarrotBoxesHarness {
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
	
	static final double MAX_DOUBLE_ERROR = 1E-9;
	static boolean compareOutput(double expected, double result){ if(Double.isNaN(expected)){ return Double.isNaN(result); }else if(Double.isInfinite(expected)){ if(expected > 0){ return result > 0 && Double.isInfinite(result); }else{ return result < 0 && Double.isInfinite(result); } }else if(Double.isNaN(result) || Double.isInfinite(result)){ return false; }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){ return true; }else{ double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > min && result < max; } }
	static double relativeError(double expected, double result) { if (Double.isNaN(expected) || Double.isInfinite(expected) || Double.isNaN(result) || Double.isInfinite(result) || expected == 0) return 0; return Math.abs(result-expected) / Math.abs(expected); }
	
	static String formatResult(double res) {
		return String.format("%.10g", res);
	}
	
	static int verifyCase(int casenum, double expected, double received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.print("PASSED");
			double rerr = relativeError(expected, received);
			if (rerr > 0) System.err.printf(" (relative error %g)", rerr);
			System.err.println();
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
			String[] information      = {"YYYYY",
 "NYNNN",
 "NNYNN",
 "NNNYN",
 "NNNNY"}
;
			double expected__         = 0.8;

			return verifyCase(casenum__, expected__, new CarrotBoxes().theProbability(information));
		}
		case 1: {
			String[] information      = {"YNNNN",
 "NYNNN",
 "NNYNN",
 "NNNYN",
 "NNNNY"};
			double expected__         = 0.2;

			return verifyCase(casenum__, expected__, new CarrotBoxes().theProbability(information));
		}
		case 2: {
			String[] information      = {"Y"};
			double expected__         = 1.0;

			return verifyCase(casenum__, expected__, new CarrotBoxes().theProbability(information));
		}
		case 3: {
			String[] information      = {"YNNNN",
 "YYNNN",
 "YNYNN",
 "NNNYY",
 "NNNYY"}
;
			double expected__         = 0.6;

			return verifyCase(casenum__, expected__, new CarrotBoxes().theProbability(information));
		}
		case 4: {
			String[] information      = {"YYYNNNYN",
 "NYNNNNYN",
 "NNYNNNNN",
 "NYNYNNNN",
 "YNNNYNNY",
 "NNYNNYNN",
 "NNNNYNYN",
 "NNYNNNNY"}
;
			double expected__         = 0.875;

			return verifyCase(casenum__, expected__, new CarrotBoxes().theProbability(information));
		}
		case 5: {
			String[] information      = {"YNNNNNNNNYNNNNNNNNNN",
 "NYNNNNNNNNNNNNNNNNNN",
 "NNYNNNNNNNYNNNNNYNNN",
 "NNNYNYNNNNNNNNYNNNNN",
 "NNNNYNNNNNNNNNYNNNNY",
 "NNNNNYNNNNNNNNNNNNNY",
 "NNNNYNYNYNNNNNNNNNNN",
 "NNNNNNNYNNNYYNNNNNNN",
 "NNNNNNNNYNNNNNNNNNNN",
 "YNNNNNNNNYNNNNNYNNNN",
 "NNNNNNNNNNYNNNNNNNNN",
 "NYNNNNNNNNNYNNNNNNNN",
 "NNNNNNNYNNNNYNNNNNNN",
 "NNNNNNNNNNNNNYNNNYNN",
 "NNNNNNNNNNNYNNYNNNYN",
 "NYNNNNNNNNNNNNNYNNNN",
 "NNYNNNNNNNNNNNNNYNNN",
 "NNNNNNNNNNNNNYNYNYNN",
 "NNNNNNNNYNYNNNNNNNYY",
 "NNNYNNNNNNNNNNNNNNNY"};
			double expected__         = 0.75;

			return verifyCase(casenum__, expected__, new CarrotBoxes().theProbability(information));
		}

		// custom cases

/*      case 6: {
			String[] information      = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new CarrotBoxes().theProbability(information));
		}*/
/*      case 7: {
			String[] information      = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new CarrotBoxes().theProbability(information));
		}*/
/*      case 8: {
			String[] information      = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new CarrotBoxes().theProbability(information));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
