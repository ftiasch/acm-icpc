import java.math.*;
import java.util.*;

public class EllysBulls {
    final static int MAGIC = 23;

    String format(int n, int m) {
        return String.format(String.format("%%0%dd", m), n);
    }

    public String getNumber(String[] guesses, int[] bulls) {
        int n = guesses.length;
        int m = guesses[0].length();

        int[] power10 = new int[m + 1];
        power10[0] = 1;
        for (int i = 1; i <= m; ++ i) {
            power10[i] = power10[i - 1] * 10;
        }

        int m1 = m >> 1;
        int m2 = m - m1;

        Set <Long> set = new HashSet <Long>();
        long[] leftHash = new long[power10[m1]];
        for (int left = 0; left < power10[m1]; ++ left) {
            String string = format(left, m1);
            long hash = 0;
            for (int i = 0; i < n; ++ i) {
                int count = 0;
                for (int j = 0; j < m1; ++ j) {
                    if (string.charAt(j) == guesses[i].charAt(j)) {
                        count ++;
                    }
                }
                hash = hash * MAGIC + count;
            }
            set.add(hash);
            leftHash[left] = hash;
        }

        String answer = "";
        for (int right = 0; right < power10[m2]; ++ right) {
            String string = format(right, m2); 
            long hash = 0;
            for (int i = 0; i < n; ++ i) {
                int count = 0;
                for (int j = 0; j < m2; ++ j) {
                    if (string.charAt(j) == guesses[i].charAt(m1 + j)) {
                        count ++;
                    }
                }
                hash = hash * MAGIC + bulls[i] - count;
            }
            if (set.contains(hash)) {
                if (!answer.equals("")) {
                    return "Ambiguity";
                }
                for (int left = 0; left < power10[m1]; ++ left) {
                    if (leftHash[left] == hash) {
                        if (!answer.equals("")) {
                            return "Ambiguity";
                        }
                        answer = format(left, m1) + format(right, m2);
                    }
                }
            }
        }
        return answer.equals("") ? "Liar" : answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			EllysBullsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				EllysBullsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class EllysBullsHarness {
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
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
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
			String[] guesses          = {"1234", "4321", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999"};
			int[] bulls               = {2, 1, 1, 0, 2, 0, 0, 0, 1, 0, 0};
			String expected__         = "1337";

			return verifyCase(casenum__, expected__, new EllysBulls().getNumber(guesses, bulls));
		}
		case 1: {
			String[] guesses          = {"0000", "1111", "2222"};
			int[] bulls               = {2, 2, 2};
			String expected__         = "Liar";

			return verifyCase(casenum__, expected__, new EllysBulls().getNumber(guesses, bulls));
		}
		case 2: {
			String[] guesses          = {"666666", "666677", "777777", "999999"};
			int[] bulls               = {2, 3, 1, 0};
			String expected__         = "Ambiguity";

			return verifyCase(casenum__, expected__, new EllysBulls().getNumber(guesses, bulls));
		}
		case 3: {
			String[] guesses          = {"000", "987", "654", "321", "100", "010"};
			int[] bulls               = {2, 1, 0, 0, 1, 1};
			String expected__         = "007";

			return verifyCase(casenum__, expected__, new EllysBulls().getNumber(guesses, bulls));
		}
		case 4: {
			String[] guesses          = {"28", "92", "70", "30", "67", "63", "06", "65",
 "11", "06", "88", "48", "09", "65", "48", "08"};
			int[] bulls               = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			String expected__         = "54";

			return verifyCase(casenum__, expected__, new EllysBulls().getNumber(guesses, bulls));
		}
		case 5: {
			String[] guesses          = {"0294884", "1711527", "2362216", "7666148", "7295642",
 "4166623", "1166638", "2767693", "8650248", "2486509",
 "6138934", "4018642", "6236742", "2961643", "8407361",
 "2097376", "6575410", "6071777", "3569948", "2606380"};
			int[] bulls               = {1, 0, 1, 3, 4, 4, 3, 2, 1, 1, 0, 4, 4, 3, 0, 0, 0, 0, 2, 1};
			String expected__         = "4266642";

			return verifyCase(casenum__, expected__, new EllysBulls().getNumber(guesses, bulls));
		}

		// custom cases

      case 6: {
			String[] guesses          = new String[50];
            for (int i = 0; i < 50; ++ i) {
                guesses[i] = "012345678";
            }
			int[] bulls               = new int[50];
            for (int i = 0; i < 50; ++ i) {
                bulls[i] = 9;
            }
			String expected__         = "012345678";

			return verifyCase(casenum__, expected__, new EllysBulls().getNumber(guesses, bulls));
		}
/*      case 7: {
			String[] guesses          = ;
			int[] bulls               = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new EllysBulls().getNumber(guesses, bulls));
		}*/
/*      case 8: {
			String[] guesses          = ;
			int[] bulls               = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new EllysBulls().getNumber(guesses, bulls));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
