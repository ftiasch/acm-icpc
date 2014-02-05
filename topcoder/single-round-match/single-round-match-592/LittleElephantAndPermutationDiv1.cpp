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

const int N = 50;
const int M = 1950;

const int MOD = (int)1e9 + 7;

int ways[2][N + 1][M + 1];

void update(int &x, int a) {
    x += a;
    if (x >= MOD) {
        x -= MOD;
    }
}

struct LittleElephantAndPermutationDiv1 {
    int getNumber(int n, int bound) {
        memset(ways, 0, sizeof(ways));
        ways[0][0][0] = 1;
        for (int i = 1; i <= n; ++ i) {
            memset(ways[i & 1], 0, sizeof(ways[i & 1]));
            // xxxxx???...
            // ???xxxxx...
            for (int a = 0; a < i; ++ a) {
                for (int s = 0; s <= M; ++ s) {
                    long long way = ways[i + 1 & 1][a][s];
                    if (way) {
                        int e = n - i + 1 - a;
                        if (a) {
                            update(ways[i & 1][a - 1][s + i + i], way * a * a % MOD);
                        }
                        update(ways[i & 1][a][s + i], way * a * e * 2 % MOD);
                        update(ways[i & 1][a + 1][s], way * e * (e - 1) % MOD);
                        update(ways[i & 1][a][s + i], way * e % MOD);
                    }
                }
            }
        }
        int result = 0;
        for (int s = bound; s <= M; ++ s) {
            update(result, ways[n & 1][0][s]);
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
			int N                     = 1;
			int K                     = 1;
			int expected__            = 1;

			clock_t start__           = clock();
			int received__            = LittleElephantAndPermutationDiv1().getNumber(N, K);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 1: {
			int N                     = 2;
			int K                     = 1;
			int expected__            = 4;

			clock_t start__           = clock();
			int received__            = LittleElephantAndPermutationDiv1().getNumber(N, K);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 2: {
			int N                     = 3;
			int K                     = 8;
			int expected__            = 18;

			clock_t start__           = clock();
			int received__            = LittleElephantAndPermutationDiv1().getNumber(N, K);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 3: {
			int N                     = 10;
			int K                     = 74;
			int expected__            = 484682624;

			clock_t start__           = clock();
			int received__            = LittleElephantAndPermutationDiv1().getNumber(N, K);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 4: {
			int N                     = 50;
			int K                     = 1000;
			int expected__            = 539792695;

			clock_t start__           = clock();
			int received__            = LittleElephantAndPermutationDiv1().getNumber(N, K);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}

		// custom cases

/*      case 5: {
			int N                     = ;
			int K                     = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = LittleElephantAndPermutationDiv1().getNumber(N, K);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 6: {
			int N                     = ;
			int K                     = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = LittleElephantAndPermutationDiv1().getNumber(N, K);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 7: {
			int N                     = ;
			int K                     = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = LittleElephantAndPermutationDiv1().getNumber(N, K);
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
