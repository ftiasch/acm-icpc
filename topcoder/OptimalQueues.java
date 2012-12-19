import java.math.*;
import java.util.*;

public class OptimalQueues {
    public int minWaitingTime(int[] a, int m, int time) {
        Arrays.sort(a);
        int n = a.length;
        int answer = 0;
        for (int i = n - 1; i >= 0; -- i) {
            answer = Math.max(answer, a[i] + time * ((n - i + m - 1) / m));
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			OptimalQueuesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				OptimalQueuesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class OptimalQueuesHarness {
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
			int[] clientArrivals      = {1,2};
			int tellerCount           = 1;
			int serviceTime           = 10;
			int expected__            = 21;

			return verifyCase(casenum__, expected__, new OptimalQueues().minWaitingTime(clientArrivals, tellerCount, serviceTime));
		}
		case 1: {
			int[] clientArrivals      = {10};
			int tellerCount           = 50;
			int serviceTime           = 50;
			int expected__            = 60;

			return verifyCase(casenum__, expected__, new OptimalQueues().minWaitingTime(clientArrivals, tellerCount, serviceTime));
		}
		case 2: {
			int[] clientArrivals      = {10,10,10};
			int tellerCount           = 2;
			int serviceTime           = 20;
			int expected__            = 50;

			return verifyCase(casenum__, expected__, new OptimalQueues().minWaitingTime(clientArrivals, tellerCount, serviceTime));
		}
		case 3: {
			int[] clientArrivals      = {2,4,6,3,5};
			int tellerCount           = 3;
			int serviceTime           = 10;
			int expected__            = 23;

			return verifyCase(casenum__, expected__, new OptimalQueues().minWaitingTime(clientArrivals, tellerCount, serviceTime));
		}

		// custom cases

/*      case 4: {
			int[] clientArrivals      = ;
			int tellerCount           = ;
			int serviceTime           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new OptimalQueues().minWaitingTime(clientArrivals, tellerCount, serviceTime));
		}*/
/*      case 5: {
			int[] clientArrivals      = ;
			int tellerCount           = ;
			int serviceTime           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new OptimalQueues().minWaitingTime(clientArrivals, tellerCount, serviceTime));
		}*/
/*      case 6: {
			int[] clientArrivals      = ;
			int tellerCount           = ;
			int serviceTime           = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new OptimalQueues().minWaitingTime(clientArrivals, tellerCount, serviceTime));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
