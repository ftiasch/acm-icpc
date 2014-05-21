#include <list>
#include <map>
#include <set>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <vector>
#include <utility>
#include <complex>
#include <sstream>
#include <iostream>
#include <algorithm>
using namespace std;

#define ALL(v) (v).begin(), (v).end()
#define SIZE(v) (int)((v).size())
#define foreach(i, v) for (__typeof((v).begin()) i = (v).begin(); i != (v).end(); ++ i)

struct CharacterBoard {
    typedef long long LL;

    static const int MOD = (int)1e9 + 9;

    int n, m;

    void addDivisors(vector <LL> &divisors, LL n) {
        if (n <= 0) {
            return;
        }
        for (LL d = 1; d * d <= n; ++ d) {
            if (n % d == 0) {
                divisors.push_back(d);
                divisors.push_back(n / d);
            }
        }
    }

    int power(int a, int n) {
        int ret = 1;
        while (n) {
            if (n % 2 == 1) {
                ret = (long long)ret * a % MOD;
            }
            a = (long long)a * a % MOD;
            n >>= 1;
        }
        return ret;
    }

    int inverse(int a) {
        return a == 1 ? 1 : (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD;
    }

    int powerSum(int a, int n) {
        return (long long)(power(a, n) + MOD - 1) * inverse(a - 1) % MOD;
    }

    int countGenerators(vector <string> fragment, int w, int i0, int j0) {
        n = fragment.size();
        m = fragment[0].size();
        vector <LL> divisors;
        for (int i = 0; i < n; ++ i) {
            for (int j = -m + 1; j < m; ++ j) {
                addDivisors(divisors, (long long)w * i + j);
            }
        }
        sort(ALL(divisors));
        divisors.erase(unique(ALL(divisors)), divisors.end());
        int answer = 0;
        if (w >= n * m) {
            answer += powerSum(26, w - n * m + 1);
        }
        foreach (iter, divisors) {
            LL length = *iter;
            if (length > w) {
                break;
            }
            if (length >= n * m) {
                (answer += MOD - power(26, length - n * m)) %= MOD;
            }
            bool conflict = false;
            map <int, int> colors;
            int count = length;
            for (int i = 0; i < n && !conflict; ++ i) {
                for (int j = 0; j < m && !conflict; ++ j) {
                    int k = ((long long)w * (i0 + i) + (j0 + j)) % length;
                    if (colors.find(k) == colors.end()) {
                        colors[k] = fragment[i][j];
                        count --;
                    } else {
                        conflict |= colors[k] != fragment[i][j];
                    }
                }
            }
            if (!conflict) {
                (answer += power(26, count)) %= MOD;
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
            string fragment[]         = {"dea",
 "abc"}
;
            int W                     = 7;
            int i0                    = 1;
            int j0                    = 1;
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = CharacterBoard().countGenerators(vector <string>(fragment, fragment + (sizeof fragment / sizeof fragment[0])), W, i0, j0);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            string fragment[]         = {"xyxxy"}
;
            int W                     = 6;
            int i0                    = 1;
            int j0                    = 0;
            int expected__            = 28;

            clock_t start__           = clock();
            int received__            = CharacterBoard().countGenerators(vector <string>(fragment, fragment + (sizeof fragment / sizeof fragment[0])), W, i0, j0);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            string fragment[]         = {"gogogo",
 "jijiji",
 "rarara"}
;
            int W                     = 6;
            int i0                    = 0;
            int j0                    = 0;
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = CharacterBoard().countGenerators(vector <string>(fragment, fragment + (sizeof fragment / sizeof fragment[0])), W, i0, j0);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            string fragment[]         = {"abababacac",
 "aaacacacbb",
 "ccabababab"}
;
            int W                     = 8827;
            int i0                    = 104;
            int j0                    = 6022;
            int expected__            = 829146844;

            clock_t start__           = clock();
            int received__            = CharacterBoard().countGenerators(vector <string>(fragment, fragment + (sizeof fragment / sizeof fragment[0])), W, i0, j0);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 4: {
            string fragment[]         = ;
            int W                     = ;
            int i0                    = ;
            int j0                    = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CharacterBoard().countGenerators(vector <string>(fragment, fragment + (sizeof fragment / sizeof fragment[0])), W, i0, j0);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 5: {
            string fragment[]         = ;
            int W                     = ;
            int i0                    = ;
            int j0                    = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CharacterBoard().countGenerators(vector <string>(fragment, fragment + (sizeof fragment / sizeof fragment[0])), W, i0, j0);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            string fragment[]         = ;
            int W                     = ;
            int i0                    = ;
            int j0                    = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = CharacterBoard().countGenerators(vector <string>(fragment, fragment + (sizeof fragment / sizeof fragment[0])), W, i0, j0);
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
