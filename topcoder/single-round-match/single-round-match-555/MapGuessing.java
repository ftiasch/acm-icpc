import java.math.*;
import java.util.*;

public class MapGuessing {
    long simulate(long goal, int n, String cmd, int p) {
        long result = 0;
        long mask = 0;
        long current = 0;
        for (int i = 0; i < cmd.length(); ++ i) {
            char c = cmd.charAt(i);
            if (c == '<') {
                p --;
            } else if (c == '>') {
                p ++;
            } else {
                mask |= 1L << p;
                if (c == '0') {
                    current &= ~(1L << p);
                } else if (c == '1') {
                    current |= 1L << p;
                }
            }
            if (p < 0 || p >= n) {
                return -1;
            }
            if ((goal & mask) == (current & mask)) {
                result |= mask;
            }
        }
        return result;
    }

    public long countPatterns(String bits, String[] cmds) {
        int n = bits.length();
        long goal = 0;
        for (int i = 0; i < n; ++ i) {
            goal |= (long)(bits.charAt(i) - '0') << i;
        }
        String cmd = concat(cmds);
        ArrayList <Long> covers = new ArrayList <Long>();
        for (int i = 0; i < n; ++ i) {
            long ret = simulate(goal, n, cmd, i);
            if (ret != -1) {
                covers.add(ret);
            }
        }
        if (covers.size() <= 20) {
            long result = 0;
            for (int mask = 1; mask < 1 << covers.size(); ++ mask) {
                long itsc = ~0;
                for (int i = 0; i < covers.size(); ++ i) {
                    if ((mask >> i & 1) == 1) {
                        itsc &= (long)covers.get(i);
                    }
                }
                long delta = 1L << Long.bitCount(itsc);
                if (Integer.bitCount(mask) % 2 == 1) {
                    result += delta;
                } else {
                    result -= delta;
                }
            }
            return result;
        } else {
            Set <Long> set = new HashSet <Long>();
            for (long cover : covers) {
                long mask = cover;
                while (true) {
                    set.add(mask);
                    if (mask == 0) {
                        break;
                    }
                    mask = (mask - 1) & cover;
                }
            }
            return set.size();
        }
    }

    String concat(String[] parts) {
        StringBuffer buffer = new StringBuffer();
        for (String part : parts) {
            buffer.append(part);
        }
        return buffer.toString();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			MapGuessingHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				MapGuessingHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class MapGuessingHarness {
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
			String goal               = "000";
			String[] code             = {"0"};
			long expected__           = 4;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}
		case 1: {
			String goal               = "001";
			String[] code             = {"0>1"};
			long expected__           = 5;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}
		case 2: {
			String goal               = "000";
			String[] code             = {"1>1>1"};
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}
		case 3: {
			String goal               = "11001";
			String[] code             = {">><<<<><<"};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}
		case 4: {
			String goal               = "1000101011";
			String[] code             = {"1<<0>>0>1"};
			long expected__           = 22;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}
		case 5: {
			String goal               = "00000010000000000000000000000000";
			String[] code             = {"><>>0<0<>>1>0><>", "<<0>>0<>><0>0>>><><", ">>>0<>", ">0><>>>>0<<><>>0>>>0<0>>0>"};
			long expected__           = 13601;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}
		case 6: {
			String goal               = "11100011010111111010100100110001101";
			String[] code             = {"11111111111111111111"
,"1<><><><><><><><><>1"
,"1<>000>000><0<><0<>1"
,"1<0<><>0><0<00>00<>1"
,"1<>00<>000><0<0<0<>1"
,"1<><>0>0><0<0<><0<>1"
,"1<000<>0><0<0<><0<>1"
,"1<><><><><><><><><>1"
,"1<>000><000<>000><>1"
,"1<>0><><0<><>0><><>1"
,"1<>000><000<>000><>1"
,"1<><>0><><0<><>0><>1"
,"1<>000><000<>000><>1"
,"1<><><><><><><><><>1"
,"11111111111111111111"};
			long expected__           = 90;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}

		// custom cases

/*      case 7: {
			String goal               = ;
			String[] code             = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}*/
/*      case 8: {
			String goal               = ;
			String[] code             = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}*/
/*      case 9: {
			String goal               = ;
			String[] code             = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new MapGuessing().countPatterns(goal, code));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
