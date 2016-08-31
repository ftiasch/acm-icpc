#include <algorithm>
#include <cassert>
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

struct TopologicalOrdering
{
    std::vector<int> construct(int ways)
    {
        if (ways == 1) {
            return {1};
        }
        for (int i = 1; i < ways; ++ i) {
            if(std::__gcd(i, ways - i) == 1) {
                int a = i, b = ways - i, v = 2;
                std::vector<int> s;
                while (a > 1 || b > 1) {
                    if (a > b) {
                        a -= b;
                        s.push_back(1);
                    } else {
                        b -= a;
                        s.push_back(0);
                    }
                    v ++;
                }
                assert(a == 1 && b == 1);
                if (v <= 50) {
                    std::vector<int> result { v }, x { 0 }, y { 1 };
                    v = 2;
                    std::reverse(s.begin(), s.end());
                    auto add_edge = [&result](int a, int b) {
                        result.push_back(a);
                        result.push_back(b);
                    };
                    for (auto&& t : s) {
                        if (t) {
                            add_edge(x.back(), v);
                            if (static_cast<int>(y.size()) >= 2) {
                                add_edge(y[static_cast<int>(y.size()) - 2], v);
                            }
                            x.push_back(v ++);
                        } else {
                            add_edge(y.back(), v);
                            if (static_cast<int>(x.size()) >= 2) {
                                add_edge(x[static_cast<int>(x.size()) - 2], v);
                            }
                            y.push_back(v ++);
                        }
                    }
                    return result;
                }
            }
        }
        return {};
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

    template<typename T> std::ostream& operator<<(std::ostream &os, const vector<T> &v) { os << "{"; for (typename vector<T>::const_iterator vi=v.begin(); vi!=v.end(); ++vi) { if (vi != v.begin()) os << ","; os << " " << *vi; } os << " }"; return os; }

    int verify_case(int casenum, const vector <int> &expected, const vector <int> &received, clock_t elapsed) {
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
            int n                     = 5;
            int expected__[]          = {5, 0, 1, 1, 2, 2, 3 };

            clock_t start__           = clock();
            vector <int> received__   = TopologicalOrdering().construct(n);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 1: {
            int n                     = 4;
            int expected__[]          = {5, 0, 2, 1, 2, 2, 3, 2, 4 };

            clock_t start__           = clock();
            vector <int> received__   = TopologicalOrdering().construct(n);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 2: {
            int n                     = 720;
            int expected__[]          = {6 };

            clock_t start__           = clock();
            vector <int> received__   = TopologicalOrdering().construct(n);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 3: {
            int n                     = 1;
            int expected__[]          = {1 };

            clock_t start__           = clock();
            vector <int> received__   = TopologicalOrdering().construct(n);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }

        // custom cases

/*      case 4: {
            int n                     = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = TopologicalOrdering().construct(n);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }*/
/*      case 5: {
            int n                     = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = TopologicalOrdering().construct(n);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }*/
/*      case 6: {
            int n                     = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = TopologicalOrdering().construct(n);
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
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
