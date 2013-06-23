import java.math.*;
import java.util.*;

public class SpaceWarDiv1 {
    int[] girls;
    
    class Enemy implements Comparable <Enemy> {
        int strength;
        long count;

        Enemy(int strength, long count) {
            this.strength = strength;
            this.count = count;
        }

        public int compareTo(Enemy other) {
            return strength - other.strength;
        }
    }

    Enemy[] enemies;

    boolean check(long limit) {
        int n = girls.length;
        long[] count = new long[n];
        for (Enemy enemy : enemies) {
            long left = enemy.count;
            for (int i = 0; i < n; ++ i) {
                if (girls[i] >= enemy.strength) {
                    long ret = Math.min(left, limit - count[i]);
                    left -= ret;
                    count[i] += ret;
                }
            }
            if (left > 0) {
                return false;
            }
        }
        return true;
    }

    public long minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength, long[] enemyCount) {
        girls = magicalGirlStrength;
        int m = enemyStrength.length;
        enemies = new Enemy[m];
        long total = 0;
        for (int i = 0; i < m; ++ i) {
            enemies[i] = new Enemy(enemyStrength[i], enemyCount[i]);
            total += enemyCount[i];
        }
        Arrays.sort(girls);
        Arrays.sort(enemies);
        long low = 0;
        long high = total + 1;
        while (low < high) {
            long middle = low + high >> 1;
            if (check(middle)) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return high <= total ? high : -1;
    }


    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			SpaceWarDiv1Harness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SpaceWarDiv1Harness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SpaceWarDiv1Harness {
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
			int[] magicalGirlStrength = {2, 3, 5};
			int[] enemyStrength       = {1, 3, 4};
			long[] enemyCount         = {2, 9, 4};
			long expected__           = 7;

			return verifyCase(casenum__, expected__, new SpaceWarDiv1().minimalFatigue(magicalGirlStrength, enemyStrength, enemyCount));
		}
		case 1: {
			int[] magicalGirlStrength = {2, 3, 5};
			int[] enemyStrength       = {1, 1, 2};
			long[] enemyCount         = {2, 9, 4};
			long expected__           = 5;

			return verifyCase(casenum__, expected__, new SpaceWarDiv1().minimalFatigue(magicalGirlStrength, enemyStrength, enemyCount));
		}
		case 2: {
			int[] magicalGirlStrength = {14, 6, 22};
			int[] enemyStrength       = {8, 33};
			long[] enemyCount         = {9, 1};
			long expected__           = -1;

			return verifyCase(casenum__, expected__, new SpaceWarDiv1().minimalFatigue(magicalGirlStrength, enemyStrength, enemyCount));
		}
		case 3: {
			int[] magicalGirlStrength = {869, 249, 599, 144, 929, 748, 665, 37, 313, 99, 33, 437, 308, 137, 665, 834, 955, 958, 613, 417};
			int[] enemyStrength       = {789, 57, 684, 741, 128, 794, 542, 367, 937, 739, 568, 872, 127, 261, 103, 763, 864, 360, 618, 307};
			long[] enemyCount         = {20626770196420L, 45538527263992L, 52807114957507L, 17931716090785L, 65032910980630L, 88711853198687L, 26353250637092L, 61272534748707L, 89294362230771L, 52058590967576L, 60568594469453L, 23772707032338L, 43019142889727L, 39566072849912L, 78870845257173L, 68135668032761L, 36844201017584L, 10133804676521L, 6275847412927L, 37492167783296L};
			long expected__           = 75030497287405L;

			return verifyCase(casenum__, expected__, new SpaceWarDiv1().minimalFatigue(magicalGirlStrength, enemyStrength, enemyCount));
		}

		// custom cases

/*      case 4: {
			int[] magicalGirlStrength = ;
			int[] enemyStrength       = ;
			long[] enemyCount         = {};
			long expected__           = ;

			return verifyCase(casenum__, expected__, new SpaceWarDiv1().minimalFatigue(magicalGirlStrength, enemyStrength, enemyCount));
		}*/
/*      case 5: {
			int[] magicalGirlStrength = ;
			int[] enemyStrength       = ;
			long[] enemyCount         = {};
			long expected__           = ;

			return verifyCase(casenum__, expected__, new SpaceWarDiv1().minimalFatigue(magicalGirlStrength, enemyStrength, enemyCount));
		}*/
/*      case 6: {
			int[] magicalGirlStrength = ;
			int[] enemyStrength       = ;
			long[] enemyCount         = {};
			long expected__           = ;

			return verifyCase(casenum__, expected__, new SpaceWarDiv1().minimalFatigue(magicalGirlStrength, enemyStrength, enemyCount));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
