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

struct CampLunch {
    static const int MOD = (int)1e9 + 7;

    void update(int &x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
    }

    int count(int n, int m, vector <string> a) {
        std::vector <int> ways(1 << m);
        ways[(1 << m) - 1] = 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                std::vector <int> new_ways(1 << m);
                for (int mask = 0; mask < 1 << m; ++ mask) {
                    int c = a[i][j] - 'A';
                    if (mask >> c & 1) {
                        update(new_ways[mask], ways[mask]);
                        update(new_ways[mask ^ (1 << c)], ways[mask]);
                        if (j) {
                            int d = a[i][j - 1] - 'A';
                            if (~mask >> d & 1) {
                                update(new_ways[mask | 1 << d], ways[mask]);
                            }
                        }
                    } else {
                        update(new_ways[mask | 1 << c], ways[mask]);
                    }
                }
                ways.swap(new_ways);
            }
        }
        return ways[(1 << m) - 1];
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
            int N                     = 2;
            int M                     = 2;
            string a[]                = {"AB","AB"};
            int expected__            = 7;

            clock_t start__           = clock();
            int received__            = CampLunch().count(N, M, vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int N                     = 2;
            int M                     = 3;
            string a[]                = {"ABC","ABC"};
            int expected__            = 22;

            clock_t start__           = clock();
            int received__            = CampLunch().count(N, M, vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int N                     = 2;
            int M                     = 3;
            string a[]                = {"ABC","BAC"};
            int expected__            = 21;

            clock_t start__           = clock();
            int received__            = CampLunch().count(N, M, vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int N                     = 1;
            int M                     = 1;
            string a[]                = {"A"};
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = CampLunch().count(N, M, vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int N                     = 1;
            int M                     = 10;
            string a[]                = {"ABDEFHIGJC"};
            int expected__            = 89;

            clock_t start__           = clock();
            int received__            = CampLunch().count(N, M, vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int N                     = 16;
            int M                     = 16;
            string a[]                = {"ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP","ABCDEFGHIJKLMNOP"};
            int expected__            = 950052677;

            clock_t start__           = clock();
            int received__            = CampLunch().count(N, M, vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

        case 6: {
            int N                     = 1;
            int M                     = 2;
            string a[]                = {"AB"};
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = CampLunch().count(N, M, vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
/*      case 7: {
            int N                     = ;
            int M                     = ;
            string a[]                = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CampLunch().count(N, M, vector <string>(a, a + (sizeof a / sizeof a[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int N                     = ;
            int M                     = ;
            string a[]                = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CampLunch().count(N, M, vector <string>(a, a + (sizeof a / sizeof a[0])));
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
