import java.math.*;
import java.util.*;

public class XorLife {
    public long countAliveCells(String[] field, int k) {
        int n = field.length;
        int m = field[0].length();
        int[][] state = new int[n][m];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (field[i].charAt(j) == 'o') {
                    state[i][j] = 1;
                }
            }
        }
        memory = new HashMap <State, Long>();
        return count(state, k);
    }

    static int[] DELTA_X = {-1, 0, 0, 0, 1};
    static int[] DELTA_Y = {0, -1, 0, 1, 0};

    Map <State, Long> memory;

    class State {
        State(int[][] state, int step) {
            this.state = state.clone();
            this.step  = step;
        }

        @Override
        public boolean equals(Object other) {
            State o = (State)other;
            if (step != o.step) {
                return false;
            }
            if (state.length != o.state.length) {
                return false;
            }
            for (int i = 0; i < state.length; ++ i) {
                if (!Arrays.equals(state[i], o.state[i])) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = step;
            int n = state.length;
            int m = state[0].length;
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    result <<= 1;
                    result |= state[i][j];
                }
            }
            return result;
        }

        int[][] state;
        int     step;
    }

    long count(int[][] state, int step) {
        State s = new State(state, step);
        if (memory.containsKey(s)) {
            return memory.get(s);
        }
        if ((step & 1) == 1) {
            int n = state.length;
            int m = state[0].length;
            int[][] newState = new int[n + 2][m + 2];
            for (int i = -1; i <= n; ++ i) {
                for (int j = -1; j <= m; ++ j) {
                    int xorSum = 0;
                    for (int k = 0; k < 5; ++ k) {
                        int x = i + DELTA_X[k];
                        int y = j + DELTA_Y[k];
                        if (0 <= x && x < n && 0 <= y && y < m) {
                            xorSum ^= state[x][y];
                        }
                    }
                    newState[i + 1][j + 1] = xorSum;
                }
            }
            state = newState;
        }
        int n = state.length;
        int m = state[0].length;
        long result = 0;
        if (step == 0) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    result += state[i][j];
                }
            }
        } else {
            for (int x = 0; x < 2; ++ x) {
                for (int y = 0; y < 2; ++ y) {
                    int[][] newState = new int[n - x + 1 >> 1][m - y + 1 >> 1];
                    for (int i = x; i < n; i += 2) {
                        for (int j = y; j < m; j += 2) {
                            newState[i >> 1][j >> 1] = state[i][j];
                        }
                    }
                    if (newState.length > 0 && newState[0].length > 0) {
                        result += count(newState, step >> 1);
                    }
                }
            }
        }
        memory.put(s, result);
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            XorLifeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                XorLifeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class XorLifeHarness {
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
            String[] field            = {"oo"
,"o."};
            int K                     = 3;
            long expected__           = 23;

            return verifyCase(casenum__, expected__, new XorLife().countAliveCells(field, K));
        }
        case 1: {
            String[] field            = {".."
,".."};
            int K                     = 23;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new XorLife().countAliveCells(field, K));
        }
        case 2: {
            String[] field            = {"o"};
            int K                     = 1234567;
            long expected__           = 11018125;

            return verifyCase(casenum__, expected__, new XorLife().countAliveCells(field, K));
        }
        case 3: {
            String[] field            = {"o.oo.ooo"
,"o.o.o.oo"
,"ooo.oooo"
,"o.o..o.o"
,"o.o..o.o"
,"o..oooo."
,"..o.o.oo"
,"oo.ooo.o"};
            int K                     = 987654321;
            long expected__           = 447104494375L;

            return verifyCase(casenum__, expected__, new XorLife().countAliveCells(field, K));
        }

        // custom cases

        case 4: {
            String[] field            = new String[50];
            for (int i = 0; i < 50; ++ i) {
                field[i] = "";
                for (int j = 0; j < 50; ++ j) {
                    if ((i * 50 + j) % 3 == 0) {
                        field[i] += "o";
                    } else {
                        field[i] += ".";
                    }
                }
            }
            int K                     = (1 << 29) - 1;
            long expected__           = -1;

            return verifyCase(casenum__, expected__, new XorLife().countAliveCells(field, K));
        }
/*      case 5: {
            String[] field            = ;
            int K                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new XorLife().countAliveCells(field, K));
        }*/
/*      case 6: {
            String[] field            = ;
            int K                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new XorLife().countAliveCells(field, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
