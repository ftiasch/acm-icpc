import java.math.*;
import java.util.*;

public class WinterAndSnowmen {
    final static int MOD = (int)1e9 + 7;

    public int getNumber(int n, int m) {
        int result = 0;
        for (int bit = 0; bit < 11; ++ bit) { // 2^10 = 1024
            int[][][] ways = new int[2][1 << 11][2];
            ways[0][0][0] = 1;
            int max = Math.max(n, m);
            for (int i = 1; i <= max; ++ i) {
                for (int xor = 0; xor < 1 << 11; ++ xor) {
                    for (int value = 0; value < 2; ++ value) {
                        ways[i & 1][xor][value] = 0;
                    }
                }
                for (int xor = 0; xor < 1 << 11; ++ xor) {
                    for (int value = 0; value < 2; ++ value) {
                        ways[i & 1][xor][value] += ways[i + 1 & 1][xor][value];
                        if (ways[i & 1][xor][value] >= MOD) {
                            ways[i & 1][xor][value] -= MOD;
                        }
                        if (i <= n) {
                            ways[i & 1][xor ^ i][value ^ (i >> bit & 1)] += ways[i + 1 & 1][xor][value];
                            if (ways[i & 1][xor ^ i][value ^ (i >> bit & 1)] >= MOD) {
                                ways[i & 1][xor ^ i][value ^ (i >> bit & 1)] -= MOD;
                            }
                        }
                        if (i <= m) {
                            ways[i & 1][xor ^ i][value] += ways[i + 1 & 1][xor][value];
                            if (ways[i & 1][xor ^ i][value] >= MOD) {
                                ways[i & 1][xor ^ i][value] -= MOD;
                            }
                        }
                    }
                }
            }
            for (int xor = 0; xor < 1 << 11; ++ xor) {
                if ((xor >> bit) == 1) {
                    result += ways[max & 1][xor][0];
                    if (result >= MOD) {
                        result -= MOD;
                    }
                }
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			WinterAndSnowmenHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				WinterAndSnowmenHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class WinterAndSnowmenHarness {
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
			int N                     = 2;
			int M                     = 2;
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new WinterAndSnowmen().getNumber(N, M));
		}
		case 1: {
			int N                     = 1;
			int M                     = 1;
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new WinterAndSnowmen().getNumber(N, M));
		}
		case 2: {
			int N                     = 3;
			int M                     = 5;
			int expected__            = 74;

			return verifyCase(casenum__, expected__, new WinterAndSnowmen().getNumber(N, M));
		}
		case 3: {
			int N                     = 7;
			int M                     = 4;
			int expected__            = 216;

			return verifyCase(casenum__, expected__, new WinterAndSnowmen().getNumber(N, M));
		}
		case 4: {
			int N                     = 47;
			int M                     = 74;
			int expected__            = 962557390;

			return verifyCase(casenum__, expected__, new WinterAndSnowmen().getNumber(N, M));
		}

		// custom cases

/*      case 5: {
			int N                     = 2000;
			int M                     = 2000;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new WinterAndSnowmen().getNumber(N, M));
		}*/
/*      case 6: {
			int N                     = ;
			int M                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new WinterAndSnowmen().getNumber(N, M));
		}*/
/*      case 7: {
			int N                     = ;
			int M                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new WinterAndSnowmen().getNumber(N, M));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
