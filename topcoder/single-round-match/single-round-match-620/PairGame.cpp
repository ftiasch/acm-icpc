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
#include <unordered_set>
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

struct PairGame {
    int maxSum(int a, int b, int c, int d) {
        std::set <std::pair <int, int>> set;
        while (true) {
            set.insert(std::make_pair(a, b));
            if (a == b) {
                break;
            }
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        int result = -1;
        while (true) {
            if (set.count(std::make_pair(c, d))) {
                result = std::max(result, c + d);
            }
            if (c == d) {
                break;
            }
            if (c > d) {
                c -= d;
            } else {
                d -= c;
            }
        }
        return result;
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
            int a                     = 1;
            int b                     = 2;
            int c                     = 2;
            int d                     = 1;
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = PairGame().maxSum(a, b, c, d);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int a                     = 15;
            int b                     = 4;
            int c                     = 10;
            int d                     = 7;
            int expected__            = 7;

            clock_t start__           = clock();
            int received__            = PairGame().maxSum(a, b, c, d);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int a                     = 1;
            int b                     = 1;
            int c                     = 10;
            int d                     = 10;
            int expected__            = -1;

            clock_t start__           = clock();
            int received__            = PairGame().maxSum(a, b, c, d);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int a                     = 1000;
            int b                     = 1001;
            int c                     = 2000;
            int d                     = 2001;
            int expected__            = 1001;

            clock_t start__           = clock();
            int received__            = PairGame().maxSum(a, b, c, d);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int a                     = 10944;
            int b                     = 17928;
            int c                     = 7704;
            int d                     = 21888;
            int expected__            = 144;

            clock_t start__           = clock();
            int received__            = PairGame().maxSum(a, b, c, d);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int a                     = 1;
            int b                     = 1;
            int c                     = 1;
            int d                     = 1;
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = PairGame().maxSum(a, b, c, d);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            int a                     = ;
            int b                     = ;
            int c                     = ;
            int d                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PairGame().maxSum(a, b, c, d);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int a                     = ;
            int b                     = ;
            int c                     = ;
            int d                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PairGame().maxSum(a, b, c, d);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int a                     = ;
            int b                     = ;
            int c                     = ;
            int d                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PairGame().maxSum(a, b, c, d);
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
