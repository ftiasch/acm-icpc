import java.math.*;
import java.util.*;

class Data {
    boolean full;
    int height;
    long ways;

    Data(boolean full, int height, long ways) {
        this.full = full;
        this.height = height;
        this.ways = ways;
    }
}

public class HatRack {
    final static Data INVALID = new Data(false, 0, 0);

    int n;
    ArrayList <ArrayList <Integer> > tree;

    Data solve(int p, int u) {
        ArrayList <Data> children = new ArrayList <Data>();
        for (Object object : tree.get(u)) {
            int v = (Integer)object;
            if (v != p) {
                children.add(solve(u, v));
            }
        }
        if (children.size() == 0) {
            return new Data(true, 1, 1);
        }
        if (children.size() <= 1) {
            Data child = children.get(0);
            return child.height == 1 ? new Data(false, 2, 1) : INVALID;
        }
        if (children.size() > 2) {
            return INVALID;
        }
        Data left = children.get(0);
        Data right = children.get(1);
        if (Math.abs(left.height - right.height) > 1 || left == INVALID || right == INVALID) {
            return INVALID;
        }
        if (left.full && right.full) {
            Data result = new Data(false, Math.max(left.height, right.height) + 1, left.ways * right.ways);
            if (left.height == right.height) {
                result.full = true;
                result.ways *= 2;
            }
            return result;
        }
        if (left.full || right.full) {
            if (right.full) {
                left = children.get(1);
                right = children.get(0);
            }
            if (left.height <= right.height) {
                return new Data(false, right.height + 1, left.ways * right.ways);
            }
        }
        return INVALID;
    }

    public long countWays(int[] knob1, int[] knob2) {
        int n = knob1.length + 1;
        tree = new ArrayList <ArrayList <Integer> >();
        for (int i = 0; i < n; ++ i) {
            tree.add(new ArrayList <Integer>());
        }
        for (int i = 0; i < n - 1; ++ i) {
            int a = knob1[i] - 1;
            int b = knob2[i] - 1;
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        long result = 0;
        for (int root = 0; root < n; ++ root) {
            result += solve(-1, root).ways;
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			HatRackHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				HatRackHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class HatRackHarness {
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
			int[] knob1               = {1};
			int[] knob2               = {2};
			long expected__           = 2;

			return verifyCase(casenum__, expected__, new HatRack().countWays(knob1, knob2));
		}
		case 1: {
			int[] knob1               = {1,1};
			int[] knob2               = {2,3};
			long expected__           = 2;

			return verifyCase(casenum__, expected__, new HatRack().countWays(knob1, knob2));
		}
		case 2: {
			int[] knob1               = {1,1,1,1};
			int[] knob2               = {2,3,4,5};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new HatRack().countWays(knob1, knob2));
		}
		case 3: {
			int[] knob1               = {6,6,6,4,1};
			int[] knob2               = {1,2,4,5,3};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new HatRack().countWays(knob1, knob2));
		}
		case 4: {
			int[] knob1               = {1,1,2,2,11,11,8,8,3,3,4,4,5};
			int[] knob2               = {2,3,11,8,12,13,9,10,4,5,7,6,14};
			long expected__           = 16;

			return verifyCase(casenum__, expected__, new HatRack().countWays(knob1, knob2));
		}
		case 5: {
			int[] knob1               = {1,2,3,4,5,6,7,8,9};
			int[] knob2               = {2,3,4,5,6,7,8,9,10};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new HatRack().countWays(knob1, knob2));
		}

		// custom cases

/*      case 6: {
			int[] knob1               = ;
			int[] knob2               = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new HatRack().countWays(knob1, knob2));
		}*/
/*      case 7: {
			int[] knob1               = ;
			int[] knob2               = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new HatRack().countWays(knob1, knob2));
		}*/
/*      case 8: {
			int[] knob1               = ;
			int[] knob2               = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new HatRack().countWays(knob1, knob2));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
