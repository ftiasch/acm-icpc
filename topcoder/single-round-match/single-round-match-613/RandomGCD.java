import java.math.*;
import java.util.*;

public class RandomGCD {
    static int MOD = (int)1e9 + 7;

    public int countTuples(int n, int k, int low, int high) {
        high /= k;
        low = (low + k - 1) / k;
        int length = high - low + 1;
        int[] numbers = new int[length];
        for (int i = 0; i < length; ++ i) {
            numbers[i] = low + i;
        }
        List <List <Integer> > factors = new ArrayList <List <Integer> >();
        for (int i = 0; i < length; ++ i) {
            factors.add(new ArrayList <Integer>());
        }
        int m = 0;
        while (m * m <= high) {
            m ++;
        }
        boolean[] isPrime = new boolean[m];
        Arrays.fill(isPrime, true);
        List <Integer> primes = new ArrayList <Integer>();
        for (int d = 2; d < m; ++ d) {
            if (isPrime[d]) {
                primes.add(d);
            }
            for (int i = 0; i < (int)primes.size() & d * primes.get(i) < m; ++ i) {
                isPrime[d * primes.get(i)] = false;
                if (d % primes.get(i) == 0) {
                    break;
                }
            }
        }
        for (int p : primes) {
            for (int i = (low + p - 1) / p * p - low; i < length; i += p) {
                if (numbers[i] % p == 0) {
                    numbers[i] /= p;
                    factors.get(i).add(p);
                }
            }
        }
        memory = new HashMap <Integer, Integer>();
        int result = 0;
        Set <Integer> used = new HashSet <Integer>();
        for (int i = 0; i < length; ++ i) {
            for (int p : factors.get(i)) {
                while (numbers[i] % p == 0) {
                    numbers[i] /= p;
                }
            }
            if (numbers[i] > 1) {
                factors.get(i).add(numbers[i]);
            }
            result += recur(n, low, high, used, factors.get(i), 0, 1, 1);
            result %= MOD;
        }
        return (result + MOD) % MOD;
    }

    int recur(int n, int low, int high, Set <Integer> used, List <Integer> factors, int i, int d, int miu) {
        if (i < factors.size()) {
            return (recur(n, low, high, used, factors, i + 1, d, miu)
                  + recur(n, low, high, used, factors, i + 1, d * factors.get(i), -miu)) % MOD;
        } else if (!used.contains(d)) {
            used.add(d);
            return miu * pow(high / d - (low - 1) / d, n);
        } else {
            return 0;
        }
    }

    Map <Integer, Integer> memory;

    int pow(int a, int n) {
        if (memory.containsKey(a)) {
            return memory.get(a);
        }
        int result = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                result = (int)((long)result * a % MOD);
            }
            a = (int)((long)a * a % MOD);
            n >>= 1;
        }
        memory.put(a, result);
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            RandomGCDHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                RandomGCDHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class RandomGCDHarness {
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
            int N                     = 2;
            int K                     = 2;
            int low                   = 2;
            int high                  = 4;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new RandomGCD().countTuples(N, K, low, high));
        }
        case 1: {
            int N                     = 2;
            int K                     = 100;
            int low                   = 2;
            int high                  = 4;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new RandomGCD().countTuples(N, K, low, high));
        }
        case 2: {
            int N                     = 1;
            int K                     = 5;
            int low                   = 5;
            int high                  = 5;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new RandomGCD().countTuples(N, K, low, high));
        }
        case 3: {
            int N                     = 73824;
            int K                     = 17347;
            int low                   = 9293482;
            int high                  = 9313482;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new RandomGCD().countTuples(N, K, low, high));
        }
        case 4: {
            int N                     = 222;
            int K                     = 222;
            int low                   = 222;
            int high                  = 22222;
            int expected__            = 339886855;

            return verifyCase(casenum__, expected__, new RandomGCD().countTuples(N, K, low, high));
        }

        // custom cases

        case 5: {
            int N                     = 1000000000;
            int K                     = 1;
            int low                   = 1000000000 - 100000 + 1;
            int high                  = 1000000000;
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new RandomGCD().countTuples(N, K, low, high));
        }
/*      case 6: {
            int N                     = ;
            int K                     = ;
            int low                   = ;
            int high                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new RandomGCD().countTuples(N, K, low, high));
        }*/
/*      case 7: {
            int N                     = ;
            int K                     = ;
            int low                   = ;
            int high                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new RandomGCD().countTuples(N, K, low, high));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
