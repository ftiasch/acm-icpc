import java.math.*;
import java.util.*;

public class FlattenOut {
    int n;
    long[] buffer;

    long[] perform(long[] height, long time) {
        for (int i = 0; i < n; ++ i) {
            if (height[i] > 0) {
                buffer[i] = time;
            } else {
                buffer[i] = 0;
            }
        }
        for (int i = 0; i < n; ++ i) {
            height[i] -= buffer[i];
            height[(i + 1) % n] += buffer[i];
        }
        return height;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    public long[] simulateIt(long[] height, long time) {
        n = height.length;
        buffer = new long[n];
        while (time >  0) {
            long sum = 0;
            long maximum = Long.MIN_VALUE;
            long minimum = Long.MAX_VALUE; 
            long minAbs = time;
            for (int i = 0; i < n; ++ i) {
                if (height[i] > 0) {
                    if (height[(i + n - 1) % n] <= 0) {
                        minAbs = Math.min(minAbs, height[i]);
                    }
                } else {
                    if (height[(i + n - 1) % n] > 0) {
                        minAbs = Math.min(minAbs, 1 - height[i]);
                    }
                }
                sum += height[i];
                maximum = Math.max(maximum, height[i]);
                minimum = Math.min(minimum, height[i]);
            }
            if (maximum <= 0) {
                break;
            }
            if (minimum >= 1) {
                break;
            }
            if (0 <= minimum && maximum <= 1) {
                long[] newHeight = new long[n];
                for (int i = 0; i < n; ++ i) {
                    newHeight[(int)((i + time) % n)] = height[i];
                }
                return newHeight;
            }
            time -= minAbs;
            height = perform(height, minAbs);
        }
        return height;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FlattenOutHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FlattenOutHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FlattenOutHarness {
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
	
	static boolean compareOutput(long[] expected, long[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

	static String formatResult(long[] res) {
		String ret = "";
		ret += "{";
		for (int i=0; i<res.length; ++i) {
			if (i > 0) ret += ",";
			ret += String.format(" %d", res[i]);
		}
		ret += " }";
		return ret;
	}
	
	static int verifyCase(int casenum, long[] expected, long[] received) { 
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
			long[] height             = {1, 3, -4, -4, 2, 0};
			long T                    = 1;
			long[] expected__         = {0, 3, -3, -4, 1, 1};

			return verifyCase(casenum__, expected__, new FlattenOut().simulateIt(height, T));
		}
		case 1: {
			long[] height             = {1, 3, -4, -4, 2, 0};
			long T                    = 2;
			long[] expected__         = {1, 2, -2, -4, 0, 1};

			return verifyCase(casenum__, expected__, new FlattenOut().simulateIt(height, T));
		}
		case 2: {
			long[] height             = {9999999999999999L, -9999999999999999L, 9999999999999999L, -9999999999999999L};
			long T                    = 9999999999999999L;
			long[] expected__         = {0, 0, 0, 0};

			return verifyCase(casenum__, expected__, new FlattenOut().simulateIt(height, T));
		}
		case 3: {
			long[] height             = {0, 0, 0};
			long T                    = 4;
			long[] expected__         = {0, 0, 0};

			return verifyCase(casenum__, expected__, new FlattenOut().simulateIt(height, T));
		}

		// custom cases

      case 4: {
            //long[] height             = {40000000000000L, 40000000000000L, 40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L};
            long[] height             = {40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, 40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L, -40000000000000L};
			long T                    = 1000000000000000L;
			long[] expected__         = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -24};

			return verifyCase(casenum__, expected__, new FlattenOut().simulateIt(height, T));
		}
/*      case 5: {
			long[] height             = {};
			long T                    = ;
			long[] expected__         = {};

			return verifyCase(casenum__, expected__, new FlattenOut().simulateIt(height, T));
		}*/
/*      case 6: {
			long[] height             = {};
			long T                    = ;
			long[] expected__         = {};

			return verifyCase(casenum__, expected__, new FlattenOut().simulateIt(height, T));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
