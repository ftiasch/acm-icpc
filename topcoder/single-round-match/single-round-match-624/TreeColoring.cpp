#include <algorithm>
#include <cassert>
#include <climits>
#include <cstdlib>
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

int seed;

int next_random() {
    return seed = (1999LL * seed + 17) % 1000003;
}

const int N = 100000;

int edge_count, first_edge[N], to[N << 1], length[N << 1], next_edge[N << 1];

void add_edge(int u, int v, int w) {
    to[edge_count] = v;
    length[edge_count] = w;
    next_edge[edge_count] = first_edge[u];
    first_edge[u] = edge_count ++;
}

bool visited[N];
int size[N], balance[N];
std::vector <int> nodes;

void prepare(int p, int u) {
    size[u] = 1;
    balance[u] = 0;
    nodes.push_back(u);
    for (int iterator = first_edge[u]; ~iterator; iterator = next_edge[iterator]) {
        int v = to[iterator];
        if (!visited[v] && v != p) {
            prepare(u, v);
            size[u] += size[v];
            balance[u] = std::max(balance[u], size[v]);
        }
    }
}

struct Data {
    int ancestor, subtree;
    long long distance;
};

Data now;

std::vector <Data> ancestors[N];

void search(int p, int u, long long d) {
    now.distance = d;
    ancestors[u].push_back(now);
    for (int iterator = first_edge[u]; ~iterator; iterator = next_edge[iterator]) {
        int v = to[iterator];
        if (!visited[v] && v != p) {
            search(u, v, d + length[iterator]);
        }
    }
}

void divide(int root) {
    nodes.clear();
    prepare(-1, root);
    for (int v : nodes) {
        balance[v] = std::max(balance[v], size[root] - size[v]);
    }
    for (int v : nodes) {
        if (balance[v] < balance[root]) {
            root = v;
        }
    }
    visited[root] = true;
    now.ancestor = root;
    now.subtree = -1;
    now.distance = 0;
    ancestors[root].push_back(now);
    for (int iterator = first_edge[root]; ~iterator; iterator = next_edge[iterator]) {
        int v = to[iterator];
        if (!visited[v]) {
            now.subtree = v;
            search(root, v, length[iterator]);
        }
    }
    for (int iterator = first_edge[root]; ~iterator; iterator = next_edge[iterator]) {
        int v = to[iterator];
        if (!visited[v]) {
            divide(v);
        }
    }
}

bool blue[N];
std::map <int, int> count[N];
std::map <int, long long>sum[N];

struct TreeColoring {
    long long color(int n, int q, int start_seed, int threshold, int max_distance) {
        memset(first_edge, -1, sizeof(first_edge));
        edge_count = 0;
        ::seed = start_seed;
        for (int i = 0; i < n - 1; ++ i) {
            int distance = next_random() % max_distance;
            int parent = next_random();
            if (parent < threshold) {
                parent = i;
            } else {
                parent %= (i + 1);
            }
            add_edge(i + 1, parent, distance);
            add_edge(parent, i + 1, distance);
        }
        memset(visited, false, sizeof(visited));
        for (int i = 0; i < n; ++ i) {
            ancestors[i].clear();
            count[i].clear();
            sum[i].clear();
        }
        divide(0);
        memset(blue, 0, sizeof(blue));
        long long result = 0;
        for (int i = 0; i < q; ++ i) {
            int type = next_random() % 2;
            int v = next_random() % n;
            if (type == 0) {
                if (!blue[v]) {
                    blue[v] = true;
                    for (auto p : ancestors[v]) {
                        count[p.ancestor][-1] ++;
                        sum[p.ancestor][-1] += p.distance;
                        if (~p.subtree) {
                            count[p.ancestor][p.subtree] ++;
                            sum[p.ancestor][p.subtree] += p.distance;
                        }
                    }
                }
            } else if (type == 1) {
                long long total = 0;
                for (auto p : ancestors[v]) {
                    total += sum[p.ancestor][-1] + (long long)p.distance * count[p.ancestor][-1];
                    if (~p.subtree) {
                        total -= sum[p.ancestor][p.subtree] + (long long)p.distance * count[p.ancestor][p.subtree];
                    }
                }
                result ^= total;
            }
        }
        return result;
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
            int N                     = 4;
            int Q                     = 6;
            int startSeed             = 15;
            int threshold             = 2;
            int maxDist               = 5;
            long long expected__      = 7;

            clock_t start__           = clock();
            long long received__      = TreeColoring().color(N, Q, startSeed, threshold, maxDist);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int N                     = 4;
            int Q                     = 5;
            int startSeed             = 2;
            int threshold             = 9;
            int maxDist               = 10;
            long long expected__      = 30;

            clock_t start__           = clock();
            long long received__      = TreeColoring().color(N, Q, startSeed, threshold, maxDist);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int N                     = 8;
            int Q                     = 8;
            int startSeed             = 3;
            int threshold             = 5;
            int maxDist               = 7;
            long long expected__      = 6;

            clock_t start__           = clock();
            long long received__      = TreeColoring().color(N, Q, startSeed, threshold, maxDist);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int N                     = 14750;
            int Q                     = 50;
            int startSeed             = 29750;
            int threshold             = 1157;
            int maxDist               = 21610;
            long long expected__      = 2537640;

            clock_t start__           = clock();
            long long received__      = TreeColoring().color(N, Q, startSeed, threshold, maxDist);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int N                     = 100000;
            int Q                     = 100000;
            int startSeed             = 123456;
            int threshold             = 500000;
            int maxDist               = 474747;
            long long expected__      = 726915029831LL;

            clock_t start__           = clock();
            long long received__      = TreeColoring().color(N, Q, startSeed, threshold, maxDist);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            int N                     = 100000;
            int Q                     = 100000;
            int startSeed             = 654321;
            int threshold             = 1000003;
            int maxDist               = 1000003;
            long long expected__      = 562600687570528LL;

            clock_t start__           = clock();
            long long received__      = TreeColoring().color(N, Q, startSeed, threshold, maxDist);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            int N                     = ;
            int Q                     = ;
            int startSeed             = ;
            int threshold             = ;
            int maxDist               = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TreeColoring().color(N, Q, startSeed, threshold, maxDist);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int N                     = ;
            int Q                     = ;
            int startSeed             = ;
            int threshold             = ;
            int maxDist               = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TreeColoring().color(N, Q, startSeed, threshold, maxDist);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            int N                     = ;
            int Q                     = ;
            int startSeed             = ;
            int threshold             = ;
            int maxDist               = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TreeColoring().color(N, Q, startSeed, threshold, maxDist);
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
