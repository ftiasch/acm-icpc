import java.math.*;
import java.util.*;

public class YetAnotherNim {
    final static int MOD = (int)1e9 + 7;

    int log(long n) {
        int ret = 0;
        for (; n > 0; n /= 2) {
            ret ++;
        }
        return ret;
    }

    long pow(long a, long n) {
        long ret = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                ret = ret * a % MOD;
            }
            a = a * a % MOD;
            n /= 2;
        }
        return ret;
    }

    long[][] multiply(long[][] a, long[][] b) {
        assert a[0].length == b.length;
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                for (int k = 0; k < n; ++ k) {
                    c[i][j] += a[i][k] * b[k][j];
                    c[i][j] %= MOD;
                }
            }
        }
        return c;
    }

    long[][] pow(long[][] a, long n) {
        int k = a.length;
        long[][] ret = new long[k][k];
        for (int i = 0; i < k; ++ i) {
            ret[i][i] = 1;
        }
        while (n > 0) {
            if (n % 2 == 1) {
                ret = multiply(ret, a);
            }
            a = multiply(a, a);
            n /= 2;
        }
        return ret;
    }

    public int solve(int n, int m, int k) {
        if (k == 1) {
            return 0;
        }
        m ++;
        if (k > log(m)) {
            return (int)pow(m - 1, n);
        }
        long[][] t = new long[k + 1][k + 1];
        for (int i = 1; i < k; ++ i) {
            for (int j = 1; j <= i; ++ j) {
                t[i][j] = 1 << j - 1;
            }
            t[i][i + 1] = m - (1 << i);
        }
        t[k][k] = m - 1;
        long[][] a = new long[k + 1][k + 1];
        a[0][1] = m - 1;
        a = multiply(a, pow(t, n - 1));
        return (int)(((pow(m - 1, n) - a[0][k]) % MOD + MOD) % MOD);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			YetAnotherNimHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				YetAnotherNimHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class YetAnotherNimHarness {
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
			int n                     = 100;
			int m                     = 1;
			int k                     = 30;
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new YetAnotherNim().solve(n, m, k));
		}
		case 1: {
			int n                     = 100;
			int m                     = 15;
			int k                     = 1;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new YetAnotherNim().solve(n, m, k));
		}
		case 2: {
			int n                     = 100;
			int m                     = 15;
			int k                     = 2;
			int expected__            = 15;

			return verifyCase(casenum__, expected__, new YetAnotherNim().solve(n, m, k));
		}
		case 3: {
			int n                     = 1;
			int m                     = 1;
			int k                     = 1;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new YetAnotherNim().solve(n, m, k));
		}
		case 4: {
			int n                     = 100;
			int m                     = 31;
			int k                     = 10;
			int expected__            = 908629681;

			return verifyCase(casenum__, expected__, new YetAnotherNim().solve(n, m, k));
		}

		// custom cases

      case 5: {
			int n                     = 979;
			int m                     = 255;
			int k                     = 6;
			int expected__            = 367946486;

			return verifyCase(casenum__, expected__, new YetAnotherNim().solve(n, m, k));
		}
/*      case 6: {
			int n                     = ;
			int m                     = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new YetAnotherNim().solve(n, m, k));
		}*/
/*      case 7: {
			int n                     = ;
			int m                     = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new YetAnotherNim().solve(n, m, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
