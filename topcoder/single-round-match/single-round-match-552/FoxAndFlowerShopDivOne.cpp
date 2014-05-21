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

const int N = 30;
const int M = 2 * N * N + 1;

int n, m;
char map[N][N];

int sum[N + 1][N + 1], delta[N + 1][N + 1], maximum[M << 1];

struct FoxAndFlowerShopDivOne {
    int get_id(int l, int r) {
        return l + r | l != r;
    }

    void insert(int l, int r, int k, int v) {
        if (l <= k && k <= r) {
            int id = get_id(l, r);
            maximum[id] = std::max(maximum[id], v);
            if (l < r) {
                int m = l + r >> 1;
                insert(l, m, k, v);
                insert(m + 1, r, k, v);
            }
        }
    }

    int query(int l, int r, int a, int b) {
        if (b < l || r < a) {
            return INT_MIN;
        }
        if (a <= l && r <= b) {
            return maximum[get_id(l, r)];
        }
        int m = l + r >> 1;
        return std::max(query(l, m, a, b), query(m + 1, r, a, b));
    }

    int solve(int limit) {
        memset(sum, 0, sizeof(sum));
        memset(delta, 0, sizeof(delta));
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = m - 1; j >= 0; -- j) {
                sum[i][j] = sum[i][j + 1] + sum[i + 1][j] - sum[i + 1][j + 1] + (map[i][j] != '.');
                delta[i][j] = delta[i][j + 1] + delta[i + 1][j] - delta[i + 1][j + 1];
                if (map[i][j] != '.') {
                    delta[i][j] += map[i][j] == 'L' ? 1 : -1;
                }
            }
        }
        int t = 2 * n * n + 1;
        std::fill(maximum, maximum + (t << 1), INT_MIN);
        int result = -1;
        for (int x0 = n - 1; x0 >= 0; -- x0) {
            for (int x1 = x0; x1 >= 0; -- x1) {
                for (int y0 = 0; y0 < m; ++ y0) {
                    for (int y1 = 0; y1 <= y0; ++ y1) {
                        int s = sum[x1][y1] - sum[x0 + 1][y1] - sum[x1][y0 + 1] + sum[x0 + 1][y0 + 1];
                        int d = delta[x1][y1] - delta[x0 + 1][y1] - delta[x1][y0 + 1] + delta[x0 + 1][y0 + 1] - n * n;
                        result = std::max(result, s + query(0, t - 1, -limit - d, limit - d));
                    }
                }
            }
            for (int x1 = n - 1; x1 >= x0; -- x1) {
                for (int y1 = 0; y1 < m; ++ y1) {
                    for (int y0 = 0; y0 <= y1; ++ y0) {
                        int s = sum[x0][y0] - sum[x1 + 1][y0] - sum[x0][y1 + 1] + sum[x1 + 1][y1 + 1];
                        int d = delta[x0][y0] - delta[x1 + 1][y0] - delta[x0][y1 + 1] + delta[x1 + 1][y1 + 1] + n * n;
                        insert(0, t - 1, d, s);
                    }
                }
            }
        }
        return result;
    }

    int theMaxFlowers(vector <string> flowers, int limit) {
        n = SIZE(flowers);
        m = SIZE(flowers[0]);
        memset(map, 0, sizeof(map));
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                map[i][j] = flowers[i][j];
            }
        }
        int result = solve(limit);
        std::swap(n, m);
        for (int i = 0; i < N; ++ i) {
            for (int j = 0; j < i; ++ j) {
                std::swap(map[i][j], map[j][i]);
            }
        }
        result = std::max(result, solve(limit));
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
            string flowers[]          = {"LLL",
 "PPP",
 "LLL"};
            int maxDiff               = 1;
            int expected__            = 7;

            clock_t start__           = clock();
            int received__            = FoxAndFlowerShopDivOne().theMaxFlowers(vector <string>(flowers, flowers + (sizeof flowers / sizeof flowers[0])), maxDiff);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            string flowers[]          = {"LLL",
 "PPP",
 "LLL"};
            int maxDiff               = 0;
            int expected__            = 6;

            clock_t start__           = clock();
            int received__            = FoxAndFlowerShopDivOne().theMaxFlowers(vector <string>(flowers, flowers + (sizeof flowers / sizeof flowers[0])), maxDiff);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            string flowers[]          = {"...",
 "...",
 "..."};
            int maxDiff               = 3;
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = FoxAndFlowerShopDivOne().theMaxFlowers(vector <string>(flowers, flowers + (sizeof flowers / sizeof flowers[0])), maxDiff);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            string flowers[]          = {"LLPL.LPP",
 "PLPPPPLL",
 "L.P.PLLL",
 "LPL.PP.L",
 ".LLL.P.L",
 "PPLP..PL"};
            int maxDiff               = 2;
            int expected__            = 38;

            clock_t start__           = clock();
            int received__            = FoxAndFlowerShopDivOne().theMaxFlowers(vector <string>(flowers, flowers + (sizeof flowers / sizeof flowers[0])), maxDiff);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            string flowers[]          = {"LLLLLLLLLL",
 "LLLLLLLLLL",
 "LLLLLLLLLL",
 "LLLLLLLLLL",
 "LLLLLLLLLL"};
            int maxDiff               = 0;
            int expected__            = -1;

            clock_t start__           = clock();
            int received__            = FoxAndFlowerShopDivOne().theMaxFlowers(vector <string>(flowers, flowers + (sizeof flowers / sizeof flowers[0])), maxDiff);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            string flowers[]          = {"LLLP..LLP.PLL.LL..LP",
 "L.PL.L.LLLL.LPLLPLP.",
 "PLL.LL.LLL..PL...L..",
 ".LPPP.PPPLLLLPLP..PP",
 "LP.P.PPL.L...P.L.LLL",
 "L..LPLPP.PP...PPPL..",
 "PP.PLLL.LL...LP..LP.",
 "PL...P.PPPL..PLP.L..",
 "P.PPPLPLP.LL.L.LLLPL",
 "PLLPLLP.LLL.P..P.LPL",
 "..LLLPLPPPLP.P.LP.LL",
 "..LP..L..LLPPP.LL.LP",
 "LPLL.PLLPPLP...LL..P",
 "LL.....PLL.PLL.P....",
 "LLL...LPPPPL.PL...PP",
 ".PLPLLLLP.LPP...L...",
 "LL...L.LL.LLLPLPPPP.",
 "PLPLLLL..LP.LLPLLLL.",
 "PP.PLL..L..LLLPPL..P",
 ".LLPL.P.PP.P.L.PLPLL"};
            int maxDiff               = 9;
            int expected__            = 208;

            clock_t start__           = clock();
            int received__            = FoxAndFlowerShopDivOne().theMaxFlowers(vector <string>(flowers, flowers + (sizeof flowers / sizeof flowers[0])), maxDiff);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            string flowers[]          = ;
            int maxDiff               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = FoxAndFlowerShopDivOne().theMaxFlowers(vector <string>(flowers, flowers + (sizeof flowers / sizeof flowers[0])), maxDiff);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            string flowers[]          = ;
            int maxDiff               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = FoxAndFlowerShopDivOne().theMaxFlowers(vector <string>(flowers, flowers + (sizeof flowers / sizeof flowers[0])), maxDiff);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            string flowers[]          = ;
            int maxDiff               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = FoxAndFlowerShopDivOne().theMaxFlowers(vector <string>(flowers, flowers + (sizeof flowers / sizeof flowers[0])), maxDiff);
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
