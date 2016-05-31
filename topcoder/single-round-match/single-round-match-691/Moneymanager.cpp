#include <algorithm>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <functional>
#include <numeric>
#include <string>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::string;
using std::vector;

struct Moneymanager
{
    static const int N = 25;
    static const int M = 10;

    static void update(int& x, int a)
    {
        x = std::max(x, a);
    }

    int getbest(const std::vector<int>& a, const std::vector<int>& b, int x)
    {
        int n = static_cast<int>(a.size()) >> 1;
        std::vector<int> order(n + n);
        std::iota(ALL(order), 0);
        std::sort(ALL(order), [&](int i, int j) { return a[i] * b[j] < a[j] * b[i]; });
        std::vector<int> b_sum(n + n + 1);
        for (int i = 0; i < n + n; ++ i) {
            b_sum[i + 1] = b_sum[i] + b[order[i]];
        }
        static int dp[N + 1][N + 1][N * M + 1];
        int result = 0;
        for (int bs = 0; bs <= n * M; ++ bs) {
            memset(dp, -1, sizeof(dp));
            dp[0][0][0] = 0;
            for (int i = 0; i <= n; ++ i) {
                for (int j = 0; j <= n; ++ j) {
                    auto&& index = order[i + j];
                    for (int s = 0; s <= i * M; ++ s) {
                        auto&& ref = dp[i][j][s];
                        if (!~ref) {
                            continue;
                        }
                        if (i < n) {
                            update(dp[i + 1][j][s + b[index]], ref + a[index] * (s + b[index]));
                        }
                        if (j < n) {
                            update(dp[i][j + 1][s], ref + a[index] * (b_sum[i + j + 1] - s + bs));
                        }
                    }
                }
            }
            auto&& ref = dp[n][n][bs];
            if (~ref) {
                update(result, ref + x * bs);
            }
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
            int a[]                   = {1,1};
            int b[]                   = {2,1};
            int X                     = 0;
            int expected__            = 5;

            clock_t start__           = clock();
            int received__            = Moneymanager().getbest(vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int a[]                   = {1,1};
            int b[]                   = {1,5};
            int X                     = 10;
            int expected__            = 61;

            clock_t start__           = clock();
            int received__            = Moneymanager().getbest(vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int a[]                   = {4,4,6,6};
            int b[]                   = {2,2,3,3};
            int X                     = 100;
            int expected__            = 726;

            clock_t start__           = clock();
            int received__            = Moneymanager().getbest(vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int a[]                   = {30,13,28,59,26,62,48,75,6,69,94,51};
            int b[]                   = {4,6,4,5,4,3,1,5,6,5,2,2};
            int X                     = 62;
            int expected__            = 22096;

            clock_t start__           = clock();
            int received__            = Moneymanager().getbest(vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 4: {
            int a[]                   = ;
            int b[]                   = ;
            int X                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = Moneymanager().getbest(vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 5: {
            int a[]                   = ;
            int b[]                   = ;
            int X                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = Moneymanager().getbest(vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int a[]                   = ;
            int b[]                   = ;
            int X                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = Moneymanager().getbest(vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), X);
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
