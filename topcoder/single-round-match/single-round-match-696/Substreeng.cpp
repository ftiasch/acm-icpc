#include <bitset>
#include <cstdio>
#include <cstring>
#include <iostream>
#include <functional>
#include <map>
#include <string>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::string;
using std::vector;

struct Substreeng
{
    using M = std::map<int, int>;

    static const int MOD = (int)1e9 + 7;

    void update(int& x, int a)
    {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
    }

    int count(const std::vector<int>& parent, const std::string& character, const std::string& pattern)
    {
        int n = parent.size() + 1;
        int m = pattern.size();
        std::vector<int> reverse(1 << m + 1);
        for (int mask = 1; mask < 1 << m + 1; ++ mask) {
            reverse[mask] = reverse[mask >> 1] >> 1 | (mask & 1) << m;
        }
        std::vector<M> ways(n + 1);
        for (int u = 0; u < n; ++ u) {
            ways[u][1 << m + 2 | 1] = 1;
        }
        auto invalid = [&](int mask) -> bool
        {
            return mask & reverse[mask >> m + 2];
        };
        int result = 0;
        for (int u = n - 1; u >= 0; -- u) {
            int p = u ? parent[u - 1] : n;
            auto& ways_p = ways[p];
            auto new_ways = ways_p;
            int ch_mask = 0;
            if (u) {
                for (int i = 0; i < m; ++ i) {
                    if (character.at(u - 1) == pattern.at(i)) {
                        ch_mask |= 1 << i + 1 | 1 << m + m + 2 - i;
                    }
                }
            }
            for (auto&& iterator : ways[u]) {
                if (invalid(iterator.first)) {
                    continue;
                }
                update(result, iterator.second);
                if (u) {
                    int new_mask = (iterator.first << 1) & ch_mask;
                    for (auto&& iterator_p : ways_p) {
                        if (invalid(iterator_p.first | new_mask)) {
                            continue;
                        }
                        update(new_ways[iterator_p.first | new_mask], 1LL * iterator_p.second * iterator.second % MOD);
                    }
                }
            }
            ways_p.swap(new_ways);
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
            int par[]                 = {0,0,0};
            string ch                 = "aab";
            string pat                = "ab";
            int expected__            = 8;

            clock_t start__           = clock();
            int received__            = Substreeng().count(vector <int>(par, par + (sizeof par / sizeof par[0])), ch, pat);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int par[]                 = {0,0,0};
            string ch                 = "aaa";
            string pat                = "b";
            int expected__            = 11;

            clock_t start__           = clock();
            int received__            = Substreeng().count(vector <int>(par, par + (sizeof par / sizeof par[0])), ch, pat);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int par[]                 = {0,1,2,3};
            string ch                 = "abab";
            string pat                = "ab";
            int expected__            = 9;

            clock_t start__           = clock();
            int received__            = Substreeng().count(vector <int>(par, par + (sizeof par / sizeof par[0])), ch, pat);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int par[]                 = {0,1,2,3,4,5,6,7,8};
            string ch                 = "ababababa";
            string pat                = "c";
            int expected__            = 55;

            clock_t start__           = clock();
            int received__            = Substreeng().count(vector <int>(par, par + (sizeof par / sizeof par[0])), ch, pat);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int par[]                 = {0,1,2,3,4,5,6,7,8};
            string ch                 = "ababababa";
            string pat                = "abab";
            int expected__            = 34;

            clock_t start__           = clock();
            int received__            = Substreeng().count(vector <int>(par, par + (sizeof par / sizeof par[0])), ch, pat);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 5: {
            int par[]                 = ;
            string ch                 = ;
            string pat                = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = Substreeng().count(vector <int>(par, par + (sizeof par / sizeof par[0])), ch, pat);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int par[]                 = ;
            string ch                 = ;
            string pat                = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = Substreeng().count(vector <int>(par, par + (sizeof par / sizeof par[0])), ch, pat);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int par[]                 = ;
            string ch                 = ;
            string pat                = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = Substreeng().count(vector <int>(par, par + (sizeof par / sizeof par[0])), ch, pat);
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
