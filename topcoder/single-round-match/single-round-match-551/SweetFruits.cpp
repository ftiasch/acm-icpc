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

struct SweetFruits {
    static const int N = 40;
    static const int MOD = (int)1e9 + 7;

    int binom[N + 1][N + 1], at_most[N + 1], exactly[N + 1], matrix[N + 1][N + 1], current[N + 1];
    std::vector <int> buckets[N + 1];

    int det(int n) {
        int result = 1;
        for (int j = 0; j < n; ++ j) {
            for (int i = j + 1; i < n; ++ i) {
                while (matrix[i][j] != 0) {
                    int scale = matrix[j][j] / matrix[i][j];
                    for (int k = 0; k < n; ++ k) {
                        (matrix[j][k] += MOD - (long long)scale * matrix[i][k] % MOD) %= MOD;
                        std::swap(matrix[i][k], matrix[j][k]);
                    }
                    result = (long long)result * (MOD - 1) % MOD;
                }
            }
        }
        for (int i = 0; i < n; ++ i) {
            result = (long long)result * matrix[i][i] % MOD;
        }
        return result;
    }

    int countTrees(vector <int> sweetness, int maxSweetness) {
        int n = SIZE(sweetness);
        std::vector <int> sweets;
        for (int i = 0; i < n; ++ i) {
            if (sweetness[i] != -1) {
                sweets.push_back(sweetness[i]);
            }
        }
        int m = SIZE(sweets);
        memset(binom, 0, sizeof(binom));
        for (int i = 0; i <= m; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
        for (int k = 0; k <= m; ++ k) {
            memset(matrix, 0, sizeof(matrix));
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < i; ++ j) {
                    if (i < k || i >= m) {
                        matrix[i][i] ++;
                        matrix[j][j] ++;
                        matrix[i][j] = matrix[j][i] = MOD - 1;
                    }
                }
            }
            at_most[k] = det(n - 1);
        }
        for (int i = 0; i <= m; ++ i) {
            exactly[i] = 0;
            for (int j = 0; j <= i; ++ j) {
                exactly[i] = (exactly[i] + MOD + (i - j & 1 ? -1LL : 1LL) * binom[i][j] * at_most[j] % MOD) % MOD;
            }
        }
        int m1 = m >> 1;
        int m2 = m - m1;
        for (int mask = 0; mask < 1 << m1; ++ mask) {
            int sum = 0;
            for (int i = 0; i < m1; ++ i) {
                if (mask >> i & 1) {
                    sum += sweets[i];
                }
            }
            buckets[__builtin_popcount(mask)].push_back(sum);
        }
        for (int i = 0; i <= m1; ++ i) {
            std::sort(ALL(buckets[i]));
        }
        std::vector <std::pair <int, int> > pairs;
        for (int mask = 0; mask < 1 << m2; ++ mask) {
            int sum = 0;
            for (int i = 0; i < m2; ++ i) {
                if (mask >> i & 1) {
                    sum += sweets[m1 + i];
                }
            }
            pairs.push_back(std::make_pair(sum, __builtin_popcount(mask)));
        }
        std::sort(ALL(pairs));
        int result = 0;
        for (int i = 0; i <= m1; ++ i) {
            current[i] = SIZE(buckets[i]);
        }
        foreach (it, pairs) {
            int sum = it->first;
            for (int i = 0; i <= m1; ++ i) {
                while (current[i] && buckets[i][current[i] - 1] + sum > maxSweetness) {
                    current[i] --;
                }
                (result += (long long)current[i] * exactly[i + it->second] % MOD) %= MOD;
            }
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
            int sweetness[]           = {1, 2, -1, 3};
            int maxSweetness          = 3;
            int expected__            = 3;

            clock_t start__           = clock();
            int received__            = SweetFruits().countTrees(vector <int>(sweetness, sweetness + (sizeof sweetness / sizeof sweetness[0])), maxSweetness);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int sweetness[]           = {1, 2, -1, 3};
            int maxSweetness          = 5;
            int expected__            = 7;

            clock_t start__           = clock();
            int received__            = SweetFruits().countTrees(vector <int>(sweetness, sweetness + (sizeof sweetness / sizeof sweetness[0])), maxSweetness);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int sweetness[]           = {-1, -1, 2, 5, 5};
            int maxSweetness          = 6;
            int expected__            = 20;

            clock_t start__           = clock();
            int received__            = SweetFruits().countTrees(vector <int>(sweetness, sweetness + (sizeof sweetness / sizeof sweetness[0])), maxSweetness);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int sweetness[]           = {2, 6, 8, 4, 1, 10, -1, -1, -1, -1};
            int maxSweetness          = 15;
            int expected__            = 17024000;

            clock_t start__           = clock();
            int received__            = SweetFruits().countTrees(vector <int>(sweetness, sweetness + (sizeof sweetness / sizeof sweetness[0])), maxSweetness);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 4: {
            int sweetness[]           = {1078451, -1, 21580110, 8284711, -1, 4202301, 3427559, 8261270, -1, 16176713, 22915672, 24495540, 19236, 5477666, 12280316, 3305896, 17917887, 564911, 22190488, 21843923, 23389728, 14641920, 9590140, 12909561, 20405638, 100184, 23336457, 12780498, 18859535, 23180993, 10278898, 5753075, 21250919, 17563422, 10934412, 22557980, 24895749, 7593671, 10834579, 5606562};
            int maxSweetness          = 245243285;
            int expected__            = 47225123;

            clock_t start__           = clock();
            int received__            = SweetFruits().countTrees(vector <int>(sweetness, sweetness + (sizeof sweetness / sizeof sweetness[0])), maxSweetness);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

/*      case 5: {
            int sweetness[]           = ;
            int maxSweetness          = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = SweetFruits().countTrees(vector <int>(sweetness, sweetness + (sizeof sweetness / sizeof sweetness[0])), maxSweetness);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int sweetness[]           = ;
            int maxSweetness          = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = SweetFruits().countTrees(vector <int>(sweetness, sweetness + (sizeof sweetness / sizeof sweetness[0])), maxSweetness);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 7: {
            int sweetness[]           = ;
            int maxSweetness          = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = SweetFruits().countTrees(vector <int>(sweetness, sweetness + (sizeof sweetness / sizeof sweetness[0])), maxSweetness);
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
