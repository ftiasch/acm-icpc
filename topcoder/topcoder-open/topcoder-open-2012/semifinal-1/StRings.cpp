#include <cmath>
#include <cstdio>
#include <cstring>
#include <cassert>
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

#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) (int)(v).size()
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

struct StRings {
    vector <int> parent;

    int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    vector <int> truncate(vector <int> a, int n) {
        if (SIZE(a) <= n) {
            return a;
        }
        vector <int> b(a.begin() + SIZE(a) - n, a.end());
        return b;
    }

    vector <int> getSmallest(int n, vector <int> first) {
        if (n == 1) {
            return first;
        }
        int m = SIZE(first);
        foreach (iter, first) {
            (*iter) --;
        }
        parent.clear();
        set <int> elements;
        for (int i = 0; i < n; ++ i) {
            parent.push_back(i);
            elements.insert(i);
        }
        vector <int> a(n, -1);
        for (int i = 0; i < n; ++ i) {
            assert(find(i) == i);
            if (i < m) {
                if (i < n - 1 && find(first[i]) == i) {
                    return vector <int>();
                }
                a[i] = first[i];
            } else {
                foreach (iter, elements) {
                    if (i < n - 1 && find(*iter) == i) {
                        continue;
                    }
                    a[i] = *iter;
                    break;
                }
                if (a[i] == -1) {
                    return vector <int>();
                }
            }
            parent[i] = a[i];
            elements.erase(a[i]);
            a[i] ++;
        }
        return truncate(a, 50);
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

    template<typename T> ostream& operator<<(ostream &os, const vector<T> &v) { os << "{"; for (typename vector<T>::const_iterator vi=v.begin(); vi!=v.end(); ++vi) { if (vi != v.begin()) os << ","; os << " " << *vi; } os << " }"; return os; }

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
            int N                     = 5;
            int first[]               = {2, 4};
            int expected__[]          = {2, 4, 1, 5, 3 };

            clock_t start__           = clock();
            vector <int> received__   = StRings().getSmallest(N, vector <int>(first, first + (sizeof first / sizeof first[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 1: {
            int N                     = 5;
            int first[]               = {1};
            int expected__[]          = { };

            clock_t start__           = clock();
            vector <int> received__   = StRings().getSmallest(N, vector <int>(first, first + (sizeof first / sizeof first[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 2: {
            int N                     = 10;
            int first[]               = {3, 8, 6};
            int expected__[]          = {3, 8, 6, 1, 2, 5, 4, 9, 10, 7 };

            clock_t start__           = clock();
            vector <int> received__   = StRings().getSmallest(N, vector <int>(first, first + (sizeof first / sizeof first[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 3: {
            int N                     = 100;
            int first[]               = {34, 68, 97, 87, 47, 39, 50, 59, 4, 7, 29, 91, 1, 80, 90, 95, 60, 40, 43, 73, 54, 69, 32, 31, 53, 11, 84, 3, 28, 77, 44, 86, 2, 75, 85, 52, 93, 81, 70, 89, 19, 67, 98, 100, 41, 65, 57, 27, 33, 49};
            int expected__[]          = {5, 6, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25, 26, 30, 35, 36, 37, 38, 42, 45, 46, 48, 51, 55, 56, 58, 61, 62, 63, 64, 71, 66, 72, 74, 76, 78, 79, 82, 88, 83, 94, 96, 92, 99 };

            clock_t start__           = clock();
            vector <int> received__   = StRings().getSmallest(N, vector <int>(first, first + (sizeof first / sizeof first[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 4: {
            int N                     = 1;
            int first[]               = {1};
            int expected__[]          = {1 };

            clock_t start__           = clock();
            vector <int> received__   = StRings().getSmallest(N, vector <int>(first, first + (sizeof first / sizeof first[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }

        // custom cases

/*      case 5: {
            int N                     = ;
            int first[]               = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = StRings().getSmallest(N, vector <int>(first, first + (sizeof first / sizeof first[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }*/
/*      case 6: {
            int N                     = ;
            int first[]               = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = StRings().getSmallest(N, vector <int>(first, first + (sizeof first / sizeof first[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }*/
/*      case 7: {
            int N                     = ;
            int first[]               = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = StRings().getSmallest(N, vector <int>(first, first + (sizeof first / sizeof first[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
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
