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

const int N   = 200;
const int MOD = (int)1e9 + 7;

int sum[N + 1], ways[2][N + 1][N + 1];

struct AlienAndPermutation {
    int getNumber(std::vector <int> p, int m) {
        int n = SIZE(p);
        memset(ways, 0, sizeof(ways));
        ways[0][0][0] = 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j <= m; ++ j) {
                for (int k = 0; k <= n; ++ k) {
                    ways[i + 1 & 1][j][k] = ways[i & 1][j][k];
                }
            }
            int begin = i;
            while (begin - 1 >= 0 && p[begin - 1] < p[i]) {
                begin --;
            }
            int end = i;
            while (end + 1 < n && p[end + 1] < p[i]) {
                end ++;
            }
            for (int j = 1; j <= m; ++ j) {
                memset(sum, 0, sizeof(sum));
                for (int k = 0; k < n; ++ k) {
                    sum[k + 1] = (sum[k] + ways[i & 1][j - 1][k]) % MOD;
                }
                for (int k = begin + 1; k <= end + 1; ++ k) {
                    add(ways[i + 1 & 1][j][k], (sum[k] + MOD - sum[begin]) % MOD);
                }
                add(ways[i + 1 & 1][j][i + 1], MOD - ways[i & 1][j - 1][i]);
            }
            for (int j = 0; j <= m; ++ j) {
                add(ways[i + 1 & 1][j][i + 1], ways[i & 1][j][i]);
            }
        }
        int result = 0;
        for (int i = 0; i <= m; ++ i) {
            add(result, ways[n & 1][i][n]);
        }
        return result;
    }

    void add(int &x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
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
            int P[]                   = {1, 2};
            int K                     = 1;
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = AlienAndPermutation().getNumber(vector <int>(P, P + (sizeof P / sizeof P[0])), K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int P[]                   = {3, 1, 2};
            int K                     = 2;
            int expected__            = 4;

            clock_t start__           = clock();
            int received__            = AlienAndPermutation().getNumber(vector <int>(P, P + (sizeof P / sizeof P[0])), K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int P[]                   = {4, 3, 2, 1};
            int K                     = 2;
            int expected__            = 13;

            clock_t start__           = clock();
            int received__            = AlienAndPermutation().getNumber(vector <int>(P, P + (sizeof P / sizeof P[0])), K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int P[]                   = {1, 7, 2, 3, 6, 4, 5};
            int K                     = 3;
            int expected__            = 77;

            clock_t start__           = clock();
            int received__            = AlienAndPermutation().getNumber(vector <int>(P, P + (sizeof P / sizeof P[0])), K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int P[]                   = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int K                     = 12;
            int expected__            = 379796836;

            clock_t start__           = clock();
            int received__            = AlienAndPermutation().getNumber(vector <int>(P, P + (sizeof P / sizeof P[0])), K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 5: {
            int P[]                   = ;
            int K                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = AlienAndPermutation().getNumber(vector <int>(P, P + (sizeof P / sizeof P[0])), K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int P[]                   = ;
            int K                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = AlienAndPermutation().getNumber(vector <int>(P, P + (sizeof P / sizeof P[0])), K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int P[]                   = ;
            int K                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = AlienAndPermutation().getNumber(vector <int>(P, P + (sizeof P / sizeof P[0])), K);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
        default:
            return -1;
        }
    }
}


int main(int argc, char *argv[]) {
    if (argc == 1) {
        moj_harness::run_test(-1);
    } else {
        for (int i=1; i<argc; ++i)
            moj_harness::run_test(atoi(argv[i]));
    }
}
// END CUT HERE
