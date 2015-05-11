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

struct InverseRMQ {
    void unique(std::vector <int> &v) {
        std::sort(ALL(v));
        v.erase(std::unique(ALL(v)), v.end());
    }

    int solve(std::vector <std::vector <int>> capacity, int s, int t) {
        int n = SIZE(capacity);
        int result = 0;
        while (true) {
            std::vector <int> p(n, -1);
            std::queue <int> q;
            q.push(s);
            while (!q.empty()) {
                int u = q.front();
                q.pop();
                for (int v = 0; v < n; ++ v) {
                    if (capacity[u][v] && p[v] == -1) {
                        p[v] = u;
                        q.push(v);
                    }
                }
            }
            if (p[t] == -1) {
                return result;
            }
            int delta = INT_MAX;
            for (int u = t; u != s; u = p[u]) {
                delta = std::min(delta, capacity[p[u]][u]);
            }
            for (int u = t; u != s; u = p[u]) {
                capacity[p[u]][u] -= delta;
                capacity[u][p[u]] += delta;
            }
            result += delta;
        }
    }

    string possible(int n, vector <int> a, vector <int> b, vector <int> c) {
        int m = SIZE(a);
        for (int i = 0; i < m; ++ i) {
            a[i] --;
            c[i] --;
        }
        std::vector <int> ends;
        ends.push_back(0);
        ends.push_back(n);
        std::vector <int> values(ends);
        for (int i = 0; i < m; ++ i) {
            ends.push_back(a[i]);
            ends.push_back(b[i]);
            values.push_back(c[i]);
            values.push_back(c[i] + 1);
        }
        unique(ends);
        std::vector <int> bounds;
        for (int i = 0; i + 1 < SIZE(ends); ++ i) {
            int bb = n;
            for (int j = 0; j < m; ++ j) {
                if (a[j] <= ends[i] && ends[i + 1] <= b[j]) {
                    bb = std::min(bb, c[j]);
                }
            }
            bounds.push_back(bb);
        }
        unique(values);
        int nn = SIZE(bounds);
        int mm = SIZE(values) - 1;
        int vs = nn + mm;
        int vt = vs + 1;
        std::vector <std::vector <int>> capacity(vt + 1, std::vector <int>(vt + 1));
        for (int i = 0; i < SIZE(bounds); ++ i) {
            capacity[vs][i] = ends[i + 1] - ends[i];
        }
        for (int j = 0; j + 1 < SIZE(values); ++ j) {
            int l = 0;
            int r = n;
            for (int k = 0; k < m; ++ k) {
                if (c[k] == values[j]) {
                    l = std::max(l, a[k]);
                    r = std::min(r, b[k]);
                }
            }
            for (int i = 0; i < SIZE(bounds); ++ i) {
                if (l <= ends[i] && ends[i + 1] <= r && values[j] <= bounds[i]) {
                    capacity[i][nn + j] = INT_MAX;
                }
            }
            capacity[nn + j][vt] = values[j + 1] - values[j];
        }
        return solve(capacity, vs, vt) == n ? "Possible" : "Impossible";
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

    int verify_case(int casenum, const string &expected, const string &received, clock_t elapsed) {
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
            cerr << "    Expected: \"" << expected << "\"" << endl;
            cerr << "    Received: \"" << received << "\"" << endl;
        }

        return verdict == "PASSED";
    }

    int run_test_case(int casenum__) {
        switch (casenum__) {
        case 0: {
            int n                     = 5;
            int A[]                   = {1,2,4};
            int B[]                   = {2,4,5};
            int ans[]                 = {3,4,5};
            string expected__         = "Possible";

            clock_t start__           = clock();
            string received__         = InverseRMQ().possible(n, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(ans, ans + (sizeof ans / sizeof ans[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int n                     = 3;
            int A[]                   = {1,2,3};
            int B[]                   = {1,2,3};
            int ans[]                 = {3,3,3};
            string expected__         = "Impossible";

            clock_t start__           = clock();
            string received__         = InverseRMQ().possible(n, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(ans, ans + (sizeof ans / sizeof ans[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int n                     = 600;
            int A[]                   = {1,101,201,301,401,501};
            int B[]                   = {100,200,300,400,500,600};
            int ans[]                 = {100,200,300,400,500,600};
            string expected__         = "Possible";

            clock_t start__           = clock();
            string received__         = InverseRMQ().possible(n, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(ans, ans + (sizeof ans / sizeof ans[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int n                     = 1000000000;
            int A[]                   = {1234,1234};
            int B[]                   = {5678,5678};
            int ans[]                 = {10000,20000};
            string expected__         = "Impossible";

            clock_t start__           = clock();
            string received__         = InverseRMQ().possible(n, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(ans, ans + (sizeof ans / sizeof ans[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int n                     = 8;
            int A[]                   = {1,2,3,4,5,6,7,8};
            int B[]                   = {1,2,3,4,5,6,7,8};
            int ans[]                 = {4,8,2,5,6,3,7,1};
            string expected__         = "Possible";

            clock_t start__           = clock();
            string received__         = InverseRMQ().possible(n, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(ans, ans + (sizeof ans / sizeof ans[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int n                     = 1000000000;
            int A[]                   = {1};
            int B[]                   = {1000000000};
            int ans[]                 = {19911120};
            string expected__         = "Impossible";

            clock_t start__           = clock();
            string received__         = InverseRMQ().possible(n, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(ans, ans + (sizeof ans / sizeof ans[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            int n                     = ;
            int A[]                   = ;
            int B[]                   = ;
            int ans[]                 = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = InverseRMQ().possible(n, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(ans, ans + (sizeof ans / sizeof ans[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int n                     = ;
            int A[]                   = ;
            int B[]                   = ;
            int ans[]                 = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = InverseRMQ().possible(n, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(ans, ans + (sizeof ans / sizeof ans[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int n                     = ;
            int A[]                   = ;
            int B[]                   = ;
            int ans[]                 = ;
            string expected__         = ;

            clock_t start__           = clock();
            string received__         = InverseRMQ().possible(n, vector <int>(A, A + (sizeof A / sizeof A[0])), vector <int>(B, B + (sizeof B / sizeof B[0])), vector <int>(ans, ans + (sizeof ans / sizeof ans[0])));
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
