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

struct TheLargestString {
    static const int N = 50;

    typedef std::pair <string, string> Pair;

    void update(Pair &x, const Pair &a) {
        x = std::max(x, a);
    }

    string find(string s, string t) {
        int n = SIZE(s);
        Pair maximum[N + 1][N + 1];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j <= i; ++ j) {
                update(maximum[i + 1][j], maximum[i][j]);
                update(maximum[i + 1][j + 1], std::make_pair(maximum[i][j].first + s[i], maximum[i][j].second + t[i]));
            }
        }
        string answer;
        for (int i = 1; i <= n; ++ i) {
            answer = std::max(answer, maximum[n][i].first + maximum[n][i].second);
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
			string s                  = "ab";
			string t                  = "zy";
			string expected__         = "by";

			clock_t start__           = clock();
			string received__         = TheLargestString().find(s, t);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 1: {
			string s                  = "abacaba";
			string t                  = "zzzaaaa";
			string expected__         = "cbaaaa";

			clock_t start__           = clock();
			string received__         = TheLargestString().find(s, t);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 2: {
			string s                  = "x";
			string t                  = "x";
			string expected__         = "xx";

			clock_t start__           = clock();
			string received__         = TheLargestString().find(s, t);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}
		case 3: {
			string s                  = "abbabbabbababaaaabbababab";
			string t                  = "bababbaabbbababbbbababaab";
			string expected__         = "bbbbbbbbbbbbbbbbbbaaab";

			clock_t start__           = clock();
			string received__         = TheLargestString().find(s, t);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}

		// custom cases

/*      case 4: {
			string s                  = ;
			string t                  = ;
			string expected__         = ;

			clock_t start__           = clock();
			string received__         = TheLargestString().find(s, t);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 5: {
			string s                  = ;
			string t                  = ;
			string expected__         = ;

			clock_t start__           = clock();
			string received__         = TheLargestString().find(s, t);
			return verify_case(casenum__, expected__, received__, clock()-start__);
		}*/
/*      case 6: {
			string s                  = ;
			string t                  = ;
			string expected__         = ;

			clock_t start__           = clock();
			string received__         = TheLargestString().find(s, t);
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
