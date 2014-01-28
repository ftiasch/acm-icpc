import java.math.*;
import java.util.*;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double norm() {
        return Math.sqrt(x * x + y * y);
    }

    Point subtract(Point o) {
        return new Point(x - o.x, y - o.y);
    }

    int to(Point a, Point b) {
        double result = det(a.subtract(this), b.subtract(this)) / a.subtract(b).norm();
        if (result < 0) {
            return -1;
        }
        return result > 0 ? 1 : 0;
    }

    static double det(Point a, Point b) {
        return a.x * b.y - a.y * b.x;
    }
}

public class FencingPenguins {
    static int MOD = (int)1e5 + 7;

    long[][] colorMask, pointMask;

    int[][] sum;

    boolean isValid(int i, int j) {
        return (colorMask[i][j] & colorMask[j][i]) == 0;
    }

    int hasPoints(int i, int j, int k) {
        return (pointMask[i][j] & pointMask[j][k] & pointMask[k][i]) == 0 ? 0 : 1;
    }

    int getSeperatedWays(int i, int j) {
        int result = pointMask[j][i] == 0 ? 1 : 0;
        if (j - i >= 4 && (pointMask[j][i] & pointMask[i + 1][j - 1]) == 0) {
            result += sum[i + 1][j - 1];
            if (result >= MOD) {
                result -= MOD;
            }
        }
        return result;
    }

    public int countWays(int numPosts, int radius, int[] x, int[] y, String color) {
        int n = numPosts;
        int m = x.length;
        Point[] post = new Point[n];
        for (int i = 0; i < n; ++ i) {
            double a = 2 * Math.PI / n * i;
            post[i] = new Point(radius * Math.cos(a), radius * Math.sin(a));
        }
        int[] id = new int[m];
        Point[] penguin = new Point[m];
        for (int i = 0; i < m; ++ i) {
            char c = color.charAt(i);
            id[i] = Character.isUpperCase(c) ? c - 'A' : c - 'a' + 26;
            penguin[i] = new Point(x[i], y[i]);
        }
        colorMask = new long[n][n];
        pointMask = new long[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                for (int k = 0; k < m; ++ k) {
                    if (penguin[k].to(post[i], post[j]) > 0) {
                        pointMask[i][j] |= 1L << k;
                        colorMask[i][j] |= 1L << id[k];
                    }
                }
            }
        }
        for (int i = 0; i < n; ++ i) {
            if (pointMask[(i + 1) % n][i] > 0) {
                return 0;
            }
        }
        sum = new int[n][n];
        int[][][] ways = new int[n][n][2];
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = i + 1; j < n; ++ j) {
                for (int t = 0; t < 2; ++ t) {
                    for (int k = i + 1; k < j; ++ k) {
                        if (isValid(i, k)) {
                            ways[i][j][t] += (int)((long)ways[k][j][t | hasPoints(i, k, j)] * getSeperatedWays(i, k) % MOD);
                            if (ways[i][j][t] >= MOD) {
                                ways[i][j][t] -= MOD;
                            }
                        }
                    }
                    if (t > 0 && isValid(i, j)) {
                        ways[i][j][t] += getSeperatedWays(i, j);
                        if (ways[i][j][t] >= MOD) {
                            ways[i][j][t] -= MOD;
                        }
                    }
                }
                if (hasPoints(i, i + 1, j) == 0) {
                    sum[i][j] += sum[i + 1][j];
                }
                for (int k = i + 1; k <= j; ++ k) {
                    if (isValid(i, k) && hasPoints(i, k, j) == 0) {
                        int extra = 0;
                        if (pointMask[j][k] == 0) {
                            extra += 1;
                        }
                        if (j - k >= 3 && hasPoints(k, k + 1, j) == 0) {
                            extra += sum[k + 1][j];
                            if (extra >= MOD) {
                                extra -= MOD;
                            }
                        }
                        sum[i][j] += (int)((long)ways[i][k][0] * extra % MOD);
                        if (sum[i][j] >= MOD) {
                            sum[i][j] -= MOD;
                        }
                    }
                }
            }
        }
        return sum[0][n - 1];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FencingPenguinsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FencingPenguinsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FencingPenguinsHarness {
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
			int numPosts              = 4;
			int radius                = 10;
			int[] x                   = {2};
			int[] y                   = {1};
			String color              = "R";
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}
		case 1: {
			int numPosts              = 4;
			int radius                = 10;
			int[] x                   = {2,-2};
			int[] y                   = {1,-1};
			String color              = "RR";
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}
		case 2: {
			int numPosts              = 8;
			int radius                = 10;
			int[] x                   = {8,-8,-8,8};
			int[] y                   = {1,-1,1,-1};
			String color              = "BBBB";
			int expected__            = 25;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}
		case 3: {
			int numPosts              = 8;
			int radius                = 10;
			int[] x                   = {8,-8,-8,8};
			int[] y                   = {1,-1,1,-1};
			String color              = "RGBY";
			int expected__            = 50;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}
		case 4: {
			int numPosts              = 6;
			int radius                = 5;
			int[] x                   = {0,0};
			int[] y                   = {-4,4};
			String color              = "rB";
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}
		case 5: {
			int numPosts              = 3;
			int radius                = 5;
			int[] x                   = {4};
			int[] y                   = {3};
			String color              = "y";
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}
		case 6: {
			int numPosts              = 200;
			int radius                = 100000;
			int[] x                   = {1020,30203,2302,203,-12321,-21332,8823,-2133,2323};
			int[] y                   = {-123,2131,4434,1223,43434,2323,4343,-213,-2325};
			String color              = "YBYBWWBRr";
			int expected__            = 27547;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}

		// custom cases

/*      case 7: {
			int numPosts              = ;
			int radius                = ;
			int[] x                   = ;
			int[] y                   = ;
			String color              = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}*/
/*      case 8: {
			int numPosts              = ;
			int radius                = ;
			int[] x                   = ;
			int[] y                   = ;
			String color              = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}*/
/*      case 9: {
			int numPosts              = ;
			int radius                = ;
			int[] x                   = ;
			int[] y                   = ;
			String color              = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FencingPenguins().countWays(numPosts, radius, x, y, color));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
