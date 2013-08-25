import java.math.*;
import java.util.*;

public class FruitTrees {
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    int solve(int a, int a0, int b, int b0) {
        // |(a0 + a * i) - (b0 + b * j)|
        int g = gcd(a, b);
        int r = ((a0 - b0) % g + g) % g;
        return Math.min(r, g - r);
    }

    public int maxDist(int a, int b, int c) {
        int ret = 0;
        for (int y = 0; y < b; ++ y) {
            for (int z = 0; z < c; ++ z) {
                ret = Math.max(ret, Math.min(solve(a, 0, b, y), Math.min(solve(b, y, c, z), solve(c, z, a, 0))));
            }
        }
        return ret;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FruitTreesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FruitTreesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FruitTreesHarness {
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
			int apple                 = 1;
			int kiwi                  = 5;
			int grape                 = 8;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new FruitTrees().maxDist(apple, kiwi, grape));
		}
		case 1: {
			int apple                 = 3;
			int kiwi                  = 3;
			int grape                 = 6;
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new FruitTrees().maxDist(apple, kiwi, grape));
		}
		case 2: {
			int apple                 = 40;
			int kiwi                  = 30;
			int grape                 = 20;
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new FruitTrees().maxDist(apple, kiwi, grape));
		}
		case 3: {
			int apple                 = 899;
			int kiwi                  = 1073;
			int grape                 = 1147;
			int expected__            = 14;

			return verifyCase(casenum__, expected__, new FruitTrees().maxDist(apple, kiwi, grape));
		}
		case 4: {
			int apple                 = 2000;
			int kiwi                  = 2000;
			int grape                 = 2000;
			int expected__            = 666;

			return verifyCase(casenum__, expected__, new FruitTrees().maxDist(apple, kiwi, grape));
		}

		// custom cases

/*      case 5: {
			int apple                 = ;
			int kiwi                  = ;
			int grape                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FruitTrees().maxDist(apple, kiwi, grape));
		}*/
/*      case 6: {
			int apple                 = ;
			int kiwi                  = ;
			int grape                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FruitTrees().maxDist(apple, kiwi, grape));
		}*/
/*      case 7: {
			int apple                 = ;
			int kiwi                  = ;
			int grape                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FruitTrees().maxDist(apple, kiwi, grape));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
