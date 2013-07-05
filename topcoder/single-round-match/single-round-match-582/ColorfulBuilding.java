import java.math.*;
import java.util.*;

public class ColorfulBuilding {
    final static int MOD = (int)1e9 + 9;

    long inverse(long a) {
        return a == 1 ? 1 : (MOD - MOD / a) * inverse(MOD % a) % MOD;
    }

    long[] rowSum;
    long[][] sum;

    void add(int count, int color, long value) {
        rowSum[count] += value;
        rowSum[count] %= MOD;
        sum[count][color] += value;
        sum[count][color] %= MOD;
    }

    long same(int count, int color) {
        return sum[count][color];
    }

    long diff(int count, int color) {
        return (rowSum[count] + MOD - sum[count][color]) % MOD;
    }

    public int count(String[] color1, String[] color2, int l) {
        String c1 = concate(color1);
        String c2 = concate(color2);
        int n = c1.length();
        int[] color = new int[n];
        Arrays.fill(color, -1);
        int m = 0;
        for (int i = 0; i < n; ++ i) {
            if (color[i] == -1) {
                for (int j = 0; j < n; ++ j) {
                    if (c1.charAt(i) == c1.charAt(j) && c2.charAt(i) == c2.charAt(j)) {
                        color[j] = m;
                    }
                }
                m ++;
            }
        }
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        long[] invFact = new long[n + 1];
        for (int i = 0; i <= n; ++ i) {
            invFact[i] = inverse(fact[i]);
        }
        long[] ways = new long[l + 1];
        ways[1] = 1;
        long answer = ways[l] * fact[n - 1] % MOD;
        sum = new long[l + 1][m];
        rowSum = new long[l + 1];
        add(1, color[n - 1], ways[1] * invFact[0] % MOD);
        for (int i = 2; i <= n; ++ i) {
            ways = new long[l + 1];
            for (int j = 1; j <= l; ++ j) {
                ways[j] = (same(j, color[n - i]) + diff(j - 1, color[n - i])) % MOD * fact[i - 2] % MOD;
            }
            answer += ways[l] * fact[n - 1] % MOD * invFact[i - 1] % MOD;
            answer %= MOD;
            for (int j = 1; j <= l; ++ j) {
                add(j, color[n - i], ways[j] * invFact[i - 1] % MOD);
            }
        }
        return (int)answer;
    }

    String concate(String[] strings) {
        StringBuffer buffer = new StringBuffer("");
        for (String string : strings) {
            buffer.append(string);
        }
        return buffer.toString();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			ColorfulBuildingHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ColorfulBuildingHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ColorfulBuildingHarness {
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
			String[] color1           = {"aaba"};
			String[] color2           = {"aaaa"};
			int L                     = 3;
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new ColorfulBuilding().count(color1, color2, L));
		}
		case 1: {
			String[] color1           = {"aaba"};
			String[] color2           = {"aaba"};
			int L                     = 4;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new ColorfulBuilding().count(color1, color2, L));
		}
		case 2: {
			String[] color1           = {"ab", "ba", "a", "aab"};
			String[] color2           = {"bb", "ba", "a", "aba"};
			int L                     = 5;
			int expected__            = 432;

			return verifyCase(casenum__, expected__, new ColorfulBuilding().count(color1, color2, L));
		}
		case 3: {
			String[] color1           = {"xxxxxxxxxxxxxxxxxxxx",
 "xxxxxxxxxxxxxxxxxxxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxOOxxxxxxxxxxxxxxxx",
 "xxOOxxxxxxxxxxxxxxxx",
 "xxOOxxxxxxxxxxxxxxxx",
 "xxOOxxxxxxxxxxxxxxxx",
 "xxOOxxxxxxxxxxxxxxxx",
 "xxOOxxxxxxxxxxxxxxxx",
 "xxOOxxxxxxxxxxxxxxxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxxxxxxxxxxxxxxxOOxx",
 "xxxxxxxxxxxxxxxxOOxx",
 "xxxxxxxxxxxxxxxxOOxx",
 "xxxxxxxxxxxxxxxxOOxx",
 "xxxxxxxxxxxxxxxxOOxx",
 "xxxxxxxxxxxxxxxxOOxx",
 "xxxxxxxxxxxxxxxxOOxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxxxxxxxxxxxxxxxxxxx",
 "xxxxxxxxxxxxxxxxxxxx"};
			String[] color2           = {"xxxxxxxxxxxxxxxxxxxx",
 "xxxxxxxxxxxxxxxxxxxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOxxxxxxxxxxxxOOxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxOOOOOOOOOOOOOOOOxx",
 "xxxxxxxxxxxxxxxxxxxx",
 "xxxxxxxxxxxxxxxxxxxx"};
			int L                     = 58;
			int expected__            = 619787617;

			return verifyCase(casenum__, expected__, new ColorfulBuilding().count(color1, color2, L));
		}
		case 4: {
			String[] color1           = {"SJXcabKTWeUXhwxGixFepQaQlFxrAedNAtVP",
 "gorBIkcTlOFbLDbFeElYAyAqszQdMbpxSRdE",
 "SQVHPehlNtesbJDflyGxMqvgzjXisRnqDcQO",
 "pIAEBwbmpPWCooQAujbGXFGLvXxTmExLARkf",
 "AFnWyWKVObfxDGCjTxdZaObgwdxlPKtIxaAd",
 "uznMpJVNjAofbHJjOrZeSHgSagOCUMGbvkVR",
 "LBRrDbTAyKfVnedKiRfzgRzECpcsziqaTwdo",
 "JrJHvsEVVGDkNVGqLbpxyLDPloBuNDQKnReI",
 "SSYpbjKHSCnQhuyYrVauWDHDyhAoGyecrZMv",
 "UdetQfWEUWHHuAxRSdkJOOJSixKpQXpCFZHO",
 "KXVsQbuQtIgsULOMsTvPFNUqkBldMTLCipYK",
 "hoXConjnDWQkZVtyZlwSedvdVrNWqQhGUClQ",
 "TpsvvyoXsXmQpBAGGVDrXCkodoRHQZmpoQDW",
 "csiJspzTqeFBRmPgeEtTAzfrfCGlTZqcPuyO",
 "vsPDVBJVaJmUAtDdcsKoUkPEbDmAwtZKwjjP",
 "MOfoMhMiKIvGQoeIJXHzyClWRtRuKXMqxUAF",
 "KyyUCkRBjsYvmPFFEGBqqVhIUdtvIyyLacfu",
 "BfuwfSajSlcuTzhMufHSQLudvGEGlyHsEmBD",
 "PLpIXZkdyXveTMfSvqnDGKWOZrTBMUIlZrqF",
 "dzVMCqrSLbanRJTYpDJNHAOLPSzmvSEPQJYm",
 "rAjRkrSjouJyFaCSPPLYSzqDmMoADyWAbobJ",
 "eOCBrJNoyFnGpXpxiExXcoOHnVsaEPXhPfLe",
 "XMjRksnxWssPEINhdhbiLBSYpCLtwNshFjXS",
 "HnnDeUAbuswsgsYQuAaXySLkFYUwFXwYTreM",
 "uqLnwOEGbwZZDgAAWEdLRZxFlogDmlhjhgHM",
 "NcfaQsgPQfirkYDRFrLQpySmBGfRHumKULZf",
 "mOpmmgclsxRzXskEywfryqCRyATNoJwnlHiD",
 "AnoKoKAjrasttjNlHCROnvTJMhEHlVPVoVMo",
 "yegLyIuRkkENFAjwzDoPLKjgUHHEkfzYDIpo",
 "EcxRGGfuaBXUFXkSxctJWOLmmVbvoMkWtTvV",
 "nBMkOBHgaltEVzfyGxseGhmBscfGIbxFbqRn",
 "GvkEcLtqdOofGtsbWDafVMbNdJxsffKDzSiR",
 "jhZQUVzTzalrZcebvyqPWtOUUyniBKTWdyLi",
 "ODJLQPMdjDdTlxrfGsNmBfeGYjzeXApqKDhJ",
 "vlJkWMzdVQujKdLViQClOrJXMTBkuZEasFgg",
 "FAsbuzrLVIgaryqXBfuBRAbReleXSSgEKSvt"};
			String[] color2           = {"XAhlUHtfjOpOlQlowWppQcodMGUiqoLobxnD",
 "IDzGlKrHiVGdBjZxIcoxjwagbWieKvUwZrjg",
 "SkpkTVeIFeoNQzUUgmYAYskKONNZdgXxSiWd",
 "rXBGhTmqnvxkmqiutoDzPusDiOUggEFXGCzm",
 "fzrxVuTbFXunGbeEavjshmrIRlDorTuISCxn",
 "LsvIaxTkOBjcskiekmrKNNFEXqnfMNXLWqqu",
 "ekzxGIlbJPVEQPYAbTuMtZKsCiubDXDiBVzU",
 "hZuOCJxvBOYENGSFcUiKKAKfCdebutVmnyvB",
 "UveNkKRQmHnedrROQOpHJfrHjwcNLUShlDbn",
 "ptUkvbaGDryHRkYfHKLkSgVpjWCEcgazyxGK",
 "nVPYEqTTJsRPjzjfdOiULhnZPFeNcnbXaQlk",
 "IPXBXRhMQIkxpygsgbcRfMuvIcuzUPPHGOWX",
 "yWWlNyEyqZSIOXBFAybIuFpVqpvmKRaRFrAE",
 "EvBJVtHvKhjrFcmtpdBbFTdTVtXXZQKAglKT",
 "bCVjHzUvyINFkxXageZQMzCyNhcifACdJVDh",
 "lZITYcDSeIbLweyFtoMAfOQyBNupKlhcNpgo",
 "BduslNrJdWOUukYFFidEkMFaghfofpxVgvJd",
 "YrJpDZKqdjEPzdLsOQEdkcrBfNHPemXHokCW",
 "GjeUKSGjDlgKTyUGNrMQbBLxRUcgrWpkAwOD",
 "wgxTcswqdJHaDugNIRMvrhBsdDaJAssVbSRW",
 "qmVmqFEpvgGioMXDSFqEoQcDOAaUoGPEovSO",
 "KrukPlpfOhawaovCfteTSCIdLMrtImVtiMyQ",
 "ykwmxHsKMFzFHwcbyyedLvhZPnaNGqJMMCxd",
 "HUNYCXjNLQIFCLLGpCXHBCHLTxLynBxnHFbx",
 "uwjzbNbJepVFgMPUXVirxYHzExquBEtPmKju",
 "xXAxAbJePyUsVHeLytDvAxBGMRtnvCEiZZqe",
 "xMkQoIVxWPXPgaOYmDjTOXiMImVdzojERNxS",
 "dwICFwMAmdOIUxyAdXdshasnzwyhfnVWVqZJ",
 "etypXNVvSTEQvriGBZdSGmDEHhvpSqkFklCS",
 "YkxpFBCRYUueRcKaJUXVdaMoYMYEooPQVMOr",
 "DTrexDqclZNKdPuTRFHualJSFziCLPCZjpxo",
 "TfEijcAsSJPikkmBSbXMqYHAhPTcpcKVSkIX",
 "xKXHYPYMJxFpSbxltDKYuRiTkOLxpQKnXZPs",
 "YFYuvuYHfpFJcrLNIdlNfBxRnWdppsdalBkx",
 "NFTysBvNFjejdnlhRTclbcfGipNCxpFEOriY",
 "thkgVflJYmbUYbIlafNUMGePQWiZyYzYXvUR"}
;
			int L                     = 1200;
			int expected__            = 396065426;

			return verifyCase(casenum__, expected__, new ColorfulBuilding().count(color1, color2, L));
		}

		// custom cases

/*      case 5: {
			String[] color1           = ;
			String[] color2           = ;
			int L                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ColorfulBuilding().count(color1, color2, L));
		}*/
/*      case 6: {
			String[] color1           = ;
			String[] color2           = ;
			int L                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ColorfulBuilding().count(color1, color2, L));
		}*/
/*      case 7: {
			String[] color1           = ;
			String[] color2           = ;
			int L                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ColorfulBuilding().count(color1, color2, L));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
