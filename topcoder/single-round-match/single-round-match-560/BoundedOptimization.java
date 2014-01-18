import java.math.*;
import java.util.*;

public class BoundedOptimization {
    public double maxValue(String[] parts, int[] lowerBound, int[] upperBound, int maxSum) {
        int n = lowerBound.length;
        boolean[][] graph = parseGraph(parts, n);
        double result = 0;
        for (int clique = 0; clique < 1 << n; ++ clique) {
            int rest = (1 << n) - 1 ^ clique;
            for (int valueMask = rest; valueMask >= 0; valueMask = valueMask - 1 & rest) {
                double[] value = new double[n];
                int nowSum = maxSum;
                for (int i = 0; i < n; ++ i) {
                    if ((rest >> i & 1) == 1) {
                        value[i] = (valueMask >> i & 1) == 0 ? lowerBound[i] : upperBound[i];
                        nowSum -= (int)value[i];
                    }
                }
                if (nowSum >= 0) {
                    if (clique > 0) {
                        int[] coefficient = new int[n];
                        double delta = nowSum;
                        for (int i = 0; i < n; ++ i) {
                            if ((clique >> i & 1) == 1) {
                                for (int j = 0; j < n; ++ j) {
                                    if (graph[i][j]) {
                                        coefficient[i] += (int)value[j];
                                    }
                                }
                                delta -= coefficient[i];
                            }
                        }
                        delta /= Integer.bitCount(clique);
                        boolean valid = true;
                        for (int i = 0; i < n; ++ i) {
                            if ((clique >> i & 1) == 1) {
                                value[i] = coefficient[i] + delta;
                                valid &= lowerBound[i] <= value[i] && value[i] <= upperBound[i];
                            }
                        }
                        if (!valid) {
                            if (valueMask == 0) {
                                break;
                            }
                            continue;
                        }
                    }
                    double sum = 0;
                    for (int i = 0; i < n; ++ i) {
                        for (int j = 0; j < i; ++ j) {
                            if (graph[i][j]) {
                                sum += value[i] * value[j];
                            }
                        }
                    }
                    result = Math.max(result, sum);
                }
                if (valueMask == 0) {
                    break;
                }
            }
        }
        return result;
    }

    boolean[][] parseGraph(String[] parts, int n) {
        String expression = "";
        for (String part : parts) {
            expression += part;
        }
        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < expression.length(); i += 3) {
            int a = expression.charAt(i) - 'a';
            int b = expression.charAt(i + 1) - 'a';
            graph[a][b] = graph[b][a] = true;
        }
        return graph;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			BoundedOptimizationHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				BoundedOptimizationHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class BoundedOptimizationHarness {
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
			String[] expr             = {"ba+cb"};
			int[] lowerBound          = {0,0,1};
			int[] upperBound          = {1,2,1};
			int maxSum                = 3;
			double expected__         = 2.25;

			return verifyCase(casenum__, expected__, new BoundedOptimization().maxValue(expr, lowerBound, upperBound, maxSum));
		}
		case 1: {
			String[] expr             = {"ab"};
			int[] lowerBound          = {0, 0, 10};
			int[] upperBound          = {20, 20, 20};
			int maxSum                = 12;
			double expected__         = 1.0;

			return verifyCase(casenum__, expected__, new BoundedOptimization().maxValue(expr, lowerBound, upperBound, maxSum));
		}
		case 2: {
			String[] expr             = {"ca+fc+fa+d","b+da+","dc+c","b","+ed+eb+ea"};
			int[] lowerBound          = {10,11,12,13,14,15};
			int[] upperBound          = {15,16,17,18,19,20};
			int maxSum                = 85;
			double expected__         = 2029.25;

			return verifyCase(casenum__, expected__, new BoundedOptimization().maxValue(expr, lowerBound, upperBound, maxSum));
		}
		case 3: {
			String[] expr             = {"db+ea+ik+kh+je+","fj+lk+i","d+jb+h","a+gk+mb+ml+lc+mh+cf+fd+","gc+ka+gf+bh+mj+eg+bf+hf+l","b+al+ja+da+i",
"f+g","h+ia+le+ce+gi+d","h+mc+fe+dm+im+kb+bc+","ib+ma+eb+mf+jk+kc+mg+mk+","gb+dl+ek+hj+dg+hi","+ch+ga+ca+fl+ij+fa+jl+dc+dj+fk","+li+jg"};
			int[] lowerBound          = {57,29,50,21,49,29,88,33,84,76,95,55,11};
			int[] upperBound          = {58,80,68,73,52,84,100,79,93,98,95,69,97};
			int maxSum                = 845;
			double expected__         = 294978.3333333333;

			return verifyCase(casenum__, expected__, new BoundedOptimization().maxValue(expr, lowerBound, upperBound, maxSum));
		}

		// custom cases

/*      case 4: {
			String[] expr             = ;
			int[] lowerBound          = ;
			int[] upperBound          = ;
			int maxSum                = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new BoundedOptimization().maxValue(expr, lowerBound, upperBound, maxSum));
		}*/
/*      case 5: {
			String[] expr             = ;
			int[] lowerBound          = ;
			int[] upperBound          = ;
			int maxSum                = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new BoundedOptimization().maxValue(expr, lowerBound, upperBound, maxSum));
		}*/
/*      case 6: {
			String[] expr             = ;
			int[] lowerBound          = ;
			int[] upperBound          = ;
			int maxSum                = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new BoundedOptimization().maxValue(expr, lowerBound, upperBound, maxSum));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
