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

const int N   = 100;

int product[N][N + 1], ways[N][N + 1];
std::pair <int, int> need[N];

struct ProductQuery {
    typedef long long Long;

    static const int MOD = (int)1e9 + 7;

    int theInput(int n, std::vector <int> from, std::vector <int> to, std::vector <int> value) {
        return (Long)solve(n, from, to, value, 2) * solve(n, from, to, value, 5) % MOD;
    }

    int solve(int n, std::vector <int> from, std::vector <int> to, std::vector <int> value, int p) {
        memset(product, -1, sizeof(product));
        int m = SIZE(from);
        for (int i = 0; i < m; ++ i) {
            int &r = product[from[i]][to[i] + 1];
            if (r != -1 && value[i] % p != r) {
                return 0;
            }
            r = value[i] % p;
        }
        std::fill(need, need + n, std::make_pair(-1, -1));
        for (int i = 0; i < n; ++ i) {
            int first = i + 1;
            while (first <= n && product[i][first] == -1) {
                first ++;
            }
            if (first <= n) {
                need[i] = std::make_pair(product[i][first], first);
                for (int j = first + 1; j <= n; ++ j) {
                    if (product[i][j] != -1) {
                        if (!product[i][first]) {
                            if (product[i][j]) {
                                return 0;
                            }
                        } else {
                            int &r = product[first][j];
                            int v = product[i][j] * inverse(product[i][first], p) % p;
                            if (r != -1 && r != v) {
                                return 0;
                            }
                            r = v;
                        }
                    }
                }
            }
        }
        memset(ways, 0, sizeof(ways));
        ways[n][n] = 1;
        for (int i = n; i >= 1; -- i) {
            for (int j = i; j <= n; ++ j) {
                if (ways[i][j]) {
                    if (need[i - 1].first == -1) {
                        update(ways[i - 1][j], (p - 1LL) * ways[i][j] % MOD);
                        update(ways[i - 1][i - 1],  ways[i][j]);
                    } else if (need[i - 1].first) {
                        if (need[i - 1].second <= j) {
                            update(ways[i - 1][j], ways[i][j]);
                        }
                    } else if (need[i - 1].second <= j) {
                        update(ways[i - 1][i - 1], ways[i][j]);
                    } else {
                        update(ways[i - 1][j], (p - 1LL) * ways[i][j] % MOD);
                        update(ways[i - 1][i - 1],  ways[i][j]);
                    }
                }
            }
        }
        int result = 0;
        for (int j = 0; j <= n; ++ j) {
            update(result, ways[0][j]);
        }
        return result;
    }

    int inverse(int a, int p) {
        assert(a);
        return a == 1 ? 1 : (Long)(p - p / a) * inverse(p % a, p) % MOD;
    }

    void update(int &x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
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
            int N                     = 3;
            int Qfrom[]               = {0};
            int Qto[]                 = {0};
            int output[]              = {5};
            int expected__            = 100;

            clock_t start__           = clock();
            int received__            = ProductQuery().theInput(N, vector <int>(Qfrom, Qfrom + (sizeof Qfrom / sizeof Qfrom[0])), vector <int>(Qto, Qto + (sizeof Qto / sizeof Qto[0])), vector <int>(output, output + (sizeof output / sizeof output[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int N                     = 5;
            int Qfrom[]               = {0, 2, 0};
            int Qto[]                 = {1, 4, 4};
            int output[]              = {3, 4, 6};
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = ProductQuery().theInput(N, vector <int>(Qfrom, Qfrom + (sizeof Qfrom / sizeof Qfrom[0])), vector <int>(Qto, Qto + (sizeof Qto / sizeof Qto[0])), vector <int>(output, output + (sizeof output / sizeof output[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int N                     = 3;
            int Qfrom[]               = {0, 1};
            int Qto[]                 = {1, 2};
            int output[]              = {5, 8};
            int expected__            = 4;

            clock_t start__           = clock();
            int received__            = ProductQuery().theInput(N, vector <int>(Qfrom, Qfrom + (sizeof Qfrom / sizeof Qfrom[0])), vector <int>(Qto, Qto + (sizeof Qto / sizeof Qto[0])), vector <int>(output, output + (sizeof output / sizeof output[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int N                     = 8;
            int Qfrom[]               = {2, 0, 3, 6};
            int Qto[]                 = {3, 7, 4, 6};
            int output[]              = {2, 0, 6, 7};
            int expected__            = 118080;

            clock_t start__           = clock();
            int received__            = ProductQuery().theInput(N, vector <int>(Qfrom, Qfrom + (sizeof Qfrom / sizeof Qfrom[0])), vector <int>(Qto, Qto + (sizeof Qto / sizeof Qto[0])), vector <int>(output, output + (sizeof output / sizeof output[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int N                     = 20;
            int Qfrom[]               = {9, 6, 2, 3, 1, 3, 12, 3, 4, 6, 10, 18, 10, 11, 15, 5};
            int Qto[]                 = {12, 12, 4, 13, 7, 14, 17, 10, 5, 13, 11, 19, 17, 14, 17, 8};
            int output[]              = {7, 5, 7, 5, 0, 5, 8, 5, 3, 5, 3, 9, 4, 1, 6, 5};
            int expected__            = 276398080;

            clock_t start__           = clock();
            int received__            = ProductQuery().theInput(N, vector <int>(Qfrom, Qfrom + (sizeof Qfrom / sizeof Qfrom[0])), vector <int>(Qto, Qto + (sizeof Qto / sizeof Qto[0])), vector <int>(output, output + (sizeof output / sizeof output[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int N                     = 58;
            int Qfrom[]               = {5, 5};
            int Qto[]                 = {8, 8};
            int output[]              = {1, 2};
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = ProductQuery().theInput(N, vector <int>(Qfrom, Qfrom + (sizeof Qfrom / sizeof Qfrom[0])), vector <int>(Qto, Qto + (sizeof Qto / sizeof Qto[0])), vector <int>(output, output + (sizeof output / sizeof output[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            int N                     = ;
            int Qfrom[]               = ;
            int Qto[]                 = ;
            int output[]              = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = ProductQuery().theInput(N, vector <int>(Qfrom, Qfrom + (sizeof Qfrom / sizeof Qfrom[0])), vector <int>(Qto, Qto + (sizeof Qto / sizeof Qto[0])), vector <int>(output, output + (sizeof output / sizeof output[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int N                     = ;
            int Qfrom[]               = ;
            int Qto[]                 = ;
            int output[]              = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = ProductQuery().theInput(N, vector <int>(Qfrom, Qfrom + (sizeof Qfrom / sizeof Qfrom[0])), vector <int>(Qto, Qto + (sizeof Qto / sizeof Qto[0])), vector <int>(output, output + (sizeof output / sizeof output[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int N                     = ;
            int Qfrom[]               = ;
            int Qto[]                 = ;
            int output[]              = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = ProductQuery().theInput(N, vector <int>(Qfrom, Qfrom + (sizeof Qfrom / sizeof Qfrom[0])), vector <int>(Qto, Qto + (sizeof Qto / sizeof Qto[0])), vector <int>(output, output + (sizeof output / sizeof output[0])));
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
