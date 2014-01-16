import java.math.*;
import java.util.*;

public class ICPCBalloons {
    public int minRepaintings(int[] balloonCount, String balloonSize, int[] maxAccepted) {
        ArrayList <Integer> mediums = new ArrayList <Integer>();
        ArrayList <Integer> larges = new ArrayList <Integer>();
        for (int i = 0; i < balloonCount.length; ++ i) {
            if (balloonSize.charAt(i) == 'M') {
                mediums.add(balloonCount[i]);
            } else {
                larges.add(balloonCount[i]);
            }
        }
        Collections.sort(mediums);
        Collections.sort(larges);
        int result = Integer.MAX_VALUE;
        Arrays.sort(maxAccepted);
        int n = maxAccepted.length;
        for (int mask = 0; mask < 1 << n; ++ mask) {
            int mediumCount = 0;
            int largeCount = 0;
            for (int count : mediums) {
                mediumCount += count;
            }
            for (int count : larges) {
                largeCount += count;
            }
            for (int i = 0; i < n; ++ i) {
                if ((mask >> i & 1) == 0) {
                    mediumCount -= maxAccepted[i];
                } else {
                    largeCount -= maxAccepted[i];
                }
            }
            if (mediumCount >= 0 && largeCount >= 0) {
                int count = 0;
                for (int i = n - 1, j = mediums.size(), k = larges.size(); i >= 0; -- i) {
                    if ((mask >> i & 1) == 0) {
                        if (j > 0) {
                            count += Math.max(maxAccepted[i] - mediums.get(-- j), 0);
                        } else {
                            count += maxAccepted[i];
                        }
                    } else {
                        if (k > 0) {
                            count += Math.max(maxAccepted[i] - larges.get(-- k), 0);
                        } else {
                            count += maxAccepted[i];
                        }
                    }
                }
                result = Math.min(result, count);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			ICPCBalloonsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ICPCBalloonsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ICPCBalloonsHarness {
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
			int[] balloonCount        = {100};
			String balloonSize        = "L";
			int[] maxAccepted         = {1,2,3,4,5};
			int expected__            = 10;

			return verifyCase(casenum__, expected__, new ICPCBalloons().minRepaintings(balloonCount, balloonSize, maxAccepted));
		}
		case 1: {
			int[] balloonCount        = {100};
			String balloonSize        = "M";
			int[] maxAccepted         = {10,20,30,40,50};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new ICPCBalloons().minRepaintings(balloonCount, balloonSize, maxAccepted));
		}
		case 2: {
			int[] balloonCount        = {5,6,1,5,6,1,5,6,1};
			String balloonSize        = "MLMMLMMLM";
			int[] maxAccepted         = {7,7,4,4,7,7};
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new ICPCBalloons().minRepaintings(balloonCount, balloonSize, maxAccepted));
		}
		case 3: {
			int[] balloonCount        = {100,100};
			String balloonSize        = "ML";
			int[] maxAccepted         = {50,51,51};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new ICPCBalloons().minRepaintings(balloonCount, balloonSize, maxAccepted));
		}
		case 4: {
			int[] balloonCount        = {8,5,1,4,1,1,3,1,3,3,5,4,5,6,9};
			String balloonSize        = "MMMLLLMMLLMLMLM";
			int[] maxAccepted         = {3,5,3,3,5,6,4,6,4,2,3,7,1,5,2};
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new ICPCBalloons().minRepaintings(balloonCount, balloonSize, maxAccepted));
		}
		case 5: {
			int[] balloonCount        = {1,18,4,7,19,7,7,1,4,8,10,5,14,13,8,22,6,3,13,5,3,4,2,1,3,15,19,4,5,9,4,11,2,7,12,20,11,26,22,7,2,10,9,20,13,20,2,9,11,9};
			String balloonSize        = "LLMLLLLMLLLLLLLLLLLLMLLLLLLLLLLMMLMLLLMLLLLLLLLMLL";
			int[] maxAccepted         = {44,59,29,53,16,23,13,14,29,42,13,15,66,4,47};
			int expected__            = 210;

			return verifyCase(casenum__, expected__, new ICPCBalloons().minRepaintings(balloonCount, balloonSize, maxAccepted));
		}

		// custom cases

/*      case 6: {
			int[] balloonCount        = ;
			String balloonSize        = ;
			int[] maxAccepted         = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ICPCBalloons().minRepaintings(balloonCount, balloonSize, maxAccepted));
		}*/
/*      case 7: {
			int[] balloonCount        = ;
			String balloonSize        = ;
			int[] maxAccepted         = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ICPCBalloons().minRepaintings(balloonCount, balloonSize, maxAccepted));
		}*/
/*      case 8: {
			int[] balloonCount        = ;
			String balloonSize        = ;
			int[] maxAccepted         = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ICPCBalloons().minRepaintings(balloonCount, balloonSize, maxAccepted));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
