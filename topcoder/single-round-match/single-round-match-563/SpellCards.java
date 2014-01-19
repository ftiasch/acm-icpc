import java.math.*;
import java.util.*;

public class SpellCards {
    public int maxDamage(int[] level, int[] damage) {
        int n = level.length;
        int result = 0;
        for (int _ = 0; _ < n; ++ _) {
            int[] maximum = new int[n + 1];
            Arrays.fill(maximum, Integer.MIN_VALUE);
            maximum[0] = 0;
            for (int i = n - 1; i >= 0; -- i) {
                int[] newMaximum = new int[n + 1];
                Arrays.fill(newMaximum, Integer.MIN_VALUE);
                for (int j = 0; j < n - i; ++ j) {
                    newMaximum[j + 1] = Math.max(newMaximum[j + 1], maximum[j]);
                    if (level[i] <= j + 1) {
                        newMaximum[j + 1 - level[i]] = Math.max(newMaximum[j + 1 - level[i]], maximum[j] + damage[i]);
                    }
                }
                maximum = newMaximum;
            }
            for (int i = 0; i <= n; ++ i) {
                result = Math.max(result, maximum[i]);
            }
            shift(level);
            shift(damage);
        }
        return result;
    }

    void shift(int[] array) {
        int n = array.length;
        int buffer = array[0];
        for (int i = 1; i < n; ++ i) {
            array[i - 1] = array[i];
        }
        array[n - 1] = buffer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			SpellCardsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SpellCardsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SpellCardsHarness {
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
			int[] level               = {1,1,1};
			int[] damage              = {10,20,30};
			int expected__            = 60;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}
		case 1: {
			int[] level               = {3,3,3};
			int[] damage              = {10,20,30};
			int expected__            = 30;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}
		case 2: {
			int[] level               = {4,4,4};
			int[] damage              = {10,20,30};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}
		case 3: {
			int[] level               = {50,1,50,1,50};
			int[] damage              = {10,20,30,40,50};
			int expected__            = 60;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}
		case 4: {
			int[] level               = {2,1,1};
			int[] damage              = {40,40,10};
			int expected__            = 80;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}
		case 5: {
			int[] level               = {1,2,1,1,3,2,1};
			int[] damage              = {10,40,10,10,90,40,10};
			int expected__            = 170;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}
		case 6: {
			int[] level               = {1,2,2,3,1,4,2};
			int[] damage              = {113,253,523,941,250,534,454};
			int expected__            = 1918;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}

		// custom cases

/*      case 7: {
			int[] level               = ;
			int[] damage              = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}*/
/*      case 8: {
			int[] level               = ;
			int[] damage              = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}*/
/*      case 9: {
			int[] level               = ;
			int[] damage              = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SpellCards().maxDamage(level, damage));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
