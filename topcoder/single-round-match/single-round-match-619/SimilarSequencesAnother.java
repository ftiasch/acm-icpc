import java.math.*;
import java.util.*;

public class SimilarSequencesAnother {
    static int MOD = (int)1e9 + 9;

    public int getCount(int n, int m) {
        if (n == 1) {
            return (int)((long)m * m % MOD);
        }
        Map <Integer, Integer> states = new HashMap <Integer, Integer>();
        if (true) {
            int[] minimum = new int[]{0, 0, 0};
            states.put(pack(minimum, new int[]{0, 0, 1, 1}), m);
            states.put(pack(minimum, new int[]{0, 0, 1, 2}), (int)((long)m * (m - 1) % MOD));
        }
        int[] minimum  = new int[3];
        int[] sequence = new int[5];
        int[] newName  = new int[6];
        for (int _ = 0; _ < n; ++ _) {
//debug(_);
            Map <Integer, Integer> newStates = new HashMap <Integer, Integer>();
            for (Map.Entry <Integer, Integer> iterator : states.entrySet()) {
                unpack(iterator.getKey(), minimum, sequence);
//debug(minimum, sequence, iterator.getValue());
                int maxElement = 0;
                for (int i = 0; i < 4; ++ i) {
                    maxElement = Math.max(maxElement, sequence[i]);
                }
                for (int nextA = maxElement + 1; nextA >= 0; -- nextA) {
                    if ((_ + 2 >= n) ^ (nextA == 0)) {
                        continue;
                    }
                    sequence[4] = nextA;
                    for (int nextB = Math.max(maxElement, nextA) + 1; nextB >= 1; -- nextB) {
                        int value = iterator.getValue();
                        for (int i = Math.max(nextA, nextB) - 1; i >= maxElement; -- i) {
                            value = (int)((long)value * (m - i) % MOD);
                        }
                        if (value == 0) {
                            continue;
                        }
                        Arrays.fill(newName, -1);
                        int nameCount = newName[0] = 0;
                        int[] newMinimum = new int[]{3, minimum[0], minimum[1]};
                        for (int i = 0; i <= 2; ++ i) {
                            for (int j = 0; minimum[i] + j <= 2; ++ j) {
                                if (nextB == sequence[2 - i + minimum[i] + j]) {
                                    newMinimum[i] = Math.min(newMinimum[i], minimum[i] + j);
                                }
                            }
                        }
                        int[] newSequence = new int[4];
                        for (int i = 1; i <= 4; ++ i) {
                            if (newName[sequence[i]] == -1) {
                                newName[sequence[i]] = ++ nameCount;
                            }
                            newSequence[i - 1] = newName[sequence[i]];
                        }
                        int newMask = pack(newMinimum, newSequence);
                        if (!newStates.containsKey(newMask)) {
                            newStates.put(newMask, 0);
                        }
                        newStates.put(newMask, add(newStates.get(newMask), value));
                    }
                }
            }
            states = newStates;
        }
        int result = 0;
        for (Map.Entry <Integer, Integer> iterator : states.entrySet()) {
            unpack(iterator.getKey(), minimum, sequence);
            if (minimum[2] <= 2) {
                result = add(result, iterator.getValue());
            }
        }
        return result;
    }

    void unpack(int mask, int[] minimum, int[] sequence) {
        for (int i = 0; i < 7; ++ i) {
            if (i < 3) {
                minimum[i] = mask & 7;
            } else {
                sequence[i - 3] = mask & 7;
            }
            mask >>= 3;
        }
    }

    int pack(int[] minimum, int[] sequence) {
        int mask = 0;
        for (int i = 6; i >= 0; -- i) {
            mask <<= 3;
            if (i < 3) {
                mask |= minimum[i];
            } else {
                mask |= sequence[i - 3];
            }
        }
        return mask;
    }

    int add(int x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SimilarSequencesAnotherHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SimilarSequencesAnotherHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SimilarSequencesAnotherHarness {
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
            int N                     = 2;
            int bound                 = 10;
            int expected__            = 10000;

            return verifyCase(casenum__, expected__, new SimilarSequencesAnother().getCount(N, bound));
        }
        case 1: {
            int N                     = 3;
            int bound                 = 3;
            int expected__            = 687;

            return verifyCase(casenum__, expected__, new SimilarSequencesAnother().getCount(N, bound));
        }
        case 2: {
            int N                     = 8;
            int bound                 = 1;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new SimilarSequencesAnother().getCount(N, bound));
        }
        case 3: {
            int N                     = 100;
            int bound                 = 123456789;
            int expected__            = 439681851;

            return verifyCase(casenum__, expected__, new SimilarSequencesAnother().getCount(N, bound));
        }
        case 4: {
            int N                     = 1;
            int bound                 = 1000000000;
            int expected__            = 81;

            return verifyCase(casenum__, expected__, new SimilarSequencesAnother().getCount(N, bound));
        }

        // custom cases

/*      case 5: {
            int N                     = ;
            int bound                 = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SimilarSequencesAnother().getCount(N, bound));
        }*/
/*      case 6: {
            int N                     = ;
            int bound                 = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SimilarSequencesAnother().getCount(N, bound));
        }*/
/*      case 7: {
            int N                     = ;
            int bound                 = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SimilarSequencesAnother().getCount(N, bound));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
