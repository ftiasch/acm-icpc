import java.math.*;
import java.util.*;

public class GooseInZooDivOne {
    final static int MOD = 1000000000 + 7;

    int n, m;
    boolean[][] visit;

    int getID(int x, int y) {
        return x * m + y;
    }

    int counter;

    void dfs(String[] field, int dist, int x, int y) {
        if (visit[x][y]) {
            return;
        }
        visit[x][y] = true;
        counter ++;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (field[i].charAt(j) == 'v' && Math.abs(i - x) + Math.abs(j - y) <= dist) {
                    dfs(field, dist, i, j);
                }
            }
        }
    }

    public int count(String[] field, int dist) {
        n = field.length;
        m = field[0].length();
        visit = new boolean[n][m];
        ArrayList <Integer> groups = new ArrayList <Integer>();
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (field[i].charAt(j) == 'v' && !visit[i][j]) {
                    counter = 0;
                    dfs(field, dist, i, j);
                    groups.add(counter);
                }
            }
        }
        int[] ways = new int[2];
        ways[0] = 1;
        for (int size : groups) {
            int[] newWays = new int[2];
            for (int i = 0; i < 2; ++ i) {
                newWays[i] = (ways[i + size & 1] + ways[i]) % MOD;
            }
            ways = newWays;
        }
        return (ways[0] + MOD - 1) % MOD;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			GooseInZooDivOneHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				GooseInZooDivOneHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class GooseInZooDivOneHarness {
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
			String[] field            = {"vvv"};
			int dist                  = 0;
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new GooseInZooDivOne().count(field, dist));
		}
		case 1: {
			String[] field            = {"."};
			int dist                  = 100;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new GooseInZooDivOne().count(field, dist));
		}
		case 2: {
			String[] field            = {"vvv"};
			int dist                  = 1;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new GooseInZooDivOne().count(field, dist));
		}
		case 3: {
			String[] field            = {"v.v..................v............................"
,".v......v..................v.....................v"
,"..v.....v....v.........v...............v......v..."
,".........vvv...vv.v.........v.v..................v"
,".....v..........v......v..v...v.......v..........."
,"...................vv...............v.v..v.v..v..."
,".v.vv.................v..............v............"
,"..vv.......v...vv.v............vv.....v.....v....."
,"....v..........v....v........v.......v.v.v........"
,".v.......v.............v.v..........vv......v....."
,"....v.v.......v........v.....v.................v.."
,"....v..v..v.v..............v.v.v....v..........v.."
,"..........v...v...................v..............v"
,"..v........v..........................v....v..v..."
,"....................v..v.........vv........v......"
,"..v......v...............................v.v......"
,"..v.v..............v........v...............vv.vv."
,"...vv......v...............v.v..............v....."
,"............................v..v.................v"
,".v.............v.......v.........................."
,"......v...v........................v.............."
,".........v.....v..............vv.................."
,"................v..v..v.........v....v.......v...."
,"........v.....v.............v......v.v............"
,"...........v....................v.v....v.v.v...v.."
,"...........v......................v...v..........."
,"..........vv...........v.v.....................v.."
,".....................v......v............v...v...."
,".....vv..........................vv.v.....v.v....."
,".vv.......v...............v.......v..v.....v......"
,"............v................v..........v....v...."
,"................vv...v............................"
,"................v...........v........v...v....v..."
,"..v...v...v.............v...v........v....v..v...."
,"......v..v.......v........v..v....vv.............."
,"...........v..........v........v.v................"
,"v.v......v................v....................v.."
,".v........v................................v......"
,"............................v...v.......v........."
,"........................vv.v..............v...vv.."
,".......................vv........v.............v.."
,"...v.............v.........................v......"
,"....v......vv...........................v........."
,"....vv....v................v...vv..............v.."
,".................................................."
,"vv........v...v..v.....v..v..................v...."
,".........v..............v.vv.v.............v......"
,".......v.....v......v...............v............."
,"..v..................v................v....v......"
,".....v.....v.....................v.v......v......."};
			int dist                  = 3;
			int expected__            = 898961330;

			return verifyCase(casenum__, expected__, new GooseInZooDivOne().count(field, dist));
		}

		// custom cases

/*      case 4: {
			String[] field            = ;
			int dist                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GooseInZooDivOne().count(field, dist));
		}*/
/*      case 5: {
			String[] field            = ;
			int dist                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GooseInZooDivOne().count(field, dist));
		}*/
/*      case 6: {
			String[] field            = ;
			int dist                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GooseInZooDivOne().count(field, dist));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
