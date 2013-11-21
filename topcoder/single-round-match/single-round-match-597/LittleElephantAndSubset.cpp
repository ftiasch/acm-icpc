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
#define SIZE(v) (int)((v).size())
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

using std::cerr;
using std::endl;
using std::vector;
using std::string;

struct LittleElephantAndSubset {
    static const int MOD = (int)1e9 + 7;

    std::vector <int> digits;
    int count[1 << 10], memory[2][11][1 << 10], ways[1 << 10];

    int go(bool is_smaller, bool is_first, int length, int mask) {
        if (length) {
            if (is_smaller && memory[is_first][length][mask] != -1) {
                return memory[is_first][length][mask];
            }
            int ret = 0;
            for (int d = 0; d < 10; ++ d) {
                if (is_first && d == 0) {
                    continue;
                }
                if (!is_smaller && d > digits[length - 1]) {
                    continue;
                }
                if (mask >> d & 1) {
                    ret += go(is_smaller || d < digits[length - 1], false, length - 1, mask ^ (1 << d));
                }
            }
            if (is_smaller) {
                memory[is_first][length][mask] = ret;
            }
            return ret;
        } else {
            return is_smaller && mask == 0;
        }
    }

    int getNumber(int n) {
        n ++;
        while (n) {
            digits.push_back(n % 10);
            n /= 10;
        }
        memset(memory, -1, sizeof(memory));
        memset(count, 0, sizeof(count));
        for (int mask = 0; mask < 1 << 10; ++ mask) {
            for (int length = 1; length <= (int)digits.size(); ++ length) {
                count[mask] += go(length < (int)digits.size(), true, length, mask);
            }
        }
        memset(ways, 0, sizeof(ways));
        ways[0] = 1;
        for (int mask = 1; mask < 1 << 10; ++ mask) {
            int left = (1 << 10) - 1 ^ mask;
            for (int super = left; ; super = super - 1 & left) {
                (ways[mask | super] += (long long)ways[super] * count[mask] % MOD) %= MOD;
                if (super == 0) {
                    break;
                }
            }
        }
        int answer = 0;
        for (int mask = 1; mask < 1 << 10; ++ mask) {
            (answer += ways[mask]) %= MOD;
        }
        return answer;
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
			int N                     = 3;
			int expected__            = 7;

			clock_t start__           = clock();
			int received__            = LittleElephantAndSubset().getNumber(N);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 1: {
			int N                     = 10;
			int expected__            = 767;

			clock_t start__           = clock();
			int received__            = LittleElephantAndSubset().getNumber(N);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 2: {
			int N                     = 47;
			int expected__            = 25775;

			clock_t start__           = clock();
			int received__            = LittleElephantAndSubset().getNumber(N);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 3: {
			int N                     = 4777447;
			int expected__            = 66437071;

			clock_t start__           = clock();
			int received__            = LittleElephantAndSubset().getNumber(N);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}

		// custom cases

/*      case 4: {
			int N                     = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = LittleElephantAndSubset().getNumber(N);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 5: {
			int N                     = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = LittleElephantAndSubset().getNumber(N);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 6: {
			int N                     = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = LittleElephantAndSubset().getNumber(N);
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
