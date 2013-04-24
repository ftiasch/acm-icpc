import java.math.*;
import java.util.*;

public class PasswordXGuessing {
    boolean check(String[] guesses, String answer) {
        int length = answer.length();
        for (String guess : guesses) {
            int count = 0;
            for (int i = 0; i < length; ++ i) {
                if (guess.charAt(i) != answer.charAt(i)) {
                    count ++;
                }
            }
            if (count != 1) {
                return false;
            }
        }
        return true;
    }

    public long howMany(String[] guesses) {
        int n = guesses.length;
        Set <String> set = new HashSet <String>();
        for (String guess : guesses) {
            for (int i = 0; i < guess.length(); ++ i) {
                for (int d = 0; d < 10; ++ d) {
                    String answer = guess.substring(0, i) + d + guess.substring(i + 1);
                    if (check(guesses, answer) && !set.contains(answer)) {
                        set.add(answer);
                    }
                }
            }
        }
        return set.size();
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			PasswordXGuessingHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PasswordXGuessingHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PasswordXGuessingHarness {
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
			String[] guesses          = {"58", "47"};
			long expected__           = 2;

			return verifyCase(casenum__, expected__, new PasswordXGuessing().howMany(guesses));
		}
		case 1: {
			String[] guesses          = {"539", "540", "541"};
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new PasswordXGuessing().howMany(guesses));
		}
		case 2: {
			String[] guesses          = {"12", "34", "56", "78"};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new PasswordXGuessing().howMany(guesses));
		}
		case 3: {
			String[] guesses          = {"2", "3", "5"};
			long expected__           = 7;

			return verifyCase(casenum__, expected__, new PasswordXGuessing().howMany(guesses));
		}
		case 4: {
			String[] guesses          = {"4747", "4747", "4747", "4747"};
			long expected__           = 36;

			return verifyCase(casenum__, expected__, new PasswordXGuessing().howMany(guesses));
		}

		// custom cases

/*      case 5: {
			String[] guesses          = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PasswordXGuessing().howMany(guesses));
		}*/
/*      case 6: {
			String[] guesses          = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PasswordXGuessing().howMany(guesses));
		}*/
/*      case 7: {
			String[] guesses          = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PasswordXGuessing().howMany(guesses));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
