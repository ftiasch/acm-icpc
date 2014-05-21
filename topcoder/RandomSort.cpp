#include <cmath>
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <queue>
#include <list>
#include <deque>
#include <utility>
#include <set>
#include <map>
using namespace std;

struct RandomSort {
    int n;
    map <vector <int>, double> expected;

    int get_inverse(vector <int> permutation) {
        int count = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = i + 1; j < n; ++ j) {
                count += permutation[i] > permutation[j];
            }
        }
        return count;
    }

    double getExpected(vector <int> source) {
        n = source.size();
        vector <pair <double, vector <int> > > permutations;
        {
            vector <int> permutation(n);
            for (int i = 0; i < n; ++ i) {
                permutation[i] = 1 + i;
            }
            do {
                permutations.push_back(make_pair(get_inverse(permutation), permutation));
            } while (next_permutation(permutation.begin(), permutation.end()));
            std::sort(permutations.begin(), permutations.end());
        }
        expected[permutations[0].second] = 0.0;
        for (int k = 1; k < (int)permutations.size(); ++ k) {
            int total = permutations[k].first;
            vector <int> &permutation = permutations[k].second;
            double answer = 0;
            for (int i = 0; i < n; ++ i) {
                for (int j = i + 1; j < n; ++ j) {
                    if (permutation[i] > permutation[j]) {
                        std::swap(permutation[i], permutation[j]);
                        answer += (expected[permutation] + 1.0) / total;
                        std::swap(permutation[i], permutation[j]);
                    }
                }
            }
            expected[permutation] = answer;
        }
        return expected[source];
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

    static const double MAX_DOUBLE_ERROR = 1e-9; static bool topcoder_fequ(double expected, double result) { if (isnan(expected)) { return isnan(result); } else if (isinf(expected)) { if (expected > 0) { return result > 0 && isinf(result); } else { return result < 0 && isinf(result); } } else if (isnan(result) || isinf(result)) { return false; } else if (fabs(result - expected) < MAX_DOUBLE_ERROR) { return true; } else { double mmin = min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double mmax = max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > mmin && result < mmax; } }
    double moj_relative_error(double expected, double result) { if (isnan(expected) || isinf(expected) || isnan(result) || isinf(result) || expected == 0) return 0; return fabs(result-expected) / fabs(expected); }

    int verify_case(int casenum, const double &expected, const double &received, clock_t elapsed) {
        cerr << "Example " << casenum << "... ";

        string verdict;
        vector<string> info;
        char buf[100];

        if (elapsed > CLOCKS_PER_SEC / 200) {
            sprintf(buf, "time %.2fs", elapsed * (1.0/CLOCKS_PER_SEC));
            info.push_back(buf);
        }

        if (topcoder_fequ(expected, received)) {
            verdict = "PASSED";
            double rerr = moj_relative_error(expected, received);
            if (rerr > 0) {
                sprintf(buf, "relative error %.3e", rerr);
                info.push_back(buf);
            }
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
            int permutation[]         = {1,3,2};
            double expected__         = 1.0;

            clock_t start__           = clock();
            double received__         = RandomSort().getExpected(vector <int>(permutation, permutation + (sizeof permutation / sizeof permutation[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int permutation[]         = {4,3,2,1};
            double expected__         = 4.066666666666666;

            clock_t start__           = clock();
            double received__         = RandomSort().getExpected(vector <int>(permutation, permutation + (sizeof permutation / sizeof permutation[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int permutation[]         = {1};
            double expected__         = 0.0;

            clock_t start__           = clock();
            double received__         = RandomSort().getExpected(vector <int>(permutation, permutation + (sizeof permutation / sizeof permutation[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int permutation[]         = {2,5,1,6,3,4};
            double expected__         = 5.666666666666666;

            clock_t start__           = clock();
            double received__         = RandomSort().getExpected(vector <int>(permutation, permutation + (sizeof permutation / sizeof permutation[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 4: {
            int permutation[]         = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = RandomSort().getExpected(vector <int>(permutation, permutation + (sizeof permutation / sizeof permutation[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 5: {
            int permutation[]         = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = RandomSort().getExpected(vector <int>(permutation, permutation + (sizeof permutation / sizeof permutation[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int permutation[]         = ;
            double expected__         = ;

            clock_t start__           = clock();
            double received__         = RandomSort().getExpected(vector <int>(permutation, permutation + (sizeof permutation / sizeof permutation[0])));
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
