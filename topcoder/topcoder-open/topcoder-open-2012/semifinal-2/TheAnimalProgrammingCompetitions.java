import java.math.*;
import java.util.*;

public class TheAnimalProgrammingCompetitions {
    final static int MOD = 1234567891;

    public int find(int[] rabbits) {
        int n = rabbits.length;
        int m = 0;
        for (int rabbit : rabbits) {
            m += rabbit;
        }
        long[][] binom = new long[4 * n + 1][4 * n + 1];
        for (int i = 0; i <= 4 * n; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
        long[] factorial = new long[4 * n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= 4 * n; ++ i) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }
        long[] ways = new long[m + 1];
        ways[0] = 1;
        for (int rabbit : rabbits) {
            long[] newWays = new long[m + 1];
            for (int old = 0; old <= m; ++ old) {
                if (ways[old] == 0) {
                    continue;
                }
                for (int bad = 0; bad <= rabbit; ++ bad) {
                    newWays[old + bad] += ways[old] * binom[rabbit][bad] % MOD * binom[4][bad] % MOD * factorial[bad] % MOD;
                    newWays[old + bad] %= MOD;
                }
            }
            ways = newWays;
        }
        long answer = 0;
        for (int bad = 0, sign = 1; bad <= m; ++ bad, sign *= -1) {
            answer += sign * ways[bad] * binom[4 * n - bad][m - bad] % MOD * factorial[m - bad] % MOD;
            answer %= MOD;
        }
        answer = (answer + MOD) % MOD;
        return (int)answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TheAnimalProgrammingCompetitionsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheAnimalProgrammingCompetitionsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheAnimalProgrammingCompetitionsHarness {
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
			int[] rabbits             = {0, 1, 0};
			int expected__            = 8;

			return verifyCase(casenum__, expected__, new TheAnimalProgrammingCompetitions().find(rabbits));
		}
		case 1: {
			int[] rabbits             = {4};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new TheAnimalProgrammingCompetitions().find(rabbits));
		}
		case 2: {
			int[] rabbits             = {1, 1, 0};
			int expected__            = 60;

			return verifyCase(casenum__, expected__, new TheAnimalProgrammingCompetitions().find(rabbits));
		}
		case 3: {
			int[] rabbits             = {0, 1, 2, 3, 4};
			int expected__            = 644027397;

			return verifyCase(casenum__, expected__, new TheAnimalProgrammingCompetitions().find(rabbits));
		}

		// custom cases

      case 4: {
			int[] rabbits             = {4, 3, 2, 3, 4, 3};
			int expected__            = 15372184;

			return verifyCase(casenum__, expected__, new TheAnimalProgrammingCompetitions().find(rabbits));
		}
/*      case 5: {
			int[] rabbits             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheAnimalProgrammingCompetitions().find(rabbits));
		}*/
/*      case 6: {
			int[] rabbits             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheAnimalProgrammingCompetitions().find(rabbits));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
