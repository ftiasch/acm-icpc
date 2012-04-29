import java.math.*;
import java.util.*;

public class STable {
    final static long MAGIC = 127;
    final static long MOD = 1000000007;

    String a, b;
    int n, m;

    class Hash {
        public long length, hashValue;
        public Hash first, second;

        Hash(long length, long hashValue, Hash first, Hash second) {
            this.length = length;
            this.hashValue = hashValue;
            this.first = first;
            this.second = second;
        }

        long powMod(long a, long n, long m) {
            long result = 1 % m;
            while (n > 0) {
                if ((n & 1) == 1) {
                    result = (result * a) % m;
                }
                a = (a * a) % m;
                n >>= 1;
            }
            return result;
        }

        public Hash join(Hash other) {
            return new Hash(length + other.length, (hashValue + powMod(MAGIC, length, MOD) * other.hashValue) % MOD, this, other);
        }

        public int get(long p) {
            if (p < 0 || p >= length) {
                return -1;
            }
            if (length == 1) {
                return (int)hashValue;
            }
            if (p < first.length) {
                return first.get(p);
            }
            return second.get(p - first.length);
        }

        boolean equals(Hash other) {
            return length == other.length && hashValue == other.hashValue;
        }

        Hash getHash(long l) {
            if (l <= 0) {
                return new Hash(0, 0, null, null);
            }
            if (l >= length) {
                return this;
            }
            if (l <= first.length) {
                return first.getHash(l);
            }
            return first.join(second.getHash(l - first.length));
        }

        public boolean compare(Hash other) {
            long lower = 0;
            long upper = Math.min(length, other.length);
            while (lower < upper) {
                long mider = (lower + upper + 1) >> 1;
                if (getHash(mider).equals(other.getHash(mider))) {
                    lower = mider;
                } else {
                    upper = mider - 1;
                }
            }
            return get(lower) < other.get(lower);
        }
    }

    public String getString(String s, String t, long p) {
        a = s;
        b = t;
        n = a.length();
        m = b.length();
        Hash hashes[][] = new Hash[n + 1][m + 1];
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= m; ++ j) {
                if (i + j > 0) {
                    if (i == 0 || j == 0) {
                        int c = j == 0? a.charAt(i - 1): b.charAt(j - 1);
                        hashes[i][j] = new Hash(1, c, null, null);
                    } else if (hashes[i - 1][j].compare(hashes[i][j - 1])) {
                        hashes[i][j] = hashes[i - 1][j].join(hashes[i][j - 1]);
                    } else {
                        hashes[i][j] = hashes[i][j - 1].join(hashes[i - 1][j]);
                    }
                }
            }
        }
        String result = new String();
        for (int i = 0; i < 50; ++ i) {
            int c = hashes[n][m].get(p + i);
            if (c == -1) {
                break;
            }
            result += (char)c;
        }
        return result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			STableHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				STableHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class STableHarness {
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
			String s                  = "ad";
			String t                  = "cb";
			long pos                  = 0;
			String expected__         = "acbacd";

			return verifyCase(casenum__, expected__, new STable().getString(s, t, pos));
		}
		case 1: {
			String s                  = "fox";
			String t                  = "cat";
			long pos                  = 0;
			String expected__         = "acfcfoacftacfcfocfox";

			return verifyCase(casenum__, expected__, new STable().getString(s, t, pos));
		}
		case 2: {
			String s                  = "Ra6b1t";
			String t                  = "W0lf";
			long pos                  = 66;
			String expected__         = "RWab0RWRWa0RWl0RWRWa6RWa0RWRWa6RWa6RWab0RWRWa6RWa6";

			return verifyCase(casenum__, expected__, new STable().getString(s, t, pos));
		}
		case 3: {
			String s                  = "M0HAXG";
			String t                  = "COFU12";
			long pos                  = 919;
			String expected__         = "MOFU2";

			return verifyCase(casenum__, expected__, new STable().getString(s, t, pos));
		}
		case 4: {
			String s                  = "a0B1c2D3e4F5gHiJkLmN";
			String t                  = "A9b8C7d6EfGhIjKlMn";
			long pos                  = 9876543210L;
			String expected__         = "B10AaB1c0Aa9Aa0AaB0AaB10AaB1c0AaB1c20Aa9Aa0AaB0Aa9";

			return verifyCase(casenum__, expected__, new STable().getString(s, t, pos));
		}
		case 5: {
			String s                  = "TCOR2";
			String t                  = "MEDiUm";
			long pos                  = 350;
			String expected__         = "MTDEMTiCMTEMTDEMTDEMTiDEMTiUCMTEMTCMTOCMTEMTDEMTCM";

			return verifyCase(casenum__, expected__, new STable().getString(s, t, pos));
		}

		// custom cases

/*      case 6: {
			String s                  = ;
			String t                  = ;
			long pos                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new STable().getString(s, t, pos));
		}*/
/*      case 7: {
			String s                  = ;
			String t                  = ;
			long pos                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new STable().getString(s, t, pos));
		}*/
/*      case 8: {
			String s                  = ;
			String t                  = ;
			long pos                  = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new STable().getString(s, t, pos));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
