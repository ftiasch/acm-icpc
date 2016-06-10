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

const int N = 300000;

int parent[N], length[N], size[N], centroid[N];
long long depth[N], sum[N], answer[N];
std::vector<int> children[N];

struct TreeSums
{
    static const int MOD = (int)1e9;

    void dfs(int u)
    {
        if (u) {
            depth[u] = depth[parent[u]] + length[u];
        }
        size[u] = 1;
        sum[u] = 0;
        std::pair<int, int> prefer{0, 0};
        for (int v : children[u]) {
            dfs(v);
            size[u] += size[v];
            sum[u] += sum[v] + (long long)length[v] * size[v];
            prefer = std::max(prefer, {size[v], v});
        }
        if (prefer.first) {
            int p = prefer.second;
            auto& a = answer[u] = answer[p];
            auto& c = centroid[u] = centroid[p];
            a += sum[u] - (sum[p] + (long long)length[p] * size[p]);
            a += (depth[c] - depth[u]) * (size[u] - size[p]);
            while (c != u) {
                auto delta = size[c] - (size[u] - size[c]);
                if (delta >= 0) {
                    break;
                }
                a += (long long)length[c] * delta;
                c = parent[c];
            }
        } else {
            centroid[u] = u;
            answer[u] = 0;
        }
    }

    long long findSums(int n, int seed, int c, int d)
    {
        int current = seed;
        for (int i = 0; i < n; ++ i) {
            children[i].clear();
        }
        for (int i = 1; i < n; ++ i) {
            current = ((long long)c * current + d) % MOD;
            parent[i] = current % i;
            current = ((long long)c * current + d) % MOD;
            length[i] = current % 1000000;
            children[parent[i]].push_back(i);
        }
        dfs(0);
        long long result = 0;
        for (int i = 0; i < n; ++ i) {
            result ^= answer[i];
        }
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

    int verify_case(int casenum, const long long &expected, const long long &received, clock_t elapsed) {
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
            int N                     = 6;
            int seed                  = 8;
            int C                     = 3;
            int D                     = 13;
            long long expected__      = 856320;

            clock_t start__           = clock();
            long long received__      = TreeSums().findSums(N, seed, C, D);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int N                     = 2;
            int seed                  = 10;
            int C                     = 20;
            int D                     = 30;
            long long expected__      = 4630;

            clock_t start__           = clock();
            long long received__      = TreeSums().findSums(N, seed, C, D);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int N                     = 6;
            int seed                  = 55;
            int C                     = 1;
            int D                     = 18;
            long long expected__      = 22;

            clock_t start__           = clock();
            long long received__      = TreeSums().findSums(N, seed, C, D);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int N                     = 300000;
            int seed                  = 999999990;
            int C                     = 999999990;
            int D                     = 999999991;
            long long expected__      = 438628640790LL;

            clock_t start__           = clock();
            long long received__      = TreeSums().findSums(N, seed, C, D);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 4: {
            int N                     = ;
            int seed                  = ;
            int C                     = ;
            int D                     = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TreeSums().findSums(N, seed, C, D);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 5: {
            int N                     = ;
            int seed                  = ;
            int C                     = ;
            int D                     = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TreeSums().findSums(N, seed, C, D);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int N                     = ;
            int seed                  = ;
            int C                     = ;
            int D                     = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TreeSums().findSums(N, seed, C, D);
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
