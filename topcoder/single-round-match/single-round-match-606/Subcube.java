import java.math.*;
import java.util.*;

public class Subcube {
    public long getCount(int[] x, int[] y, int[] z) {
        int n = x.length;
        Point[] points = new Point[n];
        for (int i = 0; i < n; ++ i) {
            points[i] = new Point(x[i], y[i], z[i]);
        }
        return search(points, new TreeSet <Long>(), new Point[8], 0, 0, 0, 0).size() - 1;
    }

    Set <Long> search(Point[] points, Set <Long> subcubes, Point[] subcube, int i, long mask, long scaleP, long scaleQ) {
        if (i < 8) {
            search(points, subcubes, subcube, i + 1, mask, scaleP, scaleQ);
            int n = points.length;
            for (int j = 0; j < n; ++ j) {
                if ((mask >> j & 1) == 0) {
                    subcube[i] = points[j];
                    long newScaleP = scaleP;
                    long newScaleQ = scaleQ;
                    boolean valid = true;
                    for (int k = 0; k < i && valid; ++ k) {
                        if (subcube[k] != null) {
                            long norm = subcube[i].to(subcube[k]);
                            long norm1 = Integer.bitCount(i ^ k);
                            valid &= newScaleP * norm1 == norm * newScaleQ;
                            newScaleP = norm;
                            newScaleQ = norm1;
                        }
                    }
                    if (valid) {
                        search(points, subcubes, subcube, i + 1, mask | 1L << j, newScaleP, newScaleQ);
                    }
                    subcube[i] = null;
                }
            }
        } else {
            subcubes.add(mask);
        }
        return subcubes;
    }

    class Point {
        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        long to(Point o) {
            return square(x - o.x) + square(y - o.y) + square(z - o.z);
        }

        private long square(long x) {
            return x * x;
        }

        int x, y, z;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SubcubeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SubcubeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SubcubeHarness {
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
            int[] X                   = {0, 0, 0, 0, 1, 1, 1, 1};
            int[] Y                   = {0, 0, 1, 1, 0, 0, 1, 1};
            int[] Z                   = {0, 1, 0, 1, 0, 1, 0, 1};
            long expected__           = 255;

            return verifyCase(casenum__, expected__, new Subcube().getCount(X, Y, Z));
        }
        case 1: {
            int[] X                   = {-2, -1, -0, 1, 2};
            int[] Y                   = {-2, -1, -0, 1, 2};
            int[] Z                   = {-2, -1, -0, 1, 2};
            long expected__           = 15;

            return verifyCase(casenum__, expected__, new Subcube().getCount(X, Y, Z));
        }
        case 2: {
            int[] X                   = {3,6,-4,-8,0,0} ;
            int[] Y                   = {4,8,3,6,0,0} ;
            int[] Z                   = {0,0,0,0,5,10} ;
            long expected__           = 23;

            return verifyCase(casenum__, expected__, new Subcube().getCount(X, Y, Z));
        }
        case 3: {
            int[] X                   = {1, 0, 2, -2, 1, 2, 2, 1, 2, 0, 3, 2, 0};
            int[] Y                   = {2, -3, -1, 2, 3, -3, -1, -2, 2, 1, 0, 2, 0};
            int[] Z                   = {1, -3, 0, 3, 1, 2, 2, 1, -1, -1, 1, 0, -3};
            long expected__           = 97;

            return verifyCase(casenum__, expected__, new Subcube().getCount(X, Y, Z));
        }
        case 4: {
            int[] X                   = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,8,9,9,10,10,11,11,12,12,13,13,14,14,15,15};
            int[] Y                   = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            int[] Z                   = {0,1,2,3,4,5,6,7,5,4,3,2,1,0,0,0,0,1,2,3,4,5,6,7,5,4,3,2,1,0};
            long expected__           = 511;

            return verifyCase(casenum__, expected__, new Subcube().getCount(X, Y, Z));
        }

        // custom cases

        case 5: {
            int[] X = {-207156, -207782, -207060, -207580, -206739, -207499, -207668, -206610, -207268, -207059, -207048, -207396, -207688, -207156, -206556, -207020, -207290, -207502, -206936, -207188, -207240, -207481, -207521, -207044, -207206, -207806, -207270, -207323, -206965, -207628, -207732, -207308, -207730, -207166, -207248, -207108, -207092, -207039, -207036, -207245, -207127, -207208, -207306, -207705, -207412, -207376, -207452, -207433};
            int[] Y = {-83392, -83610, -83594, -83640, -83851, -82993, -83696, -83626, -82944, -83356, -83272, -82864, -83652, -83264, -83400, -83072, -83680, -83842, -83344, -83592, -83512, -83048, -82769, -83712, -83017, -83468, -83134, -83486, -82693, -83220, -83248, -83021, -83210, -83735, -83818, -83952, -82976, -83488, -83552, -83510, -82993, -83276, -83125, -83230, -83811, -83033, -83324, -84135};
            int[] Z = {-284224, -284631, -284880, -284540, -284767, -284027, -284816, -284354, -284624, -284664, -284923, -284032, -285020, -285024, -284776, -284426, -284232, -283930, -284469, -284976, -284872, -284268, -284571, -284496, -284502, -284404, -284347, -284222, -284449, -284792, -284368, -284896, -284487, -284394, -284556, -285152, -284680, -284284, -284304, -285022, -284947, -284154, -284842, -284566, -284744, -284556, -284911, -284863};
            long expected__           = 1236;

            return verifyCase(casenum__, expected__, new Subcube().getCount(X, Y, Z));
        }
/*      case 6: {
            int[] X                   = ;
            int[] Y                   = ;
            int[] Z                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new Subcube().getCount(X, Y, Z));
        }*/
/*      case 7: {
            int[] X                   = ;
            int[] Y                   = ;
            int[] Z                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new Subcube().getCount(X, Y, Z));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
