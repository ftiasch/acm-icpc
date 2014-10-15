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

struct PointyWizardHats {
    typedef std::pair <int, double> Pair;

    constexpr static const double EPSILON = 1e-9;

    int getNumHats(vector <int> top_height, vector <int> top_radius, vector <int> bottom_height, vector <int> bottom_radius) {
        std::vector <Pair> tops, bottoms;
        for (int i = 0; i < SIZE(top_height); ++ i) {
            tops.push_back(std::make_pair(-top_radius[i], (double)top_height[i] / top_radius[i]));
        }
        for (int i = 0; i < SIZE(bottom_height); ++ i) {
            bottoms.push_back(std::make_pair(-bottom_radius[i], (double)bottom_height[i] / bottom_radius[i]));
        }
        std::sort(ALL(tops));
        std::sort(ALL(bottoms));
        int m = SIZE(bottoms);
        std::vector <bool> visited(m);
        int result = 0;
        for (int i = 0; i < SIZE(tops); ++ i) {
            int k = -1;
            for (int j = 0; j < m; ++ j) {
                if (!visited[j] && bottoms[j].first < tops[i].first && bottoms[j].second < tops[i].second - EPSILON
                        && (k == -1 || bottoms[j].second > bottoms[k].second)) {
                    k = j;
                }
            }
            if (~k) {
                result ++;
                visited[k] = true;
            }
        }
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
            int topHeight[]           = {30};
            int topRadius[]           = {3};
            int bottomHeight[]        = {3};
            int bottomRadius[]        = {30};
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int topHeight[]           = {4,4};
            int topRadius[]           = {4,3};
            int bottomHeight[]        = {5,12};
            int bottomRadius[]        = {5,4};
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int topHeight[]           = {3};
            int topRadius[]           = {3};
            int bottomHeight[]        = {1,1};
            int bottomRadius[]        = {2,4};
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int topHeight[]           = {10,10};
            int topRadius[]           = {2,5};
            int bottomHeight[]        = {2,9};
            int bottomRadius[]        = {3,6};
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int topHeight[]           = {3,4,5};
            int topRadius[]           = {5,4,3};
            int bottomHeight[]        = {3,4,5};
            int bottomRadius[]        = {3,8,5};
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int topHeight[]           = {1,2,3,4,5};
            int topRadius[]           = {2,3,4,5,6};
            int bottomHeight[]        = {2,3,4,5,6};
            int bottomRadius[]        = {1,2,3,4,5};
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 6: {
            int topHeight[]           = {123,214,232,323,342,343};
            int topRadius[]           = {123,123,232,123,323,434};
            int bottomHeight[]        = {545,322,123,545,777,999};
            int bottomRadius[]        = {323,443,123,656,767,888};
            int expected__            = 5;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 7: {
            int topHeight[]           = {999,999,999,10000,10000,10000};
            int topRadius[]           = {10000,10000,10000,1,2,3};
            int bottomHeight[]        = {2324,2323,234,5454,323,232};
            int bottomRadius[]        = {1,2,3222,434,5454,23};
            int expected__            = 3;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 8: {
            int topHeight[]           = ;
            int topRadius[]           = ;
            int bottomHeight[]        = ;
            int bottomRadius[]        = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 9: {
            int topHeight[]           = ;
            int topRadius[]           = ;
            int bottomHeight[]        = ;
            int bottomRadius[]        = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 10: {
            int topHeight[]           = ;
            int topRadius[]           = ;
            int bottomHeight[]        = ;
            int bottomRadius[]        = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PointyWizardHats().getNumHats(vector <int>(topHeight, topHeight + (sizeof topHeight / sizeof topHeight[0])), vector <int>(topRadius, topRadius + (sizeof topRadius / sizeof topRadius[0])), vector <int>(bottomHeight, bottomHeight + (sizeof bottomHeight / sizeof bottomHeight[0])), vector <int>(bottomRadius, bottomRadius + (sizeof bottomRadius / sizeof bottomRadius[0])));
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
