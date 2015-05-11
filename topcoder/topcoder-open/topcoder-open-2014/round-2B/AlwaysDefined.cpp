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

typedef long long Long;

struct AlwaysDefined {
    std::map <std::pair <int, Long>, Long> map;

    Long count(int w, int d, Long n) {
        if (d == w) {
            return n;
        }
        if (map.count({d, n})) {
            return map[{d, n}];
        }
        Long &result = map[{d, n}];
        for (int r = 1, i = 0; r < w && i < n; r += d, ++ i) {
            int g = std::__gcd(r, w);
            int t = (r / g) * (w / d);
            result += count(w, w / g, (n - i + t - 1) / t);
        }
        return result;
    }

    Long countIntegers(Long l, Long r, int w) {
        return count(w, 1, r) - count(w, 1, l - 1);
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
            long long L               = 10;
            long long R               = 10;
            int W                     = 4;
            long long expected__      = 1;

            clock_t start__           = clock();
            long long received__      = AlwaysDefined().countIntegers(L, R, W);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            long long L               = 1;
            long long R               = 99;
            int W                     = 2;
            long long expected__      = 50;

            clock_t start__           = clock();
            long long received__      = AlwaysDefined().countIntegers(L, R, W);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            long long L               = 1282;
            long long R               = 1410;
            int W                     = 10;
            long long expected__      = 42;

            clock_t start__           = clock();
            long long received__      = AlwaysDefined().countIntegers(L, R, W);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            long long L               = 29195807;
            long long R               = 273209804877LL;
            int W                     = 42;
            long long expected__      = 31415926535LL;

            clock_t start__           = clock();
            long long received__      = AlwaysDefined().countIntegers(L, R, W);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

        case 4: {
            long long L               = 1;
            long long R               = 1000000000000000000LL;
            int W                     = 3000;
            long long expected__      = -1;

            clock_t start__           = clock();
            long long received__      = AlwaysDefined().countIntegers(L, R, W);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
/*      case 5: {
            long long L               = ;
            long long R               = ;
            int W                     = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = AlwaysDefined().countIntegers(L, R, W);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            long long L               = ;
            long long R               = ;
            int W                     = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = AlwaysDefined().countIntegers(L, R, W);
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
