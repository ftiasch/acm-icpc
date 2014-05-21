#include <algorithm>
#include <cassert>
#include <climits>
#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <deque>
#include <iostream>
#include <list>
#include <map>
#include <queue>
#include <set>
#include <string>
#include <utility>
#include <vector>

#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) (int)((v).size())
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::cerr;
using std::endl;
using std::vector;
using std::string;

const int M = 1000000;

bool is_prime[M + 1];

struct HolyNumbers {
    std::vector <int> primes;

    long long solve(int i, long long n) {
        if (i >= primes.size() || n < primes[i]) {
            return 1;
        }
        long long p = primes[i];
        if (p * p > n) {
            return std::upper_bound(ALL(primes), n) - primes.begin() - i + 1;
        }
        long long result = solve(i + 1, n);
        long long now = p;
        while (true) {
            result += solve(i + 1, n / now);
            if (p * p > n / now) {
                break;
            }
            now *= p * p;
        }
        return result;
    }

    long long count(long long n, int m) {
        memset(is_prime, true, sizeof is_prime);
        for (int p = 2; p <= m; ++ p) {
            if (is_prime[p]) {
                primes.push_back(p);
            }
            for (int i = 0; i < SIZE(primes) && p * primes[i] <= m; ++ i) {
                is_prime[p * primes[i]] = false;
                if (p % primes[i] == 0) {
                    break;
                }
            }
        }
        return solve(0, n);
    }
};

// BEGIN CUT HERE
namespace moj_harness {
    int run_test_case(int);
    void run_test(int casenum = -1, bool quiet = false) {
        if (casenum != -1) {
            if (run_test_case(casenum) == -1 && !quiet) {
                cerr << "Illegal input! Test case " << casenum << " does not exist." << endl;
            }
            return;
        }

        int correct = 0, total = 0;
        for (int i=0;; ++i) {
            int x = run_test_case(i);
            if (x == -1) {
                if (i >= 100) break;
                continue;
            }
            correct += x;
            ++total;
        }

        if (total == 0) {
            cerr << "No test cases run." << endl;
        } else if (correct < total) {
            cerr << "Some cases FAILED (passed " << correct << " of " << total << ")." << endl;
        } else {
            cerr << "All " << total << " tests passed!" << endl;
        }
    }

    int verify_case(int casenum, const long long &expected, const long long &received, clock_t elapsed) {
        cerr << "Example " << casenum << "... ";

        string verdict;
        vector<string> info;
        char buf[100];

        if (elapsed > CLOCKS_PER_SEC / 200) {
            sprintf(buf, "time %.2fs", elapsed * (1.0/CLOCKS_PER_SEC));
            info.push_back(buf);
        }

        if (expected == received) {
            verdict = "PASSED";
        } else {
            verdict = "FAILED";
        }

        cerr << verdict;
        if (!info.empty()) {
            cerr << " (";
            for (int i=0; i<(int)info.size(); ++i) {
                if (i > 0) cerr << ", ";
                cerr << info[i];
            }
            cerr << ")";
        }
        cerr << endl;

        if (verdict == "FAILED") {
            cerr << "    Expected: " << expected << endl;
            cerr << "    Received: " << received << endl;
        }

        return verdict == "PASSED";
    }

    int run_test_case(int casenum__) {
        switch (casenum__) {
        case 0: {
            long long upTo            = 10;
            int maximalPrime          = 100;
            long long expected__      = 8;

            clock_t start__           = clock();
            long long received__      = HolyNumbers().count(upTo, maximalPrime);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            long long upTo            = 10;
            int maximalPrime          = 3;
            long long expected__      = 5;

            clock_t start__           = clock();
            long long received__      = HolyNumbers().count(upTo, maximalPrime);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            long long upTo            = 123;
            int maximalPrime          = 12;
            long long expected__      = 32;

            clock_t start__           = clock();
            long long received__      = HolyNumbers().count(upTo, maximalPrime);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            long long upTo            = 123;
            int maximalPrime          = 456;
            long long expected__      = 88;

            clock_t start__           = clock();
            long long received__      = HolyNumbers().count(upTo, maximalPrime);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            long long upTo            = 123456789;
            int maximalPrime          = 12345;
            long long expected__      = 25994500;

            clock_t start__           = clock();
            long long received__      = HolyNumbers().count(upTo, maximalPrime);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 5: {
            long long upTo            = ;
            int maximalPrime          = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = HolyNumbers().count(upTo, maximalPrime);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            long long upTo            = ;
            int maximalPrime          = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = HolyNumbers().count(upTo, maximalPrime);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            long long upTo            = ;
            int maximalPrime          = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = HolyNumbers().count(upTo, maximalPrime);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
        default:
            return -1;
        }
    }
}


int main(int argc, char *argv[]) {
    if (argc == 1) {
        moj_harness::run_test();
    } else {
        for (int i=1; i<argc; ++i)
            moj_harness::run_test(atoi(argv[i]));
    }
}
// END CUT HERE
