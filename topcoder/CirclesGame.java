import java.util.*;
import java.math.*;

public class CirclesGame {
    int sqr(int x) {
        return x * x;
    }
    
    int n;
    int[] sg, parent;
    ArrayList[] childrens;

    int solveSG(int r) {
        if (sg[r] == -1) {
            boolean[] next = new boolean[n + 1];
            Arrays.fill(next, false);
            ArrayList <Integer> children = childrens[r];
            for (int c : children) {
                boolean[] visit = new boolean[n];
                for (int i = c; i != -1; i = parent[i]) {
                    visit[i] = true;
                }
                int ret = 0;
                for (int i : children) {
                    if (parent[i] != -1 && !visit[i] && visit[parent[i]]) {
                        ret ^= solveSG(i);
                    }
                }
                if (ret <= n) {
                    next[ret] = true;
                }
            }
            sg[r] = 0;
            while (next[sg[r]]) {
                sg[r] ++;
            }
        }
        return sg[r];
    }

    public String whoCanWin(int[] x, int[] y, int[] r) {		
        n = x.length;
        parent = new int[n];
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; ++ i) {
            int x_0 = x[i];
            int y_0 = y[i] + r[i];
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    if (sqr(x_0 - x[j]) + sqr(y_0 - y[j]) <= sqr(r[j])) {
                        if (parent[i] == -1 || r[j] < r[parent[i]]) {
                            parent[i] = j;
                        }
                    }
                }
            }
        }
        childrens = new ArrayList[n];
        for (int i = 0; i < n; ++ i) {
            childrens[i] = new ArrayList <Integer>();
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = i; j != -1; j = parent[j]) {
                childrens[j].add(i);
            }
        }
        int answer = 0;
        sg = new int[n];
        Arrays.fill(sg, -1);
        for (int i = 0; i < n; ++ i) {
            if (parent[i] == -1) {
                answer ^= solveSG(i);
            }
        }
        return answer != 0 ? "Alice" : "Bob";
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			CirclesGameHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CirclesGameHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CirclesGameHarness {
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
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
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

	static int runTestCase(int casenum) {
		switch(casenum) {
		case 0: {
			int[] x                   = {0};
			int[] y                   = {0};
			int[] r                   = {1};
			String expected__         = "Alice";

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}
		case 1: {
			int[] x                   = {0, 3};
			int[] y                   = {0, 0};
			int[] r                   = {1, 1};
			String expected__         = "Bob";

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}
		case 2: {
			int[] x                   = {0, 0, 5};
			int[] y                   = {0, 0, 0};
			int[] r                   = {1, 2, 1};
			String expected__         = "Alice";

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}
		case 3: {
			int[] x                   = {0, 0, 0, 10, 10, 20};
			int[] y                   = {0, 0, 0, 0, 0, 0};
			int[] r                   = {1, 2, 3, 1, 2, 1};
			String expected__         = "Bob";

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}
		case 4: {
			int[] x                   = {10,20,30,40,50,60,70,80};
			int[] y                   = {8,7,6,5,4,3,2,1};
			int[] r                   = {2,2,2,2,2,2,2,2};
			String expected__         = "Bob";

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}
		case 5: {
			int[] x                   = {0, 3, 6, 9, 12, -4747, -4777};
			int[] y                   = {-5858, -5858, -5858, -5858, -5858, 777, 777};
			int[] r                   = {58, 1, 1, 1, 1, 44, 8};
			String expected__         = "Bob";

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}
		case 6: {
			int[] x                   = {5202, 5699, -7433, 5068, -9483, -684, -6593, 5108, -7813, 6823, 3267, -8222, -8547, 2878, 2413, 8557, 5149, 5073, -8638, -6108, 8097};
			int[] y                   = {8728, -7407, 4338, -8414, 7652, -3705, -984, 5976, -9180, -9119, 9301, 2398, 7915, 6205, 7665, 717, -9884, 11, -8579, -6903, -746};
			int[] r                   = {4196, 9303, 7152, 5875, 2943, 788, 502, 416, 1958, 3174, 182, 1256, 1147, 444, 979, 65, 1040, 1233, 438, 175, 1140};
			String expected__         = "Alice";

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}

		// custom cases

/*      case 7: {
			int[] x                   = ;
			int[] y                   = ;
			int[] r                   = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}*/
/*      case 8: {
			int[] x                   = ;
			int[] y                   = ;
			int[] r                   = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}*/
/*      case 9: {
			int[] x                   = ;
			int[] y                   = ;
			int[] r                   = ;
			String expected__         = ;

			return verifyCase(casenum, expected__, new CirclesGame().whoCanWin(x, y, r));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
