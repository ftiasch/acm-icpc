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

struct FenwickTree {
    int n;
    std::vector <std::vector <std::pair <int, int>>> a;

    FenwickTree(int n = 0) : n(n), a(n, std::vector <std::pair <int, int>>(1, {INT_MIN, -1})) {
    }

    void insert(int k, int v, int id) {
        for (; ~k; k -= ~k & k + 1) {
            if (a[k].back().first < v) {
                a[k].push_back({v, id});
            }
        }
    }

    void erase(int k, int id) {
        for (; ~k; k -= ~k & k + 1) {
            if (a[k].back().second == id) {
                a[k].pop_back();
            }
        }
    }

    int query(int k) {
        int result = INT_MIN;
        for (; k < n; k += ~k & k + 1) {
            result = std::max(result, a[k].back().first);
        }
        return result;
    }
};

struct PublicTransitHard {
    int n, x, r;
    std::vector <std::vector <int>> edges;
    std::vector <int> down, diameter;
    FenwickTree tree;

    void prepare(int p, int u) {
        down[u] = diameter[u] = 0;
        for (int v : edges[u]) {
            if (v != p) {
                prepare(u, v);
                diameter[u] = std::max(diameter[u], std::max(diameter[v], down[u] + 1 + down[v]));
                down[u] = std::max(down[u], down[v] + 1);
            }
        }
    }

    void trim(std::vector <int> &v, int n) {
        std::sort(ALL(v), std::greater <int>());
        v.resize(n);
    }

    int update(int d, int down) {
        int mm = tree.query(std::max(x - d - down + n, 0));
        return mm == INT_MIN ? n : d + (x - mm - down);
    }

    int solve(int p, int u, int d, int md) {
        int result = 0;
        if (d <= md) {
            std::vector <int> downs(3, 0), diameters(2, 0);
            for (int v : edges[u]) {
                if (v != p) {
                    downs.push_back(down[v] + 1);
                    diameters.push_back(diameter[v]);
                }
            }
            trim(downs, 3);
            trim(diameters, 2);
            result += r <= u && std::max(diameters[0], downs[0] + downs[1]) <= x && d <= update(d, downs[0]);
            for (int v : edges[u]) {
                if (v != p) {
                    int tdown = downs[down[v] + 1 == downs[0]];
                    int tdiameter = diameters[diameter[v] == diameters[0]];
                    int flag = std::find(ALL(downs), down[v] + 1) - downs.begin();
                    for (int x = 0; x < 3; ++ x) {
                        for (int y = x + 1; y < 3; ++ y) {
                            if (x != flag && y != flag) {
                                tdiameter = std::max(tdiameter, downs[x] + downs[y]);
                            }
                        }
                    }
                    if (tdiameter <= x) {
                        int k = tdown + (n - 1) - d;
                        int nmd = std::min(md, update(d, tdown));
                        tree.insert(k, tdown + d, u);
                        result += solve(u, v, d + 1, nmd);
                        tree.erase(k, u);
                    }
                }
            }
        }
        return result;
    }

    int countValidTeleporters(int _n, vector <int> parent, int _x) {
        n = _n, x = _x;
        edges.resize(n);
        for (int i = 1; i < n; ++ i) {
            int j = parent[i - 1];
            edges[i].push_back(j);
            edges[j].push_back(i);
        }
        int result = 0;
        down.resize(n);
        diameter.resize(n);
        tree = FenwickTree(n << 1);
        for (r = 0; r < n; ++ r) {
            prepare(-1, r);
            result += solve(-1, r, 0, n - 1);
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
            int N                     = 4;
            int edges[]               = {0, 1, 2};
            int X                     = 1;
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = PublicTransitHard().countValidTeleporters(N, vector <int>(edges, edges + (sizeof edges / sizeof edges[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int N                     = 3;
            int edges[]               = {0, 0};
            int X                     = 2;
            int expected__            = 6;

            clock_t start__           = clock();
            int received__            = PublicTransitHard().countValidTeleporters(N, vector <int>(edges, edges + (sizeof edges / sizeof edges[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int N                     = 6;
            int edges[]               = {0, 0, 0, 1, 1};
            int X                     = 2;
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = PublicTransitHard().countValidTeleporters(N, vector <int>(edges, edges + (sizeof edges / sizeof edges[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int N                     = 7;
            int edges[]               = {0, 1, 0, 1, 2, 4};
            int X                     = 3;
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = PublicTransitHard().countValidTeleporters(N, vector <int>(edges, edges + (sizeof edges / sizeof edges[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int N                     = 16;
            int edges[]               = {0, 1, 0, 2, 0, 0, 4, 5, 8, 9, 10, 11, 8, 4, 6};
            int X                     = 7;
            int expected__            = 31;

            clock_t start__           = clock();
            int received__            = PublicTransitHard().countValidTeleporters(N, vector <int>(edges, edges + (sizeof edges / sizeof edges[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int N                     = 56;
            int edges[]               = {0, 1, 1, 3, 1, 5, 1, 0, 8, 8, 10, 10, 12, 10, 10, 8, 16, 16, 18, 19, 19, 21, 19, 19, 24, 25, 25, 27, 18, 0, 30, 30, 30, 33, 34, 34, 34, 30, 38, 39, 39, 38, 42, 43, 0, 45, 45, 45, 48, 45, 45, 51, 45, 53, 54};
            int X                     = 12;
            int expected__            = 610;

            clock_t start__           = clock();
            int received__            = PublicTransitHard().countValidTeleporters(N, vector <int>(edges, edges + (sizeof edges / sizeof edges[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            int N                     = ;
            int edges[]               = ;
            int X                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PublicTransitHard().countValidTeleporters(N, vector <int>(edges, edges + (sizeof edges / sizeof edges[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int N                     = ;
            int edges[]               = ;
            int X                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PublicTransitHard().countValidTeleporters(N, vector <int>(edges, edges + (sizeof edges / sizeof edges[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int N                     = ;
            int edges[]               = ;
            int X                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = PublicTransitHard().countValidTeleporters(N, vector <int>(edges, edges + (sizeof edges / sizeof edges[0])), X);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
        default:
            return -1;
        }
    }
}


int main(int argc, char *argv[]) {
    if (argc == 1) {
        moj_harness::run_test(-1);
    } else {
        for (int i=1; i<argc; ++i)
            moj_harness::run_test(atoi(argv[i]));
    }
}
// END CUT HERE
