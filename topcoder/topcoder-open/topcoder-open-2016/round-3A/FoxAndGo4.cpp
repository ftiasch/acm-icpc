#include <algorithm>
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

struct FoxAndGo4
{
    int score(const std::vector<int>& parent)
    {
        auto n = parent.size() + 1;
        std::vector<std::vector<int>> children(n);
        for (int i = 1; i < n; ++ i) {
            children[parent[i - 1]].push_back(i);
        }
        std::vector<int> kind(n), size(n);
        std::map<std::vector<int>, int> deputy;
        for (int u = n - 1; u >= 0; -- u) {
            size[u] = 1;
            std::vector<int> c;
            for (auto&& v : children[u]) {
                size[u] += size[v];
                c.push_back(kind[v]);
            }
            std::sort(c.begin(), c.end());
            if (!deputy.count(c)) {
                deputy[c] = u;
            }
            kind[u] = deputy[c];
        }
        using State = std::pair<bool, std::vector<int>>;
        std::map<State, int> memory;
        std::function<int(const State&)> solve = [&](const State& state)
        {
            if (memory.count(state)) {
                return memory[state];
            }
            auto&& player = state.first;
            auto&& trees = state.second;
            if (trees.empty()) {
                return 0;
            }
            int& result = memory[state];
            result = player ? n : 0;
            for (int i = 0; i < static_cast<int>(trees.size()); ++ i) {
                if (!i || trees[i - 1] != trees[i]) {
                    auto new_trees = trees;
                    new_trees.erase(new_trees.begin() + i);
                    if (player) {
                        for (auto&& v : children[trees[i]]) {
                            if (size[v] > 1) {
                                new_trees.push_back(kind[v]);
                            }
                        }
                        std::sort(new_trees.begin(), new_trees.end());
                        result = std::min(result, solve(std::make_pair(false, new_trees)));
                    } else {
                        result = std::max(result, solve(std::make_pair(true, new_trees)) + size[trees[i]] - 1);
                    }
                }
            }
            return result;
        };
        std::vector<int> init;
        for (auto&& v : children[0]) {
            if (size[v] > 1) {
                init.push_back(kind[v]);
            }
        }
        std::sort(init.begin(), init.end());
        return solve(std::make_pair(true, init));
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
            int p[]                   = {0,1,2,3,4,5};
            int expected__            = 4;

            clock_t start__           = clock();
            int received__            = FoxAndGo4().score(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int p[]                   = {0,0,2,0,4,5};
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = FoxAndGo4().score(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int p[]                   = {0,1,2,0,4,5,0,7,8};
            int expected__            = 3;

            clock_t start__           = clock();
            int received__            = FoxAndGo4().score(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int p[]                   = {0,0,1,1,2,2,3,3,4,4,5,5,6,6};
            int expected__            = 8;

            clock_t start__           = clock();
            int received__            = FoxAndGo4().score(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int p[]                   = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49};
            int expected__            = 48;

            clock_t start__           = clock();
            int received__            = FoxAndGo4().score(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int p[]                   = {0};
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = FoxAndGo4().score(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            int p[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = FoxAndGo4().score(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int p[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = FoxAndGo4().score(vector <int>(p, p + (sizeof p / sizeof p[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int p[]                   = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = FoxAndGo4().score(vector <int>(p, p + (sizeof p / sizeof p[0])));
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
