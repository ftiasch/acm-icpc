import java.math.*;
import java.util.*;

public class OldBridges {
    final static int INF = 1000000000;

    int[] level;
    int[][] flow;

    boolean bfs(int s, int t) {
        level = new int[t + 1];
        Arrays.fill(level, -1);
        level[s] = 0;
        Queue <Integer> queue = new LinkedList <Integer>();
        queue.offer(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v <= t; ++ v) {
                if (flow[u][v] > 0 && level[v] == -1) {
                    level[v] = level[u] + 1;
                    queue.offer(v);
                }
            }
        }
        return level[t] != -1;
    }

    int dfs(int u, int t, int d) {
        if (u == t) {
            return d;
        }
        int result = 0;
        for (int v = 0; v <= t; ++ v) {
            if (flow[u][v] > 0 && level[u] + 1 == level[v]) {
                int ret = dfs(v, t, Math.min(d - result, flow[u][v]));
                flow[u][v] -= ret;
                flow[v][u] += ret;
                result += ret;
                if (result == d) {
                    break;
                }
            }
        }
        return result;
    }

    boolean check(String[] bridges, int a1, int a2, int an, int b1, int b2, int bn) {
        int n = bridges.length;
        flow = new int[n + 2][n + 2];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (bridges[i].charAt(j) == 'O') {
                    flow[i][j] = 2;
                }
                if (bridges[i].charAt(j) == 'N') {
                    flow[i][j] = INF;
                }
            }
        }
        flow[n][a1] = flow[a2][n + 1] = 2 * an;
        flow[n][b1] = flow[b2][n + 1] = 2 * bn;
        int result = 0;
        while (bfs(n, n + 1)) {
            result += dfs(n, n + 1, INF);
        }
        return result >= 2 * (an + bn);
    }

    public String isPossible(String[] bridges, int a1, int a2, int an, int b1, int b2, int bn) {
        return check(bridges, a1, a2, an, b1, b2, bn) && check(bridges, a1, a2, an, b2, b1, bn) ? "Yes" : "No";
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			OldBridgesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				OldBridgesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class OldBridgesHarness {
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

	static int runTestCase(int casenum__) {
		switch(casenum__) {
		case 0: {
			String[] bridges          = {"XOXX","OXOX","XOXO","XXOX"};
			int a1                    = 0;
			int a2                    = 1;
			int an                    = 1;
			int b1                    = 2;
			int b2                    = 3;
			int bn                    = 1;
			String expected__         = "Yes";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 1: {
			String[] bridges          = {"XOXX","OXOX","XOXO","XXOX"};
			int a1                    = 0;
			int a2                    = 2;
			int an                    = 1;
			int b1                    = 1;
			int b2                    = 3;
			int bn                    = 1;
			String expected__         = "No";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 2: {
			String[] bridges          = {"XOXO","OXOX","XOXO","OXOX"};
			int a1                    = 0;
			int a2                    = 2;
			int an                    = 1;
			int b1                    = 1;
			int b2                    = 3;
			int bn                    = 1;
			String expected__         = "Yes";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 3: {
			String[] bridges          = {"XNXO","NXOX","XOXO","OXOX"};
			int a1                    = 0;
			int a2                    = 2;
			int an                    = 1;
			int b1                    = 1;
			int b2                    = 3;
			int bn                    = 2;
			String expected__         = "No";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 4: {
			String[] bridges          = {"XOXOO","OXOXO","XOXOO","OXOXO","OOOOX"};
			int a1                    = 0;
			int a2                    = 2;
			int an                    = 2;
			int b1                    = 1;
			int b2                    = 3;
			int bn                    = 2;
			String expected__         = "Yes";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 5: {
			String[] bridges          = {"XOOOX","OXOOX","OOXOX","OOOXN","XXXNX"};
			int a1                    = 0;
			int a2                    = 4;
			int an                    = 3;
			int b1                    = 1;
			int b2                    = 2;
			int bn                    = 2;
			String expected__         = "No";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}

		// custom cases

/*      case 6: {
			String[] bridges          = ;
			int a1                    = ;
			int a2                    = ;
			int an                    = ;
			int b1                    = ;
			int b2                    = ;
			int bn                    = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}*/
/*      case 7: {
			String[] bridges          = ;
			int a1                    = ;
			int a2                    = ;
			int an                    = ;
			int b1                    = ;
			int b2                    = ;
			int bn                    = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}*/
/*      case 8: {
			String[] bridges          = ;
			int a1                    = ;
			int a2                    = ;
			int an                    = ;
			int b1                    = ;
			int b2                    = ;
			int bn                    = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
