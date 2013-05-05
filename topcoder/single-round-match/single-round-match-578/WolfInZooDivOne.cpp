#include <cmath>
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <string>
#include <sstream>
#include <vector>
#include <queue>
#include <list>
#include <deque>
#include <utility>
#include <set>
#include <map>
using namespace std;

#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

struct WolfInZooDivOne {
    static const int N = 301;
    static const int MOD = 1000000000 + 7;

    vector <int> build(vector <string> strings) {
        string buffer;
        foreach (iter, strings) {
            buffer += *iter;
        }
        stringstream ss(buffer);
        int token;
        vector <int> ret;
        while (ss >> token) {
            ret.push_back(token);
        }
        return ret;
    }

    vector <int> left, right;
    int ways[2][N][N];

    int count(int n, vector <string> left_string, vector <string> right_string) {
        left = build(left_string);
        right = build(right_string);
        int m = left.size();
        vector <int> extend(n, -1);
        for (int i = 0; i < m; ++ i) {
            extend[left[i]] = max(extend[left[i]], right[i]);
        }
        for (int i = 1; i < n; ++ i) {
            extend[i] = max(extend[i], extend[i - 1]);
        }
        memset(ways, 0, sizeof(ways));
        ways[0][0][0] = 1;
        for (int i = 0; i < n; ++ i) {
            memset(ways[i + 1 & 1], 0, sizeof(ways[i + 1 & 1]));
            for (int x = -1; x < i; ++ x) {
                for (int y = -1; y < i; ++ y) {
                    if (!ways[i & 1][x + 1][y + 1]) {
                        continue;
                    }
                    (ways[i + 1 & 1][x + 1][y + 1] += ways[i & 1][x + 1][y + 1]) %= MOD;
                    if (x == -1 || extend[x] < i) {
                        (ways[i + 1 & 1][y + 1][i + 1] += ways[i & 1][x + 1][y + 1]) %= MOD;
                    }
                }
            }
        }
        int answer = 0;
        for (int x = -1; x < n; ++ x) {
            for (int y = -1; y < n; ++ y) {
                (answer += ways[n & 1][1 + x][1 + y]) %= MOD;
            }
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
			int N                     = 5;
			string L[]                = {"0"};
			string R[]                = {"4"};
			int expected__            = 16;

			clock_t start__           = clock();
			int received__            = WolfInZooDivOne().count(N, vector <string>(L, L + (sizeof L / sizeof L[0])), vector <string>(R, R + (sizeof R / sizeof R[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 1: {
			int N                     = 5;
			string L[]                = {"0 1"};
			string R[]                = {"2 4"};
			int expected__            = 21;

			clock_t start__           = clock();
			int received__            = WolfInZooDivOne().count(N, vector <string>(L, L + (sizeof L / sizeof L[0])), vector <string>(R, R + (sizeof R / sizeof R[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 2: {
			int N                     = 10;
			string L[]                = {"0 4 2 7"};
			string R[]                = {"3 9 5 9"};
			int expected__            = 225;

			clock_t start__           = clock();
			int received__            = WolfInZooDivOne().count(N, vector <string>(L, L + (sizeof L / sizeof L[0])), vector <string>(R, R + (sizeof R / sizeof R[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 3: {
			int N                     = 100;
			string L[]                = {"0 2 2 7 10 1","3 16 22 30 33 38"," 42 44 49 51 57 60 62"," 65 69 72 74 77 7","8 81 84 88 91 93 96"};
			string R[]                = {"41 5 13 22 12 13 ","33 41 80 47 40 ","4","8 96 57 66 ","80 60 71 79"," 70 77 ","99"," 83 85 93 88 89 97 97 98"};
			int expected__            = 6419882;

			clock_t start__           = clock();
			int received__            = WolfInZooDivOne().count(N, vector <string>(L, L + (sizeof L / sizeof L[0])), vector <string>(R, R + (sizeof R / sizeof R[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}

		// custom cases

/*      case 4: {
			int N                     = ;
			string L[]                = ;
			string R[]                = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = WolfInZooDivOne().count(N, vector <string>(L, L + (sizeof L / sizeof L[0])), vector <string>(R, R + (sizeof R / sizeof R[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 5: {
			int N                     = ;
			string L[]                = ;
			string R[]                = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = WolfInZooDivOne().count(N, vector <string>(L, L + (sizeof L / sizeof L[0])), vector <string>(R, R + (sizeof R / sizeof R[0])));
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 6: {
			int N                     = ;
			string L[]                = ;
			string R[]                = ;
			int expected__            = ;

			clock_t start__           = clock();
			int received__            = WolfInZooDivOne().count(N, vector <string>(L, L + (sizeof L / sizeof L[0])), vector <string>(R, R + (sizeof R / sizeof R[0])));
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
