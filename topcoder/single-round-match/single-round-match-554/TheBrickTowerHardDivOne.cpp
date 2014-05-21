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

const int MOD = 1234567891;
const int N = 121;

struct Matrix {
    long long matrix[N][N];

    Matrix() {
        memset(matrix, 0, sizeof(matrix));
    }

    long long* operator[](int i) {
        return matrix[i];
    }

    const long long* operator[](int i) const {
        return matrix[i];
    }
};

Matrix operator *(const Matrix &a, const Matrix &b) {
    Matrix c;
    for (int i = 0; i < N; ++ i) {
        for (int j = 0; j < N; ++ j) {
            for (int k = 0; k < N; ++ k) {
                c[i][j] += (long long)a[i][k] * b[k][j] % MOD;
                if (c[i][j] >= MOD) {
                    c[i][j] -= MOD;
                }
            }
        }
    }
    return c;
}

Matrix pow(Matrix a, long long n) {
    Matrix result;
    for (int i = 0; i < N; ++ i) {
        result[i][i] = 1;
    }
    while (n) {
        if (n & 1) {
            result = result * a;
        }
        a = a * a;
        n >>= 1;
    }
    return result;
}

struct TheBrickTowerHardDivOne {
    typedef std::vector <int> State;

    void generate(std::vector <State> &states, State state, int n, int m) {
        if (n) {
            for (state.push_back(0); state.back() <= m; ++ state.back()) {
                generate(states, state, n - 1, std::max(m, state.back() + 1));
            }
        } else {
            states.push_back(state);
        }
    }

    int perm(int n, int k) {
        if (k > n) {
            return 0;
        }
        int result = 1;
        for (int i = 0; i < k; ++ i) {
            result = (long long)result * (n - i) % MOD;
        }
        return result;
    }

    std::vector <State> states;

    int getID(const State &state) {
        for (int i = 0; i < SIZE(states); ++ i) {
            if (states[i] == state) {
                return i;
            }
        }
        return -1;
    }

    int getColors(const State &state) {
        return *std::max_element(ALL(state)) + 1;
    }

    int getPairs(const State &state) {
        int result = 0;
        for (int i = 0; i < SIZE(state); ++ i) {
            for (int j = 0; j < i; ++ j) {
                result += state[i] == state[j] && __builtin_popcount(i ^ j) == 1;
            }
        }
        return result;
    }

    State cut(const State &state, int from, int length) {
        State newState;
        std::map <int, int> map;
        for (int i = from; i < from + length; ++ i) {
            if (!map.count(state[i])) {
                int id = SIZE(map);
                map[state[i]] = id;
            }
            newState.push_back(map[state[i]]);
        }
        return newState;
    }

    int find(int colors, int pairs, long long height) {
        generate(states, State(), 4, 0);
        std::vector <State> newStates;
        generate(newStates, State(), 8, 0);

        Matrix init;
        foreach (it, states) {
            if (getPairs(*it) <= pairs) {
                int ways = perm(colors, getColors(*it));
                init[0][getID(*it) * (pairs + 1) + getPairs(*it)] = ways;
                (init[0][N - 1] += ways) %= MOD;
            }
        }

        Matrix transfer;
        foreach (it, newStates) {
            State prefix = cut(*it, 0, 4);
            State suffix = cut(*it, 4, 4);
            int newPairs = getPairs(*it) - getPairs(prefix);
            int newColors = getColors(*it) - getColors(prefix);
            int ways = perm(colors - getColors(prefix), newColors);
            for (int ps = 0; ps + newPairs <= pairs; ++ ps) {
                int a = getID(prefix) * (pairs + 1) + ps;
                int b = getID(suffix) * (pairs + 1) + ps + newPairs;
                (transfer[a][b] += ways) %= MOD;
                (transfer[a][N - 1] += ways) %= MOD;
            }
        }
        transfer[N - 1][N - 1] = 1;

        return (init * (pow(transfer, height - 1)))[0][N - 1];
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
            int C                     = 2;
            int K                     = 0;
            long long H               = 2;
            int expected__            = 4;

            clock_t start__           = clock();
            int received__            = TheBrickTowerHardDivOne().find(C, K, H);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 1: {
            int C                     = 1;
            int K                     = 7;
            long long H               = 19;
            int expected__            = 1;

            clock_t start__           = clock();
            int received__            = TheBrickTowerHardDivOne().find(C, K, H);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 2: {
            int C                     = 2;
            int K                     = 3;
            long long H               = 1;
            int expected__            = 14;

            clock_t start__           = clock();
            int received__            = TheBrickTowerHardDivOne().find(C, K, H);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
        case 3: {
            int C                     = 4;
            int K                     = 7;
            long long H               = 47;
            int expected__            = 1008981254;

            clock_t start__           = clock();
            int received__            = TheBrickTowerHardDivOne().find(C, K, H);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }

        // custom cases

        case 4: {
            int C                     = 4747;
            int K                     = 7;
            long long H               = 474747474747474747LL;
            int expected__            = 0;

            clock_t start__           = clock();
            int received__            = TheBrickTowerHardDivOne().find(C, K, H);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }
/*      case 5: {
            int C                     = ;
            int K                     = ;
            long long H               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = TheBrickTowerHardDivOne().find(C, K, H);
            return verify_case(casenum__, expected__, received__, clock()-start__);
        }*/
/*      case 6: {
            int C                     = ;
            int K                     = ;
            long long H               = ;
            int expected__            = ;

            clock_t start__           = clock();
            int received__            = TheBrickTowerHardDivOne().find(C, K, H);
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
