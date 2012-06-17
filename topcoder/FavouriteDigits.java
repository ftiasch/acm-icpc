import java.math.*;
import java.util.*;

public class FavouriteDigits {
    int digit1, count1, digit2, count2, length;
    int[] digits;
    long[][][][] memory;

    int sgn(boolean b) {
        return b? 1: 0;
    }

    long count(boolean less, boolean first, int length, int current1, int current2) {
        if (length == 0) {
            return sgn(less && current1 >= count1 && current2 >= count2);
        }
        if (less && memory[sgn(first)][length][current1][current2] != -1) {
            return memory[sgn(first)][length][current1][current2];
        }
        long result = 0;
        for (int digit = 0; digit < 10; ++ digit) {
            if (less || digit <= digits[length - 1]) {
                boolean occupy = !first || digit != 0;
                result += count(less || digit < digits[length - 1], first && digit == 0, length - 1, current1 + sgn(occupy && digit == digit1), current2 + sgn(occupy && digit == digit2));
            }
        }
        if (less) {
            memory[sgn(first)][length][current1][current2] = result;
        }
        return result;
    }

    long solve(long n) {
        digits = new int[20];
        Arrays.fill(digits, 0);
        length = 0;
        while (n > 0) {
            digits[length ++] = (int)(n % 10);
            n /= 10;
        }
        return count(false, true, length, 0, 0);
    }

    public long findNext(long n, int digit1, int count1, int digit2, int count2) {
        this.digit1 = digit1;
        this.count1 = count1;
        this.digit2 = digit2;
        this.count2 = count2;
        memory = new long[2][20][20][20];
        for (int first = 0; first < 2; ++ first) {
            for (int length = 0; length < 20; ++ length) {
                for (int current1 = 0; current1 < 20; ++ current1) {
                    for (int current2 = 0; current2 < 20; ++ current2) {
                        memory[first][length][current1][current2] = -1;
                    }
                }
            }
        }
        long lower = n;
        long upper = 9999999999999999L;
        while (lower < upper) {
            long mider = (lower + upper) >> 1;
            if (solve(mider + 1) - solve(n) >= 1) {
                upper = mider;
            } else {
                lower = mider + 1;
            }
        }
        return upper;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FavouriteDigitsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FavouriteDigitsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FavouriteDigitsHarness {
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
			long N                    = 47;
			int digit1                = 1;
			int count1                = 0;
			int digit2                = 2;
			int count2                = 0;
			long expected__           = 47;

			return verifyCase(casenum__, expected__, new FavouriteDigits().findNext(N, digit1, count1, digit2, count2));
		}
		case 1: {
			long N                    = 47;
			int digit1                = 5;
			int count1                = 0;
			int digit2                = 9;
			int count2                = 1;
			long expected__           = 49;

			return verifyCase(casenum__, expected__, new FavouriteDigits().findNext(N, digit1, count1, digit2, count2));
		}
		case 2: {
			long N                    = 47;
			int digit1                = 5;
			int count1                = 0;
			int digit2                = 3;
			int count2                = 1;
			long expected__           = 53;

			return verifyCase(casenum__, expected__, new FavouriteDigits().findNext(N, digit1, count1, digit2, count2));
		}
		case 3: {
			long N                    = 47;
			int digit1                = 2;
			int count1                = 1;
			int digit2                = 0;
			int count2                = 2;
			long expected__           = 200;

			return verifyCase(casenum__, expected__, new FavouriteDigits().findNext(N, digit1, count1, digit2, count2));
		}
		case 4: {
			long N                    = 123456789012345L;
			int digit1                = 1;
			int count1                = 2;
			int digit2                = 2;
			int count2                = 4;
			long expected__           = 123456789012422L;

			return verifyCase(casenum__, expected__, new FavouriteDigits().findNext(N, digit1, count1, digit2, count2));
		}
		case 5: {
			long N                    = 92;
			int digit1                = 1;
			int count1                = 1;
			int digit2                = 0;
			int count2                = 0;
			long expected__           = 100;

			return verifyCase(casenum__, expected__, new FavouriteDigits().findNext(N, digit1, count1, digit2, count2));
		}

		// custom cases

/*      case 6: {
			long N                    = ;
			int digit1                = ;
			int count1                = ;
			int digit2                = ;
			int count2                = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new FavouriteDigits().findNext(N, digit1, count1, digit2, count2));
		}*/
/*      case 7: {
			long N                    = ;
			int digit1                = ;
			int count1                = ;
			int digit2                = ;
			int count2                = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new FavouriteDigits().findNext(N, digit1, count1, digit2, count2));
		}*/
/*      case 8: {
			long N                    = ;
			int digit1                = ;
			int count1                = ;
			int digit2                = ;
			int count2                = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new FavouriteDigits().findNext(N, digit1, count1, digit2, count2));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
