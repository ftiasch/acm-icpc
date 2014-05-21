#include <cmath>
#include <cstdio>
#include <cstring>
#include <cstdlib>
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
using namespace std;

const int M = 100;

int power_sum[M + 1], coefficient[M + 1], binom[M + 1][M + 1], division[M + 1][M + 1];

struct TrickyInequality {
    static const int MOD = (int)1e9 + 7;

    int inverse(int a) {
        return a == 1 ? 1 : (long long)(MOD - MOD / a) * inverse(MOD % a) % MOD;
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

    int choose(int n, int k) {
        if (k > n) {
            return 0;
        }
        int ret = 1;
        for (int i = 0; i < k; ++ i) {
            ret = (long long)(n + MOD - i) % MOD * ret % MOD * inverse(i + 1) % MOD;
        }
        return ret;
    }

    int countSolutions(long long s, int t, int n, int m) {
        m -= n;
        memset(power_sum, 0, sizeof(power_sum));
        for (int i = 1; i <= t; ++ i) {
            int power = 1;
            for (int j = 0; j <= m; ++ j) {
                (power_sum[j] += power) %= MOD;
                power = (long long)power * (MOD - i) % MOD;
            }
        }
        memset(coefficient, 0, sizeof(coefficient));
        coefficient[0] = 1;
        for (int i = 0; i < m; ++ i) {
            for (int j = m; j >= 0; -- j) {
                coefficient[j] = (long long)coefficient[j] * (MOD - i) % MOD;
                if (j) {
                    (coefficient[j] += coefficient[j - 1]) %= MOD;
                }
                coefficient[j] = (long long)coefficient[j] * inverse(i + 1) % MOD;
            }
        }
        memset(binom, 0, sizeof(binom));
        for (int i = 0; i <= m; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
        memset(division, 0, sizeof(division));
        division[0][0] = 1;
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < m; ++ j) {
                for (int x = 1; j + x <= m; ++ x) {
                    (division[i + 1][j + x] += (long long)division[i][j] * binom[j + x][x] % MOD * power_sum[x] % MOD) %= MOD;
                }
            }
        }
        int answer = 0;
        for (int k = 0; k <= m; ++ k) {
            if (coefficient[k] == 0) {
                continue;
            }
            for (int j = 0; j <= m; ++ j) {
                int b = choose(n, j);
                if (b == 0) {
                    continue;
                }
                for (int i = 0; i <= k; ++ i) {
                    int ret = (long long)coefficient[k] * b % MOD * binom[k][i] % MOD * division[j][k - i] % MOD * power(t, n - j) % MOD * power(s % MOD, i) % MOD;
                    (answer += ret) %= MOD;
                }
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
            long long s               = 3;
            int t                     = 1;
            int n                     = 1;
            int m                     = 2;
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = TrickyInequality().countSolutions(s, t, n, m);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            long long s               = 5;
            int t                     = 2;
            int n                     = 2;
            int m                     = 3;
            int expected__            = 8;

            clock_t start__           = clock();
            int received__            = TrickyInequality().countSolutions(s, t, n, m);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            long long s               = 30;
            int t                     = 1;
            int n                     = 1;
            int m                     = 10;
            int expected__            = 10015005;

            clock_t start__           = clock();
            int received__            = TrickyInequality().countSolutions(s, t, n, m);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            long long s               = 2000;
            int t                     = 20;
            int n                     = 100;
            int m                     = 200;
            int expected__            = 35422605;

            clock_t start__           = clock();
            int received__            = TrickyInequality().countSolutions(s, t, n, m);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            long long s               = 999999999999999999LL;
            int t                     = 100000;
            int n                     = 999999900;
            int m                     = 1000000000;
            int expected__            = 90919453;

            clock_t start__           = clock();
            int received__            = TrickyInequality().countSolutions(s, t, n, m);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            long long s               = 999999999999999999LL;
            int t                     = 100000;
            int n                     = 1000000000;
            int m                     = 1000000000;
            int expected__            = 297793005;

            clock_t start__           = clock();
            int received__            = TrickyInequality().countSolutions(s, t, n, m);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            long long s               = ;
            int t                     = ;
            int n                     = ;
            int m                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = TrickyInequality().countSolutions(s, t, n, m);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            long long s               = ;
            int t                     = ;
            int n                     = ;
            int m                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = TrickyInequality().countSolutions(s, t, n, m);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            long long s               = ;
            int t                     = ;
            int n                     = ;
            int m                     = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = TrickyInequality().countSolutions(s, t, n, m);
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
