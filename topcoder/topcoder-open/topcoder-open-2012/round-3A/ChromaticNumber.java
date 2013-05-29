import java.math.*;
import java.util.*;

public class ChromaticNumber {
    boolean[] visit;
    boolean[][] graph;

    public int minColors(String[] strings) {
        int n = strings.length;
        graph = new boolean[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = strings[i].charAt(j) == 'N';
            }
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    graph[i][j] |= graph[i][k] && graph[k][j];
                }
            }
        }
        int answer = 0;
        visit = new boolean[n];
        for (int i = 0; i < n; ++ i) {
            if (!visit[i]) {
                int count = 0;
                for (int j = 0; j < n; ++ j) {
                    if (graph[i][j]) {
                        visit[j] = true;
                        count ++;
                    }
                }
                if (count == 3) {
                    boolean found = false;
                    for (int j = 0; j < n; ++ j) {
                        for (int k = 0; k < n; ++ k) {
                            if (i != j && j != k && k != i && strings[i].charAt(j) == 'N' && strings[j].charAt(k) == 'N' && strings[k].charAt(i) == 'N') {
                                found = true;
                            }
                        }
                    }
                    if (found) {
                        answer ++;
                    } else {
                        answer += 2;
                    }
                } else {
                    answer += count + 1 >> 1;
                }
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			ChromaticNumberHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ChromaticNumberHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ChromaticNumberHarness {
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
			String[] graph            = {"N"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new ChromaticNumber().minColors(graph));
		}
		case 1: {
			String[] graph            = {"NYY",
 "YNN",
 "YNN"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new ChromaticNumber().minColors(graph));
		}
		case 2: {
			String[] graph            = {"NYNN",
 "YNNN",
 "NNNY",
 "NNYN"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new ChromaticNumber().minColors(graph));
		}
		case 3: {
			String[] graph            = {"NYNY",
 "YNYY",
 "NYNN",
 "YYNN"};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new ChromaticNumber().minColors(graph));
		}
		case 4: {
			String[] graph            = {"NYYYYYYY",
 "YNYYYYYY",
 "YYNYYYYY",
 "YYYNYYYY",
 "YYYYNYYY",
 "YYYYYNYY",
 "YYYYYYNY",
 "YYYYYYYN"};
			int expected__            = 8;

			return verifyCase(casenum__, expected__, new ChromaticNumber().minColors(graph));
		}

		// custom cases

/*      case 5: {
			String[] graph            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ChromaticNumber().minColors(graph));
		}*/
/*      case 6: {
			String[] graph            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ChromaticNumber().minColors(graph));
		}*/
/*      case 7: {
			String[] graph            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ChromaticNumber().minColors(graph));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
