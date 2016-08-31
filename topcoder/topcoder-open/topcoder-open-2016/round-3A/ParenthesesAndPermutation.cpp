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

struct ParenthesesAndPermutation
{
    std::string getSequence(const std::vector<int>& p)
    {
        auto n = p.size();
        std::string s(n, ')'), t(n, ')');
        for (int i = 1; i < n; i += 2) {
            auto best = std::make_pair(n, n);
            for (int j = 0; j < i; ++ j) {
                if (s[j] == ')') {
                    best = std::min(best, {p[j], j});
                }
            }
            auto j = best.second;
            s[j] = t[p[j]] = '(';
        }
        int sum = 0;
        for (int i = 0; i < n; ++ i) {
            sum += t[i] == '(' ? 1 : -1;
            if (sum < 0) {
                return "Impossible";
            }
        }
        return s;
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

    int verify_case(int casenum, const string &expected, const string &received, clock_t elapsed) {
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
            cerr << "    Expected: \"" << expected << "\"" << endl;
            cerr << "    Received: \"" << received << "\"" << endl;
        }

        return verdict == "PASSED";
    }

    int run_test_case(int casenum__) {
        switch (casenum__) {
        case 0: {
            int p[]                   = {2,0,3,1};
            string expected__         = "(())";

            clock_t start__           = clock();
            string received__         = ParenthesesAndPermutation().getSequence(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int p[]                   = {1,0};
            string expected__         = "Impossible";

            clock_t start__           = clock();
            string received__         = ParenthesesAndPermutation().getSequence(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int p[]                   = {4,5,6,7,0,1,2,3};
            string expected__         = "(())(())";

            clock_t start__           = clock();
            string received__         = ParenthesesAndPermutation().getSequence(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int p[]                   = {9,8,7,6,5,4,3,2,1,0};
            string expected__         = "Impossible";

            clock_t start__           = clock();
            string received__         = ParenthesesAndPermutation().getSequence(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int p[]                   = {0,1};
            string expected__         = "()";

            clock_t start__           = clock();
            string received__         = ParenthesesAndPermutation().getSequence(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 5: {
            int p[]                   = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = ParenthesesAndPermutation().getSequence(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int p[]                   = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = ParenthesesAndPermutation().getSequence(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int p[]                   = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = ParenthesesAndPermutation().getSequence(vector <int>(p, p + (sizeof p / sizeof p[0])));
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
