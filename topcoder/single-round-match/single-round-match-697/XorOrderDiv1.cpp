#include <algorithm>
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

struct XorOrderDiv1
{
    static const int MOD = 1e9 + 7;

    void update(int& x, int a)
    {
        x += a;
        if (x >= MOD) {
             x-= MOD;
        }
    }

    int get(int m, int n, int a0, int b)
    {
        std::vector<int> a(n);
        for (int i = 0; i < n; ++ i) {
            a[i] = (a0 + 1LL * b * i) % (1 << m);
        }
        std::sort(ALL(a));
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            int low = 0;
            int high = n;
            int q = 0;
            int sum = 0;
            for (int j = m - 1; j >= 0; -- j) {
                int count = 0;
                int prefix = a[i] & ~((1 << j + 1) - 1);
                int middle = std::lower_bound(a.begin() + low, a.begin() + high, prefix + (1 << j)) - a.begin();
                if (a[i] >> j & 1) {
                    count = middle - low;
                    low = middle;
                } else {
                    count = high - middle;
                    high = middle;
                }
                update(q, 1LL * sum * count % MOD * (1 << m - 1) % MOD);
                update(q, 1LL * count * count % MOD * (1 << m - 1) % MOD);
                update(sum, count);
            }
            result ^= q;
        }
        return result;
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
            int m                     = 2;
            int n                     = 3;
            int a0                    = 0;
            int b                     = 1;
            int expected__            = 8;

            clock_t start__           = clock();
            int received__            = XorOrderDiv1().get(m, n, a0, b);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int m                     = 3;
            int n                     = 5;
            int a0                    = 1;
            int b                     = 3;
            int expected__            = 48;

            clock_t start__           = clock();
            int received__            = XorOrderDiv1().get(m, n, a0, b);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int m                     = 16;
            int n                     = 100;
            int a0                    = 41;
            int b                     = 5;
            int expected__            = 523436032;

            clock_t start__           = clock();
            int received__            = XorOrderDiv1().get(m, n, a0, b);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int m                     = 30;
            int n                     = 200000;
            int a0                    = 399;
            int b                     = 18082016;
            int expected__            = 408585698;

            clock_t start__           = clock();
            int received__            = XorOrderDiv1().get(m, n, a0, b);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 4: {
            int m                     = ;
            int n                     = ;
            int a0                    = ;
            int b                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = XorOrderDiv1().get(m, n, a0, b);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 5: {
            int m                     = ;
            int n                     = ;
            int a0                    = ;
            int b                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = XorOrderDiv1().get(m, n, a0, b);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int m                     = ;
            int n                     = ;
            int a0                    = ;
            int b                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = XorOrderDiv1().get(m, n, a0, b);
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
