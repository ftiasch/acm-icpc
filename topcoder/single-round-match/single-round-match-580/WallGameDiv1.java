import java.math.*;
import java.util.*;

public class WallGameDiv1 {
    int n, m;
    int[][] sum;
    Integer[][][][] rabbitMemory, eelMemory;

    int rabbit(int i, int j, int l, int r) {
        if (rabbitMemory[i][j][l][r] == null) {
            if (i == n - 1) {
                return 0;
            }
            if (j < l || r <= j) {
                return sum[i + 1][j] - sum[i + 1][j + 1] + eel(i + 1, j, j, j);
            }
            int ret = Integer.MAX_VALUE;
            if (l > 0) {
                ret = Math.min(ret, sum[i][l - 1] - sum[i][j] + eel(i, l - 1, l, r));
            }
            if (r < m) {
                ret = Math.min(ret, sum[i][j + 1] - sum[i][r + 1] + eel(i, r, l, r));
            }
            rabbitMemory[i][j][l][r] = ret;
        }
        return rabbitMemory[i][j][l][r];
    }

    int eel(int i, int j, int l, int r) {
        if (eelMemory[i][j][l][r] == null) {
            if (i == n - 1) {
                return 0;
            }
            int ret = rabbit(i, j, l, r);
            if (r - l + 1 < m) {
                ret = Math.max(ret, rabbit(i, j, Math.min(l, j), Math.max(r, j + 1)));
            }
            eelMemory[i][j][l][r] = ret;
        }
        return eelMemory[i][j][l][r];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    public int play(String[] costs) {
        n = costs.length;
        m = costs[0].length();
        sum = new int[n][m + 1];
        for (int i = 0; i < n; ++ i) {
            sum[i][m] = 0;
            for (int j = m - 1; j >= 0; -- j) {
                sum[i][j] = sum[i][j + 1] + (costs[i].charAt(j) - '0');
            }
        }
        rabbitMemory = new Integer[n][m][m][m + 1];
        eelMemory = new Integer[n][m][m][m + 1];
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++ i) {
            answer = Math.min(answer, sum[0][i] - sum[0][i + 1] + eel(0, i, i, i));
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			WallGameDiv1Harness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				WallGameDiv1Harness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class WallGameDiv1Harness {
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
			String[] costs            = {"12"
,"34"};
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new WallGameDiv1().play(costs));
		}
		case 1: {
			String[] costs            = {"99999"
,"99999"
,"99999"};
			int expected__            = 99;

			return verifyCase(casenum__, expected__, new WallGameDiv1().play(costs));
		}
		case 2: {
			String[] costs            = {"11111"
,"90005"};
			int expected__            = 10;

			return verifyCase(casenum__, expected__, new WallGameDiv1().play(costs));
		}
		case 3: {
			String[] costs            = {"4417231387449337370319219832088987579792"
,"3117295688208899006196193430472892512797"
,"0835796222361526836944954410684516919758"
,"1988200069973565052900745230547016216225"
,"8080511471118865780390486996601082102418"
,"4229084185957675819725844582167613108400"
,"9068653877952162764545674979144308771754"
,"8551565425951612499712254182109991511690"
,"7090525592007482152807163656647658543093"
,"9198968742256995592729889137556032960142"
,"2071864379877766468141740053503050313101"
,"1055375249261868472993219156394129253481"
,"2461178270502857106406495509025426298874"
,"8216781259733537757203421037984512664825"
,"4693452554407216935375049784445568498482"
,"2709693439640250842244170297203200674391"
,"5122217695676372684217182241705137947908"
,"6668814191410691246849638388008228444294"
,"4952122070212780440963814752538149378639"
,"9827737761436134120332969866554332504400"
,"3412406588339828249986707470540386748886"
,"7521506848088506994554600408372456405830"
,"1916926179183007872881163173153907665999"
,"6636166791472761992310264951474925339024"
,"6679667717747231380196787943691121008076"
,"3218993234115685151069259138068533004433"
,"4920152552986426962081492913852060211795"
,"0365186235986445521382132973036266396894"
,"3205351414936828189305188614651974318855"
,"3144278971529524658788277656125598825426"
,"5525287537572356662391582052968411726245"
,"2130137226726919525622574701068062252930"
,"2369996694245544770519574837226971226841"
,"6806769058165410189286521891570936341547"
,"0448109211631660241710734664066810078242"
,"4622919135804954122810530631429501069659"
,"0235247446548732490429856705369583140671"
,"2193399741215975228987754171460722199304"
,"1203037020703833716225328076959743850915"
,"5419885193880826109484912489603262199432"};
			int expected__            = 7366;

			return verifyCase(casenum__, expected__, new WallGameDiv1().play(costs));
		}

		// custom cases

/*      case 4: {
			String[] costs            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new WallGameDiv1().play(costs));
		}*/
/*      case 5: {
			String[] costs            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new WallGameDiv1().play(costs));
		}*/
/*      case 6: {
			String[] costs            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new WallGameDiv1().play(costs));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
