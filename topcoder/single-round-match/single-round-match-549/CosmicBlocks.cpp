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

struct CosmicBlocks {
    static const int N = 6;

    int predecessor[N], ways[1 << N];
    std::vector <int> count, level;

    int getNumOrders(vector <int> count, int min_ways, int max_ways)
    {
        int n = SIZE(count);
        this->count = count;
        this->level.resize(n);
        return solve(min_ways, max_ways, 0);
    }

    int solve(int min_ways, int max_ways, int i)
    {
        int n = SIZE(count);
        int result = 0;
        if (i < n) {
            for (level[i] = 0; level[i] < n; ++ level[i]) {
                result += solve(min_ways, max_ways, i + 1);
            }
        } else if (*std::min_element(ALL(level)) == 0) {
            long long edges = 0;
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    if (level[i] == level[j] + 1) {
                        edges |= 1LL << i * n + j;
                    }
                }
            }
            for (long long graph = edges; ; graph = graph - 1 & edges) {
                bool valid = true;
                std::vector <int> in_count(count);
                std::vector <int> out_count(count);
                for (int i = 0; i < n && valid; ++ i) {
                    bool reachable = !level[i];
                    for (int j = 0; j < n; ++ j) {
                        if (graph >> i * n + j & 1) {
                            reachable = true;
                            in_count[j] --;
                            out_count[i] --;
                        }
                    }
                    valid &= reachable;
                }
                for (int i = 0; i < n; ++ i) {
                    valid &= std::min(in_count[i], out_count[i]) >= 0;
                }
                for (int mask = 0; mask < 1 << n && valid; ++ mask) {
                    int delta = 0;
                    int umask = 0;
                    for (int i = 0; i < n; ++ i) {
                        if ((mask >> i & 1) && level[i]) {
                            delta -= out_count[i];
                            umask |= graph >> i * n & ((1 << n) - 1);
                        }
                    }
                    for (int i = 0; i < n; ++ i) {
                        if (umask >> i & 1) {
                            delta += in_count[i];
                        }
                    }
                    valid &= delta >= 0;
                }
                if (valid) {
                    int ways = get_ways(n, graph);
                    valid &= min_ways <= ways && ways <= max_ways;
                    result += valid;
                }
                if (!graph) {
                    break;
                }
            }
        }
        return result;
    }

    int get_ways(int n, long long graph)
    {
        memset(predecessor, 0, sizeof(predecessor));
        for (int j = 0; j < n; ++ j) {
            for (int i = 0; i < n; ++ i) {
                if (graph >> i * n + j & 1) {
                    predecessor[j] |= 1 << i;
                }
            }
        }
        memset(ways, 0, sizeof(ways));
        ways[0] = 1;
        for (int mask = 1; mask < 1 << n; ++ mask) {
            for (int i = 0; i < n; ++ i) {
                if (mask >> i & 1 && !(mask & predecessor[i])) {
                    ways[mask] += ways[mask ^ (1 << i)];
                }
            }
        }
        return ways[(1 << n) - 1];
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
            int blockTypes[]          = {2,2,2};
            int minWays               = 1;
            int maxWays               = 1;
            int expected__            = 6;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int blockTypes[]          = {1,1,1,1,1,1};
            int minWays               = 720;
            int maxWays               = 720;
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int blockTypes[]          = {2,2};
            int minWays               = 1;
            int maxWays               = 2;
            int expected__            = 3;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int blockTypes[]          = {1,2};
            int minWays               = 1;
            int maxWays               = 2;
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int blockTypes[]          = {1};
            int minWays               = 1;
            int maxWays               = 1;
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int blockTypes[]          = {1,2,4,8};
            int minWays               = 5;
            int maxWays               = 30;
            int expected__            = 27;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 6: {
            int blockTypes[]          = {1,2,3,4,5,6};
            int minWays               = 1;
            int maxWays               = 720;
            int expected__            = 4445;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 7: {
            int blockTypes[]          = {7500,1000,7500,1000,7500};
            int minWays               = 8;
            int maxWays               = 88;
            int expected__            = 448;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 8: {
            int blockTypes[]          = ;
            int minWays               = ;
            int maxWays               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 9: {
            int blockTypes[]          = ;
            int minWays               = ;
            int maxWays               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 10: {
            int blockTypes[]          = ;
            int minWays               = ;
            int maxWays               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CosmicBlocks().getNumOrders(vector <int>(blockTypes, blockTypes + (sizeof blockTypes / sizeof blockTypes[0])), minWays, maxWays);
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
