import java.math.*;
import java.util.*;

public class ConvexPolygonGame {
    final static String NO = "Petya";
    final static String YES = "Masha";

    final static int MAX_ABS = 100000;

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point subtract(Point o) {
            return new Point(x - o.x, y - o.y);
        }

        long det(Point o) {
            return (long)x * o.y - (long)y * o.x;
        }
    }

    public String winner(int[] x, int[] y) {
        int n = x.length;
        for (int i = 0; i < n; ++ i) {
            x[i] += MAX_ABS;
            y[i] += MAX_ABS;
        }
        ArrayList <Point> points = new ArrayList <Point>();
        int oneCount = 0;
        int twoCount = 0;
        for (int x0 = 0; x0 <= MAX_ABS * 2; ++ x0) {
            boolean found = false;
            double yMin = Double.MAX_VALUE;
            double yMax = Double.MIN_VALUE;
            for (int i = 0; i < n; ++ i) {
                int x1 = x[i];
                int y1 = y[i];
                int x2 = x[(i + 1) % n];
                int y2 = y[(i + 1) % n];
                if ((long)(x1 - x0) * (x2 - x0) <= 0) {
                    found = true;
                    if (x1 == x2) {
                        yMin = Math.min(yMin, Math.min(y1, y2));
                        yMax = Math.max(yMax, Math.max(y1, y2));
                    } else {
                        double y0 = y1 + (double)(y2 - y1) / (x2 - x1) * (x0 - x1);
                        yMin = Math.min(yMin, y0);
                        yMax = Math.max(yMax, y0);
                    }
                }
            }
            if (found) {
                int count = 0;
                int y0 = (int)Math.ceil(yMin);
                while (count < 2 && y0 <= yMax) {
                    boolean valid = true;
                    for (int i = 0; i < n; ++ i) {
                        valid &= x[i] != x0 || y[i] != y0;
                    }
                    if (valid) {
                        count ++;
                        points.add(new Point(x0, y0));
                    }
                    y0 ++;
                }
                if (count >= 1) {
                    oneCount ++;
                }
                if (count >= 2) {
                    twoCount ++;
                }
            }
        }
        if (twoCount > 0) {
            return oneCount > 1 ? YES: NO;
        }
        if (points.size() <= 2) {
            return NO;
        }
        Point o = points.get(0);
        for (int i = 2; i < points.size(); ++ i) {
            if (points.get(1).subtract(o).det(points.get(i).subtract(o)) != 0) {
                return YES;
            }
        }
        return NO;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			ConvexPolygonGameHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ConvexPolygonGameHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ConvexPolygonGameHarness {
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
			int[] X                   = {0, 1, 0};
			int[] Y                   = {0, 0, 1};
			String expected__         = "Petya";

			return verifyCase(casenum__, expected__, new ConvexPolygonGame().winner(X, Y));
		}
		case 1: {
			int[] X                   = {0, 4, 2};
			int[] Y                   = {0, 0, 2};
			String expected__         = "Masha";

			return verifyCase(casenum__, expected__, new ConvexPolygonGame().winner(X, Y));
		}
		case 2: {
			int[] X                   = {0, 100, 100, 0};
			int[] Y                   = {0, 0, 100, 100};
			String expected__         = "Masha";

			return verifyCase(casenum__, expected__, new ConvexPolygonGame().winner(X, Y));
		}
		case 3: {
			int[] X                   = {0, 50, 100, 50};
			int[] Y                   = {0, -1, 0, 1};
			String expected__         = "Petya";

			return verifyCase(casenum__, expected__, new ConvexPolygonGame().winner(X, Y));
		}
		case 4: {
			int[] X                   = {-100000, 100000, 100000, -100000};
			int[] Y                   = {-1, -1, 1, 1};
			String expected__         = "Masha";

			return verifyCase(casenum__, expected__, new ConvexPolygonGame().winner(X, Y));
		}

		// custom cases

        case 5: {
			int[] X                   = {-1, 0, 0, -1};
			int[] Y                   = {-500, -500, 500, 500};
			String expected__         = "Masha";

			return verifyCase(casenum__, expected__, new ConvexPolygonGame().winner(X, Y));
		}  
/*      case 6: {
			int[] X                   = ;
			int[] Y                   = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new ConvexPolygonGame().winner(X, Y));
		}*/
/*      case 7: {
			int[] X                   = ;
			int[] Y                   = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new ConvexPolygonGame().winner(X, Y));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
