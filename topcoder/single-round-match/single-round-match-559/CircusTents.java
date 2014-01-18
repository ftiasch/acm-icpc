import java.math.*;
import java.util.*;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Point add(Point other) {
        return new Point(x + other.x, y + other.y);
    }

    Point subtract(Point other) {
        return new Point(x - other.x, y - other.y);
    }

    Point multiply(double k) {
        return new Point(x * k, y * k);
    }

    Point setLength(double length) {
        double scale = length / norm();
        return this.multiply(scale);
    }

    Point rotate(double a) {
        return new Point(Math.cos(a) * x - Math.sin(a) * y, Math.sin(a) * x + Math.cos(a) * y);
    }

    double norm2() {
        return x * x + y * y;
    }

    double norm() {
        return Math.sqrt(norm2());
    }

    double angle() {
        return Math.atan2(y, x);
    }
}

@SuppressWarnings("unchecked")
public class CircusTents {
    final static double PI = Math.acos(-1);

    Point getIntersection(Point a, double ra, Point b, double rb) {
        double ab = a.subtract(b).norm();
        // ra^2 - x^2 = rb^2 - (ab - x)^2
        // ra^2 - x^2 = rb^2 - ab^2 + 2ab x - x^2
        double x = ((ra * ra - rb * rb) / ab + ab) * 0.5;
        double h = Math.sqrt(ra * ra - x * x);
        Point p = b.subtract(a);
        return a.add(p.setLength(x)).add(p.rotate(PI / 2).setLength(h));
    }

    class Event implements Comparable {
        double angle;
        int delta;

        Event(double angle, int delta) {
            this.angle = angle;
            this.delta = delta;
        }

        @Override
        public int compareTo(Object o) {
            Event other = (Event)o;
            return signum(angle - other.angle);
        }

        int signum(double x) {
            if (x < 0) {
                return -1;
            }
            return x > 0 ? 1 : 0;
        }

        public String toString() {
            return String.format("<%.2f, %d>", angle, delta);
        }
    }

    void addEvent(ArrayList <Event> events, Point a, Point b) {
        double x = a.angle();
        double y = b.angle();
        events.add(new Event(x, 1));
        events.add(new Event(y, -1));
        if (x > y) {
            events.add(new Event(PI, -1));
            events.add(new Event(-PI, 1));
        }
    }

    double getArcDistance(Point a, Point b) {
        double x = a.angle();
        double y = b.angle();
        return x <= y ? y - x : (PI - x) + (y + PI);
    }

    boolean check(int[] x, int[] y, int[] r, double d) {
        int n = x.length;
        Point[] p = new Point[n];
        for (int i = 0; i < n; ++ i) {
            p[i] = new Point(x[i], y[i]);
        }
        ArrayList <Event> events = new ArrayList <Event>();
        events.add(new Event(-PI, 0));
        events.add(new Event(PI, 0));
        for (int i = 1; i < n; ++ i) {
            if (p[0].subtract(p[i]).norm() < r[0] + (r[i] + d)) {
                double tangentLength = Math.sqrt((p[0].subtract(p[i])).norm2() - r[0] * r[0]);
                if (r[i] + d <= tangentLength) {
                    Point a = getIntersection(p[i], r[i] + d, p[0], r[0]).subtract(p[0]);
                    Point b = getIntersection(p[0], r[0], p[i], r[i] + d).subtract(p[0]);
                    addEvent(events, a, b);
                } else {
                    Point o = p[0].add(p[i]).multiply(0.5);
                    double ro = p[0].subtract(p[i]).norm() * 0.5;
                    Point a = getIntersection(o, ro, p[0], r[0]).subtract(p[0]);
                    Point b = getIntersection(p[0], r[0], o, ro).subtract(p[0]);
                    double alpha = (d - tangentLength + r[i]) / r[0];
                    if (getArcDistance(a, b) + 2 * alpha >= 2 * PI) {
                        return false;
                    }
                    addEvent(events, a.rotate(-alpha), b.rotate(alpha));
                }
            }
        }
        Collections.sort(events);
        int now = 0;
        for (int i = 0; i + 1 < events.size(); ++ i) {
            now += events.get(i).delta;
            if (now == 0 && events.get(i).angle < events.get(i + 1).angle) {
                return true;
            }
        }
        return false;
    }

    public double findMaximumDistance(int[] x, int[] y, int[] r) {
        double low = 0;
        double high = 1e6;
        for (int _ = 0; _ < 100; ++ _) {
            double middle = (low + high) * 0.5;
            if (check(x, y, r, middle)) {
                low = middle;
            } else {
                high = middle;
            }
        }
        return low;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			CircusTentsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CircusTentsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CircusTentsHarness {
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
	
	static final double MAX_DOUBLE_ERROR = 1E-9;
	static boolean compareOutput(double expected, double result){ if(Double.isNaN(expected)){ return Double.isNaN(result); }else if(Double.isInfinite(expected)){ if(expected > 0){ return result > 0 && Double.isInfinite(result); }else{ return result < 0 && Double.isInfinite(result); } }else if(Double.isNaN(result) || Double.isInfinite(result)){ return false; }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){ return true; }else{ double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > min && result < max; } }
	static double relativeError(double expected, double result) { if (Double.isNaN(expected) || Double.isInfinite(expected) || Double.isNaN(result) || Double.isInfinite(result) || expected == 0) return 0; return Math.abs(result-expected) / Math.abs(expected); }
	
	static String formatResult(double res) {
		return String.format("%.10g", res);
	}
	
	static int verifyCase(int casenum, double expected, double received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.print("PASSED");
			double rerr = relativeError(expected, received);
			if (rerr > 0) System.err.printf(" (relative error %g)", rerr);
			System.err.println();
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
			int[] x                   = {0,3};
			int[] y                   = {0,0};
			int[] r                   = {1,1};
			double expected__         = 3.7390603609952078;

			return verifyCase(casenum__, expected__, new CircusTents().findMaximumDistance(x, y, r));
		}
		case 1: {
			int[] x                   = {0,3,-3,3,-3};
			int[] y                   = {0,3,3,-3,-3};
			int[] r                   = {1,1,1,1,1};
			double expected__         = 2.6055512754639887;

			return verifyCase(casenum__, expected__, new CircusTents().findMaximumDistance(x, y, r));
		}
		case 2: {
			int[] x                   = {3,7,7,7,3};
			int[] y                   = {4,6,1,-3,0};
			int[] r                   = {2,2,2,1,1};
			double expected__         = 4.3264459099620725;

			return verifyCase(casenum__, expected__, new CircusTents().findMaximumDistance(x, y, r));
		}
		case 3: {
			int[] x                   = {10,-1};
			int[] y                   = {0,0};
			int[] r                   = {8,2};
			double expected__         = 24.63092458664212;

			return verifyCase(casenum__, expected__, new CircusTents().findMaximumDistance(x, y, r));
		}
		case 4: {
			int[] x                   = {0,4,-4};
			int[] y                   = {0,4,-4};
			int[] r                   = {1,1,1};
			double expected__         = 4.745474963675133;

			return verifyCase(casenum__, expected__, new CircusTents().findMaximumDistance(x, y, r));
		}

		// custom cases

/*      case 5: {
			int[] x                   = ;
			int[] y                   = ;
			int[] r                   = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new CircusTents().findMaximumDistance(x, y, r));
		}*/
/*      case 6: {
			int[] x                   = ;
			int[] y                   = ;
			int[] r                   = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new CircusTents().findMaximumDistance(x, y, r));
		}*/
/*      case 7: {
			int[] x                   = ;
			int[] y                   = ;
			int[] r                   = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new CircusTents().findMaximumDistance(x, y, r));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
