import java.math.*;
import java.util.*;

class State implements Comparable {
    State(int length) {
        this.length = length;
        this.minIndex = Integer.MAX_VALUE;
        this.maxIndex = Integer.MIN_VALUE;
        this.parent = null;
        go = new State[2];
        states.add(this);
    }

    State extend(State start, int c) {
        State p = this;
        State np = new State(length + 1);
        while (p != null && p.go[c] == null) {
            p.go[c] = np;
            p = p.parent;
        }
        if (p == null) {
            np.parent = start;
        } else {
            State q = p.go[c];
            if (p.length + 1 == q.length) {
                np.parent = q;
            } else {
                State nq = new State(p.length + 1);
                nq.go = q.go.clone();
                nq.parent = q.parent;
                np.parent = q.parent = nq;
                while (p != null && p.go[c] == q) {
                    p.go[c] = nq;
                    p = p.parent;
                }
            }
        }
        return np;
    }

    @Override
    public int compareTo(Object other) {
        State o = (State)other;
        return o.length - length;
    }

    int     length, minIndex, maxIndex;
    State   parent;
    State[] go;

    static  List <State> states;
}

public class StringsNightmareAgain {
    public long UniqueSubstrings(int a, int b, int c, int d, int n) {
        int[] string = new int[n];
        for (int i = 0; i < a; ++ i) {
            b = (int)(((long)b * c + d) % n);
            string[b] = 1;
        }
        State.states = new ArrayList <State>();
        State start = new State(0);
        if (true) {
            State p = start;
            for (int i = n - 1; i >= 0; -- i) {
                p = p.extend(start, string[i]);
            }
            p = start;
            for (int i = n - 1; i >= 0; -- i) {
                p = p.go[string[i]];
                assert p.minIndex == Integer.MAX_VALUE && p.maxIndex == Integer.MIN_VALUE;
                p.minIndex = p.maxIndex = i;
            }
        }
        Collections.sort(State.states);
        long result = 0;
        for (State p : State.states) {
            if (p == start) {
                break;
            }
            result += Math.max(Math.min(p.maxIndex - p.minIndex, p.length) - p.parent.length, 0);
            p.parent.minIndex = Math.min(p.parent.minIndex, p.minIndex);
            p.parent.maxIndex = Math.max(p.parent.maxIndex, p.maxIndex);
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            StringsNightmareAgainHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                StringsNightmareAgainHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class StringsNightmareAgainHarness {
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
            int a                     = 0;
            int b                     = 0;
            int c                     = 0;
            int d                     = 0;
            int n                     = 4;
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new StringsNightmareAgain().UniqueSubstrings(a, b, c, d, n));
        }
        case 1: {
            int a                     = 2;
            int b                     = 3;
            int c                     = 1;
            int d                     = 1;
            int n                     = 6;
            long expected__           = 3;

            return verifyCase(casenum__, expected__, new StringsNightmareAgain().UniqueSubstrings(a, b, c, d, n));
        }
        case 2: {
            int a                     = 4;
            int b                     = 3;
            int c                     = 1;
            int d                     = 1;
            int n                     = 6;
            long expected__           = 3;

            return verifyCase(casenum__, expected__, new StringsNightmareAgain().UniqueSubstrings(a, b, c, d, n));
        }
        case 3: {
            int a                     = 4;
            int b                     = 3;
            int c                     = 3;
            int d                     = 3;
            int n                     = 10;
            long expected__           = 5;

            return verifyCase(casenum__, expected__, new StringsNightmareAgain().UniqueSubstrings(a, b, c, d, n));
        }
        case 4: {
            int a                     = 5;
            int b                     = 3;
            int c                     = 2;
            int d                     = 3;
            int n                     = 11;
            long expected__           = 9;

            return verifyCase(casenum__, expected__, new StringsNightmareAgain().UniqueSubstrings(a, b, c, d, n));
        }
        case 5: {
            int a                     = 10;
            int b                     = 1000000;
            int c                     = 1000000;
            int d                     = 1;
            int n                     = 51;
            long expected__           = 63;

            return verifyCase(casenum__, expected__, new StringsNightmareAgain().UniqueSubstrings(a, b, c, d, n));
        }

        // custom cases

/*      case 6: {
            int a                     = ;
            int b                     = ;
            int c                     = ;
            int d                     = ;
            int n                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new StringsNightmareAgain().UniqueSubstrings(a, b, c, d, n));
        }*/
/*      case 7: {
            int a                     = ;
            int b                     = ;
            int c                     = ;
            int d                     = ;
            int n                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new StringsNightmareAgain().UniqueSubstrings(a, b, c, d, n));
        }*/
/*      case 8: {
            int a                     = ;
            int b                     = ;
            int c                     = ;
            int d                     = ;
            int n                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new StringsNightmareAgain().UniqueSubstrings(a, b, c, d, n));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
