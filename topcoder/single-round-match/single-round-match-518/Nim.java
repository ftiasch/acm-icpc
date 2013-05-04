import java.math.*;
import java.util.*;

public class Nim {
    final static int MOD = 1000000000 + 7;

    int[] seed;

    boolean isPrime(int n) {
        for (int i = 2; i * i <= n; ++ i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int[] change(int[] a, int k) {
        return new int[]{(int)((long)k * (a[0] + a[1]) % MOD), (int)((long)k * (a[0] - a[1]) % MOD)};
    }

    int[] transform(int[] a, int d) {
        int begin = d == -1 ? 16 : 0;
        int end = d == -1 ? -1 : 17;
        int scale = d == -1 ? 1 : 500000004;
        for (int k = begin; k != end; k += d) {
            for (int i = 0; i < 1 << 16; ++ i) {
                if ((i >> k & 1) == 1) {
                    int[] ret = change(new int[]{a[i ^ 1 << k], a[i]}, scale);
                    a[i ^ 1 << k] = ret[0];
                    a[i] = ret[1];
                }
            }
        }
        return a;
    }

    int[] solve(int n) {
        if (n == 1) {
            return seed;
        }
        int[] tmp = solve(n >> 1);
        int[] ret = new int[1 << 16];
        for (int i = 0; i < 1 << 16; ++ i) {
            ret[i] = (int)((long)tmp[i] * tmp[i] % MOD);
        }
        if ((n & 1) == 1) {
            for (int i = 0; i < 1 << 16; ++ i) {
                ret[i] = (int)((long)ret[i] * seed[i] % MOD);
            }
        }
        return ret;
    }

    public int count(int n, int m) {
        seed = new int[1 << 16];
        for (int i = 2; i <= m; ++ i) {
            if (isPrime(i)) {
                seed[i] = 1;
            }
        }
        transform(seed, -1);
        int[] ret = solve(n);
        transform(ret, 1);
        return (ret[0] + MOD) % MOD;
     }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			NimHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				NimHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class NimHarness {
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
			int K                     = 3;
			int L                     = 7;
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new Nim().count(K, L));
		}
		case 1: {
			int K                     = 4;
			int L                     = 13;
			int expected__            = 120;

			return verifyCase(casenum__, expected__, new Nim().count(K, L));
		}
		case 2: {
			int K                     = 10;
			int L                     = 100;
			int expected__            = 294844622;

			return verifyCase(casenum__, expected__, new Nim().count(K, L));
		}
		case 3: {
			int K                     = 123456789;
			int L                     = 12345;
			int expected__            = 235511047;

			return verifyCase(casenum__, expected__, new Nim().count(K, L));
		}

		// custom cases

/*      case 4: {
			int K                     = ;
			int L                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Nim().count(K, L));
		}*/
/*      case 5: {
			int K                     = ;
			int L                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Nim().count(K, L));
		}*/
/*      case 6: {
			int K                     = ;
			int L                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Nim().count(K, L));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
