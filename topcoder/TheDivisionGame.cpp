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

const int N = 1000002;

int sg[N], number[N];

struct TheDivisionGame {
    bool is_prime(int n) {
        for (int i = 2; i * i <= n; ++ i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    long long countWinningIntervals(int begin, int end) {
        for (int i = begin; i <= end; ++ i) {
            number[i - begin] = i;
        }
        memset(sg, 0, sizeof(sg));
        for (int i = 2; i * i <= end; ++ i) {
            if (is_prime(i)) {
                int t = begin / i * i;
                if (t < begin) {
                    t += i;
                }
                while (t <= end) {
                    while (number[t - begin] % i == 0) {
                        sg[t - begin] ++;
                        number[t - begin] /= i;
                    }
                    t += i;
                }
            }
        }
        int n = end - begin + 1;
        for (int i = 0; i < n; ++ i) {
            if (number[i] > 1) {
                sg[i] ++;
            }
        }
        sg[n] = 0;
        for (int i = n - 1; i >= 0; -- i) {
            sg[i] ^= sg[i + 1];
        }
        map <int, int> hash;
        hash[0] ++;
        long long answer = 0;
        for (int i = n - 1; i >= 0; -- i) {
            answer += hash[sg[i]];
            hash[sg[i]] ++;
        }
        return (long long)n * (n + 1) / 2 - answer;
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
            int L                     = 9;
            int R                     = 10;
            long long expected__      = 2;

            clock_t start__           = clock();
            long long received__      = TheDivisionGame().countWinningIntervals(L, R);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int L                     = 2;
            int R                     = 5;
            long long expected__      = 9;

            clock_t start__           = clock();
            long long received__      = TheDivisionGame().countWinningIntervals(L, R);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int L                     = 2;
            int R                     = 6;
            long long expected__      = 13;

            clock_t start__           = clock();
            long long received__      = TheDivisionGame().countWinningIntervals(L, R);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int L                     = 2;
            int R                     = 100;
            long long expected__      = 4345;

            clock_t start__           = clock();
            long long received__      = TheDivisionGame().countWinningIntervals(L, R);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int L                     = 12566125;
            int R                     = 12567777;
            long long expected__      = 1313432;

            clock_t start__           = clock();
            long long received__      = TheDivisionGame().countWinningIntervals(L, R);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

      case 5: {
            int L                     = 1000000000;
            int R                     = L + 1000000;
            long long expected__      = -1;

            clock_t start__           = clock();
            long long received__      = TheDivisionGame().countWinningIntervals(L, R);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
/*      case 6: {
            int L                     = ;
            int R                     = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TheDivisionGame().countWinningIntervals(L, R);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int L                     = ;
            int R                     = ;
            long long expected__      = ;

            clock_t start__           = clock();
            long long received__      = TheDivisionGame().countWinningIntervals(L, R);
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
