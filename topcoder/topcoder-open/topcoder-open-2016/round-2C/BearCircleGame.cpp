#include <cassert>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <functional>
#include <string>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::string;
using std::vector;

struct BearCircleGame
{
    static const int TWO = (int)5e8 + 4;
    static const int MOD = (int)1e9 + 7;

    int inverse(int a)
    {
        assert(a);
        return a == 1 ? 1 : (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD;
    }

    int winProbability(int N, int delta)
    {
        std::vector<int> probability(N, 1);
        for (int n = 2; n <= N; ++ n) {
            std::vector<int> add(n), next(n);
            for (int limak = 0; limak < n; ++ limak) {
                next[limak] = (limak + n - delta % n) % n;
                add[limak] = (long long)TWO * probability[next[limak] % (n - 1)] % MOD;
                if (next[limak] == n - 1) {
                    add[limak] = 0;
                }
            }
            std::vector<bool> visited(n);
            for (int i = 0; i < n; ++ i) {
                if (visited[i]) {
                    continue;
                }
                std::vector<int> cycle;
                for (int j = i; !visited[j]; j = next[j]) {
                    visited[j] = true;
                    cycle.push_back(j);
                }
                int k1 = 1;
                int k0 = 0;
                for (int j = (int)cycle.size() - 1; j >= 0; -- j) {
                    k1 = (long long)k1 * TWO % MOD;
                    k0 = ((long long)k0 * TWO + add[cycle[j]]) % MOD;
                }
                int x = probability[i] = (long long)k0 * inverse((1 + MOD - k1) % MOD) % MOD;
                for (int j = (int)cycle.size() - 1; j >= 1; -- j) {
                    probability[cycle[j]] = x = ((long long)TWO * x + add[cycle[j]]) % MOD;
                }
            }
        }
        return probability[0];
    }
};

// BEGIN CUT HERE
//{{{ TESTCODE
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
            int n                     = 2;
            int k                     = 1;
            int expected__            = 333333336;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int n                     = 2;
            int k                     = 2;
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int n                     = 3;
            int k                     = 2;
            int expected__            = 142857144;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int n                     = 3;
            int k                     = 1;
            int expected__            = 238095240;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int n                     = 4;
            int k                     = 4;
            int expected__            = 142857144;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int n                     = 5;
            int k                     = 1000000000;
            int expected__            = 142857144;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 6: {
            int n                     = 2000;
            int k                     = 123;
            int expected__            = 429232785;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 7: {
            int n                     = 1987;
            int k                     = 987654321;
            int expected__            = 623410299;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

        case 8: {
            int n                     = 2000;
            int k                     = 123;
            int expected__            = 429232785;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
/*      case 9: {
            int n                     = ;
            int k                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 10: {
            int n                     = ;
            int k                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = BearCircleGame().winProbability(n, k);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
        default:
            return -1;
        }
    }
}

//}}}

//{{{ DEFAULTMAIN
int main(int argc, char *argv[]) {
    if (argc == 1) {
        moj_harness::run_test();
    } else {
        for (int i=1; i<argc; ++i)
            moj_harness::run_test(atoi(argv[i]));
    }
}
//}}}
// END CUT HERE
