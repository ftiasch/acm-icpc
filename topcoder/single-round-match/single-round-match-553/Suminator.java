import java.math.*;
import java.util.*;

public class Suminator {
    class Result {
        int present;
        long constant;

        Result(int present, long constant) {
            this.present = present;
            this.constant = constant;
        }

        Result add(Result other) {
            return new Result(present + other.present, constant + other.constant);
        }
    }

    Result simulate(int[] program) {
        int n = program.length;
        Stack <Result> stack = new Stack <Result>();
        for (int i = 0; i < n + 1; ++ i) {
            stack.push(new Result(0, 0));
        }
        for (int i = 0; i < n; ++ i) {
            if (program[i] == 0) {
                Result op1 = stack.pop();
                Result op2 = stack.pop();
                stack.push(op1.add(op2));
            } else {
                Result op = program[i] == -1 ? new Result(1, 0) : new Result(0, program[i]);
                stack.push(op);
            }
        }
        return stack.pop();
    }

    public int findMissing(int[] program, int wantedResult) {
        int index = 0;
        while (program[index] != -1) {
            index ++;
        }
        program[index] = 0;
        if (simulate(program).constant == wantedResult) {
            return 0;
        }
        program[index] = -1;
        Result ret = simulate(program);
        if (ret.present == 1) {
            long result = wantedResult - ret.constant;
            return result > 0 ? (int)result : -1;
        }
        return ret.constant == wantedResult ? 1 : -1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			SuminatorHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SuminatorHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SuminatorHarness {
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
			int[] program             = {7,-1,0};
			int wantedResult          = 10;
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new Suminator().findMissing(program, wantedResult));
		}
		case 1: {
			int[] program             = {100, 200, 300, 0, 100, -1};
			int wantedResult          = 600;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new Suminator().findMissing(program, wantedResult));
		}
		case 2: {
			int[] program             = {-1, 7, 3, 0, 1, 2, 0, 0};
			int wantedResult          = 13;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new Suminator().findMissing(program, wantedResult));
		}
		case 3: {
			int[] program             = {-1, 8, 4, 0, 1, 2, 0, 0};
			int wantedResult          = 16;
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new Suminator().findMissing(program, wantedResult));
		}
		case 4: {
			int[] program             = {1000000000, 1000000000, 1000000000, 1000000000, -1, 0, 0, 0, 0};
			int wantedResult          = 1000000000;
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new Suminator().findMissing(program, wantedResult));
		}
		case 5: {
			int[] program             = {7, -1, 3, 0};
			int wantedResult          = 3;
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new Suminator().findMissing(program, wantedResult));
		}

		// custom cases

/*      case 6: {
			int[] program             = ;
			int wantedResult          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Suminator().findMissing(program, wantedResult));
		}*/
/*      case 7: {
			int[] program             = ;
			int wantedResult          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Suminator().findMissing(program, wantedResult));
		}*/
/*      case 8: {
			int[] program             = ;
			int wantedResult          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Suminator().findMissing(program, wantedResult));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
