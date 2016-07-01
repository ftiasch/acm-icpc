#include <cstdio>
#include <cstring>
#include <climits>
#include <iostream>
#include <functional>
#include <string>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::string;
using std::vector;

struct BiconnectedDiv1
{
    int minimize(const std::vector<int>& w1, const std::vector<int>& w2)
    {
        int n = w1.size() + 1;
        std::vector<int> dp(n);
        for (int i = 1; i < n; ++ i) {
            dp[i] = INT_MAX;
            for (int j = 0; j + 2 <= i; ++ j) {
                if (dp[j] < INT_MAX) {
                    int val = dp[j] + w1[j] + w1[i - 1];
                    for (int k = j; k + 2 <= i; ++ k) {
                        val += w2[k];
                    }
                    dp[i] = std::min(dp[i], val);
                }
            }
        }
        return dp[n - 1];
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
            int w1[]                  = {1,2};
            int w2[]                  = {3};
            int expected__            = 6;

            clock_t start__           = clock();
            int received__            = BiconnectedDiv1().minimize(vector <int>(w1, w1 + (sizeof w1 / sizeof w1[0])), vector <int>(w2, w2 + (sizeof w2 / sizeof w2[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int w1[]                  = {3,0,4};
            int w2[]                  = {1,2};
            int expected__            = 10;

            clock_t start__           = clock();
            int received__            = BiconnectedDiv1().minimize(vector <int>(w1, w1 + (sizeof w1 / sizeof w1[0])), vector <int>(w2, w2 + (sizeof w2 / sizeof w2[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int w1[]                  = {3,3,3,3};
            int w2[]                  = {3,6,3};
            int expected__            = 18;

            clock_t start__           = clock();
            int received__            = BiconnectedDiv1().minimize(vector <int>(w1, w1 + (sizeof w1 / sizeof w1[0])), vector <int>(w2, w2 + (sizeof w2 / sizeof w2[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int w1[]                  = {7243,7525,8467,6351,9453,8456,8175,5874,6869,7400,6438,8926,6876};
            int w2[]                  = {2642,1743,3546,4100,2788,3019,2678,1935,1790,2674,3775,1920};
            int expected__            = 46729;

            clock_t start__           = clock();
            int received__            = BiconnectedDiv1().minimize(vector <int>(w1, w1 + (sizeof w1 / sizeof w1[0])), vector <int>(w2, w2 + (sizeof w2 / sizeof w2[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 4: {
            int w1[]                  = ;
            int w2[]                  = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = BiconnectedDiv1().minimize(vector <int>(w1, w1 + (sizeof w1 / sizeof w1[0])), vector <int>(w2, w2 + (sizeof w2 / sizeof w2[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 5: {
            int w1[]                  = ;
            int w2[]                  = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = BiconnectedDiv1().minimize(vector <int>(w1, w1 + (sizeof w1 / sizeof w1[0])), vector <int>(w2, w2 + (sizeof w2 / sizeof w2[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int w1[]                  = ;
            int w2[]                  = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = BiconnectedDiv1().minimize(vector <int>(w1, w1 + (sizeof w1 / sizeof w1[0])), vector <int>(w2, w2 + (sizeof w2 / sizeof w2[0])));
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
