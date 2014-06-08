#include <algorithm>
#include <bitset>
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

struct FibonacciXor {
    static const int N   = 73;
    static const int MOD = (int)1e9 + 7;

    typedef long long       Long;
    typedef std::bitset <N> Bitset;

    Long fibs[N];

    int find(long long a, long long b) {
        fibs[0] = 1;
        fibs[1] = 2;
        for (int i = 2; i < N; ++ i) {
            fibs[i] = fibs[i - 1] + fibs[i - 2];
        }
        memory[1] = 0;
        Bitset mask = solve(b + 1) ^ solve(a);
        int result = 0;
        for (int i = N - 1; i >= 0; -- i) {
            (result *= 2) %= MOD;
            if (mask.test(i)) {
                (result += 1) %= MOD;
            }
        }
        return result;
    }

    Bitset solve(Long n) {
        if (!memory.count(n)) {
            int i = (int)(std::lower_bound(fibs, fibs + N, n) - fibs) - 1;
            Bitset &result = memory[n];
            result ^= solve(n - fibs[i]) ^ solve(fibs[i]);
            if (n - fibs[i] & 1) {
                result.flip(i);
            }
        }
        return memory[n];
    }

    std::map <Long, Bitset> memory;
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

    int verify_case(int casenum, const int &expected, const int &received, clock_t elapsed) {
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
            long long A               = 1;
            long long B               = 2;
            int expected__            = 3;

            clock_t start__           = clock();
            int received__            = FibonacciXor().find(A, B);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            long long A               = 3;
            long long B               = 10;
            int expected__            = 25;

            clock_t start__           = clock();
            int received__            = FibonacciXor().find(A, B);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            long long A               = 1;
            long long B               = 1000000000000000LL;
            int expected__            = 780431495;

            clock_t start__           = clock();
            int received__            = FibonacciXor().find(A, B);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 3: {
            long long A               = ;
            long long B               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = FibonacciXor().find(A, B);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 4: {
            long long A               = ;
            long long B               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = FibonacciXor().find(A, B);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 5: {
            long long A               = ;
            long long B               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = FibonacciXor().find(A, B);
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
