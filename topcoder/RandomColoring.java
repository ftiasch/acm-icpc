import java.math.*;
import java.util.*;

public class RandomColoring {
    int countLength(int maxR, int r, int d) {
        return Math.min(r + d, maxR - 1) - Math.max(r - d, 0) + 1;
    }

    int countVolume(int maxR, int maxG, int maxB, int r, int g, int b, int d) {
        if (d < 0) {
            return 0;
        }
        return countLength(maxR, r, d) * countLength(maxG, g, d) * countLength(maxB, b, d);
    }

    void coverVolume(int maxR, int maxG, int maxB, int r, int g, int b, int d, double sum[][][], double weight) {
        if (d >= 0) {
            int r1 = Math.max(r - d, 0);
            int r2 = Math.min(r + d, maxR - 1);
            int g1 = Math.max(g - d, 0);
            int g2 = Math.min(g + d, maxG - 1);
            int b1 = Math.max(b - d, 0);
            int b2 = Math.min(b + d, maxB - 1);
            sum[r1][g1][b1] += weight;
            sum[r2 + 1][g1][b1] -= weight;
            sum[r1][g2 + 1][b1] -= weight;
            sum[r1][g1][b2 + 1] -= weight;
            sum[r2 + 1][g2 + 1][b1] += weight;
            sum[r2 + 1][g1][b2 + 1] += weight;
            sum[r1][g2 + 1][b2 + 1] += weight;
            sum[r2 + 1][g2 + 1][b2 + 1] -= weight;
        }
    }

    double access(double probability[][][], int r, int g, int b) {
        if (r >= 0 && g >= 0 && b >= 0) {
            return probability[r][g][b];
        }
        return 0;
    }

    public double getProbability(int N, int maxR, int maxG, int maxB, int startR, int startG, int startB, int d1, int d2) {
        double probability[][][] = new double[maxR][maxG][maxB];
        probability[startR][startG][startB] = 1.0;
        for (int round = 1; round < N; ++ round) {
            double sum[][][] = new double[maxR + 1][maxG + 1][maxB + 1];
            for (int r = 0; r < maxR; ++ r) {
                for (int g = 0; g < maxG; ++ g) {
                    for (int b = 0; b < maxB; ++ b) {
                        int ways = countVolume(maxR, maxG, maxB, r, g, b, d2) - countVolume(maxR, maxG, maxB, r, g, b, d1 - 1);
                        if (ways > 0) {
                            double weight = probability[r][g][b] / ways;
                            coverVolume(maxR, maxG, maxB, r, g, b, d2, sum, weight);
                            coverVolume(maxR, maxG, maxB, r, g, b, d1 - 1, sum, -weight);
                        }
                    }
                }
            }
            for (int r = 0; r < maxR; ++ r) {
                for (int g = 0; g < maxG; ++ g) {
                    for (int b = 0; b < maxB; ++ b) {
                        probability[r][g][b] = 0;
                    }
                }
            }
            for (int r = 0; r < maxR; ++ r) {
                for (int g = 0; g < maxG; ++ g) {
                    for (int b = 0; b < maxB; ++ b) {
                        probability[r][g][b] = access(probability, r - 1, g, b)
                            + access(probability, r, g - 1, b)
                            + access(probability, r, g, b - 1)
                            - access(probability, r - 1, g - 1, b)
                            - access(probability, r - 1, g, b - 1)
                            - access(probability, r, g - 1, b - 1)
                            + access(probability, r - 1, g - 1, b - 1)
                            + sum[r][g][b];
                    }
                }
            }
        }
        double result = 0;
        for (int r = 0; r < maxR; ++ r) {
            for (int g = 0; g < maxG; ++ g) {
                for (int b = 0; b < maxB; ++ b) {
                    if (Math.abs(r - startR) <= d2 && Math.abs(g - startG) <= d2 && Math.abs(b - startB) <= d2 && (Math.abs(r - startR) >= d1 || Math.abs(g - startG) >= d1 || Math.abs(b - startB) >= d1)) {
                        result += probability[r][g][b];
                    }
                }
            }
        }
        return 1 - result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			RandomColoringHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				RandomColoringHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class RandomColoringHarness {
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
	
	static final double MAX_DOUBLE_ERROR = 1E-9;
	static boolean compareOutput(double expected, double result){ if(Double.isNaN(expected)){ return Double.isNaN(result); }else if(Double.isInfinite(expected)){ if(expected > 0){ return result > 0 && Double.isInfinite(result); }else{ return result < 0 && Double.isInfinite(result); } }else if(Double.isNaN(result) || Double.isInfinite(result)){ return false; }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){ return true; }else{ double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > min && result < max; } }
	static double relativeError(double expected, double result) { if (Double.isNaN(expected) || Double.isInfinite(expected) || Double.isNaN(result) || Double.isInfinite(result) || expected == 0) return 0; return Math.abs(result-expected) / Math.abs(expected); }
	
	static String formatResult(double res) {
		return String.format("%.10g", res);
	}
	
	static int verifyCase(int casenum, double expected, double received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.print("PASSED");
			double rerr = relativeError(expected, received);
			if (rerr > 0) System.err.printf(" (relative error %g)", rerr);
			System.err.println();
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
			int N                     = 2;
			int maxR                  = 5;
			int maxG                  = 1;
			int maxB                  = 1;
			int startR                = 2;
			int startG                = 0;
			int startB                = 0;
			int d1                    = 0;
			int d2                    = 1;
			double expected__         = 0.0;

			return verifyCase(casenum__, expected__, new RandomColoring().getProbability(N, maxR, maxG, maxB, startR, startG, startB, d1, d2));
		}
		case 1: {
			int N                     = 3;
			int maxR                  = 5;
			int maxG                  = 1;
			int maxB                  = 1;
			int startR                = 2;
			int startG                = 0;
			int startB                = 0;
			int d1                    = 0;
			int d2                    = 1;
			double expected__         = 0.22222222222222227;

			return verifyCase(casenum__, expected__, new RandomColoring().getProbability(N, maxR, maxG, maxB, startR, startG, startB, d1, d2));
		}
		case 2: {
			int N                     = 7;
			int maxR                  = 4;
			int maxG                  = 2;
			int maxB                  = 2;
			int startR                = 0;
			int startG                = 0;
			int startB                = 0;
			int d1                    = 3;
			int d2                    = 3;
			double expected__         = 1.0;

			return verifyCase(casenum__, expected__, new RandomColoring().getProbability(N, maxR, maxG, maxB, startR, startG, startB, d1, d2));
		}
		case 3: {
			int N                     = 10;
			int maxR                  = 7;
			int maxG                  = 8;
			int maxB                  = 9;
			int startR                = 1;
			int startG                = 2;
			int startB                = 3;
			int d1                    = 0;
			int d2                    = 10;
			double expected__         = 0.0;

			return verifyCase(casenum__, expected__, new RandomColoring().getProbability(N, maxR, maxG, maxB, startR, startG, startB, d1, d2));
		}
		case 4: {
			int N                     = 10;
			int maxR                  = 7;
			int maxG                  = 8;
			int maxB                  = 9;
			int startR                = 1;
			int startG                = 2;
			int startB                = 3;
			int d1                    = 4;
			int d2                    = 10;
			double expected__         = 0.37826245943967396;

			return verifyCase(casenum__, expected__, new RandomColoring().getProbability(N, maxR, maxG, maxB, startR, startG, startB, d1, d2));
		}
		case 5: {
			int N                     = 3;
			int maxR                  = 3;
			int maxG                  = 2;
			int maxB                  = 2;
			int startR                = 1;
			int startG                = 0;
			int startB                = 0;
			int d1                    = 1;
			int d2                    = 2;
			double expected__         = 0.09090909090909148;

			return verifyCase(casenum__, expected__, new RandomColoring().getProbability(N, maxR, maxG, maxB, startR, startG, startB, d1, d2));
		}

		// custom cases

/*      case 6: {
			int N                     = ;
			int maxR                  = ;
			int maxG                  = ;
			int maxB                  = ;
			int startR                = ;
			int startG                = ;
			int startB                = ;
			int d1                    = ;
			int d2                    = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new RandomColoring().getProbability(N, maxR, maxG, maxB, startR, startG, startB, d1, d2));
		}*/
/*      case 7: {
			int N                     = ;
			int maxR                  = ;
			int maxG                  = ;
			int maxB                  = ;
			int startR                = ;
			int startG                = ;
			int startB                = ;
			int d1                    = ;
			int d2                    = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new RandomColoring().getProbability(N, maxR, maxG, maxB, startR, startG, startB, d1, d2));
		}*/
/*      case 8: {
			int N                     = ;
			int maxR                  = ;
			int maxG                  = ;
			int maxB                  = ;
			int startR                = ;
			int startG                = ;
			int startB                = ;
			int d1                    = ;
			int d2                    = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new RandomColoring().getProbability(N, maxR, maxG, maxB, startR, startG, startB, d1, d2));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
