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

const int MOD = (int)1e9 + 7;

const int N = 50;

int ways[N + 1][N + 1];
bool valid[N][N + 1];

struct TwoConvexShapes {
    int solve(std::vector <std::string> grid) {
        int n = SIZE(grid);
        int m = SIZE(grid[0]);
        memset(ways, 0, sizeof(ways));
        ways[0][0] = 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j <= m; ++ j) {
                valid[i][j] = true;
                for (int k = 0; k < j; ++ k) {
                    valid[i][j] &= grid[i][k] != 'W';
                }
                for (int k = j; k < m; ++ k) {
                    valid[i][j] &= grid[i][k] != 'B';
                }
            }
            for (int j = 0; j <= m; ++ j) {
                for (int k = j; k <= m; ++ k) {
                    if (valid[i][k]) {
                        (ways[i + 1][k] += ways[i][j]) %= MOD;
                    }
                }
            }
        }
        int result = 0;
        for (int j = 0; j <= m; ++ j) {
            (result += ways[n][j]) %= MOD;
        }
        return result;
    }

    int countWays(vector <string> grid) {
        int n = SIZE(grid);
        int m = SIZE(grid[0]);
        int result = 0;
        for (int _ = 0; _ < 2; ++ _) {
            for (int __ = 0; __ < 2; ++ __) {
                (result += solve(grid)) %= MOD;
                std::reverse(ALL(grid));
            }
            for (int j = 0; j <= m; ++ j) {
                bool v = true;
                for (int i = 0; i < n; ++ i) {
                    v &= valid[i][j];
                }
                if (v) {
                    (result += MOD - 1) %= MOD;
                }
            }
            for (int i = 0; i < n; ++ i) {
                std::reverse(ALL(grid[i]));
            }
        }
        for (int _ = 0; _ < 2; ++ _) {
            for (int i = 1; i < n; ++ i) {
                bool v = true;
                for (int j = 0; j < m; ++ j) {
                    for (int k = 0; k < i; ++ k) {
                        v &= grid[k][j] != 'W';
                    }
                    for (int k = i; k < n; ++ k) {
                        v &= grid[k][j] != 'B';
                    }
                }
                if (v) {
                    (result += MOD - 1) %= MOD;
                }
            }
            std::reverse(ALL(grid));
        }
        bool w = false;
        bool b = false;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                w |= grid[i][j] == 'W';
                b |= grid[i][j] == 'B';
            }
        }
        if (!w) {
            (result += MOD - 1) %= MOD;
        }
        if (!b) {
            (result += MOD - 1) %= MOD;
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
			string grid[]             = {"??",
 "??"};
			int expected__            = 14;

			clock_t start__           = clock();
			int received__            = TwoConvexShapes().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 1: {
			string grid[]             = {"B?",
 "??"};
			int expected__            = 7;

			clock_t start__           = clock();
			int received__            = TwoConvexShapes().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 2: {
			string grid[]             = {"WWB",
 "WWW",
 "WWW",
 "WWW"};
			int expected__            = 1;

			clock_t start__           = clock();
			int received__            = TwoConvexShapes().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 3: {
			string grid[]             = {"BBBBBB",
 "WWBBBB",
 "WBBBBB"};
			int expected__            = 0;

			clock_t start__           = clock();
			int received__            = TwoConvexShapes().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 4: {
			string grid[]             = {"?BB?",
 "BBBB",
 "?BB?"};
			int expected__            = 5;

			clock_t start__           = clock();
			int received__            = TwoConvexShapes().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 5: {
			string grid[]             = {"?WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
 "B?WWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
 "BB?WWWWWWWWWWWWWWWWWWWWWWWWWWWW",
 "BBB?WWWWWWWWWWWWWWWWWWWWWWWWWWW",
 "BBBB?WWWWWWWWWWWWWWWWWWWWWWWWWW",
 "BBBBB?WWWWWWWWWWWWWWWWWWWWWWWWW",
 "BBBBBB?WWWWWWWWWWWWWWWWWWWWWWWW",
 "BBBBBBB?WWWWWWWWWWWWWWWWWWWWWWW",
 "BBBBBBBB?WWWWWWWWWWWWWWWWWWWWWW",
 "BBBBBBBBB?WWWWWWWWWWWWWWWWWWWWW",
 "BBBBBBBBBB?WWWWWWWWWWWWWWWWWWWW",
 "BBBBBBBBBBB?WWWWWWWWWWWWWWWWWWW",
 "BBBBBBBBBBBB?WWWWWWWWWWWWWWWWWW",
 "BBBBBBBBBBBBB?WWWWWWWWWWWWWWWWW",
 "BBBBBBBBBBBBBB?WWWWWWWWWWWWWWWW",
 "BBBBBBBBBBBBBBB?WWWWWWWWWWWWWWW",
 "BBBBBBBBBBBBBBBB?WWWWWWWWWWWWWW",
 "BBBBBBBBBBBBBBBBB?WWWWWWWWWWWWW",
 "BBBBBBBBBBBBBBBBBB?WWWWWWWWWWWW",
 "BBBBBBBBBBBBBBBBBBB?WWWWWWWWWWW",
 "BBBBBBBBBBBBBBBBBBBB?WWWWWWWWWW",
 "BBBBBBBBBBBBBBBBBBBBB?WWWWWWWWW",
 "BBBBBBBBBBBBBBBBBBBBBB?WWWWWWWW",
 "BBBBBBBBBBBBBBBBBBBBBBB?WWWWWWW",
 "BBBBBBBBBBBBBBBBBBBBBBBB?WWWWWW",
 "BBBBBBBBBBBBBBBBBBBBBBBBB?WWWWW",
 "BBBBBBBBBBBBBBBBBBBBBBBBBB?WWWW",
 "BBBBBBBBBBBBBBBBBBBBBBBBBBB?WWW",
 "BBBBBBBBBBBBBBBBBBBBBBBBBBBB?WW",
 "BBBBBBBBBBBBBBBBBBBBBBBBBBBBB?W"};
			int expected__            = 73741817;

			clock_t start__           = clock();
			int received__            = TwoConvexShapes().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}

		// custom cases

/*      case 6: {
			string grid[]             = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = TwoConvexShapes().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 7: {
			string grid[]             = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = TwoConvexShapes().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 8: {
			string grid[]             = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = TwoConvexShapes().countWays(vector <string>(grid, grid + (sizeof grid / sizeof grid[0])));
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
