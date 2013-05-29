import java.math.*;
import java.util.*;

public class CowsMooing {
    final static int MOD = 10000 + 7;
    final static int DELTA = 32 * 27 * 25;

    String run(String pattern, int start) {
        String ret = "";
        int length = pattern.length();
        int i = start % length;
        do {
            ret += pattern.charAt(i);
            i = (i + DELTA) % length;
        } while (i != start % length);
        return ret;
    }

    int n;

    void clear(int[][] array) {
        for (int[] row : array) {
            Arrays.fill(row, 0);
        }
    }

    int inverse(int a) {
        return a == 1 ? 1 : (MOD - MOD / a) * inverse(MOD % a) % MOD;
    }

    public int[] getDistribution(String[] patterns) {
        n = patterns.length;
        String[][] newPatterns = new String[n][];
        for (int i = 0; i < n; ++ i) {
            int length = patterns[i].length();
            newPatterns[i] = new String[length];
            for (int j = 0; j < length; ++ j) {
                newPatterns[i][j] = run(patterns[i], j);
            }
        }
        int[] answer = new int[n + 1];
        int[][] current = new int[51][];
        for (int i = 0; i <= 50; ++ i) {
            current[i] = new int[i];
        }
        int[][] count = new int[51][n + 1];
        for (int remainder = 0; remainder < DELTA; ++ remainder) {
            clear(current);
            for (int i = 0; i < n; ++ i) {
                String pattern = newPatterns[i][remainder % patterns[i].length()];
                int length = pattern.length();
                if (length == 7) {
                    length = 49;
                } 
                for (int j = 0; j < length; ++ j) {
                    if (pattern.charAt(j % pattern.length()) == 'M') {
                        current[length][j] ++;
                    }
                }
            }
            clear(count);
            for (int i = 1; i <= 50; ++ i) {
                for (int j = 0; j < i; ++ j) {
                    count[i][current[i][j]] ++;
                }
            }
            int[] ways = new int[n + 1];
            ways[0] = 1;
            for (int i = 1; i <= 50; ++ i) {
                for (int j = n; j >= 0; -- j) {
                    int tmp = ways[j];
                    if (tmp == 0) {
                        continue;
                    }
                    for (int k = 0; j + k <= n; ++ k) {
                        ways[j + k] += tmp * count[i][k];
                        ways[j + k] %= MOD;
                        if (count[i][k] == i) {
                            break;
                        }
                    }
                    ways[j] += MOD - tmp;
                    ways[j] %= MOD;
                }
            }
            for (int i = 0; i <= n; ++ i) {
                answer[i] += ways[i] * inverse(DELTA % MOD);
                answer[i] %= MOD;
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			CowsMooingHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CowsMooingHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CowsMooingHarness {
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
	
	static boolean compareOutput(int[] expected, int[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

	static String formatResult(int[] res) {
		String ret = "";
		ret += "{";
		for (int i=0; i<res.length; ++i) {
			if (i > 0) ret += ",";
			ret += String.format(" %d", res[i]);
		}
		ret += " }";
		return ret;
	}
	
	static int verifyCase(int casenum, int[] expected, int[] received) { 
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
			String[] patterns         = {"M"};
			int[] expected__          = {0, 3235 };

			return verifyCase(casenum__, expected__, new CowsMooing().getDistribution(patterns));
		}
		case 1: {
			String[] patterns         = {"M--M-",
 "-M-M-"};
			int[] expected__          = {1294, 1294, 647 };

			return verifyCase(casenum__, expected__, new CowsMooing().getDistribution(patterns));
		}
		case 2: {
			String[] patterns         = {"M--",
 "-M--"};
			int[] expected__          = {6621, 514, 6107 };

			return verifyCase(casenum__, expected__, new CowsMooing().getDistribution(patterns));
		}
		case 3: {
			String[] patterns         = {"MM-M---M-MM--",
 "-MM-MMM----M-M-",
 "MM-",
 "M--M-M--"};
			int[] expected__          = {7907, 7504, 964, 9034, 7847 };

			return verifyCase(casenum__, expected__, new CowsMooing().getDistribution(patterns));
		}
		case 4: {
			String[] patterns         = {"-",
 "-M",
 "-MM",
 "-M--",
 "--M--",
 "M---MM",
 "MM-MM--",
 "M-M--M--",
 "M-M-MM-M-",
 "-MMMM-M-M-",
 "M-M-M-M-MMM",
 "--MMM--M--MM",
 "M--MM--MMM-M-",
 "M-MM----MM----",
 "MM----M-MMMM--M",
 "-MMMMM--M-M--M-M",
 "----M-M----MMMMMM",
 "MM-----M-MMM-MMMMM",
 "--M-MMMMMM-MMM-M--M",
 "M-M--M--M-MMM--MM---",
 "--MMMM--MM-MMM-M----M",
 "-MMM-M-MM-MMM-M-M-----",
 "-M-MMMM-MMMM--M--M--M--",
 "-M--MMM--MM---MMMM---MMM",
 "MMMM-MMMMM-M-MMMM-MM--M-M",
 "MMM-MMM-MM--M----M----MM-M",
 "MM---M-MMMMMMM----MM----MM-",
 "M----MMMMMMM-MM--MMM---M----",
 "-M-MMM-MMMM-MM--M-MMM---M----",
 "M--M-M-MM-MMM--M---MMMMMM-M-MM"};
			int[] expected__          = {0, 0, 0, 9523, 1842, 4448, 752, 9392, 9394, 7124, 193, 2222, 1873, 1875, 5758, 5429, 1847, 9481, 7496, 108, 7880, 4988, 2656, 4783, 4261, 9987, 0, 0, 0, 0, 0 };

			return verifyCase(casenum__, expected__, new CowsMooing().getDistribution(patterns));
		}
		case 5: {
			String[] patterns         = {
"MMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",
"MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"}
;
			int[] expected__          = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3235 };

			return verifyCase(casenum__, expected__, new CowsMooing().getDistribution(patterns));
		}

		// custom cases

/*      case 6: {
			String[] patterns         = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new CowsMooing().getDistribution(patterns));
		}*/
/*      case 7: {
			String[] patterns         = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new CowsMooing().getDistribution(patterns));
		}*/
/*      case 8: {
			String[] patterns         = ;
			int[] expected__          = ;

			return verifyCase(casenum__, expected__, new CowsMooing().getDistribution(patterns));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
