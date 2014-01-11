import java.math.*;
import java.util.*;

public class Ear {
    public long getCount(String[] redXRaw, String[] blueXRaw, String[] blueYRaw) {
        int[] redX = parse(redXRaw);
        int[] blueX = parse(blueXRaw);
        int[] blueY = parse(blueYRaw);
        int n = blueX.length;
        int m = redX.length;
        Arrays.sort(redX);
        long result = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (blueY[i] > blueY[j]) {
                    long left = 0;
                    for (int k = 0, count = 0; k < m; ++ k) {
                        if (redX[k] < blueX[j]) {
                            left += count;
                        }
                        if (redX[k] < blueX[i] && (blueX[i] - redX[k]) * blueY[j] - (blueX[j] - redX[k]) * blueY[i] < 0) {
                            count ++;
                        }
                    }
                    long right = 0;
                    for (int k = m - 1, count = 0; k >= 0; -- k) {
                        if (redX[k] > blueX[j]) {
                            right += count;
                        }
                        if (redX[k] > blueX[i] && (blueX[i] - redX[k]) * blueY[j] - (blueX[j] - redX[k]) * blueY[i] > 0) {
                            count ++;
                        }
                    }
                    result += left * right;
                }
            }
        }
        return result;
    }

    int[] parse(String[] parts) {
        StringBuffer buffer = new StringBuffer();
        for (String part : parts) {
            buffer.append(part);
        }
        StringTokenizer tokenizer = new StringTokenizer(buffer.toString());
        int[] result = new int[tokenizer.countTokens()];
        for (int i = 0; i < result.length; ++ i) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			EarHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				EarHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class EarHarness {
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
	
	static boolean compareOutput(long expected, long result) { return expected == result; }
	static String formatResult(long res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, long expected, long received) { 
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
			String[] redX             = {"3 2 8 7"};
			String[] blueX            = {"5 4"};
			String[] blueY            = {"2 4"};
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new Ear().getCount(redX, blueX, blueY));
		}
		case 1: {
			String[] redX             = {"3 2 8 7"};
			String[] blueX            = {"2 8"};
			String[] blueY            = {"3 4"};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new Ear().getCount(redX, blueX, blueY));
		}
		case 2: {
			String[] redX             = {"1 2 6 9"};
			String[] blueX            = {"3 6 8 5"};
			String[] blueY            = {"1 5 4 3"};
			long expected__           = 4;

			return verifyCase(casenum__, expected__, new Ear().getCount(redX, blueX, blueY));
		}
		case 3: {
			String[] redX             = {"10000"};
			String[] blueX            = {"10000 9999"};
			String[] blueY            = {"10000 9999"};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new Ear().getCount(redX, blueX, blueY));
		}
		case 4: {
			String[] redX             = {"100 2", "00", " 39", "9", " 800 900 9", "99"};
			String[] blueX            = {"15", "0 250 ", "349"};
			String[] blueY            = {"2 3 1"};
			long expected__           = 12;

			return verifyCase(casenum__, expected__, new Ear().getCount(redX, blueX, blueY));
		}
		case 5: {
			String[] redX             = {"1", " ", "2", " ", "3", " ", "4 5 6", " 7 8 9"};
			String[] blueX            = {"4", " ", "5", " ", "6", " 7 ", "8"};
			String[] blueY            = {"1", " 2 ", "3 4", " 5"};
			long expected__           = 204;

			return verifyCase(casenum__, expected__, new Ear().getCount(redX, blueX, blueY));
		}

		// custom cases

/*      case 6: {
			String[] redX             = ;
			String[] blueX            = ;
			String[] blueY            = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new Ear().getCount(redX, blueX, blueY));
		}*/
/*      case 7: {
			String[] redX             = ;
			String[] blueX            = ;
			String[] blueY            = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new Ear().getCount(redX, blueX, blueY));
		}*/
/*      case 8: {
			String[] redX             = ;
			String[] blueX            = ;
			String[] blueY            = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new Ear().getCount(redX, blueX, blueY));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
