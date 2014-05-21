import java.math.*;
import java.util.*;

public class AntsMeet {
    final static String DIRECTIONS = "NESW";
    final static int DELTA[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int countAnts(int[] x, int[] y, String string) {
        int n = x.length;
        int d[] = new int[n];
        for (int i = 0; i < n; ++ i) {
            x[i] *= 2;
            y[i] *= 2;
            d[i] = DIRECTIONS.indexOf(string.charAt(i));
        }
        boolean exists[] = new boolean[n];
        Arrays.fill(exists, true);
        for (int t = 0; t < 4444; ++ t) {
            int xx[] = new int[n];
            int yy[] = new int[n];
            for (int i = 0; i < n; ++ i) {
                xx[i] = x[i] + DELTA[d[i]][0] * t;
                yy[i] = y[i] + DELTA[d[i]][1] * t;
            }
            ArrayList <Integer> events = new ArrayList <Integer>();
            for (int i = 0; i < n; ++ i) {
                if (exists[i]) {
                    boolean found = false;
                    for (int j = 0; j < n; ++ j) {
                        if (i != j && exists[j] && xx[i] == xx[j] && yy[i] == yy[j]) {
                            found = true;
                        }
                    }
                    if (found) {
                        events.add(i);
                    }
                }
            }
            for (int e: events) {
                exists[e] = false;
            }
        }
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            if (exists[i]) {
                result ++;
            }
        }
        return result;
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            AntsMeetHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                AntsMeetHarness.run_test(Integer.valueOf(args[i]));
        }
    }
    // END CUT HERE
}

// BEGIN CUT HERE
class AntsMeetHarness {
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
                        int[] x                   = {0,10,20,30};
                        int[] y                   = {0,10,20,30};
                        String direction          = "NWNE";
                        int expected__            = 2;

                        return verifyCase(casenum__, expected__, new AntsMeet().countAnts(x, y, direction));
            }
            case 1: {
                        int[] x                   = {-10,0,0,10};
                        int[] y                   = {0,-10,10,0};
                        String direction          = "NEWS";
                        int expected__            = 0;

                        return verifyCase(casenum__, expected__, new AntsMeet().countAnts(x, y, direction));
            }
            case 2: {
                        int[] x                   = {-1,-1,-1,0,0,0,1,1,1};
                        int[] y                   = {-1,0,1,-1,0,1,-1,0,1};
                        String direction          = "ESEWNNEWW";
                        int expected__            = 4;

                        return verifyCase(casenum__, expected__, new AntsMeet().countAnts(x, y, direction));
            }
            case 3: {
                        int[] x                   = {4,7,6,2,6,5,7,7,8,4,7,8,8,8,5,4,8,9,1,5,9,3,4,0,0,1,0,7,2,6,9,6,3,0,5,5,1,2,0,4,9,7,7,1,8,1,9,2,7,3};
                        int[] y                   = {2,3,0,6,8,4,9,0,5,0,2,4,3,8,1,5,0,7,3,7,0,9,8,1,9,4,7,8,1,1,6,6,6,2,8,5,1,9,0,1,1,1,7,0,2,5,4,7,5,3};
                        String direction          = "SSNWSWSENSWSESWEWSWSENWNNNESWSWSWWSSWEEWWNWWWNWENN" ;
                        int expected__            = 25;

                        return verifyCase(casenum__, expected__, new AntsMeet().countAnts(x, y, direction));
            }
            case 4: {
                        int[] x                   = {478,-664,759,434,-405,513,565,-396,311,-174,56,993,251,-341,993,-112,242,129,383,513,-78,-341,-148,129,423 ,493,434,-405,478,-148,929,251,56,242,929,-78,423,-664,802,251,759,383,-112,-591,-591,-248,660,660,735,493};
                        int[] y                   = {-186,98,948,795,289,-678,948,-170,-195,290,-354,-424,289,-157,-166,150,706,-678,684,-294,-234,36,36,-294,-216 ,-234,427,945,265,-157,265,715,275,715,-186,337,798,-170,427,706,754,961,286,-216,798,286,961,684,-424,337};
                        String direction          = "WNSNNSSWWWEENWESNSWSWSEWWEWEWWWNWESNSSNNSNNWWWNESE";
                        int expected__            = 44;

                        return verifyCase(casenum__, expected__, new AntsMeet().countAnts(x, y, direction));
            }

            // custom cases

            /*      case 5: {
                    int[] x                   = ;
                    int[] y                   = ;
                    String direction          = ;
                    int expected__            = ;

                    return verifyCase(casenum__, expected__, new AntsMeet().countAnts(x, y, direction));
                    }*/
            /*      case 6: {
                    int[] x                   = ;
                    int[] y                   = ;
                    String direction          = ;
                    int expected__            = ;

                    return verifyCase(casenum__, expected__, new AntsMeet().countAnts(x, y, direction));
                    }*/
            /*      case 7: {
                    int[] x                   = ;
                    int[] y                   = ;
                    String direction          = ;
                    int expected__            = ;

                    return verifyCase(casenum__, expected__, new AntsMeet().countAnts(x, y, direction));
                    }*/
            default:
                    return -1;
        }
    }
}

// END CUT HERE
