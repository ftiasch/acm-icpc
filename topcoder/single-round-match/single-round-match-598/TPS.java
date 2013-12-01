import java.math.*;
import java.util.*;

public class TPS {
    final static int INF = 1000000000;

    int n;
    ArrayList <ArrayList <Integer> > tree;

    int[] go(int p, int u) {
        int[][] minimum = new int[][]{ {0, 0}, {1, 1} };
        for (int v : tree.get(u)) {
            if (v != p) {
                int[] ret = go(u, v);
                int[][] newMinimum = new int[][] { { INF, INF }, { INF, INF } };
                for (int count = 0; count < 2; ++ count) {
                    for (int empty = 0; empty < 2; ++ empty) {
                        for (int subtree = 0; subtree < 2; ++ subtree) {
                            int newCount = count | subtree;
                            int newEmpty = empty + (subtree ^ 1);
                            if (newEmpty < 2) {
                                newMinimum[newCount][newEmpty] = Math.min(newMinimum[newCount][newEmpty], minimum[count][empty] + ret[subtree]);
                            }
                        }
                    }
                }
                minimum = newMinimum;
            }
        }
        return new int[]{ Math.min(minimum[0][0], minimum[0][1]), Math.min(minimum[1][0], minimum[1][1]) };
    }

    public int minimalBeacons(String[] linked) {
        n = linked.length;
        tree = new ArrayList <ArrayList <Integer> >();
        for (int i = 0; i < n; ++ i) {
            tree.add(new ArrayList <Integer>());
            for (int j = 0; j < n; ++ j) {
                if (linked[i].charAt(j) == 'Y') {
                    tree.get(i).add(j);
                }
            }
        }
        if (n == 1) {
            return 0;
        }
        int answer = Integer.MAX_VALUE;
        for (int root = 0; root < n; ++ root) {
            int[] ret = go(-1, root);
            answer = Math.min(answer, Math.min(ret[0], ret[1]) + 1);
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TPSHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TPSHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TPSHarness {
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
			String[] linked           = {"NYNN",
 "YNYN",
 "NYNY",
 "NNYN"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new TPS().minimalBeacons(linked));
		}
		case 1: {
			String[] linked           = {"NYYY",
 "YNNN",
 "YNNN",
 "YNNN"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new TPS().minimalBeacons(linked));
		}
		case 2: {
			String[] linked           = {"NNYNNNNNNN",
 "NNNNNYNNNN",
 "YNNYNNYNNN",
 "NNYNYNNYNN",
 "NNNYNYNNYN",
 "NYNNYNNNNY",
 "NNYNNNNNNN",
 "NNNYNNNNNN",
 "NNNNYNNNNN",
 "NNNNNYNNNN"}
;
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new TPS().minimalBeacons(linked));
		}
		case 3: {
			String[] linked           = {"NYNYNNYNN",
 "YNYNYNNYN",
 "NYNNNYNNY",
 "YNNNNNNNN",
 "NYNNNNNNN",
 "NNYNNNNNN",
 "YNNNNNNNN",
 "NYNNNNNNN",
 "NNYNNNNNN"};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new TPS().minimalBeacons(linked));
		}
		case 4: {
			String[] linked           = {"NYYYYYYYYY",
 "YNNNNNNNNN",
 "YNNNNNNNNN",
 "YNNNNNNNNN",
 "YNNNNNNNNN",
 "YNNNNNNNNN",
 "YNNNNNNNNN",
 "YNNNNNNNNN",
 "YNNNNNNNNN",
 "YNNNNNNNNN"}
;
			int expected__            = 8;

			return verifyCase(casenum__, expected__, new TPS().minimalBeacons(linked));
		}
		case 5: {
			String[] linked           = {"N"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new TPS().minimalBeacons(linked));
		}

		// custom cases

/*      case 6: {
			String[] linked           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TPS().minimalBeacons(linked));
		}*/
/*      case 7: {
			String[] linked           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TPS().minimalBeacons(linked));
		}*/
/*      case 8: {
			String[] linked           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TPS().minimalBeacons(linked));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
