#include <cstdio>
#include <cstring>
#include <functional>
#include <iostream>
#include <limits>
#include <queue>
#include <string>
#include <vector>

#define ALL(v) (v).begin(), (v).end()

using std::cerr;
using std::endl;
using std::ostream;
using std::string;
using std::vector;

// {{{ Network
template<typename Type>
class Network
{
    public:
    Network(int n) : n(n), head(n, -1)
    {
    }

        void add_edge(int u, int v, Type w)
        {
            add_edge_(u, v, w);
            add_edge_(v, u, 0);
        }

        Type max_flow(int source, int target)
        {
            Type result = 0;
            while (true) {
                std::vector<int> level(n, -1);
                level.at(target) = 0;
                std::queue<int> queue;
                queue.push(target);
                while (!queue.empty() && !~level.at(source)) {
                    int v = queue.front();
                    queue.pop();
                    for (int iterator = head.at(v); ~iterator; ) {
                        auto&& edge = edges.at(iterator);
                        int u = edges.at(iterator).to;
                        if (edges.at(iterator ^ 1).capacity > 0 && !~level.at(u)) {
                            level.at(u) = level.at(v) + 1;
                            queue.push(u);
                        }
                        iterator = edge.next;
                    }
                }
                if (!~level.at(source)) {
                    break;
                }
                std::vector<int> current_head(head);
                result += dfs(level, current_head, source, target, std::numeric_limits<Type>::max());
            }
            return result;
        }

        struct Edge
        {
            Edge(int to, Type capacity, int next) : to(to), capacity(capacity), next(next) {}

            int to;
            Type capacity;
            int next;
        };

        void add_edge_(int u, int v, Type w)
        {
            edges.emplace_back(v, w, head.at(u));
            head.at(u) = static_cast<int>(edges.size()) - 1;
        }

        Type dfs(const std::vector<int>& level, std::vector<int>& head, int u, int target, Type delta)
        {
            if (u == target) {
                return delta;
            }
            Type left = delta;
            for (int& iterator = head.at(u); ~iterator; ) {
                auto& edge = edges.at(iterator);
                int v = edge.to;
                if (edge.capacity > 0 && level.at(u) == level.at(v) + 1) {
                    auto ret = dfs(level, head, v, target, std::min(left, edge.capacity));
                    edge.capacity -= ret;
                    edges.at(iterator ^ 1).capacity += ret;
                    left -= ret;
                    if (!left) {
                        return delta;
                    }
                }
                iterator = edge.next;
            }
            return delta - left;
        }

        std::vector<int> get_edges(int u)
        {
            std::vector<int> result;
            for (int iterator = head.at(u); ~iterator; ) {
                auto&& edge = edges.at(iterator);
                if (!edge.capacity) {
                    result.push_back(edge.to);
                }
                iterator = edge.next;
            }
            return result;
        }

        int n;
        std::vector<int> head;
        std::vector<Edge> edges;
};
// }}}

struct BearGridRect
{
    std::vector<int> findPermutation
    ( int n
    , const std::vector<int>& r1
    , const std::vector<int>& r2
    , const std::vector<int>& c1
    , const std::vector<int>& c2
    , const std::vector<int>& count
    )
    {
        int m = count.size();
        Network<int> network(n + m + 1 << 1);
        for (int x = 0; x < n; ++ x) {
            for (int y = 0; y < n; ++ y) {
                int i = 0;
                while (i < m && (x < r1[i] || x > r2[i] || y < c1[i] || y > c2[i])) {
                    i ++;
                }
                if (i >= m) {
                    network.add_edge(x, n + y, 1);
                }
            }
        }
        for (int i = 0; i < m; ++ i) {
            for (int x = r1[i]; x <= r2[i]; ++ x) {
                network.add_edge(x, n + n + i, 1);
            }
            for (int y = c1[i]; y <= c2[i]; ++ y) {
                network.add_edge(n + n + m + i, n + y, 1);
            }
        }
        int source = n + m << 1;
        int target = source + 1;
        for (int i = 0; i < n; ++ i) {
            network.add_edge(source, i, 1);
            network.add_edge(n + i, target, 1);
        }
        for (int i = 0; i < m; ++ i) {
            network.add_edge(n + n + i, target, count[i]);
            network.add_edge(source, n + n + m + i, count[i]);
        }
        int total = n;
        for (int i = 0; i < m; ++ i) {
            total += count[i];
        }
        if (network.max_flow(source, target) == total) {
            std::vector<int> result(n);
            std::vector<std::vector<int>> rows(m);
            for (int i = 0; i < n; ++ i) {
                for (int j : network.get_edges(i)) {
                    if (n <= j && j < n + n) {
                        result[i] = j - n;
                    }
                    if (n + n <= j && j < n + n + m) {
                        rows[j - n - n].push_back(i);
                    }
                }
            }
            for (int i = 0; i < m; ++ i) {
                for (int j : network.get_edges(n + n + m + i)) {
                    if (n <= j && j < n + n) {
                        result[rows[i].back()] = j - n;
                        rows[i].pop_back();
                    }
                }
            }
            return result;
        } else {
            return {-1};
        }
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

    template<typename T> ostream& operator<<(ostream &os, const vector<T> &v) { os << "{"; for (typename vector<T>::const_iterator vi=v.begin(); vi!=v.end(); ++vi) { if (vi != v.begin()) os << ","; os << " " << *vi; } os << " }"; return os; }

    int verify_case(int casenum, const vector <int> &expected, const vector <int> &received, clock_t elapsed) {
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
            int N                     = 5;
            int r1[]                  = {4, 0};
            int r2[]                  = {4, 1};
            int c1[]                  = {2, 1};
            int c2[]                  = {4, 2};
            int cnt[]                 = {0, 2};
            int expected__[]          = {1, 2, 4, 3, 0 };

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 1: {
            int N                     = 5;
            int r1[]                  = {4, 0, 2};
            int r2[]                  = {4, 1, 2};
            int c1[]                  = {2, 1, 2};
            int c2[]                  = {4, 2, 2};
            int cnt[]                 = {0, 2, 1};
            int expected__[]          = {-1 };

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 2: {
            int N                     = 3;
            int r1[]                  = {0};
            int r2[]                  = {2};
            int c1[]                  = {0};
            int c2[]                  = {2};
            int cnt[]                 = {0};
            int expected__[]          = {-1 };

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 3: {
            int N                     = 4;
            int r1[]                  = {0};
            int r2[]                  = {0};
            int c1[]                  = {0};
            int c2[]                  = {0};
            int cnt[]                 = {0};
            int expected__[]          = {1, 0, 2, 3 };

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 4: {
            int N                     = 2;
            int r1[]                  = {0, 0, 1, 1};
            int r2[]                  = {0, 0, 1, 1};
            int c1[]                  = {0, 1, 0, 1};
            int c2[]                  = {0, 1, 0, 1};
            int cnt[]                 = {0, 1, 1, 0};
            int expected__[]          = {1, 0 };

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 5: {
            int N                     = 2;
            int r1[]                  = {0, 0, 1, 1};
            int r2[]                  = {0, 0, 1, 1};
            int c1[]                  = {0, 1, 0, 1};
            int c2[]                  = {0, 1, 0, 1};
            int cnt[]                 = {0, 1, 0, 1};
            int expected__[]          = {-1 };

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 6: {
            int N                     = 2;
            int r1[]                  = {0, 0, 1, 1};
            int r2[]                  = {0, 0, 1, 1};
            int c1[]                  = {0, 1, 0, 1};
            int c2[]                  = {0, 1, 0, 1};
            int cnt[]                 = {1, 1, 1, 1};
            int expected__[]          = {-1 };

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 7: {
            int N                     = 6;
            int r1[]                  = {0, 0, 4};
            int r2[]                  = {2, 3, 5};
            int c1[]                  = {0, 5, 1};
            int c2[]                  = {4, 5, 5};
            int cnt[]                 = {3, 1, 1};
            int expected__[]          = {1, 2, 4, 5, 3, 0 };

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }
        case 8: {
            int N                     = 3;
            int r1[]                  = {0};
            int r2[]                  = {0};
            int c1[]                  = {0};
            int c2[]                  = {0};
            int cnt[]                 = {2};
            int expected__[]          = {-1 };

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }

        // custom cases

/*      case 9: {
            int N                     = ;
            int r1[]                  = ;
            int r2[]                  = ;
            int c1[]                  = ;
            int c2[]                  = ;
            int cnt[]                 = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }*/
/*      case 10: {
            int N                     = ;
            int r1[]                  = ;
            int r2[]                  = ;
            int c1[]                  = ;
            int c2[]                  = ;
            int cnt[]                 = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
        }*/
/*      case 11: {
            int N                     = ;
            int r1[]                  = ;
            int r2[]                  = ;
            int c1[]                  = ;
            int c2[]                  = ;
            int cnt[]                 = ;
            int expected__[]          = ;

            clock_t start__           = clock();
            vector <int> received__   = BearGridRect().findPermutation(N, vector <int>(r1, r1 + (sizeof r1 / sizeof r1[0])), vector <int>(r2, r2 + (sizeof r2 / sizeof r2[0])), vector <int>(c1, c1 + (sizeof c1 / sizeof c1[0])), vector <int>(c2, c2 + (sizeof c2 / sizeof c2[0])), vector <int>(cnt, cnt + (sizeof cnt / sizeof cnt[0])));
            return verify_case(casenum__, vector <int>(expected__, expected__ + (sizeof expected__ / sizeof expected__[0])), received__, clock()-start__);
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
