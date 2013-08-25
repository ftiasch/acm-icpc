import java.math.*;
import java.util.*;

public class ScotlandYard {
    final static long INF = 1000000000000000000L;

    int n;
    long[][] memory;
    boolean[][][] graph;

    long solve(int u, int v) {
        if (memory[u][v] == -2) {
            memory[u][v] = INF;
        }
        if (memory[u][v] == -1) {
            if (u == v) {
                memory[u][v] = 0;
            } else {
                memory[u][v] = -2;
                long ret = 0;
                for (int k = 0; k < 3; ++ k) {
                    for (int i = 0; i < n; ++ i) {
                        if (graph[k][u][i] || graph[k][v][i]) {
                            for (int j = 0; j <= i; ++ j) {
                                if (graph[k][u][j] || graph[k][v][j]) {
                                    ret = Math.max(ret, solve(i, j) + 1);
                                }
                            }
                        }
                    }
                }
                memory[u][v] = Math.min(ret, INF);
            }
        }
        return memory[u][v];
    }

    public int maxMoves(String[] taxi, String[] bus, String[] metro) {
        n = taxi.length;
        graph = new boolean[3][n][n];
        String[][] strings = new String[][]{taxi, bus, metro};
        boolean found = false;
        for (int k = 0; k < 3; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    graph[k][i][j] = strings[k][i].charAt(j) == 'Y';
                }
            }
        }
        memory = new long[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                memory[i][j] = -1;
            }
        }
        long answer = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < i; ++ j) {
                answer = Math.max(answer, solve(i, j));
            }
        }
        return answer == INF ? -1 : (int)answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			ScotlandYardHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ScotlandYardHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ScotlandYardHarness {
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
			String[] taxi             = {"NYN",
 "NNY",
 "NNN"};
			String[] bus              = {"NNN",
 "NNN",
 "NNN"};
			String[] metro            = {"NNN",
 "NNN",
 "NNN"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new ScotlandYard().maxMoves(taxi, bus, metro));
		}
		case 1: {
			String[] taxi             = {"NYY",
 "NNN",
 "NNN"};
			String[] bus              = {"NNN",
 "NNN",
 "NNN"};
			String[] metro            = {"NNN",
 "NNN",
 "NNN"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new ScotlandYard().maxMoves(taxi, bus, metro));
		}
		case 2: {
			String[] taxi             = {"NYYY",
 "YNYY",
 "YYNY",
 "YYYN"};
			String[] bus              = {"NNNN",
 "NNNN",
 "NNNN",
 "NNNN"};
			String[] metro            = {"NNNN",
 "NNNN",
 "NNNN",
 "NNNN"};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new ScotlandYard().maxMoves(taxi, bus, metro));
		}
		case 3: {
			String[] taxi             = {"NNY",
 "NNY",
 "NNN"};
			String[] bus              = {"NYN",
 "NNY",
 "NNN"};
			String[] metro            = {"NNN",
 "NNN",
 "YNN"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new ScotlandYard().maxMoves(taxi, bus, metro));
		}
		case 4: {
			String[] taxi             = {"NNN",
 "YNY",
 "NNN"};
			String[] bus              = {"NNN",
 "YNN",
 "YNN"};
			String[] metro            = {"NNN",
 "NNN",
 "YYN"};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new ScotlandYard().maxMoves(taxi, bus, metro));
		}
		case 5: {
			String[] taxi             = {"NNNNYNNNYY",
 "NNYNNYYYYY",
 "NNNNNNNNNN",
 "YYNNYYNNNY",
 "NNYNNNNNNN",
 "YNYNYNNNYN",
 "NNYNYNNNYN",
 "NNNNNNYNNN",
 "NNNNNNNNNN",
 "NNNNNNYNNN"};
			String[] bus              = {"NNYNNNYNNY",
 "YNYNNYYNYY",
 "NNNNNNNNNN",
 "YNYNNYNYNY",
 "NNYNNNNNYN",
 "YNYNYNYNYN",
 "NNYNNNNNNY",
 "YNYNNNNNNN",
 "NNNNNNNNNN",
 "NNYNYNNNNN"};
			String[] metro            = {"NNNNNNNYNN",
 "YNYNNNNNYN",
 "NNNNNNNNNN",
 "NYNNYNNNYY",
 "NNYNNNNNNN",
 "YNYNNNNNYY",
 "NNNNYNNNYN",
 "NNYNNNYNNN",
 "NNNNNNNNNY",
 "NNYNYNNNNN"};
			int expected__            = 21;

			return verifyCase(casenum__, expected__, new ScotlandYard().maxMoves(taxi, bus, metro));
		}

		// custom cases

/*      case 6: {
			String[] taxi             = ;
			String[] bus              = ;
			String[] metro            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ScotlandYard().maxMoves(taxi, bus, metro));
		}*/
/*      case 7: {
			String[] taxi             = ;
			String[] bus              = ;
			String[] metro            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ScotlandYard().maxMoves(taxi, bus, metro));
		}*/
/*      case 8: {
			String[] taxi             = ;
			String[] bus              = ;
			String[] metro            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ScotlandYard().maxMoves(taxi, bus, metro));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
