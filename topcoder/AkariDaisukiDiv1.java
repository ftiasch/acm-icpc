import java.math.*;
import java.util.*;

public class AkariDaisukiDiv1 {
    final static int MOD =  1000000007;

    public int countF(String head, String body, String tail, String seed, String pattern, int times) {
        long result = 0;
        long extraCost = 0;
        for (int t = 0; t < times; ++ t) {
            if (t < 50) {
                if (seed.indexOf('$') == -1) {
                    result = 0;
                }
                seed = head + seed + body + seed + tail;
                extraCost = 0;
                for (int i = 0; i < seed.length(); ++ i) {
                    if (seed.substring(i).startsWith(pattern)) {
                        extraCost ++;
                    }
                }
                if (seed.length() > pattern.length() * 2 - 2) {
                    seed = seed.substring(0, pattern.length() - 1) + "$" + seed.substring(seed.length() - pattern.length() + 1, seed.length());
                }
            }
            result = (result + result + extraCost) % MOD;
        }
        return (int)result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			AkariDaisukiDiv1Harness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				AkariDaisukiDiv1Harness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class AkariDaisukiDiv1Harness {
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
			String Waai               = "a";
			String Akari              = "b";
			String Daisuki            = "c";
			String S                  = "x";
			String F                  = "axb";
			int k                     = 2;
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}
		case 1: {
			String Waai               = "a";
			String Akari              = "b";
			String Daisuki            = "c";
			String S                  = "x";
			String F                  = "abcdefghij";
			int k                     = 1;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}
		case 2: {
			String Waai               = "a";
			String Akari              = "a";
			String Daisuki            = "a";
			String S                  = "b";
			String F                  = "aba";
			int k                     = 2;
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}
		case 3: {
			String Waai               = "a";
			String Akari              = "b";
			String Daisuki            = "c";
			String S                  = "d";
			String F                  = "baadbdcbadbdccccbaaaadbdcbadbdccbaadbdcba";
			int k                     = 58;
			int expected__            = 191690599;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}
		case 4: {
			String Waai               = "a";
			String Akari              = "x";
			String Daisuki            = "y";
			String S                  = "b";
			String F                  = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
			int k                     = 49;
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}
		case 5: {
			String Waai               = "waai";
			String Akari              = "akari";
			String Daisuki            = "daisuki";
			String S                  = "usushio";
			String F                  = "id";
			int k                     = 10000000;
			int expected__            = 127859200;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}
		case 6: {
			String Waai               = "vfsebgjfyfgerkqlr";
			String Akari              = "ezbiwls";
			String Daisuki            = "kjerx";
			String S                  = "jbmjvaawoxycfndukrjfg";
			String F                  = "bgjfyfgerkqlrvfsebgjfyfgerkqlrvfsebgjfyfgerkqlrvfs";
			int k                     = 1575724;
			int expected__            = 483599313;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}

		// custom cases

/*      case 7: {
			String Waai               = ;
			String Akari              = ;
			String Daisuki            = ;
			String S                  = ;
			String F                  = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}*/
/*      case 8: {
			String Waai               = ;
			String Akari              = ;
			String Daisuki            = ;
			String S                  = ;
			String F                  = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}*/
/*      case 9: {
			String Waai               = ;
			String Akari              = ;
			String Daisuki            = ;
			String S                  = ;
			String F                  = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new AkariDaisukiDiv1().countF(Waai, Akari, Daisuki, S, F, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
