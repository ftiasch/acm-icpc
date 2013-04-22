import java.math.*;
import java.util.*;

public class EllysLights {
    public int getMinimum(String initial, String[] switches) {
        int n = switches.length;
        int m = initial.length();
        int[] config = new int[n];
        Arrays.fill(config, -1);
        int answer = 0;
        for (int j = 0; j < m; ++ j) {
            if (initial.charAt(j) == 'Y') {
                boolean found = false;
                for (int i = 0; i < n; ++ i) {
                    if (switches[i].charAt(j) == 'Y') {
                        found = true;
                    }
                }
                if (!found) {
                    return -1;
                }
            }
        }
        for (int i = 0; i < n; ++ i) {
            if (config[i] == -1) {
                int minimum = Integer.MAX_VALUE;
                for (config[i] = 0; config[i] < 2; ++ config[i]) {
                    ArrayList <Integer> queue = new ArrayList <Integer>();
                    queue.add(i);
                    for (int head = 0; head < queue.size(); ++ head) {
                        int u = queue.get(head);
                        for (int v = 0; v < n; ++ v) {
                            if (u != v) {
                                int type = -1;
                                for (int k = 0; k < m; ++ k) {
                                    if (switches[u].charAt(k) == 'Y' && switches[v].charAt(k) == 'Y') {
                                        type = initial.charAt(k) == 'Y' ? 1 : 0;
                                    }
                                }
                                if (type != -1 && config[v] == -1) {
                                    config[v] = config[u] ^ type;
                                    queue.add(v);
                                }
                            }
                        }
                    }
                    int[] status = new int[m];
                    for (int j = 0; j < m; ++ j) {
                        if (initial.charAt(j) == 'Y') {
                            boolean found = false;
                            for (int u : queue) {
                                if (switches[u].charAt(j) == 'Y') {
                                    found = true;
                                }
                            }
                            if (found) {
                                status[j] = 1;
                            }
                        }
                    }
                    int count = 0;
                    for (int u : queue) {
                        if (config[u] == 1) {
                            count ++;
                            for (int j = 0; j < m; ++ j) {
                                if (switches[u].charAt(j) == 'Y') {
                                    status[j] ^= 1;
                                }
                            }
                        }
                    }
                    for (int j = 0; j < m; ++ j) {
                        if (status[j] == 1) {
                            count = Integer.MAX_VALUE;
                        }
                    }
                    minimum = Math.min(minimum, count);
                    if (config[i] == 0) {
                        for (int u : queue) {
                            config[u] = -1;
                        }
                        config[i] = 0;
                    }
                }
                if (minimum == Integer.MAX_VALUE) {
                    return -1;
                }
                answer += minimum;
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			EllysLightsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				EllysLightsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class EllysLightsHarness {
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
			String initial            = "YNYNNN";
			String[] switches         = {"YNNYNY", "NYYYNN", "YNYNYN", "NNNNYN", "NYNNNY"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new EllysLights().getMinimum(initial, switches));
		}
		case 1: {
			String initial            = "YNYNYN";
			String[] switches         = {"NNNNNN", "YYYYYY", "NYNNNN", "NNNYNN", "NNNNNY"};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new EllysLights().getMinimum(initial, switches));
		}
		case 2: {
			String initial            = "YYN";
			String[] switches         = {"YNY", "NYN"};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new EllysLights().getMinimum(initial, switches));
		}
		case 3: {
			String initial            = "NNYNYNYYYNNYYYYN";
			String[] switches         = {"NYNYNYNYNYNYNYNY",
 "YNYNYNYNYNYNYNYN",
 "NNNNNNNNNNNNNNNN",
 "YNNNNNNNNNNNNNNN",
 "NYNNNNNNNNNNNNNN",
 "NNYNNNNNNNNNNNNN",
 "NNNYNNNNNNNNNNNN",
 "NNNNYNNNNNNNNNNN",
 "NNNNNYNNNNNNNNNN",
 "NNNNNNYNNNNNNNNN",
 "NNNNNNNYNNNNNNNN",
 "NNNNNNNNYNNNNNNN",
 "NNNNNNNNNYNNNNNN",
 "NNNNNNNNNNYNNNNN",
 "NNNNNNNNNNNYNNNN",
 "NNNNNNNNNNNNYNNN",
 "NNNNNNNNNNNNNYNN",
 "NNNNNNNNNNNNNNYN",
 "NNNNNNNNNNNNNNNY"};
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new EllysLights().getMinimum(initial, switches));
		}
		case 4: {
			String initial            = "NYNYNYYYNNYYYNNYNNYYYYYNNYNYYYY";
			String[] switches         = {"NNNNNNNNNNNNNNNNNNYNNNNNNNNNNNN",
 "NNNNNNNNYNNNYNNNNYYNYNNNNYNNNNN",
 "NNNNNNNNNYNNNNNNNNNNNNYNNNNNNNN",
 "NNNNNYNNNNNNNNNNNNNNNNNNNNNNNNN",
 "NYNNNNNNNNNNNNYNNNNNNNNNNNNNNNN",
 "NNNNNNNNNNNNNYYNNNNNNNNNNNNNNNY",
 "NNNNNNYNNNNNNNNNNNNYNNNNNYNNNNN",
 "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
 "YNNNNNNNNNNNNNNNNNNYNNNNNNNNNNN",
 "NNNYNNNNNNNNNNNNNNNNNNNYYNNNNNN",
 "NYNNNNNNNNNNYNNNNNNNNNNNNNNNYNN",
 "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
 "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
 "NNNNNYNNNNNNNNNNNNNNNNNNNNNNNNY",
 "NNNNNNNNNNYNNNNNNNNNYYYNNNNNNNN",
 "NNNYNNNNNNNNNNNNNNNNNNNNNNNYNNN",
 "NNNNNNNNYNNNNNNNNNNNNNNNYNNNNNN",
 "YNNNYNNNNNNNNNNNNNNNNNNNNNNYNNN",
 "NNNNNNNNNNYNNNNNNNNNNNNNNNNNNNN",
 "NNNNYNNYNNNNNNNNNNNNNNNNNNNNNNN",
 "NNNNNNNYNNNYNNNYNNNNNNNNNNNNNYN"};
			int expected__            = 7;

			return verifyCase(casenum__, expected__, new EllysLights().getMinimum(initial, switches));
		}
		case 5: {
			String initial            = "NYNYYNYNYYYYNNYNYNNYYNNNNNYNYNNNNNYNNNYN";
			String[] switches         = {"NNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNYNNNN",
 "NNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNN",
 "NNNNNNNNNYNNNNYNNYNNNNNNNNNNNNNNNNNNNNNN",
 "NNNNNNNNNNNNNNNNNNNYNNNNYNNNNNNNYNNNNNNN",
 "NNNNNYNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNN",
 "NNNNNNNNNNNNNNNNNNYNNNNNNNNYNNNYNNNNNYNN",
 "NNNNNNNNNNYNNNNNNNNNNNNNNYNNNNNYNNYNNNNN",
 "NNNNNYNNYNNYNNNNNNNNNNNNNNNNNNNNNYNNNNNN",
 "YNNNYNNNNNNNNNNNNNYNNNYNNYNNNNNNNYNNNNNN",
 "NNNNNNNNNYYNNNNNNNNNNNNYNNNNYNNNNNNNNNNN",
 "NNNNNNNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNNNY",
 "NNNNNNNNNNNNYNNNNNNNNNNNYNNNNNNNNNNNNNNN",
 "NNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNN",
 "NNNYNNNNNNNNNNNNNNNNNYNNNNNNNNNNYNNNNNNN",
 "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
 "NNNNNNNNNNNNNNYNNYNNNNNNNNNNNNNNNNNNNNNN",
 "NNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYYNNY",
 "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNN",
 "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
 "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
 "NNNNNNNYNNNNNYNYNNNNNNNNNNNNNNNNNNNNNNNN",
 "NNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNN",
 "NYNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNN",
 "NNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
 "NYNNNNYNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNN",
 "NNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNN",
 "NNNNNNNNNNNNYNNYYNNNNNNNNNNNNNNNNNNNNNNN",
 "NNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
 "NNNYNNNNNNNNNNNNNNNNYYNNNNNNNNNNNNNNNNNN",
 "NNNNNNNNYNNNNNNNNNNNNNNNNNNNYNYNNNNNNNNN",
 "NNNNNNNNNNNNNNNNNNNNNNNNNNYNNYNNNNNNYNNN"};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new EllysLights().getMinimum(initial, switches));
		}

		// custom cases

/*      case 6: {
			String initial            = ;
			String[] switches         = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new EllysLights().getMinimum(initial, switches));
		}*/
/*      case 7: {
			String initial            = ;
			String[] switches         = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new EllysLights().getMinimum(initial, switches));
		}*/
/*      case 8: {
			String initial            = ;
			String[] switches         = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new EllysLights().getMinimum(initial, switches));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
