import java.math.*;
import java.util.*;

public class PairsOfStrings {
    ArrayList <Integer> primes, exponents, divisors;

    void factorize(int n) {
        for (int i = 2; i * i <= n; ++ i) {
            if (n % i == 0) {
                primes.add(i);
                int exponent = 0;
                while (n % i == 0) {
                    exponent ++;
                    n /= i;
                }
                exponents.add(exponent);
            }
        }
        if (n > 1) {
            primes.add(n);
            exponents.add(1);
        }
    }

    void enumerate(int i, int number) {
        if (i < primes.size()) {
            enumerate(i + 1, number);
            for (int j = 1; j <= exponents.get(i); ++ j) {
                number *= primes.get(i);
                enumerate(i + 1, number);
            }
        } else {
            divisors.add(number);
        }
    }

    final static int MOD = (int)1e9 + 7;

    long power(long a, long n) {
        long result = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                result = result * a % MOD;
            }
            a = a * a % MOD;
            n >>= 1;
        }
        return result;
    }

    public int getNumber(int n, int k) {
        primes = new ArrayList <Integer>();
        exponents = new ArrayList <Integer>();
        factorize(n);
        divisors = new ArrayList <Integer>();
        enumerate(0, 1);
        long[] memory = new long[divisors.size()];
        long result = 0;
        for (int i = 0; i < divisors.size(); ++ i) {
            int d = divisors.get(i);
            long exact = power(k, d);
            for (int j = 0; j < i; ++ j) {
                if (d % divisors.get(j) == 0) {
                    exact -= memory[j];
                }
            }
            memory[i] = exact;
            result = (result + exact * d) % MOD;
        }
        return (int)((result + MOD) % MOD);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			PairsOfStringsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PairsOfStringsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PairsOfStringsHarness {
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
			int n                     = 2;
			int k                     = 2;
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new PairsOfStrings().getNumber(n, k));
		}
		case 1: {
			int n                     = 3;
			int k                     = 2;
			int expected__            = 20;

			return verifyCase(casenum__, expected__, new PairsOfStrings().getNumber(n, k));
		}
		case 2: {
			int n                     = 3;
			int k                     = 4;
			int expected__            = 184;

			return verifyCase(casenum__, expected__, new PairsOfStrings().getNumber(n, k));
		}
		case 3: {
			int n                     = 6;
			int k                     = 2;
			int expected__            = 348;

			return verifyCase(casenum__, expected__, new PairsOfStrings().getNumber(n, k));
		}
		case 4: {
			int n                     = 100;
			int k                     = 26;
			int expected__            = 46519912;

			return verifyCase(casenum__, expected__, new PairsOfStrings().getNumber(n, k));
		}

		// custom cases

/*      case 5: {
			int n                     = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PairsOfStrings().getNumber(n, k));
		}*/
/*      case 6: {
			int n                     = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PairsOfStrings().getNumber(n, k));
		}*/
/*      case 7: {
			int n                     = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PairsOfStrings().getNumber(n, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
