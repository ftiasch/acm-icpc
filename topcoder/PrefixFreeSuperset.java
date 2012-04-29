import java.math.*;
import java.util.*;

public class PrefixFreeSuperset {
    class Node {
        public Node children[];

        Node() {
            children = new Node[2];
        }

        public void insert(String word) {
            if (word.length() > 0) {
                int token = word.charAt(0) - '0';
                if (children[token] == null) {
                    children[token] = new Node();
                }
                children[token].insert(word.substring(1));
            }
        }
    }

    final static int MAX_DEPTH = 111;

    void count(long innerNodes[], Node root, int depth) {
        int childrenCount = 0;
        for (int token = 0; token < 2; ++ token) {
            if (root.children[token] != null) {
                childrenCount ++;
                count(innerNodes, root.children[token], depth + 1);
            }
        }
        if (childrenCount == 1) {
            innerNodes[depth] ++;
        }
    }

    public long minSumLength(String[] words, long k) {
        long result = 0;
        Node root = new Node();
        for (String word : words) {
            root.insert(word);
            result += word.length();
        }
        long innerNodes[] = new long[MAX_DEPTH];
        long leaves[] = new long[MAX_DEPTH];
        count(innerNodes, root, 1);
        k -= words.length;
        if (k < 0) {
            return -1;
        }
        if (k == 0) {
            return result;
        }
        boolean found = false;
        for (int i = 1; i < MAX_DEPTH; ++ i) {
            found |= innerNodes[i] > 0;
        }
        if (!found) {
            return -1;
        }
        for (int i = 1; i < MAX_DEPTH && k > 0; ++ i) {
            long delta = Math.min(k, innerNodes[i]);
            k -= delta;
            result += i * delta;
            leaves[i + 2] += delta;
            delta = Math.min(k, leaves[i]);
            k -= delta;
            result += i * delta;
            leaves[i + 1] += delta * 2;
        }
        return result > 1e18? -2: result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			PrefixFreeSupersetHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PrefixFreeSupersetHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PrefixFreeSupersetHarness {
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
			String[] cur              = {"010"};
			long k                    = 4;
			long expected__           = 9;

			return verifyCase(casenum__, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}
		case 1: {
			String[] cur              = {"01","000"};
			long k                    = 4;
			long expected__           = 9;

			return verifyCase(casenum__, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}
		case 2: {
			String[] cur              = {"0011","011110101","11101010111","11101010100000000","11101010100000001111"};
			long k                    = 1000000000000L;
			long expected__           = 39971901640560L;

			return verifyCase(casenum__, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}
		case 3: {
			String[] cur              = {"010","00","011","1"};
			long k                    = 4;
			long expected__           = 9;

			return verifyCase(casenum__, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}
		case 4: {
			String[] cur              = {"010","00","011","1"};
			long k                    = 5;
			long expected__           = -1;

			return verifyCase(casenum__, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}

		// custom cases

/*      case 5: {
			String[] cur              = ;
			long k                    = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}*/
/*      case 6: {
			String[] cur              = ;
			long k                    = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}*/
/*      case 7: {
			String[] cur              = ;
			long k                    = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PrefixFreeSuperset().minSumLength(cur, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
