#include <algorithm>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <functional>
#include <set>
#include <string>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::string;
using std::vector;

struct BearBall
{
    using Point = std::pair<int, int>;

    int gcd(int a, int b)
    {
        return b == 0 ? a : gcd(b, a % b);
    }

    Point direction(int x, int y)
    {
        int g = std::abs(gcd(x, y));
        return {x / g, y / g};
    }

    int countThrows(const std::vector<int>& x, const std::vector<int>& y)
    {
        int n = x.size();
        int result = n * (n - 1) * 2;
        for (int i = 0; i < n; ++ i) {
            std::set<Point> directions;
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    directions.insert(direction(x[j] - x[i], y[j] - y[i]));
                }
            }
            int size = directions.size();
            if (size == 1) {
                result = 0;
                for (int i = 0; i < n; ++ i) {
                    for (int j = 0; j < n; ++ j) {
                        result += std::abs(i - j);
                    }
                }
                return result;
            }
            result -= size;
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
            int x[]                   = {1,4,2};
            int y[]                   = {1,10,7};
            int expected__            = 6;

            clock_t start__           = clock();
            int received__            = BearBall().countThrows(vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int x[]                   = {0,0,0,1,1};
            int y[]                   = {0,1,2,0,1};
            int expected__            = 22;

            clock_t start__           = clock();
            int received__            = BearBall().countThrows(vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int x[]                   = {5,7,9,11};
            int y[]                   = {4,3,2,1};
            int expected__            = 20;

            clock_t start__           = clock();
            int received__            = BearBall().countThrows(vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int x[]                   = {10,10,50,50,90,90};
            int y[]                   = {5,17,5,17,5,17};
            int expected__            = 34;

            clock_t start__           = clock();
            int received__            = BearBall().countThrows(vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int x[]                   = {-100, -90, -80, -70, -85, -90, 0, 20};
            int y[]                   = {-5, -8, -13, -21, -13, -13, -13, -69};
            int expected__            = 68;

            clock_t start__           = clock();
            int received__            = BearBall().countThrows(vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 5: {
            int x[]                   = ;
            int y[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = BearBall().countThrows(vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int x[]                   = ;
            int y[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = BearBall().countThrows(vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int x[]                   = ;
            int y[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = BearBall().countThrows(vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
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
