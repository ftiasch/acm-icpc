import java.math.*;
import java.util.*;

class Complex {
    static Complex ZERO = new Complex(0, 0);
    static Complex ONE  = new Complex(1, 0);

    double x, y;

    Complex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Complex negate() {
        return new Complex(-x, -y);
    }

    Complex add(Complex o) {
        return new Complex(x + o.x, y + o.y);
    }

    Complex multiply(Complex o) {
        return new Complex(x * o.x - y * o.y, x * o.y + y * o.x);
    }

    Complex sqrt() {
        double r = Math.sqrt(Math.sqrt(x * x + y * y));
        double a = Math.atan2(y, x) * 0.5;
        return new Complex(r * Math.cos(a), r * Math.sin(a));
    }

    static Complex sincos(double t) {
        return new Complex(Math.cos(t), Math.sin(t));
    }

    public String toString() {
        return String.format("%.2f+%.2fi", x, y);
    }
}

public class SplittingFoxes2 {
    public int[] getPattern(int[] fox) {
        int n = fox.length;
        Complex[] q = new Complex[n];
        for (int i = 0; i < n; ++ i) {
            q[i] = new Complex(fox[i], 0.);
        }
        Complex[] iq = DFT(q, -1);
        int n2 = n + 2 >> 1;
        int[] result = new int[]{-1};
        for (int mask = 0; mask < 1 << n2; ++ mask) {
            Complex[] ip = new Complex[n];
            for (int i = 0; i < n; ++ i) {
                if (i < n2) {
                    ip[i] = iq[i].sqrt();
                    if ((mask >> i & 1) == 1) {
                        ip[i] = ip[i].negate();
                    }
                } else {
                    ip[i] = ip[n - i];
                }
            }
            Complex[] p = DFT(ip, 1);
            int[] pattern = new int[n];
            boolean valid = true;
            for (int i = 0; i < n && valid; ++ i) {
                pattern[i] = (int)(p[i].x / n + 0.5);
                valid &= pattern[i] >= 0;
                if ((n - i) % n < i) {
                    valid &= pattern[i] == pattern[(n - i) % n];
                }
            }
            if (valid && Arrays.equals(fox, multiply(pattern, pattern)) && compare(pattern, result)) {
                result = pattern;
            }
        }
        return result;
    }

    int[] multiply(int[] a, int[] b) {
        int n = a.length;
        int[] c = new int[n];
        for (int i = 0; i < n; ++ i) {
            long result = 0;
            for (int j = 0; j < n && result <= 1000000; ++ j) {
                int k = (i + n - j) % n;
                result += (long)a[j] * b[k];
            }
            if (result > 1000000) {
                return new int[]{-1};
            }
            c[i] = (int)result;
        }
        return c;
    }

    boolean compare(int[] a, int[] b) {
        if (b[0] == -1) {
            return true;
        }
        for (int i = 0; i < a.length; ++ i) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return false;
    }

    Complex[] DFT(Complex[] a, int oper) {
        int n = a.length;
        Complex[] b = new Complex[n];
        Arrays.fill(b, Complex.ZERO);
        for (int k = 0; k < n; ++ k) {
            Complex u = Complex.sincos(2 * Math.PI / n * k * oper);
            Complex t = Complex.ONE;
            for (int i = 0; i < n; ++ i) {
                b[k] = b[k].add(t.multiply(a[i]));
                t = t.multiply(u);
            }
        }
        return b;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SplittingFoxes2Harness.run_test(6);
        } else {
            for (int i=0; i<args.length; ++i)
                SplittingFoxes2Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SplittingFoxes2Harness {
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

    static boolean compareOutput(int[] expected, int[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(int[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, int[] expected, int[] received) {
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
            int[] amount              = {2,0,1,1,0};
            int[] expected__          = {0, 1, 0, 0, 1 };

            return verifyCase(casenum__, expected__, new SplittingFoxes2().getPattern(amount));
        }
        case 1: {
            int[] amount              = {1,0,0,0,0,0};
            int[] expected__          = {0, 0, 0, 1, 0, 0 };

            return verifyCase(casenum__, expected__, new SplittingFoxes2().getPattern(amount));
        }
        case 2: {
            int[] amount              = {2,0,0,0,0,0};
            int[] expected__          = {-1 };

            return verifyCase(casenum__, expected__, new SplittingFoxes2().getPattern(amount));
        }
        case 3: {
            int[] amount              = {10,0,8,0,10,0,8,0};
            int[] expected__          = {1, 0, 2, 0, 1, 0, 2, 0 };

            return verifyCase(casenum__, expected__, new SplittingFoxes2().getPattern(amount));
        }
        case 4: {
            int[] amount              = {35198,27644,22185,26896,34136,26896,22185,27644};
            int[] expected__          = {0, 59, 90, 76, 22, 76, 90, 59 };

            return verifyCase(casenum__, expected__, new SplittingFoxes2().getPattern(amount));
        }
        case 5: {
            int[] amount              = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            int[] expected__          = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

            return verifyCase(casenum__, expected__, new SplittingFoxes2().getPattern(amount));
        }

        // custom cases

        case 6: {
            int[] amount              = {4, 2, 4, 2, 5, 2, 4, 2};
            int[] expected__          = {-1};

            return verifyCase(casenum__, expected__, new SplittingFoxes2().getPattern(amount));
        }
/*      case 7: {
            int[] amount              = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new SplittingFoxes2().getPattern(amount));
        }*/
/*      case 8: {
            int[] amount              = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new SplittingFoxes2().getPattern(amount));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
