#include <algorithm>
#include <cassert>
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

struct PalindromeMatrix {
    static const int N = 14;
    static const int INF = 1000000000;

    int cost[N][4], parent[N * N], count[N * N][2], minimum[N + 1][N + 1];

    int get_id(int x, int y) {
        return x * N + y;
    }

    int find(int u) {
        return parent[u] == -1 ? u : (parent[u] = find(parent[u]));
    }

    void merge(int u, int v) {
        if (find(u) != find(v)) {
            parent[find(u)] = find(v);
        }
    }

    int minChange(vector <string> a, int r, int c) {
        int n = SIZE(a);
        int m = SIZE(a[0]);
        int result = INF;
        memset(cost, -1, sizeof(cost));
        for (int mask = 0; mask < 1 << m; ++ mask) {
            if (__builtin_popcount(mask) >= c) {
                for (int i = 0; i < n; ++ i) {
                    int j = n - 1 - i;
                    if (i <= j) {
                        for (int type = 0; type < 4; ++ type) {
                            memset(parent, -1, sizeof(parent));
                            memset(count, 0, sizeof(count));
                            for (int k = 0; k < m; ++ k) {
                                if (mask >> k & 1) {
                                    merge(get_id(i, k), get_id(j, k));
                                }
                                int kk = m - 1 - k;
                                if (k < kk) {
                                    if (type >> 0 & 1) {
                                        merge(get_id(i, k), get_id(i, kk));
                                    }
                                    if (type >> 1 & 1) {
                                        merge(get_id(j, k), get_id(j, kk));
                                    }
                                }
                            }
                            for (int k = 0; k < m; ++ k) {
                                count[find(get_id(i, k))][a[i][k] - '0'] ++;
                                if (i != j) {
                                    count[find(get_id(j, k))][a[j][k] - '0'] ++;
                                }
                            }
                            cost[i][type] = 0;
                            for (int k = 0; k < m; ++ k) {
                                if (find(get_id(i, k)) == get_id(i, k)) {
                                    cost[i][type] += std::min(count[get_id(i, k)][0], count[get_id(i, k)][1]);
                                }
                                if (i != j && find(get_id(j, k)) == get_id(j, k)) {
                                    cost[i][type] += std::min(count[get_id(j, k)][0], count[get_id(j, k)][1]);
                                }
                            }
                            if (i == j && (type >> 0 & 1) != (type >> 1 & 1)) {
                                cost[i][type] = INF;
                            }
                        }
                    }
                }
                memset(minimum, 0, sizeof(minimum));
                for (int i = n; i >= 0; -- i) {
                    int j = n - 1 - i;
                    for (int k = 0; k <= n; ++ k) {
                        if (i <= j) {
                            minimum[k][i] = INF;
                            for (int type = 0; type < 4; ++ type) {
                                if (__builtin_popcount(type) <= k) {
                                    minimum[k][i] = std::min(minimum[k][i], minimum[k - __builtin_popcount(type)][i + 1] + cost[i][type]);
                                }
                            }
                        } else {
                            minimum[k][i] = k == 0 ? 0 : INF;
                        }
                    }
                }
                for (int i = r; i <= n; ++ i) {
                    result = std::min(result, minimum[i][0]);
                }
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
            string A[]                = {"0000"
,"1000"
,"1100"
,"1110"};
            int rowCount              = 2;
            int columnCount           = 2;
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            string A[]                = {"0000"
,"1000"
,"1100"
,"1110"};
            int rowCount              = 3;
            int columnCount           = 2;
            int expected__            = 3;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            string A[]                = {"01"
,"10"};
            int rowCount              = 1;
            int columnCount           = 1;
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            string A[]                = {"1110"
,"0001"};
            int rowCount              = 0;
            int columnCount           = 0;
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            string A[]                = {"01010101"
,"01010101"
,"01010101"
,"01010101"
,"01010101"
,"01010101"
,"01010101"
,"01010101"};
            int rowCount              = 2;
            int columnCount           = 3;
            int expected__            = 8;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            string A[]                = {"000000000000"
,"011101110111"
,"010001010101"
,"010001010101"
,"011101010101"
,"010101010101"
,"010101010101"
,"011101110111"
,"000000000000"
,"000000000000"};
            int rowCount              = 5;
            int columnCount           = 9;
            int expected__            = 14;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 6: {
            string A[]                = {"11111101001110"
,"11000111111111"
,"00010101111001"
,"10110000111111"
,"10000011010010"
,"10001101101101"
,"00101010000001"
,"10111010100100"
,"11010011110111"
,"11100010110110"
,"00100101010100"
,"01001011001000"
,"01010001111010"
,"10100000010011"};
            int rowCount              = 6;
            int columnCount           = 8;
            int expected__            = 31;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 7: {
            string A[]                = ;
            int rowCount              = ;
            int columnCount           = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            string A[]                = ;
            int rowCount              = ;
            int columnCount           = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 9: {
            string A[]                = ;
            int rowCount              = ;
            int columnCount           = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PalindromeMatrix().minChange(vector <string>(A, A + (sizeof A / sizeof A[0])), rowCount, columnCount);
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
