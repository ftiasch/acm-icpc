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

const static int PRIMES[] = {2, 3, 5, 7, 11, 13};

struct VerySmoothDecompositions {
    static const int MOD = (int)1e9 + 9;

    int solve(std::vector <std::string> segments) {
        std::string number;
        for (auto iterator : segments) {
            number += iterator;
        }
        std::vector <int> digits(SIZE(number) + 7 >> 3);
        for (int i = 0; i < SIZE(number); ++ i) {
            int &digit = digits[SIZE(number) - 1 - i >> 3];
            digit = digit * 10 + number[i] - '0';
        }
        int count[6];
        memset(count, 0, sizeof(count));
        for (int i = 0; i < 6; ++ i) {
            int p = PRIMES[i];
            while (true) {
                long long remainder = 0;
                for (int j = SIZE(digits) - 1; j >= 0; -- j) {
                    remainder = (remainder * 100000000 + digits[j]) % p;
                }
                if (remainder != 0) {
                    break;
                }
                count[i] ++;
                for (int j = SIZE(digits) - 1; j >= 0; -- j) {
                    digits[j] = (remainder += digits[j]) / p;
                    remainder = (remainder % p) * 100000000;
                }
                if (digits.back() == 0) {
                    digits.pop_back();
                }
            }
        }
        if (SIZE(digits) > 1 || digits[0] > 1) {
            return 0;
        }
        std::vector <int> twos = prepare(count[0], 4);
        std::vector <int> threes = prepare(count[1], 2);
        std::vector <std::vector <int>> ways(count[0] + 1, std::vector <int>(count[1] + 1));
        for (int i = 0; i <= count[0]; ++ i) {
            for (int j = 0; j <= count[1]; ++ j) {
                ways[i][j] = (long long)twos[i] * threes[j] % MOD;
            }
        }
        for (int x = 1; x <= 2; ++ x) {
            for (int i = x; i <= count[0]; ++ i) {
                for (int j = 1; j <= count[1]; ++ j) {
                    update(ways[i][j], ways[i - x][j - 1]);
                }
            }
        }
        int result = 0;
        for (int i = 0; i <= count[0]; ++ i) {
            for (int j = 0; j <= count[1]; ++ j) {
                int two = count[0] - i;
                int three = count[1] - j;
                int five = count[2];
                int seven = count[3];
                if (two + three <= five + seven && three <= five) {
                    update(result, (long long)ways[i][j] * std::max(std::min(five - three, two) - std::max(two - seven, 0) + 1, 0) % MOD);
                }
            }
        }
        return result;
    }

    std::vector <int> prepare(int count, int max) {
        std::vector <int> ways(count + 1);
        ways[0] = 1;
        for (int i = 1; i <= max; ++ i) {
            for (int j = i; j <= count; ++ j) {
                update(ways[j], ways[j - i]);
            }
        }
        return ways;
    }

    void update(int &x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
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
            string digits[]           = {"47"};
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = VerySmoothDecompositions().solve(vector <string>(digits, digits + (sizeof digits / sizeof digits[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            string digits[]           = {"2"};
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = VerySmoothDecompositions().solve(vector <string>(digits, digits + (sizeof digits / sizeof digits[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            string digits[]           = {"10"};
            int expected__            = 2;

            clock_t start__           = clock();
            int received__            = VerySmoothDecompositions().solve(vector <string>(digits, digits + (sizeof digits / sizeof digits[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            string digits[]           = {"36"};
            int expected__            = 7;

            clock_t start__           = clock();
            int received__            = VerySmoothDecompositions().solve(vector <string>(digits, digits + (sizeof digits / sizeof digits[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            string digits[]           = {"1","21"};
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = VerySmoothDecompositions().solve(vector <string>(digits, digits + (sizeof digits / sizeof digits[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 5: {
            string digits[]           = {"1024"};
            int expected__            = 23;

            clock_t start__           = clock();
            int received__            = VerySmoothDecompositions().solve(vector <string>(digits, digits + (sizeof digits / sizeof digits[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 6: {
            string digits[]           = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = VerySmoothDecompositions().solve(vector <string>(digits, digits + (sizeof digits / sizeof digits[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            string digits[]           = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = VerySmoothDecompositions().solve(vector <string>(digits, digits + (sizeof digits / sizeof digits[0])));
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 8: {
            string digits[]           = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = VerySmoothDecompositions().solve(vector <string>(digits, digits + (sizeof digits / sizeof digits[0])));
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
