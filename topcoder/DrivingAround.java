import java.math.*;
import java.util.*;

public class DrivingAround {
    final static int MOD = 1000003;

    class Matrix {
        int matrix[][];

        Matrix(int n) {
            matrix = new int[n][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    matrix[i][j] = 0;
                }
            }
        }

        public Matrix multiply(Matrix other) {
            int n = matrix.length;
            Matrix result = new Matrix(n);
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    int tmp = 0;
                    for (int k = 0; k < n; ++ k) {
                        tmp += (long)matrix[i][k] * other.matrix[k][j] % MOD;
                        tmp %= MOD;
                    }
                    result.matrix[i][j] = tmp;
                }
            }
            return result;
        }

        public int size() {
            return matrix.length;
        }
    }

    Matrix pow(Matrix a, int n) {
        int m = a.size();
        Matrix result = new Matrix(m);
        for (int i = 0; i < m; ++ i) {
            result.matrix[i][i] = 1;
        }
        while (n > 0) {
            if ((n & 1) == 1) {
                result = result.multiply(a);
            }
            n >>= 1;
            a = a.multiply(a);
        }
        return result;
    }


    public int numberOfWays(String[] adjacent, int start, int finish, int time) {
        int n = adjacent.length;
        Matrix translation = new Matrix(5 * n);
        for (int i = 0; i < n; ++ i) {
            for (int level = 0; level < 5; ++ level) {
                for (int j = 0; j < n; ++ j) {
                    if (adjacent[i].charAt(j) != '.') {
                        int cost = Integer.parseInt(adjacent[i].substring(j, j + 1));
                        if (level + 1 == cost) {
                            translation.matrix[i * 5 + level][j * 5] ++;
                        }
                    }
                }
                if (level + 1 < 5) {
                    translation.matrix[i * 5 + level][i * 5 + level + 1] ++;
                }
            }
        }
        Matrix state = new Matrix(5 * n);
        state.matrix[0][start * 5] = 1;
        return state.multiply(pow(translation, time)).matrix[0][finish * 5];
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			DrivingAroundHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				DrivingAroundHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class DrivingAroundHarness {
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
			String[] adj              = {".12",
 "2.1",
 "12."};
			int start                 = 0;
			int finish                = 2;
			int time                  = 5;
			int expected__            = 8;

			return verifyCase(casenum__, expected__, new DrivingAround().numberOfWays(adj, start, finish, time));
		}
		case 1: {
			String[] adj              = {"....52....",
 "..5.......",
 "..........",
 ".......1..",
 "......42.2",
 "5...4.....",
 ".5...4...1",
 "......5...",
 ".3244.....",
 ".........."};
			int start                 = 2;
			int finish                = 2;
			int time                  = 10;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new DrivingAround().numberOfWays(adj, start, finish, time));
		}
		case 2: {
			String[] adj              = {"...14....1",
 "......13..",
 ".2...4....",
 "....52.5..",
 "1.3..4....",
 ".3....35.5",
 "4......1.1",
 "..4.4.1.54",
 "....4.11.5",
 "31144.2.4."};
			int start                 = 7;
			int finish                = 2;
			int time                  = 100;
			int expected__            = 316984;

			return verifyCase(casenum__, expected__, new DrivingAround().numberOfWays(adj, start, finish, time));
		}

		// custom cases

/*      case 3: {
			String[] adj              = ;
			int start                 = ;
			int finish                = ;
			int time                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DrivingAround().numberOfWays(adj, start, finish, time));
		}*/
/*      case 4: {
			String[] adj              = ;
			int start                 = ;
			int finish                = ;
			int time                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DrivingAround().numberOfWays(adj, start, finish, time));
		}*/
/*      case 5: {
			String[] adj              = ;
			int start                 = ;
			int finish                = ;
			int time                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DrivingAround().numberOfWays(adj, start, finish, time));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
