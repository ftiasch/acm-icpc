import java.math.*;
import java.util.*;

public class EllysLamps {
    public int getMin(String lampsString) {
        int n = lampsString.length();
        int[] lamps = new int[n + 1];
        for (int i = 0; i < n; ++ i) {
            lamps[i] = lampsString.charAt(i) == 'Y' ? 1 : 0;
        }
        Set <State> states = new HashSet <State>();
        if (true) {
            State state = new State();
            state.values[0 | lamps[0] << 1] = 0;
            states.add(state);
        }
        for (int i = 0; i < n; ++ i) {
            Set <State> newStates = new HashSet <State>();
            for (State state : states) {
                for (int type = 0; type < 1 << 3; ++ type) {
                    if ((i - 1 >= 0 || (type >> 0 & 1) == 0) && ((type >> 1 & 1) == 1) && (i + 1 < n || (type >> 2 & 1) == 0)) {
                        State newState = new State();
                        for (int mask = 0; mask < 1 << 2; ++ mask) {
                            int newMask = mask | lamps[i + 1] << 2;
                            if (state.values[mask] < Integer.MAX_VALUE) {
                                newState.update(newMask >> 1, state.values[mask] + (newMask & 1));
                                newMask ^= type;
                                newState.update(newMask >> 1, state.values[mask] + (newMask & 1));
                            }
                        }
                        newStates.add(newState);
                    }
                }
            }
            states = newStates;
        }
        int result = 0;
        for (State state : states) {
            result = Math.max(result, state.value());
        }
        return result;
    }

    class State {
        State() {
            values = new int[1 << 2];
            Arrays.fill(values, Integer.MAX_VALUE);
        }

        @Override
        public boolean equals(Object other) {
            State o = (State)other;
            for (int i = 0; i < 1 << 2; ++ i) {
                if (values[i] != o.values[i]) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = 0;
            for (int i = 0; i < 1 << 2; ++ i) {
                result <<= 6;
                result |= values[i];
            }
            return result;
        }

        void update(int k, int v) {
            if (v < values[k]) {
                values[k] = v;
            }
        }

        int value() {
            int result = Integer.MAX_VALUE;
            for (int mask = 0; mask < 1 << 2; ++ mask) {
                if (values[mask] < Integer.MAX_VALUE) {
                    result = Math.min(result, values[mask] + Integer.bitCount(mask));
                }
            }
            return result;
        }

        int[] values;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysLampsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysLampsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysLampsHarness {
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
            String lamps              = "YNNYN";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new EllysLamps().getMin(lamps));
        }
        case 1: {
            String lamps              = "NNN";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new EllysLamps().getMin(lamps));
        }
        case 2: {
            String lamps              = "YY";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new EllysLamps().getMin(lamps));
        }
        case 3: {
            String lamps              = "YNYYYNNNY";
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new EllysLamps().getMin(lamps));
        }
        case 4: {
            String lamps              = "YNYYYYNYNNYYNNNNNNYNYNYNYNNYNYYYNY";
            int expected__            = 13;

            return verifyCase(casenum__, expected__, new EllysLamps().getMin(lamps));
        }

        // custom cases

/*      case 5: {
            String lamps              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysLamps().getMin(lamps));
        }*/
/*      case 6: {
            String lamps              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysLamps().getMin(lamps));
        }*/
/*      case 7: {
            String lamps              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysLamps().getMin(lamps));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
