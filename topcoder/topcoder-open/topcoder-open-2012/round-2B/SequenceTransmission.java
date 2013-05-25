import java.math.*;
import java.util.*;

public class SequenceTransmission {
    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    public long signalChanges(long a, long b, long n) {
        b += a;
        long answer = -n;
        for (int l = 0; l < 60; ++ l) {
            long length = 1L << l + 2;
            long period = n / length;
            long tail = n % length;
            for (long r = 0; r < length; ) {
                long v = a * r + b;
                long all = (1L << l) - 1;
                long q = Math.min(r + (all - (v & all)) / a, length - 1);
                if ((v >> l & 1) != (v >> l + 1 & 1)) {
                    if (r < tail) {
                        answer += (period + 1) * (Math.min(q, tail - 1) - r + 1);
                    }
                    if (q >= tail) {
                        answer += period * (q - Math.max(r, tail) + 1);
                    }
                }
                r = q + 1;
            }
        }
        if (a % 2 == 0) {
            if (b % 2 == 0) {
                answer += n - 1;
            }
        } else {
            if (b % 2 == 0) {
                answer += n / 2;
            } else {
                answer += (n - 1) / 2;
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			SequenceTransmissionHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SequenceTransmissionHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SequenceTransmissionHarness {
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
			long a                    = 3;
			long b                    = 5;
			long n                    = 4;
			long expected__           = 8;

			return verifyCase(casenum__, expected__, new SequenceTransmission().signalChanges(a, b, n));
		}
		case 1: {
			long a                    = 1;
			long b                    = 576460752303423487L;
			long n                    = 1;
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new SequenceTransmission().signalChanges(a, b, n));
		}
		case 2: {
			long a                    = 1;
			long b                    = 1;
			long n                    = 10;
			long expected__           = 18;

			return verifyCase(casenum__, expected__, new SequenceTransmission().signalChanges(a, b, n));
		}
		case 3: {
			long a                    = 40000;
			long b                    = 1000000000000000000L;
			long n                    = 1000000000000L;
			long expected__           = 27066924353831L;

			return verifyCase(casenum__, expected__, new SequenceTransmission().signalChanges(a, b, n));
		}

		// custom cases

/*      case 4: {
			long a                    = ;
			long b                    = ;
			long n                    = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new SequenceTransmission().signalChanges(a, b, n));
		}*/
/*      case 5: {
			long a                    = ;
			long b                    = ;
			long n                    = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new SequenceTransmission().signalChanges(a, b, n));
		}*/
/*      case 6: {
			long a                    = ;
			long b                    = ;
			long n                    = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new SequenceTransmission().signalChanges(a, b, n));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
