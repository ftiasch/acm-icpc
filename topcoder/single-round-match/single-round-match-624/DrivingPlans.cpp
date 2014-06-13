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

struct DrivingPlans {

    int count(int n, vector <int> a, vector <int> b, vector <int> c) {
        std::vector <std::vector <std::pair <int, int>>> graph(n);
        for (int i = 0; i < SIZE(a); ++ i) {
            a[i] --, b[i] --;
            graph[a[i]].push_back(std::make_pair(b[i], c[i]));
            graph[b[i]].push_back(std::make_pair(a[i], c[i]));
        }
        std::vector <int> distance(n, INT_MAX);
        distance[0] = 0;
        for (int _ = 0; _ < n; ++ _) {
            for (int u = 0; u < n; ++ u) {
                for (auto iterator : graph[u]) {
                    int v = iterator.first;
                    int w = iterator.second;
                    if (distance[u] < INT_MAX) {
                        distance[v] = std::min(distance[v], distance[u] + w);
                    }
                }
            }
        }
        std::vector <std::vector <int>> dag(n), reversed_dag(n);
        for (int u = 0; u < n; ++ u) {
            for (auto iterator : graph[u]) {
                int v = iterator.first;
                int w = iterator.second;
                if (distance[u] + w == distance[v]) {
                    dag[u].push_back(v);
                    reversed_dag[v].push_back(u);
                }
            }
        }
        std::vector <bool> visited = floodfill(reversed_dag, std::vector <bool>(n), n - 1);
        for (int u = 0; u < n; ++ u) {
            for (auto iterator : graph[u]) {
                int v = iterator.first;
                int w = iterator.second;
                if (!w && distance[u] == distance[v] && visited[v]) {
                    return -1;
                }
            }
        }
        std::vector <int> ways(n, -1);
        ways[n - 1] = 1;
        return go(dag, ways, 0);
    }

    static const int MOD = (int)1e9 + 9;

    int go(std::vector <std::vector <int>> const &dag, std::vector <int> &ways, int u) {
        if (ways[u] == -1) {
            ways[u] = 0;
            for (int v : dag[u]) {
                ways[u] += go(dag, ways, v);
                if (ways[u] >= MOD) {
                    ways[u] -= MOD;
                }
            }
        }
        return ways[u];
    }

    std::vector <bool> &floodfill(std::vector <std::vector <int>> const &dag, std::vector <bool> &&visited, int u) {
        if (!visited[u]) {
            visited[u] = true;
            for (int v : dag[u]) {
                floodfill(dag, std::move(visited), v);
            }
        }
        return visited;
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
            int A[]                   = {1,1,2,3};
            int B[]                   = {2,3,4,4};
            int C[]                   = {1,1,1,1};
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = DrivingPlans().count(N, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int N                     = 4;
            int A[]                   = {1,1,2,3};
            int B[]                   = {2,3,4,4};
            int C[]                   = {1,1,1,0};
            int expected__            = -1;

            clock_t start__           = clock();
            int received__            = DrivingPlans().count(N, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int N                     = 7;
            int A[]                   = {1,1,2,3,4,4,5,6};
            int B[]                   = {2,3,4,4,5,6,7,7};
            int C[]                   = {1,1,2,2,3,3,4,4};
            int expected__            = 4;

            clock_t start__           = clock();
            int received__            = DrivingPlans().count(N, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 3: {
            int N                     = ;
            int A[]                   = ;
            int B[]                   = ;
            int C[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = DrivingPlans().count(N, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 4: {
            int N                     = ;
            int A[]                   = ;
            int B[]                   = ;
            int C[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = DrivingPlans().count(N, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 5: {
            int N                     = ;
            int A[]                   = ;
            int B[]                   = ;
            int C[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = DrivingPlans().count(N, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(C, C + (sizeof C / sizeof C[0])));
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
