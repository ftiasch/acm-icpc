#include <cmath>
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <cassert>
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <queue>
#include <list>
#include <deque>
#include <utility>
#include <set>
#include <map>

#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) (int)(v).size()

using namespace std;

const int D = 7 * (7 * 2 + 1);
const int N = D * D * 2 + 2;
const int M = (D * D * 5 + 6) * 2;

const int DELTA[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

int mark[D][D], edge_count, first_edge[N], to[M], capacity[M], next_edge[M], level[N], current[N];

struct FoxAndCake {
    void _add_edge(int u, int v, int w) {
        assert(edge_count < M);
        to[edge_count] = v;
        capacity[edge_count] = w;
        next_edge[edge_count] = first_edge[u];
        first_edge[u] = edge_count ++;
    }

    void add_edge(int a, int b, int c) {
        assert(a < N && b < N);
        _add_edge(a, b, c);
        _add_edge(b, a, 0);
    }

    bool bfs(int source, int target) {
        memset(level, -1, sizeof(level));
        level[target] = 0;
        queue <int> q;
        q.push(target);
        while (!q.empty()) {
            int u = q.front();
            q.pop();
            for (int iter = first_edge[u]; iter != -1; iter = next_edge[iter]) {
                if (capacity[iter ^ 1] > 0 && level[to[iter]] == -1) {
                    level[to[iter]] = level[u] + 1;
                    q.push(to[iter]);
                }
            }
        }
        return level[source] != -1;
    }

    int dfs(int u, int target, int left) {
        if (u == target) {
            return left;
        }
        int delta = 0;
        for (int &iter = current[u]; iter != -1; iter = next_edge[iter]) {
            if (capacity[iter] > 0 && level[u] == level[to[iter]] + 1) {
                int ret = dfs(to[iter], target, min(left - delta, capacity[iter]));
                delta += ret;
                capacity[iter] -= ret;
                capacity[iter ^ 1] += ret;
                if (delta == left) {
                    break;
                }
            }
        }
        return delta;
    }


    string ableToDivide(int n, int m, vector <int> x, vector <int> y) {
        vector <int> xs, ys;
        for (int i = 0; i < 7; ++ i) {
            for (int d = -7; d <= 7; ++ d) {
#define ADD(x, xs, n) if (1 <= (x) && (x) <= n) xs.push_back(x)
                ADD(x[i] + d, xs, n);
                ADD(y[i] + d, ys, m);
#undef ADD
            }
        }
#define UNIQUE(v) sort(ALL(v)); v.erase(unique(ALL(v)), (v).end());
        UNIQUE(xs);
        UNIQUE(ys);
#undef UNIQUE
#define FIND(x, xs) (lower_bound(ALL(xs), x) - (xs).begin())
        memset(mark, -1, sizeof(mark));
        for (int i = 0; i < 7; ++ i) {
            x[i] = FIND(x[i], xs);
            y[i] = FIND(y[i], ys);
            mark[x[i]][y[i]] = i;
        }
#undef FIND
        n = SIZE(xs);
        m = SIZE(ys);
        int source = n * m * 2;
        int target = source + 1;
        edge_count = 0;
        memset(first_edge, -1, sizeof(first_edge));
#define GETID(x, y) ((x) * m + (y))
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (mark[i][j] == -1) {
                    add_edge(GETID(i, j), GETID(i, j) + n * m, 1);
                }
                for (int k = 0; k < 4; ++ k) {
                    int x = i + DELTA[k][0];
                    int y = j + DELTA[k][1];
                    if (0 <= x && x < n && 0 <= y && y < m) {
                        add_edge(GETID(i, j) + n * m, GETID(x, y), 1);
                    }
                }
            }
        }
        for (int i = 0; i < 3; ++ i) {
            add_edge(source, GETID(x[1 + i], y[1 + i]) + n * m, 1);
            add_edge(GETID(x[4 + i], y[4 + i]), target, 1);
        }
#undef GETID
        int flow = 0;
        while (bfs(source, target)) {
            memcpy(current, first_edge, sizeof(first_edge));
            flow += dfs(source, target, INT_MAX);
        }
        return flow == 3 ? "Yes" : "No";
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
			int n                     = 2;
			int m                     = 4;
			int x[]                   = {1,1,1,1,2,2,2};
			int y[]                   = {1,2,3,4,2,3,4};
			string expected__         = "Yes";

			clock_t start__           = clock();
			string received__         = FoxAndCake().ableToDivide(n, m, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 1: {
			int n                     = 2;
			int m                     = 4;
			int x[]                   = {1,1,2,1,2,1,2};
			int y[]                   = {1,2,2,3,3,4,4};
			string expected__         = "No";

			clock_t start__           = clock();
			string received__         = FoxAndCake().ableToDivide(n, m, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 2: {
			int n                     = 6;
			int m                     = 6;
			int x[]                   = {1,1,3,4,3,4,5};
			int y[]                   = {2,6,4,5,5,4,2};
			string expected__         = "Yes";

			clock_t start__           = clock();
			string received__         = FoxAndCake().ableToDivide(n, m, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 3: {
			int n                     = 999999999;
			int m                     = 999999999;
			int x[]                   = {500000000,1,1,1,999999999,999999999,999999999};
			int y[]                   = {500000000,1,2,3,999999997,999999998,999999999};
			string expected__         = "Yes";

			clock_t start__           = clock();
			string received__         = FoxAndCake().ableToDivide(n, m, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 4: {
			int n                     = 1000000000;
			int m                     = 1000000000;
			int x[]                   = {500000000,1,1,2,999999998,999999999,999999999};
			int y[]                   = {500000000,1,2,1,999999999,999999998,999999999};
			string expected__         = "No";

			clock_t start__           = clock();
			string received__         = FoxAndCake().ableToDivide(n, m, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}

		// custom cases

      case 5: {
			int n                     = 1000000000;
			int m                     = 172338832;
			int x[]                   = {892602377, 492378630, 747375353, 903480024, 196470835, 960508028, 183680245};
			int y[]                   = {5476503, 8296254, 19262138, 161348532, 66867705, 35333922, 45069449};
			string expected__         = "Yes";

			clock_t start__           = clock();
			string received__         = FoxAndCake().ableToDivide(n, m, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
/*      case 6: {
			int n                     = ;
			int m                     = ;
			int x[]                   = ;
			int y[]                   = ;
			string expected__         = ;

			clock_t start__           = clock();
			string received__         = FoxAndCake().ableToDivide(n, m, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 7: {
			int n                     = ;
			int m                     = ;
			int x[]                   = ;
			int y[]                   = ;
			string expected__         = ;

			clock_t start__           = clock();
			string received__         = FoxAndCake().ableToDivide(n, m, vector <int>(x, x + (sizeof x / sizeof x[0])), vector <int>(y, y + (sizeof y / sizeof y[0])));
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
