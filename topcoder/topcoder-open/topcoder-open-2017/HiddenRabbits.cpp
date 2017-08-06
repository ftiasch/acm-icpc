#include <cassert>
#include <climits>
#include <string>
#include <vector>

#define DEBUG(...) fprintf(stderr, __VA_ARGS__)

using namespace std;

struct Graph
{
    Graph(int n)
      : graph(n)
      , dfn(n, -1)
      , low(n)
      , scc(n)
    {
    }

    void add(int u, int v)
    {
        graph.at(u).emplace_back(v);
    }

    void dfs(int u)
    {
        if (dfn[u] == -1) {
            stack.push_back(u);
            int tmp = dfn[u] = low[u] = dc ++;
            for (auto&& v : graph[u]) {
                dfs(v);
                tmp = std::min(tmp, low[v]);
            }
            if ((low[u] = tmp) == dfn[u]) {
                int v = -1;
                while (u != v) {
                    v = stack.back();
                    stack.pop_back();
                    scc[v] = m;
                    low[v] = INT_MAX;
                }
                m ++;
            }
        }
    }

    void solve()
    {
        for (int i = 0; i < (int)graph.size(); ++ i) {
            dfs(i);
        }
    }

    int dc = 0, m = 0;
    std::vector<std::vector<int>> graph;
    std::vector<int> dfn, low, scc, stack;
};

struct HiddenRabbits {
    std::vector<int> whereAreTheRabbits(std::vector<int> p, int m, std::vector<int> r, std::vector <int> a, std::vector <int> b, std::vector <int> x)
    {
        int n = p.size() + 1;
        std::vector<int> to(n - 1 << 1);
        for (int i = 0; i < n - 1; ++ i) {
            to[i << 1] = i + 1;
            to[i << 1 | 1] = p[i];
        }
        std::vector<std::vector<int>> adj(n);
        std::vector<std::vector<int>> dist(n, std::vector<int>(n, n));
        for (int i = 0; i < n; ++ i) {
            dist[i][i] = 0;
        }
        for (int i = 0; i < n - 1 << 1; ++ i) {
            adj[to[i ^ 1]].push_back(i);
            dist[to[i]][to[i ^ 1]] = 1;
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    dist[i][j] = std::min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int v = (n - 1 << 1) * m;
        Graph g(v);
        auto id = [n](int who, int edge) {
            return who * (n - 1 << 1) + edge;
        };
        for (int i = 0; i < m; ++ i) {
            for (int e = 0; e < n - 1 << 1; ++ e) {
                for (auto&& f : adj[to[e]]) {
                    if ((e ^ f) != 1) {
                        g.add(id(i, f), id(i, e));
                    }
                }
            }
        }
        for (int i = 0; i < (int)r.size(); ++ i) {
            int ban = -1;
            int u = x[i];
            for (auto&& e : adj[u]) {
                int v = to[e];
                if (dist[u][r[i]] == dist[u][v] + dist[v][r[i]]) {
                    ban = e;
                } else {
                    g.add(id(a[i], e), id(b[i], e ^ 1));
                    g.add(id(b[i], e), id(a[i], e ^ 1));
                }
            }
            if (~ban) {
                ban ^= 1;
                g.add(id(a[i], ban ^ 1), id(a[i], ban));
                g.add(id(b[i], ban ^ 1), id(b[i], ban));
            }
        }
        g.solve();
        std::vector<int> result(m), solution(n - 1 << 1);
        for (int i = 0; i < m; ++ i) {
            for (int e = 0; e < n - 1 << 1; ++ e) {
                auto delta = g.scc[id(i, e)] - g.scc[id(i, e ^ 1)];
                if (delta == 0) {
                    return {};
                }
                solution[e] = delta < 0;
            }
            while (result[i] < n) {
                bool ok = true;
                for (auto&& e : adj[result[i]]) {
                    ok &= !solution[e];
                }
                if (ok) {
                    break;
                }
                result[i] ++;
            }
            assert(result[i] < n);
        }
        return result;
    }
};

// {{{ test
// BEGIN CUT HERE
#include <cstdio>
#include <ctime>
#include <iostream>
#include <string>
#include <vector>
namespace moj_harness {
    using std::string;
    using std::vector;
    int run_test_case(int);
    void run_test(int casenum = -1, bool quiet = false) {
        if (casenum != -1) {
            if (run_test_case(casenum) == -1 && !quiet) {
                std::cerr << "Illegal input! Test case " << casenum << " does not exist." << std::endl;
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
            std::cerr << "No test cases run." << std::endl;
        } else if (correct < total) {
            std::cerr << "Some cases FAILED (passed " << correct << " of " << total << ")." << std::endl;
        } else {
            std::cerr << "All " << total << " tests passed!" << std::endl;
        }
    }

    template<typename T> std::ostream& operator<<(std::ostream &os, const std::vector<T> &v) { os << "{"; for (typename std::vector<T>::const_iterator vi=v.begin(); vi!=v.end(); ++vi) { if (vi != v.begin()) os << ","; os << " " << *vi; } os << " }"; return os; }

    int verify_case(int casenum, const std::vector <int> &expected, const std::vector <int> &received, std::clock_t elapsed) {
        std::cerr << "Example " << casenum << "... ";

        string verdict;
        std::vector<string> info;
        char buf[100];

        if (elapsed > CLOCKS_PER_SEC / 200) {
            std::sprintf(buf, "time %.2fs", elapsed * (1.0/CLOCKS_PER_SEC));
            info.push_back(buf);
        }

        if (expected == received) {
            verdict = "PASSED";
        } else {
            verdict = "FAILED";
        }

        std::cerr << verdict;
        if (!info.empty()) {
            std::cerr << " (";
            for (size_t i=0; i<info.size(); ++i) {
                if (i > 0) std::cerr << ", ";
                std::cerr << info[i];
            }
            std::cerr << ")";
        }
        std::cerr << std::endl;

        if (verdict == "FAILED") {
            std::cerr << "    Expected: " << expected << std::endl;
            std::cerr << "    Received: " << received << std::endl;
        }

        return verdict == "PASSED";
    }

    int run_test_case(int casenum__) {
        switch (casenum__) {
            case 0: {
                        int p[]                   = {0,1,2};
                        int m                     = 2;
                        int r[]                   = {2};
                        int a[]                   = {0};
                        int b[]                   = {1};
                        int x[]                   = {1};
                        int expected__[]          = {1, 1 };

                        std::clock_t start__      = std::clock();
                        std::vector <int> received__   = HiddenRabbits().whereAreTheRabbits(std::vector <int>(p, p + (sizeof p / sizeof p[0])), m, std::vector <int>(r, r + (sizeof r / sizeof r[0])), std::vector <int>(a, a + (sizeof a / sizeof a[0])), std::vector <int>(b, b + (sizeof b / sizeof b[0])), std::vector <int>(x, x + (sizeof x / sizeof x[0])));
                        return verify_case(casenum__, std::vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
                    }
            case 1: {
                        int p[]                   = {0,1};
                        int m                     = 2;
                        int r[]                   = {0,1};
                        int a[]                   = {0,1};
                        int b[]                   = {1,0};
                        int x[]                   = {0,1};
                        int expected__[]          = {0, 1 };

                        std::clock_t start__      = std::clock();
                        std::vector <int> received__   = HiddenRabbits().whereAreTheRabbits(std::vector <int>(p, p + (sizeof p / sizeof p[0])), m, std::vector <int>(r, r + (sizeof r / sizeof r[0])), std::vector <int>(a, a + (sizeof a / sizeof a[0])), std::vector <int>(b, b + (sizeof b / sizeof b[0])), std::vector <int>(x, x + (sizeof x / sizeof x[0])));
                        return verify_case(casenum__, std::vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
                    }
            case 2: {
                        int p[]                   = {0,1,1};
                        int m                     = 3;
                        int r[]                   = {0,0,0,2,3,2,3,2,3};
                        int a[]                   = {0,1,2,0,0,1,1,2,2};
                        int b[]                   = {1,2,0,1,1,2,2,0,0};
                        int x[]                   = {1,1,1,2,3,2,3,2,3};
                        int expected__[]          = { };

                        std::clock_t start__      = std::clock();
                        std::vector <int> received__   = HiddenRabbits().whereAreTheRabbits(std::vector <int>(p, p + (sizeof p / sizeof p[0])), m, std::vector <int>(r, r + (sizeof r / sizeof r[0])), std::vector <int>(a, a + (sizeof a / sizeof a[0])), std::vector <int>(b, b + (sizeof b / sizeof b[0])), std::vector <int>(x, x + (sizeof x / sizeof x[0])));
                        return verify_case(casenum__, std::vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
                    }
            case 3: {
                        int p[]                   = {0,1,2};
                        int m                     = 4;
                        int r[]                   = {2,1,0,2,1,3};
                        int a[]                   = {0,1,0,0,2,2};
                        int b[]                   = {2,3,1,1,3,3};
                        int x[]                   = {1,2,0,2,1,3};
                        int expected__[]          = {0, 2, 1, 3 };

                        std::clock_t start__      = std::clock();
                        std::vector <int> received__   = HiddenRabbits().whereAreTheRabbits(std::vector <int>(p, p + (sizeof p / sizeof p[0])), m, std::vector <int>(r, r + (sizeof r / sizeof r[0])), std::vector <int>(a, a + (sizeof a / sizeof a[0])), std::vector <int>(b, b + (sizeof b / sizeof b[0])), std::vector <int>(x, x + (sizeof x / sizeof x[0])));
                        return verify_case(casenum__, std::vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
                    }
            case 4: {
                        int p[]                   = {0,0,0,1,0,2,3,1};
                        int m                     = 10;
                        int r[]                   = {3,3};
                        int a[]                   = {2,6};
                        int b[]                   = {6,2};
                        int x[]                   = {5,0};
                        int expected__[]          = { };

                        std::clock_t start__      = std::clock();
                        std::vector <int> received__   = HiddenRabbits().whereAreTheRabbits(std::vector <int>(p, p + (sizeof p / sizeof p[0])), m, std::vector <int>(r, r + (sizeof r / sizeof r[0])), std::vector <int>(a, a + (sizeof a / sizeof a[0])), std::vector <int>(b, b + (sizeof b / sizeof b[0])), std::vector <int>(x, x + (sizeof x / sizeof x[0])));
                        return verify_case(casenum__, std::vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
                    }
            case 5: {
                        int p[]                   = {0,0,0,0,2,2,3,0,7,6,9,11,4,13,7,10,12,1,18};
                        int m                     = 15;
                        int r[]                   = {13,15,1,11,17,15,13,6,15,14,10,15,3,5,0,1,17,7,9,13,18,4,4,14,16} ;
                        int a[]                   = {2,0,13,12,5,11,14,10,12,7,7,2,14,9,14,0,0,3,0,6,13,3,1,6,14} ;
                        int b[]                   = {3,7,6,0,1,12,1,1,4,2,8,10,3,3,3,10,3,0,4,4,11,2,5,14,0} ;
                        int x[]                   = {0,7,0,7,18,0,1,0,0,7,10,7,0,2,0,0,7,7,7,0,0,0,18,0,0} ;
                        int expected__[]          = {7, 18, 7, 0, 0, 18, 0, 7, 10, 2, 0, 0, 0, 0, 1 };

                        std::clock_t start__      = std::clock();
                        std::vector <int> received__   = HiddenRabbits().whereAreTheRabbits(std::vector <int>(p, p + (sizeof p / sizeof p[0])), m, std::vector <int>(r, r + (sizeof r / sizeof r[0])), std::vector <int>(a, a + (sizeof a / sizeof a[0])), std::vector <int>(b, b + (sizeof b / sizeof b[0])), std::vector <int>(x, x + (sizeof x / sizeof x[0])));
                        return verify_case(casenum__, std::vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
                    }

                    // custom cases

                    /*      case 6: {
                            int p[]                   = ;
                            int m                     = ;
                            int r[]                   = ;
                            int a[]                   = ;
                            int b[]                   = ;
                            int x[]                   = ;
                            int expected__[]          = ;

                            std::clock_t start__      = std::clock();
                            std::vector <int> received__   = HiddenRabbits().whereAreTheRabbits(std::vector <int>(p, p + (sizeof p / sizeof p[0])), m, std::vector <int>(r, r + (sizeof r / sizeof r[0])), std::vector <int>(a, a + (sizeof a / sizeof a[0])), std::vector <int>(b, b + (sizeof b / sizeof b[0])), std::vector <int>(x, x + (sizeof x / sizeof x[0])));
                            return verify_case(casenum__, std::vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
                            }*/
                    /*      case 7: {
                            int p[]                   = ;
                            int m                     = ;
                            int r[]                   = ;
                            int a[]                   = ;
                            int b[]                   = ;
                            int x[]                   = ;
                            int expected__[]          = ;

                            std::clock_t start__      = std::clock();
                            std::vector <int> received__   = HiddenRabbits().whereAreTheRabbits(std::vector <int>(p, p + (sizeof p / sizeof p[0])), m, std::vector <int>(r, r + (sizeof r / sizeof r[0])), std::vector <int>(a, a + (sizeof a / sizeof a[0])), std::vector <int>(b, b + (sizeof b / sizeof b[0])), std::vector <int>(x, x + (sizeof x / sizeof x[0])));
                            return verify_case(casenum__, std::vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
                            }*/
                    /*      case 8: {
                            int p[]                   = ;
                            int m                     = ;
                            int r[]                   = ;
                            int a[]                   = ;
                            int b[]                   = ;
                            int x[]                   = ;
                            int expected__[]          = ;

                            std::clock_t start__      = std::clock();
                            std::vector <int> received__   = HiddenRabbits().whereAreTheRabbits(std::vector <int>(p, p + (sizeof p / sizeof p[0])), m, std::vector <int>(r, r + (sizeof r / sizeof r[0])), std::vector <int>(a, a + (sizeof a / sizeof a[0])), std::vector <int>(b, b + (sizeof b / sizeof b[0])), std::vector <int>(x, x + (sizeof x / sizeof x[0])));
                            return verify_case(casenum__, std::vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
                            }*/
            default:
                    return -1;
        }
    }
}

// }}}

// {{{ main
#include <cstdlib>
int main(int argc, char *argv[]) {
    if (argc == 1) {
        moj_harness::run_test();
    } else {
        for (int i=1; i<argc; ++i)
            moj_harness::run_test(std::atoi(argv[i]));
    }
}
// END CUT HERE
// }}}
