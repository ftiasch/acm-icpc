import java.util.*;
import java.math.*;

public class TheKnights {
    int[] deltaX, deltaY, values;

    BigDecimal choose(long n) {
        BigDecimal N = new BigDecimal(n);
        return N.multiply(N.subtract(BigDecimal.ONE));
    }

    public double find(int n, int a, int b) {		
        if (a != b) {
            deltaX = new int[]{0, -a, -a, -b, -b,  a, a,  b, b};
            deltaY = new int[]{0, -b,  b, -a,  a, -b, b, -a, a};
        } else {
            deltaX = new int[]{0, -a, -a, a, a};
            deltaY = new int[]{0, -b,  b, -b, b};
        }
        values = new int[]{0, a, b, n - a, n - b, n};
        Arrays.sort(values);
        int m = deltaX.length;
        long[] count = new long[m + 1];
        for (int i = 0; i + 1 < values.length; ++ i) {
            for (int j = 0; j + 1 < values.length; ++ j) {
                if (values[i] < values[i + 1] && values[j] < values[j + 1]) {
                    int x = values[i];
                    int y = values[j];
                    int total = 0;
                    for (int k = 0; k < m; ++ k) {
                        int xx = x + deltaX[k];
                        int yy = y + deltaY[k];
                        if (0 <= xx && xx < n && 0 <= yy && yy < n) {
                            total ++;
                        }
                    }
                    count[total] += (long)(values[i + 1] - values[i]) * (values[j + 1] - values[j]);
                }
            }
        }
        BigDecimal answer = BigDecimal.ZERO;
        for (int i = 0; i <= m; ++ i) {
            answer = answer.add((BigDecimal.ONE.subtract(choose((long)n * n - i).divide(choose((long)n * n), 50, BigDecimal.ROUND_HALF_DOWN))).multiply(new BigDecimal(count[i])));
        }
        return answer.doubleValue();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TheKnightsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheKnightsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheKnightsHarness {
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
			int n                     = 2;
			int a                     = 1;
			int b                     = 1;
			double expected__         = 3.3333333333333335;

			return verifyCase(casenum__, expected__, new TheKnights().find(n, a, b));
		}
		case 1: {
			int n                     = 47;
			int a                     = 7;
			int b                     = 74;
			double expected__         = 2.0;

			return verifyCase(casenum__, expected__, new TheKnights().find(n, a, b));
		}
		case 2: {
			int n                     = 3;
			int a                     = 2;
			int b                     = 1;
			double expected__         = 4.888888888888889;

			return verifyCase(casenum__, expected__, new TheKnights().find(n, a, b));
		}
		case 3: {
			int n                     = 9999;
			int a                     = 999;
			int b                     = 99;
			double expected__         = 16.25885103191273;

			return verifyCase(casenum__, expected__, new TheKnights().find(n, a, b));
		}
		case 4: {
			int n                     = 10;
			int a                     = 1;
			int b                     = 6;
			double expected__         = 7.636363636363637;

			return verifyCase(casenum__, expected__, new TheKnights().find(n, a, b));
		}

		// custom cases

      case 5: {
			int n                     = 633986483;
			int a                     = 186837751;
			int b                     = 425941920;
			double expected__         = 5.703124894165056;

			return verifyCase(casenum__, expected__, new TheKnights().find(n, a, b));
		}
/*      case 6: {
			int n                     = ;
			int a                     = ;
			int b                     = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new TheKnights().find(n, a, b));
		}*/
/*      case 7: {
			int n                     = ;
			int a                     = ;
			int b                     = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new TheKnights().find(n, a, b));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
