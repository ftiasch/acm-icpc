import java.math.*;
import java.util.*;

public class UnknownTree {
    static int MOD = (int)1e9 + 9;

    int n;
    int[][] distances, candidates;

    int solve(int[] relativeDistances) {
        int[] radius = new int[3];
        for (int i = 0; i < 3; ++ i) {
            radius[i] = relativeDistances[(i + 1) % 3] + relativeDistances[(i + 2) % 3] - relativeDistances[i];
            if (radius[i] < 0 || radius[i] % 2 != 0) {
                return 0;
            }
            radius[i] /= 2;
        }
        for (int i = 0; i < 3; ++ i) {
            for (int j = 0; j < 3; ++ j) {
                if (i != j) {
                    distances[i][n + j] = relativeDistances[3 - i - j];
                }
            }
        }
        boolean found = false;
        Map <Long, ArrayList <Integer> > map = new HashMap <Long, ArrayList <Integer> >();
        for (int v = 0; v < n + 3; ++ v) {
            int depth = Integer.MAX_VALUE;
            for (int i = 0; i < 3; ++ i) {
                depth = Math.min(depth, distances[(i + 1) % 3][v] + distances[(i + 2) % 3][v] - relativeDistances[i]);
            }
            if (depth < 0 || depth % 2 != 0) {
                return 0;
            }
            depth /= 2;
            int positive = 0;
            int negative = 0;
            int[] delta = new int[3];
            for (int i = 0; i < 3; ++ i) {
                delta[i] = distances[i][v] - depth - radius[i];
                if (delta[i] > 0) {
                    positive ++;
                }
                if (delta[i] < 0) {
                    negative ++;
                }
            }
            if ((positive == 0 && negative == 0) || (positive == 2 && negative == 1)) {
                if (positive == 0 && negative == 0) {
                    found = true;
                }
                for (int i = 0; i < 3; ++ i) {
                    if (delta[i] < 0) {
                        for (int j = 0; j < 3; ++ j) {
                            if (i != j && delta[i] + delta[j] != 0) {
                                return 0;
                            }
                        }
                    }
                }
                long key = 123456789L * (distances[0][v] - depth) + (distances[1][v] - depth);
                if (map.get(key) == null) {
                    map.put(key, new ArrayList <Integer>());
                }
                map.get(key).add(depth);
            } else {
                return 0;
            }
        }
        if (!found) {
            return 0;
        }
        int result = 1;
        for (Map.Entry <Long, ArrayList <Integer> > entry : map.entrySet()) {
            ArrayList <Integer> depths = entry.getValue();
            Collections.sort(depths);
            if (depths.get(0) != 0) {
                return 0;
            }
            for (int i = 1, j = 0; i < depths.size(); ++ i) {
                while (depths.get(j).compareTo(depths.get(i)) < 0) {
                    j ++;
                }
                result = (int)((long)result * j % MOD);
            }
        }
        return result;
    }

    int search(int i, int[] distances) {
        if (i == 3) {
            return solve(distances);
        }
        int result = 0;
        for (int j = 0; j < 3; ++ j) {
            boolean valid = candidates[i][j] > 0;
            for (int k = 0; k < j; ++ k) {
                valid &= candidates[i][j] != candidates[i][k];
            }
            if (valid) {
                distances[i] = candidates[i][j];
                result += search(i + 1, distances);
                if (result >= MOD) {
                    result -= MOD;
                }
            }
        }
        return result;
    }

    public int getCount(int[] distancesA, int[] distancesB, int[] distancesC) {
        n = distancesA.length;
        distances = new int[3][];
        distances[0] = Arrays.copyOf(distancesA, n + 3);
        distances[1] = Arrays.copyOf(distancesB, n + 3);
        distances[2] = Arrays.copyOf(distancesC, n + 3);
        candidates = new int[3][];
        for (int i = 0; i < 3; ++ i) {
            candidates[i] = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE};
            int j = (i + 1) % 3;
            int k = (i + 2) % 3;
            for (int v = 0; v < n; ++ v) {
                candidates[i][0] = Math.min(candidates[i][0], distances[j][v] + distances[k][v]);
                candidates[i][1] = Math.max(candidates[i][1], Math.abs(distances[j][v] - distances[k][v]));
                candidates[i][2] = Math.min(candidates[i][2], distances[j][v] + distances[k][v] - distances[i][v] * 2);
            }
        }
        return search(0, new int[3]);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            UnknownTreeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                UnknownTreeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class UnknownTreeHarness {
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
            int[] distancesA          = {1};
            int[] distancesB          = {2};
            int[] distancesC          = {3};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }
        case 1: {
            int[] distancesA          = {1, 2};
            int[] distancesB          = {1, 2};
            int[] distancesC          = {1, 2};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }
        case 2: {
            int[] distancesA          = {5, 4};
            int[] distancesB          = {3, 2};
            int[] distancesC          = {2, 1};
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }
        case 3: {
            int[] distancesA          = {2, 4, 2};
            int[] distancesB          = {1, 3, 3};
            int[] distancesC          = {4, 6, 4};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }
        case 4: {
            int[] distancesA          = {4, 6, 1, 5, 3, 2, 5};
            int[] distancesB          = {4, 2, 3, 1, 3, 2, 1};
            int[] distancesC          = {5, 7, 2, 6, 4, 3, 6};
            int expected__            = 12;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }
        case 5: {
            int[] distancesA          = {6, 4, 5, 6, 8, 1, 5, 6, 4, 2};
            int[] distancesB          = {4, 2, 3, 4, 6, 1, 3, 4, 2, 2};
            int[] distancesC          = {6, 4, 5, 6, 8, 3, 5, 6, 4, 4};
            int expected__            = 9000;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }
        case 6: {
            int[] distancesA          = {8, 5, 6, 8, 6, 5, 6, 10, 8, 5, 10, 8, 7, 9, 7, 1, 11, 5, 9, 6, 6, 1, 6, 9, 8, 4, 12, 7, 5, 7, 6, 8, 12, 8, 6, 6, 5, 8, 5, 3, 3, 4, 8, 6, 6, 8, 8, 9, 7, 5};
            int[] distancesB          = {9, 6, 7, 9, 7, 6, 7, 11, 9, 6, 11, 9, 8, 10, 8, 2, 12, 6, 10, 7, 7, 4, 7, 10, 9, 5, 13, 8, 6, 8, 7, 9, 13, 9, 7, 7, 6, 9, 6, 4, 4, 5, 9, 7, 7, 9, 9, 10, 8, 6};
            int[] distancesC          = {8, 9, 6, 8, 2, 5, 6, 10, 8, 5, 10, 8, 7, 9, 1, 5, 11, 5, 9, 6, 6, 7, 6, 9, 8, 4, 12, 7, 5, 7, 6, 8, 12, 8, 6, 6, 5, 8, 1, 7, 3, 4, 8, 6, 6, 8, 8, 3, 7, 5};
            int expected__            = 770724166;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }
        case 7: {
            int[] distancesA          = {33030780, 30296205, 16842859, 28857842, 37928939, 27190807, 48689043, 33328845, 24254103, 3962046, 31043603, 25699520, 11297547, 27045586, 31603483, 23207518, 44089781, 48470539, 52366295, 39786470, 45623279, 21593844, 38639305, 27260993, 43899542, 36162768, 21640232, 43580853, 33826577, 30501815, 51470990, 2157904, 27823597, 9550575, 39234641, 24163007, 34155133, 42504989, 35821444, 36054200, 29026389, 29716374, 41764139, 19392309, 44258194, 19987908, 56722905, 46771885, 32668277, 40665175};
            int[] distancesB          = {16191697, 13457122, 3776, 12018759, 21089856, 10351724, 31849960, 16489762, 7415020, 12877037, 14204520, 8860437, 9035480, 10206503, 14764400, 6368435, 27250698, 31631456, 35527212, 22947387, 28784196, 4754761, 21800222, 10421910, 27060459, 19323685, 4801149, 26741770, 16987494, 13662732, 34631907, 18996987, 10984514, 7288508, 22395558, 7323924, 17316050, 25665906, 18982361, 19215117, 12187306, 12877291, 24925056, 2553226, 27419111, 3148825, 39883822, 29932802, 15829194, 23826092};
            int[] distancesC          = {19337227, 16602652, 3149306, 15164289, 24235386, 13497254, 34995490, 19635292, 10560550, 16030119, 17350050, 12005967, 12188562, 13352033, 17909930, 3215353, 30396228, 34776986, 38672742, 26092917, 31929726, 7907843, 24945752, 13567440, 30205989, 22469215, 7946679, 29887300, 20133024, 16808262, 37777437, 22150069, 14130044, 10441590, 25541088, 10469454, 20461580, 28811436, 22127891, 22360647, 15332836, 16022821, 28070586, 5706308, 30564641, 6294355, 43029352, 33078332, 18974724, 26971622};
            int expected__            = 101733071;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }

        // custom cases

/*      case 8: {
            int[] distancesA          = ;
            int[] distancesB          = ;
            int[] distancesC          = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }*/
/*      case 9: {
            int[] distancesA          = ;
            int[] distancesB          = ;
            int[] distancesC          = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }*/
/*      case 10: {
            int[] distancesA          = ;
            int[] distancesB          = ;
            int[] distancesC          = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new UnknownTree().getCount(distancesA, distancesB, distancesC));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
