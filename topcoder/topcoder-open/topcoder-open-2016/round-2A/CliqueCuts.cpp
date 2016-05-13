#include <algorithm>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <functional>
#include <string>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::string;
using std::vector;

const int N = 45;
const int N2 = 22;
const int MOD = (int)1e9 + 7;

int n, n2, result, weight[N][N], neighbour_mask[N], count[1 << N2], sum[1 << N2], alice_sum[N2], total_sum[N2];
bool graph[N][N];

void update(int &x, int a)
{
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

void dfs(int begin, int end, int j, int mask, int value, int intersection)
{
    if (begin && ~j && (mask >> j & 1)) {
        for (int i = 0; i < n2; ++ i) {
            update(alice_sum[i], weight[begin + j][i]);
        }
    }
    bool valid = true;
    for (int i = 0; i < j && valid; ++ i) {
        if ((mask >> i & 1) == (mask >> j & 1)) {
            auto&& w = weight[begin + i][begin + j];
            if (mask >> j & 1) {
                if (!graph[begin + i][begin + j]) {
                    valid = false;
                }
                update(value, w);
            } else {
                update(value, MOD - w);
            }
        }
    }
    if (valid) {
        j ++;
        if (j < end - begin) {
            dfs(begin, end, j, mask, value, intersection);
            dfs(begin, end, j, mask | 1 << j, value, intersection & neighbour_mask[begin + j]);
        } else if (begin) {
            update(result, sum[intersection]);
            update(result, (long long)value * count[intersection] % MOD);
            for (int i = 0; i < n2; ++ i) {
                update(result, (long long)(alice_sum[i] + MOD - total_sum[i]) * count[intersection & ~(1 << i)] % MOD);
                if (intersection >> i & 1) {
                    update(result, (long long)alice_sum[i] * count[intersection & neighbour_mask[i]] % MOD);
                }
            }
        } else {
            count[mask] ++;
            update(sum[mask], value);
        }
        j --;
    }
    if (begin && ~j && (mask >> j & 1)) {
        for (int i = 0; i < n2; ++ i) {
            update(alice_sum[i], MOD - weight[begin + j][i]);
        }
    }
}

void cov(int* f)
{
    for (int i = 0; i < n2; ++ i) {
        for (int mask = 0; mask < 1 << n2; ++ mask) {
            if (mask >> i & 1) {
                update(f[mask], f[mask ^ (1 << i)]);
            }
        }
    }
}

struct CliqueCuts
{
    int sum(int n_, std::vector<int> a, std::vector<int> b, std::vector<int> c)
    {
        n = n_;
        memset(graph, 0, sizeof(graph));
        memset(weight, 0, sizeof(weight));
        result = 0;
        for (int i = 0; i < static_cast<int>(a.size()); ++ i) {
            graph[a[i]][b[i]] = graph[b[i]][a[i]] = true;
            weight[a[i]][b[i]] = weight[b[i]][a[i]] = c[i];
            update(result, c[i]);
        }
        n2 = n >> 1;
        memset(count, 0, sizeof(count));
        memset(::sum, 0, sizeof(::sum));
        dfs(0, n2, -1, 0, 0, 0);
        cov(count);
        cov(::sum);
        memset(neighbour_mask, 0, sizeof(neighbour_mask));
        for (int j = 0; j < n; ++ j) {
            for (int i = 0; i < n2; ++ i) {
                neighbour_mask[j] |= graph[i][j] << i;
            }
        }
        memset(alice_sum, 0, sizeof(alice_sum));
        memset(total_sum, 0, sizeof(total_sum));
        for (int i = 0; i < n2; ++ i) {
            for (int j = n2; j < n; ++ j) {
                update(total_sum[i], weight[i][j]);
            }
        }
        dfs(n2, n, -1, 0, 0, (1 << n2) - 1);
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
            int n                     = 2;
            int a[]                   = {0};
            int b[]                   = {1};
            int c[]                   = {100};
            int expected__            = 100;

            clock_t start__           = clock();
            int received__            = CliqueCuts().sum(n, vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), vector <int>(c, c + (sizeof c / sizeof c[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int n                     = 5;
            int a[]                   = {0,0,0,0,1,1,1,2,2,3};
            int b[]                   = {1,2,3,4,2,3,4,3,4,4};
            int c[]                   = {0,1,2,3,4,5,6,7,8,9};
            int expected__            = 45;

            clock_t start__           = clock();
            int received__            = CliqueCuts().sum(n, vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), vector <int>(c, c + (sizeof c / sizeof c[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int n                     = 5;
            int a[]                   = {0,1,2,3};
            int b[]                   = {1,2,3,4};
            int c[]                   = {9,2,4,3};
            int expected__            = 999999941;

            clock_t start__           = clock();
            int received__            = CliqueCuts().sum(n, vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), vector <int>(c, c + (sizeof c / sizeof c[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int n                     = 10;
            int a[]                   = {0,1,2,9,5,3,4,7,3,4,1,5,2,3,0,7,8};
            int b[]                   = {6,7,4,5,6,2,6,3,1,8,2,0,9,9,8,2,5};
            int c[]                   = {10000,10000000,100000000,100,10,1,1000,100,10000,100,10000,1000,100,10,100,100000000,10};
            int expected__            = 209370454;

            clock_t start__           = clock();
            int received__            = CliqueCuts().sum(n, vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), vector <int>(c, c + (sizeof c / sizeof c[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int n                     = 45;
            int a[]                   = {};
            int b[]                   = {};
            int c[]                   = {};
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = CliqueCuts().sum(n, vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), vector <int>(c, c + (sizeof c / sizeof c[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

        case 5: {
            int n = 45;
            int m = n * (n - 1) / 2;
            int a[m], b[m], c[m];
            int t = 0;
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < i; ++ j) {
                    a[t] = i;
                    b[t] = j;
                    c[t] = t;
                    t ++;
                }
            }

            int expected__            = 489555;
            clock_t start__           = clock();
            int received__            = CliqueCuts().sum(n, vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), vector <int>(c, c + (sizeof c / sizeof c[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
/*      case 6: {
            int n                     = ;
            int a[]                   = ;
            int b[]                   = ;
            int c[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CliqueCuts().sum(n, vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), vector <int>(c, c + (sizeof c / sizeof c[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int n                     = ;
            int a[]                   = ;
            int b[]                   = ;
            int c[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CliqueCuts().sum(n, vector <int>(a, a + (sizeof a / sizeof a[0])), vector <int>(b, b + (sizeof b / sizeof b[0])), vector <int>(c, c + (sizeof c / sizeof c[0])));
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
