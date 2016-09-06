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

struct ConnectedStates
{
    static const int MOD = 1e9 + 7;

    void update(int& x, int a)
    {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
    }

    int getSum(const std::vector<int>& a)
    {
        int n = a.size();
        std::vector<int> dp(n + 1);
        dp[0] = 1;
        int sum = 0;
        int product = 1;
        for (auto&& ai : a) {
            update(sum, ai);
            product = 1LL * product * ai % MOD;
            for (int i = n; i >= 1; -- i) {
                update(dp[i], 1LL * dp[i - 1] * ai % MOD);
            }
        }
        std::vector<int> power(n);
        power[0] = 1;
        for (int i = 1; i < n; ++ i) {
            power[i] = 1LL * sum * power[i - 1] % MOD;
        }
        int result = 0;
        int binom = 1;
        for (int i = 0; i <= n - 2; ++ i) {
            update(result, 1LL * product * binom % MOD * power[n - 2 - i] % MOD * dp[i] % MOD);
            binom = 1LL * binom * (n - 2 - i) % MOD;
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
            int a[]                   = {3, 10};
            int expected__            = 30;

            clock_t start__           = clock();
            int received__            = ConnectedStates().getSum(vector <int>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int a[]                   = {2, 2, 2};
            int expected__            = 96;

            clock_t start__           = clock();
            int received__            = ConnectedStates().getSum(vector <int>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int a[]                   = {1, 1, 1, 1};
            int expected__            = 60;

            clock_t start__           = clock();
            int received__            = ConnectedStates().getSum(vector <int>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int a[]                   = {205659656, 888625810};
            int expected__            = 118040021;

            clock_t start__           = clock();
            int received__            = ConnectedStates().getSum(vector <int>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int a[]                   = {2, 1, 2, 1, 2};
            int expected__            = 27808;

            clock_t start__           = clock();
            int received__            = ConnectedStates().getSum(vector <int>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int a[]                   = {14204721, 921856626, 804287587, 304606131, 277490604, 310739929, 900757841, 818413665, 155154829, 836327185, 602928524, 26132484, 587345385, 936362852, 92603422};
            int expected__            = 376924431;

            clock_t start__           = clock();
            int received__            = ConnectedStates().getSum(vector <int>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            int a[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = ConnectedStates().getSum(vector <int>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int a[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = ConnectedStates().getSum(vector <int>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int a[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = ConnectedStates().getSum(vector <int>(a, a + (sizeof a / sizeof a[0])));
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
