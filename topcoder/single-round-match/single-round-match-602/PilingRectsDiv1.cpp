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

const int N = 200000;

int n, x[N], y[N], order[N], prefix_min[N + 1];

bool by_x(int i, int j) {
    return x[i] < x[j] || x[i] == x[j] && i < j;
}

struct PilingRectsDiv1 {
    long long solve() {
        for (int i = 0; i < n * 2; ++ i) {
            if (x[i] < y[i]) {
                std::swap(x[i], y[i]);
            }
            order[i] = i;
        }
        std::sort(order, order + n * 2, by_x);
        int argmin = 0;
        for (int i = 0; i < n * 2; ++ i) {
            if (y[order[argmin]] >= y[order[i]]) {
                argmin = i;
            }
        }
        prefix_min[1] = y[order[0]];
        for (int i = 1; i < n * 2; ++ i) {
            prefix_min[i + 1] = std::min(prefix_min[i], y[order[i]]);
        }
        long long result = 0;
        std::priority_queue <int> buffer;
        int now_min = INT_MAX;
        for (int i = n * 2 - 1; i >= 1; -- i) {
            buffer.push(y[order[i]]);
            while (SIZE(buffer) > n) {
                int x = buffer.top();
                buffer.pop();
                now_min = std::min(now_min, x);
            }
            if (i <= argmin && i <= n) {
                result = std::max(result, (long long)x[order[i]] * y[order[argmin]]
                        + (long long)x[order[0]] * std::min(now_min, prefix_min[i]));
            }
        }
        while (!buffer.empty()) {
            buffer.pop();
        }
        for (int i = n * 2 - 1; i >= 1; -- i) {
            if (i == argmin) {
                continue;
            }
            buffer.push(- y[order[i]]);
            while (SIZE(buffer) > n) {
                buffer.pop();
            }
            if (SIZE(buffer) >= n) {
                result = std::max(result, (long long)x[order[0]] * y[order[argmin]]
                        + (long long)x[order[i]] * -buffer.top());
            }
        }
        return result;
    }

    long long getmax(int n, vector <int> xs, vector <int> ys, int xa, int xb, int xc, int ya, int yb, int yc) {
        ::n = n;
        for (int i = 0; i < SIZE(xs); ++ i) {
            x[i] = xs[i];
            y[i] = ys[i];
        }
        for (int i = SIZE(xs); i < n * 2; ++ i) {
            x[i] = ((long long)x[i - 1] * xa + xb) % xc + 1;
            y[i] = ((long long)y[i - 1] * ya + yb) % yc + 1;
        }
        return solve();
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
            int N                     = 2;
            int XS[]                  = {1,2,3,4};
            int YS[]                  = {10,5,3,5};
            int XA                    = 0;
            int XB                    = 0;
            int XC                    = 1;
            int YA                    = 0;
            int YB                    = 0;
            int YC                    = 1;
            long long expected__      = 14;

            clock_t start__           = clock();
            long long received__      = PilingRectsDiv1().getmax(N, vector <int>(XS, XS + (sizeof XS / sizeof XS[0])), vector <int>(YS, YS + (sizeof YS / sizeof YS[0])), XA, XB, XC, YA, YB, YC);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int N                     = 2;
            int XS[]                  = {7,7};
            int YS[]                  = {4,4};
            int XA                    = 9;
            int XB                    = 0;
            int XC                    = 10;
            int YA                    = 2;
            int YB                    = 7;
            int YC                    = 9;
            long long expected__      = 56;

            clock_t start__           = clock();
            long long received__      = PilingRectsDiv1().getmax(N, vector <int>(XS, XS + (sizeof XS / sizeof XS[0])), vector <int>(YS, YS + (sizeof YS / sizeof YS[0])), XA, XB, XC, YA, YB, YC);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int N                     = 3;
            int XS[]                  = {5,6,9,10,4,7};
            int YS[]                  = {9,5,8,6,8,7};
            int XA                    = 0;
            int XB                    = 0;
            int XC                    = 1;
            int YA                    = 0;
            int YB                    = 0;
            int YC                    = 1;
            long long expected__      = 69;

            clock_t start__           = clock();
            long long received__      = PilingRectsDiv1().getmax(N, vector <int>(XS, XS + (sizeof XS / sizeof XS[0])), vector <int>(YS, YS + (sizeof YS / sizeof YS[0])), XA, XB, XC, YA, YB, YC);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int N                     = 10000;
            int XS[]                  = {1000000000};
            int YS[]                  = {1000000000};
            int XA                    = 93827162;
            int XB                    = 91238127;
            int XC                    = 98231267;
            int YA                    = 92138287;
            int YB                    = 98563732;
            int YC                    = 99381279;
            long long expected__      = 1240119561532788LL;

            clock_t start__           = clock();
            long long received__      = PilingRectsDiv1().getmax(N, vector <int>(XS, XS + (sizeof XS / sizeof XS[0])), vector <int>(YS, YS + (sizeof YS / sizeof YS[0])), XA, XB, XC, YA, YB, YC);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int N                     = 5;
            int XS[]                  = {375,571,973,896,460,930,583,530,657,590};
            int YS[]                  = {284,37,994,699,941,745,166,884,270,708};
            int XA                    = 428;
            int XB                    = 1000;
            int XC                    = 788;
            int YA                    = 851;
            int YB                    = 125;
            int YC                    = 894;
            long long expected__      = 420515;

            clock_t start__           = clock();
            long long received__      = PilingRectsDiv1().getmax(N, vector <int>(XS, XS + (sizeof XS / sizeof XS[0])), vector <int>(YS, YS + (sizeof YS / sizeof YS[0])), XA, XB, XC, YA, YB, YC);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int N                     = 34734;
            int XS[]                  = {898,579,465,665,875,391,583,901,326,133,656,562,701,707,492,617,455,441,382,952,689,656,334,877,120,318,497,25,569,962,845,562,632,898,882,498,595,889,382,507,791,548,810,606,105,869,569,970,322,974};
            int YS[]                  = {83,18,492,27,339,622,851,915,464,347,287,210,282,166,976,167,968,769,14,383,454,983,319,165,185,315,795,851,124,287,846,745,19,639,894,828,540,420,147,452,501,670,466,554,166,1,446,808,508,172};
            int XA                    = 569;
            int XB                    = 185;
            int XC                    = 285;
            int YA                    = 741;
            int YB                    = 304;
            int YC                    = 296;
            long long expected__      = 10050;

            clock_t start__           = clock();
            long long received__      = PilingRectsDiv1().getmax(N, vector <int>(XS, XS + (sizeof XS / sizeof XS[0])), vector <int>(YS, YS + (sizeof YS / sizeof YS[0])), XA, XB, XC, YA, YB, YC);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            int N                     = ;
            int XS[]                  = ;
            int YS[]                  = ;
            int XA                    = ;
            int XB                    = ;
            int XC                    = ;
            int YA                    = ;
            int YB                    = ;
            int YC                    = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = PilingRectsDiv1().getmax(N, vector <int>(XS, XS + (sizeof XS / sizeof XS[0])), vector <int>(YS, YS + (sizeof YS / sizeof YS[0])), XA, XB, XC, YA, YB, YC);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int N                     = ;
            int XS[]                  = ;
            int YS[]                  = ;
            int XA                    = ;
            int XB                    = ;
            int XC                    = ;
            int YA                    = ;
            int YB                    = ;
            int YC                    = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = PilingRectsDiv1().getmax(N, vector <int>(XS, XS + (sizeof XS / sizeof XS[0])), vector <int>(YS, YS + (sizeof YS / sizeof YS[0])), XA, XB, XC, YA, YB, YC);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int N                     = ;
            int XS[]                  = ;
            int YS[]                  = ;
            int XA                    = ;
            int XB                    = ;
            int XC                    = ;
            int YA                    = ;
            int YB                    = ;
            int YC                    = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = PilingRectsDiv1().getmax(N, vector <int>(XS, XS + (sizeof XS / sizeof XS[0])), vector <int>(YS, YS + (sizeof YS / sizeof YS[0])), XA, XB, XC, YA, YB, YC);
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
