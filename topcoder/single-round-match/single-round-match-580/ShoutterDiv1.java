import java.math.*;
import java.util.*;

public class ShoutterDiv1 {
    class Interval implements Comparable <Interval> {
        int a, b;

        Interval(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int compareTo(Interval o) {
            if (a != o.a) {
                return a - o.a;
            }
            return b - o.b;
        }

        public String toString() {
            return String.format("[%d, %d]", a, b);
        }
    }

    String concate(String[] strings) {
        StringBuffer buffer = new StringBuffer("");
        for (String string : strings) {
            buffer.append(string);
        }
        return buffer.toString();
    }

    public int count(String[] s1000, String[] s100, String[] s10, String[] s1, String[] t1000, String[] t100, String[] t10, String[] t1) {
        String S1000 = concate(s1000);
        String S100 = concate(s100);
        String S10 = concate(s10);
        String S1 = concate(s1);
        String T1000 = concate(t1000);
        String T100 = concate(t100);
        String T10 = concate(t10);
        String T1 = concate(t1);
        int n = S1000.length();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; ++ i) {
            intervals[i] = new Interval(Integer.parseInt(S1000.substring(i, i + 1) + S100.charAt(i) + S10.charAt(i) + S1.charAt(i)),
                                        Integer.parseInt(T1000.substring(i, i + 1) + T100.charAt(i) + T10.charAt(i) + T1.charAt(i)));
        }
        Arrays.sort(intervals);
        int leftmost = Integer.MAX_VALUE;
        int rightmost = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++ i) {
            leftmost = Math.min(leftmost, intervals[i].b);
            rightmost = Math.max(rightmost, intervals[i].a);
        }
        int answer = 0;
        for (int i = 0; i < n; ++ i) {
            int total = 0;
            int now = leftmost;
            int j = 0;
            while (true) {
                if (intervals[i].a <= now) {
                    now = Math.max(now, intervals[i].b);
                }
                if (now >= rightmost) {
                    break;
                }
                int best = now;
                while (j < n && intervals[j].a <= now) {
                    best = Math.max(best, intervals[j ++].b);
                }
                if (best <= now) {
                    return -1;
                }
                total ++;
                now = best;
            }
            answer += total;
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ShoutterDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ShoutterDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ShoutterDiv1Harness {
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
            String[] s1000            = {"22", "2"};
            String[] s100             = {"00", "0"};
            String[] s10              = {"11", "1"};
            String[] s1               = {"21", "4"};
            String[] t1000            = {"22", "2"};
            String[] t100             = {"00", "0"};
            String[] t10              = {"11", "1"};
            String[] t1               = {"43", "6"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new ShoutterDiv1().count(s1000, s100, s10, s1, t1000, t100, t10, t1));
        }
        case 1: {
            String[] s1000            = {"00"};
            String[] s100             = {"00"};
            String[] s10              = {"00"};
            String[] s1               = {"13"};
            String[] t1000            = {"00"};
            String[] t100             = {"00"};
            String[] t10              = {"00"};
            String[] t1               = {"24"};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new ShoutterDiv1().count(s1000, s100, s10, s1, t1000, t100, t10, t1));
        }
        case 2: {
            String[] s1000            = {"0000"};
            String[] s100             = {"0000"};
            String[] s10              = {"0000"};
            String[] s1               = {"1234"};
            String[] t1000            = {"0000"};
            String[] t100             = {"0000"};
            String[] t10              = {"0000"};
            String[] t1               = {"2345"};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new ShoutterDiv1().count(s1000, s100, s10, s1, t1000, t100, t10, t1));
        }
        case 3: {
            String[] s1000            = {"0000000000"};
            String[] s100             = {"0000000000"};
            String[] s10              = {"0000000000"};
            String[] s1               = {"7626463146"};
            String[] t1000            = {"0000000000"};
            String[] t100             = {"0000000000"};
            String[] t10              = {"0000000000"};
            String[] t1               = {"9927686479"};
            int expected__            = 18;

            return verifyCase(casenum__, expected__, new ShoutterDiv1().count(s1000, s100, s10, s1, t1000, t100, t10, t1));
        }
        case 4: {
            String[] s1000            = {"00000000000000000000000000000000000000000000000000"};
            String[] s100             = {"00000000000000000000000000000000000000000000000000"};
            String[] s10              = {"50353624751857130208544645495168271486083954769538"};
            String[] s1               = {"85748487990028258641117783760944852941545064635928"};
            String[] t1000            = {"00000000000000000000000000000000000000000000000000"};
            String[] t100             = {"00000000000000000000000000000000000000000000000000"};
            String[] t10              = {"61465744851859252308555855596388482696094965779649"};
            String[] t1               = {"37620749792666153778227385275518278477865684777411"};
            int expected__            = 333;

            return verifyCase(casenum__, expected__, new ShoutterDiv1().count(s1000, s100, s10, s1, t1000, t100, t10, t1));
        }

        // custom cases

/*      case 5: {
            String[] s1000            = ;
            String[] s100             = ;
            String[] s10              = ;
            String[] s1               = ;
            String[] t1000            = ;
            String[] t100             = ;
            String[] t10              = ;
            String[] t1               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ShoutterDiv1().count(s1000, s100, s10, s1, t1000, t100, t10, t1));
        }*/
/*      case 6: {
            String[] s1000            = ;
            String[] s100             = ;
            String[] s10              = ;
            String[] s1               = ;
            String[] t1000            = ;
            String[] t100             = ;
            String[] t10              = ;
            String[] t1               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ShoutterDiv1().count(s1000, s100, s10, s1, t1000, t100, t10, t1));
        }*/
/*      case 7: {
            String[] s1000            = ;
            String[] s100             = ;
            String[] s10              = ;
            String[] s1               = ;
            String[] t1000            = ;
            String[] t100             = ;
            String[] t10              = ;
            String[] t1               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ShoutterDiv1().count(s1000, s100, s10, s1, t1000, t100, t10, t1));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
