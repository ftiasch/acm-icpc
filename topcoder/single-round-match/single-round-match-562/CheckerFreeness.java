import java.math.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class CheckerFreeness {
    class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        Point subtract(Point other) {
            return new Point(x - other.x, y - other.y);
        }

        long det(Point other) {
            return x * other.y - y * other.x;
        }

        int quadrant() {
            if (y != 0) {
                return y < 0 ? -1 : 1;
            }
            return x > 0 ? -1 : 1;
        }

        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    long getArea(Point o, Point a, Point b) {
        return a.subtract(o).det(b.subtract(o));
    }

    class Pair implements Comparable {
        Point p;
        int id;

        Pair(Point p, int id) {
            this.p = p;
            this.id = id;
        }

        @Override
        public int compareTo(Object o) {
            Point q = ((Pair)o).p;
            if (p.quadrant() != q.quadrant()) {
                return p.quadrant() - q.quadrant();
            }
            long result = p.det(q);
            if (result == 0) {
                return 0;
            }
            return result > 0 ? -1 : 1;
        }
    }

    public String isFree(String[] whiteX, String[] whiteY, String[] blackX, String[] blackY) {
        int n = parse(whiteX).length;
        int m = parse(blackX).length;
        Point[] point = new Point[n + m];
        parse(point, 0, whiteX, whiteY);
        parse(point, n, blackX, blackY);
        int[][] count = new int[n + m][n + m];
        for (int i = 1; i < n + m; ++ i) {
            for (int j = 1; j < n + m; ++ j) {
                if (getArea(point[0], point[i], point[j]) > 0) {
                    for (int k = n; k < n + m; ++ k) {
                        if (getArea(point[0], point[i], point[k]) > 0
                                && getArea(point[i], point[j], point[k]) > 0
                                && getArea(point[j], point[0], point[k]) > 0) {
                            count[i][j] ++;
                        }
                    }
                    count[j][i] = -count[i][j];
                }
            }
        }
        for (int i = n; i < n + m; ++ i) {
            ArrayList <Pair> order = new ArrayList <Pair>();
            for (int j = 0; j < n + m; ++ j) {
                if (i != j) {
                    order.add(new Pair(point[j].subtract(point[i]), j));
                }
            }
            Collections.sort(order);
            for (int _j = 0; _j < order.size(); ++ _j) {
                int j = order.get(_j).id;
                if (j < n) {
                    int black = 0;
                    for (int _k = 1; _k < order.size(); ++ _k) {
                        int k = order.get((_j + _k) % order.size()).id;
                        if (getArea(point[i], point[j], point[k]) <= 0) {
                            break;
                        }
                        if (k < n) {
                            if (count[i][j] + count[j][k] + count[k][i] < black) {
                                return "NO";
                            }
                        } else {
                            black ++;
                        }
                    }
                }
            }
        }
        return "YES";
    }

    void parse(Point[] point, int offset, String[] xs, String[] ys) {
        int[] x = parse(xs);
        int[] y = parse(ys);
        for (int i = 0; i < x.length; ++ i) {
            point[offset + i] = new Point(x[i], y[i]);
        }
    }

    int[] parse(String[] parts) {
        StringBuffer buffer = new StringBuffer();
        for (String part : parts) {
            buffer.append(part);
        }
        StringTokenizer tokenizer = new StringTokenizer(buffer.toString());
        int[] result = new int[tokenizer.countTokens()];
        for (int i = 0; i < result.length; ++ i) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            CheckerFreenessHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                CheckerFreenessHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class CheckerFreenessHarness {
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
            String[] whiteX           = {"1 2"};
            String[] whiteY           = {"2 1"};
            String[] blackX           = {"1 2"};
            String[] blackY           = {"1 2"};
            String expected__         = "NO";

            return verifyCase(casenum__, expected__, new CheckerFreeness().isFree(whiteX, whiteY, blackX, blackY));
        }
        case 1: {
            String[] whiteX           = {"2", "5", "3", " ", "1", "7", "3"};
            String[] whiteY           = {"180 254"};
            String[] blackX           = {"32", "5 1", "42"};
            String[] blackY           = {"462 423"};
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new CheckerFreeness().isFree(whiteX, whiteY, blackX, blackY));
        }
        case 2: {
            String[] whiteX           = {"1 10000000 9999999"};
            String[] whiteY           = {"1 9999999 1"};
            String[] blackX           = {"2 5000000 9999998"};
            String[] blackY           = {"2 5000001 9999999"};
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new CheckerFreeness().isFree(whiteX, whiteY, blackX, blackY));
        }
        case 3: {
            String[] whiteX           = {"1"};
            String[] whiteY           = {"2"};
            String[] blackX           = {"3"};
            String[] blackY           = {"4"};
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new CheckerFreeness().isFree(whiteX, whiteY, blackX, blackY));
        }
        case 4: {
            String[] whiteX           = {"6115 9723 3794 2275 2268 2702 3657 915 7953 2743 7"
,"716 9645 2547 9490 9365 326 6601 5215 6771 7153 72"
,"93 5922 714 2258 4369 9524 302 8417 6620 1143"};
            String[] whiteY           = {"621 1611 7140 503 5345 7202 681 4908 2510 5908 279"
,"6 6286 6873 6682 9197 6710 8517 1913 7784 8533 665"
,"4 446 3561 7241 6168 2025 4739 9501 5340 6446"};
            String[] blackX           = {"6833 131 4151 1776 1959 7210 1903 6107 598 6220 94"
,"24 5374 6718 2919 6068 6644 5070 710 7121 1630 370"
,"3 1051 5739 9294 8798 3371 8107 2130 6608 534"};
            String[] blackY           = {"7496 2412 2801 3473 5810 2714 7853 9714 5470 3558 "
,"8143 2391 8234 7292 9311 1636 8978 1107 2262 9175 "
,"7259 8842 5294 7209 2317 3825 3413 820 3774 5393"};
            String expected__         = "NO";

            return verifyCase(casenum__, expected__, new CheckerFreeness().isFree(whiteX, whiteY, blackX, blackY));
        }
        case 5: {
            String[] whiteX           = {"219211 1996214 1706774 3634920 909831 1774128 8503"
,"52 2233050 2099138 3380396 1128982 3682525 1483700"
," 763080 487867 8203 1791027 463556 1103323 1406861"
," 6374234 760949 4340236 727393 2073832 1289052 103"
,"8147 4448130 151066 412440 1068735 377239 2677933 "
,"1299598 339843 289973 3707319 555280 230418 431719"};
            String[] whiteY           = {"1638083 5698739 3105504 9726902 9745795 5049444 15"
,"80592 3952120 6606668 7460527 7239299 8726128 4913"
,"547 6264100 5701660 8865937 4969114 8014845 327236"
,"1 6389154 9739755 2561669 9412417 5452337 3150506 "
,"5832197 1571976 8779325 3306446 948271 5133709 949"
,"394 6919798 7525636 3568024 6833196 9237472 733313"
,"1 9939064 9720014"};
            String[] blackX           = {"5860334 8007503 7455523 4864927 9573526 2718360 81"
,"12104 6684287 9921506 4840886 5415948 3451454 5320"
,"996 9268757 9261724 8254668 2292750 8035828 233352"
,"1 7676906 5234406 8533320 6562751 4884098 4971897 "
,"5569360 8519168 3100295 9351613 7733878 7579030 32"
,"46775 7297460 8370542 7099759 5782969 2978083 3390"
,"488 7482758 1332401 6094629 9717305 5503121 572842"
,"1 4903563 6331656 2867455 3410007 7751527 7228221 "
,"4111694 5171296 6847697 4601273 7599828 5515929 94"
,"60593 9332762 5389080 4512091 8668538 5711743 5838"
,"534 4825079 8145628 3810005 2964724 5594550 785748"
,"3 6283769"};
            String[] blackY           = {"5911694 8009943 212555 5838688 9896256 607434 5857"
,"663 4616750 1477573 7168026 3090917 4379806 326465"
,"7 4189076 2104028 3279691 94211 8503556 78457 4394"
,"360 3344176 3223317 2624498 4883494 1532240 732937"
,"1 1518674 1353567 892134 5536454 8527392 2603965 6"
,"623264 8830827 2030444 3002706 83058 4475866 20876"
,"25 1790695 4034441 5409379 3571098 4600050 736561 "
,"250475 3733256 3011439 2144994 4523046 3119883 607"
,"582 8361403 6525451 7518329 926803 4884524 8424659"
," 7088689 5762049 9532481 4914186 7314621 4339084 3"
,"741685 3837953 3177252 612550 9688871 5872931"};
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new CheckerFreeness().isFree(whiteX, whiteY, blackX, blackY));
        }

        // custom cases

/*      case 6: {
            String[] whiteX           = ;
            String[] whiteY           = ;
            String[] blackX           = ;
            String[] blackY           = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new CheckerFreeness().isFree(whiteX, whiteY, blackX, blackY));
        }*/
/*      case 7: {
            String[] whiteX           = ;
            String[] whiteY           = ;
            String[] blackX           = ;
            String[] blackY           = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new CheckerFreeness().isFree(whiteX, whiteY, blackX, blackY));
        }*/
/*      case 8: {
            String[] whiteX           = ;
            String[] whiteY           = ;
            String[] blackX           = ;
            String[] blackY           = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new CheckerFreeness().isFree(whiteX, whiteY, blackX, blackY));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
