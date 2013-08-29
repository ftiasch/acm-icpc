import java.math.*;
import java.util.*;

public class GooseTattarrattatDiv1 {
    int[] parent;

    int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    public int getmin(String string) {
        int n = string.length();
        int[] kind = new int[n];
        int[] count = new int[26];
        for (int i = 0; i < n; ++ i) {
            count[kind[i] = string.charAt(i) - 'a'] ++;
        }
        parent = new int[26];
        for (int i = 0; i < 26; ++ i) {
            parent[i] = i;
        }
        int[] maximum = new int[26];
        for (int i = 0; i < n; ++ i) {
            int a = kind[i];
            int b = kind[n - 1 - i];
            if (find(a) != find(b)) {
                parent[find(a)] = find(b);
            }
        }
        for (int i = 0; i < 26; ++ i) {
            maximum[find(i)] = Math.max(maximum[find(i)], count[i]);
        }
        int answer = n;
        for (int i = 0; i < 26; ++ i) {
            if (find(i) == i) {
                answer -= maximum[i];
            }
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			GooseTattarrattatDiv1Harness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				GooseTattarrattatDiv1Harness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class GooseTattarrattatDiv1Harness {
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
			String S                  = "geese";
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new GooseTattarrattatDiv1().getmin(S));
		}
		case 1: {
			String S                  = "tattarrattat";
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new GooseTattarrattatDiv1().getmin(S));
		}
		case 2: {
			String S                  = "xyyzzzxxx";
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new GooseTattarrattatDiv1().getmin(S));
		}
		case 3: {
			String S                  = "xrepayuyubctwtykrauccnquqfuqvccuaakylwlcjuyhyammag";
			int expected__            = 11;

			return verifyCase(casenum__, expected__, new GooseTattarrattatDiv1().getmin(S));
		}
		case 4: {
			String S                  = "abaabb";
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new GooseTattarrattatDiv1().getmin(S));
		}

		// custom cases

/*      case 5: {
			String S                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GooseTattarrattatDiv1().getmin(S));
		}*/
/*      case 6: {
			String S                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GooseTattarrattatDiv1().getmin(S));
		}*/
/*      case 7: {
			String S                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GooseTattarrattatDiv1().getmin(S));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
