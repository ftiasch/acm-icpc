import java.math.*;
import java.util.*;

public class StringSequences {
    final static int MOD = (int)1e9 + 7;

    public int countSequences(String a, String b) {
        int n = a.length();
        int m = b.length();
        a += '$';
        b += '$';
        int[][] binom = new int[m + 1][m + 1];
        for (int i = 0; i <= m; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
        int[][] waysB = new int[m + 1][m + 1];
        for (int i = m; i >= 0; -- i) {
            waysB[i][i] = 1;
            for (int j = i + 1; j <= m; ++ j) {
                for (int k = i; k < j; ++ k) { 
                    if (b.charAt(k) != b.charAt(j)) {
                        waysB[i][j] += (int)((long)waysB[i][k] * waysB[k + 1][j] % MOD * binom[j - i - 1][k - i] % MOD);
                        waysB[i][j] %= MOD;
                    }
                }
            }
        }
        int[][] waysAB = new int[n + 1][m + 1];
        for (int i = n; i >= 0; -- i) {
            for (int j = m; j >= 0; -- j) {
                if (i == n) {
                    waysAB[i][j] = waysB[j][m];
                } else {
                    for (int k = j; k < m; ++ k) {
                        if (m - j >= n - i && a.charAt(i) == b.charAt(k)) {
                            waysAB[i][j] += (int)((long)waysB[j][k] * waysAB[i + 1][k + 1] % MOD * binom[(m - j) - (n - i)][k - j] % MOD);
                            waysAB[i][j] %= MOD;
                        }
                    }
                }
            }
        }
        return waysAB[0][0];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			StringSequencesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				StringSequencesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class StringSequencesHarness {
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
			String A                  = "oxoxox";
			String B                  = "foxfoxfox";
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new StringSequences().countSequences(A, B));
		}
		case 1: {
			String A                  = "aaaaa";
			String B                  = "aaaaaaaa";
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new StringSequences().countSequences(A, B));
		}
		case 2: {
			String A                  = "tco";
			String B                  = "tcotco";
			int expected__            = 18;

			return verifyCase(casenum__, expected__, new StringSequences().countSequences(A, B));
		}
		case 3: {
			String A                  = "a";
			String B                  = "alnfrlrealjnsliejsraijneroav";
			int expected__            = 135925750;

			return verifyCase(casenum__, expected__, new StringSequences().countSequences(A, B));
		}
		case 4: {
			String A                  = "ppmtmtttppmtmpppmtmtmp";
			String B                  = "ppmmmpmtmpttmttptpmttptmtmppmppttpmtpppppmmtmmmptp";
			int expected__            = 856841145;

			return verifyCase(casenum__, expected__, new StringSequences().countSequences(A, B));
		}

		// custom cases

      case 5: {
			String A                  = "a";
			String B                  = "aba";
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new StringSequences().countSequences(A, B));
		}
/*      case 6: {
			String A                  = ;
			String B                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new StringSequences().countSequences(A, B));
		}*/
/*      case 7: {
			String A                  = ;
			String B                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new StringSequences().countSequences(A, B));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
