import java.math.*;
import java.util.*;

public class ThePowers {
    final static int MAX = 30;

    int[] partition(int n) {
        int[] count = new int[MAX];
        Set <Integer> set = new HashSet <Integer>();
        set.add(1);
        for (int i = 1; i * i <= n; ++ i) {
            if (set.contains(i)) {
                continue;
            }
            int exponent = 0;
            for (long now = i; now <= n; now *= i) {
                set.add((int)now);
                exponent ++;
            }
            count[exponent] ++;
        }
        count[1] += n - set.size();
        return count;
    }

    long getGCD(long a, long b) {
        return b == 0 ? a : getGCD(b, a % b);
    }

    long getLCM(long a, long b) {
        return a / getGCD(a, b) * b;
    }

    boolean[] use;

    long dfs(int minA, long minB, long maxB, int a, long lcm) {
        if (lcm > maxB) {
            return 0;
        }
        if (a < minA) {
            return maxB / lcm - (minB - 1) / lcm;
        }
        if (!use[a]) {
            return dfs(minA, minB, maxB, a - 1, lcm);
        }
        return dfs(minA, minB, maxB, a - 1, lcm) - dfs(minA, minB, maxB, a - 1, getLCM(lcm, a));
    }

    long prepare(int a, int b) {
        use = new boolean[MAX];
        Arrays.fill(use, true);
        long ret = (long)a * b;
        for (int i = a; i >= 1; -- i) {
            for (int j = i + i; j <= a; j += i) {
                use[j] = false;
            }
            ret -= dfs(i, (long)(i - 1) * b + 1, (long)i * b, a, 1);
        }
        return ret;
    }

    public long find(int a, int b) {
        long answer = 1;
        int[] count = partition(a);
        for (int i = 1; i < MAX; ++ i) {
            answer += prepare(i, b) * count[i];
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			ThePowersHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ThePowersHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ThePowersHarness {
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
			int A                     = 7;
			int B                     = 4;
			long expected__           = 23;

			return verifyCase(casenum__, expected__, new ThePowers().find(A, B));
		}
		case 1: {
			int A                     = 1;
			int B                     = 1;
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new ThePowers().find(A, B));
		}
		case 2: {
			int A                     = 1000000000;
			int B                     = 1000000000;
			long expected__           = 999983644283653287L;

			return verifyCase(casenum__, expected__, new ThePowers().find(A, B));
		}
		case 3: {
			int A                     = 999999999;
			int B                     = 5;
			long expected__           = 4999934406L;

			return verifyCase(casenum__, expected__, new ThePowers().find(A, B));
		}

		// custom cases

/*      case 4: {
			int A                     = ;
			int B                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new ThePowers().find(A, B));
		}*/
/*      case 5: {
			int A                     = ;
			int B                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new ThePowers().find(A, B));
		}*/
/*      case 6: {
			int A                     = ;
			int B                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new ThePowers().find(A, B));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
