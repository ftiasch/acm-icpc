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

struct TriangleFree
{
    using Long = long long;

    Long count(int n, const std::vector<int>& a, const std::vector<int>& b)
    {
        int m = a.size();
        std::vector<std::vector<int>> graph(n);
        for (int i = 0; i < m; ++ i) {
            graph[a[i]].push_back(b[i]);
            graph[b[i]].push_back(a[i]);
        }
        std::vector<Long> neighbour(n);
        for (int u = 0; u < n; ++ u) {
            for (int v : graph[u]) {
                neighbour[u] |= 1LL << v;
            }
        }
        std::vector<int> degree(n);
        for (int i = 0; i < n; ++ i) {
            degree[i] = graph[i].size();
        }
        std::vector<bool> marked(n);
        std::vector<int> vertices;
        while (true) {
            int v = std::max_element(ALL(degree)) - degree.begin();
            if (degree[v] < 3) {
                break;
            }
            degree[v] = 0;
            marked[v] = true;
            vertices.push_back(v);
            for (int u : graph[v]) {
                if (!marked[u]) {
                    degree[u] --;
                }
            }
        }
        std::vector<std::pair<int, int>> order;
        for (int u = 0; u < n; ++ u) {
            if (!marked[u]) {
                order.emplace_back(degree[u], u);
            }
        }
        std::sort(ALL(order));
        std::vector<bool> visited(n);
        std::vector<std::pair<int, std::vector<int>>> components;
        for (auto&& o : order) {
            int u = o.second;
            if (!marked[u] && !visited[u]) {
                visited[u] = true;
                std::vector<int> chain;
                chain.push_back(u);
                int v = u;
                while (true) {
                    int next = -1;
                    for (int w : graph[v]) {
                        if (!marked[w] && !visited[w]) {
                            next = w;
                            break;
                        }
                    }
                    if (!~next) {
                        break;
                    }
                    chain.push_back(v = next);
                    visited[v] = true;
                }
                bool is_cycle = false;
                for (int v : graph[chain.back()]) {
                    is_cycle |= v == chain.front();
                }
                is_cycle &= static_cast<int>(chain.size()) >= 3;
                components.emplace_back(is_cycle, chain);
            }
        }
        std::function<Long(int, Long, Long)> dfs = [&](int index, Long chosen, Long forbidden) {
            if (index < static_cast<int>(vertices.size())) {
                auto&& v = vertices[index];
                Long result = dfs(index + 1, chosen, forbidden);
                if (~forbidden >> v & 1) {
                    for (int u : graph[v]) {
                        if (chosen >> u & 1) {
                            forbidden |= (neighbour[u] & neighbour[v]);
                        }
                    }
                    result += dfs(index + 1, chosen | 1LL << v, forbidden);
                }
                return result;
            } else {
                Long result = 1;
                for (auto&& pair : components) {
                    auto&& is_cycle = pair.first;
                    auto&& chain = pair.second;
                    Long true_result = 0;
                    for (int first = 0; first < 2; ++ first) {
                        Long dp[] = {0, 0};
                        dp[first] = 1;
                        if (first && (forbidden >> chain[0] & 1)) {
                            break;
                        }
                        for (int i = 1; i < static_cast<int>(chain.size()); ++ i) {
                            Long new_dp[] = {dp[0] + dp[1], dp[0]};
                            if (forbidden >> chain[i] & 1) {
                                new_dp[1] = 0;
                            } else if (!(neighbour[chain[i]] & neighbour[chain[i - 1]] & chosen)) {
                                new_dp[1] += dp[1];
                            }
                            memcpy(dp, new_dp, sizeof(dp));
                        }
                        true_result += dp[0];
                        bool has_last = is_cycle && !!(neighbour[chain.front()] & neighbour[chain.back()] & chosen);
                        if (!first || !has_last) {
                            true_result += dp[1];
                        }
                    }
                    if (is_cycle && static_cast<int>(chain.size()) == 3 && true_result == 8) {
                        true_result --;
                    }
//printf("%lld %lld %lld\n", chosen, forbidden, true_result);
                    result *= true_result;
                }
                return result;
            }
        };
        return dfs(0, 0, 0);
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
            int n                     = 4;
            int x[]                   = {0,1,2,3};
            int y[]                   = {1,2,0,2};
            long long expected__      = 14;

            clock_t start__           = clock();
            long long received__      = TriangleFree().count(n, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int n                     = 5;
            int x[]                   = {0,0,0,0,1,1,1,2,2,3};
            int y[]                   = {1,2,3,4,2,3,4,3,4,4};
            long long expected__      = 16;

            clock_t start__           = clock();
            long long received__      = TriangleFree().count(n, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int n                     = 6;
            int x[]                   = {0,0,1,1,1,3,1,2,2,4};
            int y[]                   = {1,2,2,3,4,4,5,4,5,5};
            long long expected__      = 40;

            clock_t start__           = clock();
            long long received__      = TriangleFree().count(n, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int n                     = 60;
            int x[]                   = {10,20,30};
            int y[]                   = {20,30,10};
            long long expected__      = 1008806316530991104LL;

            clock_t start__           = clock();
            long long received__      = TriangleFree().count(n, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int n                     = 60;
            int x[]                   = {0,0,1,0,0,3,0,0,5,0,0,7,0,0,9,0,0,11,0,0,13,0,0,15,0,0,17,0,0,19,0,0,21,0,0,23,0,0,25,0,0,27,0,0,29,5,2,23,5,20,27,26,22,19,18,13,15,12,7,9};
            int y[]                   = {1,2,2,3,4,4,5,6,6,7,8,8,9,10,10,11,12,12,13,14,14,15,16,16,17,18,18,19,20,20,21,22,22,23,24,24,25,26,26,27,28,28,29,30,30,1,12,27,22,1,14,5,27,27,15,15,29,7,6,3};
            long long expected__      = 578333426763956224LL;

            clock_t start__           = clock();
            long long received__      = TriangleFree().count(n, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 5: {
            int n                     = ;
            int x[]                   = ;
            int y[]                   = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TriangleFree().count(n, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int n                     = ;
            int x[]                   = ;
            int y[]                   = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TriangleFree().count(n, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int n                     = ;
            int x[]                   = ;
            int y[]                   = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TriangleFree().count(n, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
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
